<template>
  <br />
  <br />
  <div class="container">
    <br />
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3 mx-5">
        <div class="font-bold" style="font-size: 30px">Welcome Back!</div>
        <div>
          <span>Don’t have an account,</span
          ><button style="color: #8699da" class="bw-color" @click="goToSignup">
            Sign up
          </button>
        </div>
        <br />
        <form>
          <div>
            <input
              v-model="state.form.email"
              class="mb-4 form-control radius-border rounded-pill"
              type="text"
              placeholder="email"
            />
          </div>
          <div>
            <input
              v-model="state.form.password"
              class="mb-2 form-control radius-border rounded-pill"
              type="password"
              placeholder="password"
            />
          </div>
          <div class="checkbox-container d-flex align-items-center justify-content-between">
            <div>
              <label class="mx-3">
                <input
                  type="radio"
                  name="login"
                  v-model="state.toggle"
                  :value="false"
                  checked="checked"
                />개인
              </label>
              <label>
                <input type="radio" name="login" v-model="state.toggle" :value="true" />기관
              </label>
            </div>
            <button
              class="btn btn-link"
              @click="goToFindPassword"
              style="color: #8699da; text-decoration-line: none"
            >
              forgot password
            </button>
          </div>
        </form>
        <div v-if="!state.toggle" class="mt-5 mb-5">
          <button class="btn-login font-" @click="privateLogin">
            로그인 하기
          </button>
        </div>
        <div v-else class="mt-5 mb-5">
          <button class="btn-login font-" @click="agencyLogin">
            로그인 하기
          </button>
        </div>
        <div class="position-relative mb-5">
          <hr />
          <div
            class="position-absolute top-50 start-50 translate-middle px-2 bg-white"
            style="font-size: 5px"
          >
            on continue with
          </div>
        </div>
        <div class="d-flex justify-content-evenly">
          <!-- 소셜로그인 로그인 만들기 -->
          <button class="btn-logo">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/google"
              ><img
                class="img"
                src="@\assets\google.png"
                style="width: 30px; height: 30px; border-radius: 50%"
            /></a>
          </button>
          <button class="btn-logo">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/kakao"
              ><img
                class="img"
                src="@\assets\KakaoTalk.png"
                style="width: 30px; height: 30px; border-radius: 50%"
            /></a>
          </button>
          <button class="btn-logo">
            <a
              style="color: #ffffff; text-decoration-line: none"
              href="http://localhost:8080/oauth2/authorization/naver"
              ><img
                class="img"
                src="@\assets\naver.png"
                style="width: 30px; height: 30px; border-radius: 50%"
            /></a>
          </button>
        </div>
      </div>
      <img
        class="img ms-3"
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
        id: [{ required: true, message: "Please input Email", trigger: "blur" }],
        password: [{ required: true, message: "Please input password", trigger: "blur" }],
      },
      toggle: false,
    });

    const setToken = function() {
      const token = localStorage.getItem("jwt");
      const config = {
        Authorization: `JWT ${token}`,
      };
      return config;
    };

    const goToSignup = function() {
      router.push("/signupterms");
    };

    const goToFindPassword = function() {
      router.push("/findpassword");
    };

    const privateLogin = function() {
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
          localStorage.setItem("userno", res.data.userno);
          store.state.isLogin = true;
          store.state.isUser = true;
          localStorage.setItem("isLogin", store.state.isLogin);
          localStorage.setItem("isUser", store.state.isUser);
          localStorage.setItem("isAgency", store.state.isAgency);
          sessionStorage.setItem("isLogin", store.state.isLogin);
          sessionStorage.setItem("isUser", store.state.isUser);
          location.href = "/";
        })
        .catch((err) => {
          console.log(err)
          console.log(state.toggle)
          if (err.response.status >= 500) {
            alert("개인회원이 맞는지 확인해주세요!");
          }
          else {
            alert("잘못된 이메일 혹은 비밀번호입니다.")
          }
        });
    };
    const agencyLogin = function() {  
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
            localStorage.setItem("agencycode", res.data.agencycode);
            store.state.isLogin = true;
            store.state.isAgency = true;
            localStorage.setItem("isLogin", store.state.isLogin);
            localStorage.setItem("isUser", store.state.isUser);
            localStorage.setItem("isAgency", store.state.isAgency);
            sessionStorage.setItem("isLogin", store.state.isLogin);
            sessionStorage.setItem("isAgency", store.state.isAgency);
            location.href = "/";
          })
          .catch((err) => {
            console.log(err)
            console.log(state.toggle)
            if (err.response.status >= 500) {
              alert("기관회원이 맞는지 확인해주세요!");
            }
            else {
              alert("잘못된 이메일 혹은 비밀번호입니다.")
            }
          });
      }

    return { state, privateLogin, agencyLogin, goToSignup, goToFindPassword };
  },
};
</script>
<style>
.img {
  width: 20%;
  height: 21%;
  object-fit: cover;
}

.font-bold {
  font-weight: bold;
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

.btn-login {
  width: 100%;
  height: 50px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 30px;
  color: white;
}

.btn-logo {
  width: 5rem;
  height: 3rem;
  border-radius: 10px;
  border: #a4b5f0 3px solid;
  background-color: #ffffff;
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
