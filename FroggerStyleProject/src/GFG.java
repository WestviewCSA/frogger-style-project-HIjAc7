

//Java Program to extract the list of Fonts 
import java.awt.*;
import java.util.ArrayList; 

//Driver class to check available Fonts in AWT 
class GFG { 
	// Main Function 
	public static ArrayList<String> getfonts() 
	{ 
		System.out.println("To Know the available font family names"); 
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 

		System.out.println("Getting the font family names"); 

		// Array of all the fonts available in AWT 
		String fonts[] = ge.getAvailableFontFamilyNames(); 

		// Getting the font family names 
		ArrayList<String> font = new ArrayList<String>();
		for (String i : fonts) { 
			 font.add(i);
		}
		return font;
	} 
}

