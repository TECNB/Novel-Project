package com.tec.vuepractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tec.vuepractice.core.common.resp.RestResp;
import com.tec.vuepractice.dao.entity.HomeBook;
import com.tec.vuepractice.dto.resp.BookChapterRespDto;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import com.tec.vuepractice.dto.resp.HomeBookRespDto;
import com.tec.vuepractice.dto.resp.HomeCategoryDto;

import java.util.List;

public interface HomeService extends IService<HomeBook> {
    List<HomeBookRespDto> listHomeBooks();

    List<BookInfoRespDto> listHomeCategory();

    List<BookInfoRespDto> listHomeCategory2();
    List<BookInfoRespDto> listHomeCategory3();
    List<BookInfoRespDto> listHomeCategory4();

    List<BookInfoRespDto> listHomeCategoryId(Long categoryId);
}
