/* kakao map 연동 API */
function sample4_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var roadAddr = data.roadAddress; // 도로명 주소 변수
      var extraRoadAddr = ''; // 참고 항목 변수

      // 법정동명이 있을 경우 추가한다. (법정리는 제외)
      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
      if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
        extraRoadAddr += data.bname;
      }
      // 건물명이 있고, 공동주택일 경우 추가한다.
      if(data.buildingName !== '' && data.apartment === 'Y'){
        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
      }
      // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
      if(extraRoadAddr !== ''){
        extraRoadAddr = ' (' + extraRoadAddr + ')';
      }


      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('orderRecipientPostcodeInput').value = data.zonecode;
      document.getElementById("orderRecipientAddress1Input").value = roadAddr;
      document.getElementById("orderRecipientAddress1Input").value = data.jibunAddress;

      var guideTextBox = document.getElementById("guide");
      // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
      if(data.autoRoadAddress) {
        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
        guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
        guideTextBox.style.display = 'block';

      } else if(data.autoJibunAddress) {
        var expJibunAddr = data.autoJibunAddress;
        guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
        guideTextBox.style.display = 'block';
      } else {
        guideTextBox.innerHTML = '';
        guideTextBox.style.display = 'none';
      }

      // 기존에 입력되어있던 address2 (상세주소) 를 지워준다.
      let detailedAddress = document.querySelector('#orderRecipientAddress2Input');
      detailedAddress.setAttribute("value", "");
      detailedAddress.setAttribute("placeholder", "상세주소를 입력해주세요.");
    }
  }

  ).open();
}

/* 배송정보 Input에 이미 존재하던 text를 지워주는 API */
let removeInputText = function () {
  //수령인 삭제
  let removeOrderRecipientNameInput = document.querySelector('#orderRecipientNameInput');
  removeOrderRecipientNameInput.setAttribute("value","");

  let removeOrderRecipientPostcode = document.querySelector('#orderRecipientPostcodeInput');
  removeOrderRecipientPostcode.setAttribute("value","");

  let removeOrderRecipientAddress1 = document.querySelector('#orderRecipientAddress1Input');
  removeOrderRecipientAddress1.setAttribute("value","");

  let removeOrderRecipientAddress2 = document.querySelector('#orderRecipientAddress2Input');
  removeOrderRecipientAddress2.setAttribute("value","");

  let removeOrderRecipientPhoneInput = document.querySelector('#orderRecipientPhoneInput');
  removeOrderRecipientPhoneInput.setAttribute("value","");
};

/* 모든 마일리지 사용 / 마일리지 사용 안함 api */
let useAllMileage = function() {
  let btnName = document.getElementById("btnUseAllMileage");

  if (btnName.value === "모두사용"){
    btnName.value = "사용안함";
  } else {
    btnName.value = "모두사용";
  }

  let jsUseMileageInput = document.getElementById("orderMemberUseMileage");
  let jsHasMileage = document.getElementById("orderMemberHasMileage");
  let jsTotalPayment = document.getElementById("orderTotalPayment");
  let jsTotalProductPrice = document.getElementById("orderTotalProductPrice");
  let jsTotalShippingPrice = document.getElementById("orderTotalShippingPrice");

  let jsUseMileageInputValue = jsUseMileageInput.value;
  let jsHasMileageValue = jsHasMileage.innerText;
  let jsTotalProductPriceValue = parseInt(jsTotalProductPrice.innerText);
  let jsTotalShippingPriceValue = parseInt(jsTotalShippingPrice.innerText);

  jsUseMileageInput.value = jsHasMileageValue;
  jsHasMileage.innerText = jsUseMileageInputValue;

  document.getElementById("orderTotalUseMileage").innerText = jsHasMileageValue;
  jsTotalPayment.innerText = jsTotalProductPriceValue + jsTotalShippingPriceValue - parseInt(jsUseMileageInput.value);
};

/* 마일리지 부분사용 api */
let RecalcuSubTotal = function(source) {
  let jsUseMileageInput = document.getElementById("orderMemberUseMileage");
  //불변의 총 마일리지
  let jsHiddenMileage = document.getElementById("hiddenMileage");
  //변화하는 총 마일리지
  let jsHasMileage = document.getElementById("orderMemberHasMileage");
  let jsTotalUseMileage = document.getElementById("orderTotalUseMileage");

  //불변의 총 계산금액
  let jsHiddenTotalPayment = document.getElementById("hiddenTotalPayment");
  //변화하는 총 계산금액
  let jsTotalPayment = document.getElementById("orderTotalPayment");

  //밸류값은 무조건 integer
  let jsUseMileageInputValue = parseInt(jsUseMileageInput.value);
  let jsOriginHasMileageValue = parseInt(jsHiddenMileage.innerText);
  let jsHiddenTotalPaymentValue =  parseInt(jsHiddenTotalPayment.innerText);

  if(isNaN(jsUseMileageInput.value)){
    alert("nan이 싫어요 ㅠ");
    jsUseMileageInput.setAttribute("value", "0");
  }

  if (jsUseMileageInputValue > jsOriginHasMileageValue){
    jsUseMileageInput.value = jsOriginHasMileageValue;
    jsTotalUseMileage.innerText = jsHiddenMileage.innerText;
    jsTotalPayment.innerText = jsHiddenTotalPaymentValue - jsOriginHasMileageValue;
  } else {
    //변화하는 마일리지 = 불변의 마일리지 - 입력값을 뺀 것
    jsHasMileage.innerText = (jsOriginHasMileageValue - jsUseMileageInputValue);
    jsTotalUseMileage.innerText = jsUseMileageInputValue;
    jsTotalPayment.innerText = jsHiddenTotalPaymentValue - jsUseMileageInputValue;

  }
};