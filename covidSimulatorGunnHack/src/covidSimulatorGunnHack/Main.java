package covidSimulatorGunnHack;
import java.util.Scanner;

//DRIVER CLASS
public class Main {
	
	/*
	 * App option: calc when will covid end, how many ppl are 
	 * dead after period, how many ppl are in (all states after periods),
	 *  possibility of getting surviving/dying after vaccine
	 */
	
	
	
	public static double[][] transition = new double[4][4];
	public static double[][] Overview = new double[1][4];
	
	public static void main(String[] Args) {		
		//s
		Scanner sc = new Scanner(System.in);
		
		//Get user input: 
		System.out.println("What is the infected population (# of people)?");
		double newConfPop = sc.nextDouble(); //conf (confirmed cases)
		System.out.println("What is the average numeber of people who recover monthly?");
		double newRecovPop = sc.nextDouble();
		System.out.println("What is the average number of people that died monthly?");
		double newDeadPop = sc.nextDouble();
		System.out.println("What is the total population?");
		double totalPop = sc.nextDouble();
		System.out.println("How many months do you wish to predict?");
		//int periods = sc.nextInt();
		double periods = sc.nextDouble();
		System.out.println("What is the proportion of people getting vaccinated each month?");
		double vaccProp = sc.nextDouble();
		
		//# of people that were not infected (monthly avg)
		double notInfected;
		notInfected = totalPop - newConfPop;
		
		//summary of OG inputs
		
		Overview[0][0] = notInfected;
		Overview[0][1] = newConfPop;
		Overview[0][2] = newRecovPop;
		Overview[0][3] = newDeadPop;
		
		
		//fill transition matrix with 0.0 default value
		

		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				transition[r][c] = 0.0;
			}
		}
		
		transition[0][0] = notInfected/totalPop; //this is proportion of not infected population in the total population
		transition[0][1] = newConfPop/totalPop; //pretty straightforward
		transition[1][2] = newRecovPop/newConfPop; //pretty straightforward
		transition[1][3] = newDeadPop/newConfPop;
		transition[2][2] = 1.0; //possibility of people who are recovered to get recovered. END STATE. recovered don't change status
		transition[3][3] = 1.0; //possibility of dead people to die. END STATE. dead don't resurrect
		
		System.out.println("Unaffected: ");
		System.out.println("Infected: ");
		System.out.println("Vaccinated: ");
		System.out.println("Recovered: ");
		System.out.println("Dead: ");
		
		
	
		
		
		Periods p = new Periods(); //Periods object initiate so that we can call
		p.perCalc(periods); //perCalc of "overview", "transition", multiplied for number of periods
		
		Vaccination v = new Vaccination();
		v.Vacc(Overview, transition, periods, vaccProp);
	}

}
