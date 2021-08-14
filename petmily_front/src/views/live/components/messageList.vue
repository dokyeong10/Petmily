<template>
  <el-scrollbar class="container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg" />
  </el-scrollbar>
  <!-- <div class="container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg" />
  </div> -->
</template>

<script>
import Message from "./message";
export default {
  name: "MessageList",
  components: {
    Message,
  },
  props: {
    msgs: {
      type: Array,
    },
  },
};
</script>

<style scoped>
.container {
  height: 500px;
  width: 500px;
  background: rgba(255, 209, 2, 0.61);
}
</style>
