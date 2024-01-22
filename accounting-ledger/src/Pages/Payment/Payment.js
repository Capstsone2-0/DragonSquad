import React, { useState } from 'react'
import avatarForPayment from '../../Assets/images/Lifesavers Bust.png'

const Deposit = () => {
  const [formData, setFormData] = useState({
    date: '',
    time: '',
    vendor: '',
    description: '',
    amount: '',
  });

  // Handle input changes
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Create a Transactions object from the form data
    const transactionData = {
      id: 0, // You may need to generate a unique ID on the server side
      date: formData.date,
      time: formData.time,
      vendor: formData.vendor,
      description: formData.description,
      amount: parseFloat(formData.amount), // Ensure the amount is a number
      type: 'Payment',
    };

    // Perform API call here using transactionData
    try {
      const response = await fetch('http://localhost:8080/addPayment', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(transactionData),
      });

      // Handle the API response as needed
      console.log('API Response:', response);

      // Optionally, you can reset the form after submission
      setFormData({
        date: '',
        time: '',
        vendor: '',
        description: '',
        amount: '',
      });

    } catch (error) {
      console.error('API Error:', error);
    }
  };

  return (
    <div className="Deposit_Section">
      <div className="title">
        Welcome 👋 <br />to my Accounting Ledger
      </div>
      <div className='contents'>
        <div className='Form'>
          <div className='picture'>
            {/* You can replace the image source with your own */}
            <img className='AvatarPic' src={avatarForPayment} alt='avatar'></img>
            Payment
          </div>
          <div className="input-box">
            <form onSubmit={handleSubmit}>
              <label>
                <input
                  className="get-started-input"
                  type="text"
                  value={formData.vendor}
                  onChange={handleInputChange}
                  placeholder="Enter Vendor"
                  name="vendor"
                />
              </label>
              <br />
              <label>
                <input
                  className="get-started-input"
                  type="text"
                  placeholder="Enter Amount"
                  value={formData.amount}
                  onChange={handleInputChange}
                  name="amount"
                />
              </label>
              <br />
              <label>
                <input
                  className="get-started-input"
                  type="text"
                  placeholder="Enter Description"
                  value={formData.description}
                  onChange={handleInputChange}
                  name="description"
                />
              </label>
              <br />
              <label>
                <input
                  className="get-started-input"
                  type="date"
                  placeholder="Enter time"
                  value={formData.date}
                  onChange={handleInputChange}
                  name="date"
                />
              </label>
              <br />
              <label>
                <input
                  className="get-started-input"
                  type="text"
                  placeholder="Enter Time"
                  value={formData.time}
                  onChange={handleInputChange}
                  name="time"
                />
              </label>
              <br />
              <button className="get-started-submit" type='submit'>
                Save
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Deposit;
