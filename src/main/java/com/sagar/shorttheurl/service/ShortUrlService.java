package com.sagar.shorttheurl.service;

import com.sagar.shorttheurl.model.UrlModel;
import com.sagar.shorttheurl.repo.ShortUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ShortUrlService {

    @Autowired
    private ShortUrlRepo shortUrlRepo;

    private static final String BASE_URL = "http://localhost:3000/";

    public String getShortUrl(UrlModel urlModel) {
        try{
            String shortUrl = generateShortUrl();

            urlModel.setShortUrl(shortUrl);
            shortUrlRepo.save(urlModel);
            return BASE_URL + urlModel.getShortUrl();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String getOriginalUrl(UrlModel urlModel) {
        try{
            UrlModel url = shortUrlRepo.findByShortUrl(urlModel.getShortUrl());
            return url.getOriginalUrl();
        } catch (Exception e){
            return e.getMessage();
        }
    }

    private String generateShortUrl() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int LENGTH = 10; // Length of the random string
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder result = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }
}
