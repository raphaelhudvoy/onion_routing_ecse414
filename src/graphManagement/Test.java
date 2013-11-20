package graphManagement;

import java.util.LinkedList;

import shortestPath.DepthFirstSearch;
import shortestPath.DijkstraGraph;
import shortestPath.DijkstraNode;

public class Test {

	public static void main (String [] args){
		GraphOverlord gl = new GraphOverlord (System.getProperty("user.dir") + "/dat/trial5.brite");//+ "/../../dat/trial.brite");
		//gl.print();
		Node source = gl.getRandomRouter();
		LinkedList<Node> destination = gl.getAS(source);
		//Node source = gl.getNode(8);
		System.out.println ("Source chosen as: " + source);
		for (Node n : destination) System.out.println("Destination: " + n);
		System.out.println ("Starting...");
		DijkstraGraph dg = new DijkstraGraph (source, destination);
		//DepthFirstSearch ds = new DepthFirstSearch(source);
		
		LinkedList<DijkstraNode> results = dg.returnDestinations();
		for (DijkstraNode r : results){
			System.out.println("Result for destination " + r.getNode().getNodeId() + ": " + r.getDistanceFromSource() + ", " + r.getNumberNodesFromSource());
		}
		System.out.println("done");
	}
	
}
