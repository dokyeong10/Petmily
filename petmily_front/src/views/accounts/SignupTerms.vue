<template>
  <JumbotronTerms/>
  <div class="container">
    <!-- 약관 1 -->
    <div style="width: 800px; height: 800px;">
      <div>
        <div class="position-absolute start-50 translate-middle-x">
          <div class="d-flex justify-content-start">이용 약관 동의</div>
          <div class="d-flex justify-content-center">
            <div style="overflow:auto; width:800px; height:150px; word-break:break-all;">
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
            </div>
          </div>
          <div class="d-flex justify-content-end">
            <label><input type="checkbox" value="동의함" v-model="firstAgree" @click="agree">동의함 </label>
          </div>
          <!-- 약관 2 -->
          <div class="d-flex justify-content-start">개인정보수집 및 이용동의</div>
          <div class="d-flex justify-content-center">
            <div style="overflow:auto; width:800px; height:150px; word-break:break-all;">
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
              이것은 box안 스크롤 바.
            </div>
          </div>
          <div class="d-flex justify-content-end">
            <input type="checkbox" value="동의함" v-model="secondAgree">
            <label for="checkbox">동의함</label>
          </div>
          <div class="d-flex justify-content-evenly">
            <button class="btn-white" style="color: #FFFFFF" @click="goToPrivateForm">개인 회원가입</button>
            <button class="btn-white" style="color: #FFFFFF" @click="goToAgencyForm">기관 회원가입</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import JumbotronTerms from "@/views/accounts/components/JumbotronTerms"

export default {
  name: 'SignupTerms',
  components: {
    JumbotronTerms,
  },
  setup () {
    const firstAgree = ref(false)
    const secondAgree = ref(false)
    const router = useRouter()


    const goToPrivateForm = function () {
        if (firstAgree.value && secondAgree.value) {
          router.push('/privateform')
        } else {
          alert("약관에 동의해주세요")
        }    
    }


    const goToAgencyForm = function () {
        if (firstAgree.value && secondAgree.value) {
          router.push('/agencyform')
        } else {
          alert("약관에 동의해주세요")
        }    
    }

    return {firstAgree, secondAgree, goToAgencyForm, goToPrivateForm}
    } 
  }
</script>
<style>
.btn-white {
  width: 200px;
  background-color: #A4B5F0;
  border-right: #A4B5F0 1px solid;
  border-left: #A4B5F0 1px solid;
  border-top: #A4B5F0 1px solid;
  border-bottom: #A4B5F0 1px solid;
  border-style: none;
  border-radius: 12px;
}
</style>