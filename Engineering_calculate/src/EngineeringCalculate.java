import java.util.*;

public class EngineeringCalculate {
//TODO: error entering letters. 
//Infinity value input request
//Issue with parentheses at the beginning of a line: (4+5)/12
    private static Scanner oper;

    public static void main(String[] args) {
	oper = new Scanner(System.in);
	System.out.println("Input the arithmetic expression:");
	String input = oper.next();
	System.out.println(expresion_calc(input));
    }

    private static double expresion_calc(String expresion) {
        double result = 0;
        String operation = "";
        // Function to convert String 
        // to List of Characters 
        List<Character> open_brackets = new ArrayList<Character>();
        List<Character> close_brackets = new ArrayList<Character>();
        StringBuilder inner_input = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char input_num = expresion.charAt(i);
            if(open_brackets.isEmpty()){
                if (Character.isDigit(input_num)) {

                    if (operation == "" && result == 0) {
                        result = Character.digit(input_num, Character.MAX_RADIX);
                        continue;
                    } 
                    else if (operation != "") {
                        result = calculate_with_operation(operation, Character.digit(input_num, Character.MAX_RADIX), result);
                        continue;
                    }
                }
                // Order of operations
                if (input_num == '+' || input_num == '-' || input_num == '*' || input_num == '/') {
                    operation = Character.toString(input_num);
                    continue;
                }
            }
            if (input_num == '(') {
                open_brackets.add(input_num);
                continue;
            }
            if(input_num ==')'){
                close_brackets.add(input_num);
                if(open_brackets.size() == close_brackets.size()){
                    open_brackets.remove((Character)'(');
                    close_brackets.remove((Character)')');
                    double expr_result =  expresion_calc(inner_input.toString());
                    result = calculate_with_operation(operation,expr_result,result);
                    inner_input.setLength(0);
                }
                if(open_brackets.size()> close_brackets.size()){
                    continue;
                }
            }
            else{
                inner_input.append(input_num);
            }
        }
        return result;
    }

    /**
     * this method to calculate the simple expressions  
     * @param operation
     * @param output
     * @param input_num
     * @return
     */
    private static double calculate_with_operation(String operation, double input_num, double output) {
        switch (operation) {
        case "+":
            output = output + input_num;
            break;

        case "-":
            output = output - input_num;
            break;

        case "*":
            output = output * input_num;
            break;

        case "/":
            output = output / input_num;
            break;

        default:
            break;
        }
        return output; 
    }
}
