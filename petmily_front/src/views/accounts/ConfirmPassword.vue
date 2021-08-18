<template>
  <div class="Jbgc d-flex mx-auto justify-content-center align-items-center" style="height: 250px;">
    <div class="row justify-content-center mb-5">
      <div class="font-bold mt-4" style="font-size:40px">Confim Password</div>
    </div>
  </div>
  <br />
  <p>본인 확인을 위해 비밀번호를 입력하세요</p>
  <div class="d-flex justify-content-center">
    <input
      v-model="password"
      type="password"
      class="form-control radius-border br"
      placeholder="비밀번호 입력"
      @keyup.enter="confirmEmail"
      id="email"
      style="height: 38px;"
    />
    <button
      @click="confirmPassword"
      type="button"
      class="btn-up"
      style="color: #FFFFFF; margin-left:10px;"
    >
      확인
    </button>
  </div>
</template>
<script>
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
export default {
  name: "ConfirmPassword",
  setup() {
    const router = useRouter();
    const password = ref("");

    const setToken = function() {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };

    const confirmPassword = function() {
      console.log(password.value);

      if (sessionStorage.getItem("isUser")) {
        axios({
          method: "post",
          url: "http://localhost:8080/auth/personal/me",
          data: {
            password: password.value,
          },
          headers: {
            Authorization: setToken(),
          },
        })
          .then((res) => {
            console.log(res);
            if (res.data) {
              router.push("/privatemodify");
            } else {
              alert("비밀번호를 다시 입력해주세요");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (sessionStorage.getItem("isAgency")) {
        console.log(71);
        axios({
          method: "post",
          url: "http://localhost:8080/auth/agency/me",
          data: {
            password: password.value,
          },
          headers: {
            Authorization: setToken(),
          },
        })
          .then((res) => {
            console.log(res);
            if (res.data) {
              router.push("/agencymodify");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };

    return { password, confirmPassword };
  },
};
</script>
<style></style>
