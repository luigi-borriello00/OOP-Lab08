package it.unibo.oop.lab.advanced;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    
    static final String PATH = "C:\\Users\\Luigi\\Desktop\\Uni\\2° Anno\\Programmazione a oggetti\\week08\\lab\\OOP-Lab08\\res\\config.yml";
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final List<DrawNumberView> views = new ArrayList<>();

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() {
        final Map<String, String> configurations = new HashMap<>();
        int min, max, attempts;
        final File file = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
           final Iterator iter = br.lines().iterator();
           while (iter.hasNext()) {
               final String temp = (String) iter.next();
               final StringTokenizer st = new StringTokenizer(temp, ": ");
               final String config = st.nextToken();
               final String value = st.nextToken();
               configurations.put(config, value);
           }
            min = Integer.parseInt(configurations.get("minimum"));
            max = Integer.parseInt(configurations.get("maximum"));
            attempts = Integer.parseInt(configurations.get("attempts"));
        } catch (IOException e) {
            min = this.MIN;
            max = this.MAX;
            attempts = this.ATTEMPTS;
        } 
        this.model = new DrawNumberImpl(min, max, attempts);
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new DrawNumberViewLog());        // Adding the new two views
        this.views.add(new DrawNumberViewStdout()); 
        for (final var x : views) {
            x.setObserver(this);
            x.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final var x : this.views) {
                x.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final var x : this.views) {
                x.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final var x : this.views) {
                x.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
