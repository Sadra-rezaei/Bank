public class BankAccount {
    private String name;
    private int age;
    private int balance;
    private final String address;
    private String password;
    private DepositType depositType;

    public BankAccount(String name, int age, String address, String password, DepositType depositType) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.balance = 0;
        this.password = password;
        this.depositType = depositType;


    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (this.balance > amount)
            this.balance -= amount;

        else
            System.out.println("Insufficient balance\n");
    }

    public int getBalance() {
        return this.balance;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPassword() {
        return this.password;
    }

    public DepositType getDepositType() {
        return depositType;
    }
}
