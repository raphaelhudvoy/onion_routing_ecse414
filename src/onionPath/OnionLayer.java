package onionPath;

import graphManagement.Node;

public class OnionLayer {
	
	private Node nextNode;
	private int prevSymKey, nextSymKey;
	private int timeout;

	public OnionLayer(Node nextNode, int prevSymKey, int nextSymKey, int timeout) {
		
		this.nextNode 		= nextNode;		
		this.prevSymKey 	= prevSymKey;
		this.nextSymKey		= nextSymKey;
		this.timeout 		= timeout; // in millisec
		
	}
	
	public int getPrevSymKey () {
		return prevSymKey;
	}
	
	public int getNextSymKey () {
		return nextSymKey;
	}
	
	public int getTimeout () {
		return timeout;
	}
	
	public Node getNextNode () {
		return nextNode;
	}
	
	@Override
	public String toString () {
		
		
		return "NextNode is " + ((nextNode == null) ? " last layer" : nextNode.getNodeId())  + " with prevKey of " + prevSymKey + " and nextKey is " + nextSymKey + " with a timeout of " + timeout + "ms\n";
		
	}
	
}
