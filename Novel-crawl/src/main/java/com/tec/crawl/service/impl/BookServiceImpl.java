package com.tec.crawl.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tec.crawl.dao.entity.BookChapter;
import com.tec.crawl.dao.entity.BookContent;
import com.tec.crawl.dao.entity.BookInfo;
import com.tec.crawl.dao.mapper.BookChapterMapper;
import com.tec.crawl.dao.mapper.BookContentMapper;
import com.tec.crawl.dao.mapper.BookInfoMapper;
import com.tec.crawl.service.BookService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookService {


    String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44";
    //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8080));
    List<String> BUrl1;


    List<Proxy> proxies = Arrays.asList(
            new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("60.169.245.139", 4223)),
            new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("115.207.203.11", 4231)),
            new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("60.169.245.205", 4223))
    );

    int currentProxyIndex = 0;

    String CategoryName=null;

    @Resource
    private BookInfoMapper bookInfoMapper;
    @Resource
    private BookChapterMapper bookChapterMapper;
    @Resource
    private BookContentMapper bookContentMapper;
    /**小说具体信息的获取方法，并会将其存入数据库
     *包括小说名字，小说作者，小说简介，小说章节名以及小说章节名和小说章节链接
     *
     * @param id2*/
    @Override
    public List<BookInfo> listBookDetails(int id2) throws IOException, InterruptedException {

        int count = 0;
        BUrl1 = getBookUrl1();

        for (int i=0;i< BUrl1.size();i++){
            String urlEverBook=BUrl1.get(i);
            try {

                count++;
                Proxy currentProxy = proxies.get(currentProxyIndex);
                Document document = Jsoup.connect(urlEverBook)
                        .userAgent(ua)
                        // 将代理服务器对象传递给Jsoup
                        .proxy(currentProxy)
                        .timeout(1000000000)
                        .get();

                String bookName = document.select("#info > h1").text();
                String authorName = document.select("#info > p:nth-child(2) > a").text();



                String picUrl = "https://www.9biqu.com" + document.select("#fmimg > img").attr("src");
                String lastChapterName = document.select("#info > p:nth-child(5) > a").text();


                switch (id2){
                    case 1:
                        CategoryName ="玄幻奇幻";
                        break;
                    case 2:
                        CategoryName = "武侠仙侠";
                        break;
                    case 3:
                        CategoryName = "都市言情";
                        break;
                    case 4:
                        CategoryName = "历史军事";
                        break;
                    case 5:
                        CategoryName = "网游竞技";
                        break;
                    case 6:
                        CategoryName = "科幻灵异";
                        break;
                    case 7:
                        CategoryName = "女生频道";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + id2);
                }

                Long CategoryId=Long.valueOf(id2);
                if(authorName.equals("小说免费阅读")){
                    authorName="Tec";
                }
                String bookIntro = document.select("#intro").text();

                System.out.println("第"+count+"本书名:"+bookName);
                System.out.println("第"+count+"本作者名:"+authorName);
                System.out.println("第"+count+"本介绍:"+bookIntro);
                System.out.println("第"+count+"本图片链接:"+picUrl);
                System.out.println("第"+count+"本最新章名:"+lastChapterName);



                QueryWrapper<BookInfo>BookInfoNameQueryWrapper =new QueryWrapper<>();
                BookInfoNameQueryWrapper.eq("book_name", bookName);
                BookInfo bookInfo = bookInfoMapper.selectOne(BookInfoNameQueryWrapper);

                if(bookInfo==null){
                    BookInfo bookInfo1 = new BookInfo();
                    bookInfo1.setWorkDirection(0);
                    bookInfo1.setCategoryId(CategoryId);
                    bookInfo1.setCategoryName(CategoryName);
                    bookInfo1.setPicUrl(picUrl);
                    bookInfo1.setBookName(bookName);
                    bookInfo1.setAuthorId(0L);
                    bookInfo1.setAuthorName(authorName);
                    bookInfo1.setBookDesc(bookIntro);
                    bookInfo1.setScore(6);
                    bookInfo1.setBookStatus(0);
                    bookInfo1.setVisitCount(100L);
                    //bookInfo1.setWordCount();
                    bookInfo1.setCommentCount(0);
                    //bookInfo1.setLastChapterId();
                    bookInfo1.setLastChapterName(lastChapterName);
                    bookInfo1.setLastChapterUpdateTime(LocalDateTime.now());
                    bookInfo1.setCreateTime(LocalDateTime.now());
                    bookInfo1.setUpdateTime(LocalDateTime.now());
                    bookInfo1.setIsVip(0);

                    bookInfoMapper.insert(bookInfo1);
                    System.out.println("第"+count+"本书名为"+bookName+"的小说信息已经成功存入数据表bookInfo");
                }else{
                    System.out.println("在数据表bookInfo中书名为"+bookName+"的小说在数据库中已经存在");
                }



                Thread.sleep(4000);
            } catch (SocketException e) {
                System.out.println("在爬取小说信息时，IP地址被禁止，等待10秒钟...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();
            }catch (IOException e){
                System.out.println("在爬取小说信息时，IP地址失效，将更换IP...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();
            }

        }
        System.out.println(CategoryName+"小说信息已经全部采集完成");
        return null;
    }

    /**以下方法将小说的内容存进数据库
     *
     *
     * @param id*/
    @Override
    public List<BookInfo> listBookContent(int id) throws IOException, InterruptedException {
        int count = 0;
        int countChapter = 0;

        BUrl1 = getBookUrl1();
        for (int i=0;i< BUrl1.size();i++){
            String urlEverBook=BUrl1.get(i);
            try {
                count++;
                Proxy currentProxy = proxies.get(currentProxyIndex);
                Document document = Jsoup.connect(urlEverBook)
                        .userAgent(ua)
                        // 将代理服务器对象传递给Jsoup
                        .proxy(currentProxy)
                        .timeout(1000000000)
                        .get();

                String bookName = document.select("#info > h1").text();

                //以下代码是取得每本书的每个章节名以及链接还有章节内容
                String bookChapterName;
                String bookChapterUrlPart;
                String bookChapterUrl;

                String bookContent;

                Long bookId= 0L;
                switch (id){
                    case 1:
                        CategoryName ="玄幻奇幻";
                        break;
                    case 2:
                        CategoryName = "武侠仙侠";
                        break;
                    case 3:
                        CategoryName = "都市言情";
                        break;
                    case 4:
                        CategoryName = "历史军事";
                        break;
                    case 5:
                        CategoryName = "网游竞技";
                        break;
                    case 6:
                        CategoryName = "科幻灵异";
                        break;
                    case 7:
                        CategoryName = "女生频道";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + id);
                }



                Elements bookChapter = document.select("#list > dl > dd:gt(13)");
                for(Element el:bookChapter){
                    countChapter++;
                    bookChapterName = el.select("a").text();
                    bookChapterUrlPart = el.select("a").attr("href");
                    bookChapterUrl = "https://www.9biqu.com"+ bookChapterUrlPart;
                    bookContent = getBookContent(bookChapterUrl);
                    int chineseCharCount = countChineseCharacters(bookContent);

                    System.out.println("第"+count+"本的第"+countChapter+"章名"+bookChapterName);
                    System.out.println("第"+count+"本的第"+countChapter+"章链接:"+bookChapterUrl);
                    //System.out.println("第"+count+"本的第"+countChapter+"章内容:"+bookContent);

                    //查找是否有该小说的存在
                    QueryWrapper<BookInfo>BookInfoNameQueryWrapper =new QueryWrapper<>();
                    BookInfoNameQueryWrapper.eq("book_name", bookName);
                    BookInfo bookInfoText = bookInfoMapper.selectOne(BookInfoNameQueryWrapper);



                    BookInfo bookInfoId = bookInfoMapper.selectOne(BookInfoNameQueryWrapper);
                    bookId = bookInfoId.getId();

                    //查找是否有重复章节内容的存在
                    QueryWrapper<BookChapter>BookChapterTextQueryWrapper = new QueryWrapper<>();
                    BookChapterTextQueryWrapper.eq("chapter_name",bookChapterName)
                                               .eq("book_id",bookId);
                    BookChapter bookChapterText = bookChapterMapper.selectOne(BookChapterTextQueryWrapper);


                    
                    // 如果不存在相同的章节名字，则插入书籍信息
                    if(bookInfoText != null){
                        if (bookChapterText == null ) {
                            //将数据存入bookChapter
                            BookChapter bookChapter1 = new BookChapter();
                            bookChapter1.setBookId(bookId);
                            bookChapter1.setChapterNum(countChapter);
                            bookChapter1.setChapterName(bookChapterName);
                            bookChapter1.setWordCount(chineseCharCount);
                            bookChapter1.setIsVip(0);
                            bookChapter1.setCreateTime(LocalDateTime.now());
                            bookChapter1.setUpdateTime(LocalDateTime.now());

                            bookChapterMapper.insert(bookChapter1);

                            System.out.println("第"+count+"本的第"+countChapter+"章已成功存入数据表bookChapter");

                        } else {
                            // 如果已经存在相同的书籍信息，则直接使用已存在的书籍信息的id
                            System.out.println("在数据表bookChapter中章节名为"+bookChapterName+"的小说"+bookName+"在数据库中已经存在");
                            break;
                        }
                    }else {
                        System.out.println("在数据表bookChapter中书名为"+bookName+"的小说在数据库中不存在");
                        break;                    }


                    Long chapterId = 0L;

                    // 查询是否存在该章节和该书名，用于获取该章节的ID
                    QueryWrapper<BookChapter> bookChapterQueryWrapper = new QueryWrapper<>();
                    bookChapterQueryWrapper.eq("book_id", bookId);
                    bookChapterQueryWrapper.eq("chapter_name", bookChapterName);
                    BookChapter bookChapterText2 = bookChapterMapper.selectOne(bookChapterQueryWrapper);

                    // 如果章节存在，则获取其自生成的ID，用于重复性比较，和存入数据库
                    if ( bookChapterText2 != null) {
                        chapterId = bookChapterText2.getId();
                    }
                    //获取之前存入的自生成的chapter_id，用于重复性比较
                    QueryWrapper<BookContent>BookContentQueryWrapper =new QueryWrapper<>();
                    BookContentQueryWrapper.eq("chapter_id",chapterId);
                    BookContent bookContentText = bookContentMapper.selectOne(BookContentQueryWrapper);


                    // 如果不存在相同的章节内容，则插入书籍信息
                    if (bookContentText==null && bookInfoText !=null){
                        BookContent bookContent1 = new BookContent();
                        bookContent1.setChapterId(chapterId);
                        bookContent1.setContent(bookContent);
                        bookContent1.setCreateTime(LocalDateTime.now());
                        bookContent1.setUpdateTime(LocalDateTime.now());

                        bookContentMapper.insert(bookContent1);
                        System.out.println("第"+count+"本的第"+countChapter+"章的内容已成功存入数据表bookContent");
                    }else {
                        // 如果已经存在相同的书籍信息，则直接使用已存在的书籍信息的id
                        System.out.println("在数据表bookContent中书名为"+bookName+"的小说在数据库中已经存在");
                    }


                }
                countChapter = 0;
                Thread.sleep(1000);
            } catch (SocketException e) {
                System.out.println("在爬取小说章节时，IP地址被禁止，等待10秒钟...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }catch (IOException e){
                System.out.println("在爬取小说章节时，IP地址失效，将更换IP...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }

        }
        System.out.println(CategoryName+"小说章节内容已经全部采集完成");
        return null;
    }

    /**该方法获取到小说章节的具体内容
     *
     * */
    public String getBookContent(String BookContentUrl) throws IOException, InterruptedException {

        String bookContent = null;
        while (bookContent == null){
            try {
                Proxy currentProxy = proxies.get(currentProxyIndex);
                Document document = Jsoup.connect(BookContentUrl)
                        .userAgent(ua)
                        .proxy(currentProxy)
                        .timeout(1000000000)
                        .get();
                Thread.sleep(1000);
                Elements bookContentList = document.select("#content > *:not(p:first-child)");

                bookContent = bookContentList.toString();

            }catch (SocketException e){
                System.out.println("在爬取小说章节内容时，IP地址被禁止，等待10秒钟...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }catch (IOException e){
                System.out.println("在爬取小说章节内容时，IP地址失效，将更换IP...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }
        }
        return bookContent;
    }
    /**统记中文字数
     *
     * */
    public int countChineseCharacters(String bookContent) {
        if (bookContent == null || bookContent.trim().isEmpty()) {
            return 0;
        }

        int count = 0;
        String[] words = bookContent.trim().split("\\s+");
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (isChineseCharacter(c)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**判断是否是中文
     *
     * */
    public boolean isChineseCharacter(char c) {
        Character.UnicodeScript script = Character.UnicodeScript.of(c);
        return script == Character.UnicodeScript.HAN;
    }


    /**从小说的分类列表获取BookUrl的方法
     *
     * */
    public List<String> getBookUrl1() throws IOException, InterruptedException {



        ArrayList<String> bookUrlList1 = new ArrayList<>();
        //count用于计数完成了多少BookUrl的获取
        int count=0;
        //一共有5页
        int max = 5;
        for (int i=1;i<=max;i++){
            String urlEver = "https://www.9biqu.com/class/1/"+i+".html";


            try {
                Proxy currentProxy = proxies.get(currentProxyIndex);
                Document document = Jsoup.connect(urlEver)
                        .userAgent(ua)
                        .proxy(currentProxy)
                        .timeout(1000000000)
                        .get();
                Thread.sleep(3000);
                Elements NameUrlList = document.select("#newscontent > div.update-list > div > div > ul>li");
                for (Element el : NameUrlList){
                    String BookUrlPart = el.select("span.s2 > a").attr("href");
                    String BookUrl ="https://www.9biqu.com"+ BookUrlPart;
                    bookUrlList1.add(BookUrl);
                    count++;
                    System.out.println(CategoryName+"小说地址采集完成"+count+"个");
                }
            } catch (SocketException e) {
                System.out.println("IP地址被禁止，等待10秒钟...");
                Thread.sleep(10000);
                i--;
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }catch (IOException e){
                System.out.println("在爬取小说地址时，IP地址失效，将更换IP...");
                Thread.sleep(10000);
                currentProxyIndex = (currentProxyIndex + 1) % proxies.size();

            }
        }
        System.out.println(CategoryName+"小说已经全部采集完成");


        return bookUrlList1;
    }
}

