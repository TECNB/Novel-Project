package com.tec.vuepractice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tec.vuepractice.dao.entity.BookInfo;
import com.tec.vuepractice.dto.resp.BookInfoRespDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookInfoMapper extends BaseMapper<BookInfo> {

}
