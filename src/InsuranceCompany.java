    import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class InsuranceCompany implements Cloneable {
    private String name;
    //    private ArrayList<User> users;
    private HashMap<Integer, User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
//        this.users = new ArrayList<>();
        this.users = new HashMap<Integer, User>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    // get and set methods------------------------------------------------------
    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getName()
    {
         return name;
    }
    
    public HashMap<Integer,User> getUsers() {
        return this.users;
    }
    
    public int getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(int fRate) {
        flatRate = fRate;
    }

    public boolean validateAdmin(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

     public User validateUser(String userName, String password) {

//        for(User user : users) // a bad way
//        {
//            if(user.validateUser(userID, password))
//            {
//                return user;
//            }
//        }
//        return null;

        // Correct way by using findUser
        for(User user:users.values()){
//             if ((user != null) && user.validateUser(userName, password))
             if (user.validateUser(userName, password)) {
            return user;
        }
           
        }
         return null; 
     }
     
  
    
    public User validateUser(int userID, String password) {

//        for(User user : users) // a bad way
//        {
//            if(user.validateUser(userID, password))
//            {
//                return user;
//            }
//        }
//        return null;

        // Correct way by using findUser
        
        User user = findUser(userID);
        if ((user != null) && user.validateUser(userID, password)) {
            return user;
        } else
            return null;
    }

    // finds the user with the given ID or returns null if user does not exist
    public User findUser(int userID) {
//        for (User user : users) {
//            if (user.getUserID() == userID) {
//                return user;
//            }
//        }
//        return null;
        return users.get(userID);
    }

        public User findUser(String userName) {
//        for (User user : users) {
//            if (user.getUserID() == userID) {
//                return user;
//            }
//        }
//        return null;
        return users.get(userName);
    }
    
    // adds the user to users list if userID is unique, if not returns false
    public boolean addUser(User user) {
        if (findUser(user.getUserID()) == null) {
//            users.add(user);
            users.put(user.getUserID(), user);
            return true;
        } else {
            return false;
        }
    }

    // creates and adds the User to users list if userID is unique, if not returns false. Create a user object and reuse the addUser(User user) method
    public boolean addUser(String name, int userID, Address address, String password) {
        User user = new User(userID, name, address, password);
        return addUser(user);
    }

    public boolean addUser(String name, Address address, String password) //automatic ID generation
    {
        User user = new User(name, address, password); // user constructor to generate ID automatically
        return addUser(user);
    }

    // finds the user with the given userID by using findUser method and adds the policy to the user,
    // unsuccessful if userID does not exist or policy is not unique
    public boolean addPolicy(int userID, InsurancePolicy policy) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPolicy(policy);
        }
        return false;
    }

    // finds the insurance policy for the given userID and returns it.
    // Returns null if userID does not exist or policyID does not exist for the given user
    public InsurancePolicy findPolicy(int userID, int policyID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPolicy(policyID);
        }
        return null;
    }

    // finds the user with the given userID (by calling findUser) and calls the createThirdPartyPolicy for that user.
    // Returns false if the user does not exist or if User.createThirdPartyPolicy returns false
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
        User user = findUser(userID);
        if (user != null) {
            return user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return false;
    }

    // finds the user with the given userID (by calling findUser) and calls the createComprehensivePolicy for that user.
    // Returns false if the user does not exist or if User.createComprehensivePolicy returns false
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        User user = findUser(userID);
        if (user != null) {
            return user.createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
        }
        return false;
    }

    // returns the total premium payments for the given user
    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPremiums(flatRate);
        }
        return 0;
    }

    // returns the total premium payments for all the users in the company
    public double calcTotalPayments() {
        double totalPayment = 0;
        for (User user : users.values()) {
            totalPayment += user.calcTotalPremiums(flatRate);
        }
        return totalPayment;
    }

    // calls carPriceRiseAll method for the given user. Returns false if user cannot be found
    public boolean carPriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            user.carPriceRiseAll(risePercent);
            return true;
        }
        return false;
    }

    // Raise the price of all cars for all users in the company
    public void carPriceRise(double risePercent) {
        for (User user : users.values()) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // returns a list of all the policies in the company across all users
    public HashMap<Integer, InsurancePolicy> allPolicies() {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<>();
        for (User user : users.values()) {
            for (InsurancePolicy policy : user.getPolicies().values()) {
                policies.put(policy.policyID, policy);
            }
        }
        return policies;
    }

    // find the user by calling findUser and calls filterByCarModel for the given user
//    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
//        User user = findUser(userID);
//        if (user != null) {
//            return user.filterByCarModel(carModel);
//        }
//        return new ArrayList<InsurancePolicy>();
//    }

    // find the user by calling findUser and calls filterByExpiryDate for the given user
//    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
//        User user = findUser(userID);
//        if (user != null) {
//            return user.filterByExpiryDate(date);
//        }
//        return new ArrayList<InsurancePolicy>();
//    }

    /* filters all the policies in the company by carModel across all users.
       Iterate over a loop for all users and for each user call the filterByCarModel method and
       add all the results together for a global list including all users.*/
//    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
//        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList();
//        for (User user : users) {
//            for (InsurancePolicy model : user.filterByCarModel(carModel)) {
//                filteredPolicies.add(model);
//            }
//        }
//        return filteredPolicies;
//    }

    // filters all the policies in the company by ExpiryDate across all users. The same as above
//    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
//        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList();
//        for (User user : users.values()) {
//            for (InsurancePolicy exDates : user.filterByExpiryDate(date)) {
//                filteredPolicies.add(exDates);
//            }
//        }
//        return filteredPolicies;
//    }


    // print methods

    // prints the user information and all the policies for the given userID
    public void printPolicies(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.printPolicies(flatRate);
        }
    }

    public void printUser(int userID) //added for UI
    {
        User user = findUser(userID);
        if (user != null) {
            user.print(flatRate);
        }
    }

    // prints all the users and for each user all the policies by calling User.PrintPolicies(int flatRate)
    public void print() {
        System.out.println("Company name: " + name + " Username: " + adminUsername + " Flat Rate: " + flatRate);
        for (User user : users.values()) {
            // user.print(); // WRONG not based on the spec

            // user.printUserInformation() ; // correct but duplicate
            // user.printPolicies(flatRate);

            user.print(flatRate); // add this to user and make it better than previous 2 lines
        }
    }

    public String toString() {
        String printString = "Company name: " + name + " Username: " + adminUsername + " Flat Rate: " + flatRate + "\n";
        for (User user : users.values()) {
            printString += user.toString() + "\n";
        }
        return printString;
    }

    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            boolean found = false;
            for (String city : cities) {
                if (user.getCity().equals(city)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                cities.add(user.getCity());
        }
        return cities;
    }

    public double getTotalPaymentForCity(String city) {
        double totalPaymentForCity = 0;
        for (User user : users.values()) {
            if (user.getCity().equals(city)) {
                totalPaymentForCity += user.calcTotalPremiums(flatRate);
            }
        }
        return totalPaymentForCity;
    }

    public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
        ArrayList<Double> totalPerCity = new ArrayList<>();
        for (String city : cities) {
            totalPerCity.add(getTotalPaymentForCity(city));
        }
        return totalPerCity;
    }


    public void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments) // it is in the spec but not good
    {
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }


    public void reportPaymentPerCity(ArrayList<String> cities) // for a list of given cities
    {
        ArrayList<Double> payments = getTotalPaymentPerCity(cities);
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }

    public void reportPaymentPerCity() // for all cities
    {
        ArrayList<String> cities = populateDistinctCityNames();
        ArrayList<Double> payments = getTotalPaymentPerCity(cities);
        String format = "%1$-20s%2$-20s\n";
        System.out.format(format, "City Name", "Total Monthly Payment");
        for (int i = 0; i < cities.size(); i++) {
            System.out.format(format, cities.get(i), payments.get(i));
        }
    }

    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> allModels = new ArrayList<String>();
        for (User user : users.values()) {
            ArrayList<String> userModels = user.populateDistinctCarModels();
            for (String userModel : userModels) {
                boolean found = false;
                for (String model : allModels) {
                    if (model.equals(userModel)) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    allModels.add(userModel);
            }
        }
        return allModels;
    }

