/**
 * This is the main class which will implement findMin method and print the output in a file
 * Known Bugs: None 
 * 
 * @author Linfeng Zhu
 * <linfengzhu@brandeis.edu>
 * <12/8/2022>
 * COSI 21A PA2
 */

package main;
import java.io.*;

public class FindMinPath {

	/**
	 * This is the main method run the class
	 * @param args is the algorithm run the class
	 * @throws Exception is thrown if a new file cannot be created
	 */
	 public static void main(String[] args) throws Exception{
		 //Creates a new MinPriorityQueue Q
		 Min_Priority_Queue Q=new Min_Priority_Queue();
		 //Gets the Home GraphNode
		 GraphWrapper gw=new GraphWrapper(false);
		 GraphNode home=gw.getHome();
		 home.priority=0;
		 //inserts home node into the Q
		 Q.insert(home);
		 //runs the implementation of Dijikstra's algorithm
		 GraphNode node=findMin(Q);
		 File ans = new File ("answer.txt");
		 PrintStream answer=new PrintStream(ans);
		 String result="";
		 //Add the result together
		 while(node!=null&&!node.getId().equals(home.getId())) {
			 result=node.previousDirection+"\n"+result;
			 node=node.previousNode;
		 }
		 answer.println(result);
		 
	 }
	 
	 /**
	  * This is the main method to run the find min path
	  * @param Q this is the priority queue containing all path information
	  * @return the node of the terminus
	  * @throws Exception Exception will be thrown if a node is failed to add into the hashMap
	  */
	 public static GraphNode findMin(Min_Priority_Queue Q) throws Exception{
		 while(!Q.isEmpty()) {
			 // pull the Node with highest priority
			 GraphNode curr=Q.pullHighestPriorityElement();
			 if(curr.isGoalNode()) {
				 return curr;
			 }else {
				 
				 //If the node has an east Neighbor
				 if(curr.hasEast()) {
					 int x=curr.priority+curr.getEastWeight();
					 GraphNode east=curr.getEast();
					 if(!Q.isInHeap(east)&&east.priority==0) {
						 east.priority=x;
						 east.previousNode=curr;
						 east.previousDirection="East";
						 Q.insert(east);
					 }else if(Q.isInHeap(east)){
						 if(east.priority>x) {
							 east.priority=x;
							 Q.rebalance(east);
							 east.previousNode=curr;
							 east.previousDirection="East";
						 }
					 }
				 }
				 
				 //If the node has a west neighbor
				 if(curr.hasWest()) {
					 int x=curr.priority+curr.getWestWeight();
					 GraphNode west=curr.getWest();
					 if(!Q.isInHeap(west)&&west.priority==0) {
						 west.priority=x;
						 west.previousNode=curr;
						 west.previousDirection="West";
						 Q.insert(west);
					 }else if(Q.isInHeap(west)){
						 if(west.priority>x) {
							 west.priority=x;
							 Q.rebalance(west);
							 west.previousNode=curr;
							 west.previousDirection="West";
						 }
					 }
				 }
				 
				 //If the node has a south neighbor
				 if(curr.hasSouth()) {
					 int x=curr.priority+curr.getSouthWeight();
					 GraphNode south=curr.getSouth();
					 if(!Q.isInHeap(south)&&south.priority==0) {
						 south.priority=x;
						 south.previousNode=curr;
						 south.previousDirection="South";
						 Q.insert(south);
					 }else if(Q.isInHeap(south)){
						 if(south.priority>x) {
							 south.priority=x;
							 Q.rebalance(south);
							 south.previousNode=curr;
							 south.previousDirection="South";
						 }
					 }
				 }
				 
				 //if the node has north neighbor
				 if(curr.hasNorth()) {
					 int x=curr.priority+curr.getNorthWeight();
					 GraphNode north=curr.getNorth();
					 if(!Q.isInHeap(north)&&north.priority==0) {
						 north.priority=x;
						 north.previousNode=curr;
						 north.previousDirection="North";
						 Q.insert(north);
					 }else if(Q.isInHeap(north)){
						 if(north.priority>x) {
							 north.priority=x;
							 Q.rebalance(north);
							 north.previousNode=curr;
							 north.previousDirection="North";
						 }
					 }
				 }
			 }
			 curr.priority=-1;
		 }
		 return null;
	 }
}
