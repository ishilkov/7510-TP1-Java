package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.model.exception.BaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

    private String id;
    private Atom proposition;
    private List<Atom> conditions;

    private final static String ruleExpr = "(\\s*\\w+\\s*\\(\\s*\\w+\\s*[,\\s*\\w+\\s*]*\\)\\s*)" +
            ":-" +
            "(\\s*\\w+\\s*\\(\\s*\\w+\\s*[,\\s*\\w+\\s*]*\\)" +
            "\\s*[,{0,1}\\s*\\w+\\s*\\(\\s*\\w+\\s*[,\\s*\\w+\\s*]*\\)\\s*]*)";

    private final static String condExpr = "(\\s*\\w+\\s*\\(\\s*\\w+\\s*[,\\s*\\w+\\s*]*\\)\\s*)";


    private Pattern rulePattern;
    private Pattern condPattern;

    public Rule() {
        rulePattern = Pattern.compile(ruleExpr);
        condPattern = Pattern.compile(condExpr);
    }

    public void build(String entry) throws BaseException {

        Matcher m = rulePattern.matcher(entry);
        if (!m.find()) {
            throw new BaseException("Bad database structure: " + entry);
        }

        this.proposition = this.buildProposition(m.group(1));
        this.id = this.proposition.getId();
        this.conditions = this.buildConditions(m.group(2));
    }

    private Atom buildProposition(String prop) throws BaseException {

        Atom buildProp = new Atom();
        buildProp.build(prop);
        return buildProp;
    }

    private List<Atom> buildConditions(String conds) throws BaseException {
        Matcher m = condPattern.matcher(conds);

        List<Atom> buildConds = new ArrayList<Atom>();
        while (m.find() == true) {
            String cond = m.group(1);
            Atom buildCond = new Atom();
            buildCond.build(cond);
            buildConds.add(buildCond);
        }

        return buildConds;
    }

    public String getId() {
        return this.id;
    }

    public Atom getProposition() {
        return this.proposition;
    }

    public List<Atom> getConditions() {
        return this.conditions;
    }
}
