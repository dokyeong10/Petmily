<template>
  <div>
    <div class="container mt-5" style="height: 650px;">
      <div class="d-flex justify-content-center">
      <div class="d-flex align-items-center w-50">
    <!-- 이미지에 profile_img 넣어줘야 합니다. -->
    
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner ">
        <div class="carousel-item active">
          <img src="@\assets\dog.png" class="w-100"/>
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="w-100" />
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="w-100" />
        </div>
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
    <br>
    <br>
    <br>
    <div class="fw-bold" style="font-size:2rem" align="left">
      보호센터 정보
    </div>
    <div class="position-relative mb-5 fw-bold"  align="left">
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
  </div>
    <button
      type="button"
      @click="changePassword"
      class="btn-login w-25"
      style="color: #FFFFFF;"
    >
      <span class="me-3">상세보기</span>
      <img src="@\assets\more.png" style="width: 19px; height: 19px;" />
    </button>
  </div>
</template>

<script>
// import { useRouter } from 'vue-router' 

export default {
  props: {
    aniInfo: Object
  },
  setup(props) {
    console.log(props)

  },
}

</script>

<style>
</style>