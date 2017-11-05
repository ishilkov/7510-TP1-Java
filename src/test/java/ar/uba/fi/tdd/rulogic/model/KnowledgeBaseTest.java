package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		List<String> entries = new ArrayList<>();
		entries.add("varon(juan).");
		entries.add("varon(pepe).");
		entries.add("varon(hector).");
		entries.add("varon(roberto).");
		entries.add("varon(alejandro).");
		entries.add("mujer(maria) .");
		entries.add("mujer(cecilia).");
		entries.add("padre(juan, pepe).");
		entries.add("padre(juan, pepa).");
		entries.add("padre(hector, maria).");
		entries.add("padre(roberto, alejandro).");
		entries.add("padre(roberto, cecilia).");
		entries.add("hijo(X, Y) :- varon(X), padre(Y, X).");
		entries.add("hija(X, Y) :- mujer(X), padre(Y, X).");
		entries.add("hermano(nicolas, roberto).");
		entries.add("hermano(roberto, nicolas).");
		entries.add("varon ( nicolas ) .");
		entries.add("tio(X, Y, Z):- varon(X),\thermano(X, Z),padre(Z, Y).");
		entries.add("tia(X, Y, Z):- mujer(X),\thermano(X, Z),padre(Z, Y).");

		this.knowledgeBase.buildFromList(entries);
	}

	@Test
	public void test() {

		Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));
		Assert.assertTrue(this.knowledgeBase.answer("hijo (pepe, juan)."));

	}

}
