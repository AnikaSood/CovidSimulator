package covidSimulatorGunnHack;


public class Periods {
	
	public double[] perCalc(double periods) { //calculate "calc" the predictions of population status after "c" periods
		MatrixMultiple obj = new MatrixMultiple(); //making the object so that we're able to call MatrixMult
		
		// a*(b^c) 
		//double[][] curr = a;
	    double [][] atrans = Main.transition;
	    double [][] overviewb = Main.Overview;
	    double [] c = new double [4];
	    double [][] curr = new double [4][4];
	    for(int i = 0; i < periods; i++) { 
			//curr = obj.MatrixMult(curr, b); //first runthru will have a*b, all following runthrus will have (a*b)*b how ever many times it shall (c)
			curr = obj.MatrixMult(atrans);
		
		}
	    
		for(int i = 0; i < 4; i++) {
			c[i] = 	overviewb[0][0] * curr[0][i] + overviewb[0][1] * curr[1][i] 
					+ overviewb[0][2] * curr[2][i] + overviewb[0][3] * curr[3][i];
		}
		return c; //After "periods" month how many people are infected/recovered/dead/not infected
	}	
}