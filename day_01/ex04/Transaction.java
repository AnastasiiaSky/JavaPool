package ex04;
import java.util.UUID;

class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private Category category;
    private double amount;
    private Transaction next;
    private Transaction prev;

    private enum Category {
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, double amount) {
        identifier = UUID.randomUUID().toString();
        this.recipient = recipient;
        this.sender = sender;
        if(amount < 0) {
            this.category = Category.DEBIT;
        } else {
            this.category = Category.CREDIT;
        }
        if(amount < 0) amount *= -1;
        if(sender.getBalance() < amount) {
            this.amount = 0;
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
    public ex03.Transaction.Category getCategory() {
        return category;
    }
    public void setCategory(ex03.Transaction.Category category) {
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
    public void setAmount(double amount) {
        if(amount < 0) amount *= -1;
        if(sender.getBalance() < amount) {
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    public ex03.Transaction getNext() {
        return next;
    }
    public ex03.Transaction getPrev() {
        return prev;
    }
    public void setNext(ex03.Transaction next) {
        this.next = next;
    }
    public void setPrev(ex03.Transaction prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        if(prev != null && next != null) {
            return "Transaction {"
                    + "transaction UUID = " + identifier
                    + ", Recipient = " + recipient.getName()
                    + ", Sender = " + sender.getName()
                    + ", Transaction type = " + category
                    + ", Amount = " + amount
                    + ", Prev = " + prev.getIdentifier()
                    + ", Next = " + next.getIdentifier()
                    + '}';
        } else if(prev == null) {
            return "Transaction {"
                    + "transaction UUID = " + identifier
                    + ", Recipient = " + recipient.getName()
                    + ", Sender = " + sender.getName()
                    + ", Transaction type = " + category
                    + ", Amount = " + amount
                    + ", Prev = " + "null"
                    + ", Next = " + next.getIdentifier()
                    + '}';
        } else if(next == null) {
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