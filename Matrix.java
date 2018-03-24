/**
Brittany Lei bclei
CMPS101: Programming Assignment 3
**/

public class Matrix {
  private class Entry {
    int col;
    double val;

    // Constructor
    Entry(int col, double val) {
      this.col = col;
      this.val = val;
    }

    // toString(): overrides Object's toString() method
    public String toString() {
      return String.valueOf(val);
    }

    // equals(): overrides Object's equals() method
    public boolean equals(Object x) {
      boolean eq = false;
      Entry that;
      if(x instanceof Entry){
        that = (Entry) x;
        eq = (this.col==that.col && this.val==that.val);
      }
      return eq;
    }
  }

  // Fields
  private List[] row;
  private int n;
  private int numEn;


  // Constructor
  // Makes a new n x n zero Matrix. pre: n>=1
  Matrix(int n) {
    row = new List[n];
    for (int i = 0; i < n; i++) {
      row[i] = new List();
    }
    this.n = n;
    numEn = 0;
  }
  // Access functions
  // Returns n, the number of rows and columns of this Matrix
  int getSize() {
    return n;
  }

  // Returns the number of non-zero entries in this Matrix
  int getNNZ() {
    return numEn;
  }

  // overrides Object's equals() method
  public boolean equals(Object x) {
    boolean eql = false;
    Matrix that;
    if (x instanceof Matrix) {
      eql = true;
      that = (Matrix) x;
      if (this.getNNZ() != that.getNNZ()) {
        return false;
      }
      if (this.getSize() != that.getSize()) {
        return false;
      }
      else {
        int i = 0;
        List A;
        List B;

        while (eql && i < this.getSize()) { // && this.row[i].length() != 0 && that.row[i].length() != 0) {
          A = this.row[i];
          B = that.row[i];
          eql = A.equals(B);
          i++;
        }
      }
    }

    return eql;
  }

  // Manipulation procedures
  // sets this Matrix to the zero state
  void makeZero() {
    for (int i = 0; i < n; i++){
      row[i] = new List();
    }
    numEn = 0;
  }

  // returns a new Matrix having the same entries as this Matrix
  Matrix copy() {
    Matrix C = new Matrix(getSize());
    for (int i = 0; i < n; i++) {
      if (row[i].length() != 0) {
        row[i].moveFront();
      }
      for (int j = 0; j < row[i].length(); j++) { // traverse row
        C.row[i].append(row[i].get());
        C.numEn++;
        row[i].moveNext();
      }
    }
    return C;
  }

  // changes ith row, jth column of this Matrix to x
  // pre: 1<=i<=getSize(), 1<=j<=getSize()
  void changeEntry(int i, int j, double x) {
    if (i < 1 || i > getSize()) {
      throw new RuntimeException(
        "Matrix Error: changeEntry() called on nonexistent row");
    }
    if (j < 1 || j > getSize()) {
      throw new RuntimeException(
        "Matrix Error: changeEntry() called on nonexistent column");
    }

    int k = i-1;
    if (row[k].length() == 0) { // empty row
      if (x != 0) {
        row[k].append(new Entry(j, x));
        numEn++;
      }
    }
    else {
      row[k].moveFront();
      Entry e = (Entry) row[k].get();
      int ind = row[k].index();
      while (e.col < j && row[k].get() != row[k].back()) {
        row[k].moveNext();
        e = (Entry)row[k].get();
      }

      if (e.col != j && x != 0) { // e.val == 0
        if (e.col > j)
          row[k].insertBefore(new Entry(j, x));
        if (e.col < j)
          row[k].insertAfter(new Entry(j, x));
        numEn++;
      }
      else if (e.col == j && e.val != 0 && x == 0) {
        // if (row[k].index() != -1)
        row[k].delete();
        numEn--;
      }
      else if (e.col == j && e.val != 0 && x != 0) {
        row[k].insertBefore(new Entry(j, x));
        row[k].delete();
      }
      // else e.val == 0 && x == 0, do nothing
    }
  }

  // returns a new Matrix that is the scalar product of this Matrix with x
  Matrix scalarMult(double x) {
    Matrix C = new Matrix(getSize());
    for (int i = 0; i < n; i++) {
      if (row[i].length() != 0) {
        row[i].moveFront();
      }
      Entry e;
      for (int j = 0; j < row[i].length(); j++) {
        e = (Entry)row[i].get();
        C.changeEntry(i+1, e.col, x * e.val);
        row[i].moveNext();
      }
    }
    return C;
  }

  // returns a new Matrix that is the sum of this Matrix with M
  // pre: getSize()==M.getSize()
  Matrix add(Matrix M) {
    if (this.getSize() != M.getSize()) {
      throw new RuntimeException(
        "Matrix Error: add() called on differently sized Matrices");
    }

    Matrix C = new Matrix(getSize());
    List lc;
    for (int i = 0; i < n; i++) {
      lc = addList(i, M.row[i], 0); // 0 for add
      C.row[i] = lc;
      C.numEn = C.numEn + lc.length();
    }

    return C;
  }

  // returns a new Matrix that is the difference of this Matrix with M
  // pre: getSize()==M.getSize()
  Matrix sub(Matrix M) {
    if (this.getSize() != M.getSize()) {
      throw new RuntimeException(
        "Matrix Error: subtract() called on differently sized Matrices");
    }

    Matrix C = new Matrix(getSize());
    List lc;
    for (int i = 0; i < n; i++) {
      lc = addList(i, M.row[i], -1); // -1 for sub
      C.row[i] = lc;
      C.numEn = C.numEn + lc.length();
    }

    return C;
  }

