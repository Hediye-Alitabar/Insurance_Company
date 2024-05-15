   public class ThirdPartyPolicy extends InsurancePolicy {
    protected String comments;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate, String comments) throws PolicyException {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    public String getComment() {
        return comments;
    }

    public void setComment(String comment) {
        this.comments = comment;
    }
    
    @Override
    public double calcPremium(double flatRate) {
        return car.getPrice() / 100 + numberOfClaims * 200 + flatRate;
    }

    @Override
    public void print() {
        super.print();
        System.out.println(" Comments: " + comments);
    }

    @Override
    public String toString() {
        return super.toString() + " Comments: " + comments;
    }

    //    Lab 4
//    Copy Constructor
    public ThirdPartyPolicy(ThirdPartyPolicy policy) {
        super(policy);
        this.comments = policy.comments;
    }

}
