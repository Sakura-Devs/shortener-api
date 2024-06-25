package dev.sakuras.shortenerapi.services;

import dev.sakuras.shortenerapi.models.ShortUrl;
import org.springframework.stereotype.Service;

@Service
public interface ShortUrlService {
    ShortUrl shortenUrl(String url);
    Object shortenUrl(String url, String code);
    String getOriginalUrl(String shortUrl);
}
