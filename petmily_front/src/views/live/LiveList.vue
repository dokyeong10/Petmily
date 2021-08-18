<template>
  <div class="col mt-3">
    <div class="card h-100 border-light" align="center" style="background-color:#FAF9FE">
      <div class="card-body d-flex flex-column justify-content-between">
        <h3 class="card-title font-bold mt-3 mb-4" style="font-size : 20px;">{{ live.title }}</h3>

        <img
          v-if="live.img != null"
          :src="live.img"
          class="card-img-top mb-3 justify-content-between imglive"
          style="border-radius:15px;height: 250px;"
          align="center"
          @click="goToRoom"
        /><img
          v-else
          src="@/assets/PetmilyLogo.png"
          class="card-img-top mb-3 justify-content-between imglive"
          style="border-radius:15px;height: 250px;"
          align="center"
          @click="goToRoom"
        />
        <div class="card-text font-bold mb-2 mt-2" style="font-size:15px; ">
          {{ live.description }}
        </div>
        <div class="card-text">
          시작 시간 : {{ live.startTime.substring(11, 13) }}시
          {{ live.startTime.substring(14, 16) }}분
        </div>
        <div>
          <button class="btn-live mt-3" @click="goToRoom">입장하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
export default {
  name: "LiveList",
  props: {
    live: Object,
  },
  setup(props) {
    const router = useRouter();
    const goToRoom = () => {
      router.push({
        name: "liveroom",
        params: {
          agencycode: props.live.agencycode,
        },
      });
    };
    return { goToRoom };
  },
};
</script>
<style>
.imglive {
  height: 100px;
}

.btn-live {
  justify-content: center;
  width: 50%;
  height: 40px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>
