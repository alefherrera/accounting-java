import React, { useState, useEffect } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import TransactionTypeSelect from "./TransactionTypeSelect";
import TransactionList from "./TransactionList";
import Api from "../api";

function TransactionForm() {
  const [type, setType] = useState("credit");
  const [amount, setAmount] = useState("");
  const [data, setData] = useState({ balance: 0, transactions: [] });

  function handleSubmit(event) {
    event.preventDefault();
    return Api.commitTransaction({ type, amount }).then(() => {
      return updateData();
    });
  }

  function handleTextChange(event) {
    setAmount(parseFloat(event.target.value));
  }

  function handleSelectChange(value) {
    setType(value);
  }

  function updateData() {
    return Promise.all([
      Api.getBalance(),
      Api.getTransactions(),
    ]).then(([balance, transactions]) =>
      setData({ balance: balance.amount, transactions })
    );
  }

  useEffect(() => {
    updateData();
  });

  return (
    <div>
      <div>
        <h1>Balance</h1>
        <h2>$ {data.balance}</h2>
      </div>
      <div>
        <form className="form" onSubmit={handleSubmit}>
          <TransactionTypeSelect value={type} onChange={handleSelectChange} />
          <TextField
            id="outlined-basic"
            label="Amount"
            variant="outlined"
            type="number"
            value={amount}
            onChange={handleTextChange}
          />
          <Button type="submit" variant="contained" color="primary">
            Submit
          </Button>
        </form>
      </div>
      <div>
        <TransactionList transactions={data.transactions} />
      </div>
    </div>
  );
}

export default TransactionForm;
