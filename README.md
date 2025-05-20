# Practica-3
# Chess SAN Parser

Este proyecto parsea partidas de ajedrez en notación SAN (Standard Algebraic Notation), valida sintáctica y semánticamente cada movimiento según la gramática BNF, detiene la ejecución ante el primer error y muestra la partida como un árbol binario.

## Integrantes
- Juan Pablo Parra El-Masri

## Estructura

- **src/**  
  - Move.java  
  - Turn.java  
  - TreeNode.java  
  - GameParser.java  
  - DotExporter.java  
  - Main.java  
- **README.md**

## Requisitos

- JDK 11+  
- Graphviz (opcional, para exportar .dot a PNG)

## Uso

```bash
# Compilar:
javac -d out src/*.java

# Ejecutar aplicación Swing:
java -cp out Main partida_san.txt

# Para exportar .dot y generar PNG:
java -cp out Main partida_san.txt --export-gfx partida.dot
dot -Tpng partida.dot -o partida.png
```
