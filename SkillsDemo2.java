/*Name: Dylan Coveney
 * Date:08/03/2023
 * This program will read in a persons details regarding their age, name and 
 * number of dependents and output the cost and an invoice for their healthcare
 * policy
 */
package skillsDemo2;

import java.text.DecimalFormat; 
import java.util.Scanner;

public class SkillsDemo2 {
	
	//Declaring class to store dependent details
    static class Dependent {
        String name;
        byte age;
        
        public Dependent(String name, byte age) {
            this.name = name;
            this.age = age;
        }
    }

	public static void main(String[] args) {
		
		//Declaring variables and scanners.
		Scanner sc = new Scanner(System.in);
		DecimalFormat moneyFormat = new DecimalFormat( "€###,##0.00");
		String name, roomType;
		byte age;
		byte dependent = 0;
		float basePolicy, privateRoom = 0, dependentCost, totalBeforeVat, inpatient, vat; 
		basePolicy = 436;
		dependentCost = 0;
		inpatient = 0;
		
		//Takes persons name for policy
		System.out.println("What is your name?");
		name = sc.nextLine();
		
		/*Calculates age of person taking out policy
		 * and determines if they are eligible or if they 
		 * should be charged an additional fee. */
		
		do {
		    System.out.println("What age are you? You must be over 18 to take out a policy.");
		    age = sc.nextByte();
		    if (age < 18) {
		        System.out.println("As you are under 18, you cannot take out a policy");
		    } else {
		        if (age >= 18 && age <= 50) {
		            basePolicy = 436;
		        } else {
		            basePolicy = 526;
		        }
		    }
		} while (age < 18);
			
		/*Calculating number of dependents and putting them in an array, also calculating
		 * dependent cost and obtaining dependent details regarding name and age.
		 */
		Dependent[] dependents = new Dependent[6];
		int numDependents = 0;

		do {
		    System.out.println("How many dependents do you have on this policy?");
		    dependent = sc.nextByte();
		    if (dependent < 0 || dependent > 6) {
		        System.out.println("Invalid number entered, number of dependents must be between 0-6");
		    } else {
		    	if (dependent == 1) {
		    	    dependentCost = 270;
		    	} else if (dependent == 2) {
		    	    dependentCost = 430;
		    	} else if (dependent == 3) {
		    	    dependentCost = 530;
		    	} else if (dependent >= 4 && dependent <= 6) {
		    	    dependentCost = 580;
		    	} else {
		    	    dependentCost = 0;
		    	}
		        for (int i = 0; i < dependent; i++) {
		            System.out.println("Enter dependent " + (i+1) + "'s name:");
		            String dependentName = sc.next();
		            System.out.println("Enter dependent " + (i+1) + "'s age:");
		            byte dependentAge = sc.nextByte();
		            if (dependentAge >= 18) {
		                System.out.println("Invalid age entered, dependents must be under 18 years old");
		                i--;
		            } else {
		                dependents[numDependents] = new Dependent(dependentName, dependentAge);
		                numDependents++;
		            }
		        }
		        if (numDependents == 0) {
		            System.out.println("No dependents added to policy");
		        }
		    }
		} while (dependent < 0 || dependent > 6);

		//Obtaining details of inpatient insurance and room details
		System.out.println("Do you want inpatient insurance? (y/n)");
		String inpatientChoice = sc.next();
		if (inpatientChoice.equalsIgnoreCase("y")) {
		    inpatient = 200;
		    System.out.println("Do you want a private room? (y/n)");
		    String roomChoice = sc.next();
		    if (roomChoice.equalsIgnoreCase("y")) {
		        privateRoom = 100;
		        roomType = "Private";
		    } else {
		        privateRoom = 0;
		        roomType = "Semi-Private";
		    }
		} else {
		    inpatient = 0;
		    privateRoom = 0;
		    roomType = "NA";
		}
		
		//Calculate the total before VAT and the VAT itself
		totalBeforeVat = basePolicy + inpatient + privateRoom + dependentCost;
		vat = (float)(totalBeforeVat*0.21);

		// Output policy information 
		System.out.println("\t\tMTS Healthcare Policy");
		System.out.println("\nName: " + name);
		System.out.println("Number of Dependents: " + numDependents);
		for (int i = 0; i < numDependents; i++) {
		    System.out.println("Dependent #" + (i+1) + ": \t\t\t" + dependents[i].name + " (Age:" + dependents[i].age + ")");
		}
		if (numDependents > 0) {
		    System.out.println("Dependent cost: \t\t\t\t" + moneyFormat.format(dependentCost));
		}
		System.out.println("Basic Cost(Outpatient Care): \t\t\t" + moneyFormat.format(basePolicy));
		if (inpatientChoice.equalsIgnoreCase("y")) {
		    System.out.println("Inpatient cost: \t\tYes \t\t" + moneyFormat.format(inpatient));
		} else {
		    System.out.println("Inpatient cost: \t\tNo \t\t" + moneyFormat.format(inpatient));
		}
		System.out.println("Room type: \t\t\t" + roomType + "\t\t" + moneyFormat.format(privateRoom));
		System.out.println("Total before VAT:\t\t\t\t" + moneyFormat.format(totalBeforeVat));
		System.out.println("VAT:\t\t\t\t\t\t" + moneyFormat.format(vat));
		System.out.println("Total:\t\t\t\t\t" + moneyFormat.format(totalBeforeVat + vat));
		
		sc.close();
		
	}

}
