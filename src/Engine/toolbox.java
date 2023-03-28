package Engine;

import java.util.ArrayList;

public class toolbox {
	public static String breakStringUp(String string) {
		return toolbox.breakStringUp(string, 100);
	}
	
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
	
	public static void printLine(String string) {
		toolbox.printLine(string, 50);
	}
	
	public static void printLine(String string, int interval) {
		String[] stringArr = string.split("\n");
		for(String currLine : stringArr) {
			System.out.print(currLine);
			toolbox.sleep(interval);
		}
	}
	
	public static void printChar(String string) {
		toolbox.printChar(string, 50);
	}
	
	public static void printChar(String string, int interval) {
		char[] charArr = string.toCharArray();
		for(char currChar : charArr) {
			System.out.print(currChar);
			toolbox.sleep(interval);
		}
	}
	
	private static void sleep(int millSec) {
		try {
			Thread.sleep(millSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
