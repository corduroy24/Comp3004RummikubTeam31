public class Tile {
	protected  String Color;
	protected  int Number;
	//1,2,3,4 respectively Red Blue Green Orange
	  String[] Colors = { "R", "B", "G", "O" };
	  int[] Numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };


	Tile (int x, int y) { // create a tile with the specific color and number
		this.Color = Colors[x - 1];
		this.Number = Numbers[y - 1];
	
	}


	
	public String getColor() {
		return Color;
	}

	public int getNumber() {
		return Number;
	}

	public Tile() { // used to add a wild tile to the deck
		this.Color = "Wild";
		this.Number = 14;

	}
	
	public int calculate(Tile x,Tile y) {
		return (x.getNumber()+y.getNumber());
	}

	
}