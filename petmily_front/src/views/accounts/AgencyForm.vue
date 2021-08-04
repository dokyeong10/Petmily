<template>
  <JumbotronTerms/>
  <div class="container mb-5">
    <div style="width: 600px;">
      <input v-model="state.agencyname" type="text" class="form-control radius-border br mb-5" placeholder="기관 이름">
      <span>
        <input v-model="state.email" type="text" class="form-control radius-border br" placeholder="petmily@email.com">
        <button @click="confirmEmail" class="mb-5">이메일 인증하기</button>
      </span>
      <span v-if="state.isEmail">
        <input v-model="state.number" type="text" class="form-control radius-border br" placeholder="인증번호를 입력해주세요.">
        <button @click="confirmNumber" class="mb-5">인증하기</button>
      </span>
      <div v-if="state.isConfirm" class="mb-5">
        인증이 완료되었습니다.
      </div>
      <input v-model="state.username" type="text" class="form-control radius-border br mb-5" placeholder="담당자 이름">
      <input v-model="state.password" type="password" class="form-control radius-border br mb-5" placeholder="••••••••">
      <input v-model="state.passwordConfirm" type="password" class="form-control radius-border br mb-5" placeholder="••••••••">
      <input v-model="state.phone" type="text" class="form-control radius-border br mb-5" placeholder="010-1234-5678">
      <button class="btn-white" style="color: #FFFFFF;" @click="confirmSignup">회원가입 하기</button>
    </div>
  </div>
</template>
<script>
import {reactive} from 'vue'
import axios from 'axios'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import JumbotronTerms from '@/views/accounts/components/JumbotronTerms'
export default {
  components: { JumbotronTerms },
  name: 'AgencyForm',
  componenet: {
    JumbotronTerms,
  },
  setup () {
    const state = reactive({
      isEmail: false,
      isConfirm: false,
      number: null,
      agencyname: "",
      email: "",
      username: "",
      password: null,
      passwordConfirm: null,
      phone: ""
    })
    const router = useRouter()
    const store = useStore()
    const setToken = function () {
        const token = localStorage.getItem('jwt')
        const config = {
          Authorization: `JWT ${token}`
        }
      return config
    }
    
    const confirmEmail = function () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/email/send/register',
        data: {
          email: state.email
          }
        })
        .then(res => {
          console.log(res)
          alert('작성하신 이메일로 인증번호를 보내드렸습니다. 아래 칸에 인증번호를 입력해주세요.')
          state.isEmail = true
        })
        .catch(err => {
          if (err.response.status >= 500 ) {
            alert("올바른 이메일을 입력해주세요!")
          } else {
            alert("이미 가입된 이메일입니다!")
          }
        })
    }

    const confirmNumber = function () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/email/check/authcode',
        data: {
          email: state.email,
          authCode: state.number
          }
      })
      .then(res => {
        console.log(res)
        console.log(res.data)
        if(res.data) {
          alert('인증에 성공하셨습니다.')
          state.isEmail = false
          state.isConfirm = true
        } else {
          alert('잘못된 인증 번호입니다.')
        }
      })
      .catch(err => {
        console.log(err)
      })
    }


    const confirmSignup = function () {
      const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/
      const reg_required = /.{1,}/
      const reg_phone = /^\d{3}-\d{3,4}-\d{4}$/
      const reg_name = /^[가-힣]+$/

      if (!reg_name.test(state.agencyname)) {
        return alert("기관 이름은 필수 항목입니다.")
      }

      if (!state.isConfirm) {
        return alert("이메일 인증을 진행해주세요.")
      }

      if (!reg_name.test(state.username)) {
        return alert("올바른 이름을 입력하세요.")
      }

      if(!reg.test(state.password)) {
        return alert("비밀번호는 8자 이상 16자 이하이어야 하며 숫자, 대소문자, 특수문자가 적어도 하나 이상 포함되어야 합니다.") 
      }

      if (state.password != state.passwordConfirm) {
        return alert("비밀번호가 일치하지 않습니다.")
      }

      if (!reg_required.test(state.phone)) {
        return alert("전화번호는 필수 항목입니다.")
      }

      if (!reg_phone.test(state.phone)) {
        return alert("올바른 번호를 입력해주세요. 예: 010-0000-0000")
      }

      axios({
        method: 'post',
        url: "http://localhost:8080/users/agency",
        data: {
          email: state.email,
          username: state.username,
          password: state.password,
          phone: state.phone,
        }
      })
      .then( res => {
        console.log(res.data)
        store.state.isLogin = true
        router.push('/signupsuccess')
      })
      .catch(err => {
        console.log(err)
      })

    }

    return {state, confirmSignup, setToken, confirmEmail, confirmNumber}



  }
  
}
</script>
<style>
.br {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
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

</style>