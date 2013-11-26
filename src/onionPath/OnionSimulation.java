package onionPath;

import shortestPath.DijkstraGraph;
import shortestPath.DijkstraNode;
import graphManagement.GraphOverlord;
import graphManagement.Node;

public class OnionSimulation {
	
	private Onion onion;
	
	public OnionSimulation(GraphOverlord internet, int numberOfProxies) {
		onion = new Onion(internet, numberOfProxies);
		
		//System.out.println(onion.toString());
	}
	
	private double computeCost(Node source, Node destination) {
		
		DijkstraGraph dg = new DijkstraGraph (source, destination);
		
		DijkstraNode results = dg.returnDestination();
		//System.out.println("Result for destination " + results.getNode().getNodeId() + ": " + results.getDistanceFromSource() + ", " + results.getNumberNodesFromSource());
		
		return results.getDistanceFromSource();
	}
	
	public double [] start() {
		
		//System.out.println("Going from node: " + onion.getSource().getNodeId() + " to node " + onion.getDestination().getNodeId());
		//System.out.println("With Dijkstra the cost is : " + computeCost(onion.getSource(), onion.getDestination()));
		
		double shortestPathCost = computeCost(onion.getSource(), onion.getDestination());
		double onionCost = 0;
		
		Node source, destination;
		source = onion.getSource();
		destination = onion.getNextLayer().getNextNode();
		
		do {
				
			onionCost += computeCost(source, destination);
			
			source = destination;
			destination = onion.getNextLayer().getNextNode();
			
		} while (destination != null);
		
		// Compute cost from last proxy to destination
		onionCost += computeCost(source, onion.getDestination());
		
		//System.out.println("Simulation done. The total cost is : " + onionCost);
		
		double[] results = {shortestPathCost, onionCost};
		
		return results;
	}

}
