import java.util.LinkedList;


public class MapNode {

	int x;
	int y;
	boolean goodie = true;
	
	//alle kanten die von diesem Knoten ausgehen, maximal 4!!! (auﬂer wir bauen noch portale ein =P)
	private LinkedList<MapEdge> edges = new LinkedList<MapEdge>();
	
	
	public MapNode(int x, int y) {
		this.x = x;
		this.y = y;

	}
	
	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}
	
	public void removeGoodie(){
		goodie = false;
	}
	
	
	
	
	public void addEdge(MapEdge e) { 
		edges.add(e);
	}
	
	public boolean hasSamePosition(MapNode n){
		int x1 = n.getX();
		int y1 = n.getY();
		
		if((x == x1) && (y == y1)) {
			return true;
		} else {
			return false;
		}
	}

	 // public boolean containsEdge(Brauchen wir das?)
	
}
