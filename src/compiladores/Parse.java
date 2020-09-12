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
                                            }
                                        }else
                                            error.add("Illegal FOR structure");     
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
                //aqui va 
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
    
    }
}
