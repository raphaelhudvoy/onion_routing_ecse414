package graphManagement;

import java.util.*;
import java.io.*;

public class Node {
	
	private int nodeId, xpos, ypos, inDegree, outDegree, ASid;
	private String type;
	private LinkedList<Edge> edges;

	public Node (int nodeId, int xpos, int ypos, int inDegree, int outDegree, int ASid, String type){
		this.nodeId = nodeId;
		this.xpos = xpos;
		this.ypos = ypos;
		this.inDegree = inDegree;
		this.outDegree = outDegree;
		this.ASid = ASid;
		this.type = type;

		this.edges = new LinkedList<Edge>();
	}

	public void addEdge (Edge e){
		edges.add(e);
	}

	public int getNodeId (){
		return nodeId;
	}

	public int getXpos (){
		return xpos;
	}

	public int getYpos (){
		return ypos;
	}

	public int getInDegree (){
		return inDegree;
	} 

	public int getOutDegree (){
		return outDegree;
	}

	public int getASid (){
		return ASid;
	}

	public String getType () {
		return type;
	} 

	public LinkedList<Edge> getEdges(){
		return edges;
	}	

	public String toString () {
		return "NODE_ID: " + nodeId + " XPOS: " + xpos + " YPOS: " + ypos + " IN_DEGREE: " + inDegree + 
		  " OUT_DEGREE: " + outDegree + "Num edges: " + edges.size() + " AS_ID: " + ASid + " TYPE: " + type;
	}
	
	public boolean equals (Object other){
		return this == other;
		//return this.getNodeId() == ((Node)other).getNodeId();
	}
	
	public int hasCode(){
		return nodeId;
	}
	
//	public int getDistance (){
//		return distance;
//	}
	
//	public void setDistance(int distance){
//		this.distance = distance;
//	}
	
}
