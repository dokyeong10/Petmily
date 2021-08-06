<template>
  <div class="Jbgc" style="height: 350px;">
    <div class="d-flex flex-column align-items-start">
      <h3>유기동물 목록</h3>
      <h5>당신의 따뜻한 품을 기다리고 있습니다.</h5>
    </div>
    <div class="d-flex align-items-start">
      <div class="d-flex flex-column align-items-start">
        <label for="type">필터</label>
        <select name="type" id="type">
          <option value="null">선택</option>
          <option value="type">동물</option>
          <option value="addr">지역</option>
          <option value="agencyname">보호소</option>
        </select>
      </div>
    </div>
    <div class="d-flex align-items-end flex-column mb-3">
      <div v-if="isAgency">
        <button @click="goToAnimalRegister" class="mb-auto p-2" type="button">동물 등록 하기</button>
      </div>
    </div>
  </div>
  <br>
</template>
<script>
import { useRouter } from 'vue-router' 
import { computed } from '@vue/runtime-core'
export default {
  name: 'animalcategory',
  setup () {
    const router = useRouter()

    const goToAnimalRegister = function () {
      router.push('/animalregister')
    }

    const isAgency = computed(() => {
      console.log(sessionStorage.getItem('isAgency'))
      return sessionStorage.getItem('isAgency')
    })

    return { goToAnimalRegister, isAgency }
  }
}
</script>
<style>
.Jbgc {
  background-color: #FAF9FE;
}
</style>