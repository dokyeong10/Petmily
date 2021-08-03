<template>
  <div id="nav">
    <div>
      <div>
        <!-- style="box-shadow: 0 4px 0 0 violet; content-box" 여부 다시 결정 해야함. -->
      <span>
        <router-link to="/home" class="mx-5 text-decoration-none">Home</router-link> 
        <router-link to="/home" class="mx-5 text-decoration-none">Home</router-link> 
        <router-link to="/" class="mx-5 text-decoration-none">유기동물</router-link>
        <router-link to="/" class="mx-5 text-decoration-none">커뮤니티</router-link>
        <router-link to="/" class="mx-5 text-decoration-none">라이브</router-link>
      </span>
      <span v-if="confirmLogin">
        <button class="mx-5 text-decoration-none" @click="logout">로그아웃</button>
        <router-link to="/" class="mx-5 text-decoration-none">마이페이지</router-link>
      </span>
      <span v-else>
        <router-link @click="goTologin" to="/login" class="mx-5 text-decoration-none">로그인</router-link>
        <router-link @click="goToSignup" to="/signupterms" class="mx-5 text-decoration-none">회원가입</router-link>
      </span>
      <router-view/>
      </div>
    </div>
  </div>
  
</template>
<script>
import { onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'App',
  setup () {
    const store = useStore()
    const router = useRouter()
    const goToLogin = function () {
      router.push('/login')
    }

    const goToSignup = function () {
      router.push('/signupterms')
    }

    onMounted(() => { router.push('/home') }) 

    const confirmLogin = computed(() => { 
      return store.state.isLogin
      }) 
    return { goToLogin, goToSignup, confirmLogin }
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
