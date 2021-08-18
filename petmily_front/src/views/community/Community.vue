<template>
  <div class="Jbgc" style="height: 220px">
    <div class="mx-auto pt-5" style="width: 800px">
      <div class="d-flex justify-content-start">
        <h2>커뮤니티</h2>
      </div>
    </div>
    <div class="mx-auto" style="width: 800px">
      <div class="d-flex justify-content-start">
        <input
          class="mb-1 form-control radius-border rounded-pill"
          type="text"
          v-model="state.word"
          placeholder="키워드를 입력하세요"
        />
        <button class="mb-1 btn-up" @click="search">검색</button>
      </div>
      <div class="d-flex justify-content-between">
        <div v-if="isLogin" class="mx-2">
          <input v-model="state.writeMe" type="checkbox" @click="findMine" />
          내가 작성한 글 보기
        </div>
      </div>
      <div class="pt-3 d-flex justify-content-end">
        <div v-if="isLogin">
          <button class="mb-1 btn-up" @click="goToRegisterBoard">
            글 작성하기
          </button>
        </div>
      </div>
    </div>
  </div>
  <div v-if="state.data" class="container">
    <CommunityPagination :state="state" @page-up="onPageUp" @page-down="onPageDown" @go-to-page="onGoToPage"/>
  </div>
</template>
<script>
import { reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import CommunityPagination from "@/views/community/components/CommunityPagination.vue";
export default {
  name: "Community",
  components: {
    CommunityPagination,
  },
  setup() {
    const searchBoard = function () {};

    const state = reactive({
      data: [],
      numberOfItems: 10,
      page: 1,
      numberOfPages: 0,
      word: "",
      writeMe: false,
      isUser: getUser(),
      isAgency: getAgency(),
      agencycode: getAgencycode(),
      userno: getUserno(),
    });

    const router = useRouter();
    const isLogin = sessionStorage.getItem("isLogin");

    onMounted(() => {
      axios({
        method: "post",
        url: "http://localhost:8080/board/",
        data: {
          word: state.word,
        },
      })
        .then((res) => {
          state.numberOfItems = 10;
          console.log(res.data);
          state.data = res.data;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
              state.numberOfItems;
          }

          state.data.sort(function (a, b) {
            return new Date(b.reg_date) - new Date(a.reg_date);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    });

    function getAgencycode() {
      return localStorage.getItem("agencycode");
    }

    function getUserno() {
      return Number(localStorage.getItem("userno"));
    }

    function getUser() {
      return localStorage.getItem("isUser");
    }

    function getAgency() {
      return localStorage.getItem("isAgency");
    }

    const getAll = function () {
      axios({
        method: "post",
        url: "http://localhost:8080/board/",
        data: {
          word: state.word,
        },
      })
        .then((res) => {
          state.numberOfItems = 10;
          console.log(res.data);
          state.data = res.data;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
              state.numberOfItems;
          }

          state.data.sort(function (a, b) {
            return new Date(b.reg_date) - new Date(a.reg_date);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    };

    // checkbox 클릭시
    const findMine = function () {
      console.log(state.writeMe);
      if (state.writeMe) {
        getAll();
      } else {
        console.log("================= isUser: " + state.isUser);
        console.log("================= isAgency: " + state.isAgency);
        if (state.isUser == "true") {
          console.log("================== userno: " + state.userno);
          axios({
            method: "post",
            url: "http://localhost:8080/board/",
            data: {
              word: state.word,
              userno: state.userno,
            },
          })
            .then((res) => {
              console.log(res);
              state.numberOfItems = 10;
              state.data = res.data;
              if (state.data.length % state.numberOfItems) {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                    state.numberOfItems +
                  1;
              } else {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                  state.numberOfItems;
              }

              state.data.sort(function (a, b) {
                return new Date(b.reg_date) - new Date(a.reg_date);
              });
            })
            .catch((err) => {
              console.log(err);
            });
        } else if (state.isAgency == "true") {
          console.log("============== agencycode: " + state.agencycode);
          axios({
            method: "post",
            url: "http://localhost:8080/board/",
            data: {
              word: state.word,
              agencycode: state.agencycode,
            },
          })
            .then((res) => {
              console.log(res);
              state.numberOfItems = 10;
              state.data = res.data;
              if (state.data.length % state.numberOfItems) {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                    state.numberOfItems +
                  1;
              } else {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                  state.numberOfItems;
              }

              state.data.sort(function (a, b) {
                return new Date(b.reg_date) - new Date(a.reg_date);
              });
            })
            .catch((err) => {
              console.log(err);
            });
        }
      }
    };

    // 검색 버튼 클릭시
    const search = function () {
      console.log(state.writeMe);
      if (!state.writeMe) {
        getAll();
      } else {
        if (state.isUser == "true") {
          console.log(state.userno);
          axios({
            method: "post",
            url: "http://localhost:8080/board/",
            data: {
              word: state.word,
              userno: state.userno,
            },
          })
            .then((res) => {
              console.log(res);
              state.numberOfItems = 10;
              state.data = res.data;
              if (state.data.length % state.numberOfItems) {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                    state.numberOfItems +
                  1;
              } else {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                  state.numberOfItems;
              }

              state.data.sort(function (a, b) {
                return new Date(b.reg_date) - new Date(a.reg_date);
              });
            })
            .catch((err) => {
              console.log(err);
            });
        } else if (state.isAgency == "true") {
          axios({
            method: "post",
            url: "http://localhost:8080/board/",
            data: {
              word: state.word,
              agencycode: state.agencycode,
            },
          })
            .then((res) => {
              console.log(res);
              state.numberOfItems = 10;
              state.data = res.data;
              if (state.data.length % state.numberOfItems) {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                    state.numberOfItems +
                  1;
              } else {
                state.numberOfPages =
                  (state.data.length -
                    (state.data.length % state.numberOfItems)) /
                  state.numberOfItems;
              }

              state.data.sort(function (a, b) {
                return new Date(b.reg_date) - new Date(a.reg_date);
              });
            })
            .catch((err) => {
              console.log(err);
            });
        }
      }
    };
    const goToRegisterBoard = () => {
      router.push("/communityregister");
    };

    const onPageUp = function () {
      state.page++
    }
    const onPageDown = function () {
      state.page--
    }
    const onGoToPage = function (n) {
      state.page = n
    }

    return {
      state,
      searchBoard,
      isLogin,
      goToRegisterBoard,
      findMine,
      search,
      getUser,
      getAgency,
      getAgencycode,
      getUserno,
      onPageUp,
      onPageDown,
      onGoToPage,

    };
  },
};
</script>
<style>
</style>