package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.century.finalproject.BTNode;

public class CSVReader {

	private static final int DATA_WIDTH = 4;
	
	/**TODO
	 * @description 
	 * 	Takes a String that represents the location on disk of a CSV file that contains the stored question flow and then loads it into a tree 
	 * @param target
	 * 	the target location on disk of the CSV file
	 * @return
	 * @throws IOException
	 * 	can throw a IOException if the target file can't be read
	 */
	public static BTNode readFile(String target) throws IOException{
		
		String[][] csvArray = readIn(target);
		BTNode output = createTree(csvArray);		
		return output;
		
	}

	/**
	 * @description
	 * 	takes a 2d array of strings and creates the data tree
	 * @param cVSArray
	 * @return
	 */
	protected static BTNode createTree(String[][] csvArray) {
		BTNode[] nodeArray = new BTNode[csvArray.length];
		
		for(int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = new BTNode(csvArray[i][1]);
		}
		
		for(int i = 0; i < nodeArray.length; i++) {
			if (csvArray[i][2] != null) {
				nodeArray[i].setLeft(nodeArray[findLink(csvArray[i][2])]);
			}
			if (nodeArray[findLink(csvArray[i][3])] != null) {
				nodeArray[i].setRight(nodeArray[findLink(csvArray[i][3])]);
			}		
		}
		
		return nodeArray[0];
	}


	protected static int findLink(String input) {
		int output = 0;
		input = input.substring(1);
		try {
			output = Integer.parseInt(input);
		}
		catch(Exception e){
			return output;
		}
		return output;
	}

	/**
	 * @description
	 * 	loads the target CVS file into a 2D array
	 * @param target
	 * @return
	 * @throws FileNotFoundException 
	 */
	protected static String[][] readIn(String target) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(target));
		Stack<String> initalReadStack = new Stack<String>();
		String line;
		while((line = reader.readLine()) != null) {
			initalReadStack.push(line);
		}
		
		String[][] finalArray = parseStack(initalReadStack.clone(), ",");
		
		reader.close();
		return finalArray;
	}


	/**
	 * @description
	 * 	parses stack of strings into a 2D array based on the passed separator
	 * @param stack
	 * @param separator
	 * @post
	 * 	will destroy the stack you pass it
	 * @return
	 */
	protected static String[][] parseStack(Stack<String> stack, String separator) {
		int stackSize = stack.getCount();
		String[][] output = new String[stackSize][DATA_WIDTH];
		stack = stack.getReversed();
		String line;
		for(int i = 0; i < stackSize; i++) {
			line = stack.pop();
			String[] heldArray = line.split(separator);
			for(int j = 0; j < heldArray.length; j++ ) {
				output[i][j] = heldArray[j];
			}
		}
		return output;
	}
}
