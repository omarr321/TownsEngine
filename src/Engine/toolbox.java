package Engine;

import java.text.BreakIterator;
import java.util.ArrayList;

public class toolbox {
	public static String formatString(String string) {
		return toolbox.formatString(string, 100);
	}
	
	public static String formatString(String string, int length) {
		BreakIterator bi = BreakIterator.getWordInstance();
		ArrayList<String> stringArrList = new ArrayList<>();
		bi.setText(string);
		if (bi.isBoundary(length-1)) {
			
		}
		return string;
	}
	
	public static void printLine(String string) throws InterruptedException {
		toolbox.printLine(string, 50);
	}
	
	public static void printLine(String string, int interval) throws InterruptedException {
		String[] stringArr = string.split("\n");
		for(String currLine : stringArr) {
			System.out.print(currLine);
			Thread.sleep(interval);
		}
	}
	
	public static void printChar(String string) throws InterruptedException {
		toolbox.printChar(string, 50);
	}
	
	public static void printChar(String string, int interval) throws InterruptedException {
		char[] charArr = string.toCharArray();
		for(char currChar : charArr) {
			System.out.print(currChar);
			Thread.sleep(interval);
		}
	}
}
