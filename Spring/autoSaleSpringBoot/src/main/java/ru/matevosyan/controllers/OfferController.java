package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.matevosyan.json.FormatJson;
import ru.matevosyan.json.entity.JsonResponse;
import ru.matevosyan.repository.OfferDataRepository;
import ru.matevosyan.utils.FileUploader;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

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
    protected void upload(@RequestPart("file") MultipartFile file,
                          @RequestPart("jsonData") Offer offer, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("currentUser");
        String path = String.format("%s%s/%s/%s%s/%s", this.environment.getProperty("imgFilePath"),
                "static/images", user.getName(), offer.getCar().getBrand(),
                offer.getCar().getModelVehicle(), offer.getPicture());
        this.uploader.upload(file, path);
    }

    /**
     * Get all offers.
     * @return list of offer.
     */
    @GetMapping(value = "/**/allOffers")
    @ResponseBody
    protected List<JsonResponse> getAll() {
        return format.getResponseList(this.offerRepository.findAll(new Sort(Sort.Direction.DESC, "postingDate")));
    }

    /**
     * Save passing Offer.
     * @param offer model.
     * @param httpSession HttpServletRequest to get the attribute.
     * @return formatted json to view.
     */
    @PostMapping(value = "/**/offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected JsonResponse add(@RequestBody Offer offer, HttpSession httpSession) {
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
    protected void change(@RequestBody Offer offer) {
        this.offerRepository.changeSellState(offer.getSoldState(), offer.getId());
    }

    /**
     * Get last day offers.
     * @return list of offers.
     */
    @GetMapping(value = "/**/lastAddedOffers")
    @ResponseBody
    protected List<JsonResponse> lastDayOffers() {
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
    protected List<JsonResponse> filterByBrand(HttpServletRequest req) {
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
    protected List<JsonResponse> getOffersWithPhoto() {
        String name = "default.jpeg";
        return format.getResponseList(this.offerRepository.findByPictureNotContainingOrderByPostingDateDesc(name));
    }
}
