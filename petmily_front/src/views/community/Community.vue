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
            type="checkbox"
            value="true"
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
  <div class="container">
    <CommunityPagenation/>
  </div>
</template>
<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// import axios from 'axios'
import CommunityList from '@/views/community/components/CommunityList.vue'
import CommunityPagenation from '@/views/community/components/CommunityPagenation.vue'
export default {
  name: 'Community',
  components: {
    CommunityList,
    CommunityPagenation
  },
  setup () {
    let search = ref("")
    const searchBoard = function () {
      }
    const router = useRouter()
    const isLogin  = sessionStorage.getItem('isLogin')
    const goToRegisterBoard = () => { router.push('/communityregister') }

    return { search, searchBoard, isLogin, goToRegisterBoard }
  }
}
</script>
<style>
  
</style>