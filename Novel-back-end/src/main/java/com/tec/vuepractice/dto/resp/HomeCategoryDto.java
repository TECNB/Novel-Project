package com.tec.vuepractice.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 首页分类相关 响应DIO
 * @author 34890
 * @date 2023/3/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeCategoryDto {

    private List<BookInfoRespDto> bookInfoRespDtoList;


}