  // returns a new Matrix that is the transpose of this Matrix
  Matrix transpose() {
    Matrix C = new Matrix(getSize());
    for (int i = 0; i < n; i++) {
      row[i].moveFront(); // start at the front of the row
      Entry e;
      for (int j = 0; j < row[i].length(); j++) {
        e = (Entry)row[i].get();
        C.changeEntry(e.col, i+1, e.val); // row = col and col = row
        row[i].moveNext();
      }
    }
    return C;
  }

  // returns a new Matrix that is the product of this Matrix with M
  // pre: getSize()==M.getSize()
  Matrix mult(Matrix M) {
    if (this.getSize() != M.getSize()) {
      throw new RuntimeException(
        "Matrix Error: mult() called on differently sized Matrices");
    }

    Matrix C = new Matrix(getSize());
    Matrix T = M.transpose();
    for (int i = 0; i < n; i++) {
      if (this.row[i].length() != 0) { // if all 0, mult is 0
        for (int j = 0; j < n; j++) {
          if (T.row[j].length() != 0) {
            double d = dot(this.row[i], T.row[j]); // row of this and col of M
            if (d != 0) {
              C.changeEntry(i+1, j+1, d);
            }
          }
        }
      }
    }

    return C;
  }


  // Other functions
  public String toString() { // overrides Object's toString() method
    StringBuffer sb = new StringBuffer();
    Entry e;
    for (int i = 1; i <= getSize(); i++) {
      int k = i-1;
      if (row[k].length() > 0) {
        sb.append(i + ": ");
        row[k].moveFront();
        for (int j = 0; j < row[k].length(); j++) {
          e = (Entry)row[k].get();
          sb.append("(" + e.col + ", " + e.val + ")");
          if (j != row[k].length()-1) { // last nnz entry of ith row
            sb.append(" ");
          }
          row[k].moveNext();
        }
        sb.append("\n");
      }
    }

    return new String(sb);
  }

  // returns a double that is the dot product of two lists, a and b
  private static double dot(List a, List b) {
    Entry ea;
    Entry eb;
    double d = 0;

    a.moveFront();
    b.moveFront();

    int lengths = a.length() + b.length();
    if (a.length()!= 0 && b.length() != 0) { // both rows have >0 nnz entry
      for (int i = 0; i < lengths; i++) {
        if (a.index()!= -1 && b.index() != -1) {
          ea = (Entry) a.get();
          eb = (Entry) b.get();

          // if col don't match, do nothing and move on
          if (ea.col < eb.col) {
            if (a.index() != a.length()) {
              a.moveNext();
            }
          }
          else if (ea.col > eb.col) {
            if (b.index() != b.length()) {
              b.moveNext();
            }
          }
          else { // ea.col == eb.col
            d = d + ea.val * eb.val;
            if (a.index() != a.length()) {
              a.moveNext();
            }
            if (b.index() != b.length()) {
              b.moveNext();
            }
            i++; // moved something forward twice
          }
        }
      }
    }

    return d;
  }

  // returns a new List c that is the sum of this list and List b
  private List addList(int ind, List b, int s) {
    List a = this.row[ind];
    List c = new List();
    Entry ea;
    Entry eb;
    a.moveFront();
    b.moveFront();

    Entry ec;
    int lengths = a.length() + b.length();
    for (int i = 0; i < lengths; i++) {
      if (a.length() != 0 && b.length() != 0) {
        if (a.index() == -1 && b.index() != -1) { // a has no more nnz
          eb = (Entry) b.get();
          if (s == 0)
            ec = new Entry(eb.col, eb.val);
          else // s == -1
            ec = new Entry(eb.col, -1 * eb.val);

          if (b.index() != b.length())
            b.moveNext();
          if (ec.val != 0) // append if entry isn't 0
            c.append(ec);
        }
        else if (b.index() == -1 && a.index() != -1) { // b has no more nnz
          ea = (Entry) a.get();
          ec = new Entry(ea.col, ea.val);

          if (a.index() != a.length())
            a.moveNext();
          if (ec.val != 0)
            c.append(ec);
        }
        else if (b.index() != -1 && a.index() != -1) {
          ea = (Entry) a.get();
          eb = (Entry) b.get();

          if (ea.col < eb.col) {
            ec = new Entry(ea.col, ea.val); // new entry is ea.val b/c +0
            if (a.index() != a.length())
              a.moveNext();
          }
          else if (ea.col > eb.col) {
            if (s == 0)
              ec = new Entry(eb.col, eb.val); // new entry is eb.val b/c +0
            else
              ec = new Entry(eb.col, -1 * eb.val); // -eb.val if sub(b)

            if (b.index() != b.length())
              b.moveNext();
          }
          else {
            if (s == 0)
              ec = new Entry(ea.col, ea.val + eb.val);
            else // s == -1
              ec = new Entry(ea.col, ea.val - eb.val);

            if (a.index() != a.length())
              a.moveNext();
            if (b.index() != b.length() && !a.equals(b))
              b.moveNext();
            i++;
          }
          if (ec.val != 0)
            c.append(ec);
        }

      }

    }
    if (a.length() != 0 && b.length() == 0) // a is not empty
      c = a;
    else if (b.length() != 0 && a.length() == 0){ // b is not emprty
      if (s == 0) // add
        c = b;
      else { // subtract
        b.moveFront();
        for (int k = 0; k < b.length(); k++) {
          eb = (Entry) b.get();
          c.append(new Entry(eb.col, 0 - eb.val)); // negative entry
          b.moveNext();
        }
      }
    }

    return c;
  }

}
