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
        MULTILINE("/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/"),
        UNFINISHED("\\*/"),
        SINGLE("//.+"),
        NEWLINE("\n"),
        WHITESPACE("[\\s]+"),
        RESERVED("void|int|double|bool|string|class|const|interface"
                + "|null|this|for|while|foreach|if|else|return|break|New|NewArray"
                + "|Console|WriteLine"),
        BOOLEAN("true|false"),
        ID("[a-zA-Z][\\w]*"),
        DOUBLE("([\\-|\\+]?[0-9]+\\.[0-9]?[e|E][\\-|\\+]?[0-9]+)|([\\-|\\+]?[0-9]+\\.[0-9]+)|([\\-|\\+]?[0-9]+\\.)"),
        HEXA("0[x|X][0-9a-fA-F]+"),
        DECIMAL("-?[0-9]+"), 
        STRING("\"[^\\r\\n]+\""),
        BINARYOP("\\+|\\-|\\*|/|%|<|<=|>|>=|=|==|!=|&&|\\|\\||!|;|,|\\.|\\[|\\]|\\(|\\)|\\{|\\}|\\[\\]|" +
            "\\(\\)|\\{\\}"),       
        ERROR(".");
        
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
        if(type.name() =="ERROR")
            return String.format("*** Error line %s.*** Unrecognized char:  '%s'",
                    line, data);
        else if(type.name() =="UNFINISHED")
            return String.format("*** Error line %s.*** END OF COMMENT",
                    line, data);
        else
            return String.format( "%-20sline %s cols %s-%s is %s", data, 
                    line,charStart, charEnd, type.name());
      
    }
//    public String toString() {
//      return String.format("%s line %s cols %s-%s is %s", data, line,charStart, charEnd, type.name());
//    }
  }
}



