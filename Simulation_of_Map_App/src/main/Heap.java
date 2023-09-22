/**
 * This class is the Heap class. Its fields contain hashMap, GraphNode array, queueSize and size.
 * It contains method such as heapifyUp and heapifyUp opeartions, deleteMin and insert methods.
 * Known Bugs: None 
 * 
 * @author Linfeng Zhu
 * <linfengzhu@brandeis.edu>
 * <12/8/2022>
 * COSI 21A PA2
 */

package main;

public class Heap {
	private HashMap hashMap;
	private GraphNode[] nodes;
	private int queueSize =1;
	private int size;
	

	/**
	 * Constructs a Heap object that contains a GraphNode Array with size a and a HashMap object
	 * @param a An integer indicating the size of the GraphNode Array 
	 */
	public Heap(int a) {
		size=a;
		nodes = new GraphNode[size];
		hashMap = new HashMap();
	}
	
	/**
	 * This method will return the parent's index 
	 * @param index The index we need to find its parent
	 * @return The index of its parent
	 */
	public int getParent(int index) {
		return index/2;
	}
	
	/**
	 * This method will swap the node with its parentNode
	 * @param node The node we are swapping 
	 * @param index The index of the node
	 * @throws Exception node is not able to set into HashMap
	 */
	public void swap(GraphNode node, int index) throws Exception{
		int parent = getParent(index);
		GraphNode parentNode = nodes[parent];
		nodes[parent] = node;
		nodes[index] = parentNode;
		hashMap.set(node, parent);
		hashMap.set(parentNode,index);
	}
	
	/**
	 * This method implements heapify up operation 
	 * @param node The node that heapify up is operated on
	 * @throws Exception Node is not Found in the HashMap
	 */
	public void heapifyUp(GraphNode node) throws Exception{
		int index = hashMap.getValue(node);
		int parent = getParent(index);
		while (parent >0 && nodes[parent].priority > node.priority) {
			swap(node,index);
			index = parent;
		}
	}
	
	/**
	 * This method implements heapify down operation
	 * @param node The node that heapify down is operated on
	 * @throws Exception Node is not found in the HashMap
	 */
	public void heapifyDown(GraphNode node) throws Exception{
		int index =hashMap.getValue(node);
		GraphNode child = node;
		//left child
		if(2*index < queueSize && nodes[2*index].priority < child.priority) {
			child=nodes[2*index];
		}
		//right child
		if(2*index+1 < queueSize && nodes[2*index+1].priority<child.priority) {
			child=nodes[2*index+1];
		}
		if(!child.getId().equals(node.getId())) {
			//swap the node and one of its children
			int index1=hashMap.getValue(node);
			int index2=hashMap.getValue(child);
			GraphNode temp=nodes[index1];
			nodes[index1]=nodes[index2];
			nodes[index2]=temp;
			hashMap.set(node,index2);
			hashMap.set(child,index1);
			heapifyDown(node);
		}
	}

	/**
	 * This method deletes the minimum node in the Heap
	 * @return The node with minimum value that is deleted
	 * @throws Exception When Queue is empty
	 */
	public GraphNode deleteMin() throws Exception{
		if(queueSize>=1) {
			GraphNode temp=nodes[1];
			queueSize--;
			nodes[1]=nodes[queueSize];
			hashMap.set(nodes[1], 1);
			heapifyDown(nodes[1]);
			return temp;
		}else {
			throw new Exception("Queue is Empty");
		}
	}
	
	/**
	 * This method doubles the size of the Heap array
	 */
	public void updateSize() {
		size = size*2;
		GraphNode[] newHeap = new GraphNode[size];
		//Copy every elements in the old array to the array with new size
		for(int i=0; i< nodes.length;i++) {
			newHeap[i] = nodes[i];
		}
		nodes = newHeap;
	}
	
	/**
	 * This method will insert a node into the Heap array
	 * @param node The GraphNode object that is inserted
	 * @throws Exception When node is unable to be set into the HashMap
	 */
	public void insert(GraphNode node) throws Exception{
		if(queueSize+1==size) {
			updateSize();
		}
		nodes[queueSize]=node;
		hashMap.set(node,queueSize);
		queueSize++;
		heapifyUp(node);
	}
	
	/**
	 * This is a method to check if the heap is empty
	 * @return boolean
	 */
	public boolean isEmpty() {
		return queueSize <1;
	}
	
	/**
	 * This is a method to check if a graphNode is in heap
	 * @param g the graphNode needs to be checked
	 * @return boolean, true if the graphNode is in the heap, false otherwise
	 */
	public boolean isInHeap(GraphNode node) {
		for(int i=1;i<queueSize;i++) {
			if(nodes[i].getId().equals(node.getId())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This is a toString method
	 * @return a String that represent the heap
	 */
	public String toString() {
		String output="[";
		for(int i=1;i<queueSize;i++) {
			output+=nodes[i].priority+" ";
		}
		output+="]";
		return output;
	}
}
