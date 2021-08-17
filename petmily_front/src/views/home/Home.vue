<template>
  <div>
    <Jumbotron />
    <div class="container">
      <div align="center">
        <HomeLiveList :lives="liveInfo.lives" />
      </div>
    </div>
    <div class="container">
      <div class="row justify-content-center mb-5">
        <div class="font-bold mb-5" style="font-size: 30px">
          우리 아이의 가족이 되어주세요!
        </div>
        <HomeAnimalLIst
          v-for="(animals, idx) in animalInfo.animals"
          :key="idx + 'c'"
          :animals="animals"
        />
      </div>
    </div>
    <div class="Jbgc" style="height: 650px">
      <div class="container">
        <br />
        <br />
        <div class="font-bold" style="font-size: 30px">Life Guardians</div>
        <div style="font-size: 45px" class="mb-3">생명 수호자들</div>
        <div class="d-grid w-100" align="center">
          <div class="row w-100" align="center">
            <LifeGuardians :guardians="guardiansInfo.guardians" />
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <br />
      <br />
      <div align="left" style="font-size: 25px">Our Team</div>
      <div class="mb-5" align="left" style="font-size: 35px">Developers</div>
      <div class="d-flex">
        <span class="mt-1">
          <img src="@\assets\t1.png" class="developimg" />
          <div class="font-bold mb-1" style="font-size: 20px">
            김정윤 (팀장)
          </div>
          <div class="mb-1">서버 개발</div>
          <div>서울특별시 구로구</div>
        </span>
        <span>
          <img src="@\assets\t2.png" class="developimg" />
          <div class="font-bold mb-1" style="font-size: 20px">
            오도경 (Jira 담당)
          </div>
          <div class="mb-1">서버 개발</div>
          <div>충청남도 태안군</div>
        </span>
        <span>
          <img src="@\assets\t3.png" class="developimg" />
          <div class="font-bold mb-1" style="font-size: 20px">
            박준규 (프론트 팀장)
          </div>
          <div class="mb-1">프론트 개발</div>
          <div>경기도 용인시</div>
        </span>
        <span>
          <img src="@\assets\t4.png" class="developimg" />
          <div class="font-bold mb-1" style="font-size: 20px">
            이경민 (Git 담당)
          </div>
          <div class="mb-1">프론트 개발</div>
          <div>경기도 성남시</div>
        </span>
        <span>
          <img src="@\assets\t5.png" class="developimg" />
          <div class="font-bold mb-1" style="font-size: 20px">
            김태랑 (기획 담당)
          </div>
          <div class="mb-1">프론트 개발</div>
          <div>서울특별시 중랑구</div>
        </span>
      </div>
    </div>
    <br />
    <br />
    <br />
    <br />
    <br />
  </div>
</template>
<script>
import { reactive } from "vue";
import axios from "axios";
import HomeLiveList from "@/views/home/components/HomeLiveList";
import HomeAnimalLIst from "@/views/home/components/HomeAnimalLIst";
import Jumbotron from "@/views/home/components/Jumbotron";
import LifeGuardians from "@/views/home/components/LifeGuardians";

export default {
  name: "Home",
  components: {
    Jumbotron,
    HomeLiveList,
    HomeAnimalLIst,
    LifeGuardians,
  },
  setup() {
    const items = [1, 2, 3];
    const developers = [1, 2, 3, 4, 5];
    const liveInfo = reactive({
      lives: [],
    });
    const animalInfo = reactive({
      animals: [],
    });
    const guardiansInfo = reactive({
      guardians: [],
    });
    const getGuardiansInfo = async function () {
      await axios({
        method: "get",
        url: "https://i5a408.p.ssafy.io:8080/home/shelterList",
      })
        .then((res) => {
          console.log(res);
          guardiansInfo.guardians = res.data;
          // console.log(guardiansInfo.guardians)
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const getAnimalInfo = async function () {
      axios({
        method: "get",
        url: "https://i5a408.p.ssafy.io:8080/home/animalList",
      })
        .then((res) => {
          console.log(res);
          animalInfo.animals = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const getLiveInfo = async function () {
      axios({
        method: "get",
        url: "https://i5a408.p.ssafy.io:8080/home/liveList",
      })
        .then((res) => {
          console.log(res);
          liveInfo.lives = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    };
    getLiveInfo();
    getGuardiansInfo();
    getAnimalInfo();
    return { items, guardiansInfo, developers, animalInfo, liveInfo };
  },
};
</script>
<style>
.font-bold {
  font-weight: bold;
}

.Jbgc {
  background-color: #faf9fe;
}

.developimg {
  height: 60%;
  width: 60%;
  border-radius: 15px;
  margin-bottom: 13px;
  /* //clip-path: circle(30% at 50% 50%); */
}
</style>
