package banking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<Account> accountList = new ArrayList<>();
    static AccountRepository accountRepository;

    static {
        try {
            accountRepository = new AccountRepository("card.s3db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException {

        int choice = 10;
        while(choice !=0) {
            System.out.println("1. Create an account\n2. Log into account\n0. Exit\n>");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    Account card = new Account();
                    card.createCardNumber();
                    card.createPin();
                    accountRepository.insert(card);
                    accountList.add(card);
                    infoCardAndPin(card);
                    break;
                case 2:
                    System.out.println("Enter your card number:\n>");
                    String checkCard = scanner.next();
                    System.out.println("Enter your PIN:\n>");
                    String checkPin = scanner.next();
                    card = checkAccount(checkCard, checkPin);
                    if (card != null) {
                        System.out.println("You have successfully logged in!");
                        int izbor = 100;
                        while(izbor != 0) {
                            System.out.println("1. Balance\n2. Add income\n3. Do transfer\n4. Close account\n5. Log out\n0. Exit\n>");
                            izbor = scanner.nextInt();
                            switch (izbor){
                                case 1:
                                    System.out.println("Balance: " + card.getBalance());
                                    break;
                                case 2:
                                    System.out.println("Enter income: \n");
                                    double income = scanner.nextDouble();
                                    addIncome(card, income);
                                case 3:

                                case 4:
                                    AccountRepository.deleteAccount(card);
                                    System.out.println("The account has been closed!");
                                case 5:
                                    izbor = 0;
                                    System.out.println("You have successfully logged out!");
                                    break;
                                case 0:
                                    choice = 0;
                                    break;
                                default:
                                    System.out.println("Wrong input");
                            }
                        }
                    }
                    else {
                        System.out.println("Wrong card number or PIN!");
                    }
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
    static void infoCardAndPin(Account card){
            System.out.println("Your card has been created");
            System.out.println("Your card number:\n" + card.getCardNumber());
            System.out.println("Your PIN:\n" + card.getPin());
    }
    public static Account checkAccount(String cardNumber, String pin){

        for (Account i : accountList){

            if (cardNumber.equals(i.getCardNumber()) && pin.equals(i.getPin())){
                return i;
            }
        }
        return null;
    }
    public static Account findAccount(String cardNumber, String pin) {
        for (Account a : accountList) {
            if (cardNumber.equals (a.getCardNumber ()) && pin.equals (a.getPin ())) {
                return a;
            }
        }
        return null;
    }
    public static void addIncome (Account acc, double income) {
        acc.setBalance(acc.getBalance() + income);
        accountRepository.update(acc);
        System.out.println("Income was added!");
    }
}