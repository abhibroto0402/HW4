package pset4;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


/**
 * Created by amukherjee on 5/19/17.
 */
public class CheckFormat {
    static class FormatException extends Exception{
        public FormatException( String message){
            super(message);
        }
    }
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        File file = new File("test");
        Set<Character> openBrackets = new HashSet<>();
        Set<Character> closedBrackets = new HashSet<>();
        String data;
        openBrackets.add('{');
        openBrackets.add('(');
        openBrackets.add('[');
        closedBrackets.add('}');
        closedBrackets.add(']');
        closedBrackets.add(')');
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringBuffer sb = new StringBuffer();
            while(line!=null){
                sb.append(line);
                line= br.readLine();
            }
            data = sb.toString();
            for(Character c: data.toCharArray()){
                if(openBrackets.contains((c))){
                    stack.push((c));
                }
                else if(closedBrackets.contains(c)){
                    switch (c){
                        case '}':
                            if(stack.peek().equals('{')) stack.pop();
                            break;
                        case ')':
                            if(stack.peek().equals('(')) stack.pop();
                            break;
                        case ']':
                            if(stack.peek().equals(']')) stack.pop();
                            break;
                        default:
                            throw new FormatException("Missing: "+ stack.pop());
                    }
                }
            }
            if(!stack.empty()) throw new FormatException("Missing Closure for: "+ stack.pop());
            else System.out.println("All Good!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
