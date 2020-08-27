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

##	Procedimiento	
Al iniciar el programa, se debe cargar el archivo a analizar con extensión ".txt" o ".frag" 
y al abrirlo muestra en la columna izquierda, el contenido del mismo tal cual se encuentra. 
Al presionar el botón Analizar muestra un mensaje en el que indica que ha finalizado el análisis
y que generó el archivo con extensión ".out". También se muestra en pantalla en la columna 
derecha el resultado del análisis léxico.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
