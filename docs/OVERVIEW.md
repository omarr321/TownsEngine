---
| [**BACK TO HOME**](/README.md) |

---
# Overview
> This is an overview of how the language works.

---
## Fundamentals
The language is designed to be easy to use and to understand. In this section, will explain the language's syntax, as well as good programming practices for naming variables.

#### 1. Syntax
The syntax of this language is simple to implement. A **command** is a line of text, followed by a newline. Note that newlines are used to delimit commands and indicate its end. Variables must start with a case-insensitive letter and can contain letters, numbers, and underscores. A command starts with a period (.) followed by the command name and any inputs separated by whitespace.

```.commandName "input"```

<!-- Might want to explain this section below about VARIABLE names. Explain the case used and why these examples are good .-->

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

#### 2. Command Usage
The language is designed to work with branching stories. This section is an overview of the programming environment and a guide for implementing commands.

The program is composed of objects called **Scenes**. Some of the various scenes that can be used include "start", "textBlock", etc. Each scene can have options attached to them. **Options** operate as independent objects linked to Scenes, and are interchangeable. Special scenes such as the "savePoint" and "deadEnd" work with this functionality in mind.

<!-- I'm not sure if you meant to say "Save Point" and "Dead End" instead of its camel case equivalent -->

> Example script with two scenes:
```
> Creates a start scene.
start = .CreateStart
> Sets up the start scene with a title, description, and text.
start.addTitle "Example with 2 scenes"
start.addDesc "This is an example story that has two scenes in it."
start.addText "You wake up in your bed."

> Creates a dead-end scene.
end = .CreateDeadEnd
> Sets up the dead-end scene
end.addText "You go back to sleep!"

> Creates a new option that is "Go back to sleep".
option1 = .CreateOption "Go back to sleep"
> Links the option to the dead-end scene.
option1.link end
> Adds the option to the start scene.
start.addOption option1

> Runs the story.
.start
```
