package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class AtomTest {

	private Atom atom;

	@Before
	public void setUp() throws Exception {

		this.atom = new Atom();
		this.atom.build("varon(juan, jose).");
	}

	@Test
	public void test() {
		Assert.assertTrue(this.atom.getId().equals("varon"));
		Assert.assertTrue(this.atom.getArgs().size() == 2);
		Assert.assertTrue(this.atom.getArgs().get(0).equals("juan"));
		Assert.assertTrue(this.atom.getArgs().get(1).equals("jose"));
	}

}
