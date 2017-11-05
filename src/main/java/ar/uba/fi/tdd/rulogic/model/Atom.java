package ar.uba.fi.tdd.rulogic.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Atom {

    private final static String atomExp = "\\s*(\\w+)\\s*\\((\\s*\\w+\\s*[,\\s*\\w+\\s*]*)\\)\\s*";
    private final static String argsExp = ",{0,1}\\s*(\\w+)\\s*";

    private String id;
    private List<String> args;

    private Pattern atomPattern;
    private Pattern argsPattern;

    public Atom() {
        atomPattern = Pattern.compile(atomExp);
        argsPattern = Pattern.compile(argsExp);
    }

    public Atom(String id, List<String> args) {
        this.id = id;
        this.args = args;
    }

    public void build(String entry) {
        Matcher m = atomPattern.matcher(entry);
        if (m.find() == false) {
            //throw Exception here!
        }

        this.id = m.group(1);
        this.args = this.buildArgs(m.group(2));
    }

    private List<String> buildArgs(String args) {
        Matcher m = argsPattern.matcher(args);

        List<String> buildArgs = new ArrayList<String>();
        while (m.find() == true) {
            buildArgs.add(m.group(1));
        }

        return buildArgs;
    }

    public boolean evaluate(Atom atom) {

        boolean equal = this.getArgs().size() == atom.getArgs().size();

        if (!equal) {
            return false;
        }

        for (int i = 0; i < args.size(); i ++) {
            equal = this.getArgs().get(i).equals(atom.getArgs().get(i));
            if (!equal) {
                break;
            }
        }
        return equal;
    }

    public String getId() {
        return this.id;
    }

    public List<String> getArgs() {
        return this.args;
    }

}
