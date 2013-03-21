package clueTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ClueGame.*;

public class GameActionTests {
	
	@BeforeClass
	public void initialize() {
		ClueGame game = new ClueGame();
		game.loadConfigFiles();
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Card> cards = game.getCards();

	}

	@Before
	public void setup() {

	}

	@Test
	public void TestDeal() {
		int cardsDealt = 0;
		for ( Player p : players ) {
			cardsDealt += p.getCards.size();
			assert( (p.getCards().size() == 2) || (p.getCards().size() == 3) );
		}
		assertEquals(15, cardsDealt);
		//CHECK TO SEE THAT THE SAME CARD WASN'T DEALT TO 2 PEOPLE:
		assert(true);

	}

	@Test
	public void TestLoadPlayers() {
		assertEquals("name_here", players.get(0).getName());
		assertEquals("name_here", players.get(2).getName());
		assertEquals("name_here", players.get(5).getName());
	}

	@Test
	public void TestLoadCards() {
		assertEquals(18, cards.size());
		int numWeapons = 0;
		int numPeople = 0;
		int numRooms = 0;
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

		assert(cards.contains(Card(CardType.PERSON, "Colonel Mustard")));
		assert(cards.contains(Card(CardType.WEAPON, "Lead Pipe")));
		assert(cards.contains(Card(CardType.ROOM, "Kitchen")));

	}

	@Test
	public void TestCheckAccusation() {

	}

	@Test
	public void TestPickLocation() {

	}

	@Test
	public void TestDisproveSuggestion() {

	}

	@Test
	public void TestCreateSuggestion() {

	}
}