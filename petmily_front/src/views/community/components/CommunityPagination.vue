<template>
  <div class="container">
    <div class="row">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody v-for="(board, no) in state.data" :key="no">
          <tr
            v-if="
              no < state.numberOfItems * state.page && no >= state.numberOfItems * (state.page - 1)
            "
            @click="goToBoardDetail(board.no)"
          >
            <td>{{ board.title }}</td>
            <td v-if="board.agency != null">{{ board.agency.username }}</td>
            <td v-else>{{ board.user.username }}</td>
            <td>{{ board.reg_date.substring(0, 10) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="d-flex justify-content-center">
    <a id="myA" v-if="state.page > 1" @click="pageDown">Prev</a>
    <div v-for="(n, idx) in state.numberOfPages" :key="idx">
      <div v-if="n <= state.page - (state.page % 10) + 10 && n > state.page - (state.page % 10)">
        <div v-if="state.page == n">
          <a style="font-weight: 600;">{{ n }}</a>
        </div>
        <div v-else>
          <a id="myA" @click="goToPage(n)">{{ n }}</a>
        </div>
      </div>
    </div>
    <a id="myA" v-if="state.data.length - state.numberOfItems * state.page > 0" @click="pageUp"
      >Next</a
    >
  </div>
</template>
<script>
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";
export default {
  name: "CommunityPagination",
  props: {
    state: {
      type: Object,
    },
  },
  setup(props, { emit }) {
    const router = useRouter();

    const pageUp = function() {
      emit('page-up')
    };
    const pageDown = function() {
      emit('page-down')
    };
    const goToPage = function(n) {
      emit('go-to-page', n)
    };

    const isLogin = computed(() => {
      return sessionStorage.getItem("isLogin");
    });

    const goToBoardDetail = function(no) {
      if (sessionStorage.getItem("isLogin")) {
        router.push({
          name: "boarddetail",
          params: { boardno: no },
        });
      } else {
        alert("로그인이 필요한 서비스입니다.");
      }
    };

    return { pageUp, pageDown, goToPage, isLogin, goToBoardDetail };
  },
};
</script>
<style scoped>
#myA {
  cursor: pointer;
}

tbody:hover {
  background-color: #a4b5f0;
  cursor: pointer;
  color: #FFFFFF
}
</style>
