public class InsuranceCompanyProgram
{
    public static void main(String[] args) throws CloneNotSupportedException, PolicyException {
        InsuranceCompany insuranceCompany = new InsuranceCompany("HoomanCompany", "admin", "admin", 12);
        UserInterface.mainMenu(insuranceCompany);
    }
}
