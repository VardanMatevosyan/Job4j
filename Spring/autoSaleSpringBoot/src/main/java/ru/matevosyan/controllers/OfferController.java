package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.matevosyan.json.FormatJson;
import ru.matevosyan.json.entity.JsonResponse;
import ru.matevosyan.repository.OfferDataRepository;
import ru.matevosyan.utils.FileUploader;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Controller for actions wit offer model.
 */
@Controller
public class OfferController {
    private static final String IMAGE_PACKAGE = "images";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private final OfferDataRepository<Offer> offerRepository;
    private final FileUploader uploader;
    private final FormatJson format;
    private final Environment environment;

    /**
     * OfferController constructor.
     * @param offerRepository object.
     * @param uploader object
     * @param format object.
     * @param environment for app properties.
     */
    @Autowired
    public OfferController(OfferDataRepository<Offer> offerRepository, FileUploader uploader,
                           FormatJson format, Environment environment) {
        this.offerRepository = offerRepository;
        this.uploader = uploader;
        this.format = format;
        this.environment = environment;
    }

    /**
     * Upload file and use received json for format path to write this file.
     * @param file to write.
     * @param offer model.
     * @param httpSession http session.
     */
    @PostMapping(value = "/**/uploadFile", consumes = {"multipart/form-data"})
    public ResponseEntity<Void> upload(@RequestPart("file") MultipartFile file,
                          @RequestPart("jsonData") Offer offer, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("currentUser");
        String path = String.format("%s%s/%s/%s%s/%s", this.environment.getProperty("imgFilePath"),
                "static/images", user.getName(), offer.getCar().getBrand(),
                offer.getCar().getModelVehicle(), offer.getPicture());
        this.uploader.upload(file, path);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * Get all offers.
     * @return list of offer.
     */
    @GetMapping(value = "/**/allOffers")
    @ResponseBody
    public List<JsonResponse> getAll() {
        return format.getResponseList(this.offerRepository.findAllOffers());
//        new Sort(Sort.Direction.DESC, "postingDate")
    }

    /**
     * Save passing Offer.
     * @param offer model.
     * @param httpSession HttpServletRequest to get the attribute.
     * @return formatted json to view.
     */
    @PostMapping(value = "/**/offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonResponse add(@RequestBody Offer offer, HttpSession httpSession) {
        String name = offer.getPicture();
        User user = (User) httpSession.getAttribute("currentUser");
        offer.setUser(user);
        offer.setPostingDate(new Timestamp(System.currentTimeMillis()));
        offer.setSoldState(false);

        if (name.contains("default")) {
            offer.setPicture(String.format("%s%s%s", IMAGE_PACKAGE, SEPARATOR, name));
        } else {
            offer.setPicture(String.format("%s/%s/%s%s/%s", "images", offer.getUser().getName(),
                    offer.getCar().getBrand(), offer.getCar().getModelVehicle(), name));
        }

        Offer save = this.offerRepository.save(offer);
        List<Offer> offers = Arrays.asList(save);
        List<JsonResponse> list = format.getResponseList(offers);

        return list.get(0);
    }

    /**
     * Change the sell status.
     * @param offer model.
     */
    @PutMapping(value = "/**/offerSellStatusValue")
    public ResponseEntity<String> change(@RequestBody Offer offer) {
        Integer changed = this.offerRepository.changeSellState(offer.getSoldState(), offer.getId());
        if (changed == 1) {
            return new ResponseEntity<String>("true", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("false", HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Get last day offers.
     * @return list of offers.
     */
    @GetMapping(value = "/**/lastAddedOffers")
    @ResponseBody
    public List<JsonResponse> lastDayOffers() {
        Timestamp start = Timestamp.valueOf(LocalDateTime.now().withHour(LocalTime.MIDNIGHT.getHour())
                .withMinute(LocalTime.MIDNIGHT.getMinute())
                .withSecond(LocalTime.MIDNIGHT.getSecond())
                .withNano(LocalTime.MIDNIGHT.getNano()));
        Timestamp end = Timestamp.valueOf(LocalDateTime.now());
        return format.getResponseList(this.offerRepository.findByPostingDateBetweenOrderByPostingDateDesc(start, end));
    }

    /**
     * Get offers by brand.
     * @param req HttpServletRequest to get the value.
     * @return return list of offers.
     */
    @GetMapping(value = "/**/withBrands")
    @ResponseBody
    public List<JsonResponse> filterByBrand(HttpServletRequest req) {
        List<String> brands = getBrands(req);
        return format.getResponseList(this.offerRepository.findAllByCarBrandInOrderByPostingDateDesc(brands));
    }

    /**
     * Return list of brands getting from request.
     * @param req client request.
     * @return list of brands.
     */
    private List<String> getBrands(final HttpServletRequest req) {
        String[] brandsJson = req.getParameterValues("brand");
        return Arrays.asList(brandsJson);
    }

    /**
     * Get list of offers by photo.
     * @return list of offers.
     */
    @GetMapping(value = "/**/withPhoto")
    @ResponseBody
    public List<JsonResponse> getOffersWithPhoto() {
        String name = "default.jpeg";
        return format.getResponseList(this.offerRepository.findByPictureNotContainingOrderByPostingDateDesc(name));
    }

    @DeleteMapping(value = "/**/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Offer> optional = this.offerRepository.findById(id);
        if (optional.isPresent()) {
            this.offerRepository.deleteById(id);
            Offer offer = optional.get();
            String dir = String.format("%s%s%s%s%s%s%s%s%s%s", this.environment.getProperty("imgFilePath"), SEPARATOR,
                    "static", SEPARATOR, IMAGE_PACKAGE, SEPARATOR, offer.getUser().getName(), SEPARATOR,
                    offer.getCar().getBrand(), offer.getCar().getModelVehicle());
            this.uploader.delete(new File(dir));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<Offer>(offer, responseHeaders, HttpStatus.OK);
        } else {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.TEXT_HTML);
            String msg = "Offer is not found";
            return new ResponseEntity<String>(msg, responseHeaders, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update offer and offers picture.
     * @param id offer id.
     * @param offer object;
     * @param session user.
     * @return ResponseEntity.
     */
    @PutMapping(value = "/**/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOffer(@PathVariable Integer id, @RequestBody Offer offer, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        Offer exists = this.offerRepository.getOne(id);
        if (exists.getId() == id.intValue()) {
            offer.setId(id);
            offer.setUser(currentUser);
            offer.getCar().setId(exists.getCar().getId());
            offer.setPostingDate(exists.getPostingDate());
            offer.setSoldState(exists.getSoldState());
            String pictureName = offer.getPicture();
            if (pictureName.contains("default")) {
                offer.setPicture(String.format("%s%s%s", IMAGE_PACKAGE, SEPARATOR, pictureName));
            } else {
                offer.setPicture(String.format("%s/%s/%s%s/%s", "images", currentUser.getName(),
                        offer.getCar().getBrand(), offer.getCar().getModelVehicle(), pictureName));
            }
            String path = exists.getPicture();
            String existsOfferFileName = String.format("%s%s%s%s%s", this.environment.getProperty("imgFilePath"), SEPARATOR,
                    "static", SEPARATOR, path);
            this.uploader.delete(new File(existsOfferFileName));

            Offer save = this.offerRepository.save(offer);
            return new ResponseEntity<Offer>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Can't find offer, please try again", HttpStatus.NOT_FOUND);
        }
    }
}
