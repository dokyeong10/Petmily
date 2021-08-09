<template>
  <div>
    <div id="nav" class="mb-1">
      <div>
        <!-- style="box-shadow: 0 4px 0 0 violet; content-box" 여부 다시 결정 해야함. -->
        <router-link to="/home" class="mx-5 text-decoration-none"
          ><img src="@\assets\PetmilyLogo.png" style="width: 50px; height: 50px;border-radius: 50%;"
        /></router-link>
        <router-link to="/home" class="mx-5 text-decoration-none">Home</router-link>
        <router-link to="/animallist" class="mx-5 text-decoration-none">유기동물</router-link>
        <router-link to="/" class="mx-5 text-decoration-none">커뮤니티</router-link>
        <router-link to="/" class="mx-5 text-decoration-none">라이브</router-link>

        <span v-if="confirmLogin">
          <button class="mx-5 text-decoration-none" @click="logout">로그아웃</button>
          <v-btn to="/myprofile" class="mx-5 text-decoration-none">마이페이지</v-btn>
        </span>
        <span v-else>
          <router-link to="/login" class="mx-5 text-decoration-none">로그인</router-link>
          <router-link to="/signupterms" class="mx-5 text-decoration-none">회원가입</router-link>
        </span>
      </div>
    </div>
    <router-view />
  </div>
</template>
<script>
import { onMounted, computed } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "Navbar",
  setup() {
    const router = useRouter();

    const confirmLogin = computed(() => {
      console.log(sessionStorage.getItem("isLogin"));
      return sessionStorage.getItem("isLogin");
    });

    const logout = function() {
      sessionStorage.removeItem("isLogin");
      localStorage.removeItem("jwt");
      sessionStorage.removeItem("isAgency");
      router.go();
    };

    onMounted(() => {
      router.push("/home");
    });

    return { confirmLogin, logout };
  },
};
</script>

<style>
.footer {
  color: aliceblue;
  margin: 100px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  box-shadow: 0 2px 3px 0 #edddf2;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
