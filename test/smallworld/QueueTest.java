/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallworld;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salem
 */
public class QueueTest {
    
    public QueueTest() {
    }

    /**
     * Test of isEmpty method, of class Queue.
     */
    @Test
    public void testIsEmpty() {
        Queue instance = new Queue();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class Queue.
     */
    @Test
    public void testSize() {
        Queue instance = new Queue();
        String item = "Hello";
        instance.enqueue(item);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of length method, of class Queue.
     */
    @Test
    public void testLength() {
        Queue instance = new Queue();
        int expResult = 0;
        int result = instance.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of peek method, of class Queue.
     */
    @Test
    public void testPeek() {
        Queue instance = new Queue();
        String item = "First";
        instance.enqueue(item);
        String sitem = "Second";
        instance.enqueue(sitem);
        Object expResult = "First";
        Object result = instance.peek();
        assertEquals(expResult, result);
    }

    /**
     * Test of enqueue method, of class Queue.
     */
    @Test
    public void testEnqueue() {
        Object item = null;
        Queue instance = new Queue();
        instance.enqueue(item);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult,result);
    }

    /**
     * Test of dequeue method, of class Queue.
     */
    @Test
    public void testDequeue() {
        Queue instance = new Queue();
        String item = "First";
        instance.enqueue(item);
        String sitem = "Second";
        instance.enqueue(sitem);
        Object expResult = "First";
        Object result = instance.dequeue();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Queue.
     */
    @Test
    public void testToString() {
        Queue instance = new Queue();
        String item = "Hello";
        instance.enqueue(item);
        String sitem = "Goodbye";
        instance.enqueue(sitem);
        String expResult = "Hello Goodbye ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of iterator method, of class Queue.
//     */
//    @Test
//    public void testIterator() {
//        Queue instance = new Queue();
//        String item = "Awesome";
//        instance.enqueue(item);
//        String sitem = "Great";
//        instance.enqueue(sitem);
//        Iterator expResult = null;
//        Iterator result = instance.iterator();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of main method, of class Queue.
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Queue.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
