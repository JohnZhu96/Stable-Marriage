import java.util.*; // for Scanner 
import java.io.*;   // for File

public class StableMarriage {
	//Creates a constant that represents the maximum size of array
	public static final int MAX_Size = 200;

	public static void main(String[] args) throws FileNotFoundException{
		//Takes in user's input
		Scanner userinput = new Scanner (System.in);
		//Find and scan the input file 
		Scanner groups = new Scanner(inputFile(userinput));
		//seperate the file into two groups 
		Scanner first = seperateGroups(groups);
		Scanner second = seperateGroups(groups);
		//Creates 2 object Arrays that represent the two groups 
		Person[] group1 = personArray(first);
		Person[] group2 = personArray(second);
		pair(group1,group2);
		System.out.print(group1[0].getPreference());

	}
	
	
	//Creates a method that takes in user's input and check the existence of the file
	public static File inputFile (Scanner input) {
		//Prompts the user for input file name
		System.out.println("Please enter the file name: ");
		File inputFile = new File(input.nextLine());
		//Checks whether the user's input exists 
		while (!inputFile.exists()) {
			System.out.println("File not found. Please enter another file: "); 
			Scanner input2 = new Scanner(System.in); //asks the user to enter another file name
			inputFile = new File (input2.nextLine());
		}
		return inputFile;
	}

	
	
	public static Scanner seperateGroups(Scanner groups) {
		String oneGroup ="";
		String line = groups.nextLine();
		while (!line.equals("END") && groups.hasNextLine()) {
		    oneGroup+=line+"\n";
		    line = groups.nextLine();
		}
		return new Scanner(oneGroup);
	}
    

	
     
	//Creates the method that will return a 2-D Array (row 0 represents group1 and row1 represents group2)
	public static Person[] personArray(Scanner group) {
		Person[] personArray = new Person[MAX_Size]; //Creates an empty 2-D array that will store the Person objects
		int personNum =0;  //creates a variable that indicates the person number
		while (group.hasNextLine()) { 
			String line = group.nextLine(); //stores the whole line as a String
			Scanner scanLine = new Scanner(line);
			String name=null;        //stores name as a string
			String preference = null;  //stores preference as a string
			for (int i=0;i<line.length();i++) {
				if (line.charAt(i)==':') {       //check the line character by character
					name = line.substring(0,i);  //name is the substring from beginning to the ':'
					preference = line.substring(i+1);  //preference is from the character after':' to the end
				}
			}
			personArray[personNum] = new Person(name,preference,personNum);
			personNum++;  //proceeds to the next person
		}
        return personArray;	//return the array containing all the Person objects in that group
	}
    
	
	public static int groupSize(Person[] oneGroup) {
		int size =0;
		for (int i =0; i<oneGroup.length;i++) {
			if (oneGroup[i]!=null) {
				size++;
			}
		}
		return size;
	}
	
	
	public static boolean hasAvailable(Person[]group) {
		for (int i =0; i<group.length;i++) {
			if(group[i].getIntPreference().length !=0 && group[i].isFree()) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void solveProblem(Person[]group1,Person[]group2) {
		//set each person in two groups to be free
		for (int i =0; i<groupSize(group1);i++) {
			group1[i].setToFree();
		}
		for (int i=0; i<groupSize(group2);i++) {
			group2[i].setToFree();
		}
		
		while (hasAvailable(group1)) { 
			pair(group1,group2);
	    }
			
		}

		
	
	//Creates a method that pairs all the group1 person for one time 
	public static void pair(Person[]group1,Person[]group2) {
		//Check person in group1 one by one to see if some group1 m with a nonempty preference list is free
		for (int i=0; i<groupSize(group1);i++) {
			Person m = group1[i];
			if (m.hasPreference() && m.isFree()) {
				int firstChoice = m.getIntPreference()[0];
				Person w = group2[firstChoice];
				if (!w.isFree()) {
					Person p = group1[w.getPartner()];
					p.setToFree();
				}
				//set m and w to be engaged to each other 
			    m.setPartner(w);
				w.setPartner(m);
				//Delete the successor q of m on w's preference list
				
				int[] newPreference = new int[w.getIntPreference().length];
				for (int startPoint=0;startPoint<w.getIntPreference().length;startPoint++) {
					newPreference[i]+= w.getIntPreference()[i];
					if(w.getIntPreference()[i]==m.getNumber()) {
						for (int successor = startPoint; successor<w.getIntPreference().length;successor++) {
							Person q=group1[successor];
							q.delete(w);
							
						}
					}
				}
				w.setIntPre(newPreference);
			}
	   } 

    }
}
