<template>
  <div class=" container">
    <div class="card mb-3">
      <div class="row">
        <div class="col-md-4">
          <img
            :src="animal.profile_img"
            onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
            class="img-fluid p-3"
            style="width: 180px; height: 180px; border-radius: 25px;"
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
                <!-- v-for 안에서 modal 사용방법: button의 data-bs-target과 modal의 id에 v-bind 사용! -->
                <button
                  type="button"
                  class="btn-delete-profile"
                  data-bs-toggle="modal"
                  :data-bs-target="'#animal' + animal.no"
                >
                  삭제하기
                </button>

                <!-- Modal -->
                <div
                  class="modal fade"
                  :id="'animal' + animal.no"
                  tabindex="-1"
                  aria-labelledby="animalDeleteModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="animalDeleteModalLabel">삭제 확인</h5>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">
                        정말 삭제하시겠습니까?
                      </div>
                      <div class="modal-footer">
                        <button
                          type="button"
                          class="btn-delete"
                          data-bs-dismiss="modal"
                          @click="animalDelete(animal)"
                        >
                          삭제
                        </button>
                        <button type="button" class="btn-login-modal" data-bs-dismiss="modal">
                          취소
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
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
import { reactive } from "vue";
import { useRouter } from "vue-router";

import axios from "axios";

export default {
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
    const animalDelete = function(animal) {
      axios({
        method: "delete",
        url: `https://i5a408.p.ssafy.io:8080/animal/${animal.no}`,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
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
    return { state, sex, neutered, goDetail, animalDelete };
  },
};
</script>
<style>
.card-text {
  padding: 2px;
}
.btn-delete-profile {
  width: 40%;
  height: 35px;
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
