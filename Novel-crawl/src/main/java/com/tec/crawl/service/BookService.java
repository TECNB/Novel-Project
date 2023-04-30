package com.tec.crawl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tec.crawl.dao.entity.BookInfo;

import java.io.IOException;
import java.util.List;

public interface BookService extends IService<BookInfo> {
    List<BookInfo> listBookDetails(int id2) throws IOException, InterruptedException;

    List<BookInfo> listBookContent(int id) throws IOException, InterruptedException;
}

