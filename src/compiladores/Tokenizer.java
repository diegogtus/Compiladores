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
        UNOPENEDCOMMENT(".*\\*+/"),
        SINGLE("//.*"),
        NEWLINE("\n"),
        WHITESPACE("[\\s]+"),
        RESERVED("void|int|double|bool|string|class|const|interface"
                + "|null|this|for|while|foreach|if|else|return|break|New|NewArray"
                + "|Console|WriteLine"),
        //UNCLOSEDSTRING("\"[^\r\n]+"),
        BOOLEAN("true|false"),
        ID("[a-zA-Z][\\w]*"),
        STRING("\"[^\r\n]+\""),
        UNCLOSEDSTRING("\".*"),
        //UNOPENEDSTRING("[^\r\n]+\""),
        //INVALIDID("[0-9]+[a-z_A-Z]+"),
        DOUBLE("([\\-|\\+]?[0-9]+\\.[0-9]?[e|E][\\-|\\+]?[0-9]+)|([\\-|\\+]?[0-9]+\\.[0-9]+)|([\\-|\\+]?[0-9]+\\.)"),
        HEXA("0[x|X][0-9a-fA-F]+"),
        DECIMAL("-?[0-9]+"), 
        UNCLOSEDCOMMENT("/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*"),
        BINARYOP("\\+|\\-|\\*|/|%|<|<=|>|>=|=|==|!=|&&|\\|\\||!|;|,|\\.|\\(\\)|\\[|\\]|\\(|\\)|\\{|\\}|\\[\\]|" +
            "\\(\\)|\\{\\}"),  
        //UNFINISH_STRING("\""),
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
        if(null ==type.name())
            return String.format( "NULL %-20sline %s cols %s-%s is %s", data, 
                    line,charStart, charEnd, type.name());
        else switch (type.name()) {
            case "ERROR":
                return String.format("*** Error line %s.*** Unrecognized char:  '%s'",
                        line, data);
            case "UNOPENEDCOMMENT":
                return String.format("*** Error line %s.***UNOPENED OF COMMENT",
                        line);
            case "INVALIDID":
                return String.format("*** Error line %s.***INVALID ID",
                        line);            
//            case "UNOPENEDSTRING":
//                return String.format("*** Error line %s.***UNOPENED OF COMMENT",
//                        line);
             case "UNCLOSEDCOMMENT":
                return String.format("*** EOF in comment line %s.***", line);
            case "UNCLOSEDSTRING":
                return String.format("*** EOF in string line %s.***",
                        line);
            case "ID":
                if(data.length() > 31)
                    return String.format("*** Error line %s id too long.*** TRUNCATED %.31s ", line, data);
                else
                    return String.format( "%-20sline %s cols %s-%s is %s", data,
                        line,charStart, charEnd, type.name());
            default:
                return String.format( "%-20sline %s cols %s-%s is %s", data,
                        line,charStart, charEnd, type.name());
        }
      
    }
  }
}



