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

  private void elem1(int a, int b){
    double[]k=entries[a];
    entries[a]=entries[b];
    entries[b]=k;
  }

  private void elem2(int a, int b, double k){
    for(int c=0; c<entries[0].length; c++){
      entries[b][c]+=k*entries[a][c];
    }
  }

  private void elem3(int a, double k){
    for(int c=0; c<entries[0].length; c++){
      entries[a][c]*=k;
    }
  }

  private void refstep(int k,int s){
    if (k<entries.length&&s<entries[0].length){
      int rows=entries.length;
      if (entries[k][s]==0){
        int c=k;
        boolean allzero=true;
        int winner=k;
        while (c<rows){
          if (entries[c][s]!=0){
            allzero=false;
            winner=c;
            break;
          }
          else{
            c++;
          }
        }
        if (allzero){
          refstep(k,s+1);
          return;
        }
        else{
          elem1(k,winner);
        }
      }
      elem3(k,1.0/entries[k][s]);
      if (k!=rows-1){
        int counter=k+1;
        while (counter<rows){
          elem2(k,counter,-entries[counter][s]);
          counter+=1;
        }
        refstep(k+1,s+1);
      }
    }
  }

  private int pivloc(int k){
    int cols=entries[0].length;
    int p=0;
    while (p<cols){
      if (entries[k][p]!=0){
        return p;
      }
      p++;
    }
    return -1;
  }

  private void rowdrag(int k){
    int rows=entries.length;
    while (k<rows-1){
      elem1(k,k+1);
      k+=1;
    }
  }

  public void rref(){
    refstep(0,0);
    int rows=entries.length;
    int k=0;
    while (k<rows){
      if (pivloc(k)==-1){
        rowdrag(k);
      }
      k+=1;
    }
    k=0;
    int last=rows-1;
    while (k<rows){
      if (pivloc(k)==-1){
        last=k-1;
        break;
      }
      k+=1;
    }
    if (last!=-1){
      k=0;
      while (k<=last){
        int s=pivloc(k);
        int counter=k-1;
        while (counter>=0){
          elem2(k,counter,-entries[counter][s]);
          counter-=1;
        }
        k+=1;
      }
    }
  }

  public Matrix add(Matrix m){
    if (getRows()!=m.getRows()||getColumns()!=m.getColumns()){
      throw new IllegalArgumentException("Matrices must be same size to add");
    }
    double[][] k = new double[getRows()][getColumns()];
    for (int i = 0; i<getRows(); i++){
      for (int j = 0; j<getColumns(); j++){
        k[i][j]=entries[i][j]+m.entries[i][j];
      }
    }
    Matrix n = new Matrix(k);
    return n;
  }


}
