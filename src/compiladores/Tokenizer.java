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
        RESERVED("void|int|double|bool|string|class|const|interface"
                + "|null|this|for|while|foreach|if|else|return|break|New|NewArray"
                + "|Console|WriteLine"),
        BOOLEAN("true|false"),
        ID("[a-zA-Z][\\w]*"),
        WHITESPACE("[\t\f\r]*"),
        MULTILINE("/\\*[^*]~\\*/|/[\\*\\*]+/"),
        UNFINISHED("/\\* [^\\*]+"),
        SINGLE("//[^\r\n]*[\r|\n|\r\n]?"),
        DECIMAL("-?[0-9]+"), 
        HEXA("0x[0-9a-fA-F]+|0X[0-9a-fA-F]+"),
        DOUBLE("[-+]?[0-9]+.|[-+]?[0-9]+.([0-9]+|(E|e)[-+]?[0-9]+|[0-9]+(E|e)[-+]?[0-9]+)"),
        CHAR("\"[^\\r\\n]+\""),
        BINARYOP("\\+|-|\\*|/|%|<|<=|>|>=|=|==|!=|&&|\\|\\||!|;|,|\\.|\\[|\\]|\\(|\\)|\\{|\\}|\\[\\]|" +
            "\\(\\)|\\{\\}"),       
        NEWLINE("\n|\r\n"),
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
        if(type.name() =="UNRECOGNIZED")
            return String.format("*** Error line %s.*** Unrecognized char:  '%s'",
                    line, data);
        else if(type.name() =="UNFINISHED")
            return String.format("*** Error line %s.*** UNFINISHED COMMENT",
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



