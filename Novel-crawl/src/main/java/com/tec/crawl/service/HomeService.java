package com.tec.crawl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tec.crawl.dao.entity.BookInfo;
import com.tec.crawl.dao.entity.HomeBook;
import com.tec.crawl.dto.resp.HomeBookRespDto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface HomeService extends IService<HomeBook> {

    List<BookInfo> listHomeBooks() throws IOException;
}
