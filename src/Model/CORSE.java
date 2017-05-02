
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mostafa
 */
public class CORSE {
   public  StringProperty departmentname;
   public static StringProperty name;
   public static StringProperty cost;
    
   //Getters
   public String getDepartmentname(){
       return departmentname.get();
   }
   
   
   public String getName(){
       return name.get();
   }
   
   public String getCost(){
       return cost.get();
   }
   //setters
   public void setDepartmentname(String value){
        departmentname.set(value);
   }
   
   
   public void setName(String value){
        name.set(value);
   }
   
   public void setCost(String value){
        cost.set(value);
   }
   
   
   public CORSE(String departmentname,String name,String cost){
   this.departmentname=new SimpleStringProperty(departmentname);
   this.name=new SimpleStringProperty(name);
   this.cost=new SimpleStringProperty(cost);
   }
  
   
//   property values
   
   public StringProperty departmentProperty(){
   return departmentname;
   }
   
   public StringProperty nameProperty(){
   return name;
   }
   
   public StringProperty costProperty(){
   return cost;
   }
   
   
   
   
   
   
}
