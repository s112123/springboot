// kakao map
const container = document.getElementById('map');
const options = {
  center: new kakao.maps.LatLng(33.450701, 126.570667),
  level: 3
};

const map = new kakao.maps.Map(container, options);

// 주소로 좌표 검색
const geocoder = new kakao.maps.services.Geocoder();
const storeAddress = document.getElementById('store-address').innerText;

geocoder.addressSearch(storeAddress, function(result, status) {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료된 경우
    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시
    var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });

    // 지도의 중심을 결과값으로 받은 위치로 이동
    map.setCenter(coords);
  }
});
