<template>
  <div class="Jbgc" style="height: 600px">
    <br />
    <div class="container">
      <div class="d-flex justify-content-center">
        <div class="me-5">
          <img
            v-if="aniInfo.profile_img != null"
            :src="aniInfo.profile_img"
            class="m-5"
            style="width: 360px; height: 350px; border-radius: 30px; margin-left : 7%"
          />
          <img
            v-else
            src="@/assets/PetmilyLogo.png"
            class="mt-3 mb-5"
            style="
              width: 360px;
              height: 350px;
              border-radius: 30px;
              margin-left: 7%;
            "
          />
        </div>
        <div v-if="aniInfo" class="d-flex flex-column mt-5 ms-5">
          <div class="mb-2" align="left">
            <span class="title"> 종 : </span>
            <span class="content"> {{ aniInfo.type }} </span>
          </div>
          <div class="mt-1 mb-2" align="left">
            <span class="title"> 종류 : </span>
            <span class="content"> {{ aniInfo.species }} </span>
          </div>
          <div class="mt-1 mb-2" align="left">
            <span class="title"> 나이 : </span>
            <span class="content"> {{ aniInfo.age }}</span>
          </div>
          <div class="mt-1 mb-2" align="left" v-if="aniInfo.sex">
            <span class="title"> 성별 : </span>
            <span class="content"> 수컷</span>
          </div>
          <div class="mt-1 mb-2" align="left" v-else>
            <span class="title"> 성별 : </span>
            <span class="content"> 암컷</span>
          </div>
          <div class="mt-1 mb-2 f" align="left" v-if="aniInfo.neutered">
            <span class="title"> 중성화 : </span>
            <span class="content"> O</span>
          </div>
          <div class="mt-1 mb-2" align="left" v-else>
            <span class="title"> 중성화 :</span><span class="content"> X</span>
          </div>
          <div class="mt-1 mb-2" align="left">
            <span class="title"> 발견 장소 : </span>
            <span class="content"> {{ aniInfo.find_addr }}</span>
          </div>
          <div class="mt-1 mb-2" align="left">
            <span class="title"> 발견 날짜 : </span>
            <span class="content"> {{ aniInfo.find_date.substring(0, 10) }}</span>
          </div>
          <div class="mt-1 mb-2" align="left">
            <span class="title"> 특이사항 : </span>
            <span class="content"> {{ aniInfo.text }}</span>
          </div>
        </div>
      </div>
      <br />
      <div v-if="aniInfo.agencycode == state.agencycode" class="d-flex justify-content-end">
        <button class="me-3 btn-modify" @click="animalModify">수정하기</button>
        <!-- Button trigger modal -->
        <button
          type="button"
          class="btn-delete"
          data-bs-toggle="modal"
          data-bs-target="#animalDeleteModal"
        >
          삭제하기
        </button>

        <!-- Modal -->
        <div
          class="modal fade"
          id="animalDeleteModal"
          tabindex="-1"
          aria-labelledby="animalDeleteModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="animalDeleteModalLabel">
                  삭제 확인
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">정말 삭제하시겠습니까?</div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn-delete"
                  data-bs-dismiss="modal"
                  @click="animalDelete"
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
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { reactive, onMounted } from "vue";
import axios from "axios";

export default {
  props: {
    aniInfo: Object,
  },
  setup(props) {
    const router = useRouter();

    const state = reactive({
      agencycode: getAgencycode(),
    });

    function getAgencycode() {
      return localStorage.getItem("agencycode");
    }

    const animalModify = function() {
      router.push({
        name: "animalmodify",
        params: {
          no: props.aniInfo.no,
        },
      });
    };
    const animalDelete = function() {
      axios({
        method: "delete",
        url: `https://i5a408.p.ssafy.io:8080/animal/${props.aniInfo.no}`,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      router.push({
        name: "animaldelete",
      });
    };
    return { state, animalModify, animalDelete };
  },
};
</script>

<style>
.btn-chatting {
  width: 30%;
  height: 50px;
  background-color: #000000b4;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.btn-modify {
  width: 15%;
  height: 30px;
  background-color: #a4b5f0;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.btn-delete {
  width: 15%;
  height: 30px;
  background-color: #ff8b8b;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.title {
  font-size: 22px;
  font-weight: bold;
  margin-right: 8px;
}
.content {
  font-size: 19px;
}
.btn-login-modal {
  width: 15%;
  height: 30px;
  background-color: #a4b5f0;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>
