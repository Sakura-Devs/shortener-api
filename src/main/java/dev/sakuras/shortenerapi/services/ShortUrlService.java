package dev.sakuras.shortenerapi.services;

import dev.sakuras.shortenerapi.models.ShortUrl;
import org.springframework.stereotype.Service;

@Service
public interface ShortUrlService {
    ShortUrl shortenUrl(String url);
    String getOriginalUrl(String shortUrl);
}
