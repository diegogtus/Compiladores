/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import static compiladores.Tokenizer.TokenType.ERROR;
import static compiladores.Tokenizer.TokenType.SYCLOSEPARENTHESES;
import static compiladores.Tokenizer.TokenType.SYCOLON;
import static compiladores.Tokenizer.TokenType.SYEQUALS;
import static compiladores.Tokenizer.TokenType.SYSEMICOLON;
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
        String structure;
        boolean optionalTructure = true;
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
               structure = "WHILE";
               WHILE(token);
               break;
           case FOR:
               token.remove(0);
               structure = "FOR";
               FOR(token);
               break;
           case SYSEMICOLON:
               token.remove(0);
               break;
           default:
               if(token.get(0).type == ERROR ){
                    error.add("Illegal statement: " +  token.get(0).toError());
                    token.remove(0);
                    break;
               }
               EXPR(token);
               if(token.get(0).type == SYSEMICOLON)
                   token.remove(0);
               else
                   error.add("Missing semicolon" +  token.get(0).getLine());
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
                                optionalTructure = true;
                                EXPR(token);
                                switch(token.get(0).type) {
                                    case SYSEMICOLON:
                                        token.remove(0);
                                        if(token.size()!= 0){
                                            optionalTructure = false;
                                            EXPR(token);
                                            switch(token.get(0).type) {
                                                case SYSEMICOLON:
                                                    token.remove(0);
                                                    if(token.size()!= 0){
                                                        optionalTructure = true;
                                                        EXPR(token);
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
                                                     findParenthesis(token);
                                                    break;    
                                            }
                                        }else
                                            error.add("Illegal FOR structure");     
                                        break;
                                    default:
                                        error.add("Illegal FOR structure: " + token.get(0).toError());
                                        findParenthesis(token);
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
    public void findParenthesis (ArrayList<Tokenizer.Token> token){
    while(token.get(0).type != SYCLOSEPARENTHESES){
        token.remove(0);
    }
      token.remove(0);
}
    public void WHILE (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case SYOPENPARENTHESES:
                    token.remove(0);                    
                    if(token.size()!= 0){
                        EXPR(token);
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
        EXPRAND(token);
        EXPRp(token);
    }
      public void EXPRp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYOR:
                
                EXPRAND(token);
                EXPRp(token);
                break;
            default:
                break;
        }
    }
    public void EXPRAND (ArrayList<Tokenizer.Token> token){
        EXPREQUALS(token);
        EXPRANDp(token);
    }
    public void EXPRANDp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYAND:
                token.remove(0);
                EXPREQUALS(token);
                EXPRANDp(token);
                break;
            default:
                break;
        }
    }
    public void EXPREQUALS (ArrayList<Tokenizer.Token> token){
          EXPRREL(token);
          EXPREQUALSp(token);
    }
    public void EXPREQUALSp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYDOUBLEEQUALS:
                token.remove(0);
                EXPRREL(token);
                EXPREQUALSp(token);
                break;
            case SYDIFERENT:
                token.remove(0);
                EXPRREL(token);
                EXPREQUALSp(token);
                break;
            default:
                break;
        }
    }
    public void EXPRREL (ArrayList<Tokenizer.Token> token){
          EXPRADD(token);
          EXPRRELp(token);
    }
    public void EXPRRELp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYGRATEREQUALSTHAN:
                token.remove(0);
                EXPRADD(token);
                EXPRRELp(token);
                break;
            case SYEQUALSLOWERTHAN:
                token.remove(0);
                EXPRADD(token);
                EXPRRELp(token);
                break;
            case SYLOWERTHAN:
                token.remove(0);
                EXPRADD(token);
                EXPRRELp(token);
                break;
            case SYGRATERTHAN:
                token.remove(0);
                EXPRADD(token);
                EXPRRELp(token);
                break;
            default:
                break;
        }
    }
    
    public void EXPRADD (ArrayList<Tokenizer.Token> token){
          EXPRMUL(token);
          EXPRADDp(token);
    }
    public void EXPRADDp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYMADD:
                token.remove(0);
                EXPRMUL(token);
                EXPRADDp(token);
                break;
            case SYMSUB:
                token.remove(0);
                EXPRMUL(token);
                EXPRADDp(token);
                break;
            default:
                break;
        }
    }
    public void EXPRMUL (ArrayList<Tokenizer.Token> token){
          EXPRUN(token);
          EXPRMULp(token);
    }
    public void EXPRMULp (ArrayList<Tokenizer.Token> token){
        switch(token.get(0).type){
            case SYASTERISK:
                token.remove(0);
                EXPRUN(token);
                EXPRMULp(token);
                break;
            case SYSLASH:
                token.remove(0);
                EXPRUN(token);
                EXPRMULp(token);
                break;
             case SYPERCENTAGE:
                token.remove(0);
                EXPRUN(token);
                EXPRMULp(token);
                break;
            default:
                break;
        }
    }
    public void EXPRUN (ArrayList<Tokenizer.Token> token){
         switch(token.get(0).type){
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
    }
     public void EXPREXP(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            switch(token.get(0).type) {
                case SYOPENPARENTHESES:
                    token.remove(0);
                    if(token.size()!= 0){
                        EXPR(token);
                        switch(token.get(0).type) {
                            case SYCLOSEPARENTHESES:
                                token.remove(0);
                                break;
                            default:
                                error.add("Illegal EXPRESION EXP structure: " + token.get(0).toError());
                                break;
                        }
                    }else
                        error.add("Illegal EXPRESION EXP structure");
                    break;
                
                case NEW:
                    token.remove(0);
                    if(token.size()!= 0){
                        switch(token.get(0).type){
                            case SYOPENPARENTHESES:
                                token.remove(0);
                                if(token.size()!= 0){
                                    switch(token.get(0).type){
                                        case ID:
                                            token.remove(0);
                                            if(token.size()!= 0){
                                                    switch(token.get(0).type){
                                                        case SYCLOSEPARENTHESES:
                                                            token.remove(0);
                                                            break;
                                                         default:
                                                            error.add("Illegal EXPRESION EXP structure: " + token.get(0).getLine());
                                                            break;
                                                    }
                                            }
                                            break;
                                         default:
                                            error.add("Illegal EXPRESION EXP structure: " + token.get(0).getLine());
                                            break;
                                    }
                                }else
                                    error.add("Illegal EXPRESION EXP structure");
                                break;
                            default:
                                error.add("Illegal EXPRESION EXP structure: " + token.get(0).getLine());
                                break;
                        }
                      
                    }else
                        error.add("Illegal EXPRESION EXP structure");
                    break;
                default:
                   
                   CONSTANT(token);
                    if(token.size()!= 0){
                        switch(token.get(0).type){
                            case SYDOT:
                                token.remove(0);
                                if(token.size()!= 0){
                                    switch(token.get(0).type){
                                        case ID:
                                            token.remove(0);
                                            if(token.size()>1){
                                                if(token.get(0).type == SYEQUALS){
                                                    token.remove(0);
                                                    EXPR(token);
                                                }
                                            }
                                            break;
                                        default:
                                            token.remove(0);
                                            error.add("Malformed expression afted the DOT"+  token.get(0).getLine());
                                            if(token.size()>1){
                                                if(token.get(0).type == SYEQUALS){
                                                    token.remove(0);
                                                    EXPR(token);
                                                }
                                            }
                                            break;
                                    }
                                }
                                break;
                            case SYBRAKETOPEN:
                                token.remove(0);
                                     if(token.size()!= 0){
                                         EXPR(token);
                                    switch(token.get(0).type){
                                        case SYBRAKETCLOSE:
                                            token.remove(0);
                                            break;
                                        default:
                                            error.add("Malformed expression afted the DOT"+ token.get(0).getLine());
                                            break;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
            }
        }else
            error.add("Illegal EXPRESION EXP structure"+token.get(0).getLine());
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
                case THIS:
                    token.remove(0);
                    break;
                case HEXA:
                    token.remove(0);
                case DECIMAL:
                    token.remove(0);
                     break;
                case STRING:
                    token.remove(0);
                    break;
                case BOOLEAN:
                    token.remove(0);
                    break;
                case ID:
                       token.remove(0);
                        if(token.size()>1){
                            if(token.get(0).type == SYEQUALS){
                                    token.remove(0);
                                    EXPR(token);
                                }
                            }
                       break;
                default:
                    if(!optionalTructure)                        
                        error.add("Missing expression inside structure " +structure +token.get(0).getLine());
                    optionalTructure = true;
                    //token.remove(0);
                    break;
            }
        }else
            error.add("Illegal EXPRESION CONSTANT structure"+token.get(0).getLine());
    }
}
