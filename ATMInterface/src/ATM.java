import java.util.Scanner;

public class ATM {

    public static bankAccount userAccount = new bankAccount();

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    int depositAmount = scanner.nextInt();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    int withdrawalAmount = scanner.nextInt();
                    withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }

    // Method that prints out the ATM User Interface.
    public static void displayMenu() {
        System.out.println("\nWelcome to the CODSOFT ATM!");
        System.out.println("How can we assist you?");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to check the user's bank balance.
    public static void checkBalance(){
        System.out.println("Your current balance is $" + userAccount.getBalance());
    }

    // Method to deposit money into the user's bank account.
    public static void deposit(int depositAmount){
        userAccount.setBalace(userAccount.getBalance() + depositAmount);
        System.out.println("You have successfully deposited $" + depositAmount + " into your account");
    }

    // Method to withdraw money from the user's bank account.
    public static void withdraw(int withdrawalAmount){
        if (withdrawalAmount <= userAccount.getBalance()){
            userAccount.setBalace(userAccount.getBalance() - withdrawalAmount);
            System.out.println("You have successfull withdrawn $" + withdrawalAmount + " from your account");        
        }
        else{
            System.out.println("You have insufficient funds to withdraw $" + withdrawalAmount);
        }   
    }

}

