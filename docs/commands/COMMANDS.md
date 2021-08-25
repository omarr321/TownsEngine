---
| [**BACK TO HOME**](/README.md) |

---
# Commands
This is a general overview of all the commands and their usage. You can click on any command to learn it in detail.

---

<style>
td, th {
   border: none!important;
}
</style>

<style>
td:nth-child(1) {
  width: 150px;
  }

/* the second */
td:nth-child(2) {
  width: 500px;
}

.niceTables thg {
background: grey;
word-wrap: break-word;
text-align: center;
}
.niceTables tr:nth-child(1) { background: #F2F2F2; }
.niceTables tr:nth-child(2) { background: #F2F2F2; }
.niceTables tr:nth-child(3) { background: #F2F2F2; }
.niceTables tr:nth-child(4) { background: #F2F2F2; }
.niceTables tr:nth-child(5) { background: #F2F2F2; }
.niceTables tr:nth-child(6) { background: #F2F2F2; }
</style>

<div class="niceTables">

|   Comments   | Makes comments in the code. |
|------------:|:--------------------|
| | |

| .CreateScene | Creates an empty scene. |
|------------:|:--------------------|
| .addText | Adds text to a scene or changes existing text. |
| .addOption | Adds an option object to the scene. |

| .CreateStart | Creates an empty scene the engine uses as a starting point. If a start already exists, the new scene will take its place. |
|------------:|:--------------------|
| .addTitle | Adds a title to the title screen. |
| .addDesc | Adds a descrption to the title screen. |
| .addText | Adds text to the start scene or changes existing text. |
| .addOption | Adds an option to the start scene. |

| .CreateSavePoint | Creates a save point scene. |
|------------:|:--------------------|
| .addText | Adds text the the save point or changes existing text. |
| .link | Adds where the save point goes to next. |

| .CreateTextBlock | Creates a text block scene. |
|------------:|:--------------------|
| .addText | Adds text to the text block or changes existing text. |

| .CreateDeadEnd | Creates a dead-end scene. |
|------------:|:--------------------|
| .addText | Adds text to the dead-end scene or changes existing text. |

| .CreateOption | Creates an option. |
|------------:|:--------------------|
| .link | Adds a link to where the option goes. |

| .Save | Saves the story. |
|------------:|:--------------------|
| | |

| .Start | Runs the story. |
|------------:|:--------------------|
| | |

</div>