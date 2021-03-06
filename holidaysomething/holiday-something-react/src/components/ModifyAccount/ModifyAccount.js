import React, { Component } from 'react';
import Postcode from '../Postcode/Postcode';
import DaumPostcode from 'react-daum-postcode';
// import SearchMap from '../SearchMap/SearchMap';

class ModifyAccount extends Component {
  constructor() {
    super();
    this.state = {
      modifyEmailBtnText: '이메일 변경',
      isModifyEmailHidden: true,
      modifyPhoneBtnText: '휴대폰번호 변경',
      isModifyPhoneHidden: true,
    };
  }

  toggleModifyEmail = () => {
    this.setState(prevState => ({
      ...prevState,
      isModifyEmailHidden: !prevState.isModifyEmailHidden,
      modifyEmailBtnText: (prevState.modifyEmailBtnText === '이메일 변경') ? '이메일변경 취소' : '이메일 변경',
    }));
  };

  toggleModifyPhone = () => {
    this.setState(prevState => ({
      ...prevState,
      isModifyPhoneHidden: !prevState.isModifyPhoneHidden,
      modifyPhoneBtnText: (prevState.modifyPhoneBtnText === '휴대폰번호 변경') ? '변경 취소' : '휴대폰번호 변경',
    }));
  };

  render() {
    const { user } = this.props;
    
    let phone = user.phone.split("-");
    let phoneMiddle = phone[1];
    let phoneEnd = phone[2];

    return (
      <div>
        <section className="my-modify">
          <h3>회원정보 수정</h3>

          <ul className="tbl-list">
            <li className="tbl-tr tbl-tr-id">
              <div className="tbl-th">
                <strong>아이디</strong>
              </div>
              <div className="tbl-td">
                <p>{user.loginId}</p>
              </div>
            </li>
            <li className="tbl-tr">
              <div className="tbl-th">
                <strong>이메일</strong>
              </div>
              <div className="tbl-td">
                <div className="tbl-td-row">
                  <p>{user.email}</p>
                  <ModifyEmailBtn 
                    handleClick={this.toggleModifyEmail}
                    text={this.state.modifyEmailBtnText} 
                  />
                </div>
                {/* 이메일 변경 form */}
                {!this.state.isModifyEmailHidden && <ModifyEmailDiv />}
              </div>
            </li>
            <li className="tbl-tr tbl-tr-password">
              <div className="tbl-th">
                <strong>비밀번호 변경</strong>
              </div>
              <div className="tbl-td">
                <form>
                  <div className="tbl-td-row">
                    <div>
                      <div className="input-box">
                        <label htmlFor="user_password">현재 비밀번호</label>
                        <input type="password" id="user_password" placeholder="현재 비밀번호" />
                      </div>
                      <div className="input-box">
                        <label htmlFor="new_password">신규 비밀번호</label>
                        <input type="password" id="new_password" placeholder="신규 비밀번호" />
                      </div>
                      <div className="input-box">
                        <label htmlFor="confirm_password">신규 비밀번호 확인</label>
                        <input type="password" id="confirm_password" placeholder="신규 비밀번호 확인" />
                      </div>
                    </div>
                    <button className="btn-black">비밀번호 변경</button>
                  </div>
                </form>
              </div>
            </li>
            <li className="tbl-tr tbl-tr-name">
              <div className="tbl-th">
                <strong>이름</strong>
              </div>
              <div className="tbl-td">
                <p>{user.name}</p>
              </div>
            </li>
            <li className="tbl-tr tbl-tr-phone">
              <div className="tbl-th">
                <strong>휴대폰 번호</strong>
              </div>
              <div className="tbl-td">
                {/* 기존 휴대폰 정보 보여주는 부분 */}
                <div className="hp-in">
                  <div className="phone-field">
                    <div className="input-box">
                      <input disabled placeholder="010" />
                    </div>
                    <div className="dash">-</div>
                    <div className="input-box">
                      <input disabled maxLength="4" type="tel" placeholder={phoneMiddle} />
                    </div>
                    <div className="dash">-</div>
                    <div className="input-box">
                      <input disabled maxLength="4" type="tel" placeholder={phoneEnd} />
                    </div>
                  </div>
                  <ModifyPhoneBtn
                    handleClick={this.toggleModifyPhone}
                    text={this.state.modifyPhoneBtnText} 
                  />
                </div>
                {/* 휴대폰 변경 form */}
                {!this.state.isModifyPhoneHidden && <ModifyPhoneDiv />}
              </div>
            </li>
            <li className="tbl-tr tbl-tr-addr">
              <div className="tbl-th">
                <strong>주소</strong>
              </div>
              <div className="tbl-td">
                <div className="in-row">
                  <div className="zip-box">
                    <div className="box">{user.postcode}</div>
                    <button className="btn-white" type="button">우편번호 검색</button>
                  </div>
                </div>
                <DaumPostcode />
                {/* <SearchMap /> */}

                <div className="in-half">
                  <div className="input-box">
                    <input disabled type="text" placeholder={user.address1} />
                    {/* '우편번호 검색' 버튼 */}
                  </div>
                  <div className="input-box">
                    <input type="text" defaultValue={user.address2} />
                  </div>
                  <p className="info">주소를 수정할 경우 '기본배송지정보' 도 추가됩니다.</p>
                </div>
              </div>
            </li>
            <li className="tbl-tr tbl-tr-birth">
              <div className="tbl-th">
                <strong>생일</strong>
              </div>
              <div className="tbl-td">
                <form>
                  <div className="in-row">
                    <span className="input-box">
                      <input name="birth-year" defaultValue={user.birthday[0]} />
                    </span>
                    <span className="split">년</span>
                    <span className="input-box">
                      <input name="birth-year" defaultValue={user.birthday[1]} />
                    </span>
                    <span className="split">월</span>
                    <span className="input-box">
                      <input name="birth-year" defaultValue={user.birthday[2]} />
                    </span>
                    <span className="split">일</span>
                  </div>
                </form>
              </div>
            </li>
          </ul>

          <div className="btn-box">
            <button className="btn-white" type="reset">취소</button>
            <button className="btn-black" type="submit">저장</button>
          </div>
        </section>
      </div>
    );
  }
}

