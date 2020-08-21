/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import compiladores.Tokenizer.Token;
import compiladores.Tokenizer.TokenType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author diego
 */
public class Lexer {
 public static ArrayList<Token> lex(String input) {
    // The tokens to return
    ArrayList<Token> tokens = new ArrayList<Token>();
    int line=1;
    int start=0;
    int endChar=0;
    int length=0;
    // Lexer logic begins here
    StringBuffer tokenPatternsBuffer = new StringBuffer();
    for (TokenType tokenType : TokenType.values())
      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

    // Begin matching tokens
    Matcher matcher = tokenPatterns.matcher(input);
    while (matcher.find()) {
        start = matcher.start();
        endChar = matcher.end();
        
        
      if(matcher.group(TokenType.ID.name()) != null){
          tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name()), line,start,endChar));
        continue;
      }else if (matcher.group(TokenType.NUMBER.name()) != null) {
        tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name()), line,start,endChar));
        continue;
      } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
        tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name()), line, start,endChar));
        continue;
      } else if(matcher.group(TokenType.NEWLINE.name()) != null){
          line++;
          input = input.substring(endChar);
              matcher = tokenPatterns.matcher(input);
          //}
         System.out.println(    Integer.toString(endChar) + " " +Integer.toString(input.length()));
          continue;
      }else if (matcher.group(TokenType.UNRECOGNIZED.name()) != null)
          tokens.add(new Token(TokenType.UNRECOGNIZED, matcher.group(TokenType.UNRECOGNIZED.name()), line, start,endChar));
        continue;
    }

    return tokens;
  }    
 /*   public void tokenID(String lexer){
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
                continue;
            }else if(){
            
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
    };*/
}
