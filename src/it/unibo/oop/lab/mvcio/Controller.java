package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {
    static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private File file;

    public Controller() {
        this.file = new File(Controller.PATH + "output.txt");
    }
/**
 *      This method set a new File as a current file
 *
 *      @param newFilePath 
 *              the path of the new File.
 * 
 * 
 * */
    public void setFile(final String newFilePath) {
        this.file = new File(newFilePath);
    }

    public String getFile() {
        return this.file.getName();
    }

    public String getPath() {
        return this.file.getPath();
    }

    public void writeOnFile(final String textToWrite) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(file.getPath())) {
           ps.print(textToWrite);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
