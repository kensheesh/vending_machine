public class Main {
    public static void main(String[] args) {
        MoneyReceiver coinAcceptor = new AcceptorCoin();
        AppRunner.run(coinAcceptor);
    }
}

