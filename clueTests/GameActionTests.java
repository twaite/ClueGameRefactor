package clueTests;



import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionTests {
	
	Color purple = new Color(255, 0, 255);
	Board board;
	Solution solution;
	ArrayList<BoardCell> cells;
	ClueGame game;
	ArrayList<Player> players;
	ArrayList<Card> cards;
	Card mustardCard;
	Card scarletCard;
	Card plumCard;
	Card candlestickCard;
	Card revolverCard;
	Card pipeCard;
	Card studyCard;
	Card kitchenCard;
	Card conservatoryCard;
	
	@Before
	public void initialize() {
		
		Solution solution = new Solution("Miss Scarlet", "Pool Room", "Candlestick");
		board = new Board();
		try{
			board.loadConfigFiles();
		}
		catch(BadConfigFormatException e){
			e = new BadConfigFormatException(board);
			System.out.println(e.toString());
		}
		cells = board.getCells();
		game = new ClueGame();
		game.loadConfigFiles();
		players = game.getPlayers();
		cards = game.getCards();
		mustardCard = new Card(Card.cardType.PERSON, "Colonel Mustard");
		scarletCard = new Card(Card.cardType.PERSON, "Miss Scarlet");
		plumCard = new Card(Card.cardType.PERSON, "Mr. Plum");
		candlestickCard = new Card(Card.cardType.WEAPON, "Candlestick");
		revolverCard = new Card(Card.cardType.WEAPON, "Revolver");
		pipeCard = new Card(Card.cardType.WEAPON, "Lead Pipe");
		kitchenCard = new Card(Card.cardType.ROOM, "Kitchen");
		studyCard = new Card(Card.cardType.ROOM, "Study");
		conservatoryCard = new Card(Card.cardType.ROOM, "Conservatory");

	}

	@BeforeClass
	public static void setup() {
		
	}

	@Test
	public void TestDeal() {
		int cardsDealt = 0;
		//COUNT THE NUMBER OF CARDS DEALT
		Player mustardPlayer = new Player();
		for ( Player p : players ) {
			cardsDealt += p.getCards().size();
			assertTrue( (p.getCards().size() == 2) || (p.getCards().size() == 3) );
			if ( p.getCards().contains(mustardCard) ) {
				mustardPlayer = p;
				players.remove(p); // Removes p from the list so we can search the rest later.
			}
		}

		//CHECK THAT THE NUMBER OF CARDS DEALT IS CORRECT
		assertEquals(15, cardsDealt);

		//CHECK TO SEE THAT THE SAME CARD WASN'T DEALT TO 2 PEOPLE:
		for ( Player play : players ) {
			assertFalse(play.getCards().contains(mustardCard));
		}
		players.add(mustardPlayer);

	}

	@Test
	public void TestLoadPlayers() {
		
		// CHECK THE SIZE OF THE players ARRAYLIST IS CORRECT
		assertEquals(6, players.size());
		
		// CHECK TO SEE THAT THE NAMES ARE CORRECT FOR EACH PLAYER
		assertEquals("Miss Scarlet", players.get(0).getName());
		assertEquals("Mr. Green", players.get(1).getName());
		assertEquals("Mrs. White", players.get(2).getName());
		assertEquals("Colonel Mustard", players.get(3).getName());
		assertEquals("Professor Plum", players.get(4).getName());
		assertEquals("Miss Peacock", players.get(5).getName());

		// CHECK THAT THE COLOR IS CORRECT FOR EACH PLAYER
		assertEquals(Color.red, players.get(0).getColor());
		assertEquals(Color.green, players.get(1).getColor());
		assertEquals(Color.white, players.get(2).getColor());
		assertEquals(Color.yellow, players.get(3).getColor());
		assertEquals(purple, players.get(4).getColor());
		assertEquals(Color.blue, players.get(5).getColor());
		
	}

	@Test
	public void TestLoadCards() {
		//CHECK THAT DECK HAS CORRECT NUMBER OF CARDS
		assertEquals(18, cards.size());
		int numWeapons = 0;
		int numPeople = 0;
		int numRooms = 0;
		//CHECK THAT DECK HAS CORRECT NUMBER OF EACH TYPE OF CARD
		for ( Card c : cards ) {
			if ( c.getType() == Card.cardType.WEAPON )
				++numWeapons;
			else if ( c.getType() == Card.cardType.PERSON )
				++numPeople;
			else if ( c.getType() == Card.cardType.ROOM )
				++numRooms;
		}

		assertEquals(6, numWeapons);
		assertEquals(6, numPeople);
		assertEquals(6, numRooms);

		//CHECK THAT DECK CONTAINS 3 SPECIFIC NAMED CARDS
		assertTrue(cards.contains(mustardCard));
		assertTrue(cards.contains(pipeCard));
		assertTrue(cards.contains(kitchenCard));

	}

	@Test
	public void TestCheckAccusation() {
		//ACCUSATION IS CORRECT
		assertTrue(game.checkAccusation(new Solution("Miss Scarlet", "Pool Room", "Candlestick")));

		//ACCUSATION IS INCORRECT - PERSON IS WRONG
		assertFalse(game.checkAccusation(new Solution("a", "Pool Room", "Candlestick")));

		//ACCUSATION IS INCORRECT - ROOM IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "b", "Candlestick")));

		//ACCUSATION IS INCORRECT - WEAPON IS WRONG
		assertFalse(game.checkAccusation(new Solution("Miss Scarlet", "Pool Room", "c")));

		//ACCUSATION IS INCORRECT - ALL THREE ARE WRONG
		assertFalse(game.checkAccusation(new Solution("a", "b", "c")));
	}

	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just four targets
		board.calcTargets(2, 8, 2);
		int loc_0_8_Tot = 0;
		int loc_1_7_Tot = 0;
		int loc_3_7_Tot = 0;
		int loc_4_8_Tot = 0;
		
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(0, 8)))
				loc_0_8_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(1, 7)))
				loc_1_7_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(3, 7)))
				loc_3_7_Tot++;
			else if (selected == board.getCellAt(board.calcIndex(4, 8)))
				loc_4_8_Tot++;
			else
				fail("Invalid target selected");
		}
		
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_0_8_Tot + loc_1_7_Tot + loc_3_7_Tot + loc_4_8_Tot);
		
		// Ensure each target was selected more than once
		assertTrue(loc_0_8_Tot > 1);
		assertTrue(loc_1_7_Tot > 1);
		assertTrue(loc_3_7_Tot > 1);
		assertTrue(loc_4_8_Tot > 1);
	}

	@Test
	public void TestDisproveSuggestionOnePlayerOneMatch() {
		//test one player, one correct match
		Player player = new ComputerPlayer();
		
		//deal six cards to player
		player.addCard(mustardCard);
		player.addCard(scarletCard);
		player.addCard(pipeCard);
		player.addCard(candlestickCard);
		player.addCard(studyCard);
		player.addCard(kitchenCard);
		
		//disproveSuggestion tests
		assertEquals("Revolver", player.disproveSuggestion(mustardCard, studyCard, revolverCard));
		assertEquals("Conservatory", player.disproveSuggestion(mustardCard, conservatoryCard, pipeCard));
		assertEquals("Mr. Plum", player.disproveSuggestion(plumCard, studyCard, pipeCard));
		assertEquals(null , player.disproveSuggestion(mustardCard, studyCard, pipeCard));
		
	}
	
	@Test
	public void TestDisproveSuggestionOnePlayerMultipleMatch() {
		Player player = new ComputerPlayer();
		
		//test one player, multiple possible matches
				player = new ComputerPlayer();
				player.addCard(mustardCard);
				player.addCard(kitchenCard);
				player.addCard(pipeCard);
				
				//Run 100 times
				int mustardDisp = 0;
				int kitchenDisp = 0;
				int pipeDisp = 0;
				
				for(int i = 0; i < 100; ++i) {
					String dispCard = player.disproveSuggestion(scarletCard, studyCard, candlestickCard);
					if ( dispCard == mustardCard.getName() )
						mustardDisp++;
					else if ( dispCard == kitchenCard.getName() )
						kitchenDisp++;
					else if ( dispCard == pipeCard.getName() ) 
						pipeDisp++;
					else 
						fail("Gave card player does not have");
				}
				
				//check that each card was given at least once
				assertTrue( mustardDisp > 1 );
				assertTrue( kitchenDisp > 1 );
				assertTrue( pipeDisp > 1 );
	}
	@Test
	public void TestPlayersQueried() {
		//test all players are queried
				players = new ArrayList<Player>();
				for(int i = 0; i < 5; ++i) {
					players.add(new ComputerPlayer());
				}
				players.get(0).addCard(mustardCard);
				players.get(1).addCard(scarletCard);
				players.get(2).addCard(pipeCard);
				players.get(3).addCard(candlestickCard);
				players.get(4).addCard(studyCard);
				players.add(new HumanPlayer());
				players.get(5).addCard(kitchenCard);
				
				Card plumCard = new Card(Card.cardType.PERSON, "Mr. Plum");
				Card revolverCard = new Card(Card.cardType.WEAPON, "Revolver");
				Card libraryCard = new Card(Card.cardType.ROOM, "Library");
	}
			

	@Test
	public void TestCreateSuggestion() {
		
	}
}