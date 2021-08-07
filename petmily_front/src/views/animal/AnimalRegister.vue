<template>
  <div class="container">
    <div class="align-content-center">
      <form>
        <input type="file" name="photo" id="photo" />
        <button @click="confirmProfilePicRegister">등록</button>
      </form>
      <!-- <form action="confirmProfilePicRegister" method="post" name="profilePicForm" enctype="multipart/form-data" class="d-flex flex-column align-items-start">
        <div>
          <label for="profilePic">프로필 사진을 넣어주세요.</label>
        </div>
        <div>
          <input type="file" name="profilePic">
          <input type="submit" value="등록">
        </div>
      </form> -->
      <form
        action="confirmFilesRegister"
        method="post"
        name="profilePicForm"
        enctype="multipart/form-data"
        class="d-flex flex-column align-items-start"
      >
        <div>
          <label for="profilePic">기타 사진들 및 동영상들을 넣어주세요.</label>
        </div>
        <div>
          <input multiple="multiple" type="file" name="files" />
          <input type="submit" value="등록" />
        </div>
      </form>
      <input
        v-model="state.type"
        class="form-control radius-border"
        type="text"
        placeholder="ex) 강아지, 고양이"
      />
      <input
        v-model="state.species"
        class="form-control radius-border"
        type="text"
        placeholder="ex) 말티즈 시바"
      />
      <input
        v-model="state.age"
        class="form-control radius-border"
        type="text"
        placeholder="ex) 3살 추정"
      />
      <div class="d-flex justify-content-evenly">
        <div>
          성별:
          <span>
            <label
              ><input
                type="radio"
                name="sex"
                v-model="state.sexToggle"
                checked="checked"
                value="false"
              />수컷</label
            >
            <label
              ><input
                type="radio"
                name="sex"
                v-model="state.sexToggle"
                value="true"
              />암컷</label
            >
          </span>
        </div>
        <div>
          중성화 유무:
          <span>
            <label
              ><input
                type="radio"
                name="neutered"
                v-model="state.neuteredToggle"
                value="true"
              />Yes</label
            >
            <label
              ><input
                type="radio"
                name="neutered"
                v-model="state.neuteredToggle"
                checked="checked"
                value="false"
              />No</label
            >
          </span>
        </div>
      </div>
      <input
        v-model="state.addr"
        class="form-control radius-border"
        type="text"
        placeholder="발견 주소"
      />
      <input
        v-model="state.agencycode"
        class="form-control radius-border"
        type="text"
        placeholder="특이사항"
      />
      <!-- 임시로 특이사항에 agencycode 받도록 해놓음 -->
      <input
        v-model="state.find_date"
        class="form-control radius-border"
        type="datetime-local"
        placeholder="발견날짜"
      />
      <button class="btn-white" type="button" @click="confirmAnimalRegister">
        등록하기
      </button>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

// 기관번호 자동으로 받아오는 방법
// postman에서 get으로 되어있는 주소로 jwt 토큰 값을 보내면 유저의 객체가 온다
// 그 객체 안에 기관번호가 담겨있다.
export default {
  name: "AnimalRegister",
  setup() {
    const state = reactive({
      profile_pic: "",
      files: [],
      type: "",
      species: "",
      addr: "",
      age: "",
      sexToggle: "",
      neuteredToggle: "",
      find_date: "",
      userno: "",
      agencycode: "",
    });
    const router = useRouter();
    const confirmAnimalRegister = function () {
      const reg = /.{1,}/;
      const reg_num = /^[0-9]{1,}$/;

      if (!reg.test(state.type)) {
        return alert("동물의 종류를 입력해주세요");
      }
      if (!reg.test(state.species)) {
        return alert("동물의 품종을 입력해주세요");
      }
      if (!reg.test(state.age)) {
        return alert("동물의 나이를 입력해주세요");
      }
      if (!reg_num.test(state.age)) {
        return alert("나이는 숫자만 입력해주세요.");
      }
      if (!reg.test(state.addr)) {
        return alert("발견 주소를 입력해주세요.");
      }
      if (!reg.test(state.find_date)) {
        return alert("날짜를 입력해주세요.");
      }

      axios({
        method: "post",
        url: "http://localhost:8080/animal/register",
        data: {
          name: {
            profile_pic: state.profile_pic,
            type: state.type,
            species: state.species,
            find_addr: state.addr,
            age: state.age,
            sex: state.sexToggle,
            neutered: state.neuteredToggle,
            find_date: state.find_date,
            userno: state.userno,
            agencycode: state.agencycode,
          },
          files: {
            files: state.files,
          },
        },
      })
        .then((res) => {
          console.log(res.data);
          router.push("/animalregistersuccess");
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const confirmProfilePicRegister = function () {
      const frm = new FormData();
      const photoFile = document.getElementById("photo");
      frm.append("photo", photoFile.files[0]);
      axios({
        method: "post",
        url: "",
        data: {
          frm,
        },
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    };
    const confirmFilesRegister = function () {};

    return {
      state,
      confirmAnimalRegister,
      confirmProfilePicRegister,
      confirmFilesRegister,
    };
  },
};
</script>
<style>
.br {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
}

.btn-white {
  width: 200px;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 12px;
}

.radius-border {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  width: 400px;
  color: #789ade;
}
</style>