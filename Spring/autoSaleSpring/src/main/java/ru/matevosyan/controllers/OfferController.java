package ru.matevosyan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private static final Logger LOG = LoggerFactory.getLogger(OfferController.class.getName());
    private static final String IMAGE_PACKAGE = "images";
    private static final String SEPARATOR = System.getProperty("file.separator");
    private final OfferDataRepository<Offer> offerRepository;
    private final FileUploader uploader;
    private final FormatJson format;

    /**
     * OfferController constructor.
     * @param offerRepository object.
     * @param uploader object
     * @param format object.
     */
    @Autowired
    public OfferController(OfferDataRepository<Offer> offerRepository, FileUploader uploader, FormatJson format) {
        this.offerRepository = offerRepository;
        this.uploader = uploader;
        this.format = format;
    }

    /**
     * Upload file and use received json for format path to write this file.
     * @param file to write.
     * @param req HttpServletRequest to get the attribute.
     * @param offer model.
     */
    @PostMapping(value = "/**/uploadFile", consumes = {"multipart/form-data"})
    protected void upload(@RequestPart("file") MultipartFile file,
                          HttpServletRequest req, @RequestPart("jsonData") Offer offer) {
        User user = (User) req.getSession().getAttribute("currentUser");
        String path = String.format("%s%s/%s/%s%s/%s", req.getSession().getServletContext().getRealPath("/"),
                "images", user.getName(), offer.getCar().getBrand(),
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
        return format.getResponseList(this.offerRepository.findAll());
    }

    /**
     * Save passing Offer.
     * @param offer model.
     * @param req HttpServletRequest to get the attribute.
     * @return formatted json to view.
     */
    @PostMapping(value = "/**/offer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    protected JsonResponse add(@RequestBody Offer offer, HttpServletRequest req) {
        String name = offer.getPicture();
        User user = (User) req.getSession().getAttribute("currentUser");
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
        return format.getResponseList(this.offerRepository.findByPostingDateBetween(start, end));
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
        return format.getResponseList(this.offerRepository.findAllByCarBrand(brands));
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
        return format.getResponseList(this.offerRepository.findByPictureNotContaining(name));
    }
}
