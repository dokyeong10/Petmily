<template>
  <div>
  <JumbotronAnimalDetail
    v-if="aniDetail.aniInfo"
    :aniInfo="aniDetail.aniInfo"
  />
  <AnimalCarousel
    v-if="aniDetail.aniInfo"
    :aniInfo="aniDetail.aniInfo"
  />
  <AnimalAdoptionProcess />
  </div>
</template>

<script>
import axios from 'axios'
import { reactive } from "vue";
import { useRouter } from 'vue-router' 
import AnimalCarousel from '@/views/animal/components/AnimalCarousel.vue'
import JumbotronAnimalDetail from '@/views/animal/components/JumbotronAnimalDetail.vue'
import AnimalAdoptionProcess from '@/views/animal/components/AnimalAdoptionProcess.vue'

export default {
  components: {
    JumbotronAnimalDetail,
    AnimalCarousel,
    AnimalAdoptionProcess,
  },
  setup() {
    const router = useRouter()
    const aniDetail = reactive({
      aniInfo: null,
    })
    const getAnimal = async function() {
      await axios({
        method: "get",
        url: 'http://localhost:8080/animal/details/' + router.currentRoute._value.params.id
      })
      .then((res) => {
        aniDetail.aniInfo = res.data;
        console.log(aniDetail)
        // console.log(aniDetail.aniInfo)
      })
      .catch((error) => {
        console.log(error)
      })
    }
    getAnimal()
    return { aniDetail }
  }
}
</script>

<style>
.Jbgc {
  background-color: #faf9fe;
}

.btn-chatting {
  width: 30%;
  height: 50px;
  background-color: #000000;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>