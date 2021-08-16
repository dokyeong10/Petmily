<template>
  <div class="container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg" style="color:black" />
  </div>
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
  height: 75vh;
  width: 100%;
  background: rgba(207, 190, 207, 0.61);
  overflow: scroll;
}
</style>
