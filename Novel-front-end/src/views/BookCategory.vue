<template>
    <Header />
    <div class="bookcategory">
        <div class="categorylist">
                <div class="category-content" v-for="(item, index) in catagorycommendId" :key="index">
                    <div class="category-top">
                        <div class="category-img">
                            <img :src="`${imgBaseUrl}` + `${item.picUrl}`"
                                onerror="this.src='default.gif';this.onerror=null" :alt="item.bookName"><!-- 小说封面地址 -->
                        </div>
                        <div class="category-txt">
                            <dl>
                                <dt class="category-name">
                                    <a href="javascript:void(0)" @click="bookDetail(item.id)">{{ item.bookName
                                    }}<!-- 小说标题 --></a>
                                </dt>
                                <dt class="category-author">
                                    <a href="javascript:void(0)">作者：{{ item.authorName }}</a><!-- 小说作者地址 -->
                                </dt>
                                <dd class="category-inro">
                                    <a href="javascript:void(0)" v-html="item.bookDesc"
                                        @click="bookDetail(item.id)"></a><!-- 小说简介 -->
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    <Footer />
</template>

<script>
import { reactive, toRefs, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { listHomeCategoryId } from "@/api/home";
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
            // 分类推荐
            catagorycommendId: [],
            imgBaseUrl: process.env.VUE_APP_BASE_IMG_URL,
        });
        onMounted(async () => {
            const categoryId = route.params.categoryId-2;
            dataCategoryId(categoryId);
        });
        const bookDetail = (bookId) => {
            router.push({ path: `/book/${bookId}` });
        };
        const dataCategoryId = async (categoryId) => {
            
            const { data } = await listHomeCategoryId(categoryId);
            await data.forEach((book) => {
                {
                    //历史推荐
                    state.catagorycommendId[state.catagorycommendId.length] = book;
                }
            });
        }


        return {
            ...toRefs(state),
            bookDetail,
            dataCategoryId,
        };
    }
}

</script>
