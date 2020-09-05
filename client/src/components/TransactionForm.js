import React, { useState, useEffect } from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import TransactionTypeSelect from "./TransactionTypeSelect";
import TransactionList from "./TransactionList";
import Api from "../api";

function TransactionForm() {
  const [type, setType] = useState("credit");
  const [amount, setAmount] = useState("");
  const [balance, setBalance] = useState(0);
  const [transactions, setTransactions] = useState([]);

  function handleSubmit(event) {
    event.preventDefault();
    Api.commitTransaction({ type, amount }).then((result) => {
      setBalance(result.amount);
      return updateTransactions();
    });
  }

  function handleTextChange(event) {
    setAmount(parseFloat(event.target.value));
  }

  function handleSelectChange(value) {
    setType(value);
  }

  function updateTransactions() {
    return Api.getTransactions().then((result) =>
      setTransactions(result || [])
    );
  }

  useEffect(() => {
    Api.getBalance().then((result) => setBalance(result.amount));
  });

  return (
    <div>
      <div>
        <h1>Balance</h1>
        <h2>$ {balance}</h2>
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
        <TransactionList transactions={transactions} />
      </div>
    </div>
  );
}

export default TransactionForm;
