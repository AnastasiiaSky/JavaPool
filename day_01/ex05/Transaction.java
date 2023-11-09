package ex05;

import java.util.UUID;

import ex05.exceptions.TransactionNotFoundException;
import ex05.exceptions.UserNotFoundException;
import ex05.exceptions.IllegalTransactionException;

class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private Category category;
    private double amount;
    private Transaction next;
    private Transaction prev;

    private enum Category {
        OUTCOME,
        INCOME
    }

    public Transaction(User sender, User recipient, double amount) throws IllegalTransactionException {
        identifier = UUID.randomUUID().toString();
        this.recipient = recipient;
        this.sender = sender;
        if (amount < 0) {
            this.category = Category.INCOME;
        } else {
            this.category = Category.OUTCOME;
        }

        if (sender.getBalance() < (amount *= -1)) {
            throw new IllegalTransactionException();
        } else {
            this.amount = amount;
        }
        this.next = null;
        this.prev = null;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public ex05.Transaction.Category getCategory() {
        return category;
    }

    public void setCategory(ex05.Transaction.Category category) {
        this.category = category;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAmount(double amount) throws IllegalTransactionException {
        if (sender.getBalance() < (amount *= -1)) {
            throw new IllegalTransactionException();
        } else {
            this.amount = amount;
        }
    }

    public ex05.Transaction getNext() {
        return next;
    }

    public ex05.Transaction getPrev() {
        return prev;
    }

    public void setNext(ex05.Transaction next) {
        this.next = next;
    }

    public void setPrev(ex05.Transaction prev) {
        this.prev = prev;
    }

    public String transactionInfo() {
        return sender.getName()
                + " -> "
                + recipient.getName()
                + ", " + amount
                + ", " + category
                + ", " + identifier;
    }

    @Override
    public String toString() {
        if (prev != null && next != null) {
            return "Transaction {"
                    + "transaction UUID = " + identifier
                    + ", Recipient = " + recipient.getName()
                    + ", Sender = " + sender.getName()
                    + ", Transaction type = " + category
                    + ", Amount = " + amount
                    + ", Prev = " + prev.getIdentifier()
                    + ", Next = " + next.getIdentifier()
                    + '}';
        } else if (prev == null && next != null) {
            return "Transaction {"
                    + "transaction UUID = " + identifier
                    + ", Recipient = " + recipient.getName()
                    + ", Sender = " + sender.getName()
                    + ", Transaction type = " + category
                    + ", Amount = " + amount
                    + ", Prev = " + "null"
                    + ", Next = " + next.getIdentifier()
                    + '}';
        } else if (next == null && prev != null) {
            return "Transaction {"
                    + "transaction UUID = " + identifier
                    + ", Recipient = " + recipient.getName()
                    + ", Sender = " + sender.getName()
                    + ", Transaction type = " + category
                    + ", Amount = " + amount
                    + ", Prev = " + prev.getIdentifier()
                    + ", Next = " + "null"
                    + '}';
        }
        return "Transaction {"
                + "transaction UUID = " + identifier
                + ", Recipient = " + recipient.getName()
                + ", Sender = " + sender.getName()
                + ", Transaction type = " + category
                + ", Amount = " + amount
                + ", Prev = " + "null"
                + ", Next = " + "null"
                + '}';
    }
}