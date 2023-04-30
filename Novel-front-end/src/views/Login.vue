<template>
    <Header />
    <div class="login">
        <div class="login-area">
            <div class="login-area-content">
                <h2>登录TEC</h2>
                <el-input v-model="input" placeholder="请输入邮箱" clearable prefix-icon="User" />
                <el-input v-model="input" type="password" placeholder="请输入密码" show-password prefix-icon="Lock" />
                <el-button type="primary" round>登录</el-button>
            </div>
        </div>
        <div class="lijizhuce">
            <div class="lijizhuce-content">
                <h3>还没有注册账号？</h3>
                <el-button type="primary" round ><router-link :to="{name: 'register'}">立即注册</router-link></el-button>
            </div>
        </div>
    </div>
    <Footer />
</template>

<script>
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { ref } from 'vue'
const input = ref('')
export default {
    components: {
        Header,
        Footer
    },
}
</script>