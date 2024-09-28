package com.sagar.shorttheurl.controller;

import com.sagar.shorttheurl.model.Response;
import com.sagar.shorttheurl.model.UrlModel;
import com.sagar.shorttheurl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/create")
    public Response getShortUrl(@RequestBody UrlModel urlModel) {
        return shortUrlService.getShortUrl(urlModel);
    }

    @GetMapping("/get-original")
    public Response getOriginalUrl(@RequestBody UrlModel urlModel, @RequestParam String ip) {
        System.out.println(ip);
        return shortUrlService.getOriginalUrl(urlModel);
    }

    @PutMapping("/update-url")
    public Response updateOriginalUrl(@RequestBody UrlModel urlModel) {
        return shortUrlService.updateOriginalUrl(urlModel);
    }

    @DeleteMapping("/delete-url")
    public Response deleteUrl(@RequestBody UrlModel urlModel) {
        return shortUrlService.deleteUrl(urlModel);
    }
}
