<template>
  <div class="mb-5">
    <div class="container mt-5" style="height: 650px;">
      <div class="d-flex justify-content-center">
      <div class="d-flex align-items-center w-50">
    <!-- 이미지에 profile_img 넣어줘야 합니다. -->
    
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner ">
        <div class="carousel-item active">
          <img src="@\assets\dog.png" class="w-75"/>
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="w-75" />
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="w-75" />
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
        </button>
      </div>
    <br>
    <br>
    <br>
    <div class="fw-bold" style="font-size:2rem" align="left">
      보호센터 정보
    </div>
    <div v-if="aniInfo" class="position-relative mb-5 fw-bold"  align="left">
      <hr>
      <div class="mb-3">
       센터명: {{ aniInfo.shelter.agencyname }}
      </div>
      <div class="mb-3">
       위치: {{ aniInfo.shelter.addr }}
      </div>
      <div class="mb-3">
       대표 번호: {{ aniInfo.shelter.tel }}
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
import { useRouter } from 'vue-router' 

export default {
  props: {
    aniInfo: Object
  },
  setup(props) {
    console.log(props)
    const router = useRouter()
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
}

</script>

<style>
</style>