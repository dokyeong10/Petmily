<template>
  <JumbotronTerms/>
  <div class="container mb-5">
    <div style="width: 600px;">
      <label for="agencyname" class="d-flex flex-row">기관 이름</label>
      <input v-model="state.agencyname" type="text" class="form-control radius-border br mb-5" placeholder="기관 이름" id="agencyname">
      <label for="agencycode" class="d-flex flex-row">기관 코드</label>
      <div class="d-flex" style="width: 400px;">
        <input v-model="state.agencycode" type="text" class="form-control radius-border br mb-5" placeholder="기관 코드" id="agencycode">
        <!-- 기관 코드 검색 모달 -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#agencycodeSearchModal" style="height: 38px; width: 80px">
          검색
        </button>
        <div class="modal fade" id="agencycodeSearchModal" tabindex="-1" aria-labelledby="agencycodeSearchModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="agencycodeSearchModalLabel">기관 코드 검색</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="agencycodeSearchDataDelete"></button>
              </div>
              <div class="modal-body d-flex">
                <input v-model="state.agencycodeSearchKeyword" type="text" class="form-control radius-border br" placeholder="검색어를 입력하세요." @keyup.enter="agencycodeSearch">
                <button type="button" class="btn btn-primary" @click="agencycodeSearch">검색</button>
              </div>
              <ul v-if="state.agencycodeSearchData">
                  <li v-for="value in state.agencycodeSearchData" :key="value.agencycode">기관 이름: {{ value.agencyname }} | 기관 코드: {{ value.agencycode }}</li>
              </ul>
              <div>키워드로 검색하여 기관 코드를 복사 후 붙여넣기해 주세요.</div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="agencycodeSearchDataDelete">닫기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <label for="email" class="d-flex flex-row">이메일</label>
      <span class="d-flex" style="width: 400px;">
        <input v-model="state.email" type="text" class="form-control radius-border br" placeholder="petmily@email.com" @keyup.enter="confirmEmail" id="email" style="height: 38px;">
        <button @click="confirmEmail" type="button" class="mb-5 btn btn-primary" style="height: 38px; width: 80px">인증</button>
      </span>
      <span v-if="state.isEmail" style="width: 400px;">
        <label for="emailcode" class="d-flex flex-row">인증번호</label>
        <div class="d-flex" style="width: 400px;">
          <input v-model="state.number" type="text" class="form-control radius-border br" placeholder="인증번호를 입력해주세요." @keyup.enter="confirmNumber" id="emailcode" style="height: 38px;">
          <button @click="confirmNumber" type="button" class="mb-5 btn btn-primary" style="height: 38px; width: 160px">인증하기</button>
        </div>
      </span>
      <div v-if="state.isConfirm" class="mb-5">
        인증이 완료되었습니다.
      </div>
      <label for="username" class="d-flex flex-row">담당자 이름</label>
      <input v-model="state.username" type="text" class="form-control radius-border br mb-5" placeholder="담당자 이름" id="username">
      <label for="password" class="d-flex flex-row">비밀번호</label>
      <input v-model="state.password" type="password" class="form-control radius-border br mb-5" placeholder="••••••••" id="password">
      <label for="confirmPassword" class="d-flex flex-row">비밀번호 재입력</label>
      <input v-model="state.passwordConfirm" type="password" class="form-control radius-border br mb-5" placeholder="••••••••" id="confirmPassword">
      <label for="phone" class="d-flex flex-row">전화번호</label>
      <input v-model="state.phone" type="text" class="form-control radius-border br mb-5" placeholder="010-1234-5678" id="phone">
      <button class="btn-white" style="color: #FFFFFF;" @click="confirmSignup">회원가입 하기</button>
    </div>
  </div>
</template>
<script>
import {reactive} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import JumbotronTerms from '@/views/accounts/components/JumbotronTerms'
export default {
  components: { JumbotronTerms },
  name: 'agencyform',
  componenet: {
    JumbotronTerms,
  },
  setup () {
    const state = reactive({
      isEmail: false,
      isConfirm: false,
      number: null,
      agencyname: "",
      agencycode: "",
      email: "",
      username: "",
      password: null,
      passwordConfirm: null,
      phone: "",
      agencycodeSearchKeyword: "",
      agencycodeSearchData: "",
      isClickedSearch: false,
    })
    const router = useRouter()
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
          agencyname: state.agencyname,
          agencycode: state.agencycode,
        }
      })
      .then(res => {
        console.log(res.data)
        router.push('/signupsuccess')
      })
      .catch(err => {
        console.log(err)
      })
    }
    const agencycodeSearch = function () {
      axios({
        method: 'get',
        url: `http://localhost:8080/shelter/${state.agencycodeSearchKeyword}`
      })
      .then( res => {
        console.log(res.data)
        state.agencycodeSearchData = res.data
        console.log(state.agencycodeSearchData)
      })
      .catch( err => {
        console.log(err)
      })
    }
    const agencycodeSearchDataDelete = function () {
      state.agencycodeSearchKeyword = ""
      state.agencycodeSearchData = ""
    }
    return {state, confirmSignup, setToken, confirmEmail, confirmNumber, agencycodeSearch, agencycodeSearchDataDelete }
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