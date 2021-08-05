<template>
  <br>
  <br>
  <div class="container">
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3">
        <div>Forgot Password!</div>
        <div><span>Don’t have an account,</span><button style="color: #8699DA" class="bw-color" @click="goToSignup">Sign up</button></div>
        <form>
          {{ state.isEmail }}
          {{ state.isConfirm }}
          {{ state.findEmail }}
          <span>
            <input v-model="state.findEmail" class="mb-5 radius-border form-control" type="text" placeholder="email">
            <button class="btn-white" type="button" style="color: #FFFFFF" @click="confirmEmail">이메일 인증하기</button>
          </span>
          <span v-if="state.isEmail">
          {{state.authNum}}
            <input v-model="state.authNum" class="mt-5 mb-2 radius-border form-control" type="text" placeholder="인증번호">
            <button class="btn-white" type="button" style="color: #FFFFFF" @click="confirmNumber">인증하기</button>
          </span>
          <div v-if="state.isConfirm">
            인증되었습니다.
          </div>
          <!-- <div v-if="state.isConfirm">
            <button class="btn-white" style="color: #FFFFFF;" @click="goToChangePassword">비밀번호 바꾸러 가기</button>
          </div> -->
        </form>
          <div class="m-5 d-flex justify-content-evenly">
            <div>소셜 로그인 하기</div>         
          </div>
        <div class="d-flex justify-content-evenly">
          <!-- 소셜로그인 로그인 만들기 -->
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="">구글</a></button>
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="">카카오</a></button>
          <button class="btn-white"><a style="color: #FFFFFF; text-decoration-line: none;" href="">네이버</a></button>
        </div>    
      </div>
      <img class="ms-3" style="border-radius: 12px; width: 400px;" alt="petmily login" src="@/assets/login.png">
    </div>
  </div>
</template>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
export default {
  name: 'findpassword',
  setup () {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      findEmail: '',
      authNum: '',
      isEmail: false,
      isConfirm: false
    })

    const confirmEmail = function () {
      console.log(state.findEmail)
      axios({
        method: 'post',
        url: 'http://localhost:8080/email/send/findpwd',
        data: {
          email: state.findEmail
        }
      })
      .then(res => {
        console.log(res)
        alert('입력하신 메일로 인증번호가 발송되었습니다.')
        store.state.findEmail = state.findEmail
        localStorage.setItem('findEmail', store.state.findEmail)
        state.isEmail = true
        console.log(state.isEmail)
      })
      .catch(err => {
        console.log(err)
      })
    }

    const confirmNumber = function () {
      console.log(store.state.findEmail )
      console.log(state.authNum)
      axios({
        method: 'post',
        url: 'http://localhost:8080/email/check/authcode',
        data: {
          email: store.state.findEmail,
          authCode: state.authNum
          }
      })
      .then(res => {
        console.log(res)
        if (res.data) {
          alert('인증에 성공하셨습니다.')
          state.isConfirm = true
          router.push('/changepassword')
        } else {
          alert('잘못된 인증 번호입니다.')
          return
        }
      })
      .catch(err => {
        console.log(err)
      })
    }

    const goToSignup = function () {
      router.push('/signupterms')
    }
    return {state, goToSignup, confirmEmail, confirmNumber }
  }
}
</script>
<style>
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