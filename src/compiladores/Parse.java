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
         Tokenizer.Token LastToken;
       // boolean optionalTructure = true;
    public Parse() {
        error = new ArrayList<String>();
    }
        
    public  ArrayList<String> parse(ArrayList<Tokenizer.Token> token) {
        //STATEMENT(token);
        //FUNCTION();
        //FUNCTIONDECL(token);
        PROGRAM(token);
        return error;
    }
     public void PROGRAM(ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            DECL(token);
        }
     }
      public void DECL (ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
          switch(token.get(0).type) {
              case INT:
              case DOUBLE:
              case BOOL:
              case STRING:
              case IDENT:
                  //token.remove(0);
                  switch(token.get(1).type) {
                    case SYSEMICOLON:
                    case SYSQUAREBRAKET:
                        VARIABLE(token);
                        break;
                    default:
                        FUNCTIONDECL(token);
                        break;
                  }
              
              default:
                  if (token.size() > 1) {
                    switch(token.get(1).type) {
                        case CLASS:
                            //CLASSDECL(token);
                            break;
                        case CONST:
                            CONSTDECL(token);
                            break;
                        case INTERFACE:
                            INTERFACEDECL(token);
                            break;
                    }
                        
                   }
                  break;
          }
        }
        
      }
      /* public void FUNCTDECL (ArrayList<Tokenizer.Token> token){
            if (token.size() != 0) {
                VARIABLE(token);
            }
        }
       public void VARIABLEDEC (ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            VARIABLE(token);
            
        }
     }
     public void VARIABLE (ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            TYPE(token);
             if (token.size() != 0) {
                switch(token.get(0).type) {               
                 case ID:
                     token.remove(0);
                     break;
                 default:
                     error.add("Illegal EXPRESION TYPE structure: " + token.get(0).getLine());
                     break;
               }
            }
        }
        
     }
      public void TYPE (ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {}
            switch(token.get(0).type) {
              case INT:
                  token.remove(0);
                  break;
              case DOUBLE:
                  token.remove(0);
                  break;
              case BOOL:
                  token.remove(0);
                  break;
              case STRING:
                  token.remove(0);
                  break;
              case ID:
                  token.remove(0);
                  break;
              default:
                  if (token.size() > 1) {}
                    switch(token.get(1).type) {
                            case SYSQUAREBRAKET:
                                token.remove(1);
                                TYPE(token);
                                break;
                            default:
                                break;
                    }
                  break;
          }
      }*/
    /*FunctionDecl*/
     public void CONSTTYPE(ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            switch (token.get(0).type){
                case INT:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case DOUBLERESERVED:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case BOOL:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case STRINGRESERVED:
                    token.remove(0);
                    TYPEp(token);
                    break;
//                case ID:
//                    token.remove(0);
//                    TYPEp(token);
//                    break;
                default:
                    error.add("Illegal EXPRESION CONSTTYPE structure: " + token.get(0).getLine());
                    break;
            }
        }else{
            error.add("Illegal EXPRESION TYPE structure"+token.get(0).getLine());
        }
    }
    
    public void CONSTDECL(ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            switch (token.get(0).type){
                case CONST:
                    token.remove(0);
                    if (token.size() != 0) {   
                        CONSTTYPE(token);
                        switch(token.get(0).type){
                            case ID:
                                token.remove(0);
                               if(token.size()!=0){
                                    if(token.get(0).type == SYSEMICOLON){
                                        token.remove(0);
                                        //EXPR(token);
                                    }else{
                                        error.add("Missing semicolon at the end of the CONSTDECL." + token.get(0).toError());
                                    }
                                }
                                break;
                            default:
                                 error.add("Illegal CONSTDECL structure: " + token.get(0).toError());
                                break;
                        }
                    }else{
                        error.add("Illegal EXPRESION CONSTDECL structure: " + token.get(0).getLine());
                    }
                    break;
            }
        }else{
            error.add("Illegal EXPRESION CONSTDECL structure"+token.get(0).getLine());
        }
    }
    
    public void PROTOTYPE(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            TYPE(token);
            switch(token.get(0).type){
                case VOID:
                    token.remove(0);
                    break;
                default:
                     //error.add("Illegal PROTOTYPE structure: " + token.get(0).toError());
                    break;
            }
            //if (token.size() != 0) {
            PROTOTYPEp(token);
            //}
            
        }else{
            error.add("Illegal PROTOTYPE structure: " + token.get(0).toError());
        }
        
    }
    
    public void PROTOTYPEp(ArrayList<Tokenizer.Token> token){
        if (token.size()!= 0) {
            switch(token.get(0).type){
                case ID:
                    token.remove(0);
                    if (token.size() != 0) {
                        switch(token.get(0).type){
                            case SYOPENPARENTHESES:
                                token.remove(0);
                                if (token.size()!= 0) {
                                    FORMALS(token);
                                    switch(token.get(0).type){
                                        case SYCLOSEPARENTHESES:
                                            token.remove(0);
                                            if(token.size()!=0){
                                                 if(token.get(0).type == SYSEMICOLON){
                                                     token.remove(0);
                                                     //EXPR(token);
                                                 }else{
                                                     error.add("Missing semicolon at the end of the CONSTDECL." + token.get(0).toError());
                                                 }
                                             }
                                             break;
                                        default:
                                             error.add("Illegal PROTOTYPEp structure: " + token.get(0).toError());
                                            break;
                                    }
                                }else{
                                     error.add("Illegal PROTOTYPEp structure: " + token.get(0).toError());
                                }
                                break;
                            default:
                                 error.add("Illegal PROTOTYPEp structure: " + token.get(0).toError());
                                break;
                                
                        }
                    }else{
                        error.add("Illegal PROTOTYPEp structure: " + token.get(0).toError());
                    }
                    break;
                default:
                    error.add("Missing ID at the end of the PROTOTYPEp: " + token.get(0).toError());
                    break;
            }
        }else{
            error.add("Illegal PROTOTYPEp structure: " + token.get(0).toError());
        }
        
    }
    
    
    public void INTERFACEDECL(ArrayList<Tokenizer.Token> token){
        if (token.size()!= 0) {
            switch(token.get(0).type){
                case INTERFACE:
                    token.remove(0);
                    if (token.size() != 0) {
                        switch(token.get(0).type){
                            case ID:
                                token.remove(0);
                                if (token.size()!= 0) {
                                    //FORMALS(token);
                                    switch(token.get(0).type){
                                        case SYOPENCURLYBRAKET:
                                             token.remove(0);
                                             //Ocurrencia: puede o no venir
                                            while(token.size()>1){
                                                PROTOTYPE(token);
                                            }
                                            if(token.size()!=0){
                                                //PROTOTYPE(token);
                                                 //if(token.size()> 1){
                                                     switch(token.get(0).type){
                                                        case SYCLOSECURLYBRAKET:
                                                             token.remove(0);
                                                             break;
                                                        default:
                                                            //error.add("Missing SYCLOSECURLYBRAKET at the end of the INTERFACEDECL.");
                                                            break;
                                                     }
                                                 //}
                                             }else{
                                                     error.add("Missing SYOPENCURLYBRAKET at the end of the INTERFACEDECL." + token.get(0).toError());
                                                 }
                                             break;
                                        default:
                                             error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
                                         
                                            break;
                                    }
                                }else{
                                     error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
                                }
                                break;
                            default:
                                 error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
                                 
                            break;
                                
                        }
                    }else{
                        error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
                    }
                    break;
                default:
                    error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
                    break;
            }
        }else{
            error.add("Illegal INTERFACEDECL structure: " + token.get(0).toError());
        }
        
        if (token.size()!= 0) {
             while(token.size()>1){
                PROTOTYPE(token);
            }
        }
    } 
     
    public void TYPEp(ArrayList<Tokenizer.Token> token) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         if(token.size()!= 0){
             switch(token.get(0).type) {
                 case SYBRAKETOPEN:
                     token.remove(0);
                        if(token.size()!= 0){
                            switch(token.get(0).type){
                                case SYBRAKETCLOSE:
                                    token.remove(0);
                                    break;
                                 default:
                                    error.add("Illegal EXPRESION TYPE structure: " + token.get(0).getLine());
                                    break;
                            }  
                        }      
                     TYPEp(token);
                     break;
                 default:
                     break;
             }
         }
    }
    
    public void TYPE(ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            switch (token.get(0).type){
                case INT:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case DOUBLERESERVED:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case BOOL:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case STRINGRESERVED:
                    token.remove(0);
                    TYPEp(token);
                    break;
                case ID:
                    token.remove(0);
                    TYPEp(token);
                    break;
                default:
                    //error.add("Illegal EXPRESION TYPE structure: " + token.get(0).getLine());
                    break;
            }
        }else{
            error.add("Illegal EXPRESION TYPE structure"+token.get(0).getLine());
        }
    }
    
    public void FORMALS(ArrayList<Tokenizer.Token> token){
        //while(!token.isEmpty()){
        if (token.size() != 0) {
            VARIABLE(token);
            FORMALSp(token);
        }else{
            error.add("Illegal EXPRESION FORMALS structure: " + token.get(0).getLine());
        }
    }
    
    public void FORMALSp(ArrayList<Tokenizer.Token> token){
        //while(!token.isEmpty()){
        if (token.size() != 0) {
            switch(token.get(0).type){
               case SYCOMMA:
                   token.remove(0);
                   if (token.size() != 0) {
                       FORMALS(token);
                   }
                   break;
               default:
                   //error.add("Illegal EXPRESION FORMALSp structure: " + token.get(0).getLine());
                   //token.remove(0);
                   break;
           } 
        }else{
            error.add("Illegal EXPRESION FORMALSp structure: " + token.get(0).getLine());
        }
            
        //}
    }
    
    public void VARIABLE(ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            TYPE(token);
           switch(token.get(0).type){
               case ID:
                   token.remove(0);
                   break;
               default:
                   error.add("Illegal EXPRESION VARIABLE structure: " + token.get(0).getLine());
                   break;
           } 
        }else{
            error.add("Illegal EXPRESION VARIABLE structure: " + token.get(0).getLine());
        }
    }
    
    
    public void FUNCTIONDECL(ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
            TYPE(token);
            switch(token.get(0).type){
                case VOID:
                    token.remove(0);
                    break;
                default:
                     error.add("Illegal FUNCTIONDECL structure: " + token.get(0).toError());
                    break;
            }
            //if (token.size() != 0) {
            FUNCTIONDECLp(token);
            //}
            
        }else{
            error.add("Illegal FUNCTIONDECL structure: " + token.get(0).toError());
        }
        
    }
    
    public void FUNCTIONDECLp(ArrayList<Tokenizer.Token> token){
        if (token.size()!= 0) {
            switch(token.get(0).type){
                case ID:
                    token.remove(0);
                    if (token.size() != 0) {
                        switch(token.get(0).type){
                            case SYOPENPARENTHESES:
                                token.remove(0);
                                if (token.size()!= 0) {
                                    FORMALS(token);
                                    switch(token.get(0).type){
                                        case SYCLOSEPARENTHESES:
                                            token.remove(0);
                                           // STMTBLOCK(token);
                                            break;
                                        default:
                                             error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
                                            break;
                                    }
                                }else{
                                     error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
                                }
                                break;
                            default:
                                 error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
                                break;
                                
                        }
                    }else{
                        error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
                    }
                    break;
                default:
                    error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
                    break;
            }
        }else{
            error.add("Illegal FUNCTIONDECLp structure: " + token.get(0).toError());
        }
    }
