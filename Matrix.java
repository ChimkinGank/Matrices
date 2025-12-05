import java.util.Random;

public class Matrix{
  private double[][] entries;

  public String toString(){
    String returned = "";
    for (int i = 0; i<entries.length; i++){
      returned+="[";
      for (int j = 0; j<entries[0].length-1; j++){
        returned+=entries[i][j]+", ";
      }
      returned+=entries[i][entries[0].length-1]+"]";
      returned+="\n";
    }
    return returned;
  }

  public int getRows(){
    return entries.length;
  }

  public int getColumns(){
    return entries[0].length;
  }

  public boolean canMultiply(Matrix m){
    if (getColumns()==m.getRows()){
      return true;
    }
    else{
      return false;
    }
  }

  public Matrix multiply(Matrix m){
    double[][] finish = new double[entries.length][m.entries[0].length];
    for (int i = 0; i<entries.length; i++){
      for (int j = 0; j<m.entries[0].length; j++){
        for (int k = 0; k<entries[0].length; k++){
          finish[i][j]+=entries[i][k]*m.entries[k][j];
        }
      }
    }
    Matrix n = new Matrix(finish);
    return n;
  }

  public double get(int r, int c){
    return entries[r][c];
  }

  public Matrix(double[][] stuff){
    boolean kill = false;
    if (stuff.length==0){
      kill = true;
    }
    else if (stuff[0].length==0){
      kill = true;
    }
    int cols = stuff[0].length;
    for (int i = 0; i<stuff.length; i++){
      if (stuff[i].length!=cols){
        kill = true;
      }
    }
    if (kill){
      throw new IllegalArgumentException("Matrices are rectangular and nonempty");
    }
    entries=stuff;
  }

  public Matrix transpose(){
    double[][] thing = new double[entries[0].length][entries.length];
    for (int i = 0; i<entries[0].length; i++){
      for (int j = 0; j<entries.length; j++){
        thing[i][j]=entries[j][i];
      }
    }
    Matrix m = new Matrix(thing);
    return m;
  }

  public boolean equals(Matrix thing){
    boolean boo = true;
    if (getRows()!=thing.getRows()||getColumns()!=thing.getColumns()){
      boo = false;
    }
    for (int i = 0; i<getRows(); i++){
      for (int j = 0; j<getColumns(); j++){
        if (entries[i][j]!=thing.entries[i][j]){
          boo = false;
        }
      }
    }
    return boo;
  }

  public static Matrix randomMatrix(){
    double[][] rand = new double[100][100];
    Random seed = new Random();
    for (int i = 0; i<100; i++){
      for (int j = 0; j<100; j++){
        rand[i][j]=seed.nextInt()%100;
      }
    }
    Matrix win = new Matrix(rand);
    return win;
  }
}
