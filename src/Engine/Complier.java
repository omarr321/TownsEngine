package Engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Complier {
    private static StoryObject[] list = new StoryObject[1000];
    private static int currLine = 1;
    private static boolean load = false;
    private static File lFile = null;
    private static boolean save = false;
    private static File sFile = null;
    private static boolean playerSave = false;
    private static File psFile = null;
    private static boolean compile = false;
    private static File cFile = null;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Can not run with no args!");
            System.out.println("Use -h or -help to see a help page.");
        } else {
            for(int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-h", "-help" -> {
                        System.out.println("HELP PAGE:");
                        System.out.println("(-h  | -help)                     : Shows this page");
                        System.out.println("(-c  | -compile)    <PATH> <NAME> : Compiles the file at the location");
                        System.out.println("(-s  | -save)       <PATH> <NAME> : Saves the compiled version of the script at the location with the name");
                        System.out.println("(-l  | -load)       <PATH> <NAME> : Loads a compiled version of the script from the location");
                        System.out.println("(-ps | -playerSave) <PATH> <NAME> : Loads a player save from the location");
                        System.exit(0);
                    }
                    case "-l", "-load" -> {
                        Complier.lFile = new File(args[i + 1] + File.separator + args[i + 2]);
                        i = i + 2;
                        Complier.load = true;
                    }
                    case "-s", "-save" -> {
                        Complier.sFile = new File(args[i + 1] + File.separator + args[i + 2]);
                        i = i + 2;
                        Complier.save = true;
                    }
                    case "-ps", "-playerSave" -> {
                        Complier.psFile = new File(args[i + 1] + File.separator + args[i + 2]);
                        i = i + 2;
                        Complier.playerSave = true;
                    }
                    case "-c", "-compile" -> {
                        Complier.cFile = new File(args[i + 1] + File.separator + args[i + 2]);
                        i = i + 2;
                        Complier.compile = true;
                    }
                    default -> {
                        System.out.println("Error: Unknown command \"" + args[i] + "\"!");
                        System.exit(0);
                    }
                }
            }
            if (Complier.load) {
                System.out.println("Error: Loading is not implemented!");
            }

            if (Complier.save) {
                System.out.println("Error: Saving is not implemented!");
            }

            if (Complier.playerSave) {
                System.out.println("Error: Loading a player save is not implemented!");
            }

            if (Complier.compile) {
                compile(Complier.cFile);
            }
        }
    }

    private static void compile(File file) {
        Scanner sc;
        try {
            if (!(file.exists())) {
                System.out.println("Error: File \"" + file + "\" does not exist!");
                System.exit(0);
            }

            sc = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File \"" + file + "\" does not exist!");
            System.exit(0);
        }
    }

    private static void checkCommand(String command){

    }
}
