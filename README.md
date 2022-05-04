# JDromadaire

The goal of this language is to create a bunch of tools to work with the EngDrom project and to develop a turing complete language.

## License

This software is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html).

Copyright © Thimote75 and Itai12.

## Documentation

You can see the full documentation [here](https://jdromadaire.readthedocs.io)

This project is a rework of https://github.com/MrThimote/JDromadaire, therefore the current documentation might not be up to date at all

# Rule Compiler

The JDrom language is based on the RuleCompiler system which aims to generate easily parser system using simple rules, as it is an open source project you can reuse it for other programming languages in Java. For example, here is the syntax of a ParserRule : 

```
/FOR/ /LPAREN/ EXPR /EOF/ EXPR /EOF/ EXPR /RPAREN/ {}
```

This is the syntax that you would want to represent a for, it uses tokens represented as a /TOKEN/, expressions (may contain setters), represented as EXPR and a block represented as {}.

Another example is the OptionnalRule and ManyRule, it is used for example in if statements :

```
/IF/ /LPAREN/ EXPR /RPAREN/ {} [/ELSE/ /IF/ /LPAREN/ EXPR /RPAREN/ {}]* [/ELSE/ {}]
```

The [] means an optionnal statement and the * means you can repeat it as much as you want. A feature that isn't there right now is the "+" * N to repeat N times the last expression.
