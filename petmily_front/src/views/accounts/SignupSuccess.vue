<template>
  <div>
    <JumbotronTerms/>
    <div>
      회원가입에 성공하셨습니다.
    </div>
    <button class="btn-white" @click="goTologin">로그인 하러 가기</button>
  </div>
</template>
<script>
// import { onMounted } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import JumbotronTerms from '@/views/accounts/components/JumbotronTerms'
export default {
  name: 'SignupSuccess',
  components: {
    JumbotronTerms
  },
  setup () {
    // const store = useStore()
    const router = useRouter()

    const goTologin = function () {
      router.push('/login')
    }

    // onMounted(() => {
    //   console.log(store.state.isSignup)
    //   if(store.state.isSignup) {
    //     router.push('/home')
    //   }
    // })
    // 내일 물어보기
    return { goTologin }
  }
}
</script>
<style>
.btn-white {
  width: 200px;
  background-color: #A4B5F0;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
  color: #FFFFFF;
}
</style>