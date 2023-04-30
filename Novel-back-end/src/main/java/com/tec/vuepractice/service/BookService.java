package com.tec.vuepractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tec.vuepractice.core.common.resp.RestResp;
import com.tec.vuepractice.dao.entity.BookInfo;
import com.tec.vuepractice.dto.resp.BookChapterRespDto;
import com.tec.vuepractice.dto.resp.BookContentAboutRespDto;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import com.tec.vuepractice.dto.resp.HomeBookRespDto;

import java.util.List;

public interface BookService extends IService<BookInfo> {
    RestResp<BookInfoRespDto> getBookById(Long bookId);
    RestResp<List<BookChapterRespDto>>listChapters(Long bookId);

    RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId);

    RestResp<Long> getPreChapterId(Long chapterId);

    RestResp<Long> getNextChapterId(Long chapterId);
}
