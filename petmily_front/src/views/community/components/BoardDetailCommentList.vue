<template>
  <br />
  <br />
  <div>
    <div>
      <div v-if="replyJoins">
        <div class="position-relative mb-5">
          <hr />
          <div
            class="position-absolute top-50 start-50 translate-middle px-2 bg-white"
            style="font-size: 25px"
          >
            댓글 {{ replyJoins.length }} 개
          </div>
        </div>
        <!-- replyList -->
        <div v-for="(reply, idx) in replyJoins" :key="idx + 'a'" class="p-1">
          <div class="m-2">
            <div v-if="reply.user === null" class="d-flex justify-content-start">
              <div align="left">
                <div class="d-flex justify-content-start">
                  <img
                    :src="reply.agency.img"
                    onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
                    style="width: 50px; height: 50px;"
                    class="p-1"
                  />
                  <div class="d-flex align-items-center p-2">{{ reply.agency.username }}</div>
                  <div class="d-flex align-items-center p-2">
                    {{ reply.regdate.substring(0, 10) }}
                  </div>
                  <div
                    v-if="reply.agency.agencycode == agencycode"
                    class="d-flex align-items-center p-2"
                  >
                    <button @click="replyDelete(idx)" class="bt-white btn-sm ms-2">삭제</button>
                  </div>
                </div>
                <div class="p-4" style="font-size: 20px">{{ reply.contents }}</div>
              </div>
            </div>
            <div v-else>
              <div align="left">
                <div class="d-flex justify-content-start">
                  <img
                    :src="reply.user.img"
                    onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
                    style="width: 50px; height: 50px;"
                    class="p-1"
                  />
                  <div class="d-flex align-items-center p-2">{{ reply.user.username }}</div>
                  <div class="d-flex align-items-center p-2">
                    {{ reply.regdate.substring(0, 10) }}
                  </div>
                  <div v-if="reply.user.no == userno" class="d-flex align-items-center p-2">
                    <button @click="replyDelete(idx)" class="bt-white btn-sm ms-2">삭제</button>
                  </div>
                </div>
                <div class="p-4" style="font-size: 20px">{{ reply.contents }}</div>
              </div>
            </div>
            <div class="d-flex justify-content-start">
              <button @click="changeToggle(idx)" class="bt-reply btn-sm ms-4">답글</button>
            </div>

            <!-- <hr> -->
            <!-- commentList -->
            <div v-if="reply.toggle">
              <div
                v-for="(comment, idx) in comments"
                :key="idx + 'c'"
                class="d-flex justify-content-start mx-auto"
                style="width: 80%"
              >
                <div v-if="comment.grp === reply.grp">
                  <div v-if="comment.user != null">
                    <span class="font-bold pb-3">{{ comment.user.username }} : </span>
                    <span> {{ comment.contents }} </span>
                    <button
                      @click="commentDelete(idx)"
                      v-if="comment.user.no === userno"
                      class="bt-white btn-sm ms-2"
                    >
                      삭제
                    </button>
                  </div>
                  <div v-else>
                    <span class="font-bold pb-3">{{ comment.agency.username }} : </span>
                    <span> {{ comment.contents }} </span>
                    <button
                      @click="commentDelete(idx)"
                      v-if="comment.agency.agencycode == agencycode"
                      class="bt-white btn-sm ms-2"
                    >
                      삭제
                    </button>
                  </div>
                </div>
              </div>
              <br />
              <!-- 입력부분 -->
              <div class="mx-auto" style="width: 80%;">
                <form>
                  <p>
                    <textarea
                      v-model="state.comment[idx]"
                      style="overflow:auto; white-space: pre-wrap; width: 100%"
                      class="form-control radius-border p-3"
                      placeholder="내용을 입력하세요."
                    >
                    </textarea>
                  </p>
                </form>
                <div class="d-flex justify-content-end">
                  <button @click="commentInput(idx, reply)" class="bt-reply btn-sm">
                    답글 입력하기
                  </button>
                </div>
              </div>
              <hr />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import { useRouter } from "vue-router";
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
    const router = useRouter();
    const state = reactive({
      comment: [],
    });
    const toggleCal = function() {
      let len = props.replyJoins.length;
      for (let i = 0; i < len; i++) {
        props.replyJoins[i].toggle = false;

        state.comment[i].push("");
      }
    };
    toggleCal();
    console.log(props.replyJoins);

    const userno = Number(localStorage.getItem("userno"));
    const agencycode = localStorage.getItem("agencycode");

    const changeToggle = (idx) => {
      if (!props.replyJoins[idx].toggle) {
        props.replyJoins[idx].toggle = true;
      } else {
        props.replyJoins[idx].toggle = false;
      }
      // console.log(props.replyJoins[idx].toggle)
    };

    const commentInput = (idx, reply) => {
      // console.log(localStorage.getItem('isAgency'))
      if (localStorage.getItem("isAgency") === "false") {
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
            router.go(`/boarddetail/+${reply.bno}`);
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
            router.go(`/boarddetail/+${reply.bno}`);
          })
          .catch((err) => {
            console.log(err);
          });
      }
      // const cancelInput = function () {
      //   console.log(1)
      // }
    };

    const replyDelete = function(idx) {
      axios({
        method: "delete",
        url: "https://i5a408.p.ssafy.io:8080/board/reply/delete",
        data: {
          replno: props.replyJoins[idx].no,
          bno: props.replyJoins[idx].bno,
        },
      })
        .then((res) => {
          console.log(res);
          router.go(`/boarddetail/+${props.replyJoins[idx].bno}`);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const commentDelete = function(idx) {
      axios({
        method: "delete",
        url: "https://i5a408.p.ssafy.io:8080/board/reply/delete",
        data: {
          replno: props.comments[idx].no,
          bno: props.comments[idx].bno,
        },
      })
        .then((res) => {
          console.log(res);
          console.log(idx);
          console.log(props.replyJoins);
          router.go(`/boarddetail/${router.currentRoute._value.params.boardno}`);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    return { state, changeToggle, commentInput, userno, agencycode, replyDelete, commentDelete };
  },
};
</script>
<style>
.bt-white {
  background-color: #ff8b8b;
  border-right: #ff8b8b 1px solid;
  border-left: #ff8b8b 1px solid;
  border-top: #ff8b8b 1px solid;
  border-bottom: #ff8b8b 1px solid;
  border-radius: 12px;
  color: #ffffff;
}
.bt-reply {
  background-color: #789ade;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  border-radius: 12px;
  color: #ffffff;
}
</style>
