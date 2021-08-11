<template>
  <div class="Jbgc col-4">
      <div class="card border-light lifecard mb-5" align="center" style="background-color:#FAF9FE">
      <img src="@\assets\dog.png" class="card-img-top guardimg" align="center">
      <div class="card-body d-flex flex-column justify-content-between">
        <h5 class="card-title font-bold">{{ guardians.agencyname }}</h5>
        <div class="card-text" style="margin-top:0px">{{ guardians.tel }}</div>
        <div class="card-text">{{ guardians.addr }}</div>
        <button class="btn-login" type="button" style="color: #FFFFFF">Read More</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    guardians: Object
  },
  setup(props) {
    console.log(props)
  }
}
</script>

<style>
.font-bold {
  font-weight: bold;
}

.Jbgc {
  background-color: #FAF9FE;
}

.btn-login {
  width: 100%;
  height: 50%;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 30px;
  color: white;
}

.guardimg {
  width: 100%;
  height: 50%;
  clip-path: circle(30% at 50% 50%);
}

.lifecard {
  width: 80%;
  height: 450px;
}
</style>