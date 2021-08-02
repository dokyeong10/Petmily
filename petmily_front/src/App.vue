<template>
  <div id="nav" style="box-shadow: 0 4px 4px 0 $transparent-blue-violet;">
    <span>
      <router-link to="/home" class="mx-5 text-decoration-none">Home</router-link> 
      <router-link to="/home" class="mx-5 text-decoration-none">Home</router-link> 
      <router-link to="/" class="mx-5 text-decoration-none">유기동물</router-link>
      <router-link to="/" class="mx-5 text-decoration-none">커뮤니티</router-link>
      <router-link to="/" class="mx-5 text-decoration-none">라이브</router-link>
    </span>
    <span v-if="isLogin">
      <router-link to="/" class="mx-5 text-decoration-none">로그아웃</router-link>
      <router-link to="/" class="mx-5 text-decoration-none">마이페이지</router-link>
    </span>
    <span v-else>
      <router-link @click="goTologin" to="/login" class="mx-5 text-decoration-none">로그인</router-link>
      <router-link to="/" class="mx-5 text-decoration-none">회원가입</router-link>
    </span>
    <router-view/>
  </div>
  
</template>
<script>
import { ref, onMounted } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'App',
  setup () {
    const isLogin = ref(false)
    const router = useRouter()
    const goToLogin = function () {
      router.push('/login')
    }

    onMounted(() => { router.push('/home') }) 
    return { isLogin, goToLogin}
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
