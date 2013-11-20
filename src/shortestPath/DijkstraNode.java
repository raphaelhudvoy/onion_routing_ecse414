package shortestPath;

import java.util.LinkedList;

import graphManagement.Edge;
import graphManagement.Node;

public class DijkstraNode {

	double distanceFromSource;
	int numNodesFromSource;
	LinkedList<Edge> possibleEdges;
	LinkedList<Edge> includedEdges;
	
	Node node;
	
	public DijkstraNode (Node n){
		LinkedList<Edge> temp;
		distanceFromSource = Double.MAX_VALUE;
		numNodesFromSource = -1;
		node = n;
		
		temp = n.getEdges();
		possibleEdges = new LinkedList<Edge>();
		includedEdges = new LinkedList<Edge>();
	
		for (Edge e : temp){
			possibleEdges.add(e);
		}
	}
	
	public Node getNode(){
		return node;
	}
	
	public int edgesUsed (){
		return includedEdges.size();
	}
	
	public int edgesAvailable(){
		return possibleEdges.size();
	}
	
	public LinkedList<Edge> getPossibleNeighbours (){
		return possibleEdges;
	}
	
	public void setDistanceFromSource (double d){
		distanceFromSource = d;
	}
	
	public double getDistanceFromSource (){
		return distanceFromSource;
	}

	public void setNumberNodesFromSource (int i){
		numNodesFromSource = i;
	}
	
	public int getNumberNodesFromSource (){
		return numNodesFromSource;
	}
	
	public void includeEdge (Edge e){
		includedEdges.add(e);
		possibleEdges.remove(e);
	}
	
	public void removeEdge (Edge e){
		possibleEdges.remove(e);
	}
}
