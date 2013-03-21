package clueTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ClueGame.*;

public class GameActionTests {
	
	@BeforeClass
	public void initialize() {
		Card mustardCard = new Card(CardType.PERSON, "Colonel Mustard"));
		Card pipeCard = new Card(CardType.WEAPON, "Lead Pipe"));
		Card kitchenCard = Card(CardType.ROOM, "Kitchen"));

		Board board = new Board();
		ArrayList BoardCell cells = board.getCells();
		ClueGame game = new ClueGame();
		game.loadConfigFiles();
		game.setSolution(Solution("Miss Scarlet", "Pool Room", "Candlestick"));
		game.deal();
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Card> cards = game.getCards();

	}

	@Before
	public void setup() {

	}

	@Test
	public void TestDeal() {
		int cardsDealt = 0;
		//COUNT THE NUMBER OF CARDS DEALT
		player MustardPlayer;
		for ( Player p : players ) {
			cardsDealt += p.getCards.size();
			assertTrue( (p.getCards().size() == 2) || (p.getCards().size() == 3) );
			if p.getCards.contains(mustardCard)
				mustardPlayer = new Player(p);
				players.remove(p); // Removes p from the list so we can search the rest later.
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
		// CHECK TO SEE THAT THE NAMES ARE CORRECT FOR EACH PLAYER
		assertEquals("name_here", players.get(0).getName());
		assertEquals("name_here", players.get(2).getName());
		assertEquals("name_here", players.get(5).getName());
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
		assertTrue(cards.contains(mustardCard);
		assertTrue(cards.contains(pipeCard);
		assertTrue(cards.contains(kitchenCard);

	}

	@Test
	public void TestCheckAccusation() {
		//ACCUSATION IS CORRECT
		assertTrue(game.checkAccusation(Solution("Miss Scarlet", "Pool Room", "Candlestick")));

		//ACCUSATION IS INCORRECT - PERSON IS WRONG
		assertFalse(game.checkAccusation(Solution("a", "Pool Room", "Candlestick")));

		//ACCUSATION IS INCORRECT - ROOM IS WRONG
		assertFalse(game.checkAccusation(Solution("Miss Scarlet", "b", "Candlestick")));

		//ACCUSATION IS INCORRECT - WEAPON IS WRONG
		assertFalse(game.checkAccusation(Solution("Miss Scarlet", "Pool Room", "c")));

		//ACCUSATION IS INCORRECT - ALL THREE ARE WRONG
		assertFalse(game.checkAccusation(Solution("a", "b", "c")));

	}

	@Test
	public void TestPickLocation() {
		ArrayList<BoardCell> possibleTargets = new ArrayList<BoardCell>();
		possibleTargets.add(BoardCell(row, column));
		possibleTargets.add(BoardCell(row, column));
		possibleTargets.add(BoardCell(row, column));
		possibleTargets.add(BoardCell(row, column));
		//etc.


	}

	@Test
	public void TestDisproveSuggestion() {
		players.get(0).disproveSuggestion("Mr. Green", "Kitchen", "Knife");
	}

	@Test
	public void TestCreateSuggestion() {

	}
}