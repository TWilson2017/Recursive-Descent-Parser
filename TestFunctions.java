import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TestFunctions{
	@Test
	public void testIsEmpty(){
		Queue<String> storeToken = new LinkedList<>();
		Functions function = new Functions(storeToken);
		assertTrue(function.isEmpty());
	}
	
	@Test
	public void testCorrectKeyword(){
		Queue<String> storeToken = new LinkedList<>();
		Functions function = new Functions(storeToken);

		storeToken.add("function");
		assertTrue(function.checkKeyword());
	}
	
	@Test
	public void testIncorrectKeyword(){
		Queue<String> storeToken = new LinkedList<>();
		Functions function = new Functions(storeToken);

		storeToken.add("hello");
		assertFalse(function.checkKeyword());
	}
	
	@Test
	public void testEmptyQueueKeyword(){
		Queue<String> storeToken = new LinkedList<>();
		Functions function = new Functions(storeToken);
		assertFalse(function.checkKeyword());
	}
	
   @Test
   /**
   * Checks for missing parentheses
   * 
   * Testcases:
   * Empty Queue
   * (a:real
   * a:real)
   * [a:real]
   * (a:real]
   * (a:real[
   */
	public void testMissingParentheses(){
		Queue<String> storeToken = new LinkedList<>(); 
		Functions function = new Functions(storeToken);
		assertFalse(function.checkParameters());

		Queue<String> storeToken2 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real"}));
		function = addAndRemoveFromQueue(storeToken2);
		assertFalse(function.checkParameters());
		
		Queue<String> storeToken3 = new LinkedList<>
			(Arrays.asList(new String[]{"a",":","real",")"}));
		function = addAndRemoveFromQueue(storeToken3);
		assertFalse(function.checkParameters());
		
		Queue<String> storeToken4 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a",":","real","]"}));
		function = addAndRemoveFromQueue(storeToken4);
		assertFalse(function.checkParameters());
		
		Queue<String> storeToken5 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real","]"}));
		function = addAndRemoveFromQueue(storeToken5);
		assertFalse(function.checkParameters());
		
		Queue<String> storeToken6 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real","["}));
		function = addAndRemoveFromQueue(storeToken6);
		assertFalse(function.checkParameters());
	}
	
   @Test
   /**
   * Checks for correct parameters
   * 
   * Testcases:
   * (a:integer)
   * (a:real,b:real)
   * (a:real,b:integer,c:string)
   */
   public void testCorrectParameters(){
		Functions function = new Functions();
		
		Queue<String> storeToken = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","integer",")"}));
		function = addAndRemoveFromQueue(storeToken);
		assertTrue(function.checkParameters());
	   
		Queue<String> storeToken2 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real",",","b",":","real",")"}));
		function = addAndRemoveFromQueue(storeToken2);
		assertTrue(function.checkParameters());
	   
		Queue<String> storeToken3 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real",",","b",":","integer",",","c",":","string",")"}));
		function = addAndRemoveFromQueue(storeToken3);
		assertTrue(function.checkParameters());
	}
	
   @Test
   /**
   * Checks for incorrect parameters
   * 
   * Example:
   * (a:int)
   * (a:real,b)
   * (a:realb)
   * (a:real,t:string)
   */
   public void testIncorrectParameters(){
		Functions function = new Functions();
		
		Queue<String> storeToken = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","int",")"}));
		function = addAndRemoveFromQueue(storeToken);
		assertFalse(function.checkParameters());
	   
		Queue<String> storeToken2 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real","b",")"}));
		function = addAndRemoveFromQueue(storeToken2);
		assertFalse(function.checkParameters());
	   
		Queue<String> storeToken3 = new LinkedList<>
			(Arrays.asList(new String[]{"(a:real", "b:integer)"}));
		function = addAndRemoveFromQueue(storeToken3);
		assertFalse(function.checkParameters());
	   
		Queue<String> storeToken4 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","realb",")"}));
		function = addAndRemoveFromQueue(storeToken4);
		assertFalse(function.checkParameters());
	   
		Queue<String> storeToken5 = new LinkedList<>
			(Arrays.asList(new String[]{"(","a",":","real","t",":","string",")"}));
		function = addAndRemoveFromQueue(storeToken5);
		assertFalse(function.checkParameters());
	}
	
   @Test
   /**
   * Checks for correct function body
   * 
   * Testcases:
   * [a is 5 ;]
   * [a is 5 ; b is 7 ;]
   */
   public void testCorrectFunctionBody(){
		Functions function = new Functions();
		
		Queue<String> storeToken = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","5",";","]"}));
		function = addAndRemoveFromQueue(storeToken);
		assertTrue(function.checkFunctionBody());
	   
		Queue<String> storeToken2 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","5",";","b","is","7",";","]"}));
		function = addAndRemoveFromQueue(storeToken2);
		assertTrue(function.checkFunctionBody());
	}
	
   @Test
   /**
   * Checks for incorrect function body
   * 
   * Testcases:
   * [a is 15 ;]
   * [a is k ;]
   * [a is 5 b is 7 ;]
   * [a is 7 ;)
   * [a 7 ;]
   * 
   */
   public void testIncorrectFunctionBody(){
		Functions function = new Functions();
		
		Queue<String> storeToken = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","15",";","]"}));
		function = addAndRemoveFromQueue(storeToken);
		assertFalse(function.checkFunctionBody());
	   
		Queue<String> storeToken2 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","k",";","]"}));
		function = addAndRemoveFromQueue(storeToken2);
		assertFalse(function.checkFunctionBody());
	   
		Queue<String> storeToken3 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","5","b","is","7",";","]"}));
		function = addAndRemoveFromQueue(storeToken3);
		assertFalse(function.checkFunctionBody());
	   
		Queue<String> storeToken4 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","is","7",";",")"}));
		function = addAndRemoveFromQueue(storeToken4);
		assertFalse(function.checkFunctionBody());
	   
		Queue<String> storeToken5 = new LinkedList<>
			(Arrays.asList(new String[]{"[","a","7",";","]"}));
		function = addAndRemoveFromQueue(storeToken5);
		assertFalse(function.checkFunctionBody());
	}
	
	private Functions addAndRemoveFromQueue(Queue<String> storeToken){
		Functions function = new Functions(storeToken);
		return function;
	}
}