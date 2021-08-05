<template>
  <div class="Jbgc d-flex flex-column-reverse" style="height: 350px;">
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