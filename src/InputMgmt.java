import Complier.Compiler;
import Complier.Errors.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMgmt {
    private static final String ENGINE_NAME = "TownsEngine";
    private static final String ENGINE_VERSION = "v1.0.0";
    private static String fPath = "";
    private static boolean pathSet = true;
    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        Pattern pattern = Pattern.compile("\\X*\\.town$");
        Scanner userIn = new Scanner(System.in);

        for(int i = 0; i < args.length; i+=2) {
            switch (args[i]) {
                case "--help":
                case "-h":
                    System.out.println("Help Page:");
                    System.out.println("-h / --help  | Shows the help page (this page)");
                    System.out.println("-i / --input | Set the input file for the compiler to use");
                    System.exit(0);
                case "-i":
                case "--input":
                    fPath = args[i+1];
            }
        }

        System.out.println(ENGINE_NAME + " " + ENGINE_VERSION);
        while(true) {
            if (fPath.equals("") || pathSet == false) {
                pathSet = false;
                System.out.println("What is the file path for the town file? (.town)");
                System.out.println("NOTE: Type \"quit\" to exit the program.");
                System.out.print(">>>");
                fPath = userIn.nextLine();
                if (fPath.toLowerCase().equals("quit")) {
                    System.exit(0);
                }
            }
            File f = new File(fPath);
            if(f.exists() && f.isFile()) {
                Matcher matcher = pattern.matcher(f.toString());
                if (matcher.find()) {
                    compiler.setFilePath(f.toString());
                    try {
                        compiler.compile();
                        break;
                    } catch (NoFileSet e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (InvaildCommand e) {
                        System.out.println(e.getMessage());
                    } catch (UnknownVariable e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Error: Not a valid .town file!");
                }
            } else {
                System.out.println("Error: That file does not exist!");
            }
            if (pathSet) {
                break;
            }
        }
    }
}
