package ex03;

public class TransactionsLinkedList implements TransactionsList {

    private int size = 0;
    Transaction head;
    Transaction tail;

    TransactionsLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int getSize() {return this.size;}
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
    public void addTransaction(Transaction current){
        this.size += 1;
        if(this.head == null) {
            this.head = current;
            this.tail = current;
        } else {
            current.setPrev(this.tail);
            this.tail.setNext(current);
            this.tail = this.tail.getNext();
        }
    }

    @Override
    public void deleteTransactionByUUID(String uuid) throws TransactionNotFoundException {
        boolean isFound = false;
        if(uuid.equals(null)) throw new TransactionNotFoundException();
        Transaction current = head.getNext();
        while(current != tail && size > 0) {
            if(current.getIdentifier().equals(uuid)) {
                isFound = true;
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
//                setSize(getSize() - 1);
                this.size = this.size - 1;
            } else {
                current = current.getNext();
            }
        }
//        if(isFound == true) setSize(getSize() - 1);
        if(isFound == false) throw new TransactionNotFoundException();
//        System.out.println(current.getIdentifier() + " " + current.getSender().getName() + " " + current.getRecipient().getName());
    }








//// Узел двусвязного списка
//    class TransactionNode {
//        TransactionNode prev;// ссылка на предыдущий узел
//        Transaction current; // значение
//        TransactionNode next; // ссылка на текущий узел
//
//        TransactionNode(Transaction data, TransactionNode prev, TransactionNode next) {
//            this.prev = prev;
//            this.current = data;
//            this.next = next;
//        }
//
//
//
//         TransactionNode getPrev() {return this.prev;}
//        TransactionNode getNext() {return this.next;}
//        Transaction getCurrent() {return this.current;}
//
//        void setCurrent(Transaction current) {this.current = current;}
//        void setPrev(TransactionNode prev) {this.prev = prev;}
//        void setNext(TransactionNode next) {this.next = next;}
//    }

}