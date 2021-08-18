<template>
  <div class="Jbgc">
    <br />
    <div class="container">
      <div class="d-flex justify-content-center mt-5">
        <div class="fw-bold me-5" style="font-size:50px">404 ERROR</div>
        <img src="@\assets\cat-icon.png" />
      </div>
      <div class="mt-5">
        <div class="fw-bold" style="font-size:30px">
          죄송합니다.
        </div>
        <div class="fw-bold mb-5" style="font-size:30px">
          요청하신 페이지를 찾을 수 없습니다.
        </div>
        <button class="btn-login mb-5" style="width:30%" @click="goHome">홈으로 가기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  name: "error",
  setup() {
    const router = useRouter();
    const goHome = function() {
      location.href = "/";
    };
    return { goHome };
  },
};
</script>

<style>
.Jbgc {
  background-color: #faf9fe;
}
.btn-login {
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
