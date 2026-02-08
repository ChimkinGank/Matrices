public class Driver{
  public static void main(String[] args){
    Matrix M = Matrix.randomMatrix(3);
    System.out.print(M);
    System.out.print(M.det());
  }
}
