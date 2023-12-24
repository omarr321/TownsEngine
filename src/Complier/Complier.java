package Complier;

import Complier.Errors.NoFileSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The compiler class, this takes in a file and converts it to Java code for running. will throw errors if it fines any in the code
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Complier {
    private String filePath;
    public Complier() {
        this("");
    }

    public Complier(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the filePath for te compiler, this is the file that the compiler will compile.
     * @param filePath - The file to compile
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void Compile() throws NoFileSet, IOException {
        if (this.filePath.equals("")) {
            throw new NoFileSet("Error: There is no file set for the compiler to compile");
        }

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        while(true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            //TODO: See if it comment, see if it a command, or a mutiline comment
        }
    }

    public void ConvertCommand(String command) {

    }
}
