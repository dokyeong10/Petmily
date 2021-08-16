<template>
  <div class="Jbgc d-flex mx-auto justify-content-center align-items-center" style="height: 250px;">
    <div class="row justify-content-center mb-5">
      <div class="font-bold mt-4" style="font-size:40px">Modify Mypage</div>
    </div>
  </div>
  <div class="container mb-5 d-flex justify-content-center">
    <div>
      <div>
        <br />
        <div class="mb-4">
          <label class="d-flex flex-row mb-2 semibold">프로필 사진 등록</label>
          <div class="justify-content-center mb-2">
            <input
              class="mb-2"
              id="file-selector"
              ref="profile"
              type="file"
              @change="handleProfileUpload()"
              style="width: 323.85px"
            />
            <button
              @click="profileUpload"
              class="btn-up"
              style="color: #ffffff; height: 38px; width: 66.38px"
              flat
            >
              업로드
            </button>
            <div class="d-flex justify-content-start" v-if="state.toggle">
              <div>업로드 완료!</div>
            </div>
          </div>
          <label for="email" class="d-flex flex-row mb-2">이메일</label>
          <span class="d-flex" style="width: 400px;">
            <input
              v-model="state.email"
              type="text"
              class="form-control radius-border br"
              id="email"
              style="height: 38px;"
              readonly
            />
          </span>
        </div>
        <label for="username" class="d-flex flex-row mb-2">이름</label>
        <input
          v-model="state.username"
          type="text"
          class="form-control radius-border br mb-5"
          placeholder="바꿀 이름을 입력하세요"
          id="username"
          readonly
        />
        <label for="password" class="d-flex flex-row mb-2">비밀번호</label>
        <input
          v-model="state.password"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="비밀번호를 한번 더 입력하세요"
          id="password"
        />
        <label for="confirmPassword" class="d-flex flex-row mb-2">비밀번호 재입력</label>
        <input
          v-model="state.passwordConfirm"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="비밀번호를 한번 더 입력하세요"
          id="confirmPassword"
        />
        <label for="phone" class="d-flex flex-row mb-2">전화번호</label>
        <input
          v-model="state.phone"
          type="text"
          class="form-control br radius-border mb-5"
          placeholder="바꾸실 번호를 입력하세요."
          id="phone"
        />
        <button class="btn-white-modi" style="color: #FFFFFF;" @click="modifyPrivateInfo">
          수정하기
        </button>
        <button class="btn-delete-modi" style="color: #FFFFFF;" @click="deletePrivateInfo">
          탈퇴하기
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive, ref, watchEffect } from "vue";
import { useRouter } from "vue-router";
import AWS from "aws-sdk";
import axios from "axios";
export default {
  name: "PrivateModify",
  setup() {
    const router = useRouter();
    const state = reactive({
      email: "",
      img: "",
      password: "",
      passwordConfirm: "",
      username: "",
      phone: "",

      profile: "",
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
      profileURL: "",
      toggle: false,
    });
    const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/;
    const profile = ref(null);
    const isPasswordValid = ref("");

    watchEffect(() => {
      if (reg.test(state.password)) {
        isPasswordValid.value = "조건이 충족했습니다.";
      } else {
        isPasswordValid.value =
          "비밀번호는 8자 이상 16자 이하이어야 하며 숫자, 대소문자, 특수문자가 적어도 하나 이상 포함되어야 합니다.";
      }
    });
    const setToken = function() {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };

    const getPrivateInfo = async function() {
      await axios({
        method: "get",
        url: "http://localhost:8080/users/personal/me/",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data) {
            state.email = res.data.email;
            state.phone = res.data.phone;
            state.username = res.data.username;
            state.profileURL = res.data.img;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };
    getPrivateInfo();

    const handleProfileUpload = function() {
      state.profile = profile.value.files[0];
    };

    const modifyPrivateInfo = function() {
      if (state.password != state.passwordConfirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      }
      axios({
        method: "patch",
        url: `http://localhost:8080/users/personal/${state.email}`,
        data: {
          email: state.email,
          password: state.password,
          username: state.username,
          phone: state.phone,
          img: state.profileURL,
        },
      })
        .then((res) => {
          console.log(res);
          router.push("/proflieprivate");
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const profileUpload = function() {
      AWS.config.update({
        region: state.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: state.IdentityPoolId,
        }),
      });

      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {
          Bucket: state.albumBucketName,
        },
      });
      let photoKey = state.profile.name;
      s3.upload(
        {
          Key: photoKey,
          Body: state.profile,
          ACL: "public-read",
        },
        (err, data) => {
          if (err) {
            console.log(err);
            return alert("에러가 발생했습니다.", err.message);
          }
          state.profileURL = data.Location;
          state.toggle = true;
        }
      );
    };

    const deletePrivateInfo = function() {
      confirm("탈퇴시 회원과 관련된 모든 정보가 삭제됩니다. 탈퇴하겠습니까?");

      axios({
        method: "delete",
        url: `http://localhost:8080/users/personal/${state.email}`,
      }).then((res) => {
        console.log(res);
        sessionStorage.removeItem("isLogin");
        localStorage.removeItem("jwt");
        localStorage.removeItem("email");
        sessionStorage.removeItem("isAgency");
        sessionStorage.removeItem("isUser");
        location.replace("/");
      });
    };

    return {
      state,
      getPrivateInfo,
      modifyPrivateInfo,
      handleProfileUpload,
      profileUpload,
      profile,
      deletePrivateInfo,
    };
  },
};
</script>
<style>
.btn-white-modi {
  width: 40%;
  background-color: #a4b5f0;
  border-style: none;
  border-radius: 12px;
  margin-left: 10px;
}
.btn-delete-modi {
  width: 40%;
  background-color: #ff8b8b;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>
