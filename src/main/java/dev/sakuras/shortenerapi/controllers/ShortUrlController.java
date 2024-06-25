package dev.sakuras.shortenerapi.controllers;

import dev.sakuras.shortenerapi.models.ShortUrl;
import dev.sakuras.shortenerapi.services.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url/")
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @PostMapping("/shorten")
    public ShortUrl shortenUrl(@RequestParam String url) {
        return shortUrlService.shortenUrl(url);
    }

    @PostMapping("/shorten/{code}")
    public Object shortenUrl(@RequestParam String url, @PathVariable String code) {
        return shortUrlService.shortenUrl(url, code);
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
