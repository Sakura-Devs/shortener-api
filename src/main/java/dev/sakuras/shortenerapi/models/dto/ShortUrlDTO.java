package dev.sakuras.shortenerapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrlDTO {
    private String originalURL;
    private String shortURL;
}
