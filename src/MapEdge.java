
public class MapEdge {
	
	private MapNode startNode;
	private MapNode endNode;
	private double cost;
	
	public MapNode getStartNode() {
		return startNode;
	}
	public void setStartNode(MapNode startNode) {
		this.startNode = startNode;
	}
	public MapNode getEndNode() {
		return endNode;
	}
	public void setEndNode(MapNode endNode) {
		this.endNode = endNode;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

}

/**
Attribute:

    vertex_start - Start-Knoten der Kante
    vertex_end - End-Knoten der Kante
    costs - Kosten (z.B. Länge) der Kante
**/