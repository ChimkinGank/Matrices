public class Driver{
  public static void main(String[] args){
    Matrix a = Matrix.randomMatrix();
    Matrix b = Matrix.randomMatrix();
    Matrix abT = a.multiply(b).transpose();
    Matrix bTaT=b.transpose().multiply(a.transpose());
    System.out.println(abT.equals(bTaT));
  }
}
