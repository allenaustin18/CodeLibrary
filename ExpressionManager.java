import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 
 * @author Allen Austin -  CS 320 - Data Structures Programming Assignment 1 -
 *         Expression Manager Program should use stacks to implement balanced
 *         symbols check and to evaluate infix and post-fix expressions.
 *
 */

public class ExpressionManager {


	public static void main(String[] arg) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Expression Manager:");
		System.out.println();
		System.out.println("1.) Balanced Symbols");
		System.out.println("2.) Post-fix expression");
		System.out.println("3.) infix expression");

		System.out.println("Please choose from one of the above options: ");

		//Used to throw an exception if Balanced symbols not entered properly
		try {
			int options = scan.nextInt();
			if (options == 1) {
				Scanner balancedScannerValue = new Scanner(System.in);
				System.out.println("Enter balanced expressions");
				String balancedStr = balancedScannerValue.next();
				
				boolean validateIsBalanced = isBalanced(balancedStr);
				if (validateIsBalanced) {
					System.out.println("Is Balanced ....");
				} else {
					System.out.println("Is not Balanced ....");
				}
				balancedScannerValue.close();
				} 
			
			//Used to throw an exception if postfix expression not entered properly 
			else if (options == 2) 
			{
				String[] tokens = null;
				try {
					InputStreamReader in = new InputStreamReader(System.in);
					BufferedReader buffer = new BufferedReader(in);
					String line;
					System.out.println("Enter postfix value");
					while ((line = buffer.readLine()) != null) {
						tokens = line.split("\\s");
						System.out.println("The Postfix Result =" + postfixEval(tokens));
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Only integers allowed.");
				}		
			} 
			//Used to throw an exception infix not entered properly
			else if (options == 3) 
			{
				String[] tokens = null;
				try {
					InputStreamReader in = new InputStreamReader(System.in);
					BufferedReader buffer = new BufferedReader(in);
					String line;
					System.out.println("Enter infix value");
					while ((line = buffer.readLine()) != null) {
						tokens = line.split("\\s");
						System.out.println("The Infix Result =" + infixEval(tokens));
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Only integers allowed.");
					 
				}
			} 
			else 
			{
				System.out.println("Please choose valid option");
			}
		} 
		catch (Exception e) {
			System.out.println("Please choose valid option");
		}
		scan.close();
	}

//method for balanced symbols evaluation
	public static boolean isBalanced(String expr) {
//supplied expression is iterated and assigned to variable then char is pushed to stack
		char[] bracketsArray = expr.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		Map<Character, Character> openingClosingMap = charactersMap();
		
		//iterate the group of elements and validates & pushes elements
		for (char bracket : bracketsArray) {
			if (openingClosingMap.keySet().contains(bracket)) {
				stack.push(bracket);
			} else if (openingClosingMap.values().contains(bracket)) {
				if (stack.isEmpty() || openingClosingMap.get(stack.pop()) != bracket) {
					return false;
				}
			} else {
				System.out.println("Only  ( ), { }, [ ] brackets  are allowed .");
				return false;
			}
		}
		return stack.isEmpty();
	}
//method for post-fix evaluation
//tokenizer used to recognize spaces
	public static int postfixEval(String[] expr) {
		
		String[] inputs = expr;
		Stack<Integer> s = new Stack<Integer>();
		for (String str : inputs) {
			Scanner tokens = new Scanner(str);

			while (tokens.hasNext()) {
				if (tokens.hasNextInt()) {
					s.push(tokens.nextInt());
				} else {
					int num2 = s.pop();
					int num1 = s.pop();
					String op = tokens.next();

					if (op.equals("+")) {
						s.push(num1 + num2);
					} else if (op.equals("-")) {
						s.push(num1 - num2);
					} else if (op.equals("*")) {
						s.push(num1 * num2);
					} else {
						s.push(num1 / num2);
					}
				}
			}
			tokens.close();
		}
		return s.pop();		
	}

//used to evaluate infix expression
	public static double infixEval(String[] expression)
    {
        StringBuilder aa = new StringBuilder();
        
        for(String str : expression) {
        	aa.append(str);
        }
		
//remove white space and add evaluation operator
        String expr=aa.toString().replaceAll("[\t\n ]", "")+"=";
        String operator="*/+-=";
        //split up the operators from the values
        StringTokenizer tokenizer=new StringTokenizer(expr, operator, true);
        Stack operatorStack=new Stack();
        Stack valueStack=new Stack();
        while(tokenizer.hasMoreTokens())
        {
            //add the next token to the proper stack
            String token=tokenizer.nextToken();
            if(operator.indexOf(token)<0)
                valueStack.push(token);
            else
                operatorStack.push(token);
    //perform any pending operations
            validatePendingOperations(valueStack, operatorStack);
        }
    //return the top of the value stack
        String lastOne=(String)valueStack.pop();
        return Double.parseDouble(lastOne);   
    }
     //Makes sure to use the proper order of operation when solving expression
    public static int validatePriority(String op)
    {
        if(op.equals("*") || op.equals("/"))
            return 1;
        else if(op.equals("+") || op.equals("-"))
            return 2;
        else if(op.equals("="))
            return 3;
        else
            return Integer.MIN_VALUE;
    }
    
    
   //Uses method to push first and second operands to use with scanned operator
	public static void validatePendingOperations(Stack values, 
            Stack operators)
    {
        while(operators.size()>=2)
        {
            String first=(String)operators.pop();
            String second=(String)operators.pop();
            if(validatePriority(first)<validatePriority(second))
            {
                operators.push(second);
                operators.push(first);
                return;
            }
            else
            {
                String firstValue=(String)values.pop();
                String secondValue=(String)values.pop();
                values.push(populateResults(secondValue, second, firstValue));
                operators.push(first);
            }
        }
    }
    
    //Parses value to be able to: mulitply, divide, add, or subtract 2 operands in stack.
    public static String populateResults(String operand1, String operator, String operand2)
    {
        double op1=Double.parseDouble(operand1);
        double op2=Double.parseDouble(operand2);
        if(operator.equals("*"))
            return ""+(op1*op2);
        else if(operator.equals("/"))
            return ""+(op1/op2);
        else if(operator.equals("+"))
            return ""+(op1+op2);
        else if(operator.equals("-"))
            return ""+(op1-op2);
        else
            return null;
    }

	//map valid characters for the balanced expression supplied by user 
	private static Map<Character, Character> charactersMap() {
		Map<Character, Character> openingClosingMap = new HashMap<Character, Character>();
		openingClosingMap.put(Character.valueOf('('), Character.valueOf(')'));
		openingClosingMap.put(Character.valueOf('{'), Character.valueOf('}'));
		openingClosingMap.put(Character.valueOf('['), Character.valueOf(']'));
		return openingClosingMap;
	}
	
}
