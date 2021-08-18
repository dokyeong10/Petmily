<template>
  <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
    <div class="mt-5 font-bold" style="font-size: 30px">내 프로필</div>
  </div>
  <div>
    <div class="Jbgc mx-auto" style="height: 400px; width: 800px">
      <div
        class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center"
      >
        <div>
          <img
            v-if="agencyInfo.img"
            :src="agencyInfo.img"
            style="height: 230px; width: 230px border-radius: 12px;"
            class="profile-Img"
          />
          <img
            v-else
            src="https://image.flaticon.com/icons/png/512/1299/1299865.png"
            style="height: 230px; width: 230px border-radius: 12px;"
            class="profile-Img"
          />
        </div>
        <div align="left">
          <p>
            <span class="profile-title"> AGENCY : </span>
            <span class="profile-content"> {{ agencyInfo.agencyname }}</span>
          </p>
          <p>
            <span class="profile-title">EMAIL : </span>
            <span class="profile-content">{{ agencyInfo.email }} </span>
          </p>
          <p>
            <span class="profile-title">PHONE : </span>
            <span class="profile-content">{{ agencyInfo.phone }}</span>
          </p>
          <p>
            <span class="profile-title">AGENCY NUMBER : </span>
            <span class="profile-content">{{ agencyInfo.agencycode }}</span>
          </p>
          <p>
            <span class="profile-title">NAME : </span>
            <span class="profile-content">{{ agencyInfo.username }}</span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "AgencyProfileForm",
  props: {
    agencyInfo: Object,
  },
  setup() {},
};
</script>
<style>
.profile-title {
  font-weight: bold;
  font-size: 20px;
}
.profile-content {
  font-size: 18px;
}
.profile-Img {
  border-radius: 20px;
}
</style>
