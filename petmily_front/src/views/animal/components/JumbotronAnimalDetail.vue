<template>
  <div class="Jbgc" style="height: 600px;">
    <br/>
    <div class="container">
      <div class="d-flex justify-content-start">
        <div class="me-5">
          <img
            src="@\assets\dog.png"
            class="mt-3 mb-5"
            style="width: 500px; height: 390px; border-radius: 30px; margin-left :7%"
          />
          <button class="btn-chatting">채팅상담</button>
        </div>
        <div class="d-flex flex-column mt-5">
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            이름: {{ aniInfo.species }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            종류: {{ aniInfo.type }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            나이: {{ aniInfo.age }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-if="aniInfo.sex">
            성별: 수컷
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-else>
            성별: 암컷
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-if="aniInfo.neutered">
            중성화: O
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-else>
            중성화: X
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            특이사항: {{ aniInfo.text }}
          </div>
        </div>
      </div>
      <br>
      <div class="d-flex justify-content-end">
        <button class="me-3 btn-modify">수정하기</button>
        <button class="btn-delete">삭제하기</button>
      </div>
    </div>
  </div>
</template>

<script>
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
.btn-chatting {
  width: 30%;
  height: 50px;
  background-color: #000000b4;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.btn-modify {
  width: 10%;
  height: 30px;
  background-color: #A4B5F0;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.btn-delete {
  width: 10%;
  height: 30px;
  background-color: #FF8B8B;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>