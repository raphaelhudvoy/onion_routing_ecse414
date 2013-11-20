package graphManagement;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphOverlord {
	
	private int totalNumNodes, totalNumEdges;
	private Node [] allNodes;
	private int offset;
	
	public GraphOverlord (String filePath){
		
		generateGraph(filePath);
		
	}
	
	/*
	  Pass this the brite file you wish to turn into a graph. It will construct the graph
	  by populating allNodes
	*/
	private boolean generateGraph (String filePath){

		String temp = "";
		String [] values;

		int index = 0;
		
		Edge edge = null;
		Edge edge1 = null, edge2 = null;
		
		Scanner sc = null;
		
		Pattern p = null;
		Matcher m = null;
	
		boolean matchFound = false;
			
		try {
			sc = new Scanner (new File(filePath));
			
			//advance the scanner until the entry line for the node descriptions
		
			p = Pattern.compile("Nodes: \\( \\d+ \\)");	
			while (sc.hasNext() && ! matchFound){
				temp = sc.nextLine();
				m = p.matcher(temp);
				if (m.matches()){
					matchFound = true;
				}
			}
			
			//Extract the number of Nodes
			if (matchFound) {
				p = Pattern.compile("\\d+");
				m = p.matcher(temp);
				m.find();
			
				this.totalNumNodes = Integer.parseInt(m.group());
			}
			else {
				System.out.println("The file is not correctly formatted");
				return false;
			}
			
			allNodes = new Node [this.totalNumNodes];

			//Iterate through each node, creating a graph node for it.
			while (sc.hasNext() && !(temp = sc.nextLine()).equals("")){
				values = temp.split("\t");	

				//Create and populate the nodes
				if (values.length == 7 && index < this.totalNumNodes){
					//The first node to be created doesn't necessarily had ID = 0
					if (index == 0) offset = Integer.parseInt(values[0]);
					allNodes [index] = new Node (Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6]);
				}
				else {
					System.err.println("Node value misisng");
					return false;
				}
				index ++;
			}
			
			//Now, same as above, iterate until you find the number of edges.		
			matchFound = false;
			p = Pattern.compile("Edges: \\( \\d+ \\)");
			while (sc.hasNext() && ! matchFound){
				temp = sc.nextLine();
				m = p.matcher(temp);
				if (m.matches()){
					matchFound = true;
				}
			}
			
			if (matchFound) {
				p = Pattern.compile("\\d+");
				m = p.matcher(temp);
				m.find();

				this.totalNumEdges = Integer.parseInt(m.group());
			}
			else {
				System.out.println("The file is not correctly formatted");
				return false;
			}			

			while (sc.hasNext()){
				temp = sc.nextLine();
				values = temp.split("\t");
				
				if (values.length >= 9) {
					edge1 = new Edge (Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), 
							Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), 
							Integer.parseInt(values[6]), Integer.parseInt(values[7]), values[8], 
							getNode(Integer.parseInt(values[1])), getNode(Integer.parseInt(values[2])));					
					edge2 = new Edge (Integer.parseInt(values[0]), Integer.parseInt(values[2]), Integer.parseInt(values[1]), 
							Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), 
							Integer.parseInt(values[6]), Integer.parseInt(values[7]), values[8], 
							getNode(Integer.parseInt(values[2])), getNode(Integer.parseInt(values[1])));					

					allNodes[edge1.getSourceID() - offset].addEdge(edge1);
					allNodes[edge2.getSourceID() - offset].addEdge(edge2);					
				}
				else {
					System.err.println("Edge value is missing");
					return false;
				}

			}		
	
		} catch (Exception e){
			System.err.println("Invalid file name, exiting");
			System.err.println(e.getMessage());
			return false;
		}
		finally {
			if (sc != null) sc.close();
		}
		
		return true;
		
	}
	
	public Node getNode (int nodeId) {
	  return allNodes [nodeId - offset];
	}
	
	public Node getFirstNode () {
	  return allNodes[0];
	}
	
	//Retrieves all nodes belonging in the same AS as source
	public LinkedList<Node> getAS (Node source){	
		LinkedList<Node> as = new LinkedList<Node>();
		int AsId = source.getASid();
		
		for (int i = 0 ; i < allNodes.length ; i++){
			if (allNodes[i].getASid() == AsId){
				as.add(allNodes[i]);
			}
		}		
		return as;
	}
	
	//Retrieves all boundary nodes
	public LinkedList<Node> getBoundaryNodes (){
		LinkedList<Node> boundary = new LinkedList<Node>();
		for (int i = 0 ; i < allNodes.length ; i++){
			if (allNodes[i].getType().equals("RT_BORDER")){
				boundary.add(allNodes[i]);
			}
		}
		return boundary;
	}
	
	//Retrieves a router which is not a border
	public Node getRandomRouter (){
		double ran; 
		int nodeLocation; 
		do {
			ran = Math.random();
			nodeLocation = (int)Math.floor(ran*this.totalNumNodes);
		} while (allNodes[nodeLocation].getType().equals("RT_BORDER"));
		 
		return allNodes[nodeLocation]; 
	}
	
	public void print (){
		System.out.println("Printing the graph...");
		for (int i = 0 ; i < allNodes.length ; i ++){
			System.out.println(allNodes[i]);
		}
	}
	
	//Returns all border nodes for a given AS id
	public LinkedList<Node> getBorders(int ASid){
		
		LinkedList<Node> borders = new LinkedList<Node>();
		for (int i = 0 ; i < allNodes.length ; i++){
			if (allNodes[i].getType().equals("RT_BORDER") && allNodes[i].getASid() == ASid){
				borders.add(allNodes[i]);
			}
		}
		return borders;
		
	}
	
}
