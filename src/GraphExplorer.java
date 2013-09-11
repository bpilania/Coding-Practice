import java.util.*;
import java.util.Queue;
public class GraphExplorer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Graph> graphList = new ArrayList<Graph>();

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		Graph graph = new Graph(1,list);
		graphList.add(graph);
		
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(4);
		list.add(3);
		graph = new Graph(2,list);
		graphList.add(graph);
		
		list = new ArrayList<Integer>();
		list.add(4);
		list.add(2);
		graph = new Graph(3,list);
		graphList.add(graph);
		
		
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		graph = new Graph(4,list);
		graphList.add(graph);
		
		
	
	}

}

class Graph{
	int value;
	boolean visited = false;
	ArrayList<Integer> links = new ArrayList<Integer>();
	
	Graph(int value, ArrayList<Integer> links){
		this.value = value;
		this.links = links;
	}
	
	void addNode(Integer node1, Integer node2, ArrayList<Graph> graphList){
		if(!graphList.contains(node1)){
			ArrayList<Integer> link = new ArrayList<Integer>();
			link.add(node2);
			graphList.add(new Graph(node1, link));
		}
		else{
			Graph temp = graphList.get(graphList.indexOf(node1));
			ArrayList<Integer> list = temp.links;
			if(!list.contains(node2))
				list.add(node2);
		}
		
		if(!graphList.contains(node2)){
			ArrayList<Integer> link = new ArrayList<Integer>();
			link.add(node1);
			graphList.add(new Graph(node2, link));
		}
		else{
			Graph temp = graphList.get(graphList.indexOf(node2));
			ArrayList<Integer> list = temp.links;
			if(!list.contains(node1))
				list.add(node1);
		}
	}
	
	
/*	void dfs(Graph node){
		if(node == null) return;
		
		node.visited = true;
		for(temp : links){
			if(temp.visited != true)
				dfs(temp);
			System.out.println(temp.value);
		}
		
	}
	
	void bfs(Graph node){
		Queue<Graph> queue = new java.util.LinkedList<Graph>();
		node.visited = true;
		queue.add(node);
		
		while(!queue.isEmpty()){
			Graph temp = queue.poll();
			for(Graph child : temp.links){
				if(!child.visited){
					child.visited = true;
					queue.add(child);
				}
			}
		}
	}
	*/
	boolean isRouteAvailable(Graph node, Graph p1, Graph p2){
		
		
		return false;
	}
	
	
}
