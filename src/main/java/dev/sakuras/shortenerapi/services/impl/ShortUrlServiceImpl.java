package dev.sakuras.shortenerapi.services.impl;

import dev.sakuras.shortenerapi.models.ShortUrl;
import dev.sakuras.shortenerapi.services.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sakuras.shortenerapi.repositories.ShortUrlRepository;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    public ShortUrl shortenUrl(String url) {
        // Get the last code
        String lastCode = shortUrlRepository.findLastCode();
        // Parse to int and increment
        int newCode = Integer.parseInt(lastCode) + 1;
        // Parse the new code to hexadecimal
        String code = Integer.toString(newCode, 16);

        // Create the new short URL
        ShortUrl shortUrl = ShortUrl.builder()
                .originalURL(url)
                .code(code)
                .shortURL("https://shortener.es/" + code)
                .build();
        // Save the new short URL
        shortUrlRepository.save(shortUrl);

        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        // Find the short URL
        ShortUrl shortUrlObj = shortUrlRepository.findByShortURL(shortUrl);
        // Return the original URL
        return shortUrlObj.getOriginalURL();
    }
}
