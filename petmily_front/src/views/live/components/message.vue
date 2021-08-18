<template>
  <div style="text-align: left;">
    <span id="msgs">{{ msg }}</span>
  </div>
</template>

<script>
export default {
  name: "Message",
  props: {
    msg: {
      type: String,
    },
  },
};
</script>

<style scoped>
#msgs {
  font-size: 15px;
  margin: 2px;
}
</style>
