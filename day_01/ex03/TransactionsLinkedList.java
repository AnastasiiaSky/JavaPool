package ex03;

public class TransactionsLinkedList implements TransactionsList {

    int transactionNumber;
    private final TransactionNode start = new TransactionNode(null, null, null);
    private final TransactionNode finish = new TransactionNode(null, null, null);

    TransactionsLinkedList() {
        this.transactionNumber = 0;
        start.setNext(start);
        finish.setPrev(finish);
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public ex03.TransactionsLinkedList.TransactionNode getStart() {
        return start;
    }

    public ex03.TransactionsLinkedList.TransactionNode getFinish() {
        return finish;
    }

    @Override
    public void addTransaction(Transaction current){
        this.transactionNumber += 1;
        this.start.setNext(new TransactionNode(current, start.getNext(), finish));
    }










    class TransactionNode {
        Transaction current;
        TransactionNode prev;
        TransactionNode next;

        TransactionNode(Transaction data, TransactionNode prev, TransactionNode next) {
            this.current = data;
            this.prev = prev;
            this.next = next;
        }

        TransactionNode getPrev() {return this.prev;}
        TransactionNode getNext() {return this.next;}
        Transaction getCurrent() {return this.current;}

        void setCurrent(Transaction current) {this.current = current;}
        void setPrev(TransactionNode prev) {this.prev = prev;}
        void setNext(TransactionNode next) {this.next = next;}
    }

}