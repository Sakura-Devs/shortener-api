package dev.sakuras.shortenerapi.repositories;

import dev.sakuras.shortenerapi.models.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
    ShortUrl findByShortURL(String shortURL);
    @Query(value = "{}", sort = "{code: -1}")
    String findLastCode();
}
