package Reseaubayesien;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.fuzzylite.Engine;
import com.fuzzylite.term.Term;
import com.fuzzylite.variable.InputVariable;

public class Fuzzification {
    Engine engine = new Engine();

    public Fuzzification() {
        this.engine.setName("FuzzyBayes");
    }

    public InputVariable createFuzzyNode(String name, double a, double b) {
        InputVariable e = new InputVariable(name);
        e.setRange(a, b);
        return e;
    }

    public void addFuzzyState(InputVariable e, Term t) {
        e.addTerm(t);
    }

    public double getMembershipDegree(InputVariable e, double a, String t) {
        e.setInputValue(a);
        this.engine.process();
        return e.getTerm(t).membership(a);
    }

    public double normalize(double val, double sum) {
        return val / sum;
    }
}
