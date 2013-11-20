package shortestPath;

import graphManagement.Edge;
import graphManagement.Node;

import java.util.HashSet;
import java.util.LinkedList;

public class DijkstraGraph {

	HashSet<DijkstraNode> included;
	HashSet <Node> inclusionMap;
	
	Node source;
	LinkedList<Node> destination;
	
	DijkstraNode dSource;
	LinkedList<DijkstraNode> dDestination;
	
	//The source Node, the Destination Node
	//NOTE: This only perform Dijkstra's on nodes of the same type, e.g. if it is an AS node it only looks at other AS nodes
	public DijkstraGraph (Node source, Node destination){
			
		this.source = source;
		this.destination = new LinkedList<Node>();
		this.destination.add(destination);
		if (destinationsAreValid()){
			execute();
		}
	}
	
	public DijkstraGraph (Node source, LinkedList<Node> destination){
		this.source = source;
		this.destination = destination;
		if (destinationsAreValid()){
			execute();
		}
	}
	
	private void execute (){
		
		//Set up the inclusion tables
		included = new HashSet<DijkstraNode>();
		inclusionMap = new HashSet <Node> ();

		//Add the source
		dSource = new DijkstraNode(source);
		dSource.setDistanceFromSource(0);
		dSource.setNumberNodesFromSource(0);
		dDestination = new LinkedList<DijkstraNode>();
		
		//Add the source
		included.add(dSource);
		inclusionMap.add(source);
		
		//Keeps track of destinations reached
		DijkstraNode dn;
		do {
			dn = addNearestNode();
			if (dn != null && destination.contains(dn.getNode())){
				destination.remove(dn.getNode());
				dDestination.add(dn);
			}
		} while (dn != null && destination.size() >= 0);
	}
	
	//Returns the node last added, or null
	private DijkstraNode addNearestNode (){
		
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
				if ((!inclusionMap.contains(e.getDestination())) && shouldBeSearched(e.getDestination())) {
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
		} 
		return nearestNeighbour;	
	}
	
	//Helper method to determine if the node is in the same AS / is a border node when the source is also a border node 
	private boolean shouldBeSearched (Node n){
		return (n != null && (this.source.getASid () == n.getASid()) || (this.source.getType().equals("RT_BORDER") && n.getType().equals("RT_BORDER")));
	}
	
	//Checks a list of destinations against the source to make sure they can all be reached
	public boolean destinationsAreValid (){		
		for (Node n : destination){
			if (!shouldBeSearched(n)) return false;
		}
		return true;
	}
	
	//RETURNS A SINGLE DESTINATION AS A DIJKSTRA NODE (CONTAINING DISTANCE FROM SOURCE)
	//IF THERE ARE MULTIPLE DESTINATIONS SPECIFIED, RETURNS THE CLOSEST TO SOURCE
	public DijkstraNode returnDestination (){
		DijkstraNode min = dDestination.peek();
		for (DijkstraNode dn : dDestination){
			if (dn.getDistanceFromSource() < min.getDistanceFromSource()){
				min = dn;
			}
		}
		return min;
	}
	
	//RETURNS ALL THE DESTINATIONS REACHED AS A LINKED LIST
	public LinkedList<DijkstraNode> returnDestinations (){
		return dDestination;
	}
}
