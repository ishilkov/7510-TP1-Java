package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class Base {

    private final static String ruleExp = ".*:-.*";

    private Pattern rulePattern;

    private Dictionary<String, Rule> rules;
    private List<Atom> facts;

    public Base() {
        rulePattern = Pattern.compile(ruleExp);
        rules = new Hashtable<String, Rule>();
        facts = new ArrayList<Atom>();
    }

    public void build(List<String> entries) {
        for (String entry : entries) {
            Matcher m = rulePattern.matcher(entry);
            if (m.matches()) {
                Rule rule = new Rule();
                rule.build(entry);
                this.rules.put(rule.getId(), rule);
            } else {
                Atom fact = new Atom();
                fact.build(entry);
                this.facts.add(fact);
            }
        }
    }

    public Rule getRule(String id) {
        return this.rules.get(id);
    }

    public List<Atom> getFacts(String id) {
        return this.facts.stream()
                .filter(f -> f.getId().equals(id))
                .collect(toCollection(ArrayList::new));
    }


}
