<template>
  <div id="guardianCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div
        class="carousel-item"
        v-for="(guardian, idx) in guardians"
        :key="idx"
        :class="{ active: idx == 0 }"
      >
        <div class="Jbgc col-4" style="width: 400px">
          <div
            class="card border-light lifecard mb-5"
            align="center"
            style="background-color: #faf9fe"
          >
            <img
              src="@\assets\shelter.png"
              class="card-img-top guardimg"
              align="center"
            />
            <div class="card-body d-flex flex-column justify-content-between">
              <h5 class="card-title font-bold">{{ guardian.agencyname }}</h5>
              <div class="card-text" style="margin-top: 0px">
                {{ guardian.tel }}
              </div>
              <div class="card-text mb-3">{{ guardian.addr }}</div>
              <button
                class="btn-login"
                type="button"
                style="color: #ffffff"
                @click="goToShleterDetail(guardian.agencycode)"
              >
                Read More
              </button>
            </div>
          </div>
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#guardianCarousel"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#guardianCarousel"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
      </button>
    </div>
    <br />
    <br />
    <br />
  </div>
</template>

<script>
import { useRouter } from "vue-router";
export default {
  props: {
    guardians: Array,
  },
  setup(props) {
    const router = useRouter();
    const goToShleterDetail = (agencycode) => {
      router.push({
        name: "shelterdetail",
        params: {
          agencycode: agencycode,
        },
      });
    };
    return { goToShleterDetail };
  },
};
</script>

<style>
.font-bold {
  font-weight: bold;
}

.Jbgc {
  background-color: #faf9fe;
}

.btn-login {
  width: 100%;
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

.guardimg {
  width: 100%;
  height: 50%;
  clip-path: circle(30% at 50% 50%);
}

.lifecard {
  width: 80%;
  height: 450px;
}
</style>
