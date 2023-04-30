package com.tec.vuepractice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tec.vuepractice.core.common.resp.RestResp;
import com.tec.vuepractice.core.constant.DatabaseConsts;
import com.tec.vuepractice.dao.entity.BookChapter;
import com.tec.vuepractice.dao.entity.BookContent;
import com.tec.vuepractice.dao.entity.BookInfo;
import com.tec.vuepractice.dao.mapper.BookChapterMapper;
import com.tec.vuepractice.dao.mapper.BookInfoMapper;
import com.tec.vuepractice.dao.mapper.BookContentMapper;
import com.tec.vuepractice.dto.resp.BookChapterRespDto;
import com.tec.vuepractice.dto.resp.BookContentAboutRespDto;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import com.tec.vuepractice.service.BookService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 34890
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookService {
    @Resource
    private final BookInfoMapper bookInfoMapper;
    @Resource
    private final BookChapterMapper bookChapterMapper;
    @Resource
    private final BookContentMapper bookContentMapper;

    @Override
    public RestResp<BookInfoRespDto> getBookById(Long bookId) {
        return RestResp.ok(getBookInfo(bookId));
    }

    @Override
    public RestResp<List<BookChapterRespDto>> listChapters(Long bookId) {
        QueryWrapper<BookChapter>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, bookId).orderByAsc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM);
        return RestResp.ok(bookChapterMapper.selectList(queryWrapper).stream()
                .map(v -> BookChapterRespDto.builder()
                        .id(v.getId())
                        .chapterName(v.getChapterName())
                        .isVip(v.getIsVip())
                        .build()).toList());
    }

    @Override
    public RestResp<BookContentAboutRespDto> getBookContentAbout(Long chapterId) {
        // 查询章节信息
        BookChapterRespDto bookChapter1 = getChapter(chapterId);

        // 查询章节内容
        String content = getBookContent(chapterId);

        // 查询小说信息
        BookInfoRespDto bookInfo = getBookInfo(bookChapter1.getBookId());

        // 组装数据并返回
        return RestResp.ok(BookContentAboutRespDto.builder()
                .bookInfo(bookInfo)
                .chapterInfo(bookChapter1)
                .bookContent(content)
                .build());
    }

    @Override
    public RestResp<Long> getPreChapterId(Long chapterId) {
        // 查询小说ID 和 章节号
        BookChapterRespDto chapter = getChapter(chapterId);
        Long bookId = chapter.getBookId();
        Integer chapterNum = chapter.getChapterNum();

        // 查询上一章信息并返回章节ID
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, bookId)
                .lt(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM, chapterNum)
                .orderByDesc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        return RestResp.ok(
                Optional.ofNullable(bookChapterMapper.selectOne(queryWrapper))
                        .map(BookChapter::getId)
                        .orElse(null)
        );
    }

    @Override
    public RestResp<Long> getNextChapterId(Long chapterId) {
        // 查询小说ID 和 章节号
        BookChapterRespDto chapter = getChapter(chapterId);
        Long bookId = chapter.getBookId();
        Integer chapterNum = chapter.getChapterNum();

        // 查询下一章信息并返回章节ID
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, bookId)
                .gt(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM, chapterNum)
                .orderByAsc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        return RestResp.ok(
                Optional.ofNullable(bookChapterMapper.selectOne(queryWrapper))
                        .map(BookChapter::getId)
                        .orElse(null)
        );
    }


    public BookInfoRespDto getBookInfo(Long id){
        BookInfo bookInfo = bookInfoMapper.selectById(id);
        // 查询首章ID
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, id)
                .orderByAsc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookChapter firstBookChapter = bookChapterMapper.selectOne(queryWrapper);

        return BookInfoRespDto.builder()
                .id(bookInfo.getId())
                .bookName(bookInfo.getBookName())
                .bookDesc(bookInfo.getBookDesc())
                .bookStatus(bookInfo.getBookStatus())
                .authorId(bookInfo.getAuthorId())
                .authorName(bookInfo.getAuthorName())
                .categoryId(bookInfo.getCategoryId())
                .categoryName(bookInfo.getCategoryName())
                .commentCount(bookInfo.getCommentCount())
                .firstChapterId(firstBookChapter.getId())
                .lastChapterId(bookInfo.getLastChapterId())
                .picUrl(bookInfo.getPicUrl())
                .visitCount(bookInfo.getVisitCount())
                .wordCount(bookInfo.getWordCount())
                .build();
    }

    public BookChapterRespDto getChapter(Long chapterId){
        QueryWrapper<BookChapter>chapterQueryWrapper=new QueryWrapper<>();
        chapterQueryWrapper.eq("id", chapterId);
        BookChapter bookChapter = bookChapterMapper.selectOne(chapterQueryWrapper);
        return BookChapterRespDto.builder()
                .id(chapterId)
                .bookId(bookChapter.getBookId())
                .chapterNum(bookChapter.getChapterNum())
                .chapterName(bookChapter.getChapterName())
                .chapterWordCount(bookChapter.getWordCount())
                .chapterUpdateTime(bookChapter.getUpdateTime())
                .build();
    }

    private String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> contentQueryWrapper = new QueryWrapper<>();
        contentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID, chapterId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(contentQueryWrapper);
        return bookContent.getContent();
    }
}
