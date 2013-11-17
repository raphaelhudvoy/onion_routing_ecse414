package shortestPath;

import graphManagement.Node;

import java.util.LinkedList;

public class DijkstraGraph {

	LinkedList<DijkstraNode> allNodes;
	
	public DijkstraGraph (Node source, LinkedList<Node> original){
		
		int i = 0;
		allNodes = new LinkedList<DijkstraNode>();
		for (Node a : original){
			
			if (a == source) allNodes.addFirst(new DijkstraNode(a));
			else allNodes.addLast(new DijkstraNode(a));
			
			i++;
		}
		
	}
	
}
