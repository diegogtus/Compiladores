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
    StringBuffer Patterns = new StringBuffer();
    for (TokenType tokenType : TokenType.values())
      Patterns.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
    Pattern tokenPatterns = Pattern.compile(new String(Patterns.substring(1)));

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
           System.out.println( matcher.group(TokenType.ID.name()));
           if(matcher.group(TokenType.ID.name()).length() > 31) 
                tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name()).substring(0, 31), line,start,endChar));
            else
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
      } else if (matcher.group(TokenType.SYMADD.name()) != null) {
        tokens.add(new Token(TokenType.SYMADD, matcher.group(TokenType.SYMADD.name()), line, start,endChar));
        continue;
       		 } else if (matcher.group(TokenType. SYMSUB.name()) != null) {
        tokens.add(new Token(TokenType. SYMSUB, matcher.group(TokenType. SYMSUB.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYASTERISK.name()) != null) {
        tokens.add(new Token(TokenType.SYASTERISK, matcher.group(TokenType.SYASTERISK.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYSLASH.name()) != null) {
        tokens.add(new Token(TokenType.SYSLASH, matcher.group(TokenType.SYSLASH.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYPERCENTAGE.name()) != null) {
        tokens.add(new Token(TokenType.SYPERCENTAGE, matcher.group(TokenType.SYPERCENTAGE.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYEQUALSLOWERTHAN.name()) != null) {
        tokens.add(new Token(TokenType.SYEQUALSLOWERTHAN, matcher.group(TokenType.SYEQUALSLOWERTHAN.name()), line, start,endChar));
        continue;      
 } else if (matcher.group(TokenType.SYLOWERTHAN.name()) != null) {
        tokens.add(new Token(TokenType.SYLOWERTHAN, matcher.group(TokenType.SYLOWERTHAN.name()), line, start,endChar));
        continue;
 
 } else if (matcher.group(TokenType.SYGRATERTHAN.name()) != null) {
        tokens.add(new Token(TokenType.SYGRATERTHAN, matcher.group(TokenType.SYGRATERTHAN.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYGRATEREQUALSTHAN.name()) != null) {
        tokens.add(new Token(TokenType.SYGRATEREQUALSTHAN, matcher.group(TokenType.SYGRATEREQUALSTHAN.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYEQUALS.name()) != null) {
        tokens.add(new Token(TokenType.SYEQUALS, matcher.group(TokenType.SYEQUALS.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYDOBLEEQUALS.name()) != null) {
        tokens.add(new Token(TokenType.SYDOBLEEQUALS, matcher.group(TokenType.SYDOBLEEQUALS.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYDIFERENT.name()) != null) {
        tokens.add(new Token(TokenType.SYDIFERENT, matcher.group(TokenType.SYDIFERENT.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYAND.name()) != null) {
        tokens.add(new Token(TokenType.SYAND, matcher.group(TokenType.SYAND.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYOR.name()) != null) {
        tokens.add(new Token(TokenType.SYOR, matcher.group(TokenType.SYOR.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYADMIRATION.name()) != null) {
        tokens.add(new Token(TokenType.SYADMIRATION, matcher.group(TokenType.SYADMIRATION.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYSEMICOLON.name()) != null) {
        tokens.add(new Token(TokenType.SYSEMICOLON, matcher.group(TokenType.SYSEMICOLON.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYCOLON.name()) != null) {
        tokens.add(new Token(TokenType.SYCOLON, matcher.group(TokenType.SYCOLON.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYCOMMA.name()) != null) {
        tokens.add(new Token(TokenType.SYCOMMA, matcher.group(TokenType.SYCOMMA.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYDOT.name()) != null) {
        tokens.add(new Token(TokenType.SYDOT, matcher.group(TokenType.SYDOT.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYPARENTESIS.name()) != null) {
        tokens.add(new Token(TokenType.SYPARENTESIS, matcher.group(TokenType.SYPARENTESIS.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYBRAKETOPEN.name()) != null) {
        tokens.add(new Token(TokenType.SYBRAKETOPEN, matcher.group(TokenType.SYBRAKETOPEN.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYBRAKETCLOSE.name()) != null) {
        tokens.add(new Token(TokenType.SYBRAKETCLOSE, matcher.group(TokenType.SYBRAKETCLOSE.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYOPENPARENTHESES.name()) != null) {
        tokens.add(new Token(TokenType.SYOPENPARENTHESES, matcher.group(TokenType.SYOPENPARENTHESES.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYCLOSEPARENTHESES.name()) != null) {
        tokens.add(new Token(TokenType.SYCLOSEPARENTHESES, matcher.group(TokenType.SYCLOSEPARENTHESES.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYOPENCURLYBRAKET.name()) != null) {
        tokens.add(new Token(TokenType.SYOPENCURLYBRAKET, matcher.group(TokenType.SYOPENCURLYBRAKET.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYCLOSECURLYBRAKET.name()) != null) {
        tokens.add(new Token(TokenType.SYCLOSECURLYBRAKET, matcher.group(TokenType.SYCLOSECURLYBRAKET.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYSQUAREBRAKET.name()) != null) {
        tokens.add(new Token(TokenType.SYSQUAREBRAKET, matcher.group(TokenType.SYSQUAREBRAKET.name()), line, start,endChar));
        continue;
 } else if (matcher.group(TokenType.SYCURLYBRAKET.name()) != null) {
        tokens.add(new Token(TokenType.SYCURLYBRAKET, matcher.group(TokenType.SYCURLYBRAKET.name()), line, start,endChar));
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
        continue;
      }else if (matcher.group(TokenType.ERROR.name()) != null){
          tokens.add(new Token(TokenType.ERROR, matcher.group(TokenType.ERROR.name()), line, start,endChar));
        continue;
      }else if (matcher.group(TokenType.WHITESPACE.name()) != null ||  
              matcher.group(TokenType.SINGLE.name()) != null){
        continue;
      }
      else if (matcher.group(TokenType.MULTILINE.name()) != null){
//          line++;
          String newInput = matcher.group(TokenType.MULTILINE.name());
          newInput = newInput.substring(2, newInput.length()-2);
          Matcher newMatcher = tokenPatterns.matcher(newInput);
           //System.out.println(newMatcher.find());
          while (newMatcher.find()) {
             // System.out.println(newMatcher.group(TokenType.NEWLINE.name()));
             if(newMatcher.group(TokenType.NEWLINE.name()) != null){
                line++; 
                continue;
             }
//            }else if(newMatcher.group(TokenType.ID.name()) != null){
//                //tokens.add(new Token(TokenType.ID, matcher.group(TokenType.ID.name()), line,start,endChar));
//                System.out.println(newMatcher.group(TokenType.ID.name()));
//              continue;
//            }
          }
          continue;
      }
    }
    return tokens;
  }    
}
