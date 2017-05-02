
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mostafa
 */
public class EMPLOYEE {
    public  StringProperty employeefirstname;
    public  StringProperty employeelastname;
    public  StringProperty employeestate;
    public  StringProperty employeeaddress;
    public  StringProperty employeephonenumber;
    public  StringProperty employeeemail;
    public  StringProperty employeesalary;
    public  StringProperty employeedepartment;

   
     public EMPLOYEE(String epmloyeefirstname,String employeelastname,String employeestate,
    String employeeaddress,String employeephonenumber,String employeeemail,String employeesalary
             ,String employeedepartment){
    
    this.employeefirstname=new SimpleStringProperty(epmloyeefirstname);
    this.employeelastname=new SimpleStringProperty(employeelastname);
    this.employeestate=new SimpleStringProperty(employeestate);
    this.employeeaddress=new SimpleStringProperty(employeeaddress);
    this.employeephonenumber=new SimpleStringProperty(employeephonenumber);
    this.employeeemail=new SimpleStringProperty(employeeemail);
    this.employeesalary=new SimpleStringProperty(employeesalary);
    this.employeedepartment=new SimpleStringProperty(employeedepartment);
    
    }
    
    
    
    
    
    
    public StringProperty getEmployeefirstname() {
        return employeefirstname;
    }

    public void setEmployeefirstname(StringProperty employeefirstname) {
        this.employeefirstname = employeefirstname;
    }

    public StringProperty getEmployeelastname() {
        return employeelastname;
    }

    public void setEmployeelastname(StringProperty employeelastname) {
        this.employeelastname = employeelastname;
    }

    public StringProperty getEmployeestate() {
        return employeestate;
    }

    public void setEmployeestate(StringProperty employeestate) {
        this.employeestate = employeestate;
    }

    public StringProperty getEmployeeaddress() {
        return employeeaddress;
    }

    public void setEmployeeaddress(StringProperty employeeaddress) {
        this.employeeaddress = employeeaddress;
    }

    public StringProperty getEmployeephonenumber() {
        return employeephonenumber;
    }

    public void setEmployeephonenumber(StringProperty employeephonenumber) {
        this.employeephonenumber = employeephonenumber;
    }

    public StringProperty getEmployeeemail() {
        return employeeemail;
    }

    public void setEmployeeemail(StringProperty employeeemail) {
        this.employeeemail = employeeemail;
    }

    public StringProperty getEmployeesalary() {
        return employeesalary;
    }

    public void setEmployeesalary(StringProperty employeesalary) {
        this.employeesalary = employeesalary;
    }
    
     public StringProperty getEmployeedepartment() {
        return employeedepartment;
    }

    public void setEmployeedepartment(StringProperty employeedepartment) {
        this.employeedepartment = employeedepartment;
    }
    
    public StringProperty employeefirstnameProperty(){
    return employeefirstname;
    }
    public StringProperty employeelastnameProperty(){
    return employeelastname;
    }
    public StringProperty employeestateProperty(){
    return employeestate;
    }
    public StringProperty employeeaddressProperty(){
    return employeeaddress;
    }
    public StringProperty employeephonenumberProperty(){
    return employeephonenumber;
    }
    public StringProperty employeeemailProperty(){
    return employeeemail;
    }
    public StringProperty employeesalaryProperty(){
    return employeesalary;
    }
    public StringProperty employeedepartmentProperty(){
    return employeedepartment;
    }
   
}
