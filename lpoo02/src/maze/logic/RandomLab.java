package maze.logic;

public class RandomLab {
	private char[][] maze;
	
	public final char X = 'X';

	public void fullLab(int size){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				maze[i][j] = X;
			}
		}
	}
	
	
	
	public static void main(String[] args) {

	}

}
