package com.sagar.shorttheurl.controller;

import com.sagar.shorttheurl.model.Response;
import com.sagar.shorttheurl.model.UrlModel;
import com.sagar.shorttheurl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://shortmeurl.up.railway.app")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/create")
    public ResponseEntity<?> getShortUrl(@RequestBody UrlModel urlModel) {
        Response response = shortUrlService.getShortUrl(urlModel);
        return new ResponseEntity<>(response.getMessage(), HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-original")
    public ResponseEntity<?> getOriginalUrl(@RequestBody UrlModel urlModel, @RequestParam String ip) {
        System.out.println(ip);
        Response response = shortUrlService.getOriginalUrl(urlModel);
        return new ResponseEntity<>(response.getMessage(), HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-original/{shortCode}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortCode) {
        Response response = shortUrlService.getOriginalUrl(shortCode);
        return new ResponseEntity<>(response.getMessage(), HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PutMapping("/update-url")
    public ResponseEntity<?> updateOriginalUrl(@RequestBody UrlModel urlModel) {
        Response response = shortUrlService.updateOriginalUrl(urlModel);
        return new ResponseEntity<>(response.getMessage(), HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/delete-url")
    public ResponseEntity<?> deleteUrl(@RequestBody UrlModel urlModel) {
        Response response = shortUrlService.deleteUrl(urlModel);
        return new ResponseEntity<>(response.getMessage(), HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
