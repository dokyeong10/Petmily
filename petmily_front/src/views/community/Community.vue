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
          v-model="search"
          placeholder="키워드를 입력하세요">
        <button
          class="mb-1 btn-up"
          @click="searchBoard"
          @keyup="searchBoard"
        >검색
        </button>
      </div>
      <div class="d-flex justify-content-between">
        <div v-if="isLogin" class="mx-2">
          <input
            v-model="writeMe"
            type="checkbox"
            @click="findWriteMe(writeMe)"
          /> 내가 작성한 글 보기 {{ writeMe }}
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
    <CommunityPagenation
      :state="state"
    />
  </div>
</template>
<script>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import CommunityList from '@/views/community/components/CommunityList.vue'
import CommunityPagenation from '@/views/community/components/CommunityPagenation.vue'
export default {
  name: 'Community',
  components: {
    CommunityList,
    CommunityPagenation
  },
  setup () {
    let writeMe = ref(false)
    let search = ref("")
    const searchBoard = function () {
      }

    const state = reactive({
      data: [],
      numberOfItems: 4,
      page: 1,
      numberOfPages: 0,
    });

    const router = useRouter()
    const isLogin  = sessionStorage.getItem('isLogin')
    const isUser = localStorage.getItem('isUser')
    const isAgency = localStorage.getItem('isAgency')
    const userno = Number(localStorage.getItem('userno'))
    const agencycode = localStorage.getItem('agencycode')
    
    console.log(userno)
    console.log(agencycode)

    onMounted(() => {
      axios({
        method: "get",
        url: "http://localhost:8080/board/",
      })
        .then((res) => {
          state.numberOfItems = 10;
          // console.log(res.data);
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
      if (!writeMe) {
        axios({
          method: "get",
          url: "http://localhost:8080/board/",
        })
          .then((res) => {
            state.data = []
            console.log(res.data)
            state.numberOfItems = 10;
            if (isUser === 'true') {
              res.data.forEach(element => {
                if (element.user.no === userno) {
                  state.data.push(element)
                }
                console.log(state.data)         
              });
            } else if (isAgency === 'true') {
               res.data.forEach(element => {
                if (element.agency.agencycode === agencycode) {
                  state.data.push(element)
                }
                console.log(state.data)       
              });
            } 
  
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
      } else {
        axios({
        method: "get",
        url: "http://localhost:8080/board/",
        })
        .then((res) => {
          state.data = []
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
      }
      }
    const goToRegisterBoard = () => { router.push('/communityregister') }

    return { state, search, searchBoard, isLogin, goToRegisterBoard, writeMe, findWriteMe }
    }
  }
</script>
<style>
  
</style>