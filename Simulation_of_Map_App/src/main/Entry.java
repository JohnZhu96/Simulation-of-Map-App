/**
 * This class is the Entry class. It represents an Entry that stores keys and values together.
 * Known Bugs: None 
 * 
 * @author Linfeng Zhu
 * <linfengzhu@brandeis.edu>
 * <12/8/2022>
 * COSI 21A PA2
 */

package main;

public class Entry {
	private GraphNode key;
	private int value;
	
	/**
	 * Constructs an Entry object that stores key and value 
	 * @param key GraphNode that is stored
	 * @param value Value that is stored 
	 */
	public Entry(GraphNode key, int value) {
		this.key = key;
		this.value = value;
		
	}
	
	/**
	 * A getter that returns the key
	 * @return key
	 */
	public GraphNode getKey() {
		return this.key;
	}
	
	/**
	 * A getter that returns the value 
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * A setter that sets the value with a new valuye
	 * @param newValue
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}
	
	
}
