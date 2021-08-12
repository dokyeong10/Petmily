<template>
  <div>
    <JumboTronAnimalDetail
      v-for="(aniInfo, idx) in aniDetail.aniInfo"
      :key="idx"
      :aniInfo="aniInfo"
    />
    <AnimalCarousel v-for="(aniInfo, idx) in aniDetail.aniInfo" :key="idx" :aniInfo="aniInfo" />
  </div>
</template>

<script>
import axios from "axios";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import AnimalCarousel from "@/views/animal/components/AnimalCarousel.vue";
import JumboTronAnimalDetail from "@/views/animal/components/JumbotronAnimalDetail.vue";

export default {
  components: {
    JumboTronAnimalDetail,
    AnimalCarousel,
  },
  setup() {
    const router = useRouter();
    const aniDetail = reactive({
      aniInfo: "",
    });
    const getAnimal = async function() {
      axios({
        method: "get",
        url: "http://localhost:8080/animal/details/" + router.currentRoute._value.params.id,
      })
        .then((res) => {
          aniDetail.aniInfo = res.data;
          console.log(res);
          console.log(res);
        })
        .catch((error) => {
          console.log(error);
        });
    };
    getAnimal();
    return { aniDetail };
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
