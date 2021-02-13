package covidSimulatorGunnHack;

public class FundamentalN {

	double[][] qRegion, rRegion; //regions of transition matrix
	double[][] f; //[identity - q]^(-1)
	double[][] fr; //f times r
	double[][] res; //probability of recovering/dying after being infected
	
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
	
	//f = [identity - q]^(-1)
	public void inverse() {
		//identity minus qRegion 
		f[0][0] = 1 - qRegion[0][0];
		f[0][1] = 0 - qRegion[0][1];
		f[1][0] = 0 - qRegion[1][0];
		f[1][1] = 1 - qRegion[1][1];
		
		//inverse of result
		double det = f[0][0] * f[1][1] - f[0][1] * f[1][0];
		f[0][0] = f[1][1] / det;
		f[0][1] = - f[0][1] / det;
		f[1][0] = - f[1][0] / det;
		f[1][1] = f[0][0] / det;
		
	}

	//get final results (f times r)
	public double[][] possibility() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				fr[i][j] = f[i][0] * rRegion[0][j] + f[i][1] * rRegion[1][j];
			}
		}
	
	//probability of recovering/dying after being infected
	res[0][0] = fr[1][0]; //recovering
	res[0][1] = fr[1][1]; //dying
	
	return res;
	}
}

/* split the transition thing into the smaller matricies
 * f = [identity - q]^(-1)
 * a = f x r
 * final answers (bottom 2 parts of a)
 */
