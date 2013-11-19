package shortestPath;

import graphManagement.Edge;
import graphManagement.Node;

import java.util.HashSet;
import java.util.LinkedList;

public class DijkstraGraph {

	HashSet<DijkstraNode> included;
	HashSet <Node> inclusionMap;
	
	public DijkstraGraph (Node source, LinkedList<Node> original){
		
		DijkstraNode temp;
		
		included = new HashSet<DijkstraNode>();
		inclusionMap = new HashSet <Node> ();
		
		System.out.println("#Nodes: " + original.size());
		
		//Add all nodes to the respective set
		for (Node a : original){			
			if (a == source) {
				temp = new DijkstraNode(a);
				temp.setDistanceFromSource(0);
				temp.setNumberNodesFromSource(0);
				included.add(temp);
				inclusionMap.add(a);
			}
		}
		execute();
	}
	
	private void execute (){
		while (addNearestNode()){}
//		for (DijkstraNode dn : included){
//			System.out.println("Distance to source: " + dn.getDistanceFromSource() + ", number of jumps: " + dn.getNumberNodesFromSource() +
//					", edges used: " + dn.edgesUsed() + ", edges still to be used: " + dn.edgesAvailable());
//		}
		for (Node n : inclusionMap){
			System.out.println(n);
		}
	}
	
	//Returns true if the nearest node was added, false if there were no other nodes
	private boolean addNearestNode (){
		
		DijkstraNode nearestNeighbour = null;
		double minDistance = Double.POSITIVE_INFINITY;
		Edge minEdge = null;
		DijkstraNode minSource = null;
		Node minDestination = null;
		LinkedList<Edge> edges;
		LinkedList<Edge> toBeRemoved = new LinkedList<Edge>();
		//Finds the minimum node/edge to add
		for (DijkstraNode n : included){
			edges = n.getPossibleNeighbours();
			for (Edge e : edges){
				if (! inclusionMap.contains(e.getDestination())) {
					//The distance is smaller then the current min distance
					if (e.getLength() + n.getDistanceFromSource() < minDistance){
						minDistance = e.getLength() + n.getDistanceFromSource();
						minDestination = e.getDestination();
						minSource = n;
						minEdge = e;
					}
				}
				else {
					toBeRemoved.add(e);
				}
			}
			for (Edge e : toBeRemoved){
				n.removeEdge(e);
			}
		}
		
		//Actually adds the node		
		if (minDistance != Double.POSITIVE_INFINITY){
			//Update the distance, and number of nodes
			nearestNeighbour = new DijkstraNode (minDestination);
			nearestNeighbour.setDistanceFromSource(minSource.getDistanceFromSource() + minEdge.getLength());
			nearestNeighbour.setNumberNodesFromSource(minSource.getNumberNodesFromSource() + 1);
			minSource.includeEdge(minEdge);
			
			included.add(nearestNeighbour);
			inclusionMap.add(minDestination);

		} else return false;
		return true;
	}
	
}
