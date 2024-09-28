package com.sagar.shorttheurl.repo;

import com.sagar.shorttheurl.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepo extends JpaRepository<UrlModel, Long> {
    UrlModel findByShortUrl(String shortUrl);
}
