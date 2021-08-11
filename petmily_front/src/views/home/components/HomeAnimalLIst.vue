<template>
  <span class="card mx-3 mb-5" style="width: 18rem;">
    <img src="@\assets\dog.png" class="card-img-top" style="height: 250px;" @click="goDetail"/>
    <div class="card-body">
      <h5 class="card-title font-bold" align="left">{{ animals.type }}</h5>
      <div class="card-text" align="left" style="margin-top:0px">나이: {{ animals.age }}</div>
      <span v-if="animals.sex">
        <div class="card-text" align="left">성별: 암컷</div>
      </span>
      <span v-else>
        <div class="card-text" align="left">성별: 수컷</div>
      </span>
      <div class="card-text" align="left">종류: {{ animals.species }}</div>
    </div>
    <button @click="goDetail">상세보기</button>
  </span>
</template>
<script>
import { useRouter } from 'vue-router' 

export default {
  props: {
    animals: Object,
  },
  setup(props) {
    const router = useRouter()
    const goDetail = function() {
      router.push({
        name: "animaldetail",
        params: {
          id: props.animals.no
        }
      })
    }
  return { goDetail }
  },
};
</script>
<style>
.font-bold {
  font-weight: bold;
}
</style>
