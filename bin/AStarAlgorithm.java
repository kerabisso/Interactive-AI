package de.masterfacture.ProductionPlanning.RouteCalculation;

import de.masterfacture.ProductionPlanning.Interfaces.*;

import java.util.*;

/**
 * An implementation of the A* algorithm for planning routes through the
 * production plant.
 * 
 * Nodes of type Conveyor serve as the edges of the graph.s
 * @author macke
 *
 */
public class AStarAlgorithm implements IPathAlgorithm
{
	/**
	 * The open list: nodes that need to be examined.
	 */
	private Dictionary<INode, Double> openList;
	
	/**
	 * The closed list: nodes that have already been fully examined.
	 */
	private ArrayList<INode> closedList;
	
	/**
	 * A reference to the graph on which the path is calculated.
	 */
	private IGraph graph;
	
	/**
	 * The path (list of nodes to go through).
	 */
	private ArrayList<INode> path;
	
	/**
	 * Constructor.
	 * @param graph	The graph on which to calculate the paths.
	 */
	public AStarAlgorithm(IGraph graph)
	{
		this.graph = graph;
	}
	
	/**
	 * Removes the next node from the open list (the one with
	 * the smallest value for "f").
	 * @return The next node from the open list.
	 */
	private INode removeMinNode()
	{
		// TODO: find an easier way to get the minimum value from the open list
		double minValue = 99999;
		INode minNode = null;
		Enumeration<INode> nodes = this.openList.keys();
		while (nodes.hasMoreElements())
		{
			INode curNode = (INode)nodes.nextElement();
			double curValue = this.openList.get(curNode);
			if (curValue < minValue)
			{
				minValue = curValue;
				minNode = curNode;
			}
		}
		// delete node from the list, so that it is not examined twice
		this.openList.remove(minNode);
		return minNode;
	}
	
	public ArrayList<INode> calculatePath(String from, String to)
	{
		// initialize the needed lists
		this.openList = new Hashtable<INode, Double>();
		this.closedList = new ArrayList<INode>();
		this.path = new ArrayList<INode>();
		
		// get start and end node
		INode startNode = this.graph.getNode(from);
		INode endNode = this.graph.getNode(to);
		
		// add the start node to the open list
		this.openList.put(startNode, 0.0);

		do
		{
			// get the next node from the open list
			INode curNode = this.removeMinNode();

			// end node reached?
			if (curNode == endNode)
			{
				// recursively add the nodes to the path
				double completeDistance = 0;
				path.add(curNode);
				INode predecessor = null;
				do
				{
					predecessor = curNode.getPredecessor();
					if (predecessor != null)
					{
						completeDistance += predecessor.getDistanceToSuccessor(curNode);
						path.add(predecessor);
						curNode = predecessor;
					}
				} while (predecessor != null);

				return this.path;
			}
			
			// put the current node's successors on the open list
			this.expandNode(curNode);
			
			// current node is fully examined and put on the closed list 
			this.closedList.add(curNode);
			
		// repeat until open list is empty
		} while (this.openList.size() > 0);
		
		return null;
	}
	
	/**
	 * Examines the current node's successor nodes and puts them on the open list if
	 * - the successor node is examined for the first time
	 * - a shorter path to the successor node is found 
	 * @param curNode The current node whose successors shall be examined.
	 */
	private void expandNode(INode curNode)
	{
		//System.out.println("  expanding " + curNode.getID());
		// get the current node's successors
		ArrayList<INode> successors = curNode.getSuccessors();
		for (INode successor: successors)
		{			
			// successor is already on the closed list
			if (this.closedList.contains(successor))
				continue;
			
			// costs for the new path:
			// 1) costs from current node to start node
			// 2) costs from current node to successor
			// 3) estimated costs from successor to end node
			double f = this.g(curNode) + curNode.getDistanceToSuccessor(successor) + this.h(successor);

			//System.out.println("    f for " + successor.getID() + ": " + f);

			if (this.openList.get(successor) != null)
			{
				if (f > this.openList.get(successor))
					continue;
			}

			// set the current node's predecessor
			successor.setPredecessor(curNode);
			
			// save the (new) f value to the open list
			this.openList.put(successor, f);
		}
	}
	
	/**
	 * Calculates costs for the path from the given node to the start node.
	 * @param curNode The node for which to calculate "g".
	 * @return The costs for the path from the given node to the start node.
	 */
	private double g(INode curNode)
	{
		int g = 0;
		INode predecessor = null;
		do
		{
			predecessor = curNode.getPredecessor();
			if (predecessor != null)
			{
				g += predecessor.getDistanceToSuccessor(curNode);
				curNode = predecessor;
			}
		} while (predecessor != null);
		return g;
	}

	/**
	 * Estimates the costs for the path from the given node to the end node.
	 * @param curNode The node for which to calculate "h".
	 * @return The estimated costs for the path from the given node to the end node.
	 */
	private double h(INode curNode)
	{
		// TODO: implement heuristics
		return 1;
	}
}
