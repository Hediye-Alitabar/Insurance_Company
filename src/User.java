   import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class User implements Cloneable, Comparable<User> {
    private static int count = 0;
    private String name;
    private int userID;
    private String password;
    private Address address;
    //    private ArrayList<InsurancePolicy> policies;
    private HashMap<Integer, InsurancePolicy> policies;

    public User(String name, Address address, String password) {
        this.name = name;
        // 3) ID generation for the User to be automatic by using a static count and increment it.
        this.userID = User.count++;
        this.password = password;
        this.address = address;
//        this.policies = new ArrayList<>();
        this.policies = new HashMap<Integer, InsurancePolicy>();
    }

    public User(int userID, String name, Address address, String password) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.address = address;
        this.policies = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return password;
    }
 
    public void setUserPassword(String pass) {
        password = pass;
    }
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        address = adress;
    }

    public void setCity(String ct) {
        address.setCity(ct);
    }

    public String getCity() {
        return address.getCity();
    }

    public HashMap<Integer, InsurancePolicy> getPolicies() {
        return policies;
    }

    public boolean validateUser(int userID, String password) // and not username as username might not be unique as we didn't check it
    {
        if ((this.userID == userID) && this.password.equals(password)) {
            return true;
        }
        return false;
    }
    
     public boolean validateUser(String userName, String password) // and not username as username might not be unique as we didn't check it
    {
        if (this.name.equals(userName) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    // finds the policy and returns it. Returns null if policyID does not exist.
    public InsurancePolicy findPolicy(int policyID) {
        return policies.get(policyID);
    }

    // adds a policy, returns true if successful (when policyID is unique) and returns false if not.
    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.getPolicyID()) == null) {
//            policies.add(policy);
            policies.put(policy.getPolicyID(), policy);
            return true;
        }
        return false;
    }

    // returns the total premium payments for this user by calling the corresponding static method inside InsurancePolicy.
    public double calcTotalPremiums(int flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    // calls the corresponding static method inside InsurancePolicy to increase the car price for all the policies the user owns.
    public void carPriceRiseAll(double risePercent) {
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    // creates a Third-Party Policy and adds it to the list of the user’s policies, returns false if the id is not unique.
    // Create an object from ThirdPartyPolicy and call addPolicy method to add it to the list
//    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
//        return addPolicy(new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments));
//    }

    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
        ThirdPartyPolicy thirdPartyPolicy = null;
        try {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        } catch (PolicyException e) {
            thirdPartyPolicy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return addPolicy(thirdPartyPolicy);
    }

    // creates a Comprehensive Policy and adds it to the list of the user’s policies, returns false if the id is not unique.
    // Create an object from ComprehensivePolicy and call addPolicy method to add it to the list
//    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
//        return addPolicy(new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level));
//    }

    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        ComprehensivePolicy ComprehensivePolicy = null;
        try {
            ComprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        } catch (PolicyException e) {
            ComprehensivePolicy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        return addPolicy(ComprehensivePolicy);
    }

// filters the policies and returns a list of policies with the car model containing the given carModel by calling the corresponding static method inside InsurancePolicy.
//    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
//        return InsurancePolicy.filterByCarModel(policies, carModel);
//    }


//    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
//        return InsurancePolicy.filterByExpiryDate(policies, date);
//    }


    // prints all the information of this user including all the policies information
    public void printUserInformation() {
        System.out.println("User ID:" + userID + " Name:" + name);
        System.out.println(" Address: ");
        address.print();
    }

    public void print() {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
        printUserInformation(); //instead of previous 3 lines
        InsurancePolicy.printPolicies(policies);
    }

    public void print(double flatRate) {
        // System.out.println("User ID:" + userID + " Name:" + name);
        // System.out.println(" Address: ");
        // address.print();
        printUserInformation(); //instead of previous 3 lines
        InsurancePolicy.printPolicies(policies);
    }

    // converts the user and his/her policies to String
    @Override
    public String toString() {
        String output = "User ID:" + userID + " Name:" + name + " Address: " + address.toString() + " Policies: \n";
        for (InsurancePolicy policy : policies.values()) {
            output += policy.toString() + "\n";
        }
        return output;
    }


    // prints all the policies this user owns as well as the premium payment for each policy by calling the corresponding static method inside InsurancePolicy.
    public void printPolicies(double flatRate) {
//        System.out.println("List of Policies:");
//        for(InsurancePolicy policy : policies)
//        {
//            System.out.println(policy);
//            System.out.println("Premium ="+ policy.calcPremium(flatRate));
//        }
        InsurancePolicy.printPolicies(policies, flatRate);
        System.out.println("Total Premium Payments:" + calcTotalPremiums(flatRate));
    }

    public static void printUsers(ArrayList<User> users) {
        for (User user : users)
            System.out.println(user);
    }


    // calculation
    public double calcTotalPremiums(double flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> models = new ArrayList<String>();
        for (InsurancePolicy policy : policies.values()) {
            boolean found = false;
            for (String model : models) {
                if (policy.getCarModel().equals(model)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                models.add(policy.getCarModel());
        }
        return models;
    }

    public int getTotalCountForCarModel(String carModel) {
        int count = 0;
        for (InsurancePolicy policy : policies.values()) {
            if (policy.getCarModel().equals(carModel))
                count++;
        }
        return count;
    }

    public double getTotalPaymentForCarModel(String carModel, double flatRate) {
        double total = 0;
        for (InsurancePolicy policy : policies.values()) {
            if (policy.getCarModel().equals(carModel))
                total += policy.calcPremium(flatRate);
        }
        return total;
    }

    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
//            int count=0;
        for (String model : carModels) {
            //assuming that the other method is not done. This code was correct:
//            int count=0;
//            for( InsurancePolicy policy:policies)
//            {
//                if(policy.getCarModel().equals(model))
//                    count++;
//            }
//            totalCounts.add(count);

            //a better way by using the other method
            totalCounts.add(getTotalCountForCarModel(model));
        }
        return totalCounts;
    }


    ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels, double flatRate) {
        ArrayList<Double> totalPayments = new ArrayList<Double>();
//            double payment=0;
        for (String model : carModels) {
            //assuming that the other method is not done. The folowwing code should have been done
//            double total=0;
//            for( InsurancePolicy policy:policies)
//            {
//                if(policy.getCarModel().equals(model))
//                    total+=policy.calcPremium(flatRate);
//            }
//            totalPayments.add(total);

            //a better way now by using the other method
            totalPayments.add(getTotalPaymentForCarModel(model, flatRate));
        }
        return totalPayments;
    }


    //as spec but it is not good. all lists are sent as parameters
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> premiums) {
        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
        // or System.out.printf("%-15s%-30s%-30s%-%n","Car Model","Total Premium","Average Premium");
        for (int i = 0; i < counts.size(); i++)
            System.out.println(carModels.get(i) + " \t \t \t " + premiums.get(i) + " \t \t \t " + premiums.get(i) / (double) counts.get(i));
        //or System.out.printf("%-15s%-30s%-30s%-%n",carModels.get(i),premiums.get(i),premiums.get(i)/(double)counts.get(i));

        System.out.println("------------------------------------");
    }


    //a list of given models and flatRate is sent
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, double flatRate) {
        ArrayList<Integer> counts = getTotalCountPerCarModel(carModels);
        ArrayList<Double> premiums = getTotalPaymentPerCarModel(carModels, flatRate);
        reportPaymentsPerCarModel(carModels, counts, premiums); // instead of doing this again as below

//        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
//        for (int i=0;i<counts.size();i++)
//            System.out.println(carModels.get(i)+" \t \t \t "+premiums.get(i)+" \t \t \t "+premiums.get(i)/(double)counts.get(i));
    }

    public void reportPaymentsPerCarModel(double flatRate) // for all models
    {
        ArrayList<String> carModels = populateDistinctCarModels();
        reportPaymentsPerCarModel(carModels, flatRate); // better than below

//        ArrayList<String> carModels=populateDistinctCarModels();
//        ArrayList<Integer> counts=getTotalCountPerCarModel(carModels);
//        ArrayList<Double> premiums=getTotalPaymentPerCarModel(carModels, flatRate);
//        reportPaymentsPerCarModel (carModels, counts, premiums);
//        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
//        for (int i=0;i<counts.size();i++)
//            System.out.println(carModels.get(i)+" \t \t \t "+premiums.get(i)+" \t \t \t "+premiums.get(i)/(double)counts.get(i));
    }

    //    Lab 4
