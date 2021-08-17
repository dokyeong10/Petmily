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
          v-model="state.search"
          placeholder="키워드를 입력하세요">
        <button
          class="mb-1 btn-up"
          @click="findWriteMe(state.writeMe)"
          @keyup="findWriteMe(state.writeMe)"
        >검색
        </button>
      </div>
      <div class="d-flex justify-content-between">
        <div v-if="isLogin" class="mx-2">
          <input
            v-model="state.writeMe"
            type="checkbox"
            @click="findWriteMe(state.writeMe)"
          /> 내가 작성한 글 보기
        </div>
      </div>
      <div class="pt-3 d-flex justify-content-end">
        <div v-if="isLogin" >
          <button class="mb-1 btn-up" @click="goToRegisterBoard">글 작성하기</button>
        </div>
      </div>
    </div>
  </div>
  <div v-if="state.data" class="container">
    <CommunityPagination
      :state="state"
    />
  </div>
</template>
<script>
import { reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import CommunityList from '@/views/community/components/CommunityList.vue'
import CommunityPagination from '@/views/community/components/CommunityPagination.vue'
export default {
  name: 'Community',
  components: {
    CommunityList,
    CommunityPagination
  },
  setup () {
    const searchBoard = function () {
      }

    const state = reactive({
      data: [],
      numberOfItems: 4,
      page: 1,
      numberOfPages: 0,
      search: "",
      writeMe: false
    });

    const router = useRouter()
    const isLogin  = sessionStorage.getItem('isLogin')
    const isUser = Boolean(localStorage.getItem('isUser'))
    const isAgency = Boolean(localStorage.getItem('isAgency'))
    const userno = Number(localStorage.getItem('userno'))
    const agencycode = localStorage.getItem('agencycode')
    
    console.log(userno)
    console.log(agencycode)

    onMounted(() => {
      axios({
        method: "post",
        url: "http://localhost:8080/board/",
        data: {
          word: ""
        }
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
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }

          state.data.sort(function(a, b) {
            return new Date(b.reg_date) - new Date(a.reg_date);
          });
        })
        .catch((err) => {
          console.log(err);
        });
    });

    const findWriteMe = function (writeMe) {
      console.log(writeMe)
      console.log(state.search)
       if ((!writeMe) && isUser) {
         axios({
           method: 'post',
           url: 'http://localhost:8080/board/',
           data: {
             word: state.search,
             userno: userno
           }
         })
         .then(res => {
          console.log(res)
          state.numberOfItems = 10;
          state.data = res.data;
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
            return new Date(b.reg_date) - new Date(a.reg_date);
          });
         })
         .catch(err => {
           console.log(err)
         })
       } else if ((!writeMe) && isAgency) {
         axios({
           method: 'post',
           url: 'http://localhost:8080/board/',
           data: {
             word: state.search,
             agencycode: agencycode
           }
         })
         .then(res => {
            console.log(res)
            state.numberOfItems = 10;
            state.data = res.data;
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
              return new Date(b.reg_date) - new Date(a.reg_date);
            });
         })
         .catch(err => {
           console.log(err)
         })
       } else {
        axios({
          method: "post",
          url: "http://localhost:8080/board/",
          data: {
          word: state.search
          }
        })
        .then((res) => {
          state.numberOfItems = 10;
          state.data = res.data;
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
            return new Date(b.reg_date) - new Date(a.reg_date);
          });
        })
        .catch((err) => {
          console.log(err);
        });
       }
      }
    const goToRegisterBoard = () => { router.push('/communityregister') }

    return { state, searchBoard, isLogin, goToRegisterBoard, findWriteMe }
    }
  }
</script>
<style>
  
</style>