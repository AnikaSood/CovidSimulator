package covidSimulatorGunnHack;

public class FundamentalN {

	double[][] qRegion, rRegion;
	double[][] f; //inverse
	double[][] fr;
	double[][] res;
	
	public FundamentalN()
	{
		qRegion = new double[2][2];
		rRegion = new double[2][2];
		f = new double[2][2];
		fr = new double[2][2];
		res = new double[1][2];
	}
	
	public void split() //Creating qRegion & rRegion from the transition copy
	{
		double[][] transCopy = Main.transition; //copy transition matrix from main
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				qRegion[i][j] = transCopy[i][j];
				rRegion[i][j] = transCopy[i+2][j+2];
			}
		}
	}
	
	public void inverse() {
		//identity minus 
		f[0][0] = 1 - qRegion[0][0];
		f[0][1] = 0 - qRegion[0][1];
		f[1][0] = 0 - qRegion[1][0];
		f[1][1] = 1 - qRegion[1][1];
		double det = f[0][0] * f[1][1] - f[0][1] * f[1][0];
		f[0][0] = f[1][1] / det;
		f[0][1] = - f[0][1] / det;
		f[1][0] = - f[1][0] / det;
		f[1][1] = f[0][0] / det;
		
	}

	public double[][] possibility() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				fr[i][j] = f[i][0] * rRegion[0][j] + f[i][1] * rRegion[1][j];
			}
		}

	res[0][0] = fr[1][0];
	res[0][1] = fr[1][1];
	
	return res;
	}
}

/* split the transition thing into the smaller matricies
 * f = [identity - q]^(-1)
 * a = f x r
 * final answers (bottom 2 parts of a)
 */
