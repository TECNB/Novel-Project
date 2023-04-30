package com.tec.crawl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tec.crawl.dao.entity.BookInfo;
import com.tec.crawl.dao.entity.HomeBook;
import com.tec.crawl.dao.mapper.BookInfoMapper;
import com.tec.crawl.dao.mapper.HomeBookMapper;
import com.tec.crawl.dto.resp.CategoryUrlDto;
import com.tec.crawl.dto.resp.HomeBookRespDto;
import com.tec.crawl.service.HomeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl extends ServiceImpl<HomeBookMapper, HomeBook> implements HomeService {

    @Resource
    HomeBookMapper homeBookMapper;
    @Resource
    BookInfoMapper bookInfoMapper;

    @Override
    public List<BookInfo> listHomeBooks() throws IOException {
        String url = "https://www.9biqu.com/";

        Document document = Jsoup.parse(new URL(url), 30000);

        Elements homeList = document.select("#hotcontent > div.hot-list.clearfix > div");

        ArrayList<BookInfo> bookInfoArrayList = new ArrayList<>();

        for (Element el : homeList) {
            String picUrl = el
                    .getElementsByTag("img")
                    .attr("src");
            String bookName = el
                    .select("dl > dt > a:nth-child(2)")
                    .text();

            String authorName = el
                    .select("dl > dt > a:nth-child(1)")
                    .text();

            String bookDesc = el
                    .select("dl > dd")
                    .text();
            System.out.println(bookDesc);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setPicUrl(picUrl);
            bookInfo.setBookDesc(bookDesc);
            bookInfo.setBookName(bookName);
            bookInfo.setAuthorName(authorName);
            bookInfo.setIsVip(1);
            bookInfo.setAuthorId(0L);
            bookInfo.setScore(6);


            bookInfoMapper.insert(bookInfo);
            bookInfoArrayList.add(bookInfo);
        }
        return bookInfoArrayList;
    }
}
