<template>
  <div>
    <ShelterJumbotron :agencyInfo="agencyInfo" />
    <ShelterLocation :location="location" :agencyInfo="agencyInfo" />
    <h2 class="mb-4">보호소에서 등록한 동물</h2>
    <div class="container" align="center" v-if="agencyAnimals.animals.length > 0">
      <div class="row row-cols-1 row-cols-md-4 g-4" align="center">
        <ShelterAnimals
          v-for="(animals, idx) in agencyAnimals.animals"
          :key="idx + 'b'"
          :animals="animals"
        />
      </div>
    </div>
    <div v-else class="container d-flex justify-content-center align-items-center Jbgc" style="height: 400px">
      <h2> 아직 등록한 동물이 없습니다. </h2>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

import ShelterJumbotron from "@/views/shelter/components/ShelterJumbotron.vue";
import ShelterLocation from "@/views/shelter/components/ShelterLocation.vue";
import ShelterAnimals from "@/views/shelter/components/ShelterAnimals.vue";

export default {
  name: "ShelterDetail",
  components: {
    ShelterJumbotron,
    ShelterLocation,
    ShelterAnimals,
  },
  setup() {
    const router = useRouter();
    const agencyInfo = reactive({
      // 보호소 데이터
      agencyname: "",
      address: "",
      starttime: "",
      endtime: "",
      closeday: "",
      tel: "",
      animals: [],
      agencycode: "",

      // 담당자 데이터
      email: "",
      img: "",
      phone: "",
      username: "",
    });

    const location = reactive({
      lat: null,
      lng: null,
    });

    const agencyAnimals = reactive({
      animals: [],
    });

    const getAgencyInfo = async function() {
      await axios({
        method: "get",
        url: "http://localhost:8080/shelter/detail/" + router.currentRoute._value.params.agencycode,
      })
        .then((res) => {
          if (res.data) {
            agencyInfo.agencyname = res.data.agencyname;
            agencyInfo.address = res.data.addr;
            agencyInfo.starttime = res.data.week_open_time;
            agencyInfo.endtime = res.data.week_end_time;
            agencyInfo.closeday = res.data.closeday;
            agencyInfo.tel = res.data.tel;
            location.lat = res.data.lat;
            location.lng = res.data.lng;
            agencyAnimals.animals = res.data.animals;
            agencyInfo.email = res.data.agencyJoin.email;
            agencyInfo.img = res.data.agencyJoin.img;
            agencyInfo.phone = res.data.agencyJoin.phone;
            agencyInfo.username = res.data.agencyJoin.username;
            agencyInfo.agencycode = res.data.agencycode;
            console.log(agencyAnimals.animals);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };

    getAgencyInfo();
    return { agencyInfo, location, agencyAnimals };
  },
};
</script>
<style></style>
