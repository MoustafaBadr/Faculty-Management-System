
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mostafa
 */
public class STUDENT {
    public  StringProperty firstname;
    public  StringProperty lastname;
    public  StringProperty address;
    public  StringProperty state;
    public  StringProperty phonenumber;
    public  StringProperty email;
    public  StringProperty graduationyear;

    
    
    public STUDENT(String firstname,String lastname,
    String address,String state,String phonenumber,String email,String graduationyear){
        
        this.firstname=new SimpleStringProperty(firstname);
        this.lastname=new  SimpleStringProperty(lastname);
        this.address=new SimpleStringProperty(address);
        this.state=new SimpleStringProperty(state);
        this.phonenumber=new SimpleStringProperty(phonenumber);
        this.email=new SimpleStringProperty(email);
        this.graduationyear=new SimpleStringProperty(graduationyear);
       
    }
    
    

    public StringProperty getFirstname() {
        return firstname;
    }

    public void setFirstname(StringProperty firstname) {
        this.firstname = firstname;
    }

    public StringProperty getLastname() {
        return lastname;
    }

    public void setLastname(StringProperty lastname) {
        this.lastname = lastname;
    }

    public StringProperty getAddress() {
        return address;
    }

    public void setAddress(StringProperty address) {
        this.address = address;
    }

    public StringProperty getState() {
        return state;
    }

    public void setState(StringProperty state) {
        this.state = state;
    }

    public StringProperty getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(StringProperty phonenumber) {
        this.phonenumber = phonenumber;
    }

    public StringProperty getEmail() {
        return email;
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }

    public StringProperty getGraduationyear() {
        return graduationyear;
    }

    public void setGraduationyear(StringProperty graduationyear) {
        this.graduationyear = graduationyear;
    }
    
    
    
  
    public StringProperty firstnameProperty(){
    return firstname;
    }
    public StringProperty lastnameProperty(){
    return lastname;
    }
    public StringProperty addressProperty(){
    return address;
    }
    public StringProperty stateProperty(){
    return state;
    }
    public StringProperty phonenumberProperty(){
    return phonenumber;
    }
    public StringProperty emailProperty(){
    return email;
    }
    public StringProperty graduationyearProperty(){
    return graduationyear;
    }
}
