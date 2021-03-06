---
| [**BACK TO HOME**](/README.md) | [**BACK TO EXAMPLE STORIES**](/exampleStorys/MAIN.md) |

---

# Example Story #2

**About:** This short story is about you waking up in your bed.

**Code:**
```
> Creates the start scene and sets it up
start = .CreateStartScene
start.addTitle "Example Story #2"
start.addDesc "This is the first example story"
start.addText "You wake up in your bed"

> Creating the options for the start scene
start_o1 = .CreateOption
start_o1.addText "Get up"
start_o2 = .CreateOption
start_o2.addText "Go back to sleep"
start_o3 = .CreateOption
start_o3.addText "Look out the window"

> Adding the options to the start scene
start.addOption start_o1
start.addOption start_o2
start.addOption start_o3

> Creating the scenes that link to the start scene
startSleep = .CreateDeadEnd
startWindow = .CreateScene
startGetUp = .CreateDeadEnd

> Linking the scenes to the start scene
start_o1.link startGetUp
start_o2.link startSleep
start_o3.link startWindow

> Setting up the "Get up" scene from the start scene
startGetUp.addText "You get up and go thought your day"

> Setting up the "Go back to sleep" scene from the start scene
startSleep.addText "You go back to sleep"

> Setting up the "Look out the window" scene from the start scene
startWindow.addText "You see a bird"

> Creating options for the window scene
window_o1 = .CreateOption
window_o1.addText "Put your hand out"
window_o2 = .CreateOption
window_o2.addText "Go back to sleep"

> Adding the options to the window scene
startWindow.addOption window_o1
startWindow.addOption window_o2

> Creating the scenes needed for the window scene
windowHandOut = .CreateDeadEnd
windowHandOut.addText "You put yuor hand out but the bird flys away"

> Linking the scenes
window_o1.link windowHandOut
window_o2.link startSleep

> Starts the story
.Start
```

**Graph:**

![Example Story #2 Graph](/ex2Graph.svg)
