<template>
  <div class="container">
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
      </div>
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
        url: `https://i5a408.p.ssafy.io:8080/board/${props.boardInfo.bno}`,
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
</style>
