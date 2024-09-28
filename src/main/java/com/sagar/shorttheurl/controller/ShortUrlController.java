package com.sagar.shorttheurl.controller;

import com.sagar.shorttheurl.model.UrlModel;
import com.sagar.shorttheurl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/short")
    public String getShortUrl(@RequestBody UrlModel urlModel) {
        return shortUrlService.getShortUrl(urlModel);
    }

    @GetMapping("/original")
    public String getOriginalUrl(@RequestBody UrlModel urlModel) {
        return shortUrlService.getOriginalUrl(urlModel);
    }
}
