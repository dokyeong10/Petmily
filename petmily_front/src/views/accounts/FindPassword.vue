<template>
  <br />
  <br />
  <div class="container">
    <br />
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3 mx-5">
        <div class="font-weight-bold" style="font-size: 30px">Forgot Password!</div>
        <div>
          <span>Don’t have an account,</span
          ><button style="color: #8699DA" class="bw-color mb-3" @click="goToSignup">Sign up</button>
        </div>
        <form>
          <span>
            <input
              v-model="state.findEmail"
              class="mt-4 mb-4 form-control radius-border rounded-pill"
              type="text"
              placeholder="email"
            />
            <button
              class="btn-login mb-5"
              type="button"
              style="color: #FFFFFF"
              @click="confirmEmail"
            >
              이메일 인증하기
            </button>
          </span>
          <span v-if="state.isEmail">
            <input
              v-model="state.authNum"
              class="mt-4 mb-4 form-control radius-border rounded-pill"
              type="text"
              placeholder="인증번호"
            />
            <button class="btn-login" type="button" style="color: #FFFFFF" @click="confirmNumber">
              인증하기
            </button>
          </span>
          <div v-if="state.isConfirm">
            인증되었습니다.
          </div>
          <!-- <div v-if="state.isConfirm">
            <button class="btn-white" style="color: #FFFFFF;" @click="goToChangePassword">비밀번호 바꾸러 가기</button>
          </div> -->
        </form>
      </div>
      <img
        class="ms-3"
        style="border-radius: 12px; width: 400px;"
        alt="petmily login"
        src="@/assets/login.png"
      />
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import axios from "axios";
export default {
  name: "findpassword",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      findEmail: "",
      authNum: "",
      isEmail: false,
      isConfirm: false,
    });

    const confirmEmail = function() {
      console.log(state.findEmail);
      axios({
        method: "post",
        url: "https://i5a408.p.ssafy.io:8080/email/send/findpwd",
        data: {
          email: state.findEmail,
        },
      })
        .then((res) => {
          console.log(res);
          alert("입력하신 메일로 인증번호가 발송되었습니다.");
          store.state.findEmail = state.findEmail;
          localStorage.setItem("findEmail", store.state.findEmail);
          state.isEmail = true;
          console.log(state.isEmail);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const confirmNumber = function() {
      console.log(store.state.findEmail);
      console.log(state.authNum);
      axios({
        method: "post",
        url: "https://i5a408.p.ssafy.io:8080/email/check/authcode",
        data: {
          email: store.state.findEmail,
          authCode: state.authNum,
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data) {
            alert("인증에 성공하셨습니다.");
            state.isConfirm = true;
            router.push("/changepassword");
          } else {
            alert("잘못된 인증 번호입니다.");
            return;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const goToSignup = function() {
      router.push("/signupterms");
    };
    return { state, goToSignup, confirmEmail, confirmNumber };
  },
};
</script>
<style>
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

.radius-border {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  width: 400px;
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
</style>
