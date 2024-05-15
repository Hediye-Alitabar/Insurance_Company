import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface // different designs
{
    public static void displayMainMenu() {
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n");
        System.out.println("---Main Menu---");
        System.out.println("(1) Admin Login");
        System.out.println("(2) User Login");
        System.out.println("(3) Exit");
        System.out.println("\n\nSelect and Option from 1-3");
    }

    public static void mainMenu(InsuranceCompany insuranceCompany) throws CloneNotSupportedException, PolicyException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while (input != 3) {
            try {
                displayMainMenu();
                input = sc.nextInt();
                switch (input) {
                    case 1:
                        adminLogin(insuranceCompany);
                        break;
                    case 2:
                        userLogin(insuranceCompany);
                        break;
                    case 3:
                        pressEnterToContinue();
                        break;
                    default:
                        System.out.println("Wrong Option. Please enter a number between 1-3");
                        pressEnterToContinue();
                }
            } catch (InputMismatchException | PolicyException e) {
                pressEnterToContinue();
            }
        }
    }
    //main menu options

    public static void adminLogin(InsuranceCompany insuranceCompany) throws CloneNotSupportedException, PolicyException {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;

        while (!loggedIn) {
            try {
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\n\n");
                System.out.println("--Admin Login---");
                System.out.println("Enter Username(or Q to return to main menu) :");
                username = sc.nextLine();
                if (username.equals("Q"))
                    return; // return to main menu again
                System.out.println("Enter Password:");
                password = sc.nextLine();
                if (insuranceCompany.validateAdmin(username, password)) {
                    loggedIn = true;
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Login Unsuccessful");
                }
            } catch (InputMismatchException e) {
                pressEnterToContinue();
            }
        }
        //login successfull
        adminMenu(insuranceCompany);
    }

    public static void userLogin(InsuranceCompany insuranceCompany) throws PolicyException {
        boolean loggedIn = false;
        Scanner sc = new Scanner(System.in);
        int userID;
        String password;
        User user = null;
        while (!loggedIn) {
            try {
//            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("\n\n");
                System.out.println("--User Login---");
                System.out.println("Enter UserID ( or 0 to return to main menu) : ");
                userID = sc.nextInt();
                sc.nextLine();
                if (userID == 0)
                    return; // return to main menu again
                System.out.println("Enter Password:");
                password = sc.nextLine();
                user = insuranceCompany.validateUser(userID, password);
                if (user != null) {
                    loggedIn = true;
                    System.out.println("Login Successful");
                } else {
                    System.out.println("Login Unsuccessful");
                }
            } catch (InputMismatchException e) {
                pressEnterToContinue();
            }
        }
        userMainMenu(user);
    }

    public static void displayAdminMainMenu() {
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n");
        System.out.println("---Admin Menu---");
        System.out.println("(1) Test Code");
        System.out.println("(2) Create User");
        System.out.println("(3) Create ThirdParty Policy");
        System.out.println("(4) Create Comprehensive Policy");
        System.out.println("(5) Print User Information");
        System.out.println("(6) Filter By Car Model");
        System.out.println("(7) Filter By Expiry Date");
        System.out.println("(8) Update Address");
        System.out.println("(9) report Car Model, Total Payment, and Average Premium Payment (For given user)");
        System.out.println("(10) report City Name, Total Premium Payment (Across all users)");
        System.out.println("(11) report Car Model, Total Premium Payment, and Average Premium Payment (Across all users)");
        System.out.println("(12) Log Out");
        System.out.println("\n\nSelect and Option from 1-12");
    }

    // this method will return an integer and catch mismatch exceptions
    public static int getInt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: your input is not an integer, please try again.");
            }
        }
    }

    public static String getString() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: your input is not an String, please try again.");
            }
        }
    }

    public static void adminMenu(InsuranceCompany insuranceCompany) throws CloneNotSupportedException, PolicyException {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 9) {
            displayAdminMainMenu();
            option = sc.nextInt();
            switch (option) {
                case 1:
                    TestCase.testCode(insuranceCompany);
                    break;
                case 2:
                    createUser(insuranceCompany);
                    break;
                case 3:
                    createThirdPartyPolicy(insuranceCompany);
                    break;
                case 4:
                    createComprehensivePolicy(insuranceCompany);
                    break;
                case 5:
                    printUserInformation(insuranceCompany);
                    break;
                case 6:
                    filterByCarModel(insuranceCompany);
                    break;
                case 7:
                    filterByExpiryDate(insuranceCompany);
                    break;
                case 8:
                    updateAddress(insuranceCompany);
                    break;
                case 9:
                    insuranceCompany.reportPaymentsPerCarModel();
                    break;
                case 10:
                    insuranceCompany.reportPaymentPerCity();
                    break;
                case 11:
                    insuranceCompany.reportPaymentsPerCarModel();
                    break;
                case 12:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-12");
            }
            pressEnterToContinue();
        }
    }

    public static void displayUserMainMenu() {
//        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n");
        System.out.println("---User Menu---");
        System.out.println("(1) Print All Policies and Total Payments");
        System.out.println("(2) Create Third Party Policy");
        System.out.println("(3) Create Comprehensive Policy");
        System.out.println("(4) Print User Information");
        System.out.println("(5) Update Address");
        System.out.println("(6) Log Out");
        System.out.println("\n\nSelect and Option from 1-6");
    }

    public static void userMainMenu(User user) throws PolicyException {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (input != 7) {
            displayUserMainMenu();
            input = sc.nextInt();

            switch (input) {
                case 1:
                    printUser(user);
                    break;
                case 2:
                    createThirdPartyPolicy(user);
                    break;
                case 3:
                    createComprehensivePolicy(user);
                    break;
                case 4:
                    printUser(user);
                    break;
                case 5:
                    updateAddress(user);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-6");
            }
            pressEnterToContinue();
        }
    }

    // admin menu options


    public static void createUser(InsuranceCompany insuranceCompany) //good way
    {
        UITools.addUser(insuranceCompany, UITools.getUser());
    }

    public static void createThirdPartyPolicy(InsuranceCompany insuranceCompany) throws PolicyException // good way
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create ThirdParty Policy ---");
        System.out.println("Enter a UserID to add Policy to:");
        int userID = sc.nextInt();
        sc.nextLine();
        UITools.addPolicy(insuranceCompany, userID, UITools.getThirdPartyPolicy());
    }

    public static void createComprehensivePolicy(InsuranceCompany insuranceCompany) throws PolicyException // Good way
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Create Comprehensive Policy---");

        System.out.println("Enter User ID to add Policy to:");
        int userID = sc.nextInt();
        sc.nextLine();
        UITools.addPolicy(insuranceCompany, userID, UITools.getComprehensivePolicy());
    }

    public static void createThirdParty(InsuranceCompany insuranceCompany, int userID) throws PolicyException // Good way
    {
        System.out.println("---Create ThirdParty Policy---");
        UITools.addPolicy(insuranceCompany, userID, UITools.getThirdPartyPolicy());
    }

    public static void createComprehensivePolicy(InsuranceCompany insuranceCompany, int userID) throws PolicyException // Good way
    {
        System.out.println("---Create Comprehensive Policy---");
        UITools.addPolicy(insuranceCompany, userID, UITools.getComprehensivePolicy());
    }

    public static void printUserInformation(InsuranceCompany insuranceCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Print User Information---");
        System.out.println("Enter User ID:");
        int userID = sc.nextInt();
        //insuranceCompany.printPolicies(userID); // does not print user info
        insuranceCompany.printUser(userID);// better
    }

    public static void printUser(InsuranceCompany insuranceCompany, int userID) {
        System.out.println("---User Information---");
        insuranceCompany.printUser(userID);
    }

    public static void filterByCarModel(InsuranceCompany insuranceCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Car Model---");
        System.out.println("Enter Car Model:");
        String carModel = sc.nextLine();
        HashMap<Integer, InsurancePolicy> filteredPolicies = insuranceCompany.filterByCarModel(carModel);
        System.out.println("Filtered Policies--");
        InsurancePolicy.printPolicies(filteredPolicies);
        double totalPayment = InsurancePolicy.calcTotalPayments(filteredPolicies, insuranceCompany.getFlatRate());
        System.out.println("Total Payment: $" + totalPayment);
    }

    public static void filterByExpiryDate(InsuranceCompany insuranceCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Filter By Expiry Date---");
        MyDate expiryDate = UITools.getDate();
        System.out.println("Enter User ID:");
        int userId = sc.nextInt();
        HashMap<Integer, InsurancePolicy> filteredPolicies = insuranceCompany.filterByExpiryDate(userId, expiryDate);
        System.out.println("Expired Policies--");
        InsurancePolicy.printPolicies(filteredPolicies);
    }

    public static void updateAddress(InsuranceCompany insuranceCompany) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Update Address---");
        System.out.println("Enter User ID for Address to be changed:");
        int userId = sc.nextInt();
        Address newAddress = UITools.getAddress();
        User user = insuranceCompany.findUser(userId);
        if (user != null) {
            user.setAddress(newAddress);
            System.out.println("New Address:");
            System.out.println(user.getAddress());
        } else {
            System.out.println("User has not been found");
        }
    }

    public static void updateAddress(InsuranceCompany insuranceCompany, int userID) {
        Address newAddress = UITools.getAddress();
        User user = insuranceCompany.findUser(userID);
        if (user != null) {
            user.setAddress(newAddress);
            System.out.println("New Address:");
            System.out.println(user.getAddress());
        } else {
            System.out.println("User has not been found");
        }
    }

    //User menu options

    public static void createThirdPartyPolicy(User user) throws PolicyException {
        UITools.addPolicy(user, UITools.getThirdPartyPolicy());
        //or this if we only send the userID to the menu and using company as a proxy
        //UITools.addPolicy(insuranceCompany,userID,UITools.getThirdPartyPolicy());
    }

    public static void createComprehensivePolicy(User user) throws PolicyException {
        UITools.addPolicy(user, UITools.getComprehensivePolicy());
    }

    public static void printUser(User user) {
        System.out.println("---User Information---");
        user.print();
    }

    public static void updateAddress(User user) {
//        Scanner sc = new Scanner(System.in);
        System.out.println("---Update Address---");
//        System.out.println("Address--");
//        System.out.println("Enter Street Number:");
//        int steetNum = sc.nextInt();
//        sc.nextLine();
//        System.out.println("Enter Street Name:");
//        String streetName = sc.nextLine();
//        System.out.println("Enter Suburb:");
//        String suburb = sc.nextLine();
//        System.out.println("Enter City:");
//        String city = sc .nextLine();
//        user.setAddress(new Address(steetNum, streetName, suburb, city));
        user.setAddress(UITools.getAddress());
        System.out.println("New Address:");
        System.out.println(user.getAddress());
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter Key to Continue...");
        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    //    Lab 5
//    public static void reportForGivenUser(InsuranceCompany insuranceCompany) {
//        Scanner sc = new Scanner(System.in);
//        int userID = sc.nextInt();
//        sc.nextLine();
//        User user = insuranceCompany.findUser(userID);
//        user.reportPaymentsPerCarModel(4);
//    }
}
