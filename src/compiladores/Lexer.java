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
        
       if(matcher.group(TokenType.VOID.name()) != null){
          tokens.add(new Token(TokenType.VOID, matcher.group(TokenType.VOID.name()), line,start,endChar));
        continue; 
        }else if(matcher.group(TokenType.PRINT.name()) != null){
          tokens.add(new Token(TokenType.PRINT, matcher.group(TokenType.PRINT.name()), line,start,endChar));
        continue;
        }else if(matcher.group(TokenType.INT.name()) != null){
          tokens.add(new Token(TokenType.INT, matcher.group(TokenType.INT.name()), line,start,endChar));
        continue;
        }else if(matcher.group(TokenType.DOUBLERESERVED.name()) != null){
          tokens.add(new Token(TokenType.DOUBLERESERVED, matcher.group(TokenType.DOUBLERESERVED.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.BOOL.name()) != null){
          tokens.add(new Token(TokenType.BOOL, matcher.group(TokenType.BOOL.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.STRINGRESERVED.name()) != null){
          tokens.add(new Token(TokenType.STRINGRESERVED, matcher.group(TokenType.STRINGRESERVED.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.CLASS.name()) != null){
          tokens.add(new Token(TokenType.CLASS, matcher.group(TokenType.CLASS.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.CONST.name()) != null){
          tokens.add(new Token(TokenType.CONST, matcher.group(TokenType.CONST.name()), line,start,endChar));
        continue;
        }else if(matcher.group(TokenType.IDENT.name()) != null){
          tokens.add(new Token(TokenType.IDENT, matcher.group(TokenType.IDENT.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.INTERFACE.name()) != null){
          tokens.add(new Token(TokenType.INTERFACE, matcher.group(TokenType.INTERFACE.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.NULL.name()) != null){
          tokens.add(new Token(TokenType.NULL, matcher.group(TokenType.NULL.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.THIS.name()) != null){
          tokens.add(new Token(TokenType.THIS, matcher.group(TokenType.THIS.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.FOR.name()) != null){
          tokens.add(new Token(TokenType.FOR, matcher.group(TokenType.FOR.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.WHILE.name()) != null){
          tokens.add(new Token(TokenType.WHILE, matcher.group(TokenType.WHILE.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.FOREACH.name()) != null){
          tokens.add(new Token(TokenType.FOREACH, matcher.group(TokenType.FOREACH.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.IF.name()) != null){
          tokens.add(new Token(TokenType.IF, matcher.group(TokenType.IF.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.ELSE.name()) != null){
          tokens.add(new Token(TokenType.ELSE, matcher.group(TokenType.ELSE.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.RETURN.name()) != null){
          tokens.add(new Token(TokenType.RETURN, matcher.group(TokenType.RETURN.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.BREAK.name()) != null){
          tokens.add(new Token(TokenType.BREAK, matcher.group(TokenType.BREAK.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.NEW.name()) != null){
          tokens.add(new Token(TokenType.NEW, matcher.group(TokenType.NEW.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.NEWARRAY.name()) != null){
          tokens.add(new Token(TokenType.NEWARRAY, matcher.group(TokenType.NEWARRAY.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.CONSOLE.name()) != null){
          tokens.add(new Token(TokenType.CONSOLE, matcher.group(TokenType.CONSOLE.name()), line,start,endChar));
        continue;
	}else if(matcher.group(TokenType.WRITELINE.name()) != null){
          tokens.add(new Token(TokenType.WRITELINE, matcher.group(TokenType.WRITELINE.name()), line,start,endChar));
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
 } else if (matcher.group(TokenType.SYDOUBLEEQUALS.name()) != null) {
        tokens.add(new Token(TokenType.SYDOUBLEEQUALS, matcher.group(TokenType.SYDOUBLEEQUALS.name()), line, start,endChar));
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
