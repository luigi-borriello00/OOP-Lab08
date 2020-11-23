package it.unibo.oop.lab.advanced;

public class DrawNumberViewStdout implements DrawNumberView {
    
    private DrawNumberViewObserver obs;

    public void setObserver(DrawNumberViewObserver observer) {
        this.obs = observer;
    }

    public void start() {
        System.out.println("Starting the game...");
    }

    public void numberIncorrect() {
        System.out.println("Error: number INCORRECT");
    }

    public void result(DrawResult res) {
        System.out.println("Result:" + res.getDescription());
    }

    public void limitsReached() {
        System.out.println("Limits Reached");
    }

    public void displayError(String err) {
        // TODO Auto-generated method stub
    }

}
