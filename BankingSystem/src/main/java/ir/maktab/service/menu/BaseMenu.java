package ir.maktab.service.menu;

import java.util.List;

public class BaseMenu {
	
	public static final String WRONG_NUMBER = "Invalid Number Insert!!";
	public static final String WELCOME = "Welcome to your Page";
	public static final String SUCCESS_OPERATION = "Operation Successes!!!";
	
	
	public static void singlePrintMessage(String message) {
		String text = "";
		if(message.length() > 37) {
			textSplitterSingleMessage(message, text);
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message, "|");
			System.out.println("    +--------------------------------------------+");
		}
	}

	private static void textSplitterSingleMessage(String message, String text) {
		splitInsideText(message, text);
		System.out.println("    +--------------------------------------------+");
	}

	public static <T> void singlePrintMessage(String message, T args) {
		String text = "";
		if(message.length() > 37) {
			splitInsideText(message, text);
			System.out.printf("%5s     %-37s%3s\n", "|", ("     " + args), "|");
			System.out.println("    +--------------------------------------------+");
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message + ": " + args, "|");
			System.out.println("    +--------------------------------------------+");
		}
	}

	private static void splitInsideText(String message, String text) {
		String[] messages;
		messages = message.split(" ");
		System.out.println("    +--------------------------------------------+");
		StringBuilder textBuilder = new StringBuilder(text);
		for (String s : messages) {
			if (textBuilder.length() + s.length() < 37)
				textBuilder.append(s).append(" ");
			else {
				System.out.printf("%5s     %-37s%3s\n", "|", textBuilder, "|");
				textBuilder = new StringBuilder(s + " ");
			}
		}
		text = textBuilder.toString();
		if(!text.isBlank())
			System.out.printf("%5s     %-37s%3s\n", "|", text, "|");
	}

	public static void singleSetMessage(String message) {
		String text = "";
		if(message.length() > 37) {
			textSplitterSingleMessage(message, text);
			System.out.print("           :: ");
		}
		else {
			System.out.println("    +--------------------------------------------+");
			System.out.printf("%5s     %-37s%3s\n", "|", message, "|");
			System.out.println("    +--------------------------------------------+");
			System.out.print("           :: ");
		}
	}
	
	public static void optionMessage(List<String> messages, boolean activeSelectOption) {
		System.out.println("    +----------------------------------------------+");
		for(int index = 0; index < messages.size(); index++)
			System.out.printf("%5s      %-37s%4s\n", "|", (index+1 + ". " + messages.get(index)), "|");
		System.out.println("    +----------------------------------------------+");
		if(activeSelectOption)
			System.out.print("       Select an Option: ");
	}
	
	public static void optionMessageWithTitle(List<String> messages, String title, boolean activeSelectOption) {
		
		int size;
		if(title.length() % 2 == 0) {
			size = 30 - title.length();
		}
		else {
			size = 30 - title.length();
			title += " ";
		}
		StringBuilder str1 = new StringBuilder();
		str1.append("-".repeat(Math.max(0, size / 2)));
		title = String.format("+" + str1 + " %s " + str1 + "+", title);

		title = "    " + title;
		System.out.println(title);
		
		
		for(int index = 0; index < messages.size(); index++)
			System.out.printf("%5s      %-37s%3s\n", "|", (index+1 + ". " + messages.get(index)), "|");
		System.out.println("    +---------------------------------------------+");
		if(activeSelectOption)
			System.out.print("       Select an Option: ");
	}
	
}
