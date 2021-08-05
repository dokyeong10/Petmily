<template>
  <br>
  <br>
  <div class="container">
    <div class="d-flex justify-content-evenly">
      <label><input type="radio" name="login" v-model="toggle" value="false" checked="checked">개인 로그인</label>
      <label><input type="radio" name="login" v-model="toggle" value="true">기관 로그인</label>
    </div>
    <br>
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3">
        <div>Welcome Back!</div>
        <div> <span>Don’t have an account,</span><button style="color: #8699DA" class="bw-color" @click="goToSignup">Sign up</button></div>
        <form>
          <div>
            <input v-model="state.form.email" class="mb-5 radius-border form-control" type="text" placeholder="email">
          </div>
          <div>
            <input v-model="state.form.password" class="mt-5 mb-2 radius-border form-control" type="password" placeholder="password">
          </div>
          <div class="d-flex justify-content-start checkbox-container">
            <label><input type="checkbox" name="remind_user" value="동의함">  Remember Me</label>
            <button class="btn btn-link" @click="goToFindPassword" style="color: #8699DA; text-decoration-line: none;">forgot password</button>
          </div>
        </form>
          <div class="mt-5 mb-5">
            <button class="btn-white" @click="clickLogin" style="color: #FFFFFF">로그인 하기</button>
          </div>
          <div class="m-5 d-flex justify-content-evenly">
            <div>소셜 로그인 하기</div>         
          </div>
        <div class="d-flex justify-content-evenly">
          <!-- 소셜로그인 로그인 만들기 -->
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="http://localhost:8080/oauth2/authorization/google">구글</a></button>
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="http://localhost:8080/oauth2/authorization/kakao">카카오</a></button>
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="http://localhost:8080/oauth2/authorization/naver">네이버</a></button>
        </div>    
      </div>
      <img class="ms-3" style="border-radius: 12px; width: 400px;" alt="petmily login" src="@/assets/login.png">
    </div>
  </div>
</template>
<script>
import { reactive } from 'vue'
import axios from 'axios'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: "login",
  setup () {
    const store = useStore()
    const router = useRouter()
    let toggle = true

    const state = reactive({
      form: {
        email: '',
        password: '',
        align: 'left'
      },
      rules: {
        id: [
          { required: true, message: 'Please input Email', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ]
      },
    })

    const setToken = function () {
      const token = localStorage.getItem('jwt')
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    }

    const goToSignup = function () {
      router.push("/signupterms")
    }

    const goToFindPassword = function () {
      router.push('/findpassword')
    }

    const clickLogin = function () {
      if (!toggle) {
        axios({
          method: 'post',
          url: 'http://localhost:8080/auth/personal/login',
          data: {
            email: state.form.email,
            password: state.form.password,
            header: setToken()
          }
        })
        .then(res => {
          console.log(res.data)
          localStorage.setItem('jwt', res.data.accessToken)
          store.state.isLogin = true
          sessionStorage.setItem('isLogin', store.state.isLogin)
          router.go()
        })
        .catch(err => {
          console.log(err)
          alert("개인회원이 맞는지 확인해주세요!")
        })
      } else {
        axios({
          method: 'post',
          url: 'http://localhost:8080/auth/agency/login',
          data: {
            email: state.form.email,
            password: state.form.password,
            header: setToken()
            }
          })
        .then(res => {
          console.log(res.data)
          localStorage.setItem('jwt', res.data.accessToken)
          store.state.isLogin = true
          store.state.isAgency = true
          sessionStorage.setItem('isLogin', store.state.isLogin)
          sessionStorage.setItem('isAgency', store.state.isAgency)
          router.go()
          })
        .catch(err => {
          console.log(err)
          alert("기관회원이 맞는지 확인해주세요!")
          })
        } 
      }

    return {state, clickLogin, goToSignup, goToFindPassword}

  }
  
}
</script>
<style>
img {
  width: 20%;
  height: 21%;
  object-fit: cover;
}

.btn-white {
  width: 200px;
  background-color: #A4B5F0;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.radius-border {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
  width: 400px;
  color: #789ADE;
}

.bw-color {
  background-color: #FFFFFF;
  border-right: #FFFFFF 1px solid;
  border-left: #FFFFFF 1px solid;
  border-top: #FFFFFF 1px solid;
  border-bottom: #FFFFFF 1px solid;
  font-weight: bold;
}

</style>