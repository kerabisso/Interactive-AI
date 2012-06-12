import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class MapCreator {

	int maprow; //Zeilen
	int mapcol; //Spalten
	
	char[][] map;
	
	LinkedList<MapNode> Nodes = new LinkedList<MapNode>(); // Alle Knoten die es gibt
	LinkedList<MapNode> Nodes_Done = new LinkedList<MapNode>(); // Knoten die wir schon angefasst haben (Kanten zu Nachbarn angelegt)
	LinkedList<MapNode> Nodes_Todo = new LinkedList<MapNode>(); // Knoten die wir noch anpacken müssen (Kante zu nur einem Nachbarn angelegt)
	
	MapNode pacman;
	
	LinkedList<MapNode> Ghosts = new LinkedList<MapNode>();
	LinkedList<MapNode> PoUps = new LinkedList<MapNode>();
	
	/**
	 * Liest die map aus einer txt-datei aus und schreibt die zeichen in ein char[][]
	 * @param f die uebergebene txt-Datei
	 */
	public void leseDatei(File f)  {
		try {
			LinkedList<char[]> charlist = new LinkedList<char[]>(); //hier werden die einzelnen Zeilen als char[] abgespeichert (werden spaeter ins char[][] uebertragen)
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			String row = "";
			row = br.readLine();
			
			mapcol = row.length(); //anzahl der Spalten = anzahl der Zeichen in der ersten Zeile
			charlist.add(row.toCharArray());
			int cols;
			
 			// Zeilen werden ausgelesen und als char[] in charlist gespeichert
			while ((row = br.readLine()) != null) {
				charlist.add(row.toCharArray());
				cols = row.length();
				if (cols != mapcol) {
					System.out.println("Spaltenanzahl ungleichmaessig!");
				}
			}
			
			maprow = charlist.size();
			map = new char[maprow][];
			
			//zeilen werden aus charlist in map (char[][]) geschrieben
			for (int i=0; i<charlist.size(); i++) {
				map[i] = charlist.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//Blubb2
	public void buildGraph() {
		
		// Alle Knoten in Liste Nodes bestimmten
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[0].length; j++) {
				
				char wall = "_".charAt(0); //"You can use the String.charAt(index) method. If there's just one character in the String, just use charAt(0)."
				char ghost = "G".charAt(0);
				char poup = "K".charAt(0);//powerup = keks(k)
				char pac = "P".charAt(0);
				
				
				if (map[i][j] != wall) { // _ ist Wand
					Nodes.add(new MapNode(i,j));
					
					if(map[i][j]  == ghost) {
						MapNode
					}
					
					
					
					// removeGoodie
					
					//if (map[i][j] == "G") Nodes_Ghosts.add(new MapNode(i,j));
					//TODO: Hier speichern wir geister usw. in die geisterliste usw.
					// Wand gleich als elseif
					
				}
			}
		}
		
		// Nodes_Done Liste mit Knoten die wir schon angefasst haben
		// Nodes_Todo Liste mit Knoten die wir noch anfassen müssen
		
		Nodes_Todo.add(Nodes.getFirst()); // Fügt den Startknoten in die Todo-Liste ein
		
		while(!Nodes_Todo.isEmpty()){ //waehrend noch etwas todo ist
			
			MapNode tmp_Node = Nodes_Todo.getFirst(); // Nimmt den nächsten Knoten in der Todo-Liste
			
			int x = tmp_Node.getX();
			int y = tmp_Node.getY();
			
			Nodes_Todo.remove(tmp_Node);
			Nodes_Done.add(tmp_Node);
			
			LinkedList<MapNode> Nodes_Direction = new LinkedList<MapNode>(); // Temporäre Liste von Knoten
			
			Nodes_Direction.add(new MapNode(x,y-1)); // Norden
			Nodes_Direction.add(new MapNode(x+1,y)); // Osten
			Nodes_Direction.add(new MapNode(x,y+1)); // Süden
			Nodes_Direction.add(new MapNode(x-1,y)); // Westen
			
			for(MapNode directionNode : Nodes_Direction){ // Packe alle vier Himmelsrichtungen an
				
				for(MapNode node : Nodes) { // Vergleiche mit allen anderen Knoten
					if(directionNode.hasSamePosition(node) && !Nodes_Done.contains(node) && !Nodes_Todo.contains(node)){ // Koordinate ist Knoten in Nodes // !Nodes_Todo.contains(node) vl. nochmal überdenken?
						MapEdge edge = new MapEdge(tmp_Node, node); // Erstellt Kante zwischen Knoten und Nachbarknoten
						tmp_Node.addEdge(edge);
						node.addEdge(edge);
						Nodes_Todo.add(node);
						break;
					}
				}
			}	
		
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
