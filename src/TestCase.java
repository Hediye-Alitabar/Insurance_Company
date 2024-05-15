import java.util.ArrayList;
import java.util.HashMap;

public class TestCase {

    static int FLAT_RATE;
    public static void testCode(InsuranceCompany insuranceCompany) throws CloneNotSupportedException, PolicyException {
        double flatRate = 12;
        System.out.println("---Test Code---");

        //Test one: Create few users, several policies and one insuranceCompany
        MyDate date1 = new MyDate(2012, 9, 23);
        MyDate date2 = new MyDate(2012, 12, 12);
        MyDate date3 = new MyDate(2020, 1, 1);
        MyDate date4 = new MyDate(2020, 2, 3);
        MyDate date5 = new MyDate(2017, 3, 1);
        MyDate date6 = new MyDate(2013, 7, 4);
        MyDate date7 = new MyDate(2014, 9, 23);
        MyDate date8 = new MyDate(2015, 10, 19);
        Car car1 = new Car("AUDI-A3", CarType.LUX, 2010, 24500);
        Car car2 = new Car("SRX", CarType.SUV, 2020, 34500);
        Car car3 = new Car("Camry", CarType.SED, 2013, 14500);
        Car car4 = new Car("AUDI-A1", CarType.LUX, 2020, 74500);
        InsurancePolicy policy1 = new ComprehensivePolicy("Alex", 300001, car1, 50, date1, 76, 53);
        InsurancePolicy policy2 = new ThirdPartyPolicy("Sara", 300002, car2, 12, date2, "Good");
        InsurancePolicy policy3 = new ComprehensivePolicy("Robert", 300003, car3, 50, date3, 76, 53);
        ;
        InsurancePolicy policy4 = new ThirdPartyPolicy("Alex", 300004, car4, 12, date4, "Good");
        InsurancePolicy policy5 = new ThirdPartyPolicy("John", 300005, car4, 12, date5, "Good");
        InsurancePolicy policy6 = new ThirdPartyPolicy("Jim", 300006, car2, 12, date6, "Good");
        InsurancePolicy policy7 = new ComprehensivePolicy("Joe", 300007, car1, 50, date7, 26, 23);
        ;
        InsurancePolicy policy8 = new ComprehensivePolicy("Sara", 300008, car3, 50, date8, 36, 33);
        ;
        InsurancePolicy policy9 = new ComprehensivePolicy("Jeff", 300009, car2, 50, date1, 46, 43);
        ;
        InsurancePolicy policy10 = new ComprehensivePolicy("Sara", 300010, car1, 50, date2, 56, 73);
        ;
        InsurancePolicy policy11 = new ThirdPartyPolicy("Tom", 300011, car3, 12, date3, "Good");

        Address address1 = new Address(7, "Lincoln St", "Westfield", "New York");
        Address address2 = new Address(55, "Woods St", "Eastern Beach", "Sydney");
        Address address3 = new Address(17, "Stones St", "Trotwoods", "London");
        Address address4 = new Address(21, "Washington St", "Coastfield", "Chicago");
        User user1 = new User(101, "Jeff Anderson", address1, "pass1");
        User user2 = new User(102, "Tom Jones", address2, "pass2");
        User user3 = new User(103, "Elizabeth Cooper", address3, "pass3");
        User user4 = new User(100,"john Wright", address4, "pass4");
        User user5 = new User(105, "Allison Bird", new Address(41, "Grey st", "Monavale", "Melbourne"), "password123");

        //lab1 ---Test the InsurancePolicy Class---------------------------------------------------------------------------------

        ArrayList<InsurancePolicy> policies = new ArrayList<>(); //ArrayList of parent class
        policies.add(policy1); //adding policies to the list
        policies.add(policy2);
        policies.add(policy3);
        policies.add(policy4);
        policies.add(policy5);
        policies.add(policy6);
        policies.add(policy7);


        for (InsurancePolicy policy : policies) {
            policy.print();                 //print using print method
        }

        for (InsurancePolicy policy : policies) {
            System.out.println(policy); //print using toString method
        }

        double total = 0;
        for (InsurancePolicy policy : policies) {
            total += policy.calcPremium(flatRate);
        }
        System.out.println("Total pay = " + total);

        //lab2 -----------Test the User class-----------------------------------------------------------------------

        //add policiess to the user
        UITools.addPolicy(user1, policy1);
        UITools.addPolicy(user1, policy1);
        UITools.addPolicy(user1, policy1);
        //call print() method for user1
        user1.print();
        //print user1 using toString
        System.out.println(user1.toString());
        //Find a policy by using findPolicy() to invalid ID
        InsurancePolicy foundPolicy = user1.findPolicy(300003);
        if (foundPolicy == null)//---------------------12345 is not valid
        {
            System.out.println("Policy has not been found");
        } else {
            foundPolicy.print();
        }

        foundPolicy = user1.findPolicy(300001);
        if (foundPolicy == null) {
            System.out.println("Policy has not been found");
        } else {
            foundPolicy.carPriceRise(0.1);
            foundPolicy.setPolicyHolderName("Robert");
            foundPolicy.setCarModel("Nissan Dualis");
            foundPolicy.print();
        }

        user1.setCity("Wollongong"); //instead of user1.getAddress().setCity("Wollongong");
        user1.print();
        //ask customer to enter a new address
        //COMENTED FOR ASSIGNEMNT I
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Enter the new street number: ");
//        int newStreetNum = scan.nextInt();
//        System.out.print("Enter the new street name: ");
//        String newStreetName = scan.next();
//        System.out.print("Enter the new suburb: ");
//        String newSuburb = scan.next();
//        System.out.print("Enter the new city: ");
//        String newCity = scan.next();
//        Address newAddress = new Address(newStreetNum, newStreetName, newSuburb, newCity); // by using scanner
        Address newAddress = new Address(31, "Gray Street", "Liverpool", "Sydney");// hardcoded for assigment I
        user1.setAddress(newAddress);
        user1.print();
        //print the total  payment of the user
        System.out.printf("The total payment of user: %.3f%n", user1.calcTotalPremiums(flatRate));
        //add 10% to the price of cars for all the policies this user owns and print again
        user1.carPriceRiseAll(0.1);
        System.out.printf("The new total payment of user: %.3f%n", user1.calcTotalPremiums(flatRate));

        //ask customer to enter a carModel
        //System.out.print("Enter a car model: ");
        String userCarModel = "Toyota";//scan.next(); // change scanner to hardcoded

        //call filterByCarModel method for the user and store the filtered list
//        ArrayList<InsurancePolicy> filteredPolicies = user1.filterByCarModel(userCarModel);
        HashMap<Integer,InsurancePolicy> filteredPolicies = user1.filterByCarModel(userCarModel);
        //print the filtered list by calling the static method inside InsurancePolicy
        InsurancePolicy.printPolicies(filteredPolicies);


        // ------ lab 3 testing the company --------------------------------------------------------------------------------

        //one  object of InsuranceCompany class
        //InsuranceCompany insuranceCompany = new InsuranceCompany("Hooman Company", "admin", "admin", 20); // we get it as an input parameter now

        // Log in once successfull and once not successfull
        UITools.validateAdmin(insuranceCompany, "andy", "andy12"); // not successfull login

        if (!insuranceCompany.validateAdmin("admin", "admin")) {
            System.out.println("Admin Login unsuccessful");
        } else // login successfull
        {
            System.out.println("------------------------------------");
            // Add users to the insurance company with both methods

            //Add users
            UITools.addUser(insuranceCompany, user3);
            UITools.addUser(insuranceCompany, user4);
            UITools.addUser(insuranceCompany, user5);
            UITools.addUser(insuranceCompany, user3);
            UITools.addUser(insuranceCompany, user1);
            UITools.addUser(insuranceCompany, user2);
//            UITools.addUser(insuranceCompany, "Mark Williams", 108, new Address(9, "Wind St", "West Wollongong", "Wollongong"), "pass123");

            System.out.println("-------------------------------------");

            //Add policies to users

//            insuranceCompany.addPolicy(1, policy1);  //wrong not checkign the output of the boolean method
//            insuranceCompany.addPolicy(33, policy1); // wrong not checking the output

            //correct way of adding policies as below

            //User1
            UITools.addPolicy(insuranceCompany, user1.getUserID(), policy1);
            UITools.addPolicy(insuranceCompany, user1.getUserID(), policy2);

            //User2
            UITools.addPolicy(insuranceCompany, user2.getUserID(), policy3);
            UITools.addPolicy(insuranceCompany, user2.getUserID(), policy4);

            //User3
            UITools.addPolicy(insuranceCompany, user3.getUserID(), policy5);

            //User4
            UITools.addPolicy(insuranceCompany, user4.getUserID(), policy6);
            UITools.addPolicy(insuranceCompany, user4.getUserID(), policy7);

            //User5
            UITools.addPolicy(insuranceCompany, user5.getUserID(), policy2);
            
            UITools.addPolicy(insuranceCompany, 489123, policy1); //direct userID
            UITools.addPolicy(insuranceCompany, 12345, policy2); //wrong userID

            // Add several policies to some users by calling createComprehensivePolicy() and createThirdPartyPolicy()

            insuranceCompany.createComprehensivePolicy(1, "Alex", 120, car1, 50, date1, 76, 53); // wrong

            //correct ways by using the UI methods to check the Boolean

            UITools.addComprehensivePolicy(insuranceCompany, 12, "Sara", 1234, car4, 12, new MyDate(2021, 10, 18), 5, 4);

            //Duplicate Policy
            UITools.addComprehensivePolicy(insuranceCompany, 12, "Sara", 1234, car4, 12, new MyDate(2021, 10, 18), 5, 4);

            System.out.println();

            System.out.println("--------------------------------------");

            // Ask customer to enter a userID and and print the user and all of his policies by using methods inside insuranceCompany
            System.out.println("--------------------------------------");
            //Scanner scan = new Scanner(System.in);
            //System.out.print("Enter a userID: ");
            int userID = 143543; //scan.nextInt();//change it to hardcoded rather than scanner
            insuranceCompany.printPolicies(userID);

            //or finding the user and user methods

            //Test user ID
            System.out.println("User ID find test and print");
//            int testUserID = user0.getUserID();
//            User user = insuranceCompany.findUser(testUserID); // to get user ID
            User user = insuranceCompany.findUser(143543); //direct numebr for userID

            if (user != null) {
                user.print();
                user.printPolicies(flatRate);
                System.out.println();
            } else {
                System.out.println("User has not been found");
            }

            /*
            Ask customer to enter a userID and policyID and find a policy with the given policyID for that userID by calling findPolicy (int userID ,int policyID) and then print the policy.
            */

            System.out.println("--------------------------------------");
            System.out.println("Find policy Test");
            System.out.print("Enter a userID: ");
            userID = 1345;//scan.nextInt();
            System.out.print("Enter a policyID: ");
            int policyID = 300012; //scan.nextInt();
            foundPolicy = insuranceCompany.findPolicy(userID, policyID);

            if (foundPolicy == null) {
                System.out.println("Policy has not been found");
            } else {
                foundPolicy.print();
            }

            System.out.println("--------------------------------------");
            // print all users inside the company
            System.out.println("Print the Company including All users");
            insuranceCompany.print();
            System.out.println();

            System.out.println("Print company information with ToString()");
            System.out.println(insuranceCompany);
            System.out.println();

            // Print the total payments for a given userID by calling calcTotalPayments (int userID)
            // System.out.print("Enter a userID: ");
            // int userID = scan.nextInt();
            //System.out.printf("Total payments for this user: %.2f%n", insuranceCompany.calcTotalPayments(userID));

            System.out.println("Total payments for user 1345");
            System.out.printf("Total payments for this user: %.2f%n", insuranceCompany.calcTotalPayments(1345));
            System.out.println();

            // Print the total payments for all users in the company
            System.out.printf("Total payments for all users in company: %.2f\n", insuranceCompany.calcTotalPayments());
            // you also can move these messages and prints to a method inside UITOOLS

            System.out.println();

            System.out.println("----------------All Policies----------------------");
            // Call allPolicies() for the insuranceCompany and store it in an ArrayList and print the list by using InsurancePolicy.printPolicies()
//            ArrayList<InsurancePolicy> insurancePoliciesCompany = insuranceCompany.allPolicies();
            HashMap<Integer,InsurancePolicy> insurancePoliciesCompany = insuranceCompany.allPolicies();
            InsurancePolicy.printPolicies(insurancePoliciesCompany);

            System.out.println("--------------------------------------");
            insuranceCompany.carPriceRise(0.1);
            System.out.printf("The Total Payment of users after 10 percent rise: %.2f%n", insuranceCompany.calcTotalPayments());
            System.out.println("Print All the users after car price rise");
            insuranceCompany.print();

            System.out.println("Print a user after car price rise");
//            insuranceCompany.carPriceRise(user0.getUserID(),0.1); //wrong as it does not check the output
//            UITools.carPriceRise(insuranceCompany,1345,0.1); //or
            UITools.carPriceRise(insuranceCompany, user3.getUserID(), 0.1);
            user3.print();

            //For a given userID and expiry date call filterByExpiryDate (int userID, MyDate date), store the filtered list and print the list by using InsurancePolicy.printPolicies()
            System.out.println("-----------------filter user policies by Expiry Date--------------------");
//            ArrayList<InsurancePolicy> filteredByExpiryDatePolicies1 = insuranceCompany.filterByExpiryDate(user3.getUserID(), new MyDate(2020, 7, 15));
            HashMap<Integer,InsurancePolicy> filteredByExpiryDatePolicies1 = insuranceCompany.filterByExpiryDate(user3.getUserID(), new MyDate(2020, 7, 15));
            InsurancePolicy.printPolicies(filteredByExpiryDatePolicies1);
            System.out.println();

            // For a given car model and userID call insuranceCompany.filterByCarModel (String carModel) and print the filtered list
            System.out.println("-----------------filter user policies by Car Model------------------");
            InsurancePolicy.printPolicies(insuranceCompany.filterByCarModel(user4.getUserID(), "Toyota")); //Without the seperate list
            System.out.println();

            //For the whole company call filterByExpiryDate
//            ArrayList<InsurancePolicy> filteredByExpriyDatePolicies = insuranceCompany.filterByExpiryDate(new MyDate(2015, 6, 15));
            HashMap<Integer,InsurancePolicy> filteredByExpriyDatePolicies = insuranceCompany.filterByExpiryDate(new MyDate(2015, 6, 15));
            System.out.println("Filter By Expiry Date");
            InsurancePolicy.printPolicies(filteredByExpriyDatePolicies);//or no seperate list and call it directly
            System.out.println();

            //For the whole company call filterByExpiryDate and print the list
//            ArrayList<InsurancePolicy> carModelPolicies = insuranceCompany.filterByCarModel("Toyota");
            HashMap<Integer,InsurancePolicy> carModelPolicies = insuranceCompany.filterByCarModel("Toyota");
            System.out.println("Policies by Car Model");
            InsurancePolicy.printPolicies(carModelPolicies);
            System.out.println();

            // Ask user to enter a date (year, month, and day) and call filterByExpiryDate (MyDate date) and print the filtered list
            //commneted for assigment I
//            System.out.println("--------------------------------------");
//            System.out.print("Enter a year: ");
//            int year = scan.nextInt();
//            System.out.print("Enter a month: ");
//            int month = scan.nextInt();
//            System.out.print("Enter a day: ");
//            int day = scan.nextInt();
//            MyDate userDate = new MyDate(year, month, day);
//            ArrayList<InsurancePolicy> filteredList = insuranceCompany.filterByExpiryDate(userDate);
//            InsurancePolicy.printPolicies(filteredList);

            // Find a user with the given ID (valid) and save it in a user object. Ask user to provide a new address and change the current address for the given user
            System.out.println("--------------------------------------");
            User foundUser = insuranceCompany.findUser(1345);
            Address address = new Address(114, "Sara St", "Bondi", "Sydney");// instead of scanner
            if (foundUser != null) {
                System.out.println("Found User:" + foundUser.getUserID());
                foundUser.setAddress(address);
                System.out.println("New Address:" + foundUser.getAddress());
                System.out.println();
            } else {
                System.out.println("User has not been found");
            }

            // standard test

            //Populate Distinct City Names
            System.out.println("---PopulateDistinctCityNames---");
            ArrayList<String> uniqueCities = insuranceCompany.populateDistinctCityNames();

            for (String city : uniqueCities) {
                System.out.print(city + " , ");
            }
            System.out.println();

            //Get the total payments for a city
            System.out.println("---Get Total Payment For a City (Wollongong)---");
            double totalPaymentForACity = insuranceCompany.getTotalPaymentForCity("Wollongong");
            System.out.println("Total payments for City Wollongong:" + totalPaymentForACity);
            System.out.println();

            //Get the total payments for each city in a list
            System.out.println("---Get Total Payment for all City---");
            //Uses unique cities form test above
            ArrayList<Double> totalPaymentForAllCity = insuranceCompany.getTotalPaymentPerCity(uniqueCities);

            //Prints the city payment report
            System.out.println("---Report Total Payments for all Cities---");
            insuranceCompany.reportPaymentPerCity(uniqueCities, totalPaymentForAllCity);

            //use the other report method without the need to send the seperate list
            System.out.println("---Report Total Payments for all Cities report method with no parameters---");
            insuranceCompany.reportPaymentPerCity();

            //Advanced Test Case

            //Populate Distinct Car Models for all users inside the company
            System.out.println("---Populate Distinct Car Models---");
            ArrayList<String> uniqueModels = insuranceCompany.populateDistinctCarModels();
            for (String model : uniqueModels) {
                System.out.print(model + " , ");
            }

            System.out.println();

            //Get the total payments for each model in a list
            System.out.println("---Get Total Payment for all models---");
            //Uses unique models form test above
            ArrayList<Double> totalPaymentForAllModels = insuranceCompany.getTotalPaymentPerCarModel(uniqueModels);

            //Get the count for each model in a list
            System.out.println("---Get count for all models---");
            //Uses unique models form test above
            ArrayList<Integer> countForAllModels = insuranceCompany.getTotalCountPerCarModel(uniqueModels);


            //Prints the city payment report
            System.out.println("---Report Total and average Payments for all Models---");
            insuranceCompany.reportPaymentsPerCarModel(uniqueModels, countForAllModels, totalPaymentForAllModels);

            // or using the report with no parameter
            System.out.println("---Report Total and average Payments for all Models with the report method with no parameters---");
            insuranceCompany.reportPaymentsPerCarModel();

            //Populate Distinct Car Models for one user
            System.out.println("---Populate Distinct Car Models for user 2 ---");
            uniqueModels = user2.populateDistinctCarModels();

            for (String model : uniqueModels) {
                System.out.print(model + " , ");
            }

            System.out.println();

            //Get the total payments for a car model
            System.out.println("---Get Total Payment For a Car model user 2---");
            double totalPaymentForAModel = user2.getTotalPaymentForCarModel("Toyota", flatRate);
            System.out.println("Total payment for Toyota : user 2 : " + totalPaymentForAModel);
            System.out.println();

            //Get the total count for a car model
            System.out.println("---Get the count For a car model user 2---");
            int countForAModel = user2.getTotalCountForCarModel("toyota");
            System.out.println("Count for toyota : user 2 : " + countForAModel);
            System.out.println();

            //Get the total payments for each model in a list
            System.out.println("---Get Total Payment for all models -  user2: ---");
            //Uses unique models form test above
            totalPaymentForAllModels = user2.getTotalPaymentPerCarModel(uniqueModels, flatRate);

            //Get the count for each model in a list
            System.out.println("---Get count for all models - user2: ---");
            //Uses unique models form test above
            countForAllModels = user2.getTotalCountPerCarModel(uniqueModels);

            //Prints the city payment report
            System.out.println("---Report Total and average Payments for all car models user 2: ---");
            user2.reportPaymentsPerCarModel(uniqueModels, countForAllModels, totalPaymentForAllCity);

            // or using the report with no parameter
            System.out.println("---Report Total and average Payments for all car models user 3: with the report method with no parameters---");
            user2.reportPaymentsPerCarModel(flatRate);
        } //everything inside else for admin login

//        Lab 4
        System.out.println("--------------Lab 4-----------------");
//        Shallow copy of policies
        ArrayList<InsurancePolicy> deepCopyPolicies = user1.deepCopyPolicies();
//        Deep copy of policies
        ArrayList<InsurancePolicy> shallowCopyPolicies = user1.shallowCopyPolicies();
        user1.setCity("New York");
//        Create new policy for the user
        ThirdPartyPolicy policy = new ThirdPartyPolicy("Jack", 300013, car1, 2, date1, "No comment");
        UITools.addPolicy(user1, policy);
//        Sort policies by date
        ArrayList<InsurancePolicy> sortedPolicies = user1.sortPoliciesByDate();
        System.out.println("Original: ");
        user1.print();
        System.out.println("\nDeep copy of policies: ");
        InsurancePolicy.printPolicies(deepCopyPolicies);
        System.out.println("\nShallow copy of policies: ");
        InsurancePolicy.printPolicies(shallowCopyPolicies);
        System.out.println("Sorted policies: ");
        InsurancePolicy.printPolicies(sortedPolicies);
//        Deep copy of users
//        ArrayList<User> deepCopyUsers = insuranceCompany.deepCopyUsers();
        HashMap<Integer,User> deepCopyUsers = insuranceCompany.deepCopyUsers();
//        Shallow copy of users
//        ArrayList<User> shallowCopyUsers = insuranceCompany.shallowCopyUsers();
        HashMap<Integer,User> shallowCopyUsers = insuranceCompany.shallowCopyUsers();
        User user6 = new User(106, "Aras Carter", address3, "pass6");
        UITools.addUser(insuranceCompany, user6);
        UITools.addPolicy(insuranceCompany, user6.getUserID(), policy4);
        UITools.addPolicy(insuranceCompany, user6.getUserID(), policy6);
//        Sort users by city
        ArrayList<User> sortedUsers = insuranceCompany.sortUsers();
        System.out.println("Sorted Users: ");
        User.printUsers(sortedUsers);
        System.out.println("\nDeep copy of users: ");
        User.printUsers(deepCopyUsers);
        System.out.println("\nShallow copy of users: ");
        User.shallowCopy(shallowCopyUsers);
//        Make clone of insuranceCompany
        User user = insuranceCompany.findUser(6);
//        user.setCity("New York");
        System.out.println("\nOriginal: ");
        insuranceCompany.print();
        System.out.println("\nAfter Change: \nShallow Copy");
        User.printUsers(shallowCopyUsers);
        System.out.println("\nDeep copy: ");
        User.printUsers(deepCopyUsers);

//        Lab 5
        System.out.println("--------------Lab 5-----------------");
        HashMap<Integer,InsurancePolicy> deepCopyPoliciesHashMap = user2.deepCopyPoliciesHashMap();
//        Deep copy of policies
        HashMap<Integer,InsurancePolicy> shallowCopyPoliciesHashMap = user2.shallowCopyPoliciesHashMap();
        user2.setCity("New York");
        ThirdPartyPolicy policyHashMap = new ThirdPartyPolicy("Jhon", 300017, car2, 2, date2, "No comment");
        UITools.addPolicy(user2, policyHashMap);
        user2.print();
        System.out.println("\nDeep copy of policies: ");
        InsurancePolicy.printPolicies(deepCopyPoliciesHashMap);
        System.out.println("\nShallow copy of policies: ");
        InsurancePolicy.printPolicies(shallowCopyPoliciesHashMap);
        System.out.println("Sorted policies: ");
        InsurancePolicy.printPolicies(sortedPolicies);
        ArrayList<InsurancePolicy> sortPolicies = user1.sortPoliciesByDate();
        HashMap<Integer,User> deepCopyUsersHashMap = insuranceCompany.deepCopyUsers();
//        Shallow copy of users
        HashMap<Integer,User> shallowCopyUsersHashMap = insuranceCompany.shallowCopyUsers();
        User user7 = new User(107, "Arad Walker", address4, "MNR");
        UITools.addUser(insuranceCompany, user7);
        UITools.addPolicy(insuranceCompany, user7.getUserID(), policy2);
        ArrayList<User> sortUsers = insuranceCompany.sortUsers();
        System.out.println("Sorted Users: ");
        User.printUsers(sortedUsers);
        System.out.println("\nDeep copy of users: ");
        User.printUsers(deepCopyUsers);
//        System.out.println("\nShallow copy of users ------------: ");
//        User.shallowCopy(shallowCopyUsers);
    }
}
