package com.tec.crawl.dto.resp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUrlDto {
    private String src;
    private String picUrl;
}
