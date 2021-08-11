import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
// 설치한 FontAwesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
// FontAwesome 아이콘 불러오기
import { faHeart as fasHeart } from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart } from '@fortawesome/free-regular-svg-icons'

library.add(
  fasHeart,
  farHeart
)

createApp(App).use(router).use(store).component('font-awesome-icon', FontAwesomeIcon).mount('#app')

