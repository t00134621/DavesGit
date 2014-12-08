/**  This is an insataniable class
 * @(#)PlayerDetails.java
 *
 *
 * @author David Mc Mahon
 * @version 1.00 2014/11/27
 */



import java.io.*; // input output used for file read and write//

public class PlayerDetails implements Serializable {  // serializable is used to store the data this is an interface call//
	
	//attributes
	private String firstName;  // private instance varialbles
	private String secondName;

    public PlayerDetails() {    // default constructor
    	
    	setFirst(firstName);
    	setSecond(secondName);
    }
    /**Two args Constructor  method
     @param firstName,SecondName the first and second name of the Player*/
    public PlayerDetails(String firstName,String secondName){
    	
    	this.firstName=firstName;
    	this.secondName=secondName;
    }
    
    /** acessor method to return the student firstname
     *@return the firstname of the student*/
    
    public String getFirst(){
    	
    	return firstName;
    }
    /** acessor method to return the student firstname
     *@return the secondtname of the student*/
    
     public String getSecond(){
    	
    	return secondName;
    }
    
    /** mutator method to return the student firstname
     *@param the firstname of the student*/
    
    
    public void setFirst(String firstName){
    	
    	this.firstName=firstName;
    }
    
    /** mutator method to return the student secondname
     *@param the firstname of the student*/
     
    public void setSecond(String secondName){
    	
    	this.secondName=secondName;
    }
    
    /** toString method returns the PlayerDetails
     *@return the name and status of the student as a string*/
    
    public String toString(){
    	
    	return String.format(":"+getFirst()+":"+getSecond());
    }
    
}//end class