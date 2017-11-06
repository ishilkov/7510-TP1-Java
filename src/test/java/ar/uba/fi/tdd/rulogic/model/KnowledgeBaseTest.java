package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBaseTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		this.knowledgeBase = new KnowledgeBase();
		this.knowledgeBase.build("rules.db");
	}

	@Test
	public void test() {

		try {
			Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));
			Assert.assertTrue(this.knowledgeBase.answer("hijo (pepe, juan)."));
			Assert.assertFalse(this.knowledgeBase.answer("mujer (juan)."));
			Assert.assertFalse(this.knowledgeBase.answer("hijo (pepe, pepe)."));
		}
		catch (Exception ex) {}
	}
}