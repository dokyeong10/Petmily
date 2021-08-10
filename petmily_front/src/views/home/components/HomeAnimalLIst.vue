<template>
    <span class="card mx-3 mb-5" style="width: 18rem;">
      <img src="@\assets\dog.png" class="card-img-top" style="height: 300px;">
      <div class="card-body">
        <h5 class="card-title font-bold" align="left">이름</h5>
        <div class="card-text" align="left" style="margin-top:0px">나이: 3세</div>
        <div class="card-text" align="left">성별: 수컷</div>
        <div class="card-text" align="left">종류: 시바견</div>
      </div>
    </span>
</template>
<script>
export default {
  name: 'HomeAnimalList'
}
</script>
<style>

.font-bold {
  font-weight: bold;
}

</style>