# Compiladores
Proyecto de compiladores 2020

## Descripción:
La fase 1 del proyecto consiste en realizar un analizador léxico para C#,
en el que debe validar todos las palabras reservadas del lenguaje, identificadores,
digitos, decimales, cadenas y comentarios.

##	Implementación		
En esta fase se hizo uso de la librería Regex implementando sus herramientas: Matcher
y Pattern, ya que en ellas se comparaban las cadenas del archivo a analizar versus
los patrones definidos para cada Token (Multiline, Single, Reserved, entre otros).
Cada Patrón tiene un orden definido ya que posee jerarquía al estar evaluando cada 
expresión regular, como se muestra en la Clase Tokenizar.java. Así mismo allí se definieron
los errores para cada formato de las cadenas. La clase Lexer.java se utiliza para hacer cada 
comparación entre los tokens y los patrones de Tokenizer, además de llevar el control de
las líneas y columnas que se encuentran analizando. 

```bash
Pattern p = Pattern.compile("a*b");
Matcher m = p.matcher("aaaaab");
boolean b = m.matches();
```
Utiliza la siguiente gramatica:
```bash
Stmt::= for | while | Expr ;

for:== for ( <Expr> : Expr : <Expr> ) Stmt

While:== while ( Expr ) Stmt

Expr ::=Expr || ExprAND
	|ExprAND

ExprAND:== ExprAND && ExprEQUALS
		|ExprEQUALS
	
ExprEQUALS::= ExprEQUALS == ExprREL
		| ExprEQUALS != ExprREL
		| ExprREL
		
ExprREL::= ExprREL >= ExprADD
		| ExprREL <= ExprADD
		| ExprREL > ExprADD
		| ExprREL < ExprADD
		| ExprADD
			
ExprADD::= ExprADD +  ExpMUL
		|  ExprADD - ExpMUL
		|  ExpMUL
		
ExpMUL::=  ExpMUL * ExpUN
		| ExpMUL / ExpUN
		| ExprMUL % ExpUN
		|ExpUN
		
ExpUN:== - ExpEXP
	| !ExpEXP
	| ExpEXP
	
ExpEXP:== ( Expr )
		| Constant
		|LValue
		|this
		|New(ident)
		|LValue = Expr

*********Recursividad a la izquierda eliminada**********
Expr::= ExprAND Expr´

Expr´::= || ExprAND Expr´
		| Ɛ

ExprAND::=ExprEQUALS ExprAND´

ExprAND´::= &&  ExprEQUALS ExprAND´
		| Ɛ

ExprEQUALS::= ExprREL ExprEQUALS´

ExprEQUALS´::= == ExprREL ExprEQUALS´
			| != ExprREL ExprEQUALS´
			| Ɛ

ExprREL::= ExprADD ExprREL´

ExprREL´::= >= ExprADD ExprREL´
		|<= ExprADD ExprREL´
		|> ExprADD ExprREL´
		|< ExprADD ExprREL´
		| Ɛ

ExprADD ::= ExprMUL ExprADD´

ExprADD´ ::= + ExprMUL ExprADD´
		| - ExprMUL ExprADD´
		| Ɛ

ExpMUL::= ExpUN ExpMUL´

ExpMUL´::= * ExpUN ExpMUL´
		| / ExpUN ExpMUL´
		| % ExpUN ExpMUL´
		| Ɛ

ExpUN::= - ExpEXP
	| !ExpEXP
	| ExpEXP

ExpEXP:== ( Expr )
		| Expr [Expr ]
		| Constant
		|LValue
		|this
		|New(ident)
		
```

##	Procedimiento	
Al iniciar el programa, se debe cargar el archivo a analizar con extensión ".txt" o ".frag" 

![](firstScreen.png)

Al abrirlo muestra en la columna izquierda, el contenido del mismo tal cual se encuentra. 

![](secondScreen.png)

Al presionar el botón Analizar muestra un mensaje en el que indica que ha finalizado el análisis
y que generó el archivo con extensión ".out". También se muestra en pantalla en la columna 
derecha el resultado del análisis léxico.

![](thirdScreen.png)

![](fourthScreen.png)

##	Analisis Semántico
Para el Analisis Semántico se implementó una HashTable para almacenar el valor y los tipos de tokens para las ID, funciones y métodos, ya que se facilita la inserción y busqueda de los mismos, por medio de su Key.

En la asignación de variables y constantes, se realizó un método para llamar a la busqueda del token antes de la inserción, pues si ya existia el token entonces no se agregaba. En la HashTable se almacenan el nombre del token, el valor que algunas veces puede ser calculado, y el tipo para que tenga coherencia la operación a realizar.

Para la comprobación de tipos se agregaron las siguientes producciones:
```bash
Stmt::= ... | CallStmt 

CallStmt ::== ident(Actuals)
		| ident.ident(Actuals)

Actuals ::== Expr, Actuals
		| Actuals
```

Se realizó un ajuste en Statement ya que al implementarlo confundía la llamada a LValue, por lo que fue necesario continuar haciendo uso de los follows de la Gramática.
Estas producciones sirven para almacenar los valores de las funciones y métodos según el tipo que se definió, en caso contrario debe mostrar error y sin almacenar el valor.

También se implementó un metodo para aplicar la coherción de tipos, ya que al operar un calculo entre un valor de tipo entero y uno de tipo decimal, el resultado se obtendrá de forma entera, descartando los decimales restantes si existieran.


## Release History

* 1.0.0
    * Stable: Lexer analyzer
* 1.1.1
    * Stable: Se agregaron los dos puntos y la palabra reservada Print
* 1.1.1
    * Stable: Parser for-while-Exp

## Contributing
Desarrollado por:

Diego Dominguez – [@diegogtus](https://github.com/diegogtus) – alejandro.diego.gt@gmail.com

Walter Rodriguez – [@WalterVRodriguezG](https://github.com/WalterVRodriguezG) 

## License
[Oracle Java SE](https://www.oracle.com/downloads/licenses/javase-license1.html)
