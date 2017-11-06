package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RuleTest {

	private Rule rule;

	@Before
	public void setUp() throws Exception {

		this.rule = new Rule();
		this.rule.build("tia(X, Y, Z):- mujer(X), hermano(X, Z),padre(Z, Y).");
	}

	@Test
	public void test() {
		Assert.assertTrue(this.rule.getId().equals("tia"));

		Assert.assertTrue(this.rule.getProposition().getId().equals("tia"));
		Assert.assertTrue(this.rule.getProposition().getArgs().size() == 3);
		Assert.assertTrue(this.rule.getProposition().getArgs().get(0).equals("X"));
		Assert.assertTrue(this.rule.getProposition().getArgs().get(1).equals("Y"));
		Assert.assertTrue(this.rule.getProposition().getArgs().get(2).equals("Z"));

		Assert.assertTrue(this.rule.getConditions().size() == 3);

		Assert.assertTrue(this.rule.getConditions().get(0).getId().equals("mujer"));
		Assert.assertTrue(this.rule.getConditions().get(0).getArgs().size() == 1);
		Assert.assertTrue(this.rule.getConditions().get(0).getArgs().get(0).equals("X"));

		Assert.assertTrue(this.rule.getConditions().get(1).getId().equals("hermano"));
		Assert.assertTrue(this.rule.getConditions().get(1).getArgs().size() == 2);
		Assert.assertTrue(this.rule.getConditions().get(1).getArgs().get(0).equals("X"));
		Assert.assertTrue(this.rule.getConditions().get(1).getArgs().get(1).equals("Z"));

		Assert.assertTrue(this.rule.getConditions().get(2).getId().equals("padre"));
		Assert.assertTrue(this.rule.getConditions().get(2).getArgs().size() == 2);
		Assert.assertTrue(this.rule.getConditions().get(2).getArgs().get(0).equals("Z"));
		Assert.assertTrue(this.rule.getConditions().get(2).getArgs().get(1).equals("Y"));
	}
}
