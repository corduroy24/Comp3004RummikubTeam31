import java.util.ArrayList;

import junit.framework.TestCase;


	public class TestJoker extends TestCase{
		private Support functions;
		
		public void testgameWithJokers() {
			Player p1 =  new Player("AI1",1,new PlayerStrategy2());
			
			Tile l[] = {new Tile(1,10),new Tile(1,11),new Tile(14,14), new Tile(2,4), new Tile(3,4),new Tile(4,4)};
			p1.getHand().addTilesToHand(l);
			
			System.out.println("Hand has joker and plays it as 12");
			
			assertTrue (p1.play() == true);
			p1.getHand().HandReader();
			assertTrue(p1.getHand().sizeOfHand() == 0);
			
		}
		
		
		public void testJSequences() {
			
			functions = new Support();
			Tile t1 =new Tile(1,1);
			Tile t2=new Tile(1,2);
			Tile t3=new Tile(1,3);
			
			Tile t4 = new Tile(1,11);
			Tile t5 = new Tile(1,12);
			Tile t6 = new Tile(1,13);
			
			Tile t7=new Tile(1,5);

			ArrayList<Tile> tList = new ArrayList<Tile>();
			ArrayList<Tile> tList2 = new ArrayList<Tile>();
			ArrayList<Tile> tList3 = new ArrayList<Tile>();
			
			
			tList.add(t2);
			tList.add(t3);
			
			tList=functions.getJokerSequences(tList); 
				assertEquals(4,tList.get(2).getNumber());
				
				
				tList2.add(t5);
				tList2.add(t6);
				tList2=functions.getJokerSequences(tList2);
				assertEquals(11,tList2.get(0).getNumber());
				
				tList3.add(t3);
				tList3.add(t7);
				tList3=functions.getJokerSequences(tList3);
				assertEquals(4,tList3.get(1).getNumber());
				
		}
		
		public void testJseqMax() {
			ArrayList<Tile> tList = new ArrayList<Tile>();
			functions = new Support();
			Tile joker = new Tile();	
			Tile t1 =new Tile(1,2);
			Tile t2=new Tile(1,2);
			Tile t3=new Tile(1,4);

			Tile t5 = new Tile(1,12);
			Tile t6 = new Tile(1,13);
			tList.add(t1);
			tList.add(joker);
			tList.add(t2);
			tList.add(t3);
			tList.add(t5);
			tList.add(t6);
			tList=functions.getJokerSequences(tList); 
			

			assertTrue(tList.get(0).getNumber() == 11);
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
			Tile t6=new Tile(1,7);
			Tile t7=new Tile(1,8);
			Tile t8=new Tile(1,9);
			Tile t9=new Tile(1,11);
			
			tList.add(t1);
			tList.add(t2);
			tList.add(t3);
			
			tList.add(t4);
			tList.add(t5);
			tList.add(t6);
			
			tList.add(t7);
			tList.add(t8);
			tList.add(t9);

			tList = functions.getJInBetween(tList);
			assertEquals(10,tList.get(1).getNumber());
			
		}
	
		
		
	}
