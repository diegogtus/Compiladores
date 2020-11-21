/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import static compiladores.Tokenizer.TokenType.ELSE;
import static compiladores.Tokenizer.TokenType.ERROR;
import static compiladores.Tokenizer.TokenType.ID;
import static compiladores.Tokenizer.TokenType.SYCLOSECURLYBRAKET;
import static compiladores.Tokenizer.TokenType.SYCLOSEPARENTHESES;
import static compiladores.Tokenizer.TokenType.SYCOLON;
import static compiladores.Tokenizer.TokenType.SYCOMMA;
import static compiladores.Tokenizer.TokenType.SYDOT;
import static compiladores.Tokenizer.TokenType.SYEQUALS;
import static compiladores.Tokenizer.TokenType.SYOPENCURLYBRAKET;
import static compiladores.Tokenizer.TokenType.SYSEMICOLON;
import static compiladores.Tokenizer.TokenType.WHILE;
import static compiladores.Tokenizer.TokenType.FOR;
import static compiladores.Tokenizer.TokenType.IF;
import static compiladores.Tokenizer.TokenType.INTERFACE;
import static compiladores.Tokenizer.TokenType.SYOPENPARENTHESES;
import static compiladores.Tokenizer.TokenType.VOID;
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
            do{
                DECL(token);
//                token.remove(0);
            }while(token.size() != 0);
        }
     }
     //vERIFICAR ";" De final de asignación --> EXP;
      public void DECL (ArrayList<Tokenizer.Token> token){
        if (token.size() != 0) {
            System.out.println("Token 1: " + token.get(0).data);
          switch(token.get(0).type) {
              case INT:
              case DOUBLERESERVED:
              case BOOL:
              case STRINGRESERVED:
              case ID:
              case VOID:
                  //token.remove(0);
                  switch(token.get(2).type) {
                    case SYSEMICOLON:
                    case SYSQUAREBRAKET:
                        VARIABLEDEC(token);
                        break;
                    default:
                        FUNCTIONDECL(token);
                        break;
                  }
              
              default:
                  if (token.size() > 1) {
                      System.out.println("Token 1: " + token.get(0).data);
                    switch(token.get(0).type) {
                        case CLASS:
                            CLASSDECL(token);
                            break;
                        case CONST:
                            CONSTDECL(token);
                            break;
                        case INTERFACE:
                            INTERFACEDECL(token);
                            break;
                        default:
                            //error.add("ID Invalid in Declaration. " + token.get(0).toError());
                            if (token.get(0).type == SYOPENCURLYBRAKET || token.get(0).type == SYCLOSECURLYBRAKET) {
                                error.add("ID Invalid in Declaration. " + token.get(0).toError());
                                token.remove(0);
                            }
                            break;
                    }
                        
                   }
                  break;
          }
        } 
      }
      
      public void CLASSDECL (ArrayList <Tokenizer.Token> token){
          if (token.size()!=0) {
            switch(token.get(0).type) {               
             case CLASS:
                 token.remove(0);
                 if (token.size() != 0) {   
                        switch(token.get(0).type){
                            case ID:
                                token.remove(0);
                               if(token.size()!= 0){
                                    if(token.get(0).type == SYCOLON){
                                        token.remove(0);
                                        //EXPR(token);
                                        //ClassDecl  class ident <: ident> < , ident+ ,> {Field*}
                                        if (token.size() != 0) {
                                            switch(token.get(0).type){
                                                case ID:
                                                    token.remove(0);
                                                    if (token.get(0).type == SYCOMMA) {
                                                        token.remove(0);
                                                        while(token.get(0).type == ID && token.size() != 0){
                                                            token.remove(0);
                                                            if (token.size() != 0 ) {
                                                                if (token.get(0).type == SYCOMMA) {
                                                                    token.remove(0);
                                                                }else{
                                                                    error.add("ID Invalid in Class Declaration. " + token.get(0).toError());
                                                                    break;
                                                                }
                                                            }else{
                                                                error.add("Class Declaration incomplete. " + token.get(0).toError());
                                                            }
                                                        }
                                                    }else{
                                                        error.add("ID Invalid in Class Declaration. " + token.get(0).toError());
                                                       // if (token.get(0).type == ID) {
//                                                            token.remove(0);
//                                                        }
                                                       while(token.get(0).type != SYOPENCURLYBRAKET && token.size() != 0){
                                                           token.remove(0);
                                                       }
                                                       //STATEMENT(token);
                                                    }
                                                    break;
                                                default:
                                                    error.add("Missing ID at the Class Declaration. " + token.get(0).getLine());
                                                    break;
                                            }
                                        }
                                    }else if(token.get(0).type == SYOPENCURLYBRAKET) {
                                       token.remove(0);
                                       //Agregar Método
                                       FIELD(token);
                                        if (token.size() != 0) {
                                            if (token.get(0).type == SYCLOSECURLYBRAKET) {
                                                token.remove(0);
                                            }else{
                                                error.add("Missing SYCLOSECURLYBRAKET at the end of the CLASSDECL." + token.get(0).toError());
                                            }
                                        }
                                   }else{
                                        error.add("Missing SYCLOSECURLYBRAKET at the end of the CLASSDECL." + token.get(0).toError());
                                    }
                                }
                                break;
                            default:
                                 error.add("Illegal CLASSDECL structure: " + token.get(0).toError());
                                break;
                        }
                    }else{
                        error.add("Illegal EXPRESION CLASSDECL structure: " + token.get(0).getLine());
                    }
                 break;
             default:
                 error.add("Illegal EXPRESION in CLASSDECL structure: " + token.get(0).getLine());
                 break;
            }
          }
      }
      
       public void FIELD (ArrayList<Tokenizer.Token> token){
            if (token.size() != 0) {
                switch(token.get(0).type) {
                    case CONST:
                        CONSTDECL(token);
                        break;
                    default:
                        switch(token.get(0).type) {
                            case INT:
                            case DOUBLE:
                            case BOOL:
                            case STRING:
                            case ID:
                                if (token.size()> 1) {
                                    switch(token.get(2).type) {
                                     case SYSEMICOLON:
                                      VARIABLEDEC(token);
                                      break;
                                  case SYOPENPARENTHESES:
                                      FUNCTIONDECL(token);
                                      break;
                                  default:
                                      error.add("Illegal EXPRESION in FIELD structure: " + token.get(0).getLine());
                                      break;
                                    }   
                                }
                            break;
                            case VOID:
                                FUNCTIONDECL(token);
                            break;
                        default:
                             error.add("Illegal EXPRESION in FIELD structure: " + token.get(0).getLine());
                            break;
                        }
                }
            }
        }
      
       public void VARIABLEDEC (ArrayList<Tokenizer.Token> token){
        //if (token.size() != 0) {
        while((token.get(2).type == SYSEMICOLON) || (token.get(4).type == SYSEMICOLON)){
            VARIABLE(token);
            if (token.size() != 0) {
                switch(token.get(0).type) {               
                 case SYSEMICOLON:
                     token.remove(0);
                     break;
                 default:
                     error.add("Illegal EXPRESION VARIABLEDECL structure: " + token.get(0).getLine());
                     break;
               }
            }
        }
       switch(token.get(0).type){
           case IF:
           case FOR:
           case WHILE:
           case FOREACH:
           case CONSOLE:
               STATEMENT(token);
           break;
           case CONST:
               CONSTDECL(token);
               break;
           case ID:
               STATEMENT(token);
               break;
        }
       }
       
       /*
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
            //Evaluar Prototype --> WVR
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
                    if (token.size() > 0) {
                        token.remove(0);
                    }
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
//                                             Ocurrencia: puede o no venir
                                            while(token.get(1).type == ID){
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
        
        //if (token.size()!= 0) {
             //while(token.size()>1){
                //PROTOTYPE(token);
            //}
        //}
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
                                    TYPEp(token);
                                    break;
                                 default:
                                    error.add("Illegal EXPRESION TYPE structure: " + token.get(0).getLine());
                                    break;
                            }  
                        }      
                     //TYPEp(token);
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
                                            STMTBLOCK(token);
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
     //while(!token.isEmpty()){
     //   if (token.size() != 0) {
     while(token.get(0).type != SYCLOSECURLYBRAKET){
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
           case ID:
               //token.remove(0);
               if (token.get(1).type == SYOPENPARENTHESES || token.get(3).type == SYOPENPARENTHESES) {
                   token.remove(0);
                   CALLSTMT(token);
               }else{
                   
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
               break;
           case SYOPENCURLYBRAKET:
                   STMTBLOCK(token);
                   break;
           case BREAK:
               token.remove(0);
               if (token.size() != 0) {
                   if (token.get(0).type == SYSEMICOLON) {
                       token.remove(0);
                   }else{
                       error.add("Missing semicolon" +  token.get(0).getLineSimicolon());
                   }
               }
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
                                        if (token.size()!=0) {
                                            STATEMENT(token);
                                            if (token.size() != 0 && token.size() >1) {
                                                if (token.get(1).type == ELSE) { //VERIFICAR ELSE
                                                token.remove(0);
                                                token.remove(0);
                                                STATEMENT(token);
                                                }
                                            }
                                            
//                                           }else{    
//                                                error.add("Illegal IF structure: " + token.get(0).getLine());
//                                            }
                                        }
                                        break;
                                    default:
                                       // error.add("Illegal IF structure: " + token.get(0).toError());
                                        break;
                                }
                            }
                        break;
                    default:
                         //error.add("Illegal IF structure : " + token.get(0).toError());
                        break;
                }
            }else{
                error.add("Illegal IF structure: " + token.get(0).toError());
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
        if (token.get(1).type == SYEQUALS) {
            //token.remove(0);
            LVALUE(token);
            if (token.size() != 0) {
                if (token.get(0).type == SYEQUALS) {
                    token.remove(0);
                    EXPR(token);
                }else{
                    error.add("Error to ASSIGNE variable in EXPR " +token.get(0).getLine());
                }
            }
        }else{
        EXPRAND(token);
        EXPRp(token);
        }
    }
      public void EXPRp (ArrayList<Tokenizer.Token> token){
         if(token.size()!= 0){
            switch(token.get(0).type){
              case SYOR:
                  token.remove(0);
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

    private void STMTBLOCK(ArrayList<Tokenizer.Token> token) {
        if (token.size() != 0) {
            if (token.get(0).type == SYOPENCURLYBRAKET) {
                token.remove(0);
                switch(token.get(0).type){
                    case CONST:
                        CONSTDECL(token);
                        break;
                    case IF:
                    case FOR:
                    case WHILE:
                    case BREAK:
                    case CONSOLE:
                    case RETURN:
                        STATEMENT(token);
                        break;
                    default:
                        if (token.get(1).type == SYEQUALS || token.get(2).type == SYDOT || token.get(0).type == SYOPENCURLYBRAKET) {
                            STATEMENT(token);
                        }else{
                            VARIABLEDEC(token);
                        }
                    break;
                }
                
                if (token.size() != 0) {
                    if (token.get(0).type == SYCLOSECURLYBRAKET) {
                        token.remove(0);
                    }else{
                        error.add("Missing SYCLOSECURLYBRAKET in STMTBLOCK " +structure +token.get(0).getLine());
                    }
                }else{
                    error.add("Error in STMTBLOCK structure " +token.get(0).getLine());
                }
            }else{
                error.add("Missing SYOPENCURLYBRAKET in STMTBLOCK " +structure +token.get(0).getLine());
            }
            
        }
    }
    
public void CALLSTMT(ArrayList<Tokenizer.Token> token){
    System.out.println("Income to CALLSTMT... ");
        if (token.size() != 0) {
            switch(token.get(0).type){
                case SYOPENPARENTHESES:
                    token.remove(0);
                    if (token.size() != 0 ) {
                        ACTUALS(token);
                        if (token.get(0).type == SYCLOSEPARENTHESES) {
                            token.remove(0);
                        }else{
                            error.add("Missing SYCLOSEPARENTHESES inside CALLSTMT structure " +token.get(0).getLine());
                        }
                    }else{
                        error.add("Missing expression inside CALLSTMT structure " +structure +token.get(0).getLine());
                    }
                    break;
                case SYDOT:
                    token.remove(0);
                    if (token.size() != 0) {
                        if (token.get(0).type == ID) {
                            token.remove(0);
                            ACTUALS(token);
                            if (token.get(0).type == SYCLOSEPARENTHESES) {
                            token.remove(0);
                            }else{
                                error.add("Missing SYCLOSEPARENTHESES inside CALLSTMT structure " +token.get(0).getLine());
                            }
                        }else{
                            error.add("Missing expression inside CALLSTMT structure " +structure +token.get(0). getLineForError());
                        }
                    }
                    break;
                default:
                    error.add("Missing expression inside CALLSTMT structure " +structure +token.get(0). getLineForError());
                    break;
            }
        }
    }

    private void ACTUALS(ArrayList<Tokenizer.Token> token) {
        if (token.size() != 0) {
            EXPR(token);
            if (token.get(0).type == SYCOMMA) {
                token.remove(0);
                ACTUALS(token);
            }else{
                error.add("Error in Argument for function is invalid " + token.get(0).getLine());
                return;
            }
        }
    }

    private void LVALUE(ArrayList<Tokenizer.Token> token) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (token.size() != 0) {
            if (token.get(0).type == ID) {
                token.remove(0);
            }else{
                EXPR(token);
                if (token.size() != 0) {
                    if (token.get(0).type == SYDOT) {
                        token.remove(0);
                        LVALUE(token);
                    }else{
                        error.add("Error to ASSIGNE variable " +token.get(0).getLine());
                    }
                }
            }
        }
    }
}
