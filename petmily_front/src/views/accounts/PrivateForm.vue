<template>
  <div class="container mb-5">
    <div style="width: 600px;">
      <span>
        <input v-model="state.email" type="text" class="form-control br mb-5" placeholder="petmily@email.com">
        <button @click="confirmEmail">이메일 인증하기</button>
      </span>
      
      <input v-model="state.name" type="text" class="form-control br mb-5" placeholder="홍길동">
      <input v-model="state.password" type="password" class="form-control br mb-5" placeholder="••••••••">
      <input v-model="state.passwordConfirm" type="password" class="form-control br mb-5" placeholder="••••••••">
      <input v-model="state.phoneNum" type="text" class="form-control br mb-5" placeholder="010-1234-5678">
      <button class="btn-white" @click="confirmSignup">회원가입 하기</button>
    </div>
  </div>
</template>
<script>
import {reactive} from 'vue'
// import Element from 'element-ui'
import JumbotronTerms from '@/views/accounts/components/JumbotronTerms'

export default {
  name: 'PrivateForm',
  componenet: {
    JumbotronTerms,
  },
  setup () {
    const state = reactive({
      isEmail: false,
      email: "",
      name: "",
      password: null,
      passwordConfirm: null,
      phoneNum: ""
    })

    const confirmSignup = function () {
      console.log(state.password.length)
      // if (!state.isEmail) {
      //   alert("이메일 인증을 진행해주세요.")
      // }

      if (!state.password === state.passwordConfirm) {
        alert("비밀번호가 동일하지 않습니다.")
      }
    }

    return {state, confirmSignup}



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