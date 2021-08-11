<template>
  <div class="Jbgc d-flex justify-content-center align-items-center mb-5" style="height: 230px;">
    <div class="container">
      <div class="row justify-content-center mb-5">
        <div class="font-bold mt-5" style="font-size:40px">실시간 라이브</div>
      </div>
    </div>
  </div>

  <!-- 기관 코드 검색 모달 -->
  <button
    type="button"
    class="btn-up"
    data-bs-toggle="modal"
    data-bs-target="#agencycodeSearchModal"
    style="height: 50px; width: 150px"
  >
    라이브 만들기
  </button>
  <div
    class="modal fade"
    id="agencycodeSearchModal"
    tabindex="-1"
    aria-labelledby="agencycodeSearchModalLabel"
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
        <label class="font-bold">라이브 사진</label>
        <div class="mb-4">
          <label class="d-flex flex-row mb-2">사진등록</label>
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

        <label class="font-bold">라이브 이름</label>
        <div class="modal-body d-flex mb-3">
          <input
            type="text"
            class="form-control radius-border br"
            style="width:100%"
            placeholder="제목"
          />
        </div>
        <label class="font-bold">라이브 설명</label>
        <div class="modal-body d-flex">
          <input
            type="text"
            class="form-control radius-border br"
            style="height:100px; width:100%"
            placeholder="설명"
          />
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            닫기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AWS from "aws-sdk";
import { ref, reactive } from "vue";

import axios from "axios";

export default {
  name: "Live",
  setup() {
    const file = ref(null);

    const state = reactive({
      file: null,
      imgURL: "",
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
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

    return { state, handleFileUpload, upload, file };
  },
};
</script>
// <style></style>
