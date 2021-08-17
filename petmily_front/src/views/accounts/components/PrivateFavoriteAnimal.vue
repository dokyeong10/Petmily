<template>
  <div class=" container">
    <div class="card mb-3">
      <div class="row">
        <div class="col-md-4">
          <img
            :src="animal.profile_img"
            onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
            class="img-fluid rounded-start p-3"
            style="width: 280px; height: 160px"
            alt="..."
          />
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <div class="me-2" align="left">
              <div class="card-text font-bold" style="font-size:20px">{{ animal.type }}</div>
              <div class="card-text">종: {{ animal.species }}</div>
              <div class="card-text">발견 날짜: {{ animal.find_addr }}</div>
              <div class="mt-1">  
                <span>
                  <button class="btn-detail-profile" style="height: 35px" @click="goDetail(animal)">
                    상세보기
                  </button>
                </span>
                <span>
                  <!-- 삭제하기 수정해야합니다 ! ! !  -->
                  <button
                    class="btn-delete-profile"
                    style="height: 35px"
                    @click="cancelFavorite(animal)"
                  >
                    삭제하기
                  </button>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { reactive } from "vue";
import axios from "axios";

export default {
  name: "PrivateFavoriteAnimal",
  props: {
    animal: {
      type: Object,
    },
  },
  setup(props) {
    let sex = ref("");
    let neutered = ref("");
    const getUserInfo = function() {
      const no = localStorage.getItem("userno");
      return no;
    };
    const getFavoriteInfo = function() {
      axios({
        method: "get",
        url: `http://localhost:8080/users/like/${localStorage.getItem("userno")}`,
      })
        .then((res) => {
          state.favoriteData = res.data;
          state.favoriteArray = [];
          state.favoriteData.forEach((element) => {
            if (!state.favoriteArray.includes(element.animalno)) {
              state.favoriteArray.push(element.animalno);
            }
          });
          state.favoriteArray.sort(function(a, b) {
            return a - b;
          });
        })
        .catch((err) => {
          console.log(err);
        });
    };
    getFavoriteInfo();
    const router = useRouter();
    const state = reactive({
      userno: getUserInfo(),
      // isClickedSearch: false,
      data: {},
      favoriteData: {},
      favoriteArray: [],
      tempFavoriteNumber: "",
      numberOfItems: 4,

      isLike: false,
    });
    const confirm = function() {
      if (props.animal.sex) {
        sex.value = "암컷";
      } else {
        sex.value = "수컷";
      }
      if (props.animal.neutered) {
        neutered.value = "⭕️";
      } else {
        neutered.value = "❌";
      }
    };
    const goDetail = function(animal) {
      router.push({
        name: "animaldetail",
        params: {
          id: animal.no,
        },
      });
    };

    const cancelFavorite = function(animal) {
      state.favoriteData.forEach((element) => {
        if (element.animalno === animal.no) {
          state.tempFavoriteNumber = element.no;
        }
      });
      axios({
        method: "delete",
        url: `http://localhost:8080/users/like/${state.tempFavoriteNumber}`,
      })
        .then((res) => {
          confirm("즐겨찾기 동물을 삭제하시겠습니까?");
          console.log(res);
          getFavoriteInfo();
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        });
    };
    confirm();
    return { state, sex, neutered, goDetail, cancelFavorite, getUserInfo };
  },
};
</script>
<style>
.btn-white {
  width: 200px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 12px;
}
</style>
