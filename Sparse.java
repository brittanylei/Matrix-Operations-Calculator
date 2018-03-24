/**
Brittany Lei bclei
CMPS101: Programming Assignment 3
**/

import java.io.*;
import java.util.Scanner;

public class Sparse {
  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: Sparse <input file> <output file>");
      System.exit(1);
    }

    Scanner in = new Scanner(new File(args[0]));
    PrintWriter out = new PrintWriter(new FileWriter(args[1]));


    int n = in.nextInt(); // size of matrix nxn
    int a = in.nextInt(); // # of nnz entries for A
    int b = in.nextInt(); // # of nnz entries for B

    in.nextLine();

    Matrix A = new Matrix(n);
    Matrix B = new Matrix(n);

    // build arrays
    for (int i = 0; i < a; i++) {
      A.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
    }

    in.nextLine();

    for (int i = 0; i < b; i++) {
      B.changeEntry(in.nextInt(), in.nextInt(), in.nextDouble());
    }

    // print
    // A
    out.println("A has " + A.getNNZ() + " non-zero entries:");
    out.println(A);

    // B
    out.println("B has " + B.getNNZ() + " non-zero entries:");
    out.println(B);

    // (1.5)A
    out.println("(1.5)*A =");
    out.println(A.scalarMult(1.5));

    // A + B
    out.println("A+B =");
    out.println(A.add(B));

    // A + A
    out.println("A+A =");
    out.println(A.add(A));

    // B - A
    out.println("B-A =");
    out.println(B.sub(A));

    // A - A
    out.println("A-A =");
    out.println(A.sub(A));

    // A^T
    out.println("Transpose (A) =");
    out.println(A.transpose());
    // AB
    out.println("A*B =");
    out.println(A.mult(B));

    // B^2
    out.println("B*B =");
    out.println(B.mult(B));
    // close files
    in.close();
    out.close();
  }
}
