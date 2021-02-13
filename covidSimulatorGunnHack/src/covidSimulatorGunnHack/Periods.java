package covidSimulatorGunnHack;


public class Periods {
	
	public double[][] perCalc(double[][] a, double[][] b, int periods) { //calculate "calc" the predictions of population status after "c" periods
		MatrixMultiple obj = new MatrixMultiple(); //making the object so that we're able to call MatrixMult
		
		// a*(b^c) 
		double[][] curr = a;
		for(int i = 0; i < periods; i++) { 
			curr = obj.MatrixMult(curr, b); //first runthru will have a*b, all following runthrus will have (a*b)*b how ever many times it shall (c)
		}
		
		return curr; //pretty straightforward
	}	
}