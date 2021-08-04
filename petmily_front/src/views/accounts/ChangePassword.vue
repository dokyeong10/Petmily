<template>
  <div>
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3">
        <div>Forgot Password!</div>
        <div><span>Don’t have an account,</span><button style="color: #8699DA" class="bw-color" @click="goToSignup">Sign up</button></div>
        <form>
          <div>
            <input v-model="password" class="mb-5 radius-border form-control" type="password" placeholder="New Password">
          </div>
          <div>
            <input v-model="confirmPassword" class="mt-5 mb-2 radius-border form-control" type="password" placeholder="Confirm Password">
          </div>
          <div>
            <button @click="changePassword" class="btn-white" style="color: #FFFFFF;"></button>
          </div>
        </form>
          <div v-if="isConfirm">
            인증되었습니다.
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
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
export default {
  name: 'changepassword',
  setup () {
    const password = ""
    const confirmPassword = ""

    const store = useStore()
    const router = useRouter()

    const changePassword = function () {
      const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/
      if (!reg.test(password)) {
        return alert("비밀번호는 8자 이상 16자 이하이어야 하며 숫자, 대소문자, 특수문자가 적어도 하나 이상 포함되어야 합니다.")
      }
      
      if (password != confirmPassword) {
        return alert("비밀번호가 일치하지 않습니다.")
      } else {
        axios({
          method: ,
          url:,
          data: {
            email: store.state.findEmail,
            password: password
           }
          })
          .then(res => {
            console.log(res)
            //
            router.push('/login')
          })
          .catch(err => {
            console.log(err)
          })
      }

    }

    return { password, confirmPassword, changePassword }
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