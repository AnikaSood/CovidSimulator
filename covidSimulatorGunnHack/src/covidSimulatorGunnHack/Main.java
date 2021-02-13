package covidSimulatorGunnHack;
import java.util.Scanner;

public class Main {
	
	/*
	 * App option: calc when will covid end, how many ppl are 
	 * dead after period, how many ppl are in (all states after periods),
	 *  possibility of getting surviving/dying after vaccine
	 */
	
	
	
	public static double[][] transition;
	
	public Main() {
		transition = new double[4][4];
	}
	
	public static void main(String[] Args) {
		
		//s
		Scanner sc = new Scanner(System.in);
		
		//Get user input: 
		System.out.println("What is the infected population (# of people)?");
		int newConfPop = sc.nextInt(); //conf (confirmed cases)
		System.out.println("What is the average numeber of people who recover monthly?");
		int newRecovPop = sc.nextInt();
		System.out.println("What is the average number of people that died monthly?");
		int newDeadPop = sc.nextInt();
		System.out.println("What is the total population?");
		int totalPop = sc.nextInt();
		System.out.println("How many months do you wish to predict?");
		int periods = sc.nextInt();
		System.out.println("What is the proportion of people getting vaccinated each month?");
		double vaccProp = sc.nextDouble();
		
		//# of people that were not infected (monthly avg)
		int notInfected;
		notInfected = totalPop - newConfPop;
		
		//summary of OG inputs
		double[][] Overview = new double[1][4];
		Overview[0][0] = (double) notInfected;
		Overview[0][1] = (double) newConfPop;
		Overview[0][2] = (double) newRecovPop;
		Overview[0][3] = (double) newDeadPop;
		

		
		//fill transition matrix with 0.0 default value
		int r = 0;
		int c = 0;

		for(r = 0; r < 3; r++) {
			for(c = 0; c < 3; c++) {
				transition[r][c] = 0.0;
			}
		}
		
		
		transition[0][0] = (double) notInfected/totalPop; //this is proportion of not infected population in the total population
		transition[0][1] = (double) newConfPop/totalPop; //pretty straightforward
		transition[1][2] = (double) newRecovPop/newConfPop; //pretty straightforward
		transition[1][3] = (double) newDeadPop/newConfPop;
		transition[2][2] = 1.0; //possibility of people who are recovered to get recovered. END STATE. recovered don't change status
		transition[3][3] = 1.0; //possibility of dead people to die. END STATE. dead don't resurrect
		
		Periods p = new Periods(); //Periods object initiate so that we can call
		p.perCalc(Overview, transition, periods); //perCalc of "overview", "transition", multiplied for number of periods
				

	}

}
