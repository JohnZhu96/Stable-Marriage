import java.util.*; // for Scanner 

/**
 *  Person calss is used to manipulate and get information of a person's name and preference.
 *  
 *  It can return the person's name in String, return the preference list in 
 *  an integer array, determines whether the person is free
 *  This class was written for PA4 in COSI 12B at Brandeis University
 *  Department of Computer Science.
 *  
 * @author Linfeng Zhu
 * @version 1202v1
 * @since 1.8
 */
public class Person {
	public static final int EMPTY = -1;
	public static final int MAX_PREFERENCE = 50;
	
    private String name;
    private String preference;
    private int number;
    private int partner;
    private int[] intPreference;

    /**
     * Constructs a new person with his name and preference list
     * @param name    String that represents the person's name 
     * @param preference  String that represents the person's preference list 
     */
    public Person(String name, String preference,int number) {
 	    this.name = name;
	    this.preference = preference;
	    this.number = number;
	    this.partner = EMPTY;
	    
    }

    public String getName() {
    	return name;
    }
    
    public String getPreference() {
    	return preference;
    }
    
    public int[] getIntPreference() {
    	Scanner scan = new Scanner(this.preference);
    	int[]intPre = new int[MAX_PREFERENCE];  //Creates an new array to store the preference
    	int i =0;
    	while(scan.hasNextInt()) {
    		intPre[i] = scan.nextInt();
    		i++;
    	}
    	this.intPreference = new int[i];
    	for(int j = 0; j<i;j++) {
    		this.intPreference[j]+=intPre[j];
    	}
    	return this.intPreference;	
    }
    
    public int getPartner() {
    	return partner;
    }
    
    public int getNumber() {
    	return number;
    }
    
    public void setPartner(Person newPartner) {  //Mutator that change the partner of the person
    	this.partner = newPartner.getNumber();  
    }
    
    public void setPreference(String newPreference) {
    	this.preference = newPreference;
    }
    public void setIntPre(int[] newIntPre) {
    	this.intPreference=newIntPre;
    }
    public void delete(Person w) {
    	Scanner scan = new Scanner(preference);
    	String newPreference="";
    	String token;
    	while (scan.hasNext()) {
    		token =scan.next();
    		if (!token.equals(""+w.getNumber())) {
    			newPreference+=token;
    		}
    	}
    	this.preference = newPreference;
    }
    


    public void setToFree() {
    	this.partner = EMPTY;
    }

    public boolean isFree() {
    	if(partner == EMPTY) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean hasPreference() {
    	if (preference.equals(null)) {
    		return false;
    	}
    	return true;
    }
    
    
    
    public String toString() {
    	return (name + ":" + preference);
    }
    
    


}
