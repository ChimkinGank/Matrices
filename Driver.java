public class Driver{
  public static void main(String[] args){
    Matrix a = Matrix.randomMatrix();
    Matrix b = Matrix.randomMatrix();
    Matrix abT = a.multiply(b).transpose();
    Matrix bTaT=b.transpose().multiply(a.transpose());
    System.out.println(abT.equals(bTaT));
    Matrix c = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,10}});
    c.rref();
    System.out.println(c);
  }
}
