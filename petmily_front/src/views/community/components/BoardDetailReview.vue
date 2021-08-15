<template>
  <div class="container">
    <label class="mx-auto d-flex flex-row mb-2 semibold" style="width: 600px">댓글</label>
    <div class="d-flex justify-content-center">
      <div aling="center">
        <form class="pb-3">
          <p>
            <textarea
              v-model="state.contents"
              class="form-control r-border"
              placeholder="댓글을 입력하세요."
            >
            </textarea>
          </p>
        </form>
        <div class="d-flex justify-content-end">
          
        <button
            class="btn-white pe-3"
            @click="registerReview"
            style="color: #FFFFFF;"
          >
              댓글 등록하기
        </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from 'vue'
import axios from 'axios'
export default {
  name: 'BoardDetailReview',
  props: {
    replyJoins: Object
  },
  setup (props) {
    const state = reactive({
      contents: ""
    })
    
    const registerReview = function () {
      console.log(localStorage.getItem("isUser"))
      if (localStorage.getItem("isUser") === 'true') {
        // console.log('여기')
        axios({
          method: 'post',
          url: 'http://localhost:8080/board/reply/register',
          data: {
            contents: state.contents,
            bno: props.replyJoins.no,
            userno: localStorage.getItem("userno")
          }
        })
        .then(res => {
          console.log(res)
          router.go()
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        axios({
          method: 'post',
          url: 'http://localhost:8080/board/reply/register',
          data: {
            contents: state.contents,
            bno: props.replyJoins.no,
            agencycode: localStorage.getItem("agencycode")
          }
        })
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
      }
    }

    return { state, registerReview }

  }
}
</script>
<style>
.r-border {
  width: 600px;
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  color: #789ade;
}
</style>