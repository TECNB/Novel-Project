package com.tec.vuepractice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tec.vuepractice.core.common.resp.RestResp;
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
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl extends ServiceImpl<HomeBookMapper, HomeBook> implements HomeService {
    @Resource
    private final HomeBookMapper homeBookMapper;
    @Resource
    private final BookInfoMapper bookInfoMapper;

    @Override
    public List<HomeBookRespDto> listHomeBooks() {
        // 从首页小说推荐表中查询出需要推荐的小说
        QueryWrapper<HomeBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        List<HomeBook> homeBooks = homeBookMapper.selectList(queryWrapper);

        // 获取推荐小说ID列表
        if (!CollectionUtils.isEmpty(homeBooks)) {
            List<Long> bookIds = homeBooks.stream()
                    .map(HomeBook::getBookId)
                    .toList();

            // 根据小说ID列表查询相关的小说信息列表
            QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
            bookInfoQueryWrapper.in(DatabaseConsts.CommonColumnEnum.ID.getName(), bookIds);
            List<BookInfo> bookInfos = bookInfoMapper.selectList(bookInfoQueryWrapper);

            // 组装 HomeBookRespDto 列表数据并返回
            if (!CollectionUtils.isEmpty(bookInfos)) {
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream()
                        .collect(Collectors.toMap(BookInfo::getId, Function.identity()));
                return homeBooks.stream().map(v -> {
                    BookInfo bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto bookRespDto = new HomeBookRespDto();
                    bookRespDto.setType(v.getType());
                    bookRespDto.setBookId(v.getBookId());
                    bookRespDto.setBookName(bookInfo.getBookName());
                    bookRespDto.setPicUrl(bookInfo.getPicUrl());
                    bookRespDto.setAuthorName(bookInfo.getAuthorName());
                    bookRespDto.setBookDesc(bookInfo.getBookDesc());
                    bookRespDto.setCategoryName(bookInfo.getCategoryName());
                    return bookRespDto;
                }).toList();

            }

        }
        return Collections.emptyList();
    }

    @Override
    public List<BookInfoRespDto> listHomeCategory() {
        QueryWrapper<BookInfo>CategoryQueryWrapperJunshi = new QueryWrapper<>();
        CategoryQueryWrapperJunshi.eq("category_name","历史军事").last("limit 2");
        List<BookInfo> bookInfoJunshi = bookInfoMapper.selectList(CategoryQueryWrapperJunshi);
        return bookInfoJunshi.stream()
                .map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .bookDesc(v.getBookDesc())
                        .bookName(v.getBookName())
                        .authorName(v.getAuthorName())
                        .build()).toList();
    }

    @Override
    public List<BookInfoRespDto> listHomeCategory2() {
        QueryWrapper<BookInfo>CategoryQueryWrapperJunshi = new QueryWrapper<>();
        CategoryQueryWrapperJunshi.eq("category_name","科幻灵异").last("limit 2");
        List<BookInfo> bookInfoJunshi = bookInfoMapper.selectList(CategoryQueryWrapperJunshi);
        return bookInfoJunshi.stream()
                .map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .bookDesc(v.getBookDesc())
                        .bookName(v.getBookName())
                        .authorName(v.getAuthorName())
                        .build()).toList();
    }

    @Override
    public List<BookInfoRespDto> listHomeCategory3() {
        QueryWrapper<BookInfo>CategoryQueryWrapperJunshi = new QueryWrapper<>();
        CategoryQueryWrapperJunshi.eq("category_name","都市言情").last("limit 2");
        List<BookInfo> bookInfoJunshi = bookInfoMapper.selectList(CategoryQueryWrapperJunshi);
        return bookInfoJunshi.stream()
                .map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .bookDesc(v.getBookDesc())
                        .bookName(v.getBookName())
                        .authorName(v.getAuthorName())
                        .build()).toList();
    }

    @Override
    public List<BookInfoRespDto> listHomeCategory4() {
        QueryWrapper<BookInfo>CategoryQueryWrapperJunshi = new QueryWrapper<>();
        CategoryQueryWrapperJunshi.eq("category_name","玄幻奇幻").last("limit 2");
        List<BookInfo> bookInfoJunshi = bookInfoMapper.selectList(CategoryQueryWrapperJunshi);
        return bookInfoJunshi.stream()
                .map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .bookDesc(v.getBookDesc())
                        .bookName(v.getBookName())
                        .authorName(v.getAuthorName())
                        .build()).toList();
    }

    @Override
    public List<BookInfoRespDto> listHomeCategoryId(Long categoryId) {
        QueryWrapper<BookInfo> CategoryIdqueryWrapper= new QueryWrapper<>();
        CategoryIdqueryWrapper
                .eq("category_id",categoryId)
                .last("limit 6");
        List<BookInfo> bookInfoCategoryId = bookInfoMapper.selectList(CategoryIdqueryWrapper);
        return bookInfoCategoryId.stream()
                .map(v -> BookInfoRespDto.builder()
                        .id(v.getId())
                        .categoryId(v.getCategoryId())
                        .categoryName(v.getCategoryName())
                        .bookDesc(v.getBookDesc())
                        .bookName(v.getBookName())
                        .authorName(v.getAuthorName())
                        .build()).toList();
    }
}
