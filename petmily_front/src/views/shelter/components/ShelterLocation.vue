<template>
  <br />
  <br />
  <div>
    <h2>오시는 길</h2>
    <div class="d-flex mx-auto justify-content-center" style="width:1000px;height:450px;">
      <div class="" id="map" style="width:900px;height:400px;"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ShelterLocation",
  props: {
    location: Object,
    agencyInfo: Object,
  },
  mounted() {
    if (window.kakao && window.kakao.map) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b22c5ac8dfbf7a1a5b88a66c9907591d";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.location.lat, this.location.lng), //, 지도의 중심좌표
        level: 4,
      };
      const map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.

      var imageSrc = "https://image.flaticon.com/icons/png/512/5015/5015093.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(55, 60), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(this.location.lat, this.location.lng); // 마커가 표시될 위치입니다// 마커를 생성합니다

      var marker = new kakao.maps.Marker({
        position: markerPosition,
        image: markerImage, // 마커이미지 설정
      });

      marker.setMap(map);

      // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
      var content =
        '<div class="customoverlay">' +
        '<a href="https://map.kakao.com/link/to/' +
        this.agencyInfo.agencyname +
        "," +
        this.location.lat +
        "," +
        this.location.lng +
        '" <a href="https://map.kakao.com/link/to/' +
        this.agencyInfo.agencyname +
        "," +
        this.location.lat +
        "," +
        this.location.lng +
        '" target="_blank"><span class="title">' +
        this.agencyInfo.agencyname +
        "</span></a>" +
        "</div>";

      // // 마커에 커서가 오버됐을 때 마커 위에 표시할 윈도우를 생성
      // var iwContent =
      //     '<div style="padding:5px; color: #a4b5f0;">' +
      //     this.agencyInfo.agencyname +
      //     ' <br><a href="https://map.kakao.com/link/map/' +
      //     this.agencyInfo.agencyname +
      //     "," +
      //     this.location.lat +
      //     "," +
      //     this.location.lng +
      //     '" style="color:blue" target="_blank">큰지도로보기</a> <a href="https://map.kakao.com/link/to/' +
      //     this.agencyInfo.agencyname +
      //     "," +
      //     this.location.lat +
      //     "," +
      //     this.location.lng +
      //     '" style="color:blue" target="_blank">길찾기</a></div>',
      //   iwRemoveable = true;

      // 커스텀 오버레이가 표시될 위치입니다
      var position = new kakao.maps.LatLng(this.location.lat, this.location.lng);

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: position,
        content: content,
        yAnchor: 1,
      });
    },
  },
};
</script>
<style>
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
