package intlist;

import java.util.Arrays;
import java.util.stream.IntStream;



public class IntList {
	private static class Node{
		/**
		 * @invar | next != null
		 * @invar | next.previous == this
		 * @invar | previous.next == this
		 * @invar | previous != null
		 * @peerObject
		 */
		private Node previous;
		private int value;
		/**
		 * @invar | next != null
		 * @peerObject hiermee zijn deze automatisch ook representation objects
		 */
		private Node next;
		
		private int getLengthTo(Node node) {
			if(this == node) return 0;
			else return 1 + next.getLengthTo(node);
		}
	}
	
	/**
	 *@invar | sentinel != null
	 *@representationObject
	 *@invar | sentinel.next.getLengthTo(sentinel)==length
	 */
	private Node sentinel;
	private int length;
	//resultaat is onafhankelijk van het intlist object:
	
	/**
	 * @creates|result
	 * @basic
	 */
	public int[] getElements() {
		int[] result = new int[length];
		Node node = sentinel.next;
		for(int  i = 0; i < length ; i++) {
			result[i] = node.value;
			node = node.next;
		}
		return result;
	}
	/**
	 * @post |result == getElements().length
	 */
	public int getLength() {
		return length;
	}
	
	private Node getNodeAt(int  index) {
		if (index <= length /2) {
		Node node = sentinel.next;
		while(0 < index) {
			node = node.next;
			index --;
		}
		return node;
	}
		else {
			Node node = sentinel.previous;
			while(index < length) {
				node = node.previous;
				index++;
				
			}
			return node;
		}	
	}
	/**
	 * @pre|0 <=index && index<getLength()
	 * @post |result == getElements()[index]
	 */
	public int getElementAt(int index) {
		return getNodeAt(index).value;
	}
	/**
	 * @post| this.getLength()==0
	 */
	
	public IntList() {
		sentinel = new Node();
		sentinel.next = sentinel ;
		sentinel.previous = sentinel;
		
	}
	
	/**
	 * @post| getLength() == old(getLength()) + 1
	 * @post| getElementAt(old(getLength())) == value
	 * @post| IntStream.range(0,old(getLength())).allMatch(i->
	 * 		| getElementAt(i) == old(getElements())[i])
	 * @post| Arrays.equals(getElements(),0,getLength()-1,old(getElements()),0,old(getLength()))
	 * 
	 * // IntStream.range(a,b).alMatch(i-> uitspraak over i)
	 * // voor alle i , a <=i < b ==> uitspraak over i
	 */
	public void addElement(int value) {
		Node newNode = new Node();
		newNode.value = value;
		newNode.next = sentinel;
		newNode.previous = sentinel.previous;
		sentinel.previous = newNode;
		newNode.previous.next = newNode;
		length ++;
		
	}
	/**
	 *@mutates | this
	 *@pre | 0 <= index && index < getLength()
	 *@post | getLength() == old(getLength())
	 *@post | IntStream.range(0,getLength()).allMatch(i->
	 *		| 	i == index ?
	 *		|  		getElementAt(i) == value
	 *		|	:
	 *		| 	getElementAt(i) == old(getElements())[i])
	 */
	public void setElement(int index, int value) {
		getNodeAt(index).value = value;
	}
}