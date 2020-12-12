import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Console;
class BankWork           // initialize and declare objects.
{
    final int max_limit = 20;
    final int min_limit = 1;
    final double min_bal = 500;
    private String name[] = new String[20];
    private int accNo[] = new int[20];
    private String accType[] = new String[20];
    private double balamount[] = new double[20];
    private String username[] = new String[20];
    private String password[] = new String[20];
    private String transaction[][][] = new String[20][100][4];
    private int trans[] = new int[20];
    static int totRec = 0;
    private int transactionno;
    private int data;
    BankWork()     // create a constructor here of Bank.
    {
        for (int i = 0; i < max_limit; i++) 
        {
            name[i] = "";
            accNo[i] = 0;
            accType[i] = "";
            username[i] = "";
            password[i] = "";
            balamount[i] = 0.0;
        }
    }
    public void newEntry()    // Create method to create New entry.
    {
        String str;
        int account;
        double amount;
        boolean permit;
        permit = true;
        transactionno = 0;
        if (totRec > max_limit) 
        {
            System.out.println("\n\n\nSorry we cannot admit you in our bank...\n\n\n");
            permit = false;
        }
        if (permit = true) 
        {
            totRec++;
            System.out.println("\n\n\n=====Registration=====");
            try
            {
                accNo[totRec] = totRec; //Created  AutoNumber  to accNo so no invalid id occurs
                System.out.println("Account Number :  " + accNo[totRec]);
                BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter the username : ");
                System.out.flush();
                username[totRec] = obj.readLine();
                System.out.print("Enter the password of the Customer : ");
                System.out.flush();
                password[totRec] = obj.readLine();
                System.out.print("Enter the name of the Customer : ");
                System.out.flush();
                name[totRec] = obj.readLine();
                System.out.print("Enter Account Type (Saving or Current) : ");
                System.out.flush();
                accType[totRec] = obj.readLine();
                do 
                {
                    // enter the starting amount.
                    // minimum amount must be 500.
                    System.out.print("Enter Initial  Amount to be deposited : ");
                    System.out.flush();
                    str = obj.readLine();
                    balamount[totRec] = Double.parseDouble(str);
                    if (balamount[totRec] < min_bal) 
                    {
                        System.out.println("\nWarning....Minimum 500 Rs to be deposited\n");
                    }
                }
                while (balamount[totRec] < min_bal);
                LocalDateTime datetime1 = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                data = 0;
                transaction[totRec][transactionno][data] = datetime1.format(format);
                data++;
                transaction[totRec][transactionno][data] = String.valueOf(balamount[totRec]);
                data++;
                transaction[totRec][transactionno][data] = " - ";
                data++;
                transaction[totRec][transactionno][data] = String.valueOf(balamount[totRec]);
                transactionno++;
                trans[totRec] = transactionno;
                System.out.println("\n....Registration Successful....\n\n\n");
            } 
            catch (Exception e) 
            {
                System.out.println("Exception in Entering a record.....");
            }
        }
    }
    public void login()    // create method to display records.
    {
        String str, usname, pwd;
        int account = 0, choice, i;
        boolean valid = true;
        System.out.println("\n\n=====Login=====\n");
        try 
        {
            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            Console console = System.console();
            System.out.print("Enter the username : ");
            System.out.flush();
            usname = obj.readLine();
            for (i = 0; i < 20; i++) 
            {
                if (usname.equals(username[i]) == true)
                {
                    account = i;
                    break;
                }
            }
            System.out.print("Enter the password : ");
            char pass[] = console.readPassword();
            pwd = String.valueOf(pass);
            if (pwd.equals(password[account]) == true)
            {
                System.out.println("\n....Logged In....\n");
                System.out.println("Welcome " + name[account] + "\n\n");
                do 
                {
                    System.out.println("Choose Your Choices ...");
                    System.out.println("1) Balance...");
                    System.out.println("2) Account Type...");
                    System.out.println("3) Deposit");
                    System.out.println("4) Withdraw");
                    System.out.println("5) Balance Statement");
                    System.out.println("6) Interests on Loans");
                    System.out.println("7) Log Out");
                    System.out.print("Enter your choice :  ");
                    System.out.flush();
                    str = obj.readLine();
                    choice = Integer.parseInt(str);
                    switch (choice) 
                    {
                        case 1:
                            System.out.print("\n\n\nAvailable Balance : " + balamount[account] + "\n\n");
                            break;
                        case 2:
                            System.out.print("\n\n\nAccount Type : " + accType[account] + "\n\n");
                            break;
                        case 3:
                            deposit(account);
                            break;
                        case 4:
                            withdraw(account);
                            break;
                        case 5:
                            history(account);
                            break;
                        case 6:
                            interest();
                            break;
                        case 7:
                            System.out.println("\n\n....Logged Out....\n\n");
                            break;
                        default:
                            System.out.println("\nInvalid Choice \n\n");
                    }
                } while (choice != 7);
            } 
            else 
            {
                System.out.println("\n\n....Incorrect Username or Password....");
                System.out.println("....Try again or register....\n\n");
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Exception in Login.....");
        }
    }
    public void deposit(int account)    // create method to deposit amount.
    {
        String str;
        double amount;
        transactionno = trans[account];
        System.out.println("\n\n\n=====DEPOSIT AMOUNT=====");
        try {
            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Amount you want to Deposit  : ");
            System.out.flush();
            str = obj.readLine();
            amount = Double.parseDouble(str);
            balamount[account] = balamount[account] + amount;
            LocalDateTime datetime1 = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            data = 0;
            transaction[account][transactionno][data] = datetime1.format(format);
            data++;
            transaction[account][transactionno][data] = String.valueOf(amount);
            data++;
            transaction[account][transactionno][data] = " - ";
            data++;
            transaction[account][transactionno][data] = String.valueOf(balamount[account]);
            transactionno++;
            trans[account] = transactionno;
            System.out.println("\nAfter Updation...");
            System.out.println("Account Number :  " + account);
            System.out.println("Balance Amount :  " + balamount[account] + "\n\n\n");
        }
        catch (Exception e)
        {
            System.out.println("Exception in Depositing record.....");
        }
    }
    public void withdraw(int account)     // creating method for withdraw money.
    {
        String str;
        double amount, checkamount;
        boolean valid = true;
        transactionno = trans[account];
        System.out.println("\n\n\n=====WITHDRAW MONEY=====");
        try
        {
            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Balance is : " + balamount[account]);
            System.out.print("Enter Amount you want to withdraw  : ");
            System.out.flush();
            str = obj.readLine();
            amount = Double.parseDouble(str);
            if (amount <= balamount[account]) 
            {
                checkamount = balamount[account] - amount;
                if (checkamount >= min_bal) 
                {
                    balamount[account] = checkamount;
                    LocalDateTime datetime1 = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    data = 0;
                    transaction[account][transactionno][data] = datetime1.format(format);
                    data++;
                    transaction[account][transactionno][data] = " - ";
                    data++;
                    transaction[account][transactionno][data] = String.valueOf(amount);
                    data++;
                    transaction[account][transactionno][data] = String.valueOf(balamount[account]);
                    transactionno++;
                    trans[account] = transactionno;
                    System.out.println("\nAfter Updation...");
                    System.out.println("Account Number :  " + account);
                    System.out.println("Balance Amount :  " + balamount[account] + "\n\n\n");
                } 
                else 
                {
                    System.out.println("\n\nAs per Bank Rule you should maintain minimum balance of Rs 500\n\n\n");
                }
            } 
            else 
            {
                System.out.println("\n\nWarning....Can't withdraw money more than available\n\n");
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in Withdrawing record.....");
        }
    }
    public void interest() // creating method for Interest Table.
    {
        System.out.println("\n\n\n\t\t\t\t\tInterest Table\n");
        System.out.print("Loan Type\t\t\t\tLoan Amount\t\t\t  Interests\n\n");
        System.out.print("Home Loan\t\t\t\tUpto 30 Lac\t\t\t      7%\n");
        System.out.print("\t\t\t\t   Above 30 Lac to 75 Lac\t\t     7.25%\n");
        System.out.print("\t\t\t\t\tAbove 75 Lac\t\t\t     7.35%\n");
        System.out.print("\nEducation Loan\t\t\t\t      -\t\t\t\t     9.30%\n");
        System.out.print("\t\t\t\t\t'0.50% concession in interest for girl students'\n");
        System.out.print("\nAuto Loan\t\t\t\t      -\t\t\t\t     9.52%\n");
        System.out.print("\nPersonal Loan\t\t\t\tUpto 10 Lac\t\t\t10.60% - 11.10%\n");
        System.out.print("\t\t\t\t\tAbove 10 Lac\t\t\t11.10% - 11.60%\n");
        System.out.print("\nGold Loan\t\t\t\t      -\t\t\t\t      7.5%\n\n\n\n");
    }
    public void history(int account) // creating method for transaction history.
    {
        transactionno = trans[account];
        int i, j;
        System.out.print("\n\n   Date   &  Time  \t\tCredit\t\tDebit\t\tAmount\n\n");
        for (i = 0; i < transactionno; i++)
        {
            for (j = 0; j < 4; j++) 
            {
                System.out.print(transaction[account][i][j] + "\t\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n\n");
    }

};
class Main
{
    public static void main(String args[]) 
    {
        String str;
        int choice;
        choice = 0;
        BankWork BW_obj = new BankWork();
        do 
        {
            System.out.println("Choose Your Choices ...");
            System.out.println("1) Registration...");
            System.out.println("2) Login...");
            System.out.println("3) Exit");
            System.out.print("Enter your choice :  ");
            System.out.flush();
            try 
            {
                BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
                str = obj.readLine();
                choice = Integer.parseInt(str);
                switch (choice)
                {
                    case 1:
                        BW_obj.newEntry();
                        break;
                    case 2:
                        BW_obj.login();
                        break;
                    case 3:
                        System.out.println("\n\n.....THANKS FOR VISITING.....");
                        break;
                    default:
                        System.out.println("\nInvalid Choice \n\n");
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Exception in Main....");
            }
        }while (choice != 3);
    }
}