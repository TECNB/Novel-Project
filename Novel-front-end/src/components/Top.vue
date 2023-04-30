<template>
    <div class="top-color">
        <div class="top">
            <div class="top-center">
                <div class="logo">
                    <router-link :to="{ name: 'home' }"><img src="@/assets/logo.png" alt="TEC" class="logo" /></router-link>
                </div>
                <div class="search">
                    <div class="top-input">
                        <el-input v-model="input" placeholder="书名、作者、关键字" />
                    </div>
                    <div class="top-button">
                        <el-button type="primary">
                            <el-icon style="vertical-align: middle">
                                <Search />
                            </el-icon>
                            <span style="vertical-align: middle"> 搜索 </span>
                        </el-button>
                    </div>
                </div>

                <div class="shelf">

                </div>
                <div class="user">
                    <router-link :to="{ name: 'login' }" class="top-a">登录</router-link>
                    <router-link :to="{ name: 'register' }" class="top-a">注册</router-link>
                </div>
            </div>

        </div>
    </div>
</template>

<script scope setup>
import {
    Check,
    Delete,
    Edit,
    Message,
    Search,
    Star,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const input = ref('')
</script>