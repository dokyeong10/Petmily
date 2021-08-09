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
    <div class="container">
      <MyProfileFavoriteAnimal />
    </div>
  </div>
</template>
<script>
import MyProfileFavoriteAnimal from "@/views/accounts/components/MyProfileFavoriteAnimal";
import MyProfileForm from "@/views/accounts/components/MyProfileForm";
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
      const config = {
        Authorization: `Bearer ${token}`,
      };
      return config;
    };

    const getUserInfo = async function () {
      await axios({
        method: "get",
        url: "http://localhost:8080/users/personal/me",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
          console.log(1);
          console.log(res);
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