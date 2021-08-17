<template>
  <br />
  <br />
  <div class="container">
    <div v-if="userInfo" class="d-flex justify-content-evenly pb-5">
      <div>
        <img :src="userInfo.img" alt="" style="width:300px" />
        <p class="board-user">{{ userInfo.username }}</p>
      </div>

      <div>
        <BoardDetailContents :boardInfo="boardInfo" :userInfo="userInfo" />
      </div>
    </div>
    <hr />

    <div v-if="userInfo" class="d-flex flex-column justify-content-center align-items-center" >
      <h4 class="mt-4 mb-3">{{ userInfo.username }}님의 후기 사진</h4>
      <div v-if="newFiles" style="max-width: 585px; height: 309.52px;">
        <div id="boardDetailUserCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-inner flex-row">
            <div class="carousel-item" v-for="(board, idx) in newFiles.files.boardFiles" :key="idx" :class="{ active: idx == 0 }">
              <div
                v-if="
                  board.extension == 'jpg' ||
                    board.extension == 'jpeg' ||
                    board.extension == 'png' ||
                    board.extension == 'bmp' ||
                    board.extension == 'gif'
                "
              >
                <img style="border-radius: 20px; max-width: 575.85px; height: 309.52px;" :src="board.file" alt="" />
              </div>
              <div v-else>
                <video :src="board.file" controls autoplay muted style="max-width: 575.85px; height: 309.52px;"></video>
              </div>
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#boardDetailUserCarousel"
            data-bs-slide="prev"
            style="height: 173.96px;"
          >
            <span style="color: red; margin-top: 141.96px;" class="carousel-control-prev-icon" aria-hidden="true"></span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#boardDetailUserCarousel"
            data-bs-slide="next"
            style="height: 173.96px;"
          >
            <span style="color: red; margin-top: 141.96px;" class="carousel-control-next-icon" aria-hidden="true"></span>
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
import BoardDetailContents from "@/views/community/components/BoardDetailContents.vue";

export default {
  name: "BoardDetailUser",
  components: {
    BoardDetailContents,
  },
  props: {
    userInfo: Object,
    boardFiles: Object,
    boardInfo: Object,
    newFiles: Object,
  },
  setup() {
    // console.log(props.boardFiles[0])
  },
};
</script>
<style>
.carousel-control-prev-icon {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23789ade' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E");
}
.carousel-control-next-icon {
  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23789ade' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E");
}
.img-re {
  border-radius: 15%;
}
.board-user {
  font-size: 23px;
  margin-top: 7%;
  font-weight: bold;
}
.carousel-inner{
  width: 575.85px !important;
}
</style>