/*CODIGO WALTER*/
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
           case IF:
               token.remove(0);
               structure = "IF";
               IF(token);
               break;
           case ELSE:
                token.remove(0);
               structure = "ELSE";
               ELSE(token);
               break;
           case RETURN:
                token.remove(0);
               structure = "RETURN";
               RETURN(token);
               break;
           case CONSOLE:
                token.remove(0);
               structure = "Console";
                PRINT(token);
               break;
           case PRINT:
                token.remove(0);
               structure = "PRINT";
               PRINT(token);
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
                if(token.size()!= 0){
                    if(token.get(0).type == SYSEMICOLON)
                        token.remove(0);
                    else
                        error.add("Missing semicolon" +  token.get(0).getLineSimicolon());
                    if(token.size()!= 0){
                        if(LastToken == token.get(0))
                            token.remove(0);
                        if(token.size()!= 0)
                            LastToken = token.get(0);
                    }
                    break;
                }error.add("Missing semicolon at the end of the file" );
        } 
    }
 }

    public void PRINT (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
              switch(token.get(0).type) {
                      case SYDOT:
                           token.remove(0);
                           if(token.size()!= 0){
                                switch(token.get(0).type) {
                                        case WRITELINE:
                                             token.remove(0);
                                             if(token.size()!= 0){
                                                    switch(token.get(0).type) {
                                                            case  SYOPENPARENTHESES:
                                                                 token.remove(0);
                                                                 EXPR(token);
                                                                 if(token.size()!= 0){
                                                                    switch(token.get(0).type) {
                                                                            case  SYCLOSEPARENTHESES:
                                                                                 token.remove(0);
                                                                                 if(token.size()!= 0){
                                                                                    switch(token.get(0).type) {
                                                                                            case  SYSEMICOLON:
                                                                                                 token.remove(0);
                                                                                            break;

                                                                                    }
                                                                                } 
                                                                            break;

                                                                    }
                                                                }                   
                                                                 break;

                                                    }
                                               }
                                             break;
                                             
                                }
                           }
                          break;
                      default:
                          error.add("Malformed CONSOLE statement" );
                          break;
                    }
         }
         
    }
     public void BREAK (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
                    switch(token.get(0).type) {
                      case SYSEMICOLON:
                           token.remove(0);
                          break;
                      default:
                          error.add("Missing semicolon at the end of BREAK statement" );
                          break;
                    }
                }else
                    error.add("Missing semicolon at the end of BREAK statement" );
     }
    public void RETURN (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
                EXPR(token);
                if(token.size()!= 0){
                    switch(token.get(0).type) {
                      case SYSEMICOLON:
                           token.remove(0);
                          break;
                      default:
                          error.add("Missing semicolon at the end of RETURN statement" );
                          break;
                    }
                }else
                    error.add("Missing semicolon at the end of RETURN statement" );
         }
    }
    public void ELSE (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
                STATEMENT(token);  
         }
    }
    public void IF (ArrayList<Tokenizer.Token> token){
            if(token.size()!= 0){
                switch(token.get(0).type){
                    case SYOPENPARENTHESES:
                        token.remove(0);
                            if(token.size()!= 0){
                                EXPR(token);
                                switch(token.get(0).type) {
                                    case SYCLOSEPARENTHESES:
                                        token.remove(0);
                                        STATEMENT(token);
                                        break;
                                    default:
                                        error.add("Illegal IF structure: " + token.get(0).toError());
                                        break;
                                }
                            }
                        break;
                    default:
                         error.add("Illegal IF structure: " + token.get(0).toError());
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
                               // optionalTructure = true;
                                EXPR(token);
                                switch(token.get(0).type) {
                                    case SYSEMICOLON:
                                        token.remove(0);
                                        if(token.size()!= 0){
                                          //  optionalTructure = false;
                                            EXPR(token);
                                            switch(token.get(0).type) {
                                                case SYSEMICOLON:
                                                    token.remove(0);
                                                    if(token.size()!= 0){
                                                      //  optionalTructure = true;
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
         if(token.size()!= 0){
            switch(token.get(0).type){
              case SYOR:

                  EXPRAND(token);
                  EXPRp(token);
                  break;
              default:
                  break;
          }
        }
    }
    public void EXPRAND (ArrayList<Tokenizer.Token> token){
        EXPREQUALS(token);
        EXPRANDp(token);
    }
    public void EXPRANDp (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){   
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
    }
    public void EXPREQUALS (ArrayList<Tokenizer.Token> token){
          EXPRREL(token);
          EXPREQUALSp(token);
    }
    public void EXPREQUALSp (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
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
    }
    public void EXPRREL (ArrayList<Tokenizer.Token> token){
          EXPRADD(token);
          EXPRRELp(token);
    }
    public void EXPRRELp (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
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
    }
    
    public void EXPRADD (ArrayList<Tokenizer.Token> token){
          EXPRMUL(token);
          EXPRADDp(token);
    }
    public void EXPRADDp (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
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
    }
    public void EXPRMUL (ArrayList<Tokenizer.Token> token){
          EXPRUN(token);
          EXPRMULp(token);
    }
    public void EXPRMULp (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
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
    }
    public void EXPRUN (ArrayList<Tokenizer.Token> token){
        if(token.size()!= 0){
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
                  //  if(!optionalTructure)                        
                        error.add("Missing expression inside structure " +structure +token.get(0). getLineForError());
                   // optionalTructure = true;
                    //token.remove(0);
                    break;
            }
        }else
            error.add("Illegal EXPRESION CONSTANT structure"+token.get(0).getLine());
    }
}
