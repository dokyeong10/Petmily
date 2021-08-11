<template>
  <div>
    <div>
      <MyProfileMyPage />
    </div>
    <br />
    <div class="container">
      <MyProfileForm />
    </div>
    <br />
<<<<<<< HEAD
    <div class="container">
      <MyProfileFavoriteAnimal />
=======
    <div class="d-flex jusify-content-start mx-auto" style="width: 800px">
      <h2>즐겨찾기 한 동물</h2>
    </div>
    <div class="mx-auto" style="height: 400px; width: 800px;">
      <div class="container">
        <div class="row row-cols-1 row-cols-md-2 g-4">
          <PrivateFavoriteAnimal
            v-for="(animal, idx) in animalLikes.likes" 
            :key="idx"
            :animal="animal"
          />
        </div>
      </div>
>>>>>>> origin/emergency_backup_push_branch
    </div>
  </div>
</template>
<script>
<<<<<<< HEAD
import MyProfileFavoriteAnimal from "@/views/accounts/components/MyProfileFavoriteAnimal";
import MyProfileForm from "@/views/accounts/components/MyProfileForm";
=======
import axios from "axios";
import { reactive } from 'vue';
import { useRouter } from 'vue-router'
import PrivateFavoriteAnimal from "@/views/accounts/components/PrivateFavoriteAnimal";
import PrivateProfileForm from "@/views/accounts/components/PrivateProfileForm";
>>>>>>> origin/emergency_backup_push_branch
import MyProfileMyPage from "@/views/accounts/components/MyProfileMyPage";
import axios from "axios";

export default {
  name: "ProfilePrivate",
  components: {
    MyProfileMyPage,
    MyProfileForm,
    MyProfileFavoriteAnimal,
  },
  setup() {
    const setToken = function () {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };
    console.log(setToken());
    const getUserInfo = async function () {
      await axios({
        method: "get",
        url: "http://localhost:8080/users/mypage/",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
<<<<<<< HEAD
          console.log(1);
          console.log(res.data);
=======
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
          }
>>>>>>> origin/emergency_backup_push_branch
        })
        .catch((err) => {
          console.log(2);
          console.log(err);
        });
    };
    console.log(getUserInfo());
    return { getUserInfo, setToken };
  },
};
</script>
<style>
</style>
