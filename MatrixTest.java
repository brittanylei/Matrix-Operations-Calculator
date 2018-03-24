/**
Brittany Lei bclei
CMPS101: Programming Assignment 3
**/

public class MatrixTest{
  public static void main(String[] args) {
    Matrix A = new Matrix(4);
    Matrix B = new Matrix(4);

    // test toString() and getSize()
    System.out.println("A " + A.getSize() + "\n" + A);
    System.out.println("B " + A.getSize() + "\n" + B);

    // A.changeEntry(1, 3, 3.4);
    // System.out.println(A.getSize());
    // System.out.println(A.getNNZ());
    // System.out.println();
    // System.out.println(A);
    // A.changeEntry(1, 2, 0);
    // System.out.println(A);
    // A.changeEntry(3, 4, 12.1);
    // System.out.println(A);
    // A.changeEntry(2, 2, 23);
    // System.out.println(A);

    // test changeEntry()
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 4; j++) {
        A.changeEntry(i, j, i+j);
        // System.out.println(A);
      }
    }
    System.out.println("A" + "\n" + A);

    // test copy()
    B = A.copy();
    System.out.println("B = A" + "\n" + B);
    System.out.println("B == A" + "\n" + B.equals(A));

    // A = new Matrix(10);
    // B = new Matrix(15);
    // A.changeEntry(1, 1, 1);
    // B.changeEntry(1, 1, 1);
    // System.out.println("B == A" + "\n" + B.equals(A));
    // B = new Matrix(10);
    // A.changeEntry(1, 1, 1);
    // A.changeEntry(1, 3, 1);
    // B.changeEntry(1, 1, 1);
    // B.changeEntry(1, 3, 1);
    // System.out.println("A" + "\n" + A);
    // System.out.println("B" + "\n" + B);
    // System.out.println("B == A" + "\n" + B.equals(A));

    // test scalarMult()
    B = B.scalarMult(2);
    System.out.println("B = 2B" + "\n" + B);

    System.out.println();
    // test transpose()
    B = A.transpose();
    System.out.println("A" + "\n" + A);
    System.out.println("B = T(A)" + "\n" + B);

    Matrix C = new Matrix(4);
    C.changeEntry(1, 1, 1);
    // C.changeEntry(1, 3, 2);
    C.changeEntry(2, 2, 3);
    C.changeEntry(3, 2, 4);
    C.changeEntry(3, 4, 5);
    C.changeEntry(4, 1, 6);
    C.changeEntry(4, 2, 7);
    C.changeEntry(4, 4, 8);
    System.out.println("C" + "\n" + C);
    C = C.transpose();
    System.out.println("T(C)" + "\n" + C);

    System.out.println();
    B = C.transpose();
    System.out.println("B = T(C)" + "\n" + B);

    // test mult()
    Matrix D = A.mult(C);
    System.out.println("D = AC" + "\n" + D);

    // test add() and sub()
    Matrix E = D.add(C);
    System.out.println("E = D + C" + "\n" + E);

    E = E.sub(C);
    System.out.println("E = D - C" + "\n" + E);

    // test getNNZ() and makeZero()
    System.out.println(A.getNNZ());
    A.makeZero();
    System.out.println("A: " + A + A.getNNZ());

  }
}
