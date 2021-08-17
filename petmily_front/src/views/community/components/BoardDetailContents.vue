<template>
  <div class="container">
<<<<<<< HEAD
    <div v-if="boardInfo">
      <div class="d-flex justify-content-between pb-3">
        <h4>제목: {{ boardInfo.title }}</h4>
      </div>
      <div>
        <pre
          style="overflow:auto; white-space: pre-wrap;"
          align="left"
          class="d-flex justify-content-start"
        >
 {{ boardInfo.contents }} </pre
        >
      </div>
      <div v-if="userInfo.no === userno" class="d-flex justify-content-end">
        <button @click="goToModify" class="bn-modify m-1">수정</button>
        <button @click="deleteBoard" class="bn-delete m-1">삭제</button>
      </div>
      <div v-else-if="userInfo.agencycode === agencycode" class="d-flex justify-content-end">
        <button @click="goToModify" class="bn-modify m-1">수정</button>
        <button @click="deleteBoard" class="bn-delete m-1">삭제</button>
=======
    <div v-if="boardInfo" class="content-board">
      <div class="d-flex justify-content-center pb-3">
        <div class="title-board">제목: {{ boardInfo.title }}</div>
      </div>
      <div class="d-flex justify-content-center">
        <pre
          style="overflow:auto; white-space: pre-wrap;"
          align="left"
          class="d-flex justify-content-start content-board2"
        >
 {{ boardInfo.contents }} </pre
        >
>>>>>>> 0461f99be4ec5ba0d98706cf5f52187a39fc1ea6
      </div>
    </div>
    <div v-if="userInfo.no === userno" class="d-flex justify-content-end">
      <button @click="goToModify" class="bn-modify m-1">수정</button>
      <button @click="deleteBoard" class="bn-delete m-1">삭제</button>
    </div>
    <div v-else-if="userInfo.agencycode === agencycode" class="d-flex justify-content-end">
      <button @click="goToModify" class="bn-modify m-1">수정</button>
      <button @click="deleteBoard" class="bn-delete m-1">삭제</button>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import axios from "axios";
export default {
  name: "BoardDetailContents",
  props: {
    boardInfo: Object,
    userInfo: Object,
  },
  setup(props) {
    console.log(props.boardInfo);
    const router = useRouter();
    const userno = Number(localStorage.getItem("userno"));
    const agencycode = localStorage.getItem("agencycode");
    const deleteBoard = function() {
      axios({
        method: "delete",
<<<<<<< HEAD
        url: `https://i5a408.p.ssafy.io:8080/board/${props.boardInfo.bno}`,
=======
        url: `http://localhost:8080/board/${props.boardInfo.bno}`,
>>>>>>> 0461f99be4ec5ba0d98706cf5f52187a39fc1ea6
      })
        .then((res) => {
          console.log(res);
          location.href = "/community";
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const goToModify = function() {
      router.push({
        name: "boarddetailmodify",
        params: {
          boardno: router.currentRoute._value.params.boardno,
        },
      });
    };

    return { deleteBoard, goToModify, userno, agencycode };
  },
};
</script>
<style>
.bn-modify {
  width: 50px;
  height: 30px;
  background-color: #a4b5f0;
  border-style: none;
  border-radius: 12px;
  color: white;
}
.bn-delete {
  width: 50px;
  height: 30px;
  background-color: #ff8b8b;
  border-style: none;
  border-radius: 12px;
  color: white;
}
<<<<<<< HEAD
=======
.content-board {
  background: #fffff1;
  height: 350px;
  width: 550px;
  text-align: center;
  border-radius: 20%;
}
.title-board {
  font-weight: bold;

  font-size: 26px;
  margin-top: 4%;
  /* //margin-left: 15%; */
}
.content-board2 {
  margin-top: 7%;
  /* margin-left: 10%; */
  text-align: center;
  font-size: 16px;
}
>>>>>>> 0461f99be4ec5ba0d98706cf5f52187a39fc1ea6
</style>
