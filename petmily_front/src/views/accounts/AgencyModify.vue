<template>
  <div class="Jbgc d-flex mx-auto justify-content-center align-items-center" style="height: 300px;">
    <div>
      <h1>Modify Member</h1>
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
          <label for="agencyname" class="d-flex flex-row mb-2">보호소 이름</label>
          <span class="d-flex" style="width: 400px;">
            <input
              v-model="state.agencyname"
              type="text"
              class="form-control radius-border br mb-5"
              id="agencyname"
              style="height: 38px;"
              readonly
            />
          </span>
          <label for="agencycode" class="d-flex flex-row mb-2">보호소 코드</label>
          <span class="d-flex" style="width: 400px;">
            <input
              v-model="state.agencycode"
              type="text"
              class="form-control radius-border br mb-5"
              id="agencycode"
              style="height: 38px;"
              readonly
            />
          </span>
          <label for="email" class="d-flex flex-row mb-2">이메일</label>
          <span class="d-flex" style="width: 400px;">
            <input
              v-model="state.email"
              type="text"
              class="form-control radius-border br mb-5"
              id="email"
              style="height: 38px;"
              readonly
            />
          </span>
          <label for="username" class="d-flex flex-row mb-2">이름</label>
        </div>
        <input
          v-model="state.username"
          type="text"
          class="form-control radius-border br mb-5"
          placeholder="바꿀 이름을 입력하세요"
          id="username"
        />
        <label for="password" class="d-flex flex-row mb-2">비밀번호</label>
        <input
          v-model="state.password"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="••••••••"
          id="password"
        />
        <label for="confirmPassword" class="d-flex flex-row mb-2">비밀번호 재입력</label>
        <input
          v-model="state.passwordConfirm"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="••••••••"
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
        <button class="btn-white" style="color: #FFFFFF;" @click="modifyAgencyInfo">
          수정하기
        </button>
        <button class="btn-white" style="color: #FFFFFF;" @click="deleteAgencyInfo">
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
      agencyname: "",
      agencycode: "",

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

    const getAgencyInfo = async function() {
      await axios({
        method: "get",
        url: "https://i5a408.p.ssafy.io:8080/users/agency/me/",
        headers: {
          Authorization: setToken(),
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data) {
            state.agencyname = res.data.agencyname;
            state.agencycode = res.data.agencycode;
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
    getAgencyInfo();

    const handleProfileUpload = function() {
      state.profile = profile.value.files[0];
    };

    const modifyAgencyInfo = function() {
      if (state.password != state.passwordConfirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      }
      axios({
        method: "patch",
        url: `https://i5a408.p.ssafy.io:8080/users/agency/${state.email}`,
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
          router.push("/profileagency");
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

    const deleteAgencyInfo = function() {
      // 회원 탈퇴 시 모달창... 띄울까 말까.. 고민해봐야 합니다..
      alert("탈퇴시 회원과 관련된 모든 정보가 삭제됩니다. 탈퇴하겠습니까?");

      axios({
        method: "delete",
        url: `https://i5a408.p.ssafy.io:8080/users/agency/${state.email}`,
      }).then((res) => {
        console.log(res);
        sessionStorage.removeItem("isLogin");
        localStorage.removeItem("jwt");
        localStorage.removeItem("email");
        sessionStorage.removeItem("isAgency");
        sessionStorage.removeItem("isUser");
        router.go("/home");
      });
    };

    return {
      state,
      getAgencyInfo,
      modifyAgencyInfo,
      handleProfileUpload,
      profileUpload,
      profile,
      deleteAgencyInfo,
    };
  },
};
</script>
<style></style>
