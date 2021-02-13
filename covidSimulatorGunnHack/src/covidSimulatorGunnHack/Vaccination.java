package covidSimulatorGunnHack;

public class Vaccination {

	public double[][] Vacc(double[][] a, double[][] b, int periods, double vaccProp) { //calculate "calc" the predictions of population status after "c" periods
		
		double trend = 1 - vaccProp; //the % of uninfected people who are left w/o vacc
		
		Periods p = new Periods();
		double[][] finMatrix = p.perCalc(a, b, periods);
		
		finMatrix[0][1] = finMatrix[0][1] * trend;
		finMatrix[0][0] = 1 - finMatrix[0][1];
		
		return finMatrix; //return 1 * 4 matrix
	}	
	
	
}

