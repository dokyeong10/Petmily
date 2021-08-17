<template>
  <div class="col-md-8">
    <div class="card h-50 w-50 border-light lifecard mb-1" align="center" style="background-color:#FAF9FE">
      <div class="card-body d-flex flex-column justify-content-start">
        <img :src="animals.profile_img" class="card-img-top rounded p-4" align="center" />
        <h5 class="card-title font-bold">{{ animals.type }}</h5>
        <div class="d-flex justify-content-center">
          <div align="left">
            <p class="card-text p-1" style="margin-top:0px">종: {{ animals.species }}</p>
            <p class="card-text p-1">성별: {{ sex }}</p>
            <p class="card-text p-1">중성화: {{ neutered }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from 'vue'
export default {
  name: 'ShelterAnimals',
  props: {
    animals: Object
  },
  setup (props) {
    let sex = ref("")
    let neutered = ref("")
    const confirm = function() {
      if (props.animals.sex) {
        sex.value = "암컷"
      } else {
        sex.value = "수컷"
      }
      if (props.animals.neutered) {
        neutered.value = "⭕️"
      } else {
        neutered.value = "❌"
      }
    }
    confirm()
    console.log(props.animals.sex)
    return { sex, neutered, props }
  }
}
</script>

<style>
  
</style>