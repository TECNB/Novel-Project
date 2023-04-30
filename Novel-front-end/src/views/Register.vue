<template>
    <Header />
    <div class="register">
        <div class="register-area">
            <div class="register-area-content">
                <h2>登录TEC</h2>
                <el-input v-model="input" placeholder="请输入邮箱" clearable prefix-icon="User" />
                <el-input v-model="input" type="password" placeholder="请输入密码" show-password prefix-icon="Lock" />
                <el-input v-model="input" placeholder="请输入验证码" clearable prefix-icon="Message"/>
                <el-button type="primary" class="yanzhengma">点击发送验证码</el-button>
                <el-button type="primary" round class="zhuce">注册</el-button>
            </div>
        </div>
        <div class="lijidenglu">
            <div class="lijidenglu-content">
                <h3>已有账号？</h3>
                <el-button type="primary" round><router-link :to="{ name: 'login' }">立即登陆</router-link></el-button>
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