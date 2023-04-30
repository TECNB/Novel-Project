package com.tec.crawl.core.utils;

import com.tec.crawl.dto.resp.CategoryUrlDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws IOException {
        new HtmlParseUtil().categoryUrl("https://www.9biqu.com/class/1/1.html").forEach(System.out::println);
    }

    public List<CategoryUrlDto>categoryUrl(String keywords) throws IOException {
        String url = keywords;
        int count = 0;
        // 设置计数器初始值为 0
        int maxCount = 10;
        // 设置最大数量为 10
        Document document = Jsoup.parse(new URL(url), 30000);

        Elements categoryList = document.select("#newscontent > div.update-list > div > div > ul");

        Elements elements = document.getElementsByTag("li");

        ArrayList<CategoryUrlDto> categoryUrlDtoArrayList = new ArrayList<>();

        for (Element el : elements) {
            count++;
            String src = el.getElementsByTag("a").eq(0).attr("href");

            CategoryUrlDto categoryUrlDto = new CategoryUrlDto();
            categoryUrlDto.setSrc(src);

            categoryUrlDtoArrayList.add(categoryUrlDto);
            if (count >= maxCount) {
                break; // 如果达到最大数量，跳出循环
            }
        }
        for (Element el : categoryList) {
            String bookName = el
                    .select("li > span.s2 > a")
                    .text();
            System.out.println(bookName);
            String authorName = el
                    .select("dl > dt > a:nth-child(1)")
                    .text();
            System.out.println(authorName);
            String bookDesc = el
                    .select("dl > dd")
                    .text();
            System.out.println(bookDesc);
            CategoryUrlDto categoryUrlDto = new CategoryUrlDto();

            categoryUrlDtoArrayList.add(categoryUrlDto);
        }
        return categoryUrlDtoArrayList;
    }
}
