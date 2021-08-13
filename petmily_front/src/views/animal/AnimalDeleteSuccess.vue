<template>
  <div>
    <JumbotronAnimalDeleteSuccess/>
    <div class="d-flex flex-column align-items-center">
      <img src="@\assets\image-finish.png" style="width: 512px;">
      <br>
      <h3>
        <b>삭제가 완료되었습니다.</b>
      </h3>
    </div>
    <br>
    <button class="btn-white" @click="goToHome">홈으로 가기</button>
  </div>
  <br>
</template>
<script>
// import { onMounted } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import JumbotronAnimalDeleteSuccess from '@/views/animal/components/JumbotronAnimalDeleteSuccess'
export default {
  name: 'animaldeletesuccess',
  components: {
    JumbotronAnimalDeleteSuccess
  },
  setup () {
    const router = useRouter()

    const goToHome = function () {
      router.push('/')
    }

    return { goToHome }
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