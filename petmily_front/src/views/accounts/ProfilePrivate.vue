<template>
  <div>
    <div>
      <MyProfileMyPage />
    </div>
    <br />
    <div class="container">
      <PrivateProfileForm 
        :userInfo="userInfo"
      />
    </div>
    <div class="Jbgc mx-auto d-flex justify-content-end" style="width: 800px;">
      <button @click="goToConfirmPassword" class="btn-l m-3 btn-white">정보 수정 하기</button>
    </div>
    <br />
    <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
      <h2>즐겨찾기 한 동물</h2>
    </div>
    <div class="mx-auto" style="height: 400px; width: 800px;">
      <div class="container">
        <div class="row row-cols-1 row-cols-md-2 g-4">
          <PrivateFavoriteAnimal
            v-for="(animal, idx) in animalLikes.likes" :ref="animalLikes.likes"
            :key="idx"
            :animal="animal"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router'
import PrivateFavoriteAnimal from "@/views/accounts/components/PrivateFavoriteAnimal";
import PrivateProfileForm from "@/views/accounts/components/PrivateProfileForm";
import MyProfileMyPage from "@/views/accounts/components/MyProfileMyPage";

export default {
  name: "ProfilePrivate",
  components: {
    MyProfileMyPage,
    PrivateProfileForm,
    PrivateFavoriteAnimal,
  },
  setup() {

    const userInfo = reactive({
      email: "",
      img: "",
      phone: "",
      username: "",
    })

    const router = useRouter()
    const animalLikes = reactive({
      likes: []
    })

    const setToken = function () {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };
    // console.log(setToken());
    const getUserInfo = async function () {
      await axios({
        method: "get",
        url: "http://localhost:8080/users/mypage/",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
          // console.log(1);
          // console.log(res.data);
          userInfo.email = res.data.email
          userInfo.img = res.data.img
          userInfo.phone = res.data.phone
          userInfo.username = res.data.username
          // console.log(res.data.animalLikesJoins)
          if (res.data.animalLikesJoins.length != 0) {
            const len = res.data.animalLikesJoins.length
            for(let i = 0; i < len; i++) {
              animalLikes.likes.push(res.data.animalLikesJoins[i].animal)
            }
            // console.log(animalLikes)
          } else {
            isExist.value = false
            console.log(isExist.value)
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };
    getUserInfo()

    const goToConfirmPassword = function () {
      router.push('/confirmpassword')
    }

    // console.log(animalLikes.likes)
    return { getUserInfo, setToken, userInfo, animalLikes, goToConfirmPassword };
  },
};
</script>
<style>
</style>
