/**
 * This class is the Min_Priority_Queue class, which is a subclass of Heap class.
 * Its fields contain hashMap, GraphNode array, queueSize and size.
 * It contains method such as heapifyUp and heapifyUp opeartions, deleteMin and insert methods.
 * Known Bugs: None 
 * 
 * @author Linfeng Zhu
 * <linfengzhu@brandeis.edu>
 * <12/8/2022>
 * COSI 21A PA2
 */

package main;

public class Min_Priority_Queue extends Heap{
	
	/**
	 * Constructs that inherits the heap class and constructs a priority queue of size 20
	 */
	public Min_Priority_Queue() {
		super(20);
	}
	
	/**
	 * This method will insert a graphNode object into the priority queue
	 * @param g GraphNode that is inserted 
	 * @throws Exception Failure to add the GraphNode
	 */
	public void insert(GraphNode g) throws Exception {
		super.insert(g);
	}
	
	/**
	 * This method will pull the GraphNode with highest priority
	 * @return the GraphNode deleted
	 * @throws Exception When heap is empty
	 */
	public GraphNode pullHighestPriorityElement() throws Exception {
		return super.deleteMin();
	}
	
	/**
	 * This method will rebalance the priority queue
	 * @param g the graphNode needs to heapify up
	 * @throws Exception is thrown if the node g is not in the hash map
	 */
	public void rebalance(GraphNode g) throws Exception {
		super.heapifyUp(g);
	}
	
	/**
	 * This method will check whether the priority queue is empty
	 * @return boolean, true if the priority queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}

}
