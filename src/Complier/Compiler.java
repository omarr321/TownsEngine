package Complier;

import Complier.Errors.NoFileSet;
import Engine.other.Option;
import Engine.scenes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The compiler class, this takes in a file and converts it to Java code for running. will throw errors if it fines any in the code
 *
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Compiler<T extends Scene> {
    private String filePath;
    private DataMgmt dataMgmt;
    public Compiler() {
        this("");
    }

    public Compiler(String filePath) {
        this.filePath = filePath;
        dataMgmt = new DataMgmt<>();
    }

    /**
     * Sets the filePath for te compiler, this is the file that the compiler will compile.
     * @param filePath - The file to compile
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void compile() throws NoFileSet, IOException {
        if (this.filePath.equals("")) {
            throw new NoFileSet("Error: There is no file set for the compiler to compile");
        }

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        int currLine = 0;
        boolean multilineComment = false;
        while(true) {
            currLine++;
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            if (!(line.equals(""))) {
                if (!(multilineComment)) {
                    if (line.toCharArray()[0] == '>') {
                        if (line.toCharArray()[1] == '>') {
                            multilineComment = true;
                        }
                    } else {
                        this.processCmd(this.convertCommand(line));
                    }
                } else {
                    if (line.substring(line.length()-2, line.length()).equals("<<")){
                        multilineComment = false;
                    }
                }
            }
        }
    }

    private String[] convertCommand(String command) {
        char[] currCommand = command.toCharArray();
        String variableName = "";
        String commandName = "";
        String commandArgs = "";
        String newVar = "0";
        int stepCount = 0;
        for (int i = 0; i < currCommand.length; i++) {
            if (stepCount == 0) {
                Character.compare(currCommand[i], ' ');
                //This steps gets the variable name
                if (Character.compare(currCommand[i], ' ') == 0 || Character.compare(currCommand[i], '.') == 0) {
                    stepCount++;
                } else {
                    variableName = variableName + currCommand[i];
                }
            } else if (stepCount == 1) {
                if (currCommand[i] == '=') {
                    i += 2;
                    newVar = "1";
                } else {
                    commandName = commandName + currCommand[i];
                }
                stepCount = 2;
            } else if (stepCount == 2) {
                if (currCommand[i] != ' ') {
                    commandName = commandName + currCommand[i];
                } else {
                    stepCount = 3;
                }
            } else if (stepCount == 3) {
                commandArgs = commandArgs + currCommand[i];
            }
        }

        return new String[]{newVar, variableName, commandName, commandArgs};
    }

    private void processCmd(String[] cmdArr) {
        switch(cmdArr[0]) {
            case "0":
                if (dataMgmt.hasKey(cmdArr[1])) {
                    this.processSubCmd((T) dataMgmt.getValue(cmdArr[1]));
                } else if (dataMgmt.hasKey_O(cmdArr[1])) {
                    this.processSubCmd(dataMgmt.getValue_O(cmdArr[1]));
                } else {
                    //TODO: Throw variable doesn't exist error
                }
                break;
            case "1":
                if (cmdArr[2].equals("CreateOption")) {
                    dataMgmt.setKey(cmdArr[1], new Option<>(cmdArr[3]));
                } else if (cmdArr[2].equals("CreateScene")) {
                    dataMgmt.setKey(cmdArr[1], new Scene());
                } else {
                    dataMgmt.setKey(cmdArr[1], this.getNewSceneObj(cmdArr[2]));
                }
                break;
            default:
                System.out.print("Command Error");
        }
    }

    private T getNewSceneObj(String cmd) {
        switch(cmd){
            case "CreateStart":
                return (T) new TitleScene<>();
            case "CreateSavePoint":
                return (T) new SavePoint<>();
            case "CreateTextBlock":
                return (T) new TextBlock<>();
            case "CreateDeadEnd":
                return (T) new Deadend<>();
            default:
                //TODO: Throw cmd is incorrect error
        }
        return null;
    }

    private void processSubCmd(T scene) {

    }

    private void processSubCmd(Option option) {

    }
}
