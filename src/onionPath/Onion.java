package onionPath;

import java.util.Stack;
import graphManagement.GraphOverlord;
import graphManagement.Node;

public class Onion {
	
	private Stack<OnionLayer> layers;
	private Node source, destination;
	private final int TIMEOUT = 10;
	
	public Onion (GraphOverlord internet, int numberOfProxies) {
		
		Node prevNode;
		
		layers 		= new Stack<OnionLayer>();
		destination	= internet.getRandomRouter();		
		prevNode = destination;
		
		if (numberOfProxies > 0) {
			
			int key =  (int )(Math.random() * 10000 + 1); //	Random number between 1 and 10,000
			
			OnionLayer newLayer = new OnionLayer(null, key, 0, TIMEOUT); // This is the last layer so there is no next node
			layers.push(newLayer);
			
			for (int i = 1; i < numberOfProxies; i++ ) { //	Starts at 1
				
				// Generation the symmetric key for the previous node
				int prevKey =  (int )(Math.random() * 10000 + 1); //	Random number between 1 and 10,000
				
				
				//	Adding the layer to the onion
				Node nextNode = findNextNode(internet, prevNode); 
				layers.push(new OnionLayer(nextNode, prevKey, key, TIMEOUT));
				prevNode = nextNode;
				
				//prevKey become nextkey
				key = prevKey;
			}
			
			source = findNextNode(internet, prevNode);
			// Making sure destination is not the same as the source
			while (source == destination) {
				source = findNextNode(internet, prevNode);
			}
		}
	}
	
	private Node findNextNode (GraphOverlord internet, Node prevNode) {
		
		Node nextNode = internet.getRandomRouter();
		
		while(nextNode == prevNode) {
			nextNode = internet.getRandomRouter();
		}
		
		return nextNode;
	}
	
	public Node getSource () {
		return source;
	}
	
	public Node getDestination () {
		return destination;
	}
	
	public OnionLayer getNextLayer() {
		return layers.pop();
	}
	
	@Override
	public String toString () {
		
		return " Source node is " + source + " \n Destination node is " + destination + "\n Layers are \n" + layers.toString();
		
	}

}
