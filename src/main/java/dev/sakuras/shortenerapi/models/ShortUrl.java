package dev.sakuras.shortenerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "short_urls")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrl {
    private String originalURL;
    private String shortURL;
    private String code;
}
