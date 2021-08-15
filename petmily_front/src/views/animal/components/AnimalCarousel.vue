<template>
  <div class="mb-5">
    <div class="container mt-5" style="height: 650px;">
      <div class="d-flex justify-content-center">
        <div class="d-flex align-items-center w-50">
          <!-- 이미지에 profile_img 넣어줘야 합니다. -->

          <div>
            <div id="animalCarousel" class="carousel slide" data-bs-ride="carousel">
              <div v-if="aniInfo.animalFiles">
                <div class="carousel-inner">
                  <div class="carousel-item" v-for="(file, idx) in aniInfo.animalFiles" :key="idx" :class="{ active: idx == 0 }">
                    <div v-if="file.extension == 'jpg' || file.extension == 'jpeg' || file.extension == 'png' || file.extension == 'bmp' || file.extension == 'gif'">
                      <img :src="file.file" class="w-75" />
                    </div>
                    <div v-else>
                      <video :src="file.file" class="w-75" controls autoplay muted></video>
                    </div>
                  </div>
                  <button
                    class="carousel-control-prev"
                    type="button"
                    data-bs-target="#animalCarousel"
                    data-bs-slide="prev"
                  >
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  </button>
                  <button
                    class="carousel-control-next"
                    type="button"
                    data-bs-target="#animalCarousel"
                    data-bs-slide="next"
                  >
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  </button>
                </div>
              </div>
              <br />
              <br />
              <br />
            </div>
            <div class="fw-bold" style="font-size:2rem" align="left">
              보호센터 정보
            </div>
            <div v-if="aniInfo" class="position-relative mb-5 " align="left">
              <hr />
              <div class="mb-3">
                <span class="title-s"> 센터명 : </span>
                <span class="content-s">{{ aniInfo.shelter.agencyname }}</span>
              </div>
              <div class="mb-3">
                <span class="title-s">위치 : </span>
                <span class="content-s">{{ aniInfo.shelter.addr }}</span>
              </div>
              <div class="mb-3">
                <span class="title-s">대표 번호 :</span
                ><span class="content-s"> {{ aniInfo.shelter.tel }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="me-5" align="right">
        <button
          type="button"
          @click="goToShleterDetail"
          class="btn-login w-25 mb-5"
          style="color: #FFFFFF;"
        >
          <span class="me-3">상세보기</span>
          <img src="@\assets\more.png" style="width: 19px; height: 19px;" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  props: {
    aniInfo: Object,
  },
  setup(props) {
    console.log(props);
    const router = useRouter();
    const goToShleterDetail = () => {
      router.push({
        name: "shelterdetail",
        params: {
          agencycode: props.aniInfo.shelter.agencycode,
        },
      });
    };
    return { goToShleterDetail };
  },
};
</script>

<style>
.title-s {
  font-weight: bold;
  font-size: 18px;
}
.content-s {
  font-size: 18px;
}
</style>
