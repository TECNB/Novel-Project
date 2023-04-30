<template>
    <div class="content-top-color">
        <Top />
    </div>
    <div class="BookContent">

        <div class="BookContent-center">
            <div class="txt-title">
                <h1 v-if="data.chapterInfo">{{ data.chapterInfo.chapterName }}</h1>
            </div>
            <div class="txt-info">
                <p v-if="data.chapterInfo">类别：{{ data.bookInfo.categoryName }}</p>
                <p v-if="data.chapterInfo">作者：{{ data.bookInfo.authorName }}</p>
                <p v-if="data.chapterInfo">访问量：{{ data.bookInfo.visitCount }}</p>
                <p v-if="data.chapterInfo">字数：{{ data.bookInfo.wordCount }}</p>
            </div>
            <div class="txt-content" v-html="data.bookContent"></div>
        </div>
        <div class="txt-bt">
            <a href="javascript:void(0)" @click="preChapter(data.chapterInfo.bookId)"><button class="bt-pre" > 上一章</button></a>
            <a href="javascript:void(0)" @click="bookDetail(data.chapterInfo.bookId)"><button class="bt-catalog" > 目录</button></a>
            <a href="javascript:void(0)" @click="nextChapter(data.chapterInfo.bookId)"><button class="bt-next" > 下一章</button></a>
        </div>
    </div>
    <Footer />
</template>

<script>
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import Top from '@/components/Top';
import { reactive, toRefs, onMounted } from "vue";
import { getBookContent, getPreChapterId, getNextChapterId } from "@/api/book";
import { useRouter, useRoute } from "vue-router";
import { ElMessage } from "element-plus";
export default {
    name: "BookContent",
    components: {
        Top,
        Footer,
    },
    setup() {
        const route = useRoute();
        const router = useRouter();
        const state = reactive({
            data: {},
            imgBaseUrl: process.env.VUE_APP_BASE_IMG_URL,
        });
        onMounted(() => {
            init(route.params.chapterId);
            console.log("route.params.chapterId:", route.params.chapterId);
        });
        const bookDetail = (bookId) => {
            router.push({ path: `/book/${bookId}` });
        };
        const chapterList = (bookId) => {
            router.push({ path: `/chapter_list/${bookId}` });
        };
        const preChapter = async (bookId) => {
            const { data } = await getPreChapterId(route.params.chapterId);
            if (data) {
                router.push({ path: `/book/${bookId}/${data}` });
                init(data)
                window.scrollTo(0, 0);
            } else {
                ElMessage.warning("已经是第一章了！");
            }
        };
        const nextChapter = async (bookId) => {
            const { data } = await getNextChapterId(route.params.chapterId);
            if (data) {
                router.push({ path: `/book/${bookId}/${data}` });
                init(data)
                window.scrollTo(0, 0);
            } else {
                ElMessage.warning("已经是最后一章了！");
            }
        };

        const init = async (chapterId) => {
            const { data } = await getBookContent(chapterId);
            state.data = data;
        };

        return {
            ...toRefs(state),
            bookDetail,
            chapterList,
            preChapter,
            nextChapter,
        };
    },
    mounted() { },
}
</script>