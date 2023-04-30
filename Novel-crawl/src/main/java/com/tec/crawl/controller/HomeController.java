package com.tec.crawl.controller;

import com.tec.crawl.core.common.resp.RestResp;
import com.tec.crawl.core.constant.ApiRouterConsts;
import com.tec.crawl.dao.entity.BookInfo;
import com.tec.crawl.dao.mapper.BookInfoMapper;
import com.tec.crawl.dao.mapper.HomeBookMapper;
import com.tec.crawl.dto.resp.BookInfoRespDto;
import com.tec.crawl.dto.resp.HomeBookRespDto;
import com.tec.crawl.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
public class HomeController {

    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;

    private final HomeService homeService;

    @GetMapping("/")
    public RestResp<List<BookInfo>> listHomeBooks() throws IOException {
        return RestResp.ok(homeService.listHomeBooks());
    }

}
