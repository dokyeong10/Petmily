<template>
<div class="d-flex jusify-content-start mx-auto" style="width: 800px">
    <h2>
      내 프로필
    </h2>
  </div>
  <div>
    <div class="Jbgc mx-auto" style="height: 400px; width: 800px;">
      <div class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center">     
        <div>
          <img :src="agencyInfo.img" style="height: 200px; width: 200px;">
        </div>
        <div align="left">
          <p>AGENCY   {{ agencyInfo.agencyname }}</p>
          <p>EMAIL    {{ agencyInfo.email }}</p>
          <p>PHONE    {{ agencyInfo.phone }}</p>
          <p>AGENCY NUMBER {{ agencyInfo.agencycode }}</p>
          <p>NAME  {{ agencyInfo.username }}</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'AgencyProfileForm',
  props: {
    agencyInfo: Object
  },
  setup () {}
}
</script>
<style>
  
</style>