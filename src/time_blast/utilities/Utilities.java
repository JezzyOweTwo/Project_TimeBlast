package time_blast.utilities;
import java.util.*;

public interface Utilities {
	static Scanner scan = new Scanner(System.in);  		// Create a Scanner object

	default int response(String question) { 
		ArrayList<String> options = optionArrayGenerator(question);
		return response(options.remove(0),options);
	}
	
	default <T> int response(String message,ArrayList<T> list) {
		// print message and options
		System.out.println(message);
		for(T t : list) {
			System.out.println(t.toString());
		}
		int input=0;
		do {
			input = Integer.parseInt(scan.nextLine());	// input from the user	
		} while(input<1||input>list.size());							
		return input-1;	
	}
	
	// this method returns an array of the original question and then all options
	private ArrayList<String> optionArrayGenerator(String question){
		ArrayList<String> options = new ArrayList<>(Arrays.asList(question.split("\n")));
		return options;
	}
}	

class ScrollingText {
	private static int scrollSpeed=20;
	
	public static void scroll(String title){
		System.out.print("\n");
		for (int i=0;i<title.length();i++) {
			System.out.print(title.substring(0,1)); 
	        try {Thread.sleep(scrollSpeed);}
	        catch(InterruptedException e){System.out.println(e);}	
		}
		System.out.print("\n");
	}
	
	public static void setScrollSpeed(int speed){scrollSpeed = speed;}
	public static int getScrollSpeed() {return scrollSpeed;}
}
