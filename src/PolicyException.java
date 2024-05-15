public class PolicyException extends Exception {
    private int policyID;
    private int oldPolicyID;

    public PolicyException(int policyID) {
        oldPolicyID = policyID;
        this.policyID = generateID();
    }

    public int generateID() {
        int id = (int) ((Math.random() * ((399999 - 300000) + 1)) + 300000);
        return id;
    }

    public String toString() {
        return " The Policy ID was not valid and a new ID" + generateID() + " is generated by admin and assigned for the Policy";
    }
}
