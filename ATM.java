import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ATM {
    public static void main(String[] args) {
        System.out.println("====== Sadra Bank ======\n\n");
        boolean hasAccount = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> allAccounts = new ArrayList<>();
        BankAccount selectedAccount = null;
        String tempAddress;
        String tempPassword;
        int amount;

        do {

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

                    System.out.print("Enter your name: ");
                    String name = scanner.next();

                    System.out.print("\nEnter your age: ");
                    int age = scanner.nextInt();

                    int tempIntAddress = random.nextInt(899999) + 100000;
                    tempAddress = Integer.toString(tempIntAddress);
                    System.out.println("====Your bank address is: " + tempAddress + " =====\n======= PLEASE DON'T FORGET IT !!!! ======");

                    System.out.print("\nEnter a password: ");
                    tempPassword = scanner.next();

                    allAccounts.add(new BankAccount(name, age, tempAddress, tempPassword));

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
                                if (tempPassword.equals(tempPassword)) {
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


                case 1:
                    if (hasAccount) {
                        System.out.print("Enter the amount to deposit: ");
                        amount = scanner.nextInt();

                        selectedAccount.deposit(amount);
                    }
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 2:
                    if (hasAccount) {
                        System.out.print("Enter the amount to withdraw: ");
                        amount = scanner.nextInt();

                        selectedAccount.withdraw(amount);
                    }
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 3:
                    if (hasAccount)
                        System.out.println("Your balance is: " + selectedAccount.getBalance() + "\n");
                    else
                        System.out.println("====Error: Select an account first\n");
                    break;

                case 4:
                    selectedAccount = null;
                    hasAccount = false;
                    break;

            }

            if (choice == 99)
                break;

        } while (true);
    }
}
