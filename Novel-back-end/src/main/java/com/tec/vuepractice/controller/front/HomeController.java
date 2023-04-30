package com.tec.vuepractice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tec.vuepractice.core.common.resp.RestResp;
import com.tec.vuepractice.core.constant.ApiRouterConsts;
import com.tec.vuepractice.core.constant.DatabaseConsts;
import com.tec.vuepractice.dao.entity.BookInfo;
import com.tec.vuepractice.dao.entity.HomeBook;
import com.tec.vuepractice.dao.mapper.BookInfoMapper;
import com.tec.vuepractice.dao.mapper.HomeBookMapper;
import com.tec.vuepractice.dto.resp.BookChapterRespDto;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import com.tec.vuepractice.dto.resp.HomeBookRespDto;
import com.tec.vuepractice.dto.resp.HomeCategoryDto;
import com.tec.vuepractice.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@RequiredArgsConstructor
public class HomeController {

    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;

    private final HomeService homeService;

    @GetMapping("books")
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeService.listHomeBooks());
    }

    @GetMapping("category")
    public  RestResp<List<BookInfoRespDto>> listHomeCategory(){
        return RestResp.ok(homeService.listHomeCategory());
    }
    @GetMapping("category2")
    public  RestResp<List<BookInfoRespDto>> listHomeCategory2(){
        return RestResp.ok(homeService.listHomeCategory2());
    }
    @GetMapping("category3")
    public  RestResp<List<BookInfoRespDto>> listHomeCategory3(){
        return RestResp.ok(homeService.listHomeCategory3());
    }
    @GetMapping("category4")
    public  RestResp<List<BookInfoRespDto>> listHomeCategory4(){
        return RestResp.ok(homeService.listHomeCategory4());
    }
    @GetMapping("{categoryId}")
    public  RestResp<List<BookInfoRespDto>> listHomeCategoryId(@PathVariable("categoryId") Long categoryId){
        return RestResp.ok(homeService.listHomeCategoryId(categoryId));
    }
}
