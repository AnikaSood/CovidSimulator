public class MatrixMultiple{

 
 //public double[][] MatrixMult (double[][] a, double[][] b) {
   public double [][] MatrixMult (double[][] a) {
 
  double[][] transCopy = Main.transition;
  
  double [][] b = new double [4][4];
  
  for(int i = 0; i < 4; i++) {
   for(int j = 0; j < 4; j++) {
    b[i][j] =  a[i][0] * transCopy[0][j] +
       a[i][1] * transCopy[1][j] +
       a[i][2] * transCopy[2][j] +
       a[i][3] * transCopy[3][j];
   }
  }

  
  return b;
 }
}