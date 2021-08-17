<template>
  <div>
    <div>
      <MyProfileMyPage />
    </div>
    <div>
      <AgencyProfileForm :agencyInfo="agencyInfo" />
    </div>
    <div class="Jbgc mx-auto d-flex justify-content-end" style="width: 800px;">
      <button @click="goToConfirmPassword" class="btn-l m-3 btn-white">정보 수정 하기</button>
    </div>
    <br />
    <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
      <div class="mt-4 mb-4 font-bold" style="font-size:30px">
        등록한 동물
      </div>
    </div>

    <div class="mx-auto Jbgc mb-5" style="width: 800px;">
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
    <br>
  </div>
</template>
<script>
import axios from "axios";
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import MyProfileMyPage from "@/views/accounts/components/MyProfileMyPage";
import AgencyRegisterAnimal from "@/views/accounts/components/AgencyRegisterAnimal";
import AgencyProfileForm from "@/views/accounts/components/AgencyProfileForm";

export default {
  name: "ProfileAgency",
  components: {
    MyProfileMyPage,
    AgencyProfileForm,
    AgencyRegisterAnimal,
  },
  setup() {
    const agencyInfo = reactive({
      email: "",
      phone: "",
      username: "",
      img: "",
      agencyname: "",
      agencycode: "",
      tel: "",
      closeday: "",
    });
    const regAnimal = reactive({
      animal: [],
    });

    const router = useRouter();
    const store = useStore();

    const setToken = function() {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };

    const getAgencyInfo = async function() {
      await axios({
        method: "get",
        url: "https://i5a408.p.ssafy.io:8080/users/agency/mypage",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
          console.log(res);
          agencyInfo.email = res.data.email;
          agencyInfo.agencyname = res.data.agencyname;
          agencyInfo.agencycode = res.data.agencycode;
          agencyInfo.img = res.data.img;
          agencyInfo.phone = res.data.phone;
          agencyInfo.username = res.data.username;
          agencyInfo.tel = res.data.shelterJoin.tel;
          agencyInfo.closeday = res.data.shelterJoin.closeday;
          const len = res.data.shelterJoin.animals.length;
          if (len != 0) {
            for (let i = 0; i < len; i++) {
              regAnimal.animal.push(res.data.shelterJoin.animals[i]);
            }
          }
          console.log(agencyInfo);
          console.log(regAnimal.animal);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const goToConfirmPassword = function() {
      router.push("/confirmpassword");
    };

    console.log(store.email);
    getAgencyInfo();
    return { agencyInfo, regAnimal, goToConfirmPassword };
  },
};
</script>
<style></style>
