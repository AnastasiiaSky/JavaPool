package ex03;

public interface TransactionsList {
    void addTransaction();
    void deleteTransactionByUUID(Strung uuid);
    Transaction[] toArray(// связный список);
}