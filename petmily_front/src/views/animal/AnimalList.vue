<template>
  <div>
    <div class="Jbgc" style="height: 350px;">
      <div class="d-flex flex-column" style="width: 1200px;">
        <div class="d-flex flex-column align-items-start">
          <h3>유기동물 목록</h3>
          <h5>당신의 따뜻한 품을 기다리고 있습니다.</h5>
        </div>
        <div class="d-flex align-items-start">
          <div class="d-flex flex-column align-items-start">
            <label for="filter">필터</label>
            <select name="filter" id="filter" v-model="state.key" style="height: 30px;">
              <option value="" selected disabled>선택</option>
              <option value="all">전체목록</option>
              <option value="type">품종</option>
              <option value="addr">지역</option>
              <option value="agencyname">보호소</option>
            </select>
          </div>
            <div name="search">
              <div v-if="state.key != 'all'" class="d-flex flex-column align-items-start" >
                <label for="search">검색</label>
                <input type="text" v-model="state.word" placeholder="검색어를 입력하세요."/>
              </div>
            </div>
            <button class="btn-up" @click="search" style="height: 30px; margin-top: 24px;">검색</button>
        </div>
        <div class="d-flex align-items-end flex-column mb-3">
          <div v-if="isAgency">
            <button @click="goToAnimalRegister" class="mb-auto p-2" type="button">동물 등록 하기</button>
          </div>
        </div>
      </div>
    </div>
    <br>

  </div>
</template>
<script>


import { reactive } from "vue";
import { useRouter } from 'vue-router' 
import { computed } from '@vue/runtime-core'
import axios from "axios";

export default {
  name: 'animallist',
  setup () {
    const router = useRouter()
    const state = reactive({
      key: "",
      word: "",
    })
    const goToAnimalRegister = function () {
      router.push('/animalregister')
    }

    const isAgency = computed(() => {
      console.log(sessionStorage.getItem('isAgency'))
      return sessionStorage.getItem('isAgency')
    })
    
    const search = function () {
      axios({
        method: "post",
        url: "http://localhost:8080/animal/",
        data: {
          key: state.key,
          word: state.word,
        }
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    }
    return { state, goToAnimalRegister, isAgency, search, }
  }
}
</script>
<style>
  .Jbgc {
  background-color: #FAF9FE;
  }
</style>