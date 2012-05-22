import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MapCreator {
	
	private MapEdge[] edges;
	private int pacMode;
	private int cookies;
	private char[][] map;
	
	
	public void iniArray(File f)throws IOException{
		FileReader fr=new FileReader(f);
		BufferedReader br= new BufferedReader(fr);
		
		String zeile="";
		int zaehlerZeile=0;
		int zaehlerSpalte=0;
		while((zeile=br.readLine())!=null){
			zaehlerZeile++;
			if ((zaehlerSpalte != 0) && (zeile.length() != zaehlerSpalte) {
				zaehlerSpalte= zeile.length();
			} else
		}

	
		
	}
	
	public void erzeugeArray(File f)throws IOException{
		FileReader fr=new FileReader(f);
		BufferedReader br= new BufferedReader(fr);
		
		
		
	}

}



/**
 * 
 * Attribute:
    edges[edge] - Array mit Edges. Vergleichen kann man über die compare-Funktion.
	Wir können auch ein edges[vertex][vertex] - array nehmen und die Kanten "weglassen". 
	Ginge auch. Auch "costs" bei Kanten ist (so wie wir die Karte im Augenblick generieren 
	und solange wir keine Knoten zu langen Kanten zusammenfassen) überflüssig.  TODO
    pacman_mode - Flag für den Pagman-Modus
    cookies - Gesammelte Puntke
 */

/**
 * Funktionen:

initialize_graph (KARTE.txt){

char-array map[int][int] = "";

$last_space = "";

liest KARTE.txt zeilenweise aus, aktuelles Zeichen in Variable $zeichen:

schreibt Felder in map-array map[x][y] (der Wert ist entweder X, O, P, S oder G)

if($zeichen == "O") $last_space = $zeichen // 
Damit ist der letztplazierte freie Platz in der Variable -> unser Startpunkt zur Graphenübersetung


}
explore_graph (map[int][int], Knoten

wenn nicht X, dann (begehbar)

wenn kante noch nicht im
 */
    