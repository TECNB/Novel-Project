package com.tec.vuepractice.controller.front;

import com.tec.vuepractice.core.common.resp.RestResp;
import com.tec.vuepractice.core.constant.ApiRouterConsts;
import com.tec.vuepractice.dto.resp.BookChapterRespDto;
import com.tec.vuepractice.dto.resp.BookContentAboutRespDto;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import com.tec.vuepractice.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 34890
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService ;

    /**
     * 小说信息查询接口
     */
    @GetMapping("{id}")
    public RestResp<BookInfoRespDto> getBookById(@PathVariable("id") Long bookId){
        return bookService.getBookById(bookId);
    }

    /**
     * 小说章节列表查询接口
     */
    @GetMapping("chapter/list")
    public RestResp<List<BookChapterRespDto>> listChapters(Long bookId) {
        return bookService.listChapters(bookId);
    }
    /**
     * 小说内容相关信息查询接口
     */
    @GetMapping("content/{chapterId}")
    public RestResp<BookContentAboutRespDto> getBookContentAbout(@PathVariable("chapterId") Long chapterId) {
        log.info("查询方法调用之前，参数id={}", chapterId);
        return bookService.getBookContentAbout(chapterId);
    }

    /**
     * 获取上一章节ID接口
     */
    @GetMapping("pre_chapter_id/{chapterId}")
    public RestResp<Long> getPreChapterId(@PathVariable("chapterId") Long chapterId) {
        return bookService.getPreChapterId(chapterId);
    }

    /**
     * 获取下一章节ID接口
     */
    @GetMapping("next_chapter_id/{chapterId}")
    public RestResp<Long> getNextChapterId(@PathVariable("chapterId") Long chapterId) {
        return bookService.getNextChapterId(chapterId);
    }
}
