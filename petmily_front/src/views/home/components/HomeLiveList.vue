<template>
  <br />
  <div class="container mt-5 justify-content-center" style="height: 650px;">
    <div class="d-flex ">
      <div class="d-flex flex-column align-items-start">
        <div class="d-flex">
          <div class="ms-5 font-bold" align="left" style="font-size: 26px;color: #AB0B14;">
            Live
          </div>
          <img src="@\assets\circle.png" style="width: 19px; height: 19px;" />
        </div>
        <div class="mb-4" align="left" style="font-size: 30px;">진행 중인 라이브</div>
      </div>
    </div>
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner ">
        <div class="carousel-item active">
          <img src="@\assets\dog.png" class="d-block w-50" />
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="d-block w-50" />
        </div>
        <div class="carousel-item">
          <img src="@\assets\dog.png" class="d-block w-50" />
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
    </div>
    <button
      type="button"
      @click="changePassword"
      class="btn-login mt-5 w-25 me-5"
      style="color: #FFFFFF;"
    >
      <span class="me-3">더보기</span>
      <img src="@\assets\more.png" style="width: 19px; height: 19px;" />
    </button>
  </div>
</template>
<script>
export default {
  name: "HomeLiveLIst",
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
