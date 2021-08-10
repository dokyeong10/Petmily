<template>
  <div class="col">
    <div class="card-group">
      <div class="card">
        <!-- <img :src="animal.img" alt="animal"> -->
        <div class="card-body">
          <p class="card-text">나이: {{ animal.age }}</p>
          <p class="card-text">성별: {{ sex }} </p>
          <p class="card-text">중성화: {{ neutered }} </p>
          <p class="card-text">동물 종: {{ animal.type }} </p>
          <p class="card-text">종류: {{ animal.species }}</p>
          <p class="card-text">특이사항: {{ animal.text }}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from 'vue'

export default {
  name: 'PrivateFavoriteAnimal',
  props: {
    animal: {
      type: Object
    }
  },
  setup (props) {
    let sex = ref("")
    let neutered = ref("")
    const confirm = function() {
      if (props.animal.sex) {
        sex.value = "암컷"
      } else {
        sex.value = "수컷"
      }
      if (props.animal.neutered) {
        neutered.value = "⭕️"
      } else {
        neutered.value = "❌"
      }
    }
    confirm()
    return { sex, neutered }
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
}
</style>