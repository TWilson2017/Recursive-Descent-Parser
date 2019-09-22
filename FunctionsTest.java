import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;


public class FunctionsTest{
	public static void main (String[] args){
		Scanner input = new Scanner(System.in);
		String token;
		Queue<String> storeToken = new LinkedList<>();
		
		token = input.next();
		while(token.compareTo(".") != 0) {
			if(!token.equals(" ")){
				storeToken.add(token);
				token = input.next();
			}
		}
		Functions function = new Functions(storeToken);
		function.validateFunctionSignatures();
	}
}