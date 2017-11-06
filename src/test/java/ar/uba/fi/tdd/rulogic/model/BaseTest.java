package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.model.exception.BaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

	private Base base;

    @org.junit.Rule
    public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		this.base = new Base();
	}

	@Test
	public void badFactTest() throws BaseException {

		List<String> db = new ArrayList<>();
		db.add("varon(juan.");

		exception.expect(BaseException.class);
        exception.expectMessage("Bad database structure: varon(juan.");

        base.build(db);
    }

    @Test
    public void badRuleTest() throws BaseException {

        List<String> db = new ArrayList<>();
        db.add("tio(X, Y, Z) :-");

        exception.expect(BaseException.class);
        exception.expectMessage("tio(X, Y, Z) :-");

        base.build(db);
    }
}