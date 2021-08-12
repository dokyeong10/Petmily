<template>
  <JumbotronForms />

  <div class="container mb-5 d-flex justify-content-center">
    <div>
      <div>
        <div class="d-flex flex-column align-items-start">
          <h1 class="overpass">Sign up to Petmily</h1>
          <h5 class="overpass colored">Individual Member</h5>
        </div>
        <br />
        <div class="mb-4">
          <label for="email" class="d-flex flex-row mb-2 semibold">이메일</label>
          <span class="d-flex" style="width: 400px;">
            <input
              v-model="state.email"
              type="text"
              class="form-control radius-border br"
              placeholder="petmily@email.com"
              @keyup.enter="confirmEmail"
              id="email"
              style="height: 38px;"
            />
            <button
              @click="confirmEmail"
              type="button"
              class="btn-up"
              style="color: #FFFFFF; margin-left:10px;"
            >
              인증
            </button>
          </span>
        </div>
        <div class="mb-4">
          <span v-if="state.isEmail" style="width: 400px;">
            <label for="emailcode" class="d-flex flex-row mb-2 semibold">인증번호</label>
            <div class="d-flex" style="width: 400px;">
              <input
                v-model="state.number"
                type="text"
                class="form-control radius-border br"
                placeholder="인증번호를 입력해주세요."
                @keyup.enter="confirmNumber"
                id="emailcode"
                style="height: 38px;"
              />
              <button @click="confirmNumber" type="button" class="btn-up" style="color: #FFFFFF;">
                인증하기
              </button>
            </div>
          </span>
        </div>
        <div v-if="state.isConfirm" class="mb-5">
          인증이 완료되었습니다.
        </div>
        <div class="mb-4">
          <label class="d-flex flex-row mb-2 semibold">사진등록</label>
          <div class="justify-content-center">
            <input
              class="mb-2"
              id="file-selector"
              ref="file"
              type="file"
              @change="handleFileUpload()"
              style="width: 323.85px"
            />
            <button
              @click="upload"
              class="btn-up"
              style="color: #FFFFFF; height: 38px; width: 66.38px;"
              flat
            >
              업로드
            </button>
            <div class="d-flex justify-content-start" v-if="state.imgURL">
              <div>
                업로드 완료!
              </div>
            </div>
          </div>
        </div>
        <label for="username" class="d-flex flex-row mb-2 semibold">이름</label>
        <input
          v-model="state.username"
          type="text"
          class="form-control radius-border br mb-5"
          placeholder="홍길동"
          id="username"
        />
        <label for="password" class="d-flex flex-row mb-2 semibold">비밀번호</label>
        <input
          v-model="state.password"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="••••••••"
          id="password"
        />
        <label for="confirmPassword" class="d-flex flex-row mb-2 semibold">비밀번호 재입력</label>
        <input
          v-model="state.passwordConfirm"
          type="password"
          class="form-control radius-border br mb-5"
          placeholder="••••••••"
          id="confirmPassword"
        />
        <label for="phone" class="d-flex flex-row mb-2 semibold">전화번호</label>
        <input
          v-model="state.phone"
          type="text"
          class="form-control br radius-border mb-5"
          placeholder="010-1234-5678"
          id="phone"
        />
        <button class="btn-white" style="color: #FFFFFF;" @click="confirmSignup">
          회원가입 하기
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import { ref, reactive } from "vue";
import AWS from "aws-sdk";
import axios from "axios";
import { useRouter } from "vue-router";
import JumbotronForms from "@/views/accounts/components/JumbotronForms";

