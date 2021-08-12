<template>
  <div>
    <div >
      <!-- prev -->
      <div v-if="pageloc > 1">
        <button @click="minusNum">prev</button>
      </div>
      <!-- 버튼 부분 -->
      <div>
        
      </div>
      <!-- next -->
      <div v-if="pageloc < pageMax">
        <button @click="minusNum">next</button>
      </div>
    </div>
  </div>
</template>
<script>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
export default {
  name: 'CommunityPagenation',
  setup () {
    let pageloc = ref(1)
    let pageMax = ref(1)
    let len = ref(null)

    const boardList = reactive({
      boards: []
    })

    const boardNum = reactive({
      arrayNum: []
    })

    const minusNum = () => { return pageloc -= 1 }
    const plusNum = () => { return pageloc += 1 }

    onMounted(() => {
      axios({
        method: 'get',
        url: 'http://localhost:8080/board/'
      })
      .then(res => {
        console.log(res.data)
        len = res.data.length
        console.log(len)
        pageMax = parseInt(len / 10) + 1
        console.log(pageMax)
        boardList.boards = res.data
      })
      .catch(err => {
        console.log(err)
      })
      movePage()

    })

    
    
    console.log(boardNum.arrayNum)
    const movePage = async function (pageloc) {
        for (let i = pageloc-1; i < pageloc + 9; i++) {
          if (i < len) {
            boardNum.arrayNum.push(i)
          } else {
            return
          }
        }
    }





    return { pageloc, pageMax, minusNum, plusNum, boardNum, movePage }
  }
}
</script>
<style>
  
</style>