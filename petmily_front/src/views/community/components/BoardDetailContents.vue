<template>
  <div class="container">
    <div v-if="boardInfo">
      <div class="d-flex justify-content-start pb-3">
        <h4> 제목: {{ boardInfo.title }} </h4>
      </div>
      <div>
        <pre style="overflow:auto; white-space: pre-wrap;" align="left"  class="d-flex justify-content-start"> {{ boardInfo.contents }} </pre>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'BoardDetailContents',
  props: {
    boardInfo: Object
  },
  setup () {
    
  }
}
</script>
<style>
  
</style>