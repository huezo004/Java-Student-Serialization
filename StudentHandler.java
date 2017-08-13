/*Denyse Huezo
  dah12
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.lang.Double;
import java.lang.Integer; 
import java.util.InputMismatchException; 
import java.lang.ClassNotFoundException; 

public class StudentHandler{

    //container to hols Student object 
  private ArrayList<Student> list; 

    //only constructor 
  StudentHandler(){

      list = new ArrayList<Student>(); 
    }

  public void saveStudents(Scanner s){

      //function to save students to file and serialize them 
      
      //flag with bool value, only changes when input file exists 
      boolean flag=false;


      //do while contains catch and try to implement input of file name 
      do{
	  
         System.out.println(); 

	 System.out.println("Enter file name for serialization");
      
         String name= s.next();  // grabs input 
	  
      try{
	  //ObjectOutputstream will read serialize list into file 
          ObjectOutputStream serializedFile= new ObjectOutputStream( new FileOutputStream(name, true));

          flag=true; 

	  //writes serialized Students in list to file 
          for( int i= 0; i<list.size(); ++i)
	      serializedFile.writeObject(list.get(i));                    
      
      }//end try

      //exception to handle if the input file does not exit 
      catch(IOException x){
	  
	System.out.println("File does not exist,please Enter another one");
	System.out.println();
	System.out.println(); 
	s.next();
	
      }//end catch 
      }while(flag==false); 
  }//end function

   public void loadStudents(Scanner s) throws ClassNotFoundException {

      // clear list to load new Students from  serialized file 
      list.clear();

      //flag with bool value, only changes when input file exists 
      boolean flag=false; 

      do{
	
	  System.out.println("Enter file to load from");
	  String name= s.next();
	
        try{
	    //Object will deserialize the file 
	    ObjectInputStream deserializedFile= new ObjectInputStream(new FileInputStream(name));

	    flag=true;	   
	    
	    while(true){ // while there is info to read from file keep adding Students to list
	   	
		list.add((Student)(deserializedFile.readObject()));	    
	    }	   
	  }
	
	catch(EOFException e){ // returns when end of file is reached 
	   return; 
         }

	//handles if file contains serialized objects that are not Student
	catch(ClassNotFoundException c){
	    System.out.println("There is another class other than Student in file");
	   
	    s.next(); 
	}
	    
	catch(IOException x){   // handles errors if the file does not exit
	   System.out.println("File not found");	   
	   s.next(); 
	}
	
      }while(flag==false); //end do
      
   }//end loadStudents()

 public void addStudent(Scanner s){

     /*Function to enter information of one Student to list */
     
	System.out.print("Please input a first name: ");

	String fn= s.next();  //grabs name 

	System.out.print("Please input a last name: ");

	String ln= s.next(); // grans last name 

	//Student constructed with informaion from user 
	Student newStudent = new Student(fn, ln);
	
        list.add(newStudent);	//ads Student to list 

	System.out.print("Please input student homework grades one at a time (negative value to finish): ");

	//check if double and negative

	//adds grades to studet 
	Double n= new Double(s.nextDouble());

        newStudent.addHW(n); 

	boolean flag= false;

	do{       
	    	
	System.out.print("Please add another homework grade ");

	//check if double 
	Double d = new Double(s.nextDouble());

	if(d <0){
	 flag=false;
	 break;
	}
        newStudent.addHW(d); 
     
	}while(flag= true);

	//end of adding grades to student 

	System.out.print("Please input student test grades one at a time (negative value to finish): ");

	
	//check if double and negative

	
	//adds test to students 

	Double testg= new Double (s.nextDouble());

	newStudent.addTest(testg);

	do{       
	    	
	System.out.print("Please add nother test grade ");

	//check if double
	
	Double testGrade = new Double (s.nextDouble());

	if(testGrade < 0){

	    flag=false;

	    break;
	}
	
        newStudent.addTest(testGrade); 

	}while(flag= true);       
    }
    //end of adding tests to student 

    public void printAllStudents(){

	// set grade for each student in list 
        for(int i=0; i<list.size(); i++)
	    list.get(i).calcGrade();
	
	// use function from Student to print Student to console 
	for(Student s : list)
	    System.out.println(s.toString()); 	    

	//Prints how many students from static variable
	//Not working 
	
       System.out.println("Printed " + Student.getNumStudents() + " Student Records");  

    }										     										     
   public void clearAllStudents(){
       
       //erase Student elements from list 
	list.clear(); 
      }
    
   private static void printMenu(){
       
	    System.out.println("1: Print out all loaded students");
	    System.out.println("2: Add student");
	    System.out.println("3: Clear students");
	    System.out.println("4: Save student to file");
	    System.out.println("5: Load students from file");
	    System.out.println("6: Quit");	    
	}
    
  public static void main(String[] args) throws IOException, ClassNotFoundException{
	
	Scanner input= new Scanner(System.in); 

	StudentHandler roster= new StudentHandler();

	Integer number =0;        


	//do while that runs the whole program until input == 6 
	do{    
	   
	printMenu();

	// try block to handle if input is not type int or if input not in the tange of 1-6
	try{
	  number = new Integer(input.nextInt());
        
	 if(number >6 || number < 0)
	     
	    throw new InputMismatchException(" Wrong number range"); 

	 if(number==1)
	    roster.printAllStudents();

	 if(number==2)
	    roster.addStudent(input);

	 if(number==3)
	    roster.clearAllStudents();

	 if(number==4)
	    roster.saveStudents(input);

	 if(number==5)
	    roster.loadStudents(input); 
       
	 if(number==6){
	    System.out.println("Good-bye");
	    System.exit(0);
	 }	
	}//end try
	
	//handles wrong input exception
        catch(InputMismatchException i){
        System.out.println("Invalid choice try again");
	System.out.println();
	System.out.println();
	input.next(); 
       }
       
    }while(number != 6); 
	             		
    }//end main 

}//end StudentHandler class 
