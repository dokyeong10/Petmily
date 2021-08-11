<template>
  <br />
  <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
    <h2>내 프로필</h2>
  </div>
  <div>
    <div class="Jbgc mx-auto" style="height: 400px; width: 800px">
      <div
        class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center"
      >
        <div>
          <img :src="userInfo.img" style="height: 200px; width: 200px" />
        </div>
        <div align="left">
          <p>NAME {{ userInfo.username }}</p>
          <p>EMAIL {{ userInfo.email }}</p>
          <p>PHONE {{ userInfo.phone }}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "PrivateProfileForm",
  props: {
    userInfo: Object,
  },
  setup() {
    // console.log(props.userInfo)
  },
};
</script>
<style>
.btn-white {
  width: 200px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.inner30 {
  top: 30%;
}

.btn-l {
  width: 10rem;
  height: 3rem;
  border-radius: 12px;
  border-color: #b8b6f9;
  background-color: #b8b6f9;
}
</style>