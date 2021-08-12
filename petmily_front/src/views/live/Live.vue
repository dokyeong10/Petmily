<template>
  <div class="Jbgc d-flex justify-content-center align-items-center mb-5" style="height: 250px;">
    <div class="container">
      <div class="row justify-content-center mb-5">
        <div class="font-bold mt-4" style="font-size:40px">실시간 라이브</div>
      </div>
      <div v-if="isAgency">
        <button
          class="btn-up"
          data-bs-toggle="modal"
          data-bs-target="#LiveModal"
          style="height: 50px; width: 150px"
        >
          라이브 만들기
        </button>
      </div>
    </div>
  </div>

  <!-- 기관 코드 검색 모달 -->
  <div
    class="modal fade"
    id="LiveModal"
    tabindex="-1"
    aria-labelledby="LiveModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header mb-3">
          <h5 class="modal-title" id="LiveInfo">라이브 정보 입력</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="mb-4 mt-3">
          <label class="d-flex flex-row ms-4 mb-2 font-bold">사진등록</label>
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
              class="btn-up "
              style="color: #FFFFFF; height: 38px; width: 66.38px;"
              flat
            >
              업로드
            </button>
            <div class="d-flex justify-content-start" v-if="state.imgURL">
              <div class="ms-3">
                업로드 완료!
              </div>
            </div>
          </div>
        </div>

        <p style="color:red;">업로드 버튼을 눌러주세요</p>

        <label class="d-flex flex-row ms-4 mb-2 font-bold">라이브 제목</label>
        <div class="modal-body d-flex mb-3">
          <input
            v-model="state.title"
            type="text"
            class="form-control radius-border br"
            style="width:100%"
            placeholder="제목"
          />
        </div>
        <label class="d-flex flex-row ms-4 mb-1 font-bold">설명</label>
        <div class="modal-body d-flex">
          <input
            v-model="state.description"
            type="text"
            class="form-control radius-border br"
            style="height:100px; width:100%"
            placeholder="설명"
          />
        </div>

        <div class="modal-footer justify-content-center">
          <button class="btn-white mt-3" style="color: #FFFFFF; height:40px;" @click="LiveStart">
            라이브 시작하기
          </button>
        </div>
      </div>
    </div>
  </div>

  <div class="">
    <!-- <div class="font-bold mb-3" style="font-size: 2rem;">진행 중인 라이브</div> -->
    <div class=" " align="center">
      <div class="row" align="center">
        <LiveList v-for="(live, no) in liveInfo.live" :key="no + 'b'" :live="live" />
      </div>
    </div>
  </div>
</template>

<script>
import AWS from "aws-sdk";
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import LiveList from "@/views/live/LiveList";
import { computed } from "@vue/runtime-core";

export default {
  name: "Live",
  components: {
    LiveList,
  },
  setup() {
    const file = ref(null);
    const liveInfo = reactive({
      live: [],
    });

    const getLiveInfo = async function() {
      await axios({
        method: "get",
        url: "http://localhost:8080/live/",
      })
        .then((res) => {
          // console.log(res);
          liveInfo.live = res.data;
          console.log(liveInfo.live);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const setToken = function() {
      console.log("setToken");
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      console.log(config);
      return config;
    };

    const state = reactive({
      agencycode: "",
      title: "",
      description: "",
      start_time: null,
      img: "",

      file: null,
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
      imgURL: null,
    });

    const router = useRouter();

    const LiveStart = function() {
      const reg = /.{1,}/;
      // if (!state.img) {
      //   return alert("방사진을 등록해주세요.");
      // }

      if (state.img && !state.imgURL) {
        return alert("업로드 버튼을 눌러주세요.");
      }
      if (!reg.test(state.title)) {
        return alert("방제목을 입력하세요.");
      }
      if (!reg.test(state.description)) {
        return alert("방 설명 입력하세요.");
      }
      axios({
        method: "post",
        url: "http://localhost:8080/live/register",
        headers: {
          Authorization: setToken(),
        },
        data: {
          img: state.imgURL,
          title: state.title,
          description: state.description,
        },
      })
        .then((res) => {
          console.log(res.data);
          alert("라이브방 생성 성공!");
          // router.push("/signupsuccess");
          location.href = "/LiveRoom/" + state.agencycode;
        })
        .catch((err) => {
          console.log(err);
          alert("이미 라이브중인 기관입니다!");
          document.querySelector("#LiveModal").classList.add("hidden");
          // router.replace("/livelist");
        });
    };

    const isAgency = computed(() => {
      console.log(sessionStorage.getItem("isAgency"));
      return sessionStorage.getItem("isAgency");
    });

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

    getLiveInfo();
    return { state, handleFileUpload, upload, file, LiveStart, liveInfo, isAgency };
  },
};
</script>
<style></style>
