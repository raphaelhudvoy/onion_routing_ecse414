package graphManagement;

public class Edge {
	
	private int edgeId, from, to, ASfrom, ASto;
	private double length, delay, bandwidth;
	private String type;
	private Node source, destination;

	public Edge (int edgeId, int from, int to, double length, double delay, double bandwidth, int ASfrom, int ASto, String type, Node source, Node destination) {
		this.edgeId = edgeId;
		this.from = from;
		this.to = to;
		this.length = length;
		this.delay = delay;
		this.bandwidth = bandwidth;
		this.ASfrom = ASfrom;
		this.ASto = ASto;
		this.type = type;
		this.source = source;
		this.destination = destination;
	}

	public int getEdgeId (){
		return edgeId;
	}

	public int getSourceID (){
		return from;
	}

	public int getDestinationID () {
		return to;
	}

	public double getDelay () {
		return delay;
	}

	public double getLength (){
		return length;
	}

	public double getBandwidth (){
		return bandwidth;
	}

	public int getASfrom (){
		return ASfrom;
	}

	public int getASto () {
		return ASto;
	}

	public String getType (){
		return type;
	}
	
	public Node getSource (){
		return source;
	}
	
	public Node getDestination(){
		return destination;
	}
	
	public String toString (){
	  return "EDGE_ID: " + edgeId + " FROM: " + from + " TO: " + to + " LENGTH: " + length + " DELAY: " + delay +
	    " BANDWIDTH: " + bandwidth + " AS_FROM: " + ASfrom + " AS_TO: " + ASto + " TYPE: " + type; 
	}
	
	public boolean equals (Object other){
		return this == other;
		//return this.getDestinationID() == ((Edge)other).getDestinationID();
	}
	
	public int hashCode (){
		return edgeId;
	}

}
