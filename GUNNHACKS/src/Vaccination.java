//credits to Mr Xu

public class Vaccination {

	 public double[] Vacc(double periods, double vaccProp) { //calculate "calc" the predictions of population status after "c" periods
	  
	  double trend = 1 - vaccProp; //the % of uninfected people who are left w/o vacc
	  
	  //Periods p = new Periods();
	  //double[] finMatrix = p.perCalc(periods);
	  
	  MatrixMultiple obj = new MatrixMultiple(); //making the object so that we're able to call MatrixMult
	  
	  // a*(b^c) 
	  //double[][] curr = a;
//	     double [][] atrans = Main.transition;
	     double [][] atrans = new double [4][4];
	  
	     double [][] overviewb = Main.Overview;
	     double [] c = new double [4];
//	     double [][] curr = Main.transition;
	     double [][] curr = new double [4][4];
//	     double [][] transCopy = Main.transition;
	     double [][] transCopy = new double [4][4];
	     for(int j = 0; j < 4; j++) {
	    	  for(int k = 0; k < 4; k++) {
	    		  atrans[j][k] = Main.transition[j][k];
	    		  curr[j][k] = Main.transition[j][k];
	    		  transCopy[j][k] = Main.transition[j][k];
	    	  }
	      }
	     
	 	     
	     for(int k = 0; k < periods - 1; k++) { 
	   //curr = obj.MatrixMult(curr, b); //first runthru will have a*b, all following runthrus will have (a*b)*b how ever many times it shall (c)
	    	  double [][] b = new double [4][4];	      
	    	  for(int i = 0; i < 4; i++) {
	    		   for(int j = 0; j < 4; j++) {
	    		    b[i][j] =  curr[i][0] * transCopy[0][j] +
	    		       curr[i][1] * transCopy[1][j] +
	    		       curr[i][2] * transCopy[2][j] +
	    		       curr[i][3] * transCopy[3][j];
	    		   }
	    		  }
	    	  for(int i = 0; i < 4; i++) {
	    		   for(int j = 0; j < 4; j++) {
	    			    	  curr[i][j] = b[i][j];
	    		   }
    		  }
	    	  transCopy[0][1] = transCopy[0][1]*trend;
	    	  transCopy[0][0] = 1 - transCopy[0][1];
	  
	    	 
	     }
	  
	     
	  for(int i = 0; i < 4; i++) {
	   c[i] =  overviewb[0][0] * curr[0][i] + overviewb[0][1] * curr[1][i] 
	     + overviewb[0][2] * curr[2][i] + overviewb[0][3] * curr[3][i];
	  }
	  return c; //After "periods" month how many people are infected/recovered/dead/not infected
	  
	 // finMatrix[1] = finMatrix[1] * trend; //unvacc
	 // finMatrix[0] = 1 - finMatrix[1]; //vacc
	  
	 // return finMatrix; //return 1 * 4 matrix
	  
	  
	 } 
	 
	 
	}