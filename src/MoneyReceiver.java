interface MoneyReceiver {
    boolean doPurchasing(int amount);
    int getBalance();

    void insertMoney(int amount);

}