//    Copy Constructor
    public User(User user) {
        this.name = user.name;
        this.password = user.password;
        this.userID = user.userID;
        this.address = new Address(user.address);
        this.policies = new HashMap<>();
        for (InsurancePolicy policy : user.policies.values()) {
            if (policy instanceof ComprehensivePolicy) {
                policies.put(policy.getPolicyID(), new ComprehensivePolicy((ComprehensivePolicy) policy));
            }
            if (policy instanceof ThirdPartyPolicy) {
                policies.put(policy.getPolicyID(), new ThirdPartyPolicy((ThirdPartyPolicy) policy));
            }

        }

    }

    public User clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.address = address.clone();
        user.policies = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            user.policies.put(policy.policyID, policy.clone());
        }
        return user;
    }

    //   Shallow copy of policies
//    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
//        ArrayList<User> shallowCopy = new ArrayList<>();
//        for (User user : users) {
//            shallowCopy.add(user); //user.clone()
//        }
//        return shallowCopy;
//    }
//
//    //   Deep copy of policies by using clone()
//    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
//        ArrayList<User> deepCopy = new ArrayList<>();
//        for (User user : users) {
//            deepCopy.add(user.clone());
//        }
//        return deepCopy;
//    }
//
//    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
//        ArrayList<InsurancePolicy> deepCopy = InsurancePolicy.deepCopy(policies);
//        return deepCopy;
//    }
//
//    public ArrayList<InsurancePolicy> shallowCopyPolicies() throws CloneNotSupportedException {
//        ArrayList<InsurancePolicy> shallowCopy = InsurancePolicy.shallowCopy(policies);
//        return shallowCopy;
//    }


    public int compareTo(User otherUser) {
        return address.compareTo(otherUser.address);
    }

    public int compareTo1(User user) {
        int flatRate = 4;
        if (calcTotalPremiums(flatRate) > user.calcTotalPremiums(flatRate)) {
            return 1;
        } else if (calcTotalPremiums(flatRate) < user.calcTotalPremiums(flatRate)) {
            return -1;
        }
        return 0;
    }
    
    public int CompareTo2(User user)
    {
        return name.compareTo(user.name);
    }
    
