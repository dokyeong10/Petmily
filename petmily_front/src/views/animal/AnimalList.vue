<template>
  <div>
<<<<<<< HEAD
    <AnimalCategory/>
    <AnimalListItem/>
=======
    <div class="Jbgc" style="height: 350px;">
      <div class="d-flex flex-column" style="width: 1200px;">
        <div class="d-flex flex-column align-items-start">
          <h3>유기동물 목록</h3>
          <h5>당신의 따뜻한 품을 기다리고 있습니다.</h5>
        </div>
        <div class="d-flex align-items-start">
          <div class="d-flex flex-column align-items-start">
            <label for="filter">필터</label>
            <select name="filter" id="filter" v-model="state.key" style="height: 30px;">
              <option value="" selected disabled>선택</option>
              <option value="all">전체목록</option>
              <option value="type">품종</option>
              <option value="addr">지역</option>
              <option value="agencyname">보호소</option>
            </select>
          </div>
            <div name="search">
              <div v-if="state.key != 'all'" class="d-flex flex-column align-items-start" >
                <label for="search">검색</label>
                <input type="text" v-model="state.word" placeholder="검색어를 입력하세요."/>
              </div>
            </div>
            <button class="btn-up" @click="search" style="height: 30px; margin-top: 24px;">검색</button>
        </div>
        <div class="d-flex justify-content-start" v-if="!state.key && state.isClickedSearch" style="color: red;">
          필터를 선택해주세요!
        </div>
        <div class="d-flex align-items-end flex-column mb-3">
          <div v-if="isAgency">
            <button @click="goToAnimalRegister" class="mb-auto p-2" type="button">동물 등록 하기</button>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="container" >
      <div class="row">
        <div class="col-md-6" v-for="(animal, no) in state.data" :key="no">
          <div class="card mb-3" style="max-width: 540px;">
            <div class="row g-0">
              <div class="col-md-4">
                <img :src="animal.profile_img" class="img-fluid rounded-start" alt="...">
              </div>
              <div class="col-md-8">
                <div class="card-body">
                  <h5 class="card-title">{{ animal.type }}</h5>
                  <p class="card-text">{{ animal.species }}</p>
                  <p class="card-text">{{ animal.text }}</p>
                  <p class="card-text"><small class="text-muted">{{ animal.find_date.substring(0, 10) }} {{ animal.find_date.substring(11, 19) }}</small></p>
                  <div v-if="isLogin && !isAgency" class="card-text">
                    <div v-if="state.isFavorite">
                      <font-awesome-icon :icon="['fas','heart']" />
                    </div>
                    <div v-else @click="addToFavorite">
                      <font-awesome-icon :icon="['far','heart']" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
>>>>>>> origin/emergency_backup_push_branch
  </div>
</template>
<script>
import AnimalCategory from '@/views/animal/components/AnimalCategory'
import AnimalListItem from '@/views/animal/components/AnimalListItem'

export default {
  name: 'animallist',
<<<<<<< HEAD
  components: {
    AnimalCategory,
    AnimalListItem
=======
  setup () {
    const setToken = function () {
      const token = localStorage.getItem("jwt");
      const config = `Bearer ${token}`;
      return config;
    };
    const getUserInfo = async function () {
      await axios({
        method: "get",
        url: "http://localhost:8080/users/personal/me",
        headers: {
          Authorization: setToken(),
        }
      })
      .then((res) => {
        console.log(1)
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
        console.log(2)
      })
    } 
    getUserInfo()

    const router = useRouter()
    const state = reactive({
      key: "",
      word: "",
      isClickedSearch: false,
      data: {},
      isFavorite: false,
    })
    const goToAnimalRegister = function () {
      router.push('/animalregister')
    }

    const isAgency = computed(() => {
      console.log(sessionStorage.getItem('isAgency'))
      return sessionStorage.getItem('isAgency')
    })
    
    const isLogin = computed(() => {
      return sessionStorage.getItem('isLogin')
    })
    const search = function () {
      axios({
        method: "post",
        url: "http://localhost:8080/animal/",
        data: {
          key: state.key,
          word: state.word,
        }
      })
      .then(res => {
        console.log(res)
        state.data = res.data
      })
      .catch(err => {
        console.log(err)
      })
      state.isClickedSearch = true
    }

    const addToFavorite = () => {
      
    }
    return { state, goToAnimalRegister, isAgency, isLogin, search, addToFavorite, getUserInfo, }
>>>>>>> origin/emergency_backup_push_branch
  }
}

</script>
<style>
  
</style>