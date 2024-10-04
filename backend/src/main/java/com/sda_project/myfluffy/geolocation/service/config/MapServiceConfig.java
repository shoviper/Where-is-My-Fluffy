package com.sda_project.myfluffy.geolocation.service.config;

import com.sda_project.myfluffy.geolocation.adapter.GoogleMapAdapter;
import com.sda_project.myfluffy.geolocation.adapter.MapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapServiceConfig {

    @Value("${api.google-map.url}")
    private String googleMapUrl;

    @Bean
    public MapService mapService() {
        return new GoogleMapAdapter(googleMapUrl);  // Use GoogleMapAdapter by default
        // In the future, switch to OpenStreetMapAdapter or others:
        // return new OpenStreetMapAdapter();
    }
}
