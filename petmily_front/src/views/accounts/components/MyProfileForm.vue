<template>
  <br>
  <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
    <h2>
      내 프로필
    </h2>
  </div>
  <div class="Jbgc mx-auto" style="height: 400px; width: 800px;">
    <div class="position-relative inner d-flex justify-content-evenly align-items-center">
      <div>
        Profile Photo
      </div>
      <div>
        <p>ID</p>
        <p>AGENCY</p>
        <p>EMAIL</p>
        <p>PHONE</p>
        <p>NAME</p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'myprofileform',
}
</script>
<style>
.btn-white {
  width: 200px;
  background-color: #A4B5F0;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.inner {
  top: 30%;
}
</style>