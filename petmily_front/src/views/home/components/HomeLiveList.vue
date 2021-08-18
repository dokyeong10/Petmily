<template>
  <br />
  <div class="container mt-5" style="height: 650px">
    <div class="d-flex justify-content-center">
      <div class="d-flex align-items-center w-50">
        <div
          class="font-bold"
          align="left"
          style="font-size: 26px; color: #ab0b14"
        >
          Live
        </div>
        <img src="@\assets\circle.png" style="width: 19px; height: 19px" />
      </div>
    </div>
    <div class="d-flex align-items-center w-50">
      <div class="mb-4" align="center" style="font-size: 30px">
        진행 중인 라이브
      </div>
    </div>
    <div id="liveCarousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div
          class="carousel-item"
          v-for="(live, idx) in lives"
          :key="idx"
          :class="{ active: idx == 0 }"
        >
          <img
            :src="live.img"
            class="w-80"
            style="height: 300px; border-radius: 12px;"
            onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
            @click="goLiveRoom(live.agencycode)"
          />
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#liveCarousel"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#liveCarousel"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
        </button>
      </div>
      <br />
      <br />
      <br />
    </div>
    <button
      type="button"
      @click="mvLiveList"
      class="btn-login mt-5 w-25 me-5"
      style="color: #ffffff"
    >
      <span class="me-3">더보기</span>
      <img src="@\assets\more.png" style="width: 19px; height: 19px; border-radius: 12px;" />
    </button>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
export default {
  props: {
    lives: Array,
  },
  name: "HomeLiveLIst",

  setup(props) {
    const router = useRouter();
    const mvLiveList = () => {
      location.href = "/live";
    };

    function goLiveRoom(agencycode) {
      console.log(agencycode);
      router.push({
        name: "liveroom",
        params: {
          agencycode: agencycode,
        },
      });
    }

    return { mvLiveList, goLiveRoom };
  },
};
</script>
<style>
.Jbgc {
  background-color: #b8b6f9;
}

.btn-login {
  width: 100%;
  height: 50px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 30px;
  color: white;
}

.font-bold {
  font-weight: bold;
}
</style>
