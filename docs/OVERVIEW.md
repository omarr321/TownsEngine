---
| [**BACK TO HOME**](/README.md) |

---
# Overview
> This is an overview of how the language works.

---
## Fundamentals
The language is designed to be easy to use and to understand. In this section, will explain the language's syntax, as well as good programming practices for naming variables.

#### 1. Syntax
The syntax of this language is simple to implement. A **command** is a line of text, followed by a newline. Note that newlines are used to delimit commands and indicate its end. **Variables** must contain letters, numbers, and underscores. A command starts with a period (.) followed by the command name and any inputs separated by whitespace.

> Example of good variable names:
> * var1
> * 3VarTwo
> * var_Three
> * 4goodVarFour
> * _var_5

> Example of bad variable names:
> * var-2
> * Var#3
> * Var\+#$4

> Example of good commands:
> * var1 = .createScene
> * var1.addText "This is the text to add."

> Example of bad commands:
> * var1.addText"This is the text to add."
> * var1.addText This is the text to add.

#### 2. Command Usage
The language is designed to work with branching stories. This section is an overview of the programming environment and a guide for implementing commands.

The program is composed of objects called **Scenes**. Some of the various scenes that can be used include "start", "textBlock", etc. Each scene can have options attached to them. **Options** operate as independent objects linked to Scenes, and are interchangeable. Special scenes such as the "savePoint" and "deadEnd" work with this functionality in mind.
