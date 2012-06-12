
public class Test {
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		int[] b = {6,7,8,9,0};
		
		int[][] test = new int[2][];
		test[0] = a;
		test[1] = b;
		
		System.out.println(test[1][2]);
		
		
	}

}
