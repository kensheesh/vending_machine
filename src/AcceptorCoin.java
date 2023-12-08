class AcceptorCoin implements MoneyReceiver {
    private int balance;
    @Override
    public void insertMoney(int amount) {
        balance += amount;
    }



    public AcceptorCoin() {
        this.balance = 129;
    }


    @Override
    public boolean doPurchasing(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;

        } else {
            System.out.println(" Пожалуйста, положите деньги. У вас не хватает денег");
            return false;
        }
    }


    @Override
    public int getBalance() {
        return balance;
    }
}