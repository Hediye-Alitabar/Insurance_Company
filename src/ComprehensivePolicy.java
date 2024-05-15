    public class ComprehensivePolicy extends InsurancePolicy {
    protected int driverAge;
    protected int level;

    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, int driverAge, int level) throws PolicyException {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }
    
    @Override
    public double calcPremium(double flatRate) {
        if (driverAge <= 30) {
            return car.getPrice() / 50 + numberOfClaims * 200 + flatRate + (30 - driverAge) * 50;
        } else
            return car.getPrice() / 50 + numberOfClaims * 200 + flatRate;
    }

    // print
    @Override
    public void print() {
        super.print();
        System.out.println(" Driver Age: " + driverAge + " Level: " + level);
    }

    @Override
    public String toString() {
        return super.toString() + " Driver Age: " + driverAge + " Level: " + level;
    }

//    Lab 4
//    Copy Constructor

    public ComprehensivePolicy(ComprehensivePolicy policy){
        super(policy); //?
        this.level=policy.level;
        this.driverAge=policy.driverAge;
    }

}

