import React, { useState, useEffect } from "react";
import Api from "../api";

function getClassName(type) {
  return type;
}

function TransactionItem({ transaction }) {
  return (
    <div>
      <h2 className={getClassName(transaction.type)}>
        Type: {transaction.type} - Amount: $ {transaction.amount}
      </h2>
    </div>
  );
}

function TransactionList({ transactions }) {
  return (
    <div>
      <h1>Transactions</h1>
      {transactions.map((transaction) => (
        <TransactionItem key={transaction.id} transaction={transaction} />
      ))}
    </div>
  );
}

export default TransactionList;