//    public ArrayList<Integer> getTotalCountPerCarModel (ArrayList<String> carModels)
//    {
//        ArrayList<Integer> totalCounts=new ArrayList<Integer>();
//        int count=0;
//        for (String model:carModels)
//        {
//            count=0;
//            for (User user:users)
//            {
//                count+=user.getTotalCountForCarModel(model); // by calling this method which is not the same as spec
//            }
//            totalCounts.add(count);
//        }
//        return totalCounts;
//    }

    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> totalCounts = new ArrayList<Integer>();
        for (int i = 0; i < carModels.size(); i++) {
            totalCounts.add(0);// initial values with 0
        }

        for (User user : users.values()) {
            ArrayList<Integer> userCounts = user.getTotalCountPerCarModel(carModels);
            for (int i = 0; i < userCounts.size(); i++) {
                totalCounts.set(i, totalCounts.get(i) + userCounts.get(i));//for each element of total add the user count
            }
        }
        return totalCounts;
    }

    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels) {
        ArrayList<Double> totalPayments = new ArrayList<Double>();
        for (int i = 0; i < carModels.size(); i++) {
            totalPayments.add(0.0);// initial values with 0
        }

        for (User user : users.values()) {
            ArrayList<Double> userTotalPayments = user.getTotalPaymentPerCarModel(carModels, flatRate);
            for (int i = 0; i < userTotalPayments.size(); i++) {
                totalPayments.set(i, totalPayments.get(i) + userTotalPayments.get(i));
            }
        }
        return totalPayments;
    }

    //as spec but it is not good. all lists are sent as parameters
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts, ArrayList<Double> payments) {
        System.out.println("\n Car Model \t \t \t Total Payments \t \t \t Average Payment");
        for (int i = 0; i < counts.size(); i++)
            System.out.println(carModels.get(i) + " \t \t \t " + payments.get(i) + " \t \t \t " + payments.get(i) / (double) counts.get(i));
    }

    //a list of given models
    public void reportPaymentsPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> counts = getTotalCountPerCarModel(carModels);
        ArrayList<Double> payments = getTotalPaymentPerCarModel(carModels);
        reportPaymentsPerCarModel(carModels, counts, payments);
    }

    public void reportPaymentsPerCarModel() // for all models
    {
        ArrayList<String> carModels = populateDistinctCarModels();
        reportPaymentsPerCarModel(carModels);
    }

    //    Lab 4
//    Copy Constructor
    public InsuranceCompany(InsuranceCompany insuranceCompany) {
        this.adminUsername = insuranceCompany.adminUsername;
        this.adminPassword = insuranceCompany.adminPassword;
        this.flatRate = insuranceCompany.flatRate;
        this.name = insuranceCompany.name;
//        this.users= new ArrayList<>();
        this.users = new HashMap<Integer, User>();
        for (User user : insuranceCompany.users.values()) {
            users.put(user.getUserID(), new User(user));
        }
    }

    public InsuranceCompany clone() throws CloneNotSupportedException {
        InsuranceCompany insuranceCompany = (InsuranceCompany) super.clone();
        insuranceCompany.users = new HashMap<Integer, User>();
        for (User user : users.values()) {
            insuranceCompany.users.put(user.getUserID(), user.clone());
        }
        return insuranceCompany;
    }

//        Deep copy of users
//    public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
//        return User.deepCopy(users);
//    }
//       Shallow copy of users
//    public ArrayList<User> shallowCopyUsers() {
//        return User.shallowCopy(users);
//    }


    //  Sort users by user`s city
//    public ArrayList<User> sortUsers() {
//        ArrayList<User> sortedUsers = users;
//        Collections.sort(sortedUsers);
//        return sortedUsers;
//    }

//    Lab 5

    public HashMap<Integer, InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByCarModel(carModel);
        }
        return new HashMap<>();
    }

    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new HashMap<>();
    }

    public HashMap<Integer, InsurancePolicy> filterByCarModel(String carModel) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        for (User user : users.values()) {
            for (InsurancePolicy model : user.filterByCarModel(carModel).values()) {
                filteredPolicies.put(user.getUserID(), model);
            }
        }
        return filteredPolicies;
    }

    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(MyDate date) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        for (User user : users.values()) {
            for (InsurancePolicy exDates : user.filterByExpiryDate(date).values()) {
                filteredPolicies.put(user.getUserID(), exDates);
            }
        }
        return filteredPolicies;
    }

    //    Deep copy of users by Using HashMap
    public HashMap<Integer, User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopy(users);
    }

    //   Shallow copy of users by Using HashMap
    public HashMap<Integer, User> shallowCopyUsers() {
        return User.shallowCopy(users);
    }

    public ArrayList<User> sortUsers() {
        HashMap<Integer, User> sort = shallowCopyUsers();
        ArrayList<User> sortedUsers = new ArrayList<>(sort.values());
        Collections.sort(sortedUsers);
        return sortedUsers;
    }

    public HashMap<String, Double> getTotalPremiumPerCity() {
        HashMap<String, Double> cities = new HashMap<>();
        for (User user : users.values()) {
            Double premium = cities.get(user.getCity());
            if (premium != null) {
                cities.put(user.getCity(), user.calcTotalPremiums(4));
            } else {
                cities.put(user.getCity(), user.calcTotalPremiums(4));
            }

        }
        return cities;
    }


    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> models = new HashMap<>();
        for (User user : users.values()) {
            HashMap<String, Integer> carModel = user.getTotalCountPerCarModel();
            for (String model : carModel.keySet()) {
                Integer count = models.get(model);
                if (count != null) {
                    models.put(model, count + carModel.get(model));
                } else {
                    models.put(model, carModel.get(model));
                }
            }
        }
        return models;
    }


    public HashMap<String, Double> getTotalPremiumPerCarModel() {
        HashMap<String, Double> models = new HashMap<>();
        for (User user : users.values()) {
            HashMap<String, Double> carModel = user.getTotalPremiumPerCarModel();
            for (String model : carModel.keySet()) {
                Double premium = models.get(model);
                if (premium != null) {
                    models.put(model, premium + carModel.get(model));
                } else {
                    models.put(model, carModel.get(model));
                }
            }
        }
        return models;
    }


    public void reportPaymentPerCity(HashMap<String, Integer> cities, HashMap<String, Double> payments) {
//        HashMap<String, Double> payments = getTotalPremiumPerCity();
        System.out.println("City Name\", \"Total premium Payments");
        for (String city : cities.keySet()) {
            System.out.println(cities.get(city) + " \t \t \t " + payments.get(city));
        }
    }


    public void reportPaymentsPerCarModel(HashMap<String, Integer> carModels, HashMap<String, Double> totalPayment, HashMap<String, Integer> totalCount) {
        System.out.println("\n CarModel \t \t \t Total Premium \t \t \tAverage Premium");
        for (String carModel : carModels.keySet()) {
            System.out.println(carModels.get(carModel) + " \t \t \t " + totalPayment.get(carModel) + " \t \t \t " + totalPayment.get(carModel) / (double) totalCount.get(carModel));
        }
        System.out.println("------------------------------------");
    }
    
//     public ArrayList<User> sortUsersByName() {
//        HashMap<Integer, User> sort = shallowCopyUsers();
//        ArrayList<User> sortList = new ArrayList<>(sort.values());
//        Collections.sort(sortList, new TotalPremiumComparator());
//        return sortList;
//    }.
    
    public void save() throws IOException{
           ObjectOutputStream outputSt = new ObjectOutputStream(Files.newOutputStream(Paths.get("company.ser")));
           outputSt.writeObject(this);
           outputSt.close();
     }
    public static InsuranceCompany load() throws IOException, ClassNotFoundException{
          ObjectInputStream  inputst = new ObjectInputStream(Files.newInputStream(Paths.get("company.ser")));
          InsuranceCompany company=(InsuranceCompany)inputst.readObject();
          inputst.close();
          return company;
    }

     public ArrayList<User> shallowCopy()
    {
        HashMap<Integer,User> shallowCopyusers=shallowCopyUsers();
        ArrayList<User> userList = new ArrayList<>(shallowCopyusers.values());
        return userList;
    }
     
//     public int comareTo(User user)
//     {
//      for(User otherUser:users.values())
//      {
//           int flatRate = 4;
//        if (otherUser.calcTotalPremiums(flatRate) > user.calcTotalPremiums(flatRate)) {
//            return 1;
//        } else if (otherUser.calcTotalPremiums(flatRate) < user.calcTotalPremiums(flatRate)) {
//            return -1;
//        }
//       
//      }
//      return 0;
//     }
class TotalPremiumComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return (int) user1.calcTotalPremiums((TestCase.FLAT_RATE) - user2.calcTotalPremiums(TestCase.FLAT_RATE));
    }
}
}
//class TotalPremiumComparator implements Comparator<User> {
//    @Override
//    public int compare(User user1, User user2) {
//        return (int) user1.calcTotalPremiums((TestCase.FLAT_RATE) - user2.calcTotalPremiums(TestCase.FLAT_RATE));
//    }
//}




