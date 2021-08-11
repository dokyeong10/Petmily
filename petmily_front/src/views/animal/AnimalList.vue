<template>
  <div>
    <AnimalCategory/>
    <AnimalListItem/>
  </div>
</template>
<script>
import AnimalCategory from '@/views/animal/components/AnimalCategory'
import AnimalListItem from '@/views/animal/components/AnimalListItem'

export default {
  name: 'animallist',
  components: {
    AnimalCategory,
    AnimalListItem
  }
}

</script>
<style>
  
</style>