package banking;

public class Account {

    private int id;
    private String cardNumber;
    private String pin;
    private double balance;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getPin() {
        return pin;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int createId() {
        id = 0;
        id++;
        this.id = id;
        return id;
    }
    public String createCardNumber() {
        cardNumber = "400000" + String.format("%09d", (long) (Math.random() * 999999999L));
        int lastDigit = getCheckDigit(cardNumber);
        this.cardNumber = cardNumber + lastDigit;
        return cardNumber;
    }
    public String createPin() {
        pin = String.format("%04d", (long) (Math.random() * 9999L));
        this.pin = pin;
        return pin;
    }
    private int getCheckDigit(String number) {

        int sum = 0;
        int remainder = (number.length() + 1) % 2;
        for (int i = 0; i < number.length(); i++) {

            // Get the digit at the current position.
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == remainder) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        int checkDigit = ((mod == 0) ? 0 : 10 - mod);

        return checkDigit;
    }
}
