<template>
  <form @keypress="if (event.keyCode == 13) submitForm;" label-width="100px">
    <div class="chatbox d-flex-lg-row">
      <span>
        <input
          type="textarea"
          placeholder="채팅을 입력하세요."
          class="chat"
          v-model="messageForm.message"
        />
      </span>
      <span>
        <button class="my-btn btn-up mt-1" type="primary" @click="submitForm">
          입력
        </button></span
      >
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      messageForm: {
        message: "",
      },
    };
  },

  props: {
    userName: String,
  },

  methods: {
    submitForm(event) {
      const msg = this.messageForm.message.trim();
      if (msg != "") {
        event.preventDefault();
        this.$emit("sendMsg", "[" + this.userName + "] : " + msg);
      }
      this.messageForm.message = "";
    },
  },
};
</script>

<style scoped>
.my-btn {
  height: 40px;
  width: 90%;
}
.chat {
  width: 90%;
  height: 50px;
}
/* .chatbox {
  width: 100%;
} */
</style>
