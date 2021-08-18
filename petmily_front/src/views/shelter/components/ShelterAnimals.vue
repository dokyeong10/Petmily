<template>
  <div class="col">
    <div class="card h-100 border-light" align="center" style="background-color:#FAF9FE">
      <div class="card-body d-flex flex-column align-items-center justify-content-start">
        <img :src="animals.profile_img" class="p-4" style="width: 200px; height: 200px;"/>
        <h5 class="card-title font-bold">{{ animals.type }}</h5>
        <div class="d-flex justify-content-center">
          <div class="m-2" align="left">
            <hr>
            <div class="m-2">
              <span class="card-text p-1 font-bold" style="margin-top:0px">종:</span><span>{{ animals.species }}</span>
            </div>
            <div class="m-2">
              <span class="card-text p-1 font-bold">성별:</span><span>{{ sex }}</span>
            </div>
            <div class="m-2">
              <span class="card-text p-1 font-bold">중성화:</span><span>{{ neutered }}</span>
            </div>
            <hr>
            <div class="m-2">
              <button class="btn-white btn-sm ms-2" @click="goToAnimalDetail"> 상세보기 </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
export default {
  name: 'ShelterAnimals',
  props: {
    animals: Object
  },
  setup (props) {
    let sex = ref("")
    let neutered = ref("")
    const router = useRouter()
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
    console.log(props.animal)
    const goToAnimalDetail = function () {
      router.push({name: 'animaldetail',
        params: {
          id: props.animals.no
        }
      })
    }


    return { sex, neutered, props, goToAnimalDetail }
  }
}
</script>

<style>
  
</style>