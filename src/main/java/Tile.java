public class Tile{
	protected  String Color;
	protected  int Number;
	//1,2,3,4 respectively Red Blue Green Orange
	String[] Colors = { "R", "B", "G", "O", "J"};
	int[] Numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	private boolean isJoker = false;
	private int jokerPoint = 0;
	private String jokerColor = "J";
	
	public void setJokerPoint(int a) {jokerPoint = a;}
	public int getJokerPoint() {return jokerPoint;}
	public void setJokerColor(int a) {jokerColor = Colors[a - 1];}
	public void setJokerColor(String a) {jokerColor = a;}
	public String getJokerColor() {return jokerColor;}
	
	
	
	
	Tile (int x, int y) { // create a tile with the specific color and number
		if(x == 14 && y == 14) { 
			isJoker = true;
			this.Color = "J";
			this.Number = 14;
		}
		else if(x == 5 && y == 14)
		{
			isJoker = true;
			this.Color = "J";
			this.Number = 14;
		}
		else {
		this.Color = Colors[x - 1];
		this.Number = Numbers[y - 1];
		}
	}

	public void setJoker(boolean x) {
		isJoker=x;
	}
	
	public void setColor(String x) {
		Color = x;	
	}
	
	public void setNumber(int x) {
		Number=x;
	}
	
	public boolean isJoker() {
		return isJoker;
	}
	
	public String getColor() {
		return Color;
	}

	public int getNumber() {
		return Number;
	}

	public Tile() 
	{
		this.Color = "J";
		this.Number = 14;
		this.isJoker = true;
	}
	
	public int calculate(Tile x,Tile y) {
		return (x.getNumber()+y.getNumber());
	}
	
	public String toString() {
		String out = "";
		String color = " ";
		if(Color.equals("R"))
			color = "Red";
		else if(Color.equals("G"))
			color = "Green";
		else if(Color.equals("B"))
			color = "Blue";
		else if (Color.equals("J")) {
			color = "Joker";
			out += ("{" + color + this.getJokerPoint() + "} ");
			return out;
		}
		else 
			color = "Orange";
		
			
			
		out += ("{" + color + Number + "} ");
		return out;
	}	
}