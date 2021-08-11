<template>
  <div></div>
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