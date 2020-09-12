/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author diego
 */
public class Parse{
        ArrayList<String> error; 
        Tokenizer.Token Token;
    public Parse() {
        error = new ArrayList<String>();
    }
        
    public  ArrayList<String> parse(ArrayList<Tokenizer.Token> token) {
        STATEMENT(token);
        return error;
    }


    public void STATEMENT(ArrayList<Tokenizer.Token> token){
     while(!token.isEmpty()){
        switch (token.get(0).type) {
           case WHILE:
               token.remove(0);
               WHILE(token);
               break;
           case FOR:
               token.remove(0);
               FOR(token);
               break;
           case SYSEMICOLON:
               token.remove(0);
               break;
           default:
               error.add("Illegal statement: " +  token.get(0).toError());
               token.remove(0);
               break;
        } 
    }
 }
     public void FOR (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type){
                case SYOPENPARENTHESES:
                        token.remove(0);
                            if(token.size()!= 0){
                                switch(token.get(0).type) {
                                    case SYCOLON:
                                        token.remove(0);
                                        if(token.size()!= 0){
                                            switch(token.get(0).type) {
                                                case SYCOLON:
                                                    token.remove(0);
                                                    if(token.size()!= 0){
                                                        switch(token.get(0).type) {
                                                        case SYCLOSEPARENTHESES:
                                                            token.remove(0);
                                                            if(token.size()== 0)
                                                                error.add("Missing semicolon at the end of the file.");
                                                            else
                                                                STATEMENT(token);
                                                            break;
                                                        default:
                                                            error.add("Illegal FOR structure: " + token.get(0).toError());
                                                            break;
                                                        }
                                                    }else
                                                        error.add("Illegal FOR structure");     
                                                    break;
                                                default:
                                                    error.add("Illegal FOR structure: " + token.get(0).toError());
                                                    break;    
                                            }
                                        }else
                                            error.add("Illegal FOR structure");     
                                        break;
                                    default:
                                        error.add("Illegal FOR structure: " + token.get(0).toError());
                                        break;
                                }
                            }else
                                error.add("Illegal FOR structure");    
                        break;
                default:
                    error.add("Illegal FOR structure: " + token.get(0).toError());
                    break;
            }
        }else
            error.add("Illegal FOR structure");
     }
    public void WHILE (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case SYOPENPARENTHESES:
                    token.remove(0);
                    EXPR(token);
                    if(token.size()!= 0){
                         switch(token.get(0).type) {
                        case SYCLOSEPARENTHESES:
                            token.remove(0);
                            if(token.size()== 0)
                                error.add("Missing semicolon at the end of the file.");
                            else
                                STATEMENT(token);
                            break;
                        default:
                            error.add("Illegal WHILE structure: " + token.get(0).toError());
                            break;
                        }
                    }else
                        error.add("Illegal WHILE structure");
                    break;
                default:
                    error.add("Illegal WHILE structure: " + token.get(0).toError());
                    break;
            }
        }else
            error.add("Illegal WHILE structure");
    }
    public void EXPR (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            if(token.get(1).type == Tokenizer.TokenType.SYOR)
            EXPR(token);
            switch(token.get(0).type) {
                case SYOR:
                    token.remove(0);
                    EXPRAND(token);
                    break;
                default:
                    EXPRAND(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION structure");
    }
     public void EXPRAND (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            if(token.get(1).type == Tokenizer.TokenType.SYAND)
            EXPRAND(token);
            switch(token.get(0).type) {
                case SYAND:
                    token.remove(0);
                    EXPREQUALS(token);
                    break;
                default:
                        EXPREQUALS(token);
                    break;
                    
            }
        }else
            error.add("Illegal EXPRESION AND structure");
    }
    public void EXPREQUALS(ArrayList<Tokenizer.Token> token){
        if(!token.isEmpty()){
           if(token.get(1).type == Tokenizer.TokenType.SYEQUALS || token.get(1).type == Tokenizer.TokenType.SYDIFERENT)
           EXPREQUALS(token);
            switch(token.get(0).type) {
                case SYDOUBLEEQUALS:
                    token.remove(0);
                    EXPRREL(token);
                    break;
                case SYDIFERENT:
                    token.remove(0);
                    EXPRREL(token);
                    break;
                default:
                    EXPRREL(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION EQUALS structure");
    }
     public void EXPRREL (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
           if(token.get(1).type == Tokenizer.TokenType.SYEQUALSLOWERTHAN || 
                   token.get(1).type == Tokenizer.TokenType.SYGRATEREQUALSTHAN ||
                   token.get(1).type == Tokenizer.TokenType.SYGRATERTHAN ||
                   token.get(1).type == Tokenizer.TokenType.SYLOWERTHAN)
           EXPRREL(token);
            switch(token.get(0).type) {
                case SYAND:
                    token.remove(0);
                    EXPRADD(token);
                    break;
                case SYGRATEREQUALSTHAN:
                    token.remove(0);
                    EXPRADD(token);
                    break;
                case SYEQUALSLOWERTHAN:
                    token.remove(0);
                    EXPRADD(token);
                    break;
                case SYGRATERTHAN:
                    token.remove(0);
                    EXPRADD(token);
                    break;
                case SYLOWERTHAN:
                    token.remove(0);
                    EXPRADD(token);
                    break;
                default:
                    EXPRADD(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION REL structure");
    }
      public void EXPRADD(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
           if(token.get(1).type == Tokenizer.TokenType.SYMADD || 
                   token.get(1).type == Tokenizer.TokenType.SYMSUB)
           EXPRADD(token);
            switch(token.get(0).type ) {
                case SYMADD:
                    token.remove(0);
                    EXPRMUL(token);
                    break;
                case SYMSUB:
                   token.remove(0);
                    EXPRMUL(token);
                    break;
                default:
                    EXPRMUL(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION ADD structure");
    }
      
     public void EXPRMUL(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
          if(token.get(1).type == Tokenizer.TokenType.SYASTERISK ||
                  token.get(1).type == Tokenizer.TokenType.SYSLASH ||
                  token.get(1).type == Tokenizer.TokenType.SYPERCENTAGE){
            EXPRMUL(token);
          }
            switch(token.get(0).type) {
                case SYASTERISK :
                    token.remove(0);
                    EXPRUN(token);
                    break;
                case SYSLASH:
                   token.remove(0);
                    EXPRUN(token);
                    break;
                case SYPERCENTAGE:
                   token.remove(0);
                    EXPRUN(token);
                    break;
                default:
                    EXPRUN(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION MUL structure");
    }
     public void EXPRUN(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case SYMSUB:
                    token.remove(0);
                    EXPREXP(token);
                    break;
                case SYADMIRATION:
                    token.remove(0);
                    EXPREXP(token);
                    break;
                default:
                    EXPREXP(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION ADD structure");
    }
     public void EXPREXP(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case SYOPENPARENTHESES:
                    token.remove(0);
                    EXPREXP(token);
                    if(token.size()!= 0){
                        switch(token.get(0).type) {
                            case SYCLOSEPARENTHESES:
                                break;
                            default:
                                error.add("Illegal EXPRESION EXP structure: " + token.get(0).toError());
                                break;
                        }
                    }else
                        error.add("Illegal EXPRESION EXP structure");
                    break;
                case THIS:
                    token.remove(0);
                    break;
                default:
                    CONSTANT(token);
                    break;
            }
        }else
            error.add("Illegal EXPRESION EXP structure");
    }
     public void CONSTANT (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case NULL:
                    token.remove(0);
                    break;
                case DOUBLE:
                    token.remove(0);
                    break;
                case HEXA:
                    token.remove(0);
                    break;
                case DECIMAL:
                    token.remove(0);
                    break;
                case STRING:
                    token.remove(0);
                    break;
                case BOOLEAN:
                    token.remove(0);
                    break;
                default:
                    error.add("Illegal EXPRESION CONSTANT structure "+ token.get(0).toError());
                    token.remove(0);
                    break;
            }
        }else
            error.add("Illegal EXPRESION CONSTANT structure");
    }
}
