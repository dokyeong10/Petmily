<template>
  <div class="container">
    <div class="align-content-center">
      <input class="form-control radius-border" type="text" placeholder="사진 혹은 동영상을 넣어주세요.">
      <input v-model="state.type" class="form-control radius-border" type="text" placeholder="ex) 강아지, 고양이">
      <input v-model="state.species" class="form-control radius-border" type="text" placeholder="ex) 말티즈 시바">
      <input v-model="state.age" class="form-control radius-border" type="text" placeholder="ex) 3살 추정">
      <div class="d-flex justify-content-evenly">
        <div>
          성별:
          <span>
            <label><input type="radio" name="sex" v-model="state.sexToggle" checked="checked" value="false">수컷</label>
            <label><input type="radio" name="sex" v-model="state.sexToggle" value="true">암컷</label>
          </span>
        </div>
        <div>
          중성화 유무:
          <span>
            <label><input type="radio" name="neutered" v-model="state.neuteredToggle" value="true">Yes</label>
            <label><input type="radio" name="neutered" v-model="state.neuteredToggle" checked="checked" value="false">No</label>
          </span>
        </div>
      </div>
      <input v-model="state.addr" class="form-control radius-border" type="text" placeholder="발견 주소">
      <input v-model="state.agencycode" class="form-control radius-border" type="text" placeholder="특이사항">
      <!-- 임시로 특이사항에 agencycode 받도록 해놓음 -->
      <input v-model="state.find_date" class="form-control radius-border" type="datetime-local" placeholder="발견날짜">
      <button class="btn-white" type="button" @click="confirmAnimalRegister">등록하기</button>
    </div>
  </div>
</template>
<script>
import {reactive} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'


// 기관번호 자동으로 받아오는 방법
// postman에서 get으로 되어있는 주소로 jwt 토큰 값을 보내면 유저의 객체가 온다
// 그 객체 안에 기관번호가 담겨있다.
export default {
  name: 'AnimalRegister',
  setup () {
    const state = reactive({
      type: '',
      species: '',
      addr: '',
      age: '',
      sexToggle: '',
      neuteredToggle: '',
      find_date: '',
      userno: '',
      agencycode: '',
    })
    const router = useRouter()
    const confirmAnimalRegister = function () {
      axios({
        method: 'post',
        url: 'http://localhost:8080/animal/register',
        data: {
          type: state.type,
          species: state.species,
          addr: state.addr,
          age: state.age,
          sex: state.sexToggle,
          neutered: state.neuteredToggle,
          find_date: state.find_date,
          userno: state.userno,
          agencycode: state.agencycode
        }
      })
      .then( res => {
        console.log(res.data)
        router.push('/animalregistersuccess')
      })
      .catch(err => {
        console.log(err)
      })
    }
    return { state, confirmAnimalRegister }
  }
}
</script>
<style>
.br {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
}

.btn-white {
  width: 200px;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.radius-border {
  border-radius: 12px;
  border-right: #789ADE 1px solid;
  border-left: #789ADE 1px solid;
  border-top: #789ADE 1px solid;
  border-bottom: #789ADE 1px solid;
  width: 400px;
  color: #789ADE;
}
</style>