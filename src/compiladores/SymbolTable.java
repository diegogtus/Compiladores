/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.util.HashMap;

/**
 *
 * @author diego
 */
public class SymbolTable{
    
    public static class Values{
        //public final String name;
        public String type="";
        public String attribute="";
        public String value="";


         public Values( ) {
         
        }
        public Values(  String Type,  String Attribute,  String Value) {
            this.type = Type;
            this.attribute = Attribute;
            this.value = Value;
        }
       
    }
    HashMap<String, Values> symTable;
    SymbolTable(){
        symTable = new HashMap<String, Values>();
    }
    
}
