import java.util.ArrayList;

import junit.framework.TestCase;


	public class TestJoker extends TestCase{
		private Support functions;
		
		public void testJSequences() {
			functions = new Support();
			Tile t1 =new Tile(1,1);
			Tile t2=new Tile(1,2);
			Tile t3=new Tile(1,3);
			
			Tile t4 = new Tile(1,11);
			Tile t5 = new Tile(1,12);
			Tile t6 = new Tile(1,13);
			
			ArrayList<Tile> tList = new ArrayList<Tile>();
			ArrayList<Tile> tList2 = new ArrayList<Tile>();
			
			tList.add(t1);
			tList.add(t2);
			tList.add(t3);
			
			functions.getJokerSequences(tList);
				assertEquals(4,tList.get(tList.size()-1).getNumber());
				
				tList2.add(t4);
				tList2.add(t5);
				tList2.add(t6);
				functions.getJokerSequences(tList2);
				assertEquals(10,tList2.get(0).getNumber());
		}
		
		public void testJSets() {
			functions = new Support();
			Tile t1 =new Tile(1,1);
			Tile t2=new Tile(2,1);
			Tile t3=new Tile(3,1);
			
			ArrayList<Tile> tList = new ArrayList<Tile>();
			tList.add(t1);
			tList.add(t2);		
			tList.add(t3);
			
			ArrayList<Tile> tList2 = new ArrayList<Tile>();
			
			tList2.add(t1);
			tList2.add(t2);	
			
			functions.getJokerSets(tList);
				assertEquals("O",tList.get(tList.size()-1).getColor());
				
				functions.getJokerSets(tList2);
				assertEquals("G",tList2.get(tList2.size()-1).getColor());
				
		}
		
		public void testInBetween() {
			functions = new Support();
			ArrayList<Tile> tList = new ArrayList<Tile>();
			Tile t1 =new Tile(1,1);
			Tile t2=new Tile(1,2);
			Tile t3=new Tile(1,3);
			
			Tile t4 =new Tile(1,5);
			Tile t5=new Tile(1,6);
			Tile t6=new Tile(1,8);
			
			tList.add(t1);
			tList.add(t2);
			tList.add(t3);
			
			tList.add(t4);
			tList.add(t5);
			tList.add(t6);
			
			
			tList = functions.getJInBetween(tList);
			assertEquals(7,tList.get(1).getNumber());
		}
		
		
	}
