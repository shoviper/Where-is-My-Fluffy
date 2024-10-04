package com.sda_project.myfluffy.ads.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sda_project.myfluffy.ads.plugin.MyAdsPlugin;
import com.sda_project.myfluffy.ads.dto.AdsDto;

@RestController
@RequestMapping(path = "/ads", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AdsController {

    MyAdsPlugin myAdsPlugin = new MyAdsPlugin();
    @GetMapping
    public ResponseEntity<AdsDto> fetchAds() {
        Object ads = myAdsPlugin.sendAd();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AdsDto.builder()
                        .ads(ads)
                        .build());
    }

}
