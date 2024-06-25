package dev.sakuras.shortenerapi.repositories;

import dev.sakuras.shortenerapi.models.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {
    Optional<ShortUrl> findByCode(String code);
    Optional<ShortUrl> findFirstByOrderByCodeDesc();
    Optional<ShortUrl> findByOriginalURL(String url);
}