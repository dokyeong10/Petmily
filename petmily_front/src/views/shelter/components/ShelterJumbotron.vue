<template>
  <div>
    <div class="Jbgc mx-auto" style="height: 500px;">
      <div>
        <img :src="agencyInfo.img" class="m-4" style="height: 200px; width: 200px;">
      </div>
      <div class="d-flex justify-content-evenly">
        <div class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center">     
          <div align="left">
            <h3>담당자 정보</h3>
            <p>AGENCY:   {{ agencyInfo.username }}</p>
            <p>EMAIL:    {{ agencyInfo.email }}</p>
            <p>PHONE:    {{ agencyInfo.phone }}</p>
            <p>AGENCY NUMBER: {{ agencyInfo.agencycode }}</p>
            <p>DAY OFF: {{ agencyInfo.closeday }}</p>
          </div>
        </div>
        <div class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center">     
          <div align="left" >
            <h3>보호소 정보</h3>
            <p>AGENCY NAME:   {{ agencyInfo.agencyname }}</p>
            <p>ADDRESS:    {{ agencyInfo.address }}</p>
            <p>OPEN:    {{ agencyInfo.starttime }}</p>
            <p>CLOSE:    {{ agencyInfo.endtime }}</p>
            <p>TEL:  {{ agencyInfo.tel }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'ShelterJumbotron',
  props: {
    agencyInfo: Object
  },
  setup () {}
}
</script>
<style>
  
</style>