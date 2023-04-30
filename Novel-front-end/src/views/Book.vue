<template>
    <Header />
    <div class="book">
        <div class="bookContent">
            <div class="bookNav">
                <a href="@/home">Tec > {{ book.bookName }}</a>
            </div>
            <div class="bookInfo">
                <div class="bookInfo-img">
                    <img id="bookCover" class="cover" :src="`${imgBaseUrl}` + `${book.picUrl}`" :alt="book.bookName"
                        onerror="this.src='default.gif';this.onerror=null" />
                </div>
                <div class="bookInfo-eximg">
                    <div class="book-main">
                        <h1 class="bookInfo-name">{{ book.bookName }}</h1>
                        <p class="bookInfo-author">作者：{{ book.authorName }}</p>
                    </div>

                    <div class="bookInfo-info">

                        <span>类别：{{ book.categoryName }}</span>
                        <span>状态：{{
                            book.bookStatus == 0 ? "连载中" : "已完结"
                        }}</span>
                        <span>总点击：{{ book.visitCount }}</span>
                        <span>总字数：{{ book.wordCount }}</span>
                    </div>
                    <div class="bookInfo-des">
                        <p v-html="book.bookDesc"></p>
                    </div>
                    <button class="reading" @click="bookContent(book.id, book.firstChapterId)">
                        点击阅读
                    </button>
                </div>
            </div>
        </div>
        <div class="chapter">
            <div class="chapter-bookName">
                <p>全部章节</p>
            </div>
            <div class="chapter-all">
                <ul v-for="(item, index) in chapterList" :key="index">
                    <li><a @click="bookContent(book.id, item.id)" href="javascript:void(0)">{{ item.chapterName }}</a></li>
                </ul>
            </div>
        </div>
    </div>
    <Footer />
</template>
<script>
import { reactive, toRefs, onMounted } from "vue";
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { useRouter, useRoute } from "vue-router";
import {
    getBookById,
    listChapters,
} from "@/api/book";
export default {
    name: "book",
    components: {
        Header,
        Footer,
    },
    setup() {
        const route = useRoute();
        const router = useRouter();
        const state = reactive({
            book: {},
            books: [],
            chapterList: [],
        });
        onMounted(() => {
            const bookId = route.params.id;
            loadBook(bookId);
            loadChapterList(bookId);
        });
        const loadBook = async (bookId) => {
            const { data } = await getBookById(bookId);
            state.book = data;
            document
                .getElementById("bookCover")
                .setAttribute("onerror", "this.src='default.gif';this.onerror=null");
        };

        const loadChapterList = async (bookId) => {
            const { data } = await listChapters({ bookId: bookId });
            state.chapterList = data;
        };
        const bookContent = (bookId, chapterId) => {
            router.push({ path: `/book/${bookId}/${chapterId}` });
        };
        return {
            ...toRefs(state),
            bookContent,
        };
    },

}
</script>