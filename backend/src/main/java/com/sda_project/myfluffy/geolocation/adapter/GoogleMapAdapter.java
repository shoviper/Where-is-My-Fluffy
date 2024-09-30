package com.sda_project.myfluffy.geolocation.adapter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleMapAdapter implements MapService {

    private final String googleMapBaseUrl;

    public GoogleMapAdapter(String googleMapBaseUrl) {
        this.googleMapBaseUrl = googleMapBaseUrl;
    }

    @Override
    public String buildMapUrl(String address) {
        if (address == null || address.isEmpty()) {
            return googleMapBaseUrl + "No+Address+Available";
        }
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        return googleMapBaseUrl + encodedAddress;
    }
}

