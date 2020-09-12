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
        NEWLINE("[\n]|[\\s]+\n"),
        WHITESPACE("[\\s]+"),
        
        /*RESERVED("void|int|double|bool|string|class|const|interface"
                + "|null|this|for|while|foreach|if|else|return|break|New|NewArray"
                + "|Console|WriteLine"),*/
        VOID("void"),
        INTERFACE("interface"),
        INT("int"),
        DOUBLERESERVED("double"),
        BOOL("bool"),
        STRINGRESERVED("string"),
        CLASS("class"),
        CONST("const"),
        NULL("null"),
        THIS("this"),
        FOREACH("foreach"),
        FOR("for"),
        WHILE("while"),
        IF("if"),
        ELSE("else"),
        RETURN("return"),
        BREAK("break"),
        NEWARRAY("NewArray"),
        NEW("New"),
        CONSOLE("Console"),
        WRITELINE("WriteLine"),
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
       /* BINARYOP */
        SYMADD("\\+"),
        SYMSUB("\\-"),
        SYASTERISK("\\*"),
        SYSLASH("/"),
        SYPERCENTAGE("%"),
        SYEQUALSLOWERTHAN("<="),
        SYLOWERTHAN("<"),
        SYGRATEREQUALSTHAN(">="),
        SYGRATERTHAN(">"),
        SYDOBLEEQUALS("=="),
        SYEQUALS("="),
        SYDIFERENT("!="),
        SYAND("&&"),
        SYOR("\\|\\|"),
        SYADMIRATION("!"),
        SYSEMICOLON(";"),
        SYCOLON(":"),
        SYCOMMA(","),
        SYDOT("\\."),
        SYPARENTESIS("\\(\\)"),
        SYSQUAREBRAKET("\\[\\]"),
        SYCURLYBRAKET("\\{\\}"),
        SYBRAKETOPEN("\\["),
        SYBRAKETCLOSE("\\]"),
        SYOPENPARENTHESES("\\("),
        SYCLOSEPARENTHESES("\\)"),
        SYOPENCURLYBRAKET("\\{"),
        SYCLOSECURLYBRAKET("\\}"),
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
            return String.format( "NULL %-35sline %s cols %s-%-5s is %s", data, 
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
                if(data.length() == 31){   
//                    String truncated = "";
//                    truncated = data.substring(0, 30);
                    return String.format("*** Error line %s id too long.*** TRUNCATED %s \n %-35s line %s cols %s-%-5s is %s", line, data, data,
                        line,charStart, charEnd, type.name());
                }
                else
                    return String.format( "%-35sline %s cols %s-%-5s is %s", data,
                        line,charStart, charEnd, type.name());
            default:
                return String.format( "%-35sline %s cols %s-%-5s is %s", data,
                        line,charStart, charEnd, type.name());
        }
      
    }
  }
}



