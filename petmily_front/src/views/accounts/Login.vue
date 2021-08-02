<template>
  <br>
  <br>
  <div class="container">
  <button>개인 로그인</button>
  <button>기관 로그인</button>
    <div class="d-flex justify-content-center">
      <div>
        <div align="left" class="mb-3">
          <div>Welcome Back!</div>
          <div> <span>Don’t have a account,</span><button style="color: #8699DA" class="bw-color">Sign up</button></div>
        </div>
        <form ref="loginForm">
          <div>
            <input v-model="state.form.id" class="mb-5 radius-border form-control" type="text" placeholder="email">
          </div>
          <div>
            <input v-model="state.form.password" class="mb-5 radius-border form-control" type="password" placeholder="password">
          </div>
        </form>
        <div>
          <button class="btn-white" @click="clickLogin" style="color: #FFFFFF">로그인 하기</button>
        </div>
      </div>
      <img class="ms-3" style="border-radius: 12px" alt="petmily login" src="@/assets/login.png">
    </div>
  </div>
  
</template>
<script>
import { onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: "login",
  setup () {
    const loginForm = ref(null)
    const store = useStore()
    const router = useRouter()
    // const toggle = false

    const state = reactive({
      form: {
        id: '',
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

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          console.log('submit')
          axios({
            method: 'post',
            url: "http://localhost:8080/users/personal",
            headers: setToken()
          })
          .then((res) => {
            console.log(res)
            localStorage.setItem('jwt', res.data.token)
            store.state.email = state.form.id
            router.push({name: '/'})
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    onMounted(() => {
      // console.log(loginForm.value)
    })

    return {state, clickLogin}

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