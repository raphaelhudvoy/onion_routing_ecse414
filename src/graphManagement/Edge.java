package graphManagement;

import java.util.*;
import java.io.*;

public class Edge {
	
	private int edgeId, from, to, ASfrom, ASto;
	private double length, delay, bandwidth;
	private String type;

	public Edge (int edgeId, int from, int to, double length, double delay, double bandwidth, int ASfrom, int ASto, String type) {
		this.edgeId = edgeId;
		this.from = from;
		this.to = to;
		this.length = length;
		this.delay = delay;
		this.bandwidth = bandwidth;
		this.ASfrom = ASfrom;
		this.ASto = ASto;
		this.type = type; 
	}

	public int getEdgeId (){
		return edgeId;
	}

	public int getFrom (){
		return from;
	}

	public int getTo () {
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
	
	public String toString (){
	  return "EDGE_ID: " + edgeId + " FROM: " + from + " TO: " + " LENGTH: " + length + " DELAY: " + delay +
	    " BANDWIDTH: " + bandwidth + " AS_FROM: " + ASfrom + " AS_TO: " + ASto + " TYPE: " + type; 
	}

}
