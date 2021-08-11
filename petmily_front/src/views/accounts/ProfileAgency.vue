<template>
  <div>
<<<<<<< HEAD
    
=======
    <div>
      <MyProfileMyPage />
    </div>
    <div>
      <AgencyProfileForm
       :agencyInfo="agencyInfo"
      />
    </div>
    <div class="Jbgc mx-auto d-flex justify-content-end" style="width: 800px;">
      <button @click="goToConfirmPassword" class="btn-l m-3 btn-white">정보 수정 하기</button>
    </div>
    <br />
    <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
      <h2>등록한 동물</h2>
    </div>
      <div class="mx-auto" style="height: 400px; width: 800px;">
        <div class="container">
          <div class="row row-cols-1 row-cols-md-2 g-4">
            <AgencyRegisterAnimal
            v-for="(animal, idx) in regAnimal.animal"
            :key="idx"
            :animal="animal"
            />
          </div>
      </div>
    </div>
>>>>>>> origin/emergency_backup_push_branch
  </div>
</template>
<script>
export default {
  name: 'ProfileAgency'
}
</script>
<style>
  
</style>