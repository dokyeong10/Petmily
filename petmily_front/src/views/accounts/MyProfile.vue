<template>
  <div>
    <div>
      <MyProfileMyPage/>
    </div>
    <div class="container">
      <MyProfileForm/>
    </div>
    <div>
      <MyProfileFavoriteAnimal/>
    </div>
  </div>
</template>
<script>
import MyProfileFavoriteAnimal from '@/views/accounts/components/MyProfileFavoriteAnimal'
import MyProfileForm from '@/views/accounts/components/MyProfileForm'
import MyProfileMyPage from '@/views/accounts/components/MyProfileMyPage'

export default {
  name: 'myprofile',
  components: {
    MyProfileMyPage,
    MyProfileForm,
    MyProfileFavoriteAnimal,
  }
}
</script>
<style>
  
</style>