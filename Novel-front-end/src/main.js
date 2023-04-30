import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from '@/router'
import '@/assets/css/global.css';
import '@/assets/css/home.css';
import '@/assets/css/login.css';
import '@/assets/css/register.css';
import '@/assets/css/book.css';
import '@/assets/css/BookContent.css';
import '@/assets/css/bookcategory.css';

import * as ElementPlusIconsVue from '@element-plus/icons-vue'



const app = createApp(App)

app.use(ElementPlus)

app.use(router)

app.mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}