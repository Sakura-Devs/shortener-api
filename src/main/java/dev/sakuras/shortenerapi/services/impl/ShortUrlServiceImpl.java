package dev.sakuras.shortenerapi.services.impl;

import dev.sakuras.shortenerapi.models.ShortUrl;
import dev.sakuras.shortenerapi.models.dto.ErrorDTO;
import dev.sakuras.shortenerapi.services.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sakuras.shortenerapi.repositories.ShortUrlRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    /**
     * Shorten a URL
     * @param url URL to shorten
     * @return ShortUrl
     */
    @Override
    public ShortUrl shortenUrl(String url) {
        ShortUrl shortUrl;
        // Check if the URL is already shortened
        shortUrl = shortUrlRepository.findByOriginalURL(url).orElse(null);
        // If the URL is already shortened, return it
        if (shortUrl != null) {
            return shortUrl;
        }

        int newCode;
        // Get the last document
        ShortUrl lastDocument = shortUrlRepository.findFirstByOrderByCodeDesc().orElse(null);
        if (lastDocument == null) {
            // If there is no short URL, start from 0
            newCode = 0;
        } else {
            // Get the last code
            String lastCode = lastDocument.getCode();
            // Parse to int and increment
            newCode = Integer.parseInt(lastCode) + 1;
        }

        // Parse the new code to hexadecimal
        String code = Integer.toString(newCode, 16);
        // Create the new short URL
        shortUrl = ShortUrl.builder()
                .originalURL(url)
                .code(code)
                .shortURL("https://shortener.es/" + code)
                .build();
        // Save the new short URL
        shortUrlRepository.save(shortUrl);

        // Return the short URL
        return shortUrl;
    }

    /**
     * Shorten a URL with a custom code
     * @param url URL to shorten
     * @param code Custom code
     * @return ShortUrl
     */
    @Override
    public Object shortenUrl(String url, String code) {
        // Check if the custom code is already in use
        ShortUrl shortUrl = shortUrlRepository.findByCode(code).orElse(null);
        // If the custom code is already in use, return error
        if (shortUrl != null) {
            return new ErrorDTO("The code is already in use", Timestamp.valueOf(LocalDateTime.now()));
        }

        // Create the new short URL
        shortUrl = ShortUrl.builder()
                .originalURL(url)
                .code(code)
                .shortURL("https://shortener.es/" + code)
                .build();
        // Save the new short URL
        shortUrlRepository.save(shortUrl);

        // Return the short URL
        return shortUrl;
    }

    /**
     * Get the original URL from a short URL
     * @param code Short URL code
     * @return Original URL
     */
    @Override
    public String getOriginalUrl(String code) {
        // Find the short URL
        ShortUrl shortUrl = shortUrlRepository.findByCode(code).orElse(null);
        // Return the original URL
        return shortUrl == null ? "https://shortener.es" : shortUrl.getOriginalURL();
    }
}
