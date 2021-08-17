<template>
  <div>
    <JumbotronAnimalDetail v-if="state.aniInfo" :aniInfo="state.aniInfo" />
    <AnimalCarousel v-if="state.aniInfo" :aniInfo="state.aniInfo" />
    <AnimalAdoptionProcess />
  </div>
</template>

<script>
import axios from "axios";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import AnimalCarousel from "@/views/animal/components/AnimalCarousel.vue";
import JumbotronAnimalDetail from "@/views/animal/components/JumbotronAnimalDetail.vue";
import AnimalAdoptionProcess from "@/views/animal/components/AnimalAdoptionProcess.vue";

export default {
  components: {
    JumbotronAnimalDetail,
    AnimalCarousel,
    AnimalAdoptionProcess,
  },
  setup() {
    const router = useRouter();
    const state = reactive({
      aniInfo: null,
    });
    const getAnimal = function() {
      axios({
        method: "get",
        url:
          "https://i5a408.p.ssafy.io:8080/animal/details/" + router.currentRoute._value.params.id,
      })
        .then((res) => {
          state.aniInfo = res.data;
          console.log(state);
          // console.log(state.aniInfo)
        })
        .catch((error) => {
          console.log(error);
        });
    };
    getAnimal();
    return { state };
  },
};
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
