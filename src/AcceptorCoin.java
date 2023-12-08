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
        if (balance <= amount) {
            balance -= amount;
            return false;

        } else {
            System.out.println(" Пожалуйста, положите деньги. У вас не хватает денег");
            return true;
        }
    }


    @Override
    public int getBalance() {
        return balance;
    }
}
