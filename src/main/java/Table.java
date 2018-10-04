import java.util.ArrayList;

public class Table {
	private ArrayList<ArrayList<Tile>> table;
	
	public int getNumberOfTile() {
		// TODO Auto-generated method stub
		int u = 0;
		for(int i =0; i < table.size();i++) {
			u += table.get(i).size();
		}
		return u;
	}

	public boolean addTiles(ArrayList<Tile> tiles2) {
		// TODO Auto-generated method stub
		return false;
	}

	// a is the number of row holding tile.
	// b is the index of Tile in a
	// c is the number of row we want to push tile in
	// d is the number of index we want to put tile in c.
	public boolean moveTile(int a, int b, int c, int d) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Tile> getSetOrSequences(int i) {
		// TODO Auto-generated method stub
		return table.get(i);		
	}

}