export default {
  components: { JumbotronForms },
  name: "privateform",

  setup() {
    const file = ref(null);

    const state = reactive({
      isEmail: false,
      isConfirm: false,
      number: null,
      email: "",
      username: "",
      password: null,
      passwordConfirm: null,
      phone: "",

      file: null,
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
      imgURL: "",
    });
    const router = useRouter();

    const setToken = function() {
      const token = localStorage.getItem("jwt");
      const config = {
        Authorization: `JWT ${token}`,
      };
      return config;
    };

    const confirmEmail = function() {
      axios({
        method: "post",
        url: "http://localhost:8080/email/send/register",
        data: {
          email: state.email,
        },
      })
        .then((res) => {
          console.log(res);
          alert("작성하신 이메일로 인증번호를 보내드렸습니다. 아래 칸에 인증번호를 입력해주세요.");
          state.isEmail = true;
        })
        .catch((err) => {
          if (err.response.status >= 500) {
            alert("올바른 이메일을 입력해주세요!");
          } else {
            alert("이미 가입된 이메일입니다!");
          }
        });
    };

    const confirmNumber = function() {
      axios({
        method: "post",
        url: "http://localhost:8080/email/check/authcode",
        data: {
          email: state.email,
          authCode: state.number,
        },
      })
        .then((res) => {
          console.log(res);
          if (res.data) {
            alert("인증에 성공하셨습니다.");
            state.isEmail = false;
            state.isConfirm = true;
          } else {
            alert("잘못된 인증 번호입니다.");
            return;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const confirmSignup = function() {
      const reg = /(?=.*[a-zA-ZS])(?=.*?[#?!@$%^&*-]).{8,16}/;
      const reg_required = /.{1,}/;
      const reg_phone = /^\d{3}-\d{3,4}-\d{4}$/;
      const reg_name = /^[가-힣]+$/;

      if (!state.isConfirm) {
        return alert("이메일 인증을 진행해주세요.");
      }

      if (!reg_name.test(state.username)) {
        return alert("올바른 이름을 입력하세요.");
      }

      if (!reg.test(state.password)) {
        return alert(
          "비밀번호는 8자 이상 16자 이하이어야 하며 숫자, 대소문자, 특수문자가 적어도 하나 이상 포함되어야 합니다."
        );
      }

      if (state.password != state.passwordConfirm) {
        return alert("비밀번호가 일치하지 않습니다.");
      }

      if (!reg_required.test(state.phone)) {
        return alert("전화번호는 필수 항목입니다.");
      }

      if (!reg_phone.test(state.phone)) {
        return alert("올바른 번호를 입력해주세요. 예: 010-0000-0000");
      }

      axios({
        method: "post",
        url: "http://localhost:8080/users/personal",
        data: {
          email: state.email,
          username: state.username,
          password: state.password,
          phone: state.phone,
          img: state.imgURL,
        },
      })
        .then((res) => {
          console.log(res.data);
          router.push("/signupsuccess");
        })
        .catch((err) => {
          console.log(err);
        });
    };

    // 이미지 업로드 구문 ///
    const handleFileUpload = function() {
      state.file = file.value.files[0];
    };

    const upload = function() {
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
      let photoKey = state.file.name;
      s3.upload(
        {
          Key: photoKey,
          Body: state.file,
          ACL: "public-read",
        },
        (err, data) => {
          if (err) {
            console.log(err);
            return alert("에러가 발생했습니다.", err.message);
          }
          state.imgURL = data.Location;
        }
      );
    };

    return {
      state,
      confirmSignup,
      setToken,
      confirmEmail,
      confirmNumber,
      handleFileUpload,
      upload,
      file,
    };
  },
};
</script>
<style>
@import url("https://fonts.googleapis.com/css2?family=Overpass:wght@700&display=swap");
.overpass {
  font-family: "Overpass", sans-serif;
}
.colored {
  color: #b8b6f9;
}
.image-upload {
  width: 120px !important;
  height: 120px !important;
  font-size: 100px;
  text-align: right;
  min-width: 0 !important;
  outline: none;
  background: rgb(0, 0, 0);
  cursor: inherit;
  display: block !important;
  border-radius: 50% !important;
  cursor: pointer;
  position: absolute;
  margin: 0 !important;
  z-index: -1;
}

.image-upload-wrapper {
  position: inherit;
  width: 120px !important;
  height: 120px !important;
  font-size: 100px;
  text-align: right;
  min-width: 0 !important;
  outline: none;
  background: rgb(255, 255, 255);
  cursor: inherit;
  display: block !important;
  border-radius: 50% !important;
  cursor: pointer;
}
.br {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
}
.btn-up {
  width: 100px;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 12px;
  margin-left: 10px;
}
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

.radius-border {
  border-radius: 12px;
  border-right: #789ade 1px solid;
  border-left: #789ade 1px solid;
  border-top: #789ade 1px solid;
  border-bottom: #789ade 1px solid;
  width: 400px;
  color: #789ade;
}

.semibold {
  font-weight: 600;
}
</style>
