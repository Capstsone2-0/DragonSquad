import React, { useState, useEffect } from 'react';
import MyList from './list';
import './Ledger.css';

const Ledger = () => {
  const [titleName, setTitleName] = useState("All Transaction");
  const [selectedOption, setSelectedOption] = useState("all");
  const [listsOfTransaction, setListsOfTransaction] = useState([]);
  console.log(listsOfTransaction);

  useEffect(() => {
    // Fetch lists when selectedOption changes
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/${selectedOption}`);
        const data = await response.json();
        setListsOfTransaction(data); // Assuming API returns an array of transactions
      } catch (error) {
        console.error('API Error:', error);
      }
    };

    fetchData();
  }, [selectedOption]);

  const handleOptionChange = (e) => {
    const selectedValue = e.target.value;
    setSelectedOption(selectedValue);
   if (selectedValue === "deposit") {
  setTitleName("Deposit Transactions");
}
if (selectedValue === "payment") {
  setTitleName("Payment Transactions");
}

  };

  return (
    <div>
      <div className="title">
        Welcome 👋 <br />to my Accounting Ledger
      </div>
      <div className='contentsOfLedger'>
        <div className='first_part'>
          <h2>{titleName}</h2>
          <div className='selection'>
            <label htmlFor="transactionType">Choose Option</label>
            <select
              id="transactionType"
              name="transactionType"
              value={selectedOption}
              onChange={handleOptionChange}
            >
              <option value="all">All transactions</option>
              <option value="deposit">Deposit transactions</option>
              <option value="payment">Payment Transactions</option>
            </select>
          </div>
        </div>

        <div className= 'displayingLists'>
          <div className='headerPart'>
            <li className='list-item'>Status</li>
            <li className='list-item'>Date</li>
            <li className='list-item'>Time</li>
            <li className='list-item'>Vendor</li>
            <li className='list-item'>Description</li>
            <li className='list-item'>Amount</li>
          </div>
          <div className='listPart'>
            {listsOfTransaction.map((transaction, index) => (
              <MyList key={index} transaction={transaction} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Ledger;
