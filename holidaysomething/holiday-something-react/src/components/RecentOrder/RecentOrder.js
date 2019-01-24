import React, { Component } from 'react';
import styled from 'styled-components';

const RecentOrderWrapper = styled.div`

`;



class RecentOrder extends Component {
  render() {
    const { user } = this.props;

    return (
      <RecentOrderWrapper>
        <h2>최근 주문</h2>
        <table>
          <thead>
            <tr>
              <th>주문일</th>
              <th>주문내역</th>
              <th>주문번호</th>
              <th>결제금액</th>
            </tr>
          </thead>
          <tbody>
            {user.orders.map((order) => {
              return (
                <tr key={order.id}>
                  <td>{order.date}</td>
                  <td></td>
                  <td>{order.orderNumber}</td>
                  <td>{order.totalPrice}</td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </RecentOrderWrapper>
    );
  }
}

export default RecentOrder;