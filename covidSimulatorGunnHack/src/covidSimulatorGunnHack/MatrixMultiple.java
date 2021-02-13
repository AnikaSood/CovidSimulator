package covidSimulatorGunnHack;

public class MatrixMultiple {

	
	public double[][] MatrixMult (double[][] a, double[][] b) {
		
		double[][] transCopy = Main.transition;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				a[i][j] = transCopy[i][j];
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = 	a[i][0] * transCopy[0][j] +
							a[i][1] * transCopy[1][j] +
							a[i][2] * transCopy[2][j] +
							a[i][3] * transCopy[3][j];
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				a[i][j] = b[i][j];
			}
		}
			
		return a;
	}
}
