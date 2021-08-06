<template>
  <br />
  <br />
  <div class="container">
    <div class="d-flex justify-content-evenly">
      <label
        ><input
          type="radio"
          name="login"
          v-model="state.toggle"
          value="false"
          checked="checked"
        />개인 로그인</label
      >
      <label
        ><input
          type="radio"
          name="login"
          v-model="state.toggle"
          value="true"
        />기관 로그인</label
      >
    </div>
    <br />
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3">
        <div class="font-weight-bold" style="font-size: 30px">Welcome Back!</div>
        <div>
          <span>Don’t have an account,</span
          ><button style="color: #8699da" class="bw-color" @click="goToSignup">
            Sign up
          </button>
        </div>
        <br>
        <form>
          <div>
            <label>이메일</label>
            <input
              v-model="state.form.email"
              class="mb-4 form-control radius-border rounded-pill p-3"
              type="text"
              placeholder="email"
            />
          </div>
          <div>
            <label>비밀번호</label>
            <input
              v-model="state.form.password"
              class="mb-2 form-control radius-border rounded-pill p-3"
              type="password"
              placeholder="password"
            />
          </div>
          <div class="checkbox-container d-flex align-items-center justify-content-between">
            <label>
              <input 
                class="checkbox-round"
                type="checkbox" 
                name="remind_user" 
                value="동의함" />
              Remember Me
            </label>
            <button
              class="btn btn-link"
              @click="goToFindPassword"
              style="color: #8699da; text-decoration-line: none"
            >
              forgot password
            </button>
          </div>
        </form>
        <div class="mt-5 mb-5">
          <button class="btn-white" @click="clickLogin" style="color: #ffffff">
            로그인 하기
          </button>
        </div>
        <div class="m-5 d-flex justify-content-evenly">
          <div>소셜 로그인 하기</div>
        </div>
        <div class="d-flex justify-content-evenly">
          <!-- 소셜로그인 로그인 만들기 -->
          <button class="btn-logo">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/google"
              ><img src="src\assets\google.png"></a
            >
          </button>
          <button class="btn-white">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/kakao"
              >카카오</a
            >
          </button>
          <button class="btn-white">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/naver"
              >네이버</a
            >
          </button>
        </div>
      </div>
      <img
        class="ms-3"
        style="border-radius: 12px; width: 400px"
        alt="petmily login"
        src="@/assets/login.png"
      />
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import axios from "axios";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "login",
  setup() {
    const store = useStore();
    const router = useRouter();

    const state = reactive({
      form: {
        email: "",
        password: "",
        align: "left",
      },
      rules: {
        id: [
          { required: true, message: "Please input Email", trigger: "blur" },
        ],
        password: [
          { required: true, message: "Please input password", trigger: "blur" },
        ],
      },
      toggle: false,
    });

    const setToken = function () {
      const token = localStorage.getItem("jwt");
      const config = {
        Authorization: `JWT ${token}`,
      };
      return config;
    };

    const goToSignup = function () {
      router.push("/signupterms");
    };

    const goToFindPassword = function () {
      router.push("/findpassword");
    };

    const clickLogin = function () {
      if (!state.toggle) {
        axios({
          method: "post",
          url: "http://localhost:8080/auth/personal/login",
          data: {
            email: state.form.email,
            password: state.form.password,
            header: setToken(),
          },
        })
          .then((res) => {
            console.log(res.data);
            localStorage.setItem("jwt", res.data.accessToken);
            store.state.isLogin = true;
            sessionStorage.setItem("isLogin", store.state.isLogin);
            location.href = "/home";
          })
          .catch((err) => {
            console.log(err);
            alert("개인회원이 맞는지 확인해주세요!");
          });
      } else {
        axios({
          method: "post",
          url: "http://localhost:8080/auth/agency/login",
          data: {
            email: state.form.email,
            password: state.form.password,
            header: setToken(),
          },
        })
          .then((res) => {
            console.log(res.data);
            localStorage.setItem("jwt", res.data.accessToken);
            store.state.isLogin = true;
            store.state.isAgency = true;
            sessionStorage.setItem("isLogin", store.state.isLogin);
            sessionStorage.setItem("isAgency", store.state.isAgency);
            location.href = "/home";
          })
          .catch((err) => {
            console.log(err);
            alert("기관회원이 맞는지 확인해주세요!");
          });
      }
    };

    return { state, clickLogin, goToSignup, goToFindPassword };
  },
};
</script>
<style>


img {
  width: 20%;
  height: 21%;
  object-fit: cover;
}

.btn-white {
  width: 200px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.btn-logo {
  width: 3rem;
  height: 3rem;
  background-color: rgba(255, 255, 255, 0);
  border-radius: 50%;
}

.radius-border {
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  color: #789ade;
}

.bw-color {
  background-color: #ffffff;
  border-right: #ffffff 1px solid;
  border-left: #ffffff 1px solid;
  border-top: #ffffff 1px solid;
  border-bottom: #ffffff 1px solid;
  font-weight: bold;
}

.checkbox-round {
    width: 1rem;
    height: 1rem;
    background-color: white;
    border-radius: 50%;
    vertical-align: middle;
    border: 1px solid #ddd;
    -webkit-appearance: none;
    outline: none;
    cursor: pointer;
}

.checkbox-round:checked {
    background-color: #789ade;
}

.checkbox-container {
width: 25rem;
}

</style>