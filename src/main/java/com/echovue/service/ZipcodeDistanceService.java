package com.echovue.service;

import com.echovue.model.Distance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ZipcodeDistanceService {
    private static final Logger LOGGER = Logger.getLogger("ZipcodeDistanceService");
    private URL zipcodeapi;
    private String apiKey = "P6wa1NepBwp5wssOz9sXj7rfL3sPOvGDBdOC022CyrH5U9UtjmrDuSSfWl4KQLos";

    public Optional<Double> getDistance(final String zipCode1,
                                        final String zipCode2) {
        try {
            zipcodeapi = new URL("https://www.zipcodeapi.com/rest/");

        RestTemplate restTemplate = new RestTemplate();
        return Optional.of(restTemplate.getForObject(
                zipcodeapi.toString() + apiKey + "/distance.json/"
                + zipCode1 + "/" + zipCode2 + "/mile", Distance.class).getDistance());

        } catch (MalformedURLException urlException) {
            LOGGER.log(Level.SEVERE, "Invalid URL for ZipCodeApi");
        } catch (org.springframework.web.client.HttpClientErrorException ex) {
            LOGGER.log(Level.SEVERE, "Bad request");
        }
        return Optional.empty();
    }
}
