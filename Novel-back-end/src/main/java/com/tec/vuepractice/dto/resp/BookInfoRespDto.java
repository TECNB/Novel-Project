package com.tec.vuepractice.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookInfoRespDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 类别名
     */
    private String categoryName;

    /**
     * 小说封面地址
     */
    private String picUrl;

    /**
     * 小说名
     */
    private String bookName;

    /**
     * 作家id
     */
    private Long authorId;

    /**
     * 作家名
     */
    private String authorName;

    /**
     * 书籍描述
     */
    private String bookDesc;

    /**
     * 书籍状态;0-连载中 1-已完结
     */
    private Integer bookStatus;

    /**
     * 点击量
     */
    private Long visitCount;

    /**
     * 总字数
     */
    private Integer wordCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 首章节ID
     */
    private Long firstChapterId;

    /**
     * 最新章节ID
     */
    private Long lastChapterId;

    /**
     * 最新章节名
     */
    private String lastChapterName;

    /**
     * 最新章节更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
}
