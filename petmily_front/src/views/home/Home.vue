<template>
  <div>
    <Jumbotron/>
    <HomeLiveList/>
    <HomeAnimalLIst/>
  </div>
</template>
<script>
import HomeLiveList from "@/views/home/components/HomeLiveList";
import HomeAnimalLIst from "@/views/home/components/HomeAnimalLIst";
import Jumbotron from "@/views/home/components/Jumbotron";

export default {
  name: "Home",
  components: {
    Jumbotron,
    HomeLiveList,
    HomeAnimalLIst,
  },
  setup() {},
};
</script>
<style>
</style>