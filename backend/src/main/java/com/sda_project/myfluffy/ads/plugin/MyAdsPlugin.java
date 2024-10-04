// plugin/MyRewardPlugin.java
package com.sda_project.myfluffy.ads.plugin;

import org.springframework.stereotype.Component;

@Component
public class MyAdsPlugin implements AdsPlugin {

    private String url1 = "https://s3images.coroflot.com/user_files/individual_files/715724_ybg_cowaio0uohcx_5rn5a6xw.jpg";
    private String url2 = "https://www.adgully.com/img/800/202106/pedigree-small-dog.jpg";
    @Override
    public Object sendAd() {
        // can implenet google ads like this
        String url = random_select_ads();
        // In actual use, you would call Google Ads APIs to send the ad.
        System.out.println("Ad sent via our plugin.");

        return url;
    }

    public String random_select_ads() {
        if (Math.random() < 0.5) {
            return url1;
        } else {
            return url2;
        }

    }
    
}