// '이메일 변경' / '이메일 변경 취소' toggle 버튼
class ModifyEmailBtn extends Component {
  render() {
    return (
      <button className="btn-black" onClick={this.props.handleClick}>
        {this.props.text}
      </button>
    );
  }
}

// 이메일 변경 form (show/hide toggle)
class ModifyEmailDiv extends Component {
  // 이메일 변경 '변경하기' 버튼 눌렀을 때
  submitEmail = () => {
    let email = document.querySelector('#email');
    let emailVal = email.value;
    console.log(`emailVal: ${emailVal}`);
    if (emailVal === '') {
      alert('이메일을 입력하세요.');
      return;
    }
    
    return fetch('/api/user/account', {
      headers: { 
        "Content-Type": "application/json",
      },
      method: 'PATCH',
      body: JSON.stringify({
        email: emailVal,
      }),
    })
    .then(response => console.log(response))
    .then(alert("이메일이 변경되었습니다."))
    .then(window.location.replace("/mypage"))
    .catch(err => console.error(err));
  };

  render() {
    return (
      <div className="edit-box">
        <form>
          <div className="edit-in">
            <span className="input-box">
              <input type="email" id="email" name="email" required />
            </span>

            <input type="button" className="btn-black" value="변경하기" onClick={this.submitEmail.bind(this)}/>
          </div>
        </form>
      </div>
    )
  }
}

// '휴대폰번호변경' 버튼
class ModifyPhoneBtn extends Component {
  render() {
    return (
      <button className="btn-black" type="button" onClick={this.props.handleClick}>
        {this.props.text}
      </button>
    );
  }
}

// 휴대폰 변경 form (show/hide toggle)
class ModifyPhoneDiv extends Component {
  // 휴대폰번호 변경 '변경하기' 버튼 눌렀을 때
  submitPhone = () => {
    let editBox = document.querySelector('.edit-box');
    let phone1select = document.getElementsByClassName('user-cell-first')[0].getElementsByTagName('select');
    let phone1 = phone1select[0].options[phone1select[0].selectedIndex].value;
    let phone2 = editBox.querySelectorAll('.input-box')[0].firstChild.value;
    let phone3 = editBox.querySelectorAll('.input-box')[1].firstChild.value;
    console.log(`Modifying phone number to: ${phone1}-${phone2}-${phone3}`);
    if (phone1 === '' || phone2 === '' || phone3 === '') {
      alert('휴대폰번호를 입력하세요.');
      return;
    }
    const phone = `${phone1}-${phone2}-${phone3}`;

    return fetch('/api/user/account', {
      headers: {
        "Content-Type": "application/json",
      },
      method: 'PATCH',
      body: JSON.stringify({
        phone: phone,
      }),
    })
    .then(response => console.log(response))
    .then(alert("휴대폰번호가 변경되었습니다."))
    .then(window.location.replace("/mypage"))
    .catch(err => console.error(err));
  };

  render() {
    return (
      <div className="edit-box">
        <div className="hp-in">
          <form>
            <div className="phone-field">
              <div className="user-cell-first">
                <select>
                  <option value="010">010</option>
                  <option value="011">011</option>
                  <option value="016">016</option>
                  <option value="017">017</option>
                  <option value="018">018</option>
                  <option value="019">019</option>
                </select>
              </div>
              <div className="dash">-</div>
              <div className="input-box">
                <input type="tel"/>
              </div>
              <div className="dash">-</div>
              <div className="input-box">
                <input type="tel"/>
              </div>
              <input type="button" className="btn-black" value="변경하기"
                     onClick={this.submitPhone.bind(this)}/>
            </div>
          </form>
        </div>
      </div>
    )
  }
}

export default ModifyAccount;