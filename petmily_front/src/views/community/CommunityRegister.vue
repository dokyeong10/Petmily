<template>
  <div>
    <CommunityRegisterJumbotron/>
    <CommunityRegisterForm/>
  </div>
</template>
<script>
import CommunityRegisterJumbotron from '@/views/community/components/CommunityRegisterJumbotron.vue'
import CommunityRegisterForm from '@/views/community/components/CommunityRegisterForm.vue'
export default {
  name: 'CommunityRegister',
  components: {
    CommunityRegisterJumbotron,
    CommunityRegisterForm
  },
  setup () {}
}
</script>
<style>
  
</style>