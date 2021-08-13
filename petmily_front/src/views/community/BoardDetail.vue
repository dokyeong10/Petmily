<template>
  <div>
    <BoardDetailJumbotron/>
    <div v-if="userInfo.user != null">
      <BoardDetailUser
        :userInfo="userInfo.user"
        :boardFiles="boardFiles"
        :boardInfo="boardInfo"
      />
    </div>
      <div v-else>
      <BoardDetailUser
        :userInfo="userInfo.agency"
        :boardFiles="boardFiles"
        :boardInfo="boardInfo"
      />
    </div>
    <div>
      <BoardDetailReview
        :replyJoins="replyJoins"
      />
    </div>
  </div>
</template>
<script>
import { useRouter } from 'vue-router'
import { onMounted, reactive } from 'vue'
import axois from 'axios'

// components
import BoardDetailJumbotron from '@/views/community/components/BoardDetailJumbotron.vue'
import BoardDetailUser from '@/views/community/components/BoardDetailUser.vue'
import BoardDetailReview from '@/views/community/components/BoardDetailReview.vue'

export default {
  name: 'BoardDetail',
  components: {
    BoardDetailJumbotron,
    BoardDetailUser,
    BoardDetailReview
  },
  setup () {
    const router = useRouter()
    const boardInfo = reactive({
      title: "",
      contents: "",
      reg_date: "",
    })
    
    const userInfo = reactive({
      user: null,
      agency: null
    })
    
    const boardFiles = reactive({
      img: [],
      video: []
    })
    
    const replyJoins = reactive({
      data: null,
      no: null
    })
      
    onMounted(() => {
      axois({
        method: 'get',
        url: 'http://localhost:8080/board/details/' + router.currentRoute._value.params.boardno
      })
      .then(res => {
        console.log(res)
        boardInfo.title = res.data.title
        boardInfo.contents = res.data.contents
        boardInfo.reg_date = res.data.reg_date.substring(0, 10)
        userInfo.user = res.data.user
        userInfo.agency = res.data.agency
        replyJoins.data = res.data.replyJoins
        replyJoins.no = res.data.no
        boardFiles.data = res.data.boardFiles
        console.log(res.data.boardFiles[0].extension === 'png')
        res.data.boardFiles.forEach(element => {
          if ( element.extension === 'png' || element.extension === 'gif' || element.extension === 'jpg' || element.extension === 'jpeg' || element.extension === 'bmp') {
            boardFiles.img.push(element) 
            } else {
            boardFiles.video.push(element) 
            }
          } 
        );
        console.log(boardFiles)

        // console.log(userInfo.user)
        // console.log(userInfo.agency)
        // console.log(boardFiles.data)
        // console.log(replyJoins.data)
      })
      .catch(err => {
        console.log(err)
      })
      
    })
    return { boardInfo, userInfo, boardFiles, replyJoins }
  }
}
</script>
<style>
  
</style>