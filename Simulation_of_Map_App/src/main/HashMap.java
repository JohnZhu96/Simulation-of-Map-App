/**
 * This class is the HashMap class. It contains fields such as size, number of elements stored, load factor, and 
 * an array with Enrty objects. It includes hashFunction method, set and add methods, and get method.
 * Known Bugs: None 
 * 
 * @author Linfeng Zhu
 * <linfengzhu@brandeis.edu>
 * <12/8/2022>
 * COSI 21A PA2
 */

package main;

public class HashMap {
	private int size =15;
	private double numOfElement;
	private double loadFactor;
	
	private Entry[] hashArray;

	
	/**
	 * Constructs a HashMap
	 */
	public HashMap() {
		hashArray=new Entry[size];
	}
	
	/**
	 * This method represents a Hash Function that uses double hashing
	 * @param id String that represents UUID
	 * @param a Represents the time to use hash function 
	 * @return integer that represents the hash value
	 */
	public int hashFunction(String id,int a) {
		int output=0;
		for(int i=0;i<=7;i++) {
			char temp=id.charAt(i);
			if(Character.isDigit(temp)) {
				output+=temp;
			}
		}
		output=(a*(a+ output%size)+(output%size))%size;
		return output;
	}
	
	/**
	 * This is the method to set an object into the HashMap
	 * @param key GraphNode to be set into the HashMap
	 * @param value The value being set with the node
	 * @throws Exception Unable to add into the HashMap
	 */
	public void set(GraphNode key,int value) throws Exception {
		if(!hasKey(key)) {
			add(key,value);
		}else {
			int count=0;
			int index=hashFunction(key.getId(),count);
			while(!hashArray[index].getKey().getId().equals(key.getId())) {
				count++;
				index=hashFunction(key.getId(),count);
			}
			hashArray[index].setValue(value);
		}
	}
	
	/**
	 * This method will rehash the the HashMap to a new HashMap twice in size
	 */
	public void rehash() {
		//the hash table will rehash if the loadfactor exceeds 0.5
		if(loadFactor>0.5) {
			this.size=size*2;
			loadFactor=numOfElement/size;
			Entry[] temp=new Entry[size];
			//rehash the elements into the new hash table
			for(int i=0;i<hashArray.length;i++) {
				if(hashArray[i]!=null) {
					int count=0;
					int index=hashFunction(hashArray[i].getKey().getId(),count);
					
					while(temp[index]!=null) {
						count++;
						index=hashFunction(hashArray[i].getKey().getId(),count);
					}
					temp[index]=hashArray[i];
				}
			}
			hashArray=temp;
		}
	}
	
	/**
	 * This method adds an object into the HashMap
	 * @param key GraphNode to be set into the HashMap
	 * @param value The value being set with the node
	 * @throws Exception Unable to add the GraphNode
	 */
	public void add(GraphNode key,int value) throws Exception {
		for(int i=0;i<=size;i++) {
			int index=hashFunction(key.getId(),i);
			//add the element in the hash table if the slot is not occupied
			if(hashArray[index]==null) {
				Entry temp=new Entry(key,value);
				hashArray[index]=temp;
				numOfElement++;
				this.loadFactor=numOfElement/size;
				//if the load factor exceeds 0.5, rehash the hash table
				rehash();
				return;
			}
		}
		throw new Exception("UNABLE TO ADD");
	}
	

	
	/**
	 * This method gets the value of a node
	 * @param g GraphNode needs to find value in the hash map
	 * @return 
	 * @throws Exception GraphNode not Found
	 */
	public int getValue(GraphNode g) throws Exception {
		if(hasKey(g)) {
			int count=0;
			int location=hashFunction(g.getId(),count);
			while(!hashArray[location].getKey().getId().equals(g.getId())) {
				count++;
				location=hashFunction(g.getId(),count);
			}
			return hashArray[location].getValue();
		}else {
			throw new Exception("KEY NOT FOUND");
		}
	}
	
	/**
	 * This method checks if the node is in the hash map
	 * @param g Target GraphNode
	 * @return boolean 
	 */
	public boolean hasKey(GraphNode g) {
		for(int i=0;i<numOfElement;i++) {
			int location=hashFunction(g.getId(),i);
			if(hashArray[location]==null) {
				return false;
			}else if(hashArray[location].getKey().getId().equals(g.getId())) {
				return true;
			}
		}
		return false;
	}
	

	
	/**
	 * This method gets the Entry array stored in the hash map
	 * @return 
	 */
	public Entry[] getArray() {
		return hashArray;
	}
}
