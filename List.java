/**
Brittany Lei bclei
CMPS101: Programming Assignment 3
**/

public class List{
  private class Node{
    // Fields
    Object data;
    Node next;
    Node prev;

    // Constructor
    Node(Object data) { this.data = data; next = null; }

    // toString():  overrides Object's toString() method
    public String toString() {
      return String.valueOf(data);
    }

    // equals(): overrides Object's equals() method
    public boolean equals(Object x){
      boolean eq = false;
      Node that;
      if(x instanceof Node){
        that = (Node) x;
        eq = (this.data==that.data);
      }
      return eq;
    }
  }

  // Fields
  private Node front;
  private Node back;
  private Node cursor;
  private int length;
  private int index;

  // Constructor
  // Creates a new empty list.
  List() {
    front = back = cursor = null;
    length = 0;
    index = 0;
  }

  // Access functions
  // Returns the number of elements in this List.
  int length() {
    return length;
  }

  // If cursor is defined, returns the index of the cursor element,
   // otherwise returns -1.
  int index() {
    if (cursor != null) {
      return index;
    }

    return -1;
  }

  // Returns front element. Pre: length()>0
  Object front()
  {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: front() called on empty List");
    }

    return front.data;
  }

  // Returns back element. Pre: length()>0
  Object back()
  {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: back() called on empty List");
    }

    return back.data;
  }

  // Returns cursor element. Pre: length()>0, index()>=0
  Object get()
  {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: get() called on empty List");
    }
    if (index() < 0) {
      throw new RuntimeException(
        "List Error: get() called on nonexistent cursor");
    }

    return cursor.data;
  }

  // Returns true if and only if this List and L are the same
   // object sequence. The states of the cursors in the two Lists
   // are not used in determining equality.
  public boolean equals(Object x) {

    boolean eql = true;
    List that;
    if (x instanceof List){
      that = (List) x;
      if (this.length() != that.length()) {
        eql = false;
      }
      else if (this.length() == that.length() && this.length() == 0) {
        return true;
      }
      else {
        Node A = this.front;
        Node B = that.front;
        while (eql && A != null) { // exit loop when one comparison not equal or end
          eql = A.data.equals(B.data);
          A = A.next;
          B = B.next;
        }
      }
    }

    return eql;
  }

  // Manipulation procedures
  // Resets this List to its original empty state.
  void clear() {
    front = back = cursor = null;
    length = 0;
    index = -1;
  }

  // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
  void moveFront() {
    if (length != 0) {
      cursor = front;
      index = 0;
    }
  }

  // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
  void moveBack() {
    if (length != 0) {
      cursor = back;
      index = length-1;
    }
  }

  // If cursor is defined and not at front, moves cursor one step toward
   // front of this List, if cursor is defined and at front, cursor becomes
  // undefined, if cursor is undefined does nothing.
  void movePrev() {
    if (cursor != null && index > -1) {
      cursor = cursor.prev; // if cursor at front, prev is undefined
      index--;
    }
  }

  // If cursor is defined and not at back, moves cursor one step toward
   // back of this List, if cursor is defined and at back, cursor becomes
   // undefined, if cursor is undefined does nothing.
  void moveNext() {
    if (cursor != null && index != length-1) {
      cursor = cursor.next;
      index++;
    }
    else if (index == length-1) { // cursor at back
      cursor = cursor.next;
      index = -1;
    }
  }

  // Insert new element into this List. If List is non-empty,
   // insertion takes place before front element.
  void prepend(Object data) {
    Node N = new Node(data);
    if (length != 0) {
      N.next = front;
      front.prev = N;
      front = N;
      if (index != -1) {
        index++;    // if cursor defined, index increases by one b/c extra element
      }
    }
    else { // list empty
      front = back = N;
    }

    length++;

  }

  // Insert new element into this List. If List is non-empty,
   // insertion takes place after back element.
  void append(Object data) {
    Node N = new Node(data);
    if (length !=0) {
      back.next = N;
      N.prev = back;
      back = N;
    }
    else { // list empty
      front = back = N;
    }

    length++;
  }

  // Insert new element before cursor.
   // Pre: length()>0, index()>=0
  void insertBefore(Object data) {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: insertBefore() called on empty List");
    }
    if (index < 0) {
      throw new RuntimeException(
        "List Error: insertBefore() called on nonexistent cursor");
    }

    Node N = new Node(data);
    if (index != 0){
      N.prev = cursor.prev;
      cursor.prev.next = N; // element before cursor <-> N
    }
    else {  // cursor at front
      front = N;
    }

    N.next = cursor;
    cursor.prev = N; // N <-> cursor

    index++; // added element before cursor, so index increases by 1
    length++;
  }

  // Inserts new element after cursor.
   // Pre: length()>0, index()>=0
  void insertAfter(Object data) {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: insertAfter() called on empty List");
    }
    if (index < 0) {
      throw new RuntimeException(
        "List Error: insertAfter() called on nonexistent cursor");
    }

    Node N = new Node(data);
    if (index != length()-1) {
      cursor.next.prev = N;
      N.next = cursor.next; // N <-> element after cursor
    }
    else { // cursor at back
      back = N;
    }

    cursor.next = N;
    N.prev = cursor; // cursor <-> N

    length++;
  }

  // Deletes the front element. Pre: length()>0
  void deleteFront() {
    if (length <= 0) {
      throw new RuntimeException(
        "List Error: deleteFront() called on empty List");
    }

    front = front.next;
    if (length == 1) {
      back = front; // = null;
      cursor = null;
    }

    length--;
    if (index != -1) { // cursor defined
      index--;
    }

  }

  // Deletes the back element. Pre: length()>0
  void deleteBack() {
    if (length <= 0) {
      throw new RuntimeException(
        "List Error: deleteBack() called on empty List");
    }

    if (length > 1) {
      back = back.prev;
      back.next = null;
    }
    else { // length == 1
      back = front = cursor = null;
    }

    if (index == length-1) { // cursor at back, goes off List
      index = -1;
    }

    length--;
  }


  // Deletes cursor element, making cursor undefined.
   // Pre: length()>0, index()>=0
  void delete() {
    if (length() <= 0) {
      throw new RuntimeException(
        "List Error: delete() called on empty List");
    }
    if (index < 0) {
      throw new RuntimeException(
        "List Error: delete() called on nonexistent cursor");
    }

    if (index != 0 && index != length-1) {
      cursor.next.prev = cursor.prev;
      cursor.prev.next = cursor.next;

    }
    else if (index == 0) { // cursor at front
      front = cursor.next;
    }
    else { // cursor at back
      back = cursor.prev;
      back.next = null;

    }

    cursor = null;
    index = -1;
    length--;
  }

  // Other methods
  // Overrides Object's toString method. Returns a String
   // representation of this List consisting of a space
  // separated sequence of Objects, with front on left.
  public String toString() {
    StringBuffer sb = new StringBuffer();
    Node N = front;

    while(N!=null) {
      sb.append(N.toString());
      if (N.next != null)
        sb.append(" ");
      N = N.next;
    }

    return new String(sb);
  }
}