//    public int compareTo2(InsurancePolicy policy) {
//        int flatRate = 4;
//        if (calcTotalPremiums(flatRate) > user.calcTotalPremiums(flatRate)) {
//            return 1;
//        } else if (calcTotalPremiums(flatRate) < user.calcTotalPremiums(flatRate)) {
//            return -1;
//        }
//        return 0;
//    }


    //    Sort policies by date
//    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
//        ArrayList<InsurancePolicy> sortedPolicies = policies;
//        Collections.sort(sortedPolicies);
//        return sortedPolicies;
//    }

//    Lab 5

    //    HashMap
    public static void printUsers(HashMap<Integer, User> users) {
        for (User user : users.values())
            System.out.println(user);
    }

    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(MyDate date) {
        return InsurancePolicy.filterByExpiryDate(policies, date);
    }

    public HashMap<Integer, InsurancePolicy> filterByCarModel(String carModel) {
        return InsurancePolicy.filterByCarModel(policies, carModel);
    }

    public static HashMap<Integer, User> shallowCopy(HashMap<Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<>();
        for (User user : users.values()) {
            shallowCopy.put(user.getUserID(), user);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values()) {
            deepCopy.put(user.getUserID(), user.clone());
        }
        return deepCopy;
    }

    public HashMap<Integer, InsurancePolicy> deepCopyPoliciesHashMap() throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> deepCopy = InsurancePolicy.deepCopyHashMap(policies);
        return deepCopy;
    }

    public HashMap<Integer, InsurancePolicy> shallowCopyPoliciesHashMap() {
        HashMap<Integer, InsurancePolicy> shallowCopy = InsurancePolicy.shallowCopyHashMap(policies);
        return shallowCopy;
    }

    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> deepCopy = InsurancePolicy.deepCopy(policies);
        return deepCopy;
    }

    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        ArrayList<InsurancePolicy> shallowCopy = InsurancePolicy.shallowCopy(policies);
        return shallowCopy;
    }

    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
        HashMap<Integer, InsurancePolicy> sort = shallowCopyPoliciesHashMap();
        ArrayList<InsurancePolicy> sortedPolicies = new ArrayList<>(sort.values());
        Collections.sort(sortedPolicies);
        return sortedPolicies;
    }

    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> totalCounts = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            boolean isExisted = totalCounts.containsKey(policy.getCarModel());
            if (isExisted) {
                Integer value = totalCounts.get(policy.getCarModel()) + 1;
                totalCounts.put(policy.getCarModel(), value);
            } else {
                totalCounts.put(policy.getCarModel(), 0);
            }
        }
        return totalCounts;
    }


    public HashMap<String, Double> getTotalPremiumPerCarModel() {
        HashMap<String, Double> totalPayments = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            boolean idExisted = totalPayments.containsKey(policy.getCarModel());
            if (idExisted) {
                Double premium = totalPayments.get(policy.getCarModel()) + policy.calcPremium(4);
                totalPayments.put(policy.getCarModel(), premium);
            } else {
                totalPayments.put(policy.getCarModel(), 0.0);
            }
        }
        return totalPayments;
    }

    public void reportPaymentsPerCarModel(HashMap<String, Integer> carModels, HashMap<String, Double> totalPayment, HashMap<String, Integer> totalCount) {
        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
        for (String carModel : carModels.keySet()) {
            System.out.println(carModels.get(carModel) + " \t \t \t " + totalPayment.get(carModel) + " \t \t \t " + totalPayment.get(carModel) / (double) totalCount.get(carModel));
        }
        System.out.println("------------------------------------");
    }
    
    public ArrayList<InsurancePolicy> shallowCopy()
    {
        HashMap<Integer,InsurancePolicy> shallowCopyPolicies=shallowCopyPoliciesHashMap();
        ArrayList<InsurancePolicy> policyList = new ArrayList<>(shallowCopyPolicies.values());
        return policyList;
    }

    public double calcTotalPremiums1(double flatRate) {
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }
    
}