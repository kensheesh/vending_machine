public class Card implements MoneyReceiver {
    private int balance;



    public Card(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void insertMoney(int amount) {
        balance += amount;
    }


    @Override
    public boolean doPurchasing(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("На вашей карте нет денег столько. Уменьшите вводимую сумму");
            return false;
        }
    }

    @Override
    public int getBalance() {
        return balance;
    }
}

