<template>
  <div align="center">
    <div class="d-flex justify-content-center">
      <!-- <img width="10px" height="10px" :src="reply.user.img" onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'">
      <div> {{ reply.contents }} </div>
      <div> {{ reply.regdate.substring(0,10) }} </div> -->
    </div>
      <div>
        <button @click="changeToggle"> 답글 달기 </button>
      </div>
    <div v-if="state.toggle">

      <form>
          <p>
            <textarea
              v-model="state.comment"
              style="overflow:auto; white-space: pre-wrap;"
              class="form-control radius-border"
              placeholder="내용을 입력하세요."
            >
            </textarea>
          </p>
        </form>
    </div>
  </div>


</template>
<script>
import { reactive } from 'vue'

export default {
  name: "BoardDetailCommentList",
  props: {
    replyJoins: {
      type: Array
    }
  },
  setup (props) {
    const reply = reactive({
      data: []
    })
    // console.log(props)  
    // // onMounted(() => {
    // // })
    
    // const getRelay = async function() {
    //   props.replys.forEach(element => {
    //     console.log(element)
    //     if (element.user === null) {
    //       reply.data.push(element.agency)
    //     } else {
    //       reply.data.push(element.user)
    //     }
    //   })
    // }
    // getRelay()
    // console.log(reply)

    const state = reactive({
      comment: "",
      toggle: false
    })

    const changeToggle = () => {
      if (!state.toggle) { state.toggle = true }
      else { state.toggle = false }
    }
    return { reply, state, changeToggle }
  }
}
</script>
<style>
  
</style>