<template>
  <div class=" container">
    <div class="card mb-3">
      <div class="row">
        <div class="col-md-4">
          <img
            :src="animal.profile_img"
            onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
            class="img-fluid rounded-start"
            style="width: 280px; height: 160px"
            alt="..."
          />
        </div>
        <div class="col-md-8">
          <div class="card-body">
            <div class="me-2">
              <div class="card-text font-bold" style="font-size:20px">{{ animal.type }}</div>
              <div class="card-text">{{ animal.species }}</div>
              <div class="card-text">{{ animal.find_addr }}</div>
              <span>
                <button class="btn-detail-profile" style="height: 35px" @click="goDetail(animal)">
                  상세보기
                </button>
              </span>
              <span>
                <!-- 삭제하기 수정해야합니다 ! ! !  -->
                <button class="btn-delete-profile" style="height: 35px" @click="goDetail(animal)">
                  삭제하기
                </button>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import { reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import JumbotronAnimalDetail from "@/views/animal/components/JumbotronAnimalDetail.vue";

export default {
  components: {
    JumbotronAnimalDetail,
  },
  name: "AgencyRegisterAnimal",
  props: {
    animal: {
      type: Object,
    },
  },
  setup(props) {
    const router = useRouter();
    const state = reactive({
      // isClickedSearch: false,
      data: {},
      favoriteData: {},
      favoriteArray: [],
      tempFavoriteNumber: "",
      numberOfItems: 4,
      page: 1,
      numberOfPages: 0,
      isLike: false,
    });
    let sex = ref("");
    let neutered = ref("");

    const goDetail = function(animal) {
      router.push({
        name: "animaldetail",
        params: {
          id: animal.no,
        },
      });
    };
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
    confirm();
    return { state, sex, neutered, goDetail };
  },
};
</script>
<style>
.card-text {
  padding: 2px;
}
.btn-delete-profile {
  width: 40%;
  font-size: 13px;
  background-color: #ff8b8b;
  border-style: none;
  border-radius: 30px;
  color: white;
  margin-left: 10px;
}
.btn-detail-profile {
  width: 40%;
  font-size: 13px;
  background-color: #a4b5f0;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>
