// Layout.js
import React, { useState , useEffect} from "react";
import { Outlet, Link, Route, Routes, useNavigate } from "react-router-dom";
import "./Layout.css";

import Deposit from "../Deposit/Deposit";
import Ledger from "../Ledger/Ledger";
import Report from "../Report/Report";
import Payment from "../Payment/Payment";

import depositIcon from "../../Assets/images/Bank.png"
import paymentIcon from "../../Assets/images/Card Payment.png";
import ledgerIcon from "../../Assets/images/Ledger.png"
import reportIcon from "../../Assets/images/Report Card.png" 

function Layout() {
  return (
    <div className="SideLayout">
      <nav>
        <div className="item1">
          Accounting Ledger
        </div>

        <div className="items">
          <Link to="/" className="linkName">Deposit </Link>
          <img src={depositIcon} alt="Deposit" />
        </div>

        <div className="items">
          <Link to="/Payment" className="linkName">Payment </Link>
          <img src={paymentIcon} alt="Payment" />
        </div>

        <div className="items">
          <Link to="/Ledger" className="linkName">Ledger </Link>
          <img src={ledgerIcon} alt="Ledger" />
        </div>

        <div className="items">
          <Link to="/Report" className="linkName">Report </Link>
          <img src={reportIcon} alt="Report" />
        </div>

      </nav>
      <div className="MainPart">
       
        <Routes>
          <Route path="/" element={<Deposit />} />
          <Route path="Payment" element={<Payment />} />
          <Route path="Ledger" element={<Ledger />} />
          <Route path="Report" element={<Report />} />

        </Routes>
      </div>
    </div>
  );
}

export default Layout;