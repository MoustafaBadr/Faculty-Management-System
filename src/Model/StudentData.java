package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {

    private IntegerProperty student_id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty address;
    private StringProperty city;
    private StringProperty state;
    private StringProperty country;
    private StringProperty phone_num;
    private StringProperty email;
    private StringProperty graduation_year;

    public IntegerProperty student_id() {
        return student_id;
    }
    
    public StringProperty first_name() {
        return first_name;
    }

    public StringProperty last_name() {
        return last_name;
    }

    public StringProperty address() {
        return address;
    }

    public StringProperty city() {
        return city;
    }

    public StringProperty state() {
        return state;
    }
    
     public StringProperty country() {
        return country;
    }

    public StringProperty phone_num() {
        return phone_num;
    }

    public StringProperty email() {
        return email;
    }

    public StringProperty graduation_year() {
        return graduation_year;
    }

    //===================================================
    public void setStudent_id(int student_id) {
        this.student_id.set(student_id);
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public void setAdress(String address) {
        this.address.set(address);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setPhone_num(String phone_num) {
        this.phone_num.set(phone_num);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year.set(graduation_year);
    }

//    public void setCourse_name(String course_name) {
//        this.course_name.set(course_name);
//    }
    //---------- Get --------------
    public int getStudent_id() {
        return student_id.get();
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getState() {
        return state.get();
    }

    public String getCountry() {
        return country.get();
    }

    public String getPhone_num() {
        return phone_num.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getGraduation_year() {
        return graduation_year.get();
    }
//    public String getCourse_name() {
//        return course_name.get();
//    }

    public StudentData(int student_id, String first_name, String last_name, String address, String city, String state, String country, String phone_num, String email, String graduation_year) {
        this.student_id = new SimpleIntegerProperty(student_id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.country = new SimpleStringProperty(country);
        this.phone_num = new SimpleStringProperty(phone_num);
        this.email = new SimpleStringProperty(email);
        this.graduation_year = new SimpleStringProperty(graduation_year);

        //  this.course_name = new SimpleStringProperty(course_name);
    }

}
