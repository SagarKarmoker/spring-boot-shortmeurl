package com.sagar.shorttheurl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@NoArgsConstructor
public class UrlModel {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String shortUrl;
    private int visitCounter;

    public UrlModel(String originalUrl, String shortUrl){
        this.id = this.getId();
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        visitCounter = 0;
    }
}
