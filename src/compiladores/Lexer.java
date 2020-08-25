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
    int lineas = 0;
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
        
       if(matcher.group(TokenType.RESERVED.name()) != null){
          tokens.add(new Token(TokenType.RESERVED, matcher.group(TokenType.RESERVED.name()), line,start,endChar));
        continue; 
      }else if(matcher.group(TokenType.BOOLEAN.name()) != null){
          tokens.add(new Token(TokenType.BOOLEAN, matcher.group(TokenType.BOOLEAN.name()), line,start,endChar));
        continue;
      }else if(matcher.group(TokenType.ID.name()) != null){
          tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name()), line,start,endChar));
        continue;
      }else if(matcher.group(TokenType.UNOPENEDCOMMENT.name()) != null){
          tokens.add(new Token(TokenType.UNOPENEDCOMMENT, matcher.group(TokenType.UNOPENEDCOMMENT.name()), line,start,endChar));
        continue;
      }else if(matcher.group(TokenType.DOUBLE.name()) != null){
          tokens.add(new Token(TokenType.DOUBLE, matcher.group(TokenType.DOUBLE.name()), line,start,endChar));
        continue;
       }else if(matcher.group(TokenType.HEXA.name()) != null){
          tokens.add(new Token(TokenType.HEXA, matcher.group(TokenType.HEXA.name()), line,start,endChar));
        continue;
      }else if (matcher.group(TokenType.DECIMAL.name()) != null) {
        tokens.add(new Token(TokenType.DECIMAL, matcher.group(TokenType.DECIMAL.name()), line,start,endChar));
        continue;
      }else if(matcher.group(TokenType.STRING.name()) != null){
          tokens.add(new Token(TokenType.STRING, matcher.group(TokenType.STRING.name()), line,start,endChar));
        continue;
      } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
        tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name()), line, start,endChar));
        continue;
      } else if(matcher.group(TokenType.NEWLINE.name()) != null){
          line++;
          input = input.substring(endChar);
          matcher = tokenPatterns.matcher(input);
          continue;/*
      }else if (matcher.group(TokenType.INVALIDID.name()) != null){
          tokens.add(new Token(TokenType.INVALIDID, matcher.group(TokenType.INVALIDID.name()), line, start,endChar));
        continue;
      }else if (matcher.group(TokenType.UNOPENEDSTRING.name()) != null){
          tokens.add(new Token(TokenType.UNOPENEDSTRING, matcher.group(TokenType.UNOPENEDSTRING.name()), line, start,endChar));
        continue;*/
      }else if (matcher.group(TokenType.UNCLOSEDSTRING.name()) != null){
          tokens.add(new Token(TokenType.UNCLOSEDSTRING, matcher.group(TokenType.UNCLOSEDSTRING.name()), line, start,endChar));
        continue;
      }else if (matcher.group(TokenType.UNCLOSEDCOMMENT.name()) != null){
          tokens.add(new Token(TokenType.UNCLOSEDCOMMENT, matcher.group(TokenType.UNCLOSEDCOMMENT.name()), line, start,endChar));
          line++;
        continue;
      }else if (matcher.group(TokenType.ERROR.name()) != null){
          tokens.add(new Token(TokenType.ERROR, matcher.group(TokenType.ERROR.name()), line, start,endChar));
        continue;
      }else if (matcher.group(TokenType.WHITESPACE.name()) != null ||  
              matcher.group(TokenType.SINGLE.name()) != null){   
        continue;
      }else if (matcher.group(TokenType.MULTILINE.name()) != null){
          //line++;
          continue;
      }else if (matcher.group(TokenType.MULTILINECOMMENTUNCLOSEDWITHNEWLINE.name()) != null){
          line++;
          continue;
      }else if (matcher.group(TokenType.MULTILINECOMMENTUNOPPENDWITHNEWLINE.name()) != null){
          //line++;
          continue;
      }
       
    }
    return tokens;
  }    
}
