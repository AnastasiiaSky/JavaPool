package ex03;

public interface TransactionsList {
    void addTransaction(Transaction current);
    void deleteTransactionByUUID(String uuid);
//    Transaction[] toArray(// связный список);
}