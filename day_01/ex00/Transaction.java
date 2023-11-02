package ex00;
import java.util.UUID;

class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category category;
    private double amount;

    private enum Category {
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, double amount) {
        identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        if(amount < 0) {
            this.category = Category.DEBIT;
        } else {
            this.category = Category.CREDIT;
        }
        this.amount = amount;

    }

    public UUID getIdentifier() {
        return identifier;
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

    public ex00.Transaction.Category getCategory() {
        return category;
    }

    public void setCategory(ex00.Transaction.Category category) {
        this.category = category;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TRANSACTION{"
                + "transaction UUID = " + identifier
                + ", Recipient = " + recipient.getUserName()
                + ", Sender = " + sender.getUserName()
                + ", Transaction type = " + category
                + ", Amount = " + amount
                + '}';
    }
}