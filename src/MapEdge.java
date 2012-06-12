import java.util.LinkedList;


public class MapEdge {
	
	//Liste um den Start- und Endknoten der Kante zu speichern -> nur 2 Eintraege!!!!
	private LinkedList<MapNode> node = new LinkedList<MapNode>();
	
	public MapEdge(MapNode one, MapNode two) {
		node.add(one);
		node.add(two);
	}

}
