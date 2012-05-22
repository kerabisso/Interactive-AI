
import siris.java.JavaInterface;
import siris.pacman.graph.JavaGraphWrapper;

public class Level {

	public static void main(String[] args) {
		
		
		
		JavaInterface inst = new JavaInterface(true);
		inst.startRenderer(800, 600);
		
		JavaGraphWrapper.drawGraph(root, inst);
	}

	
}
