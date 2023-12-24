package Engine.other;

import java.util.ArrayList;

/**
 * This class has some utility methods that are very useful in the engine
 * @author Omar M. Radwan
 * @version 1.0.0
 */
public class Toolbox {

	/**
	 * This uses {@link #breakStringUp(String, int) breakStringUp} method with a length of 100
	 * @param string - String to split
	 * @return - A new string with newlines that splits the string up
	 */
	public static String breakStringUp(String string) {
		return Toolbox.breakStringUp(string, 100);
	}

	/**
	 * This breaks a string up by the length provided
	 * @param string - String to split
	 * @param length - The target length to split by
	 * @return - A new string with newlines that splits the string up
	 */
	public static String breakStringUp(String string, int length) {
		ArrayList<String> all = new ArrayList<>();

        String[] newLine = string.split("\n");

        for (String item : newLine) {
            while(true) {
                if (item.length() <= length) {
                    all.add(item);
                    break;
                } else {
                    Boolean flag = false;
                    for (int i = length; i > 0; i--) {
                        if (item.charAt(i) == ' ') {
                            all.add(item.substring(0, i));
                            item = item.substring(i+1);
                            break;
                        }
                    }
                }
            }
        }
        String[] temp = (String[]) all.toArray(new String[all.size()]);
        String tempRe = "";
        for (String line : temp) {
        	tempRe = tempRe + line + "\n";
        }
        return tempRe;
	}

	/**
	 * This uses {@link #printLine(String, int) printLine} method with an interval of 50
	 * @param string - The string to print
	 */
	public static void printLine(String string) {
		Toolbox.printLine(string, 50);
	}

	/**
	 * This prints the string provided line by line with a delay of the interval between each line.
	 * @param string - The string to print
	 * @param interval - The interval to wait between each line
	 */
	public static void printLine(String string, int interval) {
		String[] stringArr = string.split("\n");
		for(String currLine : stringArr) {
			System.out.print(currLine);
			Toolbox.sleep(interval);
		}
	}

	/**
	 * This uses {@link #printChar(String, int) printChar} method with an interval of 50
	 * @param string - The string to print
	 */
	public static void printChar(String string) {
		Toolbox.printChar(string, 50);
	}

	/**
	 * This prints the string provided char by char with a delay of the interval between each char
	 * @param string - The string to print
	 * @param interval - The interval to wait between each char
	 */
	public static void printChar(String string, int interval) {
		char[] charArr = string.toCharArray();
		for(char currChar : charArr) {
			System.out.print(currChar);
			Toolbox.sleep(interval);
		}
	}

	/**
	 * This sleeps the thread for the set number milliseconds
	 * @param millSec - How long to sleep for in milliseconds
	 */
	private static void sleep(int millSec) {
		try {
			Thread.sleep(millSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
