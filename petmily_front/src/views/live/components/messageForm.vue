<template>
  <form @keypress="if (event.keyCode == 13) submitForm;">
    <div class="chatbox">
      <span class="d-flex">
        <input
          type="textarea"
          placeholder="채팅을 입력하세요."
          class="chat"
          v-model="messageForm.message"
        />
        <button class="my-btn btn-up" type="primary" @click="submitForm">
          입력
        </button>
      </span>
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
}
.chat {
  font-size: 19px;
}
/* .chatbox {
  width: 100%;
} */
</style>
