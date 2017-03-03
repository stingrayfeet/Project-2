//Do not edit this imports
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Scanner;

/**
 * STUDENTS FILL IN PROPER DOCUMENTATION HERE
 * @author Richard Blake
 * COP 3502 Section Number: 
 *
 */
public class PoetryDecoder {


	/**
	 * This main method asks the user to enter input. Then, it calls
	 * the decode method on that input. 
	 * STUDENTS: DO NOT EDIT this main method. Do all of your work
	 * in the methods below it. Note: This is the only method that has System.out output. 
	 * No other method prints to the console! (You may of course print to console for your own
	 * debugging purposes, but remove that debug code before you submit your program.) 
	 * @param args This main method does not take command line args.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//This prompt to the user is intentionally vague to avoid
		//incrimating those who use this program. 
		System.out.println("Please enter your input: ");
		String hex = sc.nextLine();

		//STUDENTS: Your decode method must return a fully formatted String,
		//which will be output here. 
		System.out.print(decode(hex));
		sc.close();
	}

	/**
	 * The decode method 
	 * STUDENTS: This decode method is where all your work happens. 
	 * To keep it clean and simple, it calls other methods.
	 * @param hex The string of hex digits that is to be decoded.
	 * @return A String representing an entire decoded poem of English words. 
	 * (The returned string includes line breaks and spacing.) 
	 */
	public static String decode(String hex) {
		String letters = toEnglishLetters(hex);
		String[] shortest = findWordsOfLength(letters, 4);
		String[] medium = findWordsOfLength(letters, 5);
		String[] longest = findWordsOfLength(letters, 6);
		String formattedPoem = formatPoem(shortest, medium, longest);


		return formattedPoem;
		//STUDENTS: First, translate the hex string into English characters. 
		//Use the toEnglishCharacters() method to do that. 

		//STUDENTS: Now that you have the String of English characters, search 
		//it to extract all four-letter words and store those in one array. 
		//Search it again to extract all five-letter words and store in a different array.
		//Search it one final time to extract all six-letter words and store in a different array.
		//To do this, call your findWordsOfLength() method three times with the appropriate parameters.

		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile.
	}


	/**
	 * STUDENTS: You must write this method. Fill in its body. But do not change its header! 
	 * 
	 * This method takes a String of hex characters and, chunking them two at a time,
	 * finds a corresponding English letter according to the ASCII table. If the String of 
	 * hex characters is of odd length, the final hex character is IGNORED. 
	 * Note that the pairs of hex characters may not precisely correspond to ASCII letter values--
	 * that would be too easy to track! Instead, you must translate the hex pair into an ASCII English
	 * letter (no punctuation or digits) using character arithmetic. More details on that: 
	 * 
	 * To translate any pair of hex digits to English letters, here is the procedure:
	 * Take every two hex digits and convert it to its decimal form. If this number is inside the ASCII range 
	 * for an English letter, either lower case or upper case, then you're done converting to a letter.
	 * If it is not inside the ASCII range for letters, mod the value by 26 and map it to an ASCII letter based on 
	 *  the result. mod 0='a'; mod 1 = 'b'; mod 2='c' and so on. You do not need
	 *  a big switch statement or if's. Just use character arithmetic. 
	 *  
	 * @param String hex: String of hex characters. If the String is of odd length, the final hex
	 * character is ignored and not translated.
	 * 
	 * @return String: the String of English letters. Each letter came from a pair of hex
	 * digits in the original input String. 
	 */
	public static String toEnglishLetters(String hex){

		//STUDENTS: delete this return statement and replace it with your own.
		//It's just a placeholder so that your method can compile.
		hex.toLowerCase();
		String counter = "";
		int decimalValue = 0;
		String englishLetters = "";

		//creates new loop to run through hex and store it in counter
		for (int i = 0; i < hex.length(); i++) {
			//counter storing its value plus the value of hex at character i
			counter += hex.charAt(i);
			//conditional which catches when counter has a length of 2;
			if (i % 2 != 0 && i != 0) {
				//converts base 16 to base 10 (and changes decimal values which aren't in alphabet ASCII range) 
				decimalValue = Integer.valueOf(counter, 16);
				if (decimalValue < 97 || decimalValue > 122) {
					decimalValue = (decimalValue % 26) + 97;
				}
				//stores itself plus the character representation of the ASCII values given by decimalValue
				englishLetters += Character.toString((char) decimalValue);
				//resets counter to an empty string
				counter = "";
			}
		}
		//returns string of characters representing the ASCII values from decimalValue
		return englishLetters;
	}


