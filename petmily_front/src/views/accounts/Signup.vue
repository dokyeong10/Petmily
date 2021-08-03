<template>
  <div class="container">
    <JumbotonTerms/>
    <!-- 약관 1 -->
    <div class="d-flex justify-content-center">
      <div style="overflow:auto; width:600px; height:150px; word-break:break-all;">
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
    <div>
      <label><input type="checkbox" name="color" value="동의함">동의함</label>
      <label><input type="checkbox" name="color" value="동의하지 않음">동의하지 않음</label>
    </div>
    <!-- 약관 2 -->
    <div class="d-flex justify-content-center">
      <div style="overflow:auto; width:600px; height:150px; word-break:break-all;">
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
      <label><input type="checkbox" name="color" value="동의함">동의함</label>
      <label><input type="checkbox" name="color" value="동의하지 않음">동의하지 않음</label>
    </div>
    <div class="d-flex justify-content-evenly">
      <button class="btn-white" style="color: #FFFFFF">개인 회원가입</button>
      <button class="btn-white" style="color: #FFFFFF">기관 회원가입</button>
    </div>
  </div>
</template>
<script>
import JumbotonTerms from "@/views/accounts/components/JumbotonTerms"

export default {
  name: 'Signup',
  components: {
    JumbotonTerms,
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