<template>
  <div>
    <div class="Jbgc mx-auto" style="height: 600px">
      <div>
        <img
          v-if="agencyInfo.img"
          :src="agencyInfo.img"
          class="mt-4 mb-5"
          style="height: 200px; width: 200px; border-radius: 40%"
        />
        <img
          v-else
          src="https://image.flaticon.com/icons/png/512/2817/2817900.png"
          class="mt-4 mb-5"
          style="height: 200px; width: 200px; border-radius: 40%"
        />
      </div>
      <div class="d-flex justify-content-evenly">
        <div
          class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center"
          style="margin-left: 150px"
        >
          <div align="left">
            <div class="Info" align="center">담당자 정보</div>
            <p>
              <span class="title-sh"> AGENCY : </span>
              <span v-if="agencyInfo.username" class="content-sh">{{
                agencyInfo.username
              }}</span>
              <span v-else class="content-sh" style="color: #e26161"
                >아직 정보가 없습니다.</span
              >
            </p>
            <p>
              <span class="title-sh">EMAIL : </span>
              <span class="content-sh" v-if="agencyInfo.email">{{
                agencyInfo.email
              }}</span>
              <span v-else class="content-sh" style="color: #e26161"
                >아직 정보가 없습니다.</span
              >
            </p>
            <p>
              <span class="title-sh">PHONE : </span>
              <span class="content-sh" v-if="agencyInfo.phone">{{
                agencyInfo.phone
              }}</span>
              <span v-else class="content-sh" style="color: #e26161"
                >아직 정보가 없습니다.</span
              >
            </p>

            <p>
              <span class="title-sh">DAY OFF : </span>
              <span class="content-sh">{{ agencyInfo.closeday }}</span>
            </p>
          </div>
        </div>
        <div
          class="d-inline position-relative inner30 d-flex justify-content-evenly align-items-center"
        >
          <div align="left">
            <div class="Info" align="center">보호소 정보</div>
            <p>
              <span class="title-sh">AGENCY NAME : </span>
              <span class="content-sh">{{ agencyInfo.agencyname }}</span>
            </p>
            <p>
              <span class="title-sh">ADDRESS : </span>
              <span class="content-sh"> {{ agencyInfo.address }}</span>
            </p>
            <p>
              <span class="title-sh">OPEN : </span>
              <span class="content-sh"> {{ agencyInfo.starttime }}</span>
            </p>
            <p>
              <span class="title-sh">CLOSE : </span>
              <span class="content-sh">{{ agencyInfo.endtime }}</span>
            </p>
            <p>
              <span class="title-sh">TEL : </span>
              <span class="content-sh">{{ agencyInfo.tel }}</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ShelterJumbotron",
  props: {
    agencyInfo: Object,
  },
  setup() {},
};
</script>
<style>
.Info {
  font-weight: bold;
  font-size: 30px;
  margin-top: 3px;
  margin-bottom: 20px;
  color: rgb(34, 22, 6);
  background-color: beige;
  
}
.title-sh {
  font-weight: bold;
  font-size: 20px;
  margin-top: 10px;
}
.content-sh {
  font-size: 22px;
  margin-top: 5px;
}
</style>
