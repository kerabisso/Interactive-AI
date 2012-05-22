
public class MapNode {
	
	private int x;
	private int y;
	private NodeType n;
	
	
	public int getX() {
		return x;
	}
	private void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
	public NodeType getN() {
		return n;
	}
	public void setN(NodeType n) {
		this.n = n;
	}

}

/**
Attribute:

    x -  x-Korrdinate des Knotens
    y - y-Korrdinate des Knotens
    type - Typ des Knotens (0 = Leer, X = Wand, P = Super-Keks, S = Start, G = Geist)
**/
