package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class KnowledgeBase {

	private Base base;

	public KnowledgeBase() {
		this.base = new Base();
	}

	public void build(String filePath) throws NoSuchFileException {

		List<String> entries = new BufferedReader(new InputStreamReader(getResource(filePath)))
			.lines().collect(toCollection(ArrayList::new));
		base.build(entries);
	}

	public void buildFromList(List<String> entries) {
		base.build(entries);
	}

	public boolean answer(String query) {

		Atom prop = new Atom();
		prop.build(query);

		Rule rule = this.base.getRule(prop.getId());

		if (rule != null) {
			return evaluateRule(prop, rule);
		} else {
			return evaluateFact(prop);
		}
	}

	public InputStream getResource(String filePath) throws NoSuchFileException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(filePath);

		if(inputStream == null) {
			throw new NoSuchFileException("Resource file not found. Note that the current directory is the source folder!");
		}

		return inputStream;
	}

	private boolean evaluateFact(Atom prop) {
		List<Atom> facts = this.base.getFacts(prop.getId());
		return facts.stream().anyMatch(f -> f.evaluate(prop));
	}

    private boolean evaluateRule(Atom prop, Rule rule) {
		Dictionary<String, String> argMap = this.mapProposition(prop, rule);
		List<Atom> parsedConds = this.parseConditions(argMap, rule);

		return parsedConds.stream()
				.allMatch(c -> this.base.getFacts(c.getId()).stream()
						.anyMatch(f -> f.evaluate(c)));
	}

    private Dictionary<String, String> mapProposition(Atom prop, Rule rule) {
		Dictionary<String, String> argMap = new Hashtable<>();

		List<String> propArgs = rule.getProposition().getArgs();

		for (int i=0; i < propArgs.size(); i++) {
			argMap.put(propArgs.get(i), prop.getArgs().get(i));
		}

		return argMap;
	}

    private List<Atom> parseConditions(Dictionary<String, String> argMap, Rule rule) {
		List<Atom> parsedConds = new ArrayList<>();

		rule.getConditions().forEach(c -> {
			List<String> parsedArgs = new ArrayList<String>();
			c.getArgs().forEach(a -> parsedArgs.add(argMap.get(a)));
			parsedConds.add(new Atom(c.getId(), parsedArgs));
		});

		return parsedConds;
	}

}
