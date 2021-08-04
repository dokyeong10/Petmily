<template>
  <JumbotronTerms/>
  <div class="container mb-5">
    <div style="width: 600px;">
      <span>
        <input v-model="state.email" type="text" class="form-control br" placeholder="petmily@email.com">
        <button @click="confirmEmail" class="mb-5">이메일 인증하기</button>
      </span>
      <input v-model="state.username" type="text" class="form-control br mb-5" placeholder="홍길동">
      <input v-model="state.password" type="password" class="form-control br mb-5" placeholder="••••••••">
      <input v-model="state.passwordConfirm" type="password" class="form-control br mb-5" placeholder="••••••••">
      <input v-model="state.phone" type="text" class="form-control br mb-5" placeholder="010-1234-5678">
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
  name: 'PrivateForm',
  componenet: {
    JumbotronTerms,
  },
  setup () {
    const state = reactive({
      isEmail: false,
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

    // const confirmEmail = function () {
    //   axios({
    //     method: 'post',
    //     url: ''
    //   })
    // }

    const confirmSignup = function () {
      const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/
      const reg_required = /.{1,}/
      const reg_phone = /^\d{3}-\d{3,4}-\d{4}$/
      const reg_name = /^[가-힣]+$/

      // if (!state.isEmail) {
      //   return alert("이메일 인증을 진행해주세요.")
      // }

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
        url: "http://localhost:8080/users/personal",
        data: {
          email: state.email,
          username: state.username,
          password: state.password,
          phone: state.phone,
        }
      })
      .then( res => {
        console.log(res.data)
        store.state.isSignup = true
        router.push('/signupsuccess')
      })
      .catch(err => {
        console.log(err)
      })

    }

    return {state, confirmSignup, setToken }



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


</style>