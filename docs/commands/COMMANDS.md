---
| [**BACK TO HOME**](/README.md) |

---
# commands
This is a general overview of all the commands and their usage. You can click on any command to learn it in detail.

<!-- I got a bit confused about how to implement the commands until reading the Overview. Should be fine, but I provided a brief reference to the usage. -->

#### General usage reference:

```.commandName "input"```

---
## [Comments](/commands/COMMENTS.md)
Makes comments in the code.

<!-- Follows syntax? -->

---
## [.CreateScene](/commands/createScene/MAIN.md)
Creates an empty scene.
### [.addText](/commands/createScene/ADDTEXT.md)
Adds text to a scene or changes existing text.
### [.addOption](/commands/createScene/ADDOPTION.md)
Adds an option object to the scene.

<!-- Attaches? -->

---
## [.CreateStart](/commands/createStart/MAIN.md)
Creates an empty scene the engine uses as a starting point. If a start already exists, the new scene will take its place.
### [.addTitle](/commands/createStart/ADDTITLE.md)
Adds a title to the title screen.
### [.addDesc](/commands/createStart/ADDDESC.md)
Adds a description to the title screen.
### [.addText](/commands/createStart/ADDTEXT.md)
Adds text to the scene.
### [.addOption](/commands/createStart/ADDOPTION.md)
Adds an option to the scene.

---
## [.CreateSavePoint](/commands/createSavePoint/MAIN.md)
Creates a save point scene.
### [.addText](/commands/createSavePoint/ADDTEXT.md)
Adds text to the save point.
### [.link](/commands/createSavePoint/LINK.md)
Adds where the save point goes to next.

<!-- Elaborate on what a savepoint is -->

---
## [.CreateTextBlock](/commands/createTextBlock/MAIN.md)
Creates a text block scene.
### [.addText](/commands/createTextBlock/ADDTEXT.md)
Adds text to the text block.

<!-- Elaborate on what a text block scene is -->

---
## [.CreateDeadEnd](/commands/createDeadEnd/MAIN.md)
Creates a dead-end scene.
### [.addText](/commands/createDeadEnd/ADDTEXT.md)
Adds text to the dead-end scene.

---
## [.CreateOption](/commands/createOption/MAIN.md)
Creates an option object.
### [.link](/commands/createOption/LINK.md)
Adds a link to where the options go.

---
## [.save](/commands/SAVE.md)
Saves the story.

---
## [.start](/commands/START.md)
Runs the story.

---

<!-- Im sure you have not gotten to it yet, but are you intending to create a "Scenes" section that explains each scene? It may also be helpful to briefly summarize each scene before their corresponding commands -->

<!-- Noticed that constructor commands for scenes start with uppercase. Was this intentional? -->