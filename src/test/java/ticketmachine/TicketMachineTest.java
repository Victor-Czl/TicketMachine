package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	public void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	public void imprimePasSiPasAssezDArgent() {
		machine.insertMoney(PRICE - 1);
		assertFalse(machine.printTicket(),"Pas assez d'argent. La machine ne doit pas imprimer");
	}

	@Test
	// S4 :  on imprime le ticket si le montant inséré est suffisant
	public void imprimeSiAssezDArgent() {
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "Il y a assez d'argent. La machine doit imprimer le ticket");
	}

	@Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	public void balanceDecrementeApresImpressionDuTicket() {
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(0, machine.getBalance());
	}

	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	public void balanceNeDecrementePasAvantImpressionDuTicket() {
		machine.insertMoney(PRICE);
		assertEquals(PRICE, machine.getBalance());
	}

	@Test
	// S7 : refund() rend correctement la monnaie
	public void rendCorrectementLaMonaie () {
		machine.insertMoney(PRICE);
		assertEquals(PRICE, machine.refund());
	}

	@Test
	// S8 : refund() remet la balance à zéro
	public void remetBalanceAZeroApresUnRefund () {
		machine.insertMoney(PRICE);
		machine.refund();
		assertEquals(0, machine.getBalance());
	}

}
