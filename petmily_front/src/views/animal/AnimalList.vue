<template>
  <div>
    <div class="Jbgc mb-5" style="height: 300px">
      <div class="container" style="height: 350px; max-width: 800px">
        <div class="d-flex flex-column">
          <div class="d-flex flex-column align-items-start mt-5">
            <h2>유기동물 목록</h2>
            <h5>당신의 따뜻한 품을 기다리고 있습니다.</h5>
          </div>
          <br />
          <div class="d-flex align-items-start">
            <div class="d-flex flex-column align-items-start">
              <label for="filter">필터</label>
              <select
                name="filter"
                id="filter"
                v-model="state.key"
                style="height: 30px; margin-right: 10px"
              >
                <option value="" selected>선택</option>
                <!-- <option value="all">전체목록</option> -->
                <option value="type">품종</option>
                <option value="addr">지역</option>
                <option value="agencyname">보호소</option>
              </select>
            </div>
            <div name="search">
              <div class="d-flex flex-column align-items-start">
                <label for="search">검색</label>
                <input
                  type="text"
                  v-model="state.word"
                  placeholder="검색어를 입력하세요."
                  @keyup.enter="search"
                />
              </div>
            </div>
            <button class="btn-up" @click="search" style="height: 30px; margin-top: 24px">
              검색
            </button>
            <div v-if="isLogin && !isAgency" class="mt-4 ms-4">
              <label for="isLike">즐겨찾기한 동물</label>
              <input type="checkbox" class="ms-1" v-model="state.isLike" @click="getFavoriteOnly" />
            </div>
          </div>
        </div>
        <div class="d-flex align-items-end flex-column-reverse">
          <div v-if="isAgency">
            <button
              @click="goToAnimalRegister"
              class="btn-detail mt-3 mb-auto p-2"
              type="button"
              style="height: 35px; width: 130px"
            >
              동물 등록 하기
            </button>
          </div>
        </div>
      </div>
    </div>
    <br />
    <div class="container">
      <div class="row">
        <div class="col-lg-6" v-for="(animal, no) in state.data" :key="no">
          <div
            v-if="
              no < state.numberOfItems * state.page && no >= state.numberOfItems * (state.page - 1)
            "
          >
            <div class="card mb-3" style="max-width: 636px;">
              <div class="row g-0">
                <div class="col-md-4">
                  <img
                    :src="animal.profile_img"
                    onerror="this.src='https://petmily.s3.ap-northeast-2.amazonaws.com/PetmilyLogo.png'"
                    class="m-4"
                    style="width: 200px; height: 200px; border-radius: 12px"
                    alt="..."
                  />
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <div class="d-flex justify-content-center">
                      <h5 class="card-title mb-0 me-2 mt-1">{{ animal.type }}</h5>
                      <div v-if="isLogin && !isAgency" class="card-text">
                        <div v-if="favoriteFilter(animal)">
                          <font-awesome-icon
                            :icon="['fas', 'heart']"
                            id="myDiv"
                            style="color: red; font-size: 25;"
                            @click="cancelFavorite(animal)"
                          />
                        </div>
                        <div v-else>
                          <font-awesome-icon
                            :icon="['far', 'heart']"
                            id="myDiv"
                            style="color: red; font-size: 25;"
                            @click="addToFavorite(animal)"
                          />
                        </div>
                      </div>
                    </div>
                    <p class="card-text mt-3">{{ animal.species }}</p>
                    <p class="card-text">{{ animal.text }}</p>
                    <p class="card-text">
                      <small class="text-muted"
                        >{{ animal.find_date.substring(0, 10) }}
                        {{ animal.find_date.substring(11, 19) }}</small
                      >
                    </p>

                    <button class="btn-detail mb-3" style="height: 35px" @click="goDetail(animal)">
                      상세보기
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="d-flex justify-content-center">
    <a id="myA" v-if="state.page > 1" @click="pageDown">Prev</a>
    <div v-for="(n, idx) in state.numberOfPages" :key="idx">
      <div v-if="n <= state.page - (state.page % 10) + 10 && n > state.page - (state.page % 10)">
        <div v-if="state.page == n">
          <a style="font-weight: 600">{{ n }}</a>
        </div>
        <div v-else>
          <a id="myA" @click="goToPage(n)">{{ n }}</a>
        </div>
      </div>
    </div>
    <a id="myA" v-if="state.data.length - state.numberOfItems * state.page > 0" @click="pageUp"
      >Next</a
    >
  </div>
  <br>
  <br>
