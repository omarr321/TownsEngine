---
| [**BACK TO HOME**](/README.md) | [**BACK TO EXAMPLE STORIES**](/exampleStorys/MAIN.md) |

---

# Example Story #3

**About:** An adaptation of the fictional text adventure eXit from Mr. Robot. Utilizes many of the engine's available objects and commands.

**Code:**
```
> Creates a start scene.
/start = .CreateStart

> Sets up the start scene with a title, description, and text.
/start.addTitle "eXit"
/start.addDesc "A game about survival under dark circumstances."
/start.addText "You're trapped in a dungeon with your friend. You see a barrel. What do you do?"

> Creating options >> start scene
/start_o1 = .CreateOption
/start_o1.addText "Move the barrel"
/start_o2 = .CreateOption
/start_o2.addText "Sit down next to my friend"
/start_o3 = .CreateOption
/start_o3.addText "Nothing"

> Adding the options to the start scene
/start.addOption start_o1
/start.addOption start_o2
/start.addOption start_o3

> Creating the scenes that link to the start scene
/sceneTunnel = .CreateScene
/sceneNote = .CreateSavePoint
/sceneAloneEnding = .CreateDeadEnd

> Linking the scenes to the start scene
/start_o1.link sceneTunnel
/start_o2.link sceneNote
/start_o3.link sceneAloneEnding

> Setting up the alone ending
/sceneAloneEnding.addText "You die alone. How pathetic!"

> Setting up the secret tunnel scene
/sceneTunnel.addText "The barrel rolls aside and you find a secret tunnel. What do you do?"

> Creating options >> start scene
/tunnel_o1 = .CreateOption
/tunnel_o1.addText "Enter tunnel"
/tunnel_o2 = .CreateOption
/tunnel_o2.addText "Go back"

> Create additional TextBlock and link it
/sceneNoteTB = .CreateTextBlock
/sceneNoteTB.addText "You start to escape but your friend is too weak to go with you."
/sceneNoteTB.link sceneNote

> Adding the options >> tunnel scene
/sceneTunnel.addOption tunnel_o1
/sceneTunnel.addOption tunnel_o2
/sceneTunnel.addOption tunnel_o3

> Linking the scenes >> tunnel scene
/tunnel_o1.link sceneNoteTB
/tunnel_o2.link start

> Setting up the note scene
/sceneNote.addText "They hand you a note. What do you do?"

> Creating options >> note scene
/note_o1 = .CreateOption
/note_o1.addText "Read note"
/note_o2 = .CreateOption
/note_o2.addText "Light a match"

> Create additional TextBlock and Scene
/sceneDark = .CreateScene
/sceneDark.addText "It is too dark to read the note. What do you do?"
/sceneDontLeave = .CreateScene
/sceneDontLeave.addText "The note says, \"Don't leave me here\". Do you leave your friend or stay?"

> Adding the options >> note scene
/sceneNote.addOption note_o1
/sceneNote.addOption note_o2
/sceneNote.addOption note_o3

> Linking scenes >> note options
/note_o1.link sceneDark
/note_o2.link sceneDontLeave

> Creating options >> dont leave scene
/dontleave_o1 = .CreateOption
/dontleave_o1.addText "Leave"
/dontleave_o2 = .CreateOption
/dontleave_o2.addText "Stay"

> Create additional TextBlock and DeadEnds
/sceneBoatTB = .CreateTextBlock
/sceneBoatTB.addText "You leave the tunnel before it collapses and hop onto a boat."
/sceneBoatEnding = .CreateDeadEnd
/sceneBoatEnding.addText "Congratulations, you're heading to a new world!"
/sceneExcitingTimeEnding = .CreateDeadEnd
/sceneExcitingTimeEnding.addText "It's an exciting time in the world."

> Adding the options and link >> dark scene
/sceneDontLeave.addOption dontleave_o1
/sceneDontLeave.addOption dontleave_o2
/dontleave_o1.link sceneBoatTB
/sceneBoatTB.link sceneBoatEnding
/dontleave_o2.link sceneExcitingTimeEnding

> Creating options >> dark scene
/dark_o1 = .CreateOption
/dark_o1.addText "Leave"

> Add options and link remaining scenes
/sceneDark.addOption dark_o1
/dark_o1.link sceneBoatTB
```

**Graph:**

![Example Story #3 Graph](/ex3Graph.svg)