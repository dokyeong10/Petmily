<template>
  <div class="container mb-5 d-flex justify-content-center">
    <div>
      <label class="d-flex flex-row mb-2 semibold">반려견 사진 또는 동영상 등록 </label>
      <div class="justify-content-center mb-2">
        <input
          multiple="multiple"
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
          style="color: #ffffff; height: 38px; width: 66.38px"
          flat
        >
          업로드
        </button>
        <div class="d-flex justify-content-start" v-if="state.imgURL.length">
          <div>업로드 완료!</div>
        </div>
      </div>
      <br />
      <label for="title" class="d-flex flex-row mb-2 semibold">제목</label>
      <input
        v-model="state.title"
        type="text"
        class="form-control radius-border br mb-5"
        placeholder="제목을 입력하세요."
        id="title"
      />

      <label class="d-flex flex-row mb-2 semibold">내용</label>
      <form>
        <p>
          <textarea
            v-model="state.contents"
            cols="50"
            rows="10"
            style="overflow:auto; white-space: pre-wrap;"
            class="form-control radius-border"
            placeholder="내용을 입력하세요."
          >
          </textarea>
        </p>
      </form>
      <button
        class="btn-white"
        @click="registerBoard"
        @keyup.enter="registerBoard"
        style="color: #FFFFFF;"
      >
        게시글 수정하기
      </button>
    </div>
  </div>
</template>
<script>
import { onMounted, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
export default {
  name: "BoardDetailModify",
  setup() {
    const router = useRouter();
    const state = reactive({
      no: null,
      title: "",
      contents: "",

      file: [],
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
      profileURL: "",
      imgURL: [],
    });

    const file = ref(null);

    onMounted(() => {
      axios({
        method: "get",
        url: `https://i5a408.p.ssafy.io:8080/board/details/${router.currentRoute._value.params.boardno}`,
      })
        .then((res) => {
          console.log(res);
          state.no = res.data.no;
          state.contents = res.data.contents;
          state.title = res.data.title;
          // state.photo = res
        })
        .catch((err) => {
          console.log(err);
        });
    });

    const handleFileUpload = function() {
      file.value.files.forEach(function(element) {
        state.file.push(element);
      });
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
      let photoKey = [];
      state.file.forEach(function(file) {
        photoKey.push(file.name);
      });

      for (var i = 0; i < file.value.files.length; i++) {
        s3.upload(
          {
            Key: photoKey[i],
            Body: state.file[i],
            ACL: "public-read",
          },
          (err, data) => {
            if (err) {
              console.log(err);
              return alert("에러가 발생했습니다.", err.message);
            }
            state.imgURL.push(data.Location);
          }
        );
      }
    };

    const registerBoard = function() {
      const reg_title = /.{1,100}/;
      const reg_content = /.{1,500}/;
      console.log(state);
      if (!reg_title.test(state.title)) {
        return alert("제목은 1자 이상 100자 이하로 작성해주세요!");
      }

      if (!reg_content.test(state.title)) {
        return alert("제목은 1자 이상 500자 이하로 작성해주세요!");
      }

      if (state.file.length && !state.imgURL.length) {
        return alert("파일 업로드를 먼저 해주세요!");
      }
      axios({
        method: "patch",
        url: "https://i5a408.p.ssafy.io:8080/board/update",
        data: {
          no: state.no,
          title: state.title,
          contents: state.contents,
          files: state.imgURL,
        },
      })
        .then((res) => {
          console.log(res);
          alert("등록되었습니다.");
          router.push("/community");
        })
        .catch((err) => {
          console.log(err);
          alert("에러가 발생했습니다.");
        });
    };
    return { state, registerBoard, handleFileUpload, upload, file };
  },
};
</script>
<style></style>
