<template>
<<<<<<< HEAD
  <div class="Jbgc d-flex justify-content-center align-items-center mb-5" style="height: 230px;">
    <div class="container">
      <div class="row justify-content-center mb-5">
        <div class="font-bold mt-5" style="font-size:40px">실시간 라이브</div>
=======
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>라이브 생성</h1>
        <div class="form-group mb-5">
          <p>
            <label>기관 이름</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>라이브 이름</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              방 생성!
            </button>
          </p>
        </div>
>>>>>>> d8bf42f77f3518f77ddd35c450600438e44f6acc
      </div>
    </div>
  </div>

<<<<<<< HEAD
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
=======
    <div id="session" v-if="session">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video
          :stream-manager="publisher"
          @click="updateMainVideoStreamManager(publisher)"
        />
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"
        />
>>>>>>> d8bf42f77f3518f77ddd35c450600438e44f6acc
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

<<<<<<< HEAD
      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {
          Bucket: state.albumBucketName,
        },
=======
      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
>>>>>>> d8bf42f77f3518f77ddd35c450600438e44f6acc
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
