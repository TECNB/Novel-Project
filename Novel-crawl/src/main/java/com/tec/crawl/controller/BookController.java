package com.tec.crawl.controller;

import com.tec.crawl.core.common.resp.RestResp;
import com.tec.crawl.core.constant.ApiRouterConsts;
import com.tec.crawl.dao.entity.BookInfo;
import com.tec.crawl.dao.mapper.BookInfoMapper;
import com.tec.crawl.dao.mapper.HomeBookMapper;
import com.tec.crawl.service.BookService;
import com.tec.crawl.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final HomeService homeService;

    private final BookService bookService;

    @GetMapping("/bookDetails/{id}")
    public RestResp<List<BookInfo>> listBooks(@PathVariable("id") int id2) throws IOException, InterruptedException {
        return RestResp.ok(bookService.listBookDetails(id2));
    }
    @GetMapping("/bookContent/{id}")
    public RestResp<List<BookInfo>> listBooksContent(@PathVariable("id") int id) throws IOException, InterruptedException {
        return RestResp.ok(bookService.listBookContent(id));
    }

}