	/**
	 * STUDENTS: Complete this method. Do NOT change its header. 
	 * This method searches an input String of English letters, and returns all words of 
	 * the specified length that occur in the String. A "sliding window" will be considered, so 
	 * a given character could occur in more than one word that is found. This is desirable.
	 * 
	 * The size of the array you return depends on the size of the string passed and the word size to be found. 
	 * The array must be able to hold the maximum number of words of the size wordSize that could potentially be found in
	 * the input string. You do not have to first check how many real words there are. Instead, just compute how many words
	 * there *could* be. For example, a letter string of length 15 could have 13 possible three-letter words (think of
	 * a sliding window). As another example, a letter string of size 100 could have 97 possible four-letter words. Make
	 * a simple arithmetic expression to decide the size of your array at the beginning of this method.
	 * 
	 * DO NOT make the array hold a random or just very large number of words: so, do not set the array to a large number 
	 * like 500. You will lose points if you do this. 
	 * Also, you must fill up the array in the order that you encounter valid words of that length. So the first word 
	 * found goes to index 0, second word goes to index 1, etc. The array does not need to be completely filled up, 
	 * it just need to store all the words found. Thus the remaining elements of the array must be null.
	 * 
	 * For example, if you created an array of size 97 and you only found five valid words to store in it, you would have
	 * index 0, 1, 2, 3, and 4 filled up with valid words, and indices 5 through 96 would be null. 
	 * 
	 * @param String letters: A string of English letters
	 * @param String wordSize: The size of words to be extracted from the input String
	 * @return String[]: Contains a word in each element. Elements can be null if no words are found.  
	 */
	public static String[] findWordsOfLength(String letters, int wordSize) {
		String[] wordsOfLengthX = new String[0];


		//initialize String array, wordHolder (which holds Strings of wordSize), and k (which assigns valid words to index[k] and then increments k)
		//creates array the size of letters - (wordSize - 1)
		if (letters.length() >= 5) {
			wordsOfLengthX = new String[letters.length() - (wordSize - 1)];	
		}

		String wordHolder = "";
		int k = 0;

		//initializes for loop which runs through String letters and stores the substring from i to i + wordSize in String wordHolder
		for (int i = 0; i <= (letters.length() - wordSize); i++) {
			//System.out.println("DEBUG; i = " + i);
			//checks if wordHolder can safely store letters' substring from i to i + wordSize
			if (i < (letters.length() - wordSize)) {
				wordHolder = letters.substring(i, i + wordSize);
				//System.out.println("DEBUG: wordHolder = " + wordHolder);
			}

			//executes when the substring's 2nd parameter throws OOBE, and stores substring from i to the last index of letters.
			//It then concatenates onto wordHolder the last character in letters
			if (i == (letters.length() - wordSize)) {
				//wordHolder = letters.substring(i, (letters.length() - 1)) + letters.charAt(letters.length() - 1);
				wordHolder = letters.substring(letters.length() - wordSize);
				//System.out.println("ELSE CASE: wordHolder = " + wordHolder);
			}

			//if the current value of wordHolder is a word (determined by provided method), wordsOfLengthX stores wordHolder's value and assigns it to index k
			//k is then incremented to prepare for the next index
			if (isWord(wordHolder) == true) {
				wordsOfLengthX[k] = wordHolder;
				k++;
			}

			//resets wordHolder to an empty string
			wordHolder = "";
		}


		//returns array populated with total real words of length wordSize
		return wordsOfLengthX;
	}

	/**
	 * This method formats a poem according to Poetry Movement specifications. 
	 * @param Takes three arrays of Strings of shortest, medium, and longest lengths. 
	 * @return Returns a String with line breaks and tabs as needed for poetic formatting. 
	 */
	public static String formatPoem(String[] shortest, String[] medium, String[] longest){
		//initializes variables for counting and a string to hold the final formatted poem
		String formatString = "";
		//These ints keep track of the individual indexes of the 3 arrays
		int shortestC = 0;
		int mediumC = 0;
		int longestC = 0;

		//for loop to search the 3 arrays (from longest to shortest)
		for (int i = 0; i < shortest.length; i++) {

			//checks if array longest has a value at index 'longestC'
			if (longest.length > longestC && longest[longestC] != null) {
				formatString += longest[longestC] + " ";
				longestC++;

			}

			//checks if array medium has a value at index 'mediumC'
			if (medium.length > mediumC && medium[mediumC] != null) {
				formatString += medium[mediumC] + " ";
				mediumC++;

			}

			//checks if array shortest has a value at index 'shortestC'
			if (shortest.length > shortestC && shortest[shortestC] != null) {
				formatString += shortest[shortestC] + " ";
				shortestC++;

			}

			//after all if statements have been attempted, a newLine statement is implemented 
			//to shift the next iteration of words to the next line
			formatString += "\n";

			//for loop to keep track of how many tabs the successive nextLine requires
			for (int k = 0; k <= i; k++) {
				formatString += "\t";
			}

		}

		//returns formatted poem in a String
		return formatString;

	}



	/* *******************************************************************************
	STUDENTS -- BELOW THIS POINT ARE METHODS PROVIDED FOR YOU. DO NOT EDIT THEM. 
	USE THEM AS INDICATED IN THEIR DESCRIPTIONS. 
	 ********************************************************************************/

	/**
	 * This method checks whether the given String occurs in a dictionary of English.
	 * STUDENTS: DO NOT MODIFY THIS METHOD IN ANY WAY. IT IS PROVIDED FOR YOU. 
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param possWord The word to be checked.
	 * @return boolean Returns true if the word given is an English word, false otherwise.
	 */
	private static boolean isWord(String possWord) {
		boolean isWord = true;
		try {
			//connect to the URL. 
			String s = getUrl(possWord);
			Document d = Jsoup.connect(s).timeout(6000).get();
			Elements tdTags = d.select("h3");

			// Loop over all tdTags, in this case: the h3 tag 
			for( Element element : tdTags ){
				String check = element.toString();

				//Wordnet has a special h3 tag that appears only if the word is not in the dictionary
				//We search for this tag. If it is found, then the word searched is not in the dictionary
				if(check.equals("<h3>Your search did not return any results.</h3>") ){
					isWord = false;
				}
			}
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		return isWord;
	}

	/**
	 * This is a helper method that the teaching staff code uses. 
	 * STUDENTS: DO NOT EDIT THIS METHOD.
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param String: search 
	 * @return A string containing the URL for the wordnet search.
	 */
	private static String getUrl(String search) {
		//Standard URL for wordnet to search
		String url = "http://wordnetweb.princeton.edu/perl/webwn?s=";
		String newURL = null;
		try {
			//Get new page from word wordnet and get its location
			Document doc = Jsoup.connect(url + search).timeout(6000).get();
			newURL = doc.location().toString();
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		//Return the string of the new URL. 
		return (newURL);
	}

}

