import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;


/* Recursive Descent Parser for the following grammar
 * 
 * validateFunctionSignatures -> checkKeyword checkFunctionName checkParameters checkFunctionSignature
 * checkKeyword -> isEmpty | keyword == "function"
 * checkFunctionName -> isEmpty | functionName == "func1" | "func2" | "func3"
 * checkParameters -> isEmpty | checks for left parentheses |checkRemainingParameters 
 * checkRemainingParameters -> Correct example: a : integer )
 * checkFunctionSignature -> isEmpty | checks for left bracket | checkRemainingSignature
 * checkRemainingSignature -> Correct example: a is 5 ; ]
 * isEmpty -> storeToken.size() == 0
 * 
 */
public class Functions{
	private Queue<String> storeToken = new LinkedList<>();
	private int counter = 0;
	private boolean isRightParentheses = false;
	private boolean isRightBracket = false;
	private final int parameterCount = 4;
	
	Functions() {}
	
	Functions(Queue<String> storeToken){
		this.storeToken = storeToken;
	}
	
	public void validateFunctionSignatures(){
		if(checkKeyword() == false){
			System.out.println("Expected : function");
		}
		else{
			if(checkFunctionName() == false){
				System.out.println("Error in function name");
			}
			else{
				if(checkParameters()){
					if(checkFunctionSignature()){
						System.out.println("Success");
					}
				}
			}
		}
	}
	
	public boolean isEmpty(){
		if(storeToken.size() == 0){
			return true;
		}
		return false;
	}
	
   /**
   * Checks for keyword --> "function".
   */ 
	public boolean checkKeyword(){
		String head = storeToken.peek();
		if(!isEmpty()){
			if(head.equals("function")){
				storeToken.remove();
				return true;
			}
		}
		return false;
	}
	
   /**
   * Checks for function name --> "func1" or "func2" or "func3".
   */
	public boolean checkFunctionName(){
		String head = storeToken.peek();
		if(!isEmpty()){
			if(head.equals("func1") || head.equals("func2") || head.equals("func3")){
				storeToken.remove();
				return true;
			}
		}
		return false;
	}
	
   /**
   * Checks for correct parameters
   * 
   * Example:
   * ( a : real , b : integer , c : string )
   */
	public boolean checkParameters(){
		String head = storeToken.peek();
				
		if(isEmpty() || !head.equals("(")){
			System.out.println("Expected : (");
			return false;
		}
		storeToken.remove();
		head = storeToken.peek();
		counter++;
		
		if(isEmpty()){
			System.out.println("Expected : )");
			return false;
		}
		if(!head.equals(")")){
			return checkRemainingParameters();
		}
		else{
			storeToken.remove();
			counter = 0;
			return true;
		}
	}
	
   /**
   * Checks for correct remaining variables and right parentheses
   * 
   * Example:
   * a : real , b : integer , c : string )
   */
	private boolean checkRemainingParameters(){
		int size = storeToken.size();
		for(int i = 0; i < size; i++){
			String head = storeToken.peek();
			if(counter == 1 && !Arrays.asList("a", "b", "c", "d", "e", "f", "g")
			   .contains(head)){
				System.out.println("Only accepted variables are a - g");
				return false;
			}
			else if(counter == 2 && !head.equals(":")){
				System.out.println("Expected : :");
				return false;
			}
			else if(counter == 3 && !Arrays.asList("integer", "real", "string").contains(head)){
				System.out.println("Expected data type");
				return false;
			}
			else if(counter == 4 && head.equals(")")){
				isRightParentheses = true;
				storeToken.remove();
				break;
			}
			else if(counter == 4 && !head.equals(",")){
				System.out.println("Expected : ,");
				return false;
			}
			else{
				if(counter != 4){
					counter++;
				}
				else{
					counter = 1;
				}
				storeToken.remove();
			}
		}
		if(isRightParentheses){
			return true;
		}
		else{
		    System.out.println("Expected : )");
			return false;
		}
	}
	
   /**
   * Checks for correct function signature
   * 
   * Example:
   * [ a is 4 ; b is 6 ; d is 7 ; ]
   */
	public boolean checkFunctionSignature(){
		String head = storeToken.peek();
				
		if(isEmpty() || !head.equals("[")){
			System.out.println("Expected : [");
			return false;
		}
		storeToken.remove();
		head = storeToken.peek();
		counter++;
		
		if(isEmpty()){
			System.out.println("Expected : ]");
			return false;
		}
		if(!head.equals("]")){
			return checkRemainingSignature();
		}
		else{
			storeToken.remove();
			counter = 0;
			return true;
		}
	}
	
   /**
   * Checks for correct remaining function signature and right bracket
   * 
   * Example:
   * a is 4 ; b is 6 ; d is 7 ; ]
   */
	private boolean checkRemainingSignature(){
		int size = storeToken.size();
		for(int i = 0; i < size; i++){
			String head = storeToken.peek();
			if(counter == 1 && !Arrays.asList("a", "b", "c", "d", "e", "f", "g")
			   .contains(head)){
				System.out.println("Only accepted variables are a - g");
				return false;
			}
			else if(counter == 2 && !head.equals("is")){
				System.out.println("Expected : is");
				return false;
			}
			// Check for single numbers only
			else if(counter == 3 && !head.matches("\\d")){
				System.out.println("Expected number");
				return false;
			}
			else if(counter == 4 && !head.equals(";")){
				System.out.println("Expected : ;");
				return false;
			}
			else if(counter == 5 && head.equals("]")){
				isRightBracket = true;
				storeToken.remove();
				break;
			}
			// Backtrack if the fifth element is not a right bracket
			else if(counter == 5 && !head.equals("]")){
				counter = 1;
				i = i - 1;
			}
			else{
				counter++;
				storeToken.remove();
			}
		}
		if(isRightBracket){
			return true;
		}
		else{
		    System.out.println("Expected : ]");
			return false;
		}
	}
}