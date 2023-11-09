package ex03;

import ex03.exceptions.TransactionNotFoundException;

public class TransactionsLinkedList implements TransactionsList {

    private int size = 0;
    Transaction head;
    Transaction tail;

    TransactionsLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty(TransactionsLinkedList cur) {
        return this.size == 0;
    }

    public Transaction getHead() {
        return head;
    }

    public Transaction getTail() {
        return tail;
    }

    public void setHead(Transaction head) {
        this.head = head;
    }

    public void setTail(Transaction tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void addTransaction(Transaction current) {
        this.size += 1;
        if (this.head == null) {
            this.head = current;
            this.tail = current;
        } else {
            current.setPrev(this.tail);
            this.tail.setNext(current);
            this.tail = this.tail.getNext();
        }
    }

    @Override
    public void deleteTransactionByUUID(String uuid) {
        try {
            for (Transaction current = head; current != null; current = current.getNext()) {
                if (current.getIdentifier().equals(uuid)) {
                    if (current != null && current.getNext() != null && current.getPrev() != null) {
                        current.getPrev().setNext(current.getNext());
                        current.getNext().setPrev(current.getPrev());
                        current.setNext(null);
                        current.setPrev(null);
                    } else if (current.getPrev() == null && current.getNext() != null) {
                        head = current.getNext();
                        head.setPrev(null);
                        current.setNext(null);
                        current.setPrev(null);
                    } else if (current.getNext() == null && current.getPrev() != null) {
                        current.getPrev().setNext(null);
                        current.setNext(null);
                        current.setPrev(null);
                    }
                    size--;
                } else {
                    current = current.getNext();
                }
            }
        } catch (TransactionNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactionsArr = new Transaction[size];
        if (size != 0) {
            Transaction tmpHead = head;
            for (int i = 0; i < size; ++i) {
                transactionsArr[i] = tmpHead;
                tmpHead = tmpHead.getNext();
            }
            return transactionsArr;
        }
        return transactionsArr;
    }

    @Override
    public String toString() {
        return "Transaction list {"
                + "size - " + size
                + '}';
    }

}