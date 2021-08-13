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
            <tr v-if="no < state.numberOfItems * state.page && no >= state.numberOfItems * (state.page - 1)" @click="goToBoardDetail(board.no)">
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
          <a style="font-weight: 600;" >{{ n }}</a>
        </div>
        <div v-else>
          <a id="myA" @click="goToPage(n)">{{ n }}</a>
        </div>
      </div>
    </div>
    <a id="myA" v-if="state.data.length - state.numberOfItems * state.page > 0" @click="pageUp"
      >Next</a>
  </div>
</template>
<script>
import { reactive, onMounted, computed } from "vue";
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import axios from "axios";
export default {
  name: "CommunityPagenation",
  setup() {
    const router = useRouter()
    const store = useStore ()
    const state = reactive({
      data: [],
      numberOfItems: 4,
      page: 1,
      numberOfPages: 0,
    });

    onMounted(() => {
      axios({
        method: "get",
        url: "http://localhost:8080/board/",
      })
        .then((res) => {
          console.log(res.data);
          state.data = res.data;
          state.numberOfItems = 10;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }

          state.data.sort(function(a, b) {
            return new Date(b.reg_date) - new Date(a.reg_date)
          }) 

        })
        .catch((err) => {
          console.log(err);
        });
    });

    const pageUp = function() {
      state.page++;
    };
    const pageDown = function() {
      state.page--;
    };
    const goToPage = function(n) {
      state.page = n;
    };

    const isLogin = computed(() => {
      return sessionStorage.getItem('isLogin');
    });

    const goToBoardDetail = function(no) {
      if(sessionStorage.getItem('isLogin')) {
          router.push({
            name: 'boarddetail',
            params: { boardno: no }
          })
        } else {
          alert("로그인이 필요한 서비스입니다.")
        }
      }

    return { state, pageUp, pageDown, goToPage, isLogin, goToBoardDetail };
  },
};
</script>
<style scoped>
#myA {
  cursor: pointer;
}

tbody:hover {
  border: 2px solid #789ade;
  cursor: pointer;
}
</style>
