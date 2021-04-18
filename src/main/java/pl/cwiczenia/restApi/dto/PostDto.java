package pl.cwiczenia.restApi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
