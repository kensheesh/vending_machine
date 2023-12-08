import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private static boolean isExit = false;
    private final MoneyReceiver moneyReceiver;

    private AppRunner(MoneyReceiver moneyReceiver) {
        this.moneyReceiver = moneyReceiver;
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
    }

    public static void run(MoneyReceiver moneyReceiver) {
        AppRunner app = new AppRunner(moneyReceiver);
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("Главное меню:");
        print("1 = Показать доступные товары");
        print("2 = Пополнить ваш баланс");
        print("h = Выйти");

        String choice = fromConsole().substring(0, 1);

        switch (choice) {
            case "1":
                print("В автомате доступны:");
                showProducts(products);
                print("Монет на сумму: " + moneyReceiver.getBalance());
                UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
                allowProducts.addAll(getAllowedProducts().toArray());
                chooseAction(allowProducts);
                break;
            case "2":
                topBalance();
                break;
            case "h":
                isExit = true;
                break;
            default:
                print("Неправильная команда. Попробуйте заново!");
        }
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (moneyReceiver.getBalance() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        showActions(products);
        print(" h - Выйти");
        String action = fromConsole().substring(0, 1);
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    if (moneyReceiver.doPurchasing(products.get(i).getPrice())) {
                        print("Вы купили " + products.get(i).getName());
                    }
                    break;
                } else if ("h".equalsIgnoreCase(action)) {
                    isExit = true;
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попрбуйте еще раз.");
            chooseAction(products);
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }

    private void topBalance() {
        print("Введите сумму для пополнения вашего баланса:");
        try {
            int amountToAdd = Integer.parseInt(fromConsole());
            if (amountToAdd > 0) {
                moneyReceiver.insertMoney(amountToAdd);
                print("Баланс успешно пополнен Ваш баланс: " + moneyReceiver.getBalance());
            } else {
                print("Вы ввели некорректное число для пополнения баланса (отрицтальное)");
            }
        } catch (NumberFormatException e) {
            print("Недопустимый ввод! Введите число для пополнения баланса.");
        }
    }
}


