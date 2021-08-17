<template>
  <br>
  <br>
  <div class="container">
    <div v-if="userInfo" class="d-flex justify-content-evenly pb-5">
      <div class="card" style="width: 12rem;">
        <img :src="userInfo.img" alt="">
          <div class="card-body">
            <p class="card-text">{{ userInfo.username }}</p>
          <hr>
          </div>
      </div>
      <div>
      <BoardDetailContents
        :boardInfo="boardInfo"
        :userInfo="userInfo"
      />
      </div>
    </div>
    <hr>
    <!-- 케로젤 -->
    <!-- 케로젤 문제 생김 안넘감...망함.. -->
    <div v-if="userInfo">
    <h4> {{ userInfo.username }}님의 후기 사진  </h4>
    <div v-if="boardFiles">
      <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner mx-auto">
          <div class="carousel-item active">
            <img :src="userInfo.img" alt="" height="200" width="200">
          </div>
          <div class="carousel-item" v-for="(board, idx) in boardFiles.img" :key="idx">
            <img :src="board.file" alt="" height="200" width="200">
          </div>
        </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="prev"
      >
        <span style="color: red;" class="carousel-control-prev-icon" aria-hidden="true"></span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleControls"
        data-bs-slide="next"
      >
        <span style="color: red;" class="carousel-control-next-icon" aria-hidden="true"></span>
      </button>
      </div>
    </div>
    </div>
    <!-- <div v-if="boardFiles.video.length"> -->
      <!-- <carousel :autoplay="true" :nav="false" :dots="false" class="marginTop50"> -->
        <!-- <video :src="boardFiles.video.file"></video> -->
        <!-- <div v-if="boardFiles.video.length">
          <video muted>
            <source :src="boardFiles.video[0].file" type="video/mp4">
            <strong>Your browser does not support the video tag.</strong>
          </video>
        </div> -->
        <!-- </carousel> -->
    <!-- </div> -->
    
  </div>

  <!-- <carousel :autoplay="true" :nav="false" :dots="false" class="marginTop50">
    <img v-for="(board, idx) in boardFiles.img" :key="idx" :src="board.file" alt="" height="200" width="200">
  </carousel> -->

</template>
<script>
import carousel from 'vue-owl-carousel'
import BoardDetailContents from '@/views/community/components/BoardDetailContents.vue'
export default {
  name: 'BoardDetailUser',
  components: {
    BoardDetailContents,
    carousel
  },
  props: {
    userInfo: Object,
    boardFiles: Object,
    boardInfo: Object
  },
  setup () {
    // console.log(props.boardFiles[0])
  }
}
</script>
<style>
.carousel-control-prev-icon { 
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23789ade' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E");
  }
.carousel-control-next-icon {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23789ade' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E");
  }


</style>