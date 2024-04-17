/*
 * name: haris ahmad
 * email: hka5236@psu.edu
 * class: cmpsc 470-001
 */

1. java -jar jflex-1.6.1.jar Lexer.flex
2. ./yacc.linux -Jnorun -Jthrows="Exception" -Jextends=ParserImpl Parser.y
3. javac *.java
4. java Program <test>.minc
