package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class person {

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public void setCourse_id(int value) {
        course_id.set(value);
    }

    public String getDepartment_naem() {
        return department_naem.get();
    }

    public void setDepartment_naem(String value) {
        department_naem.set(value);
    }

    public String getCost() {
        return cost.get();
    }

    public void setCost(String value) {
        cost.set(value);
    }

    public StringProperty CostPro() {
        return cost;
    }

    public StringProperty DepartmentPro() {
        return department_naem;
    }

    public StringProperty namePro() {
        return name;
    }

    public IntegerProperty course_idpro() {
        return course_id;
    }

    private StringProperty name;
    private StringProperty department_naem;
    private StringProperty cost;
    private IntegerProperty course_id;

    public person(String department_id, String name, String cost, Integer course_id) {
        this.name = new SimpleStringProperty(name);
        this.department_naem = new SimpleStringProperty(department_id);
        this.cost = new SimpleStringProperty(cost);
        this.course_id = new SimpleIntegerProperty(course_id);

    }

}
