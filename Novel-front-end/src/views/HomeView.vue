<template>
  <div class="home">
    <Header />
    <div class="home-center">
      <div class="hotcontent">
        <div class="hot-list">
          <div class="itemList" v-for="(item, index) in hotRecommend" :key="index">
            <div class="items_img">
              <a href="javascript:void(0)" @click="bookDetail(item.bookId)"><!-- 小说地址 -->
                <img :src="`${imgBaseUrl}` + `${item.picUrl}`" onerror="this.src='default.gif';this.onerror=null"
                  :alt="item.bookName"><!-- 小说封面地址 -->
              </a>
            </div>
            <div class="items_txt">
              <dl>
                <dt>
                  <a class="home-bookName" href="javascript:void(0)" @click="bookDetail(item.bookId)">{{
                    item.bookName
                  }}</a><!-- 小说地址 -->
                  <a class="author" href="javascript:void(0)"><span>作者：{{ item.authorName }}</span></a><!-- 小说作者地址 -->
                </dt>
                <dd class="intro">
                  <a href="javascript:void(0)" @click="bookDetail(item.bookId)" v-html="item.bookDesc"></a>
                </dd>
              </dl>
            </div>
          </div>
        </div>



        <div class="tuijian-list">
          <p>经典推荐</p>
          <div class="tuijian-list-content">
            <div class="tuijian-list-list" v-for="(item, index) in goodRecommend" :key="index">
              <ul>
                <li>
                  <span class="tuijian-list-categoryName">[{{ item.categoryName }}]</span>
                  <span class="tuijian-list-bookName"><a href="javascript:void(0)" @click="bookDetail(item.bookId)">{{
                    item.bookName
                  }}</a></span><!-- 小说地址 -->
                  <span class="tuijian-list-authorName"><a href="javascript:void(0)" @click="bookDetail(item.bookId)">{{
                    item.authorName
                  }}</a></span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>



      <div class="novelslist">
        <div class="fenlei-content-center">
          <h3>历史军事</h3>
          <div class="fenlei-content" v-for="(item, index) in catagorycommend" :key="index">
            <div class="fenlei-top">
              <div class="fenlei-img">
                <img :src="`${imgBaseUrl}` + `${item.picUrl}`" onerror="this.src='default.gif';this.onerror=null"
                  :alt="item.bookName"><!-- 小说封面地址 -->
              </div>
              <div class="fenlei-txt">
                <dl>
                  <dt class="fenlei-name">
                    <a href="javascript:void(0)" @click="bookDetail(item.id)">{{ item.bookName }}<!-- 小说标题 --></a>
                  </dt>
                  <dt class="fenlei-author">
                    <a href="javascript:void(0)">作者：{{ item.authorName }}</a><!-- 小说作者地址 -->
                  </dt>
                  <dd class="fenlei-inro">
                    <a href="javascript:void(0)" v-html="item.bookDesc" @click="bookDetail(item.id)"></a><!-- 小说简介 -->
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>

        <div class="fenlei-content-center">
          <h3>玄幻奇幻</h3>
          <div class="fenlei-content" v-for="(item, index) in catagorycommend3" :key="index">
            <div class="fenlei-top">
              <div class="fenlei-img">
                <img :src="`${imgBaseUrl}` + `${item.picUrl}`" onerror="this.src='default.gif';this.onerror=null"
                  :alt="item.bookName"><!-- 小说封面地址 -->
              </div>
              <div class="fenlei-txt">
                <dl>
                  <dt class="fenlei-name">
                    <a href="javascript:void(0)" @click="bookDetail(item.id)">{{ item.bookName }}<!-- 小说标题 --></a>
                  </dt>
                  <dt class="fenlei-author">
                    <a href="javascript:void(0)">作者：{{ item.authorName }}</a><!-- 小说作者地址 -->
                  </dt>
                  <dd class="fenlei-inro">
                    <a href="javascript:void(0)" v-html="item.bookDesc" @click="bookDetail(item.id)"></a><!-- 小说简介 -->
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="novelslist">
        <div class="fenlei-content-center">
          <h3>科幻灵异</h3>
          <div class="fenlei-content" v-for="(item, index) in catagorycommend2" :key="index">
            <div class="fenlei-top">
              <div class="fenlei-img">
                <img :src="`${imgBaseUrl}` + `${item.picUrl}`" onerror="this.src='default.gif';this.onerror=null"
                  :alt="item.bookName"><!-- 小说封面地址 -->
              </div>
              <div class="fenlei-txt">
                <dl>
                  <dt class="fenlei-name">
                    <a href="javascript:void(0)" @click="bookDetail(item.id)">{{ item.bookName }}<!-- 小说标题 --></a>
                  </dt>
                  <dt class="fenlei-author">
                    <a href="javascript:void(0)">作者：{{ item.authorName }}</a><!-- 小说作者地址 -->
                  </dt>
                  <dd class="fenlei-inro">
                    <a href="javascript:void(0)" v-html="item.bookDesc" @click="bookDetail(item.id)"></a><!-- 小说简介 -->
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>

        <div class="fenlei-content-center">
          <h3>都市言情</h3>
          <div class="fenlei-content" v-for="(item, index) in catagorycommend4" :key="index">
            <div class="fenlei-top">
              <div class="fenlei-img">
                <img :src="`${imgBaseUrl}` + `${item.picUrl}`" onerror="this.src='default.gif';this.onerror=null"
                  :alt="item.bookName"><!-- 小说封面地址 -->
              </div>
              <div class="fenlei-txt">
                <dl>
                  <dt class="fenlei-name">
                    <a href="javascript:void(0)" @click="bookDetail(item.id)">{{ item.bookName }}<!-- 小说标题 --></a>
                  </dt>
                  <dt class="fenlei-author">
                    <a href="javascript:void(0)">作者：{{ item.authorName }}</a><!-- 小说作者地址 -->
                  </dt>
                  <dd class="fenlei-inro">
                    <a href="javascript:void(0)" v-html="item.bookDesc" @click="bookDetail(item.id)"></a><!-- 小说简介 -->
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import { reactive, toRefs, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { listHomeBooks, listHomeCategory,listHomeCategory2,listHomeCategory3,listHomeCategory4 } from "@/api/home";
import { getBookById, } from "@/api/book";
import { SetUp } from '@element-plus/icons-vue';
export default {
  name: "home",
  components: {
    Header,
    Footer,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      // 热门推荐
      hotRecommend: [],
      // 精品推荐
      goodRecommend: [],
      // 分类推荐
      catagorycommend: [],
      catagorycommend2: [],
      catagorycommend3: [],
      catagorycommend4: [],
      imgBaseUrl: process.env.VUE_APP_BASE_IMG_URL,
    });
    onMounted(async () => {
      const { data } = await listHomeBooks();
      await data.forEach((book) => {

        if (book.type == 3) {
          //热门推荐
          state.hotRecommend[state.hotRecommend.length] = book;
        }
        if (book.type == 4) {
          //精品推荐
          state.goodRecommend[state.goodRecommend.length] = book;
        }
      });
      dataCategory();
      dataCategory2();
      dataCategory3();
      dataCategory4();
    });
    const bookDetail = (bookId) => {
      router.push({ path: `/book/${bookId}` });
    };
    const dataCategory = async () => {
      const { data } = await listHomeCategory();
      await data.forEach((book) => {
        if (book.categoryName == "历史军事") {
          //历史推荐
          state.catagorycommend[state.catagorycommend.length] = book;
        }
      });
    }
    const dataCategory2 = async () => {
      const { data } = await listHomeCategory2();
      await data.forEach((book) => {
        if (book.categoryName == "科幻灵异") {
          //历史推荐
          state.catagorycommend2[state.catagorycommend2.length] = book;
        }
      });
    }
    const dataCategory3 = async () => {
      const { data } = await listHomeCategory3();
      await data.forEach((book) => {
        if (book.categoryName == "都市言情") {
          //历史推荐
          state.catagorycommend3[state.catagorycommend3.length] = book;
        }
      });
    }
    const dataCategory4 = async () => {
      const { data } = await listHomeCategory4();
      await data.forEach((book) => {
        if (book.categoryName == "玄幻奇幻") {
          //历史推荐
          state.catagorycommend4[state.catagorycommend4.length] = book;
        }
      });
    }


    return {
      ...toRefs(state),
      bookDetail,
      dataCategory,
    };
  }
}

</script>
