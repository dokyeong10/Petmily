<template>
  <div class="container">
    <div class="align-content-center">
      <input class="form-control radius-border" type="text" placeholder="사진 혹은 동영상을 넣어주세요.">
      <input class="form-control radius-border" type="text" placeholder="ex) 강아지, 고양이">
      <input class="form-control radius-border" type="text" placeholder="ex) 말티즈 시바">
      <input class="form-control radius-border" type="text" placeholder="ex) 3살 추정">
      <div class="d-flex justify-content-evenly">
        <div>
          성별:
          <span>
            <label><input type="radio" name="sex" v-model="sexToggle" checked="checked">수컷</label>
            <label><input type="radio" name="sex" v-model="sexToggle" checked="checked">암컷</label>
          </span>
        </div>
        <div>
          중성화 유무:
          <span>
            <label><input type="radio" name="neutered" v-model="sexToggle" checked="checked">Yes</label>
            <label><input type="radio" name="neutered" v-model="sexToggle" checked="checked">No</label>
          </span>
        </div>
      </div>
      <input class="form-control radius-border" type="text" placeholder="발견 주소">
      <input class="form-control radius-border" type="text" placeholder="특이사항">
      <input class="form-control radius-border" type="datetime-local" placeholder="발견날짜">
      <button class="btn-white" type="button">등록하기</button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'AnimalRegister'
}
</script>
<style>
.br {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
}

.btn-white {
  width: 200px;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.radius-border {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
  width: 400px;
  color: #789ADE;
}
</style>