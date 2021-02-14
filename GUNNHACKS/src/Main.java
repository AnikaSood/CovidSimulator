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
System.out.println("What is the average number of people who recover monthly?");
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
transition[1][1] = 1 - transition[1][2] - transition[1][3];  //add
transition[2][2] = 1.0; //possibility of people who are 
transition[3][3] = 1.0; //possibility of dead people to die. END STATE. dead don't resurrect
//notInfectedPercent newlyInfectedPercent 
//NULL NULL 

//begin loop
//Period
//Vaccinations 
Periods p = new Periods(); //Periods object initiate so that we can call

double[] postPerCalc = p.perCalc(periods); //perCalc of "overview", "transition", multiplied for number of periods
System.out.println("stats for period class"+ postPerCalc);

Vaccination v = new Vaccination();
double[] postVacc = v.Vacc(periods, vaccProp);

//prints the stats for each period
// calculate the vaccine percentage and period stats for each period
//this means calling the period and vaccine class every time
//for (int period = 0; period < periods; period++) {
//}
//postPerCalc = p.perCalc();
System.out.println("\n Assume the vaccine is not out... \n");
System.out.println(periods + " MONTHS PASSED... \n");
System.out.println("Unaffected: " + (int) postPerCalc[0]);
System.out.println("Infected: " + (int) postPerCalc[1]);
System.out.println("Recovered: " + (int) postPerCalc[2]);
System.out.println("Deaths: " + (int) postPerCalc[3]);
System.out.println("\n");
System.out.println("Assume the vaccine is out and distributing at the entered trend...");
System.out.println("\n" + periods + " MONTHS PASSED... \n");
System.out.println("Unaffected after vaccinated: " + (int) postVacc[0]);
System.out.println("Infected after vaccinated: \": " + (int) postVacc[1]);
System.out.println("Recovered after vaccinated: \": " + (int) postVacc[2]);
System.out.println("Deaths after vaccinated: \": " + (int) postVacc[3]);


//System.out.println("Vaccinated: " + postVacc[0]);

}

}