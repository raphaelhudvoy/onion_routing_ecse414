package shortestPath;

import graphManagement.Edge;
import graphManagement.Node;

import java.util.HashSet;
import java.util.LinkedList;

public class DepthFirstSearch {
	
	HashSet <Node> touched;
	public DepthFirstSearch (Node n){
		touched = new HashSet<Node>();
		touched.add(n);
		search(n);
		System.out.println("# Connected nodes: " + touched.size());
		for (Node n1 : touched) System.out.println(n1);
	}
	
	public void search (Node s){

		Edge [] deep;
		System.out.println("Searching from node: " + s.getNodeId());
		System.out.println("NUmber touched: " + touched.size());
		LinkedList<Edge> whatever = s.getEdges();
		deep = new Edge[whatever.size()];
		//System.out.println(s);
		System.out.println("remaining neighboor of node: " + s.getNodeId());
		
		for (int i = 0 ; i < deep.length ; i++){
			deep[i] = whatever.pop();
		}
			//if (!touched.contains(e.getDestination())){
			//	System.out.println(e.getDestinationID());				
			//}
		
		System.out.println();
		
		for (int i = 0 ; i < deep.length ; i++){
		//for (Edge e : whatever){
			//System.out.println(" Trying with edge : " + e.getDestinationID());
			if (! touched.contains(deep[i].getDestination())){
			//	System.out.println("Adding node: " + e.getDestinationID());
				touched.add(deep[i].getDestination());
				search(deep[i].getDestination());
			}
			/*else {
				if (deep[i].getDestination().getNodeId() == 9 ) {
					System.out.println("9 is already in the LISTT!!!!!");
					System.out.println ("9's id: " + deep[i].getDestination().getNodeId());
					touched.add(deep[i].getDestination());
					for (Node t : touched) System.out.println(t.getNodeId());
				}
			}*/
		//}
		}
		System.err.println("Checked all node: " + s.getNodeId() + " neighboors");
		
	}
}
