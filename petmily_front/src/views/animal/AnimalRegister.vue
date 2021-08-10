<template>
  <JumbotronAnimalRegister/>
  <div class="container">
    <div class="mb-5 d-flex justify-content-center">
      <div>
        <label class="d-flex flex-row mb-2 semibold">프로필 사진 등록</label>
        <div class="justify-content-center mb-2">
          <input class="mb-2" id="file-selector" ref="profile" type="file" @change="handleProfileUpload()" style="width: 323.85px"/>
          <button @click="profileUpload" class="btn-up" style="color: #FFFFFF; height: 38px; width: 66.38px;" flat>업로드</button>
          <div class="d-flex justify-content-start" v-if="state.profileURL">
            <div>
              업로드 완료!
            </div>
          </div>
        </div>
        <label class="d-flex flex-row mb-2 semibold">사진 또는 동영상 등록</label>
        <div class="justify-content-center mb-2">
          <input multiple="multiple" class="mb-2" id="file-selector" ref="file" type="file" @change="handleFileUpload()" style="width: 323.85px"/>
          <button @click="upload" class="btn-up" style="color: #FFFFFF; height: 38px; width: 66.38px;" flat>업로드</button>
          <div class="d-flex justify-content-start" v-if="state.imgURL.length">
            <div>
              업로드 완료!
            </div>
          </div>
        </div>
          
        <label class="d-flex flex-row mb-2 semibold">종류</label>
        <input
          v-model="state.type"
          class="form-control radius-border mb-2"
          type="text"
          placeholder="ex) 강아지, 고양이"
        />
        <label class="d-flex flex-row mb-2 semibold">종</label>
        <input
          v-model="state.species"
          class="form-control radius-border mb-2"
          type="text"
          placeholder="ex) 말티즈, 시바"
        />
        <label class="d-flex flex-row mb-2 semibold">나이</label>
        <input
          v-model="state.age"
          class="form-control radius-border mb-3"
          type="text"
          placeholder="ex) 3"
        />
        <div class="d-flex justify-content-between mb-3">
          <div class="semibold">
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
          <div class="semibold">
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
        <label class="d-flex flex-row mb-2 semibold">발견 장소</label>
        <input
          v-model="state.addr"
          class="form-control radius-border mb-2"
          type="text"
          placeholder="발견 주소"
        />
        <!-- 임시로 특이사항에 agencycode 받도록 해놓음 -->
        <label class="d-flex flex-row mb-2 semibold">특이사항</label>
        <input
          v-model="state.text"
          class="form-control radius-border mb-2"
          type="text"
          placeholder="특이사항"
          style="height: 76px;"
        />
        <label class="d-flex flex-row mb-2 semibold">발견 날짜</label>
        <input
          v-model="state.find_date"
          class="form-control radius-border mb-5"
          type="datetime-local"
          placeholder="발견 날짜"
        />
        <button class="btn-white" type="button" @click="confirmAnimalRegister">
          등록하기
        </button>
      </div>
    </div>
  </div>
  
</template>
<script>
import { ref, reactive } from "vue"
import AWS from "aws-sdk"
import axios from "axios"
import { useRouter } from "vue-router"
import JumbotronAnimalRegister from '@/views/animal/components/JumbotronAnimalRegister'


// 기관번호 자동으로 받아오는 방법
// postman에서 get으로 되어있는 주소로 jwt 토큰 값을 보내면 유저의 객체가 온다
// 그 객체 안에 기관번호가 담겨있다.
export default {
  components: { JumbotronAnimalRegister },
  name: "AnimalRegister",
  setup() {
    const setToken = function () {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };
    const file = ref(null)
    const profile = ref(null)

    const state = reactive({
      type: "",
      species: "",
      addr: "",
      age: "",
      sexToggle: "",
      neuteredToggle: "",
      find_date: "",
      userno: "",
      agencycode: "",
      text: "",
      animalno: "",

      profile: "",
      file: [],
      albumBucketName: "petmily",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:50493919-440f-47aa-8403-c78182e3ed3e",
      profileURL: "",
      imgURL: [],
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
      
      // 기관정보 받아오기
      axios({
        method: "get",
        url: "http://localhost:8080/users/agency/me",
        headers: {
          Authorization: setToken(),
        }
      })
      .then(res => {
        console.log(res.data)
        state.agencycode = res.data.agencycode
      })
      .catch(err => {
        console.log(err)
      })

      axios({
        method: "post",
        url: "http://localhost:8080/animal/register",
        data: {
          type: state.type,
          species: state.species,
          find_addr: state.addr,
          age: state.age,
          sex: state.sexToggle,
          neutered: state.neuteredToggle,
          find_date: state.find_date,
          agencycode: state.agencycode,
          text: state.text,
          profile_img: state.profileURL,
        },
      })
        .then((res) => {
          console.log(res.data);
          router.push("/animalregistersuccess");
        })
        .catch((err) => {
          console.log(err);
        });

        axios({
          method: "post",
          url: "http://localhost:8080/animal/file",
          data: {
            file: state.imgURL
          }
        })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    

    // 이미지 업로드 구문 ///
    const handleProfileUpload = function() {
      state.profile = profile.value.files[0];
    };

    const handleFileUpload = function() {
      file.value.files.forEach( function (element) {
        state.file.push(element)
      })
    };

    const upload = function() {
      // db에 있는 마지막 animalno 받아오는 부분
      // axios({
      //   method: "",
      //   url: "",
      // })
      // .then(res => {
      //   state.animalno = res.data.animalno ++
      // })
      // .catch(err => {
      //   console.log(err)
      // })
      // // animalno를 보내주는 부분
      // axios({
      //   method: "post",
      //   url: "http://localhost:8080/animal/register",
      //   data: {
      //     animalno: state.animalno,
      //   }
      // })
      // .then(res => {
      //   console.log(res.data)
      // })
      // .catch(err => {
      //   console.log(err)
      // })

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
      let photoKey = []
      state.file.forEach( function (file) {
        photoKey.push(file.name)
      })
      
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
          state.profileURL= data.Location;
        }
      );
    
    };

    return {
      state,
      confirmAnimalRegister,
      handleProfileUpload,
      handleFileUpload,
      upload,
      file,
      profile,
      profileUpload,
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
.semibold {
  font-weight: 600;
}
</style>