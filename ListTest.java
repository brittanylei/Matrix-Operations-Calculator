/**
Brittany Lei bclei
CMPS101: Programming Assignment 3
**/

public class ListTest{
  public static void main(String[] args) {
    List A = new List();
    List B = new List();

    for (int i = 0; i < 20; i++) {
      A.append(i);
      B.append(i);
    }

    // test toString()
    System.out.println("A: " + A);
    System.out.println("The length of A is " + A.length());
    System.out.println("B: " + B);
    System.out.println("The length of B is " + B.length());
    // System.out.println(A.get());

    // test move
    A.moveFront();
    for (int i = 0; i < 10; i++) {
      A.moveNext();
    }
    B.moveBack();
    for (int i = 0; i < 10; i++) {
      B.movePrev();
    }
    System.out.println();

    System.out.println("A equals B: " + A.equals(B));
    System.out.println();

    // test index()
    System.out.println("A - cursor: " + A.index() + " data: " + A.get());
    System.out.println("B - cursor: " + B.index() + " data: " + B.get());


    System.out.println("A["+A.index()+"] equals B["+B.index()+"]: " + A.get().equals(B.get()));
    System.out.println();
    A.movePrev();

    System.out.println("A - cursor: " + A.index() + " data: " + A.get());
    System.out.println("B - cursor: " + B.index() + " data: " + B.get());
    System.out.println("A["+A.index()+"] equals B["+B.index()+"]: " + A.get().equals(B.get()));
    System.out.println();

    // test prepend()
    A.prepend(5);
    B.prepend(18);

    A.append(5);
    B.append(18);
    // test insert
    A.insertAfter(18);
    B.insertBefore(18);
    System.out.println("A: " + A);
    System.out.println("B: " + B);
    System.out.println();

    // test delete
    A.deleteFront();
    A.deleteFront();
    B.deleteFront();
    B.deleteBack();
    System.out.println("A: " + A);
    System.out.println("B: " + B);

    System.out.println(A.index());
    for (int i = 0; i < 5; i++) {
      A.moveNext();
    }
    System.out.println(A.index());
    A.delete();
    System.out.println("A: " + A);
  }
}
