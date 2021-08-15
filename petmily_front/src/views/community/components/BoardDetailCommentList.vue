<template>
  <div>
    <div>
      <div v-if="replyJoins">
        <div
          v-for="(reply, idx) in replyJoins"
          :key="idx + 'a'"
          class="d-flex justify-content-between"
        >
          <div v-if="reply.user === null" class="d-flex justify-content-between">
            <div>
              <img
                :src="reply.agency.img"
                onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
                style="width: 20px; height: 20px;"
              />
            </div>
            <div>{{ reply.agency.username }}</div>
          </div>
          <div v-else class="d-flex justify-content-between">
            <img
              :src="reply.user.img"
              onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
              style="width: 20px; height: 20px;"
            />
            <div>{{ reply.user.username }}</div>
          </div>
          <div>
            {{ reply.regdate.substring(0, 10) }}
          </div>
          <div>{{ reply.contents }}</div>
          <button v-if="!reply.toggle" @click="changeToggle(idx)" class="btn-white pe-3">
            답글
          </button>
          <div v-if="reply.toggle">
            <div v-for="(comment, idx) in comments" :key="idx + 'c'">
              <div v-if="comment.grp === reply.grp">
                {{ comment.contents }}
              </div>
            </div>
            <form class="d-flex">
              <p>
                <textarea
                  v-model="state.comment[idx]"
                  style="overflow:auto; white-space: pre-wrap;"
                  class="form-control radius-border"
                  placeholder="내용을 입력하세요."
                >
                </textarea>
              </p>
              <button @click="commentInput(idx, reply)" class="btn-white pe-3">
                답글 입력하기
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
// import { useRouter } from 'vue-router'
import axios from "axios";
export default {
  name: "BoardDetailCommentList",
  props: {
    replyJoins: {
      type: Array,
    },
    comments: {
      type: Array,
    },
  },
  setup(props) {
    // const router = useRouter()
    const state = reactive({
      comment: [],
    });
    console.log(props.replyJoins);
    const toggleCal = function() {
      let len = props.replyJoins.length;
      for (let i = 0; i < len; i++) {
        props.replyJoins[i].toggle = false;
        state.comment[i].push("");
      }
    };
    toggleCal();

    const changeToggle = (idx) => {
      if (!props.replyJoins[idx].toggle) {
        props.replyJoins[idx].toggle = true;
      } else {
        props.replyJoins[idx].toggle = false;
      }
      console.log(props.replyJoins[idx].toggle);
    };

    const commentInput = (idx, reply) => {
      if (localStorage.getItem("isAgency") === false) {
        console.log(localStorage.getItem("userno"));
        axios({
          method: "post",
          url: "https://i5a408.p.ssafy.io:8080/board/reply/re/register",
          data: {
            replno: reply.no,
            contents: state.comment[idx],
            bno: reply.bno,
            userno: localStorage.getItem("userno"),
          },
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        })
          .then((res) => {
            console.log(res);
            // router.go(`/boarddetail/+${reply.bno}`)
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        axios({
          method: "post",
          url: "https://i5a408.p.ssafy.io:8080/board/reply/re/register",
          data: {
            replno: reply.no,
            contents: state.comment[idx],
            bno: reply.bno,
            agencycode: localStorage.getItem("agencycode"),
          },
          headers: {
            Authorization: "Bearer " + localStorage.getItem("jwt"),
          },
        })
          .then((res) => {
            console.log(res);
            // router.go(`/boarddetail/+${reply.bno}`)
          })
          .catch((err) => {
            console.log(err);
          });
      }
      // changeToggle(idx)
    };
    return { state, changeToggle, commentInput };
  },
};
</script>
<style></style>
