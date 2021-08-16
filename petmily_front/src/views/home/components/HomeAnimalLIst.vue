<template>
  <span class=" mx-3 mb-5" style="width: 18rem; background-color:#FAF9FE">
    <img
      :src="animals.profile_img"
      onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
      class="card-img-top main-animal"
      style="height: 220px;"
      @click="goDetail"
    />
    <div class="card-body">
      <h5 class="card-title font-bold mb-3 mt-2" align="left">{{ animals.type }}</h5>
      <div class="card-text mb-2" align="left" style="margin-top:0px">
        <span class="font-bold me-2">나이: </span>{{ animals.age }}
      </div>
      <span v-if="animals.sex">
        <div class="card-text mb-2" align="left">
          <span class="font-bold me-2">성별:</span> <span> 암컷</span>
        </div>
      </span>
      <span v-else>
        <div class="card-text mb-2" align="left">
          <span class="font-bold me-2">성별:</span> <span>수컷</span>
        </div>
      </span>
      <div class="card-text " align="left">
        <span class="font-bold me-2">종류:</span> {{ animals.species }}
      </div>
    </div>
    <button class="btn-login mb-3" style="height:35px;" @click="goDetail">상세보기</button>
  </span>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  props: {
    animals: Object,
  },
  setup(props) {
    const router = useRouter();
    const goDetail = function() {
      router.push({
        name: "animaldetail",
        params: {
          id: props.animals.no,
        },
      });
    };
    return { goDetail };
  },
};
</script>
<style>
.font-bold {
  font-weight: bold;
}
.main-animal {
  border-radius: 15%;
}
</style>
