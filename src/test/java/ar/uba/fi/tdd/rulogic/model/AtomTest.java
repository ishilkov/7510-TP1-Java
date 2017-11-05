package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class AtomTest {

	@InjectMocks
	private Atom atom;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		this.atom.build("varon(juan).");
	}

	@Test
	public void test() {

		Assert.assertTrue(this.atom.getId().equals("varon"));
		Assert.assertTrue(this.atom.getArgs().size() == 1);
		Assert.assertTrue(this.atom.getArgs().get(0).equals("juan"));

	}

}
