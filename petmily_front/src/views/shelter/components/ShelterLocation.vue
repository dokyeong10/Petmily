<template>
  <br>
  <br>
  <div >
    <h2>오시는 길</h2>
    <div class="d-flex mx-auto justify-content-center" style="width:1000px;height:450px;">
      <div class="" id="map" style="width:500px;height:400px;"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'ShelterLocation',
  props: {
    location: Object
  },
  mounted() { 
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods : {
    initMap() {
      const container = document.getElementById('map');
      const options = { center: new kakao.maps.LatLng(this.location.lat, this.location.lng), level: 3 };
      const map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      const marker = new kakao.maps.Marker({ position: map.getCenter() });
      marker.setMap(map); 
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b22c5ac8dfbf7a1a5b88a66c9907591d';
      document.head.appendChild(script);
    }
  }
}

</script>
<style>

</style>