<template>
  <br />
  <br />
  <div class="container">
    <br />
    <div class="d-flex justify-content-center">
      <div align="left" class="mb-3 mx-5">
        <div class="font-bold" style="font-size: 30px">Forgot Password!</div>
        <div>
          <span>Don’t have an account,</span
          ><button style="color: #8699DA" class="bw-color" @click="goToSignup">Sign up</button>
        </div>
        <form>
          <div>
            <input
              v-model="state.password"
              class="mt-4 mb-4 form-control radius-border rounded-pill"
              type="password"
              placeholder="New Password"
            />
          </div>
          <div>
            <input
              v-model="state.confirmPassword"
              class="mb-1 form-control radius-border rounded-pill"
              type="password"
              placeholder="Confirm Password"
            />
          </div>
          <div class="position-relative mb-5">
            <button
              type="button"
              @click="changePassword"
              class="btn-login mt-5"
              style="color: #FFFFFF;"
            >
              비밀번호 바꾸기
            </button>
          </div>
        </form>
        <div v-if="isConfirm">
          인증되었습니다.
        </div>
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
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import axios from "axios";
import { reactive } from "@vue/reactivity";
export default {
  name: "changepassword",
  setup() {
    const state = reactive({
      password: "",
      confirmPassword: "",
    });

    const store = useStore();
    const router = useRouter();

    const changePassword = function() {
      const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/;
      if (!reg.test(state.password)) {
        return alert(
          "비밀번호는 8자 이상 16자 이하이어야 하며 숫자, 대소문자, 특수문자가 적어도 하나 이상 포함되어야 합니다."
        );
      }

      if (state.password != state.confirmPassword) {
        return alert("비밀번호가 일치하지 않습니다.");
      } else {
        axios({
          method: "patch",
          url: "http://localhost:8080/users/findpwd/change",
          data: {
            email: store.state.findEmail,
            password: state.password,
          },
        })
          .then((res) => {
            console.log(res);
            //
            router.push("/login");
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };

    return { state, changePassword };
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

.radius-border {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  width: 400px;
  color: #789ade;
}

.font-bold {
  font-weight: bold;
}

.bw-color {
  background-color: #ffffff;
  border-right: #ffffff 1px solid;
  border-left: #ffffff 1px solid;
  border-top: #ffffff 1px solid;
  border-bottom: #ffffff 1px solid;
  font-weight: bold;
}
</style>
