package chapter_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Question 2.1: Write code to remove duplicates from an unsorted linked list.
 * 
 * FOLLOW UP
 * 
 * How would you solve this problem if a temporary buffer is not allowed?
 * 
 * @author Sudharsanan Muralidharan
 */
public class RemoveDups {

  /**
   * Remove duplicates from the list using a set
   * 
   * Complexity: O(n), Space: O(n)
   * 
   * @param list
   */
  private void removeDuplicatesSet(CustomLinkedList<Integer> list) {
    Node<Integer> current = list.head();
    Set<Integer> set = new HashSet<Integer>();

    while (current.next != null) {
      if (set.add(current.next.data)) {
        current = current.next;
      } else {
        current.next = current.next.next;
      }
    }
  }

  /**
   * Use two pointers to check and remove duplicates from the list.
   * 
   * Complexity: O(n2), Space: O(1)
   * 
   * @param list
   */
  private void removeDuplicates(CustomLinkedList<Integer> list) {
    Node<Integer> current = list.head();
    while (current != null) {
      Node<Integer> second = current;
      while (second.next != null) {
        if (current.data.equals(second.next.data)) {
          second.next = second.next.next;
        } else {
          second = second.next;
        }
      }
      current = current.next;
    }
  }

  public static void main(String[] args) throws FileNotFoundException {
    FileReader fileReader = new FileReader(new File("input_files/chapter_2/remove_dups"));
    Scanner scanner = new Scanner(fileReader);
    RemoveDups removeDups = new RemoveDups();
    String input;
    String[] elements;

    while (scanner.hasNextLine()) {
      input = scanner.nextLine();
      elements = input.split(":");
      CustomLinkedList<Integer> list = new CustomLinkedList<Integer>();
      for (String e : elements) {
        list.add(Integer.parseInt(e));
      }

      System.out.println("Original Linked List:");
      list.print();

      removeDups.removeDuplicates(list);
      System.out.println("After Removing Duplicates: ");
      list.print();

      removeDups.removeDuplicatesSet(list);
      System.out.println("After Removing Duplicates using Set:");
      list.print();

      System.out.println();
    }

    scanner.close();
  }
}