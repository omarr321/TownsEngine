---
| [**BACK TO HOME**](/README.md) |

---
# overview
> This is an overview of home the language works. After you read this take a look at [commands](/commands/MAIN.md).

---
## fundamentals
The language is designed to be easy to use and to understand.
In this section we will talk about how it works and how to write it out.
#### 1. Syntax
The syntax is very simple. A command is a line of text with a newline at the end.
If there is no newline, the interpiter will read it as one command. Variables must start with a letter either up or lower case
and can contain letters, numbers, and underscores. Commands starts with dots followed by the command with any inputs after that with
a space inbeween the commands and separate inputs.
> Example of good variable names:
> * var1
> * VarTwo
> * var_Three
> * goodVarFour
> * var_5

> Example of bad variable names:
> * 123
> * var-2
> * Var#3
> * Var\+#$4

> Example of good commands:
> * .start
> * .save
> * var1 = .createScene
> * var1.addText "This is the text to add."

> Example of bad commands:
> * start
> * var1.addText"This is the text to add."
> * var1.addText This is the text to add.

#### 2. How to use
The langauge is designed to do branching stories. This section will teach you how the program environment works and how to use it.
The program will be composed of Scenes whether it a start, textBlock, etc. Each scene can have options attached to them. The options must
have scene attached to them so when you select that option, it pick the scene connected to that option. Special scenes like the Save Point or Dead End work with
this functionality in mind.

> Example script with two scenes:
```start = .createStart
start.addText "You wake up in your bed."
end = .createDeadEnd
end.addText "You go back to sleep!"
option1 = .createOption "Go back to sleep"
option1.link end
start.addOption option1
.start
```