/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author diego
 */
public class Lexer {
    Lexer(){
    };
    public void tokenID(String lexer){
    Pattern pattern = Pattern.compile("[a-zA-Z](\\w|_)*", Pattern.CASE_INSENSITIVE);
    Matcher matcher;
    boolean matchFound;
    String lex;
    Integer line = 0;
    Integer end = 0;
    Integer start = 0;
    Integer stringLength = lexer.length()-1;
        while (end <  stringLength) {
            matcher = pattern.matcher(lexer);
            matchFound = matcher.find(end);
            
            if(matchFound) {
                start = matcher.start();
                end = matcher.end();
                lex = matcher.group(); 
                System.out.println(lex + " line: " + lineNumber() + " cols " + start + "-" + end+ " is T_Identifier");
            }else {
              System.out.println("*** Error line " + ".*** Unrecognized char: " + lexer.substring(start+1, end+1));
              start = end;
              end++;
            }
            
        }
    
    };
    
    public int lineNumber(){
        Pattern pattern = Pattern.compile("[a-zA-Z](\\w|_)*", Pattern.CASE_INSENSITIVE);
    Matcher matcher;
    boolean matchFound;
        return 1;
    };
}
