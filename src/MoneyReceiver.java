interface MoneyReceiver {
    boolean makePurchase(int amount);
    int getBalance();

    void insertMoney(int amount);

}
