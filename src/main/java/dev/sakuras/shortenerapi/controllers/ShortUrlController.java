package dev.sakuras.shortenerapi.controllers;

import dev.sakuras.shortenerapi.models.dto.ShortUrlDTO;
import dev.sakuras.shortenerapi.services.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shorten")
    public ShortUrlDTO shortenUrl(@RequestParam String url) {
        return shortUrlService.shortenUrl(url).toDTO();
    }

    @GetMapping("/{code}")
    public void getOriginalUrl(@PathVariable String code, HttpServletResponse response) {
        // Get the original URL from the code
        String originalUrl = shortUrlService.getOriginalUrl(code);
        // Redirect to the original URL
        response.setHeader("Location", originalUrl);
        response.setStatus(302);
    }

}
