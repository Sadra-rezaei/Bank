import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ATM {
    public static void main(String[] args) {

        boolean hasAccount = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> allAccounts = new ArrayList<>();
        BankAccount selectedAccount = null;
        String tempAddress;
        String tempPassword;
        int amount = 0;

        do {

            System.out.println("\n====== Sadra Bank ======\n\n");
            if (!hasAccount) {
                System.out.println("0. Create an account");
                System.out.println("10. Login Account");
            }
            else {
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. View balance");
                System.out.println("4. === Logout Account ===");
            }

            System.out.println("99. === Exit ===");

            System.out.print("\n=====Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    Random random = new Random();
                    DepositType tempDepositType = null;

                    System.out.print("Enter your name: ");
                    String name = scanner.next();

                    System.out.print("\nEnter your age: ");
                    int age = scanner.nextInt();

                    int tempIntAddress = random.nextInt(899999) + 100000;
                    tempAddress = Integer.toString(tempIntAddress);
                    System.out.println("====Your bank address is: " + tempAddress + " =====\n======= PLEASE DON'T FORGET IT !!!! ======");

                    System.out.print("\nEnter a password: ");
                    tempPassword = scanner.next();

                    System.out.println("\nWhat type of money do you want to keep in your account? ");
                    System.out.println("1. Dollar\n2. Rials");
                    int type = scanner.nextInt();
                    switch (type) {
                        case 1:
                            tempDepositType = DepositType.DOLLARS;
                            break;

                        case 2:
                            tempDepositType = DepositType.RIALS;
                            break;
                    }


                    allAccounts.add(new BankAccount(name, age, tempAddress, tempPassword,tempDepositType));

                    selectedAccount = allAccounts.get(allAccounts.size() - 1);
                    hasAccount = true;
                    break;

                case 10:
                    if (allAccounts.isEmpty()) {
                        System.out.println("There is no account\n");
                        break;
                    }

                    System.out.print("Enter your bank address: ");
                    tempAddress = scanner.next();
                    for (BankAccount account : allAccounts) {
                        if (account.getAddress().equals(tempAddress)) {
                            while (true) {
                                System.out.print("Enter your bank password: ");
                                tempPassword = scanner.next();
                                if (tempPassword.equals(account.getPassword())) {
                                    selectedAccount = account;
                                    hasAccount = true;
                                    break;
                                }

                                else
                                    System.out.println("Incorrect password\n");
                            }
                        }
                    }
                    break;


                case 1:  // Deposit
                    if (hasAccount) {
                        DepositType depositType = exchange(scanner);

                        if (depositType == selectedAccount.getDepositType()) {
                            System.out.print("Enter the amount to deposit to "+ depositType+": ");
                            while (true) {
                                amount = scanner.nextInt();
                                if (!(amount <= 0))
                                    break;
                            }
                        }
                        else {
                            if (selectedAccount.getDepositType() == DepositType.DOLLARS) {
                                System.out.print("Enter the amount to deposit to "+ depositType+": ");
                                while (true) {
                                    amount = scanner.nextInt();
                                    if (!(amount <= 0))
                                        break;
                                }
                                amount /= (int) CurrencyFetcher.getUSDPrice();

                            }
                            else if (selectedAccount.getDepositType() == DepositType.RIALS) {
                                System.out.print("Enter the amount to deposit to "+ depositType+": ");
                                while (true) {
                                    amount = scanner.nextInt();
                                    if (!(amount <= 0))
                                        break;
                                }
                                amount *= (int) CurrencyFetcher.getUSDPrice();

                            }
                        }
                        selectedAccount.deposit(amount);
                    }
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 2: //Withdraw
                    if (hasAccount) {
                        DepositType depositType = exchange(scanner);

                        if (depositType == selectedAccount.getDepositType()) {
                            System.out.print("Enter the amount to withdraw: ");
                            while (true) {
                                amount = scanner.nextInt();
                                if (!(amount <= 0))
                                    break;
                            }                        }

                        else {

                            if (selectedAccount.getDepositType() == DepositType.DOLLARS) {
                                System.out.print("Enter the amount to withdraw to " + depositType + ": ");
                                while (true) {
                                    amount = scanner.nextInt();
                                    if (!(amount <= 0))
                                        break;
                                }
                                amount /= (int) CurrencyFetcher.getUSDPrice();

                            } else if (selectedAccount.getDepositType() == DepositType.RIALS) {

                                System.out.print("Enter the amount to withdraw to " + depositType + ": ");
                                while (true) {
                                    amount = scanner.nextInt();
                                    if (!(amount <= 0))
                                        break;
                                }
                                amount /= (int) CurrencyFetcher.getUSDPrice();
                            }
                        }
                    selectedAccount.withdraw(amount);
                    }
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 3:  //View balance
                    if (hasAccount)
                        System.out.println("Your balance is: " + selectedAccount.getBalance() +" on "+ selectedAccount.getDepositType()+ "\n");
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 4:  //Logout
                    selectedAccount = null;
                    hasAccount = false;
                    break;

            }

            if (choice == 99)
                break;

        } while (true);
    }

    public static DepositType exchange(Scanner input) {

        System.out.println("Would you like your transaction to be in dollars or rials? ");
        System.out.println("1-Dollars\n2-Rials");
        boolean breaker = true;
        while (true) {
            int type = input.nextInt();
            if (type == 1) {
                return DepositType.DOLLARS;
            } else if (type == 2) {
                return DepositType.RIALS;
            } else {
                System.out.println("invalid option");
            }
        }
    }
}