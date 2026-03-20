public class Driver{
  public static void main(String[] args){
    double[][] k = new double[43][3];
    double[] n = new double[]{0,0,0.005,0.005,0.01,0.01,0.02,0.025,0.03,0.04,0.05,0.06,0.07,0.08,0.095,0.11,0.125,0.14,0.155,0.175,0.195,0.215,0.235,0.255,0.28,0.30,0.32,0.345,0.37,0.40,0.43,0.46,0.49,0.53,0.56,0.595,0.63,0.67,0.70,0.75,0.79,0.83,0.87};
    double[][] l = new double[43][1];
    for (int i = 0; i<43; i++){
      double c = i/100.0;
      k[i][0]=1;
      k[i][1]=c;
      k[i][2]=c*c;
      l[i][0]=n[i];
    }
    Matrix A = new Matrix(k);
    Matrix y = new Matrix(l);
    System.out.print(A.transpose().multiply(A).inverse().multiply(A.transpose()).multiply(y));
  }
}
