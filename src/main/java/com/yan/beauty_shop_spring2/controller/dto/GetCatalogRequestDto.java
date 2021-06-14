package com.yan.beauty_shop_spring2.controller.dto;

import lombok.Data;

@Data
public class GetCatalogRequestDto {
    private String sortMethod;
    private String filterMethod;
    private String filter;
}
