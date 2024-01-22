import React from 'react';
import depositIcon from '../../Assets/images/Sort Down.png';
import paymentIcon from '../../Assets/images/Sort Up.png';

const MyList = ({ transaction }) => {
  const {
    date,
    time,
    vendor,
    description,
    amount,
    type, // Assuming your transaction object has a 'transactionType' property
  } = transaction;

  return (
    <div className='itemInList'>
      <img className=''src={type === 'Deposit' ? paymentIcon : depositIcon} alt="icon" />
      <div className='date'>{date}</div>
      <div className='time'>{time}</div>
      <div className='vendor'>{vendor}</div>
      <div className='Description'>{description}</div>
      <div className='amount'>$ {amount}</div>
    </div>
  );
};

export default MyList;
