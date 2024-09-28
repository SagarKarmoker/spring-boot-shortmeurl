package com.sagar.shorttheurl.service;

import com.sagar.shorttheurl.model.Response;
import com.sagar.shorttheurl.model.UrlModel;
import com.sagar.shorttheurl.repo.ShortUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ShortUrlService {

    @Autowired
    private ShortUrlRepo shortUrlRepo;

    private static final String BASE_URL = "http://localhost:3000/short/";

    public Response getShortUrl(UrlModel urlModel) {
        try{
            String shortUrl = generateShortUrl();

            // check existing
            UrlModel url = shortUrlRepo.findByShortUrl(shortUrl);
            if(url != null){
                return null;
            }
            urlModel.setShortUrl(shortUrl);
            shortUrlRepo.save(urlModel);
            return new Response(BASE_URL + urlModel.getShortUrl(), HttpStatus.CREATED);
        }catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response getOriginalUrl(UrlModel urlModel) {
        try{
            UrlModel url = shortUrlRepo.findByShortUrl(urlModel.getShortUrl());
            if(url != null){
                return new Response(url.getOriginalUrl(), HttpStatus.OK);
            }
            return new Response("URL not found", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response getOriginalUrl(String shortCode) {
        try{
            UrlModel url = shortUrlRepo.findByShortUrl(shortCode);
            if(url != null){
                return new Response(url.getOriginalUrl(), HttpStatus.OK);
            }
            return new Response("URL not found", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response updateOriginalUrl(UrlModel urlModel) {
        try{
            UrlModel existingModel = shortUrlRepo.findByShortUrl(urlModel.getShortUrl());

            if(existingModel != null){
                // setting new original url
                existingModel.setOriginalUrl(urlModel.getOriginalUrl());

                shortUrlRepo.save(existingModel);
                return new Response(BASE_URL + urlModel.getShortUrl(), HttpStatus.OK);
            }

            return new Response("URL not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Response deleteUrl(UrlModel urlModel) {
        try {
            UrlModel existingModel = shortUrlRepo.findByShortUrl(urlModel.getShortUrl());
            if(existingModel != null){
                shortUrlRepo.delete(existingModel);
                return new Response("Deleted Url: " + urlModel.getShortUrl(), HttpStatus.OK);
            }
            return new Response("URL not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
