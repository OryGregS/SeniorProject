package wmu.datamatching;

public class Contact {

    private String LastName;
    private String MiddleName;
    private String FirstName;
    private String FirmName;
    private String OfficeName;
    private String Email;
    private String BusinessPhone;
    private String Address1;
    private String Address2;
    private String City;
    private String StateProvince;
    private String Zip1;
    private String Zip2;
    private String CountryID;
    private String CRDNumber;
    private String ContactID;

    /**
     * Default constructor to initialize fields
     */
    public Contact() {
        this.LastName = null;
        this.MiddleName = null;
        this.FirstName = null;
        this.FirmName = null;
        this.OfficeName = null;
        this.Email = null;
        this.BusinessPhone = null;
        this.Address1 = null;
        this.Address2 = null;
        this.City = null;
        this.StateProvince = null;
        this.Zip1 = null;
        this.Zip2 = null;
        this.CountryID = null;
        this.CRDNumber = null;
        this.ContactID = null;
    }



    public void setLastName(String LAST) {
        LastName = LAST;
    }

    public void setMiddleName(String MIDDLE) {
        MiddleName = MIDDLE;
    }

    public void setFirstName(String FIRST) {
        FirstName = FIRST;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public void setOfficeName(String officeName) {
        OfficeName = officeName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBusinessPhone(String businessPhone) {
        BusinessPhone = businessPhone;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public void setAddress2(String address2) {
        Address1 = address2;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setStateProvince(String stateProvince) {
        StateProvince = stateProvince;
    }

    public void setZip1(String zip1) {
        Zip1 = zip1;
    }

    public void setZip2(String zip2) {
        Zip2 = zip2;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public void setCRDNumber(String CRDNumber) {
        this.CRDNumber = CRDNumber;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getFirmName() {
        return FirmName;
    }

    public String getOfficeName() {
        return OfficeName;
    }

    public String getEmail() {
        return Email;
    }

    public String getBusinessPhone() {
        return BusinessPhone;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public String getCity() {
        return City;
    }

    public String getStateProvince() {
        return StateProvince;
    }

    public String getZip1() {
        return Zip1;
    }

    public String getZip2() {
        return Zip2;
    }

    public String getCountryID() {
        return CountryID;
    }

    public String getCRDNumber() {
        return CRDNumber;
    }

    public String getContactID() {
        return ContactID;
    }



    public void printAll() {
        System.out.printf("%-40s %-40s %-40s %-40s %-40s %-40s %-40s " +
                "%-40s %-40s %-40s %-40s %-40s %-40s %-40s %-40s %-40s",
                LastName, MiddleName, FirstName, FirmName, OfficeName,
                Email, BusinessPhone, Address1, Address2, City,
                StateProvince, Zip1, Zip2, CountryID, CRDNumber, ContactID);
    }
}
