public class Driver{
  public static void main(String[] args){
    Matrix M = Matrix.randomMatrix(3);
    System.out.print(M);
    Matrix N = M.inverse();
    System.out.print(N);
    System.out.print(M.multiply(N));
  }
}
