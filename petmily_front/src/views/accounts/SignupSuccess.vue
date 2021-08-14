<template>
  <div>
    <JumbotronSuccess />
    <div class="d-flex flex-column align-items-center">
      <img src="@\assets\image-finish.png" style="width:300px;" />
      <br />
      <h3>
        <b>회원 가입이 완료되었습니다.</b>
      </h3>
    </div>
    <br />
    <button class="btn-white" @click="goTologin">로그인 하러 가기</button>
  </div>
  <br />
</template>
<script>
// import { onMounted } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from "vue-router";
import JumbotronSuccess from "@/views/accounts/components/JumbotronSuccess";
export default {
  name: "SignupSuccess",
  components: {
    JumbotronSuccess,
  },
  setup() {
    // const store = useStore()
    const router = useRouter();

    const goTologin = function() {
      router.push("/login");
    };

    // onMounted(() => {
    //   console.log(store.state.isSignup)
    //   if(store.state.isSignup) {
    //     router.push('/home')
    //   }
    // })
    // 내일 회의하기
    return { goTologin };
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
  color: #ffffff;
}
</style>
