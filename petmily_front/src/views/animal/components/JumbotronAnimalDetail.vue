<template>
  <div class="Jbgc" style="height: 600px;">
    <br/>
    <div class="container">
      <div class="d-flex justify-content-start">
        <div class="me-5">
          <img
            src="@\assets\dog.png"
            class="mt-3 mb-5"
            style="width: 500px; height: 390px; border-radius: 30px; margin-left :7%"
          />
          <button class="btn-chatting">채팅상담</button>
        </div>
        <div v-if="aniInfo" class="d-flex flex-column mt-5">
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            이름: {{ aniInfo.species }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            종류: {{ aniInfo.type }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            나이: {{ aniInfo.age }}
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-if="aniInfo.sex">
            성별: 수컷
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-else>
            성별: 암컷
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-if="aniInfo.neutered">
            중성화: O
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;" v-else>
            중성화: X
          </div>
          <div class=" mt-1 mb-3 font-bold" align="left" style="font-size: 17px;">
            특이사항: {{ aniInfo.text }}
          </div>
        </div>
      </div>
      <br>
      <div v-if="aniInfo.agencycode == state.agencycode" class="d-flex justify-content-end">
        <button class="me-3 btn-modify" @click="animalModify">수정하기</button>
        <!-- Button trigger modal -->
        <button type="button" class="btn-delete" data-bs-toggle="modal" data-bs-target="#animalDeleteModal">
          삭제하기
        </button>

        <!-- Modal -->
        <div class="modal fade" id="animalDeleteModal" tabindex="-1" aria-labelledby="animalDeleteModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="animalDeleteModalLabel">삭제 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                정말 삭제하시겠습니까?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="animalDelete">삭제</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
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
    aniInfo: Object
  },
  setup(props) {
    onMounted(() => {
      const setToken = function() {
        const token = localStorage.getItem("jwt");
        const config = `Bearer ${token}`;
        return config;
      };
      axios({
        method: "get",
        url: "http://localhost:8080/users/agency/me",
        headers: {
          Authorization: setToken(),
        }
      })
      .then((res) => {
        state.agencycode = res.data.agencycode
      })
      .catch((err) => {
        console.log(err)
      })
    })
    const router = useRouter();

    const state = reactive({
      agencycode: "",
    })

    const animalModify = function () {
      router.push({
        name: "animalmodify",
        params: {
          no: props.aniInfo.no,
        },
      });
    }
    const animalDelete = function () {
      axios({
        method: "delete",
        url: `http://localhost:8080/animal/${props.aniInfo.no}`,
      })
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
      router.push({
        name: "animaldelete",
      });
    }
    return { state, animalModify, animalDelete, }
  },
}
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
  background-color: #A4B5F0;
  border-style: none;
  border-radius: 30px;
  color: white;
}
.btn-delete {
  width: 15%;
  height: 30px;
  background-color: #FF8B8B;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>