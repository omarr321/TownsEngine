package Complier;

import Complier.Errors.InvaildCommand;
import Complier.Errors.NoFileSet;
import Complier.Errors.UnknownVariable;
import Engine.other.Option;
import Engine.other.Player;
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

    private Player player;
    public Compiler() {
        this("");
    }

    public Compiler(String filePath) {
        this.filePath = filePath;
        this.dataMgmt = new DataMgmt<>();
        this.player = new Player("PLAYER");
    }

    /**
     * Sets the filePath for te compiler, this is the file that the compiler will compile.
     * @param filePath - The file to compile
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void compile() throws NoFileSet, IOException, InvaildCommand, UnknownVariable {
        System.out.println("Compiling...");
        if (this.filePath.equals("")) {
            throw new NoFileSet("ERROR: There is no file set for the compiler to compile");
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
                        this.processCmd(this.convertCommand(line), currLine, line);
                    }
                } else {
                    if (line.substring(line.length()-2, line.length()).equals("<<")){
                        multilineComment = false;
                    }
                }
            }
        }
        System.out.println("DONE!");
        System.out.println("Running...");
        this.player.play();
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

    private void processCmd(String[] cmdArr, int lineNum, String cmd) throws InvaildCommand, UnknownVariable {
        switch(cmdArr[0]) {
            case "0":
                if (dataMgmt.hasKey(cmdArr[1])) {
                    if (dataMgmt.getValueType(cmdArr[1]) instanceof TitleScene<?>) {
                        switch(cmdArr[2]) {
                            case "addTitle":
                                ((TitleScene) dataMgmt.getValue(cmdArr[1])).setTitle(this.removeQuotes(cmdArr[3]));
                                break;
                            case "addDesc":
                                ((TitleScene) dataMgmt.getValue(cmdArr[1])).setText(this.removeQuotes(cmdArr[3]));
                                break;
                            case "addNextScene":
                                Scene temp = dataMgmt.getValue(this.removeQuotes(cmdArr[3]));
                                ((TitleScene) dataMgmt.getValue(cmdArr[1])).addStartScene(this.checkVar(dataMgmt.getValue(this.removeQuotes(cmdArr[3])), lineNum, cmd), this.player);
                                break;
                            case "addCredit":
                                ((TitleScene) dataMgmt.getValue(cmdArr[1])).setCredit(this.removeQuotes(cmdArr[3]));
                                break;
                            default:
                                throw new InvaildCommand("ERROR AT LINE " + lineNum + ": UNKNOWN COMMAND - " + cmd);
                        }
                    } else if (dataMgmt.getValueType(cmdArr[1]) instanceof SavePoint<?>) {
                        switch(cmdArr[2]) {
                            case "addText":
                                ((SavePoint) dataMgmt.getValue(cmdArr[1])).setText(this.removeQuotes(cmdArr[3]));
                                break;
                            case "link":
                                ((SavePoint) dataMgmt.getValue(cmdArr[1])).setNextScene(this.checkVar(dataMgmt.getValue(this.removeQuotes(cmdArr[3])), lineNum, cmd));
                                break;
                            default:
                                throw new InvaildCommand("ERROR AT LINE " + lineNum + ": UNKNOWN COMMAND - " + cmd);
                        }
                    } else if (dataMgmt.getValueType(cmdArr[1]) instanceof TextBlock<?>) {
                        switch(cmdArr[2]) {
                            case "addText":
                                ((TextBlock) dataMgmt.getValue(cmdArr[1])).setText(this.removeQuotes(cmdArr[3]));
                                break;
                            case "link":
                                ((TextBlock) dataMgmt.getValue(cmdArr[1])).setNextScene(this.checkVar(dataMgmt.getValue(this.removeQuotes(cmdArr[3])), lineNum, cmd));
                                break;
                            default:
                                throw new InvaildCommand("ERROR AT LINE " + lineNum + ": UNKNOWN COMMAND - " + cmd);
                        }
                    } else if (dataMgmt.getValueType(cmdArr[1]) instanceof Deadend<?>) {
                        switch(cmdArr[2]) {
                            case "addText":
                                ((Deadend) dataMgmt.getValue(cmdArr[1])).setText(this.removeQuotes(cmdArr[3]));
                                break;
                            default:
                                throw new InvaildCommand("ERROR AT LINE " + lineNum + ": UNKNOWN COMMAND - " + cmd);
                        }
                    } else if (dataMgmt.getValueType(cmdArr[1]) instanceof Scene) {
                        switch(cmdArr[2]) {
                            case "addText":
                                ((Scene) dataMgmt.getValue(cmdArr[1])).setText(this.removeQuotes(cmdArr[3]));
                                break;
                            case "addOption":
                                ((Scene) dataMgmt.getValue(cmdArr[1])).addOption(this.checkVar_O(dataMgmt.getValue_O(this.removeQuotes(cmdArr[3])), lineNum, cmd));
                                break;
                            default:
                                throw new InvaildCommand("ERROR AT LINE " + lineNum + ": UNKNOWN COMMAND - " + cmd);
                        }
                    } else {
                        throw new UnknownVariable("ERROR AT LINE " + lineNum + ": UNKNOWN VARIABLE - " + cmd);
                    }
                } else if (dataMgmt.hasKey_O(cmdArr[1])) {
                    switch(cmdArr[2]) {
                        case "addText":
                            dataMgmt.getValue_O(cmdArr[1]).setText(this.removeQuotes(cmdArr[3]));
                            break;
                        case "link":
                            dataMgmt.getValue_O(cmdArr[1]).setScene(this.checkVar(dataMgmt.getValue(this.removeQuotes(cmdArr[3])), lineNum, cmd));
                            break;
                        default:
                            throw new InvaildCommand("ERROR AT LINE " + lineNum + ": COMMAND - " + cmd);
                    }
                } else {
                    throw new UnknownVariable("ERROR AT LINE " + lineNum + ": UNKNOWN VARIABLE - " + cmd);
                }
                break;
            case "1":
                if (cmdArr[2].equals("CreateOption")) {
                    dataMgmt.setKey(cmdArr[1], new Option<>());
                } else {
                    dataMgmt.setKey(cmdArr[1], this.getNewSceneObj(cmdArr[2], cmdArr[1]));
                }
                break;
            default:
                System.out.print("Command Error");
        }
    }

    private T getNewSceneObj(String cmd, String varName) {
        switch(cmd){
            case "CreateScene":
                this.dataMgmt.setKeyType(varName, new Scene());
                return (T) new Scene();
            case "CreateStart":
                TitleScene temp = new TitleScene<>();
                this.player.setStartScene(temp);
                this.dataMgmt.setKeyType(varName, new TitleScene<>());
                return (T) temp;
            case "CreateSavePoint":
                this.dataMgmt.setKeyType(varName, new SavePoint<>());
                return (T) new SavePoint<>();
            case "CreateTextBlock":
                this.dataMgmt.setKeyType(varName, new TextBlock<>());
                return (T) new TextBlock<>();
            case "CreateDeadEnd":
                this.dataMgmt.setKeyType(varName, new Deadend<>());
                return (T) new Deadend<>();
        }
        return null;
    }

    private Scene checkVar(Scene variable, int lineNum, String cmd) throws UnknownVariable {
        if (variable == null) {
            throw new UnknownVariable("ERROR AT LINE " + lineNum + ": UNKNOWN VARIABLE - " + cmd);
        }
        return variable;
    }

    private Option checkVar_O(Option variable, int lineNum, String cmd) throws UnknownVariable {
        if (variable == null) {
            throw new UnknownVariable("ERROR AT LINE " + lineNum + ": UNKNOWN VARIABLE - " + cmd);
        }
        return variable;
    }

    private String removeQuotes(String str) {
        String temp = str;
        if (temp.charAt(0) == '"') {
            temp = temp.substring(1);
        }
        if (temp.charAt(temp.length()-1) == '"') {
            temp = temp.substring(0, temp.length()-1);
        }

        return temp;
    }
}
