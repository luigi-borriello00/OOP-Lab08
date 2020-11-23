package it.unibo.oop.lab.advanced;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class DrawNumberViewLog implements DrawNumberView {

    static final String PATH = "C:\\Users\\Luigi\\Desktop\\Uni\\2° Anno\\Programmazione a oggetti\\week08\\lab\\OOP-Lab08\\res\\log.txt";
    private DrawNumberViewObserver observer;
    private final File file = new File(PATH);
    private  PrintStream ps;

    public DrawNumberViewLog() {
        try {
            ps = new PrintStream(this.file);
        } catch (FileNotFoundException e) {
            ps = null;
            e.printStackTrace();
        }
    }

    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;

    }

    public void start() {
        ps.print("Starting the game...\n");
    }

    public void numberIncorrect() {
        ps.append("Error: number INCORRECT\n");
    }

    public void result(final DrawResult res) {
        ps.append("Result: " + res.getDescription() + "\n");
    }

    public void limitsReached() {
        ps.append("Limits Reached \n");
    }

    @Override
    public void displayError(String err) {
        // TODO Auto-generated method stub
    }

}
