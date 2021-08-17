<template>
  <div>
  <JumbotronAnimalRegister/>
    <div class="d-flex flex-column align-items-center">
      <img src="@\assets\image-finish.png" style="width:300px;" />
      <br />
      <h3>
        <b>동물 등록에 성공하셨습니다.</b>
      </h3>
    </div>
    <br />
    <button class="btn-white" @click="goToAnimalList">동물 목록 보러 가기</button>
  </div>
</template>
<script>
// import { onMounted } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import JumbotronAnimalRegister from '@/views/animal/components/JumbotronAnimalRegister.vue'
export default {
  name: 'AnimalRegisterSuccess',
  components: {
    JumbotronAnimalRegister
  },
  setup () {
    const router = useRouter()
    const goToAnimalList = function () {
      router.push('/animallist')
    }
    return { goToAnimalList }
  }
}
</script>
<style>

</style>