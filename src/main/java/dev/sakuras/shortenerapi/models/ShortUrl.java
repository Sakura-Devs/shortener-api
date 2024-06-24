package dev.sakuras.shortenerapi.models;

import dev.sakuras.shortenerapi.models.dto.ShortUrlDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "short_urls")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrl {
    @Id
    private String id;
    private String originalURL;
    private String shortURL;
    private String code;

    public ShortUrlDTO toDTO() {
        return ShortUrlDTO.builder()
                .originalURL(originalURL)
                .shortURL(shortURL)
                .build();
    }
}
