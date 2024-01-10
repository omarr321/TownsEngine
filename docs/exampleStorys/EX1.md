---
| [**BACK TO HOME**](/README.md) | [**BACK TO EXAMPLE STORIES**](/exampleStorys/MAIN.md) |

---

# Example Story #1

**About:** This is a very short story about a person who gets ready for work.

**Code:**
```
> Creates a start scene.
start = .CreateStart
> Sets up the start scene with a title, description, and text.
start.addTitle "Average Day"
start.addDesc "The day of a working person. Navigate the life and try not die."
start.addCredit "Omar Radwan"

> Creates the first scene
firstScene = .CreateScene
firstScene.addText "You wake up in your bed. The light is shining thought the window."
> Options for firstScene
firstSceneOp1 = .CreateOption
firstSceneOp1.addText "Get up"
firstSceneOp2 = .CreateOption
firstSceneOp2.addText "Go back to sleep"
> Links the options to the first scene
firstScene.addOption firstSceneOp1
firstScene.addOption firstSceneOp2
> Adds this scene as the start scene
start.addNextScene firstScene

> Creates a dead end if you choose to go back to sleep
deadEndSleep = .CreateDeadEnd
deadEndSleep.addText "You close your eyes and after a while drift off back to sleep."
> Links the dead end to the option where you go to sleep
firstSceneOp2.link deadEndSleep

> Creates a scene for if you get up
getUp = .CreateScene
getUp.addText "You get up and stretch your arms. You look around the room."
> Options for getUp scene
getUpOp1 = .CreateOption
getUpOp1.addText "Look out the window"
getUpOp2 = .CreateOption
getUpOp2.addText "Get ready for the day"
> Links the options to the getUp scene
getUp.addOption getUpOp1
getUp.addOption getUpOp2
> Adds this getUp scene to the option
firstSceneOp1.link getUp

> Creates a Dead End for looking out the window
lookingWindow = .CreateDeadEnd
lookingWindow.addText "You look out the window. You see some birds singing on a tree. As you listen to the birds, you fall out the window. As you hit the ground you neck breaks, killing you instantly."
getUpOp1.link lookingWindow

> Creates a Text Block for getting ready for the day
getReadyPt1 = .CreateTextBlock
getReadyPt1.addText "You head over to the dresser and open it. You see all your cloths neatly arranged and folded. After a while, you pick out some cloths and head to the bathroom to shower."
getUpOp2.link getReadyPt1

> Creates a save point for after the shower
getReadyPt2 = .CreateSavePoint
getReadyPt2.addText "After the shower you head back to your room. You put on your clothes and take a look in the mirror. You are ready for the day."
> Links the get ready to the previous one
getReadyPt1.link getReadyPt2

> More Story
headingToWork = .CreateScene
headingToWork.addText "You head out of your room and head downstairs into your kitchen. You realize you should probably eat something, you should have enough time."
> Creates the options for the scene
headingToWorkOp1 = .CreateOption
headingToWorkOp1.addText "Skip breakfast and head right to work"
headingToWorkOp2 = .CreateOption
headingToWorkOp2.addText "Make a quick breakfast of eggs"
headingToWorkOp3 = .CreateOption
headingToWorkOp3.addText "Make a longer breakfast of pancakes and bacon and eggs"
> Linking to options to the scene
headingToWork.addOption headingToWorkOp1
headingToWork.addOption headingToWorkOp2
headingToWork.addOption headingToWorkOp3
> Linking heading to work to the save
getReadyPt2.link headingToWork

> Making the next scenes
toWorkPt1 = .CreateTextBlock
toWorkPt1.addText "You walk past the kitchen and head to work right away."
toWorkPt2 = .CreateTextBlock
toWorkPt2.addText "You get work making some eggs. Lucky, you still had a few left in the fridge. You decide on scrambled eggs and make it fast. You add some cottage cheese and manage to eat it in the nick of time before leaving for work."
toWorkPt3 = .CreateTextBlock
toWorkPt3.addText "YOu get to work making a great breakfast. You put together a feast of eggs, bacon, and pancakes. It takes you a while but it perfect. You realize you are running late for work."
> Linking the scenes to the breakfast scene
headingToWorkOp1.link toWorkPt1
headingToWorkOp2.link toWorkPt2
headingToWorkOp3.link toWorkPt3

> Making the lateToWork dead end
lateToWork = .CreateDeadEnd
lateToWork.addText "You rush out the door. and speed down the road. As you cross an intersection you get T-Boned by a semi-truck killing you instantly"
headingToWorkOp3.link lateToWork

> Arrive to work
atWorkPt1 = .CreateDeadEnd
atWorkPt1.addText "You make it to work ok and start your day."
headingToWorkOp2.link atWorkPt1
headingToWorkOp1.link atWorkPt1
```