    import java.util.ArrayList;
import java.util.HashMap;

public abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy> {
    protected String policyHolderName;
    protected int policyID;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy(String policyHolderName, int policyID, Car car, int numberOfClaims, MyDate expiryDate) throws PolicyException {
        this.policyHolderName = policyHolderName;
        this.policyID = policyID;
        if (policyID < 300000 || policyID > 399999) {
            throw new PolicyException(policyID);
        }
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    // get and set methods------------------------------------------------------
    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyID(int newID)
    {
         policyID= newID;
    }
    
   
    
     public void setNumberOfClaims(int newNumberOfClaims)
    {
         numberOfClaims= newNumberOfClaims;
    }
    
    public void setPolicyHolderName(String newName) {
        policyHolderName = newName;
    }

    public int getPolicyID() {
        return policyID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car cr) {
        car = cr;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    
    public String getCarModel() {
        return car.getModel();
    }

    public void setCarModel(String model) {
        car.setCarModel(model);
    }

    public CarType getType() {
        return car.getCarType();
    }
    
    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(MyDate eDate) {
        expiryDate = eDate;
    }

    public abstract double calcPremium(double flatRate);

    // It has one parameter, a price rise in percent. The method increases the policy’s car price by rise percent.
    public void carPriceRise(double risePercent) {
        car.priceRise(risePercent);
    }

    // print and toString methods-----------------------------------------------

    public void print() {
        System.out.print("Policy holder name: " + policyHolderName + " ID: " + policyID);
        car.print();
        System.out.print(" Number of claims: " + numberOfClaims);
        //System.out.println(" Expiry Date: " + expiryDate);
        expiryDate.print();
    }

    public void print(double flatRate) {
        print();
        System.out.print(" Premium Payment: " + calcPremium(flatRate));
    }

    @Override
    public String toString() {
        return "Policy holder name: " + policyHolderName + " ID: " + policyID + " Car: " + car + " Number of claims: " + numberOfClaims + " Date: " + expiryDate+"\n";
    }

    // prints a list of policies.
    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies) {
            policy.print();
        }
    }

    //    HashMap
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies.values()) {
            policy.print();
        }
    }

    //print a list of policies and the premiums
//    public static void printPolicies(ArrayList<InsurancePolicy> policies, double flatRate) {
//        for (InsurancePolicy policy : policies) {
//            policy.print(flatRate);
//        }
//    }


    //calculates the total premium payments for a list of policies.
    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, double flatRate) {
        double total = 0;
        for (InsurancePolicy policy : policies) {
            total += policy.calcPremium(flatRate);
        }
        return total;
    }


    // calls the carPriceRise method for all the policies in a given list ( in a for each loop). This is to increase the price of cars for all policies in a list.
    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy : policies) {
            policy.carPriceRise(risePercent);
        }
    }


    // filter methods-----------------------------------------------------------

    // which filter a list of policies and creates a filtered list of policies, all with the given car model.
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();

        for (InsurancePolicy policy : policies) {
            if (policy.getCarModel().equals(carModel)) {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    }


    // which filter a list of policies and creates a filtered list of policies that are expired by the given date.
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();

        for (InsurancePolicy policy : policies) {
            if (policy.getExpiryDate().isExpired(date)) {
                filteredPolicies.add(policy);
            }
        }
        return filteredPolicies;
    }


    //    Lab 4
//    Copy Constructor
    public InsurancePolicy(InsurancePolicy policy) {
        this.numberOfClaims = policy.numberOfClaims;
        this.policyHolderName = policy.policyHolderName;
        this.policyID = policy.policyID;
        this.car = new Car(policy.car);
        this.expiryDate = new MyDate(policy.expiryDate);
    }

    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        InsurancePolicy outPut = (InsurancePolicy) super.clone();
        outPut.car = car.clone();
        outPut.expiryDate = expiryDate.clone();
        return outPut;
    }

    //    Shallow copy
//    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException {
//        ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
//        for (InsurancePolicy policy : policies) {
//            shallowCopy.add(policy); //policy.clone()
//        }
//        return shallowCopy;
//    }

    //   Deep copy by using clone()
//    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies) throws CloneNotSupportedException {
//        ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
//        for (InsurancePolicy policy : policies) {
//            deepCopy.add(policy.clone());
//        }
//        return deepCopy;
//    }

    public int compareTo1(InsurancePolicy otherPolicy) 
    {
        return expiryDate.compareTo(otherPolicy.expiryDate);
    }

//    Lab 7
    @Override
    public int compareTo(InsurancePolicy otherPolicy)
    {
        return policyHolderName.compareTo(otherPolicy.policyHolderName);
    }
    
    //    Lab 5

    //    HashMap
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies, double flatRate) 
    {
        for (InsurancePolicy policy : policies.values()) 
        {
            policy.print(flatRate);
        }
    }

    //    HashMap
    public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy> policies, double risePercent) {
        for (InsurancePolicy policy : policies.values()) {
            policy.carPriceRise(risePercent);
        }
    }

    //    HashMap
    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, double flatRate) {
        double total = 0;
        for (InsurancePolicy policy : policies.values()) {
            total += policy.calcPremium(flatRate);
        }
        return total;
    }

    //    HashMap
    public static HashMap<Integer, InsurancePolicy> filterByCarModel(HashMap<Integer, InsurancePolicy> policies, String carModel) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();

        for (InsurancePolicy policy : policies.values()) {
            if (policy.getCarModel().equals(carModel)) {
                filteredPolicies.put(policy.policyID, policy);
            }
        }
        return filteredPolicies;
    }

    // HashMap
    public static HashMap<Integer, InsurancePolicy> filterByExpiryDate(HashMap<Integer, InsurancePolicy> policies, MyDate date) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();

        for (InsurancePolicy policy : policies.values()) {
            if (policy.getExpiryDate().isExpired(date)) {
                filteredPolicies.put(policy.policyID, policy);
            }
        }
        return filteredPolicies;
    }

    public static ArrayList<InsurancePolicy> shallowCopy(HashMap<Integer, InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> shallowCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values()) {
            shallowCopy.add(policy);
        }
        return shallowCopy;
    }

    public static ArrayList<InsurancePolicy> deepCopy(HashMap<Integer, InsurancePolicy> policies) throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> deepCopy = new ArrayList<>();
        for (InsurancePolicy policy : policies.values()) {
            deepCopy.add(policy.clone());
        }
        return deepCopy;
    }

    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(HashMap<Integer, InsurancePolicy> policies) {
        HashMap<Integer, InsurancePolicy> shallowCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            shallowCopy.put(policy.policyID, policy);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(HashMap<Integer, InsurancePolicy> policies) throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> shallowCopy = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            shallowCopy.put(policy.policyID, policy.clone());
        }
        return shallowCopy;
    }
    
    public int compareTo2(InsurancePolicy policy) {
        int flatRate = 4;
        if (calcPremium(flatRate) > policy.calcPremium(flatRate)) {
            return 1;
        } else if (calcPremium(flatRate) < policy.calcPremium(flatRate)) {
            return -1;
        }
        return 0;
    }
}
