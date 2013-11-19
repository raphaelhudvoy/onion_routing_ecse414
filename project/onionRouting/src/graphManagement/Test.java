package graphManagement;

import shortestPath.DepthFirstSearch;
import shortestPath.DijkstraGraph;

public class Test {

	public static void main (String [] args){
		GraphOverlord gl = new GraphOverlord (System.getProperty("user.dir") + "/dat/trial.brite");//+ "/../../dat/trial.brite");
		//gl.print();
		Node source = gl.getRandomRouter();
		//Node source = gl.getNode(8);
		System.out.println ("Source chosen as: " + source);
		System.out.println ("Starting...");
		//DijkstraGraph dg = new DijkstraGraph (source, gl.getAS(source));
		DepthFirstSearch ds = new DepthFirstSearch(source);
		System.out.println("done");
	}
	
}
