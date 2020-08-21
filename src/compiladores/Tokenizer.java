/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

/**
 *
 * @author diego
 */
public class Tokenizer{
    public static enum TokenType {
        // Token types cannot have underscores
        ID("[a-zA-Z][\\w|_]*"),
        NUMBER("-?[0-9]+"), 
        BINARYOP("[*|/|+|-|=]"),
        WHITESPACE("[ \t\f\r]+"),
        NEWLINE("\n"),
        UNRECOGNIZED(".");
        
        public final String pattern;

        private TokenType(String pattern) {
          this.pattern = pattern;
        }
      }

  public static class Token {
    public TokenType type;
    public String data;
    public int line;
    public int charStart;
    public int charEnd;
    
    public Token(){
    }
    public Token(TokenType type, String data, Integer line, Integer charStart, Integer charEnd) {
      this.type = type;
      this.data = data;
      this.line = line;
      this.charStart = charStart;
      this.charEnd = charEnd;
    }

    @Override
    public String toString() {
        if(type.name() =="UNRECOGNIZED"){
            return String.format("*** Error line %s.*** Unrecognized char:  %s", line, data);
        }else
            return String.format("%s line %s cols %s-%s is %s", data, line,charStart, charEnd, type.name());
      
    }
//    public String toString() {
//      return String.format("%s line %s cols %s-%s is %s", data, line,charStart, charEnd, type.name());
//    }
  }
}



