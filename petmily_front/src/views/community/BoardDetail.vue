<template>
  <div>
    <BoardDetailJumbotron />
    <BoardDetailUser :userInfo="userInfo" />
    <BoardDetailContents :boardInfo="boardInfo" />
    <BoardDetailReview :replyJoins="replyJoins" />
  </div>
</template>
<script>
import { useRouter } from "vue-router";
import { onMounted, reactive } from "vue";
import axois from "axios";

// components
import BoardDetailJumbotron from "@/views/community/components/BoardDetailJumbotron.vue";
import BoardDetailUser from "@/views/community/components/BoardDetailUser.vue";
import BoardDetailContents from "@/views/community/components/BoardDetailContents.vue";
import BoardDetailReview from "@/views/community/components/BoardDetailReview.vue";

export default {
  name: "BoardDetail",
  components: {
    BoardDetailJumbotron,
    BoardDetailUser,
    BoardDetailContents,
    BoardDetailReview,
  },
  setup() {
    const router = useRouter();
    const boardInfo = reactive({
      title: "",
      contents: "",
      reg_date: "",
    });

    const userInfo = reactive({
      data: null,
    });

    const boardFiles = reactive({
      data: null,
    });

    const replyJoins = reactive({
      data: null,
    });

    onMounted(() => {
      axois({
        method: "get",
        url: "http://localhost:8080/board/details/" + router.currentRoute._value.params.boardno,
      })
        .then((res) => {
          console.log(res);
          boardInfo.title = res.data.title;
          boardInfo.contents = res.data.content;
          boardInfo.reg_date = res.data.reg_date.substring(0, 10);
          userInfo.data = res.data.user;
          replyJoins.data = res.data.replyJoins;
          console.log(boardInfo);
          console.log(userInfo.data);
          console.log(replyJoins.data);
        })
        .catch((err) => {
          console.log(err);
        });
    });
    return { boardInfo, userInfo, boardFiles, replyJoins };
  },
};
</script>
<style></style>
