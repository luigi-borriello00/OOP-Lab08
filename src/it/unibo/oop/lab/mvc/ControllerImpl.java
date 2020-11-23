package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    static final int OFFSET = 1;
    private final List<String> historyStrings = new ArrayList<>();
    private String currentString;

    public void setNextString(String x) {
        this.historyStrings.add(x);
 
    }

    public String getNextString() {
        this.currentString = this.historyStrings.get(this.historyStrings.size() - this.OFFSET);
        return this.currentString;
    }

    public List<String> getHistory() {
        return this.historyStrings;
    }

    public String getCurrentString() {
        return this.currentString;
    }

}