</template>
<script>
import { reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { computed } from "@vue/runtime-core";
import axios from "axios";

export default {
  name: "animallist",
  setup() {
    onMounted(() => {
      axios({
        method: "post",
        url: "http://localhost:8080/animal/",
        data: {
          key: "all",
          word: "",
          isLike: state.isLike,
          no: state.userno,
        },
      })
        .then((res) => {
          state.data = res.data;
          state.numberOfItems = 4;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    });

    const getUserInfo = function() {
      const no = localStorage.getItem("userno");
      return no;
    };
    const getFavoriteInfo = function() {
      axios({
        method: "get",
        url: `http://localhost:8080/users/like/${localStorage.getItem("userno")}`,
      })
        .then((res) => {
          state.favoriteData = res.data;
          state.favoriteArray = [];
          state.favoriteData.forEach((element) => {
            if (!state.favoriteArray.includes(element.animalno)) {
              state.favoriteArray.push(element.animalno);
            }
          });
          state.favoriteArray.sort(function(a, b) {
            return a - b;
          });
        })
        .catch((err) => {
          console.log(err);
        });
    };
    getFavoriteInfo();

    const router = useRouter();
    const state = reactive({
      key: "",
      word: "",
      userno: getUserInfo(),
      // isClickedSearch: false,
      data: {},
      favoriteData: {},
      favoriteArray: [],
      tempFavoriteNumber: "",
      numberOfItems: 4,
      page: 1,
      numberOfPages: 0,
      isLike: false,
    });

    const getAll = function() {
      axios({
        method: "post",
        url: "http://localhost:8080/animal/",
        data: {
          key: "all",
          word: "",
          isLike: false,
          no: state.userno,
        },
      })
        .then((res) => {
          state.data = res.data;
          state.numberOfItems = 4;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    };
    const getFavoriteOnly = function() {
      if (state.isLike) {
        getAll();
      } else {
        axios({
          method: "post",
          url: "http://localhost:8080/animal/",
          data: {
            key: state.key,
            word: state.word,
            isLike: state.isLike,
            no: state.userno,
          },
        }).then((res) => {
          console.log(state.isLike);
          state.page = 1;
          state.data = [];
          res.data.forEach((element) => {
            if (state.favoriteArray.includes(element.no)) state.data.push(element);
            state.data.sort(function(a, b) {
              if (a.no < b.no) return -1;
              if (a.no > b.no) return 1;
              return 0;
            });
          });
          state.numberOfItems = 4;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }
        });
      }
    };

    const goToAnimalRegister = function() {
      router.push("/animalregister");
    };

    const isAgency = computed(() => {
      return sessionStorage.getItem("isAgency");
    });

    const isLogin = computed(() => {
      return sessionStorage.getItem("isLogin");
    });
    const search = function() {
      axios({
        method: "post",
        url: "http://localhost:8080/animal/",
        data: {
          key: state.key,
          word: state.word,
          isLike: state.isLike,
          no: state.userno,
        },
      })
        .then((res) => {
          state.data = res.data;
          if (state.data.length % state.numberOfItems) {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) /
                state.numberOfItems +
              1;
          } else {
            state.numberOfPages =
              (state.data.length - (state.data.length % state.numberOfItems)) / state.numberOfItems;
          }
        })
        .catch((err) => {
          console.log(err);
        });
      // state.isClickedSearch = true;
    };

    const addToFavorite = function(animal) {
      axios({
        method: "post",
        url: "http://localhost:8080/users/like",
        data: {
          animalno: animal.no,
          userno: state.userno,
        },
      })
        .then((res) => {
          getFavoriteInfo();
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const cancelFavorite = function(animal) {
      state.favoriteData.forEach((element) => {
        if (element.animalno === animal.no) {
          state.tempFavoriteNumber = element.no;
        }
      });
      axios({
        method: "delete",
        url: `http://localhost:8080/users/like/${state.tempFavoriteNumber}`,
      })
        .then((res) => {
          console.log(res);
          getFavoriteInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const pageUp = function() {
      state.page++;
    };
    const pageDown = function() {
      state.page--;
    };
    const goToPage = function(n) {
      state.page = n;
    };
    const goDetail = function(animal) {
      router.push({
        name: "animaldetail",
        params: {
          id: animal.no,
        },
      });
    };

    const favoriteFilter = function(animal) {
      if (state.favoriteArray.includes(animal.no)) {
        return true;
      } else {
        return false;
      }
    };

    return {
      state,
      goToAnimalRegister,
      isAgency,
      isLogin,
      search,
      getUserInfo,
      addToFavorite,
      cancelFavorite,
      pageUp,
      pageDown,
      goToPage,
      goDetail,
      getFavoriteInfo,
      getAll,
      getFavoriteOnly,
      favoriteFilter,
    };
  },
};
</script>
<style>
.Jbgc {
  background-color: #faf9fe;
}
#myDiv {
  cursor: pointer;
}
#myDiv:hover {
  font-weight: 600;
}
.semibold {
  font-weight: 600;
}
#myA {
  cursor: pointer;
}
#myA:hover {
  text-decoration: underline;
}
.btn-detail {
  width: 40%;
  height: 50%;
  background-color: #a4b5f0;
  border-right: #a4b5f0 1px solid;
  border-left: #a4b5f0 1px solid;
  border-top: #a4b5f0 1px solid;
  border-bottom: #a4b5f0 1px solid;
  border-style: none;
  border-radius: 30px;
  color: white;
}
</style>
