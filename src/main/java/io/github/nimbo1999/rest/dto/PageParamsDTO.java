package io.github.nimbo1999.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageParamsDTO {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "id";
}
