<template>
  <br>
  <br>
  <br>
  <div class="container">
    <label class="mx-auto d-flex flex-row mb-2 semibold" style="width: 600px">댓글</label>
    <div class="d-flex justify-content-center">
      <div aling="center">
        <form class="pb-3">
          <p>
            <textarea
              v-model="state.review"
              class="form-control r-border"
              placeholder="댓글을 입력하세요."
            >
            </textarea>
          </p>
        </form>
        <div class="d-flex justify-content-end">
          <form class="">
            <button
                class="btn-white pe-3"
                @click="registerReview"
                @keyup.enter="registerReview"
                style="color: #FFFFFF;"
              >
                  등록하기
              </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from 'vue'
export default {
  name: 'BoardDetailReview',
  props: {
    replyJoins: Object
  },
  setup () {
    const state = reactive({
      review: ""
    })

    const registerReview = function () {

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