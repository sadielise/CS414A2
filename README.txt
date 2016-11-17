Monopoly Game - Group 4 - CS414 Assignment 5, Fall 2016
This is a version of Monopoly recreated using Java for the purpose of experiencing the full process of application development. 
The game’s rules and specifications are based off of the Wikipedia page. A game is timer-based, and the winner is determined by 
who has the most liquidated funds. The Bank is handled internally, and the gui is interactable.

External .jar File Dependencies: // need to write

How to Run from Eclipse:
First, clone the directory into wherever you want to store this repository. 
Next, load the project into your Eclipse environment. Inside of the packet labeled “app”, open up Application.java. 
There are no extra parameters needed to run the program. Execute the main method in Application.java and the Swing 
graphical user interface should appear. You can have as little as 2 and up to 4 Players. You are allowed to interact with any 
of the given button, and the game will commence giving occasional instructions and options throughout.
The game is based off of a timer which can be set in minutes at the beginning of the game. Once the timer is done, 
the Player with the most liquidated funds will win, and you will have a chance to start a new game.

**No usernames or passwords are needed**

Assignment Discussion:
Our code implements a clean separation of the user interface layer from the application and domain layers by using message
passing instead of dependencies. The code is fairly extensive, but there are comments throughout and it is fairly well-organized.
The main issue we struggled with was how to organize the code. When refactoring, there was often a trade-off between pushing code from
a large class out to smaller classes because those smaller classes didn't have references to variables that the large class had
access to. Thus, we had to work to balance the classes while avoiding extensive variable passing back and forth.

Patterns Used:
We implemented the Factory pattern for the board spaces in the domain. The BoardSpaceFactory class uses an Enum to determine the type
of board space that needs to be created and then returns that board space. This pattern makes our code much cleaner.
We implemented the ...

Refactorings Used:


**************************************************************
Included Packets of a4
app:
Our application code is included in this packet. Application.java runs the main application using the graphical user interface. The game is initialized here and parameters are set for the models, views, and controllers to communicate. 

domain:
This is our controller code. Here are all of our objects that interact with each other. Or main “façade” is located inside of MonopolyGame.java. It’s interface is IMonopolyGame.java, which allows the gui and the controller to communicate through MonopolyGame’s methods. See file ~DesignClassDiagram.vsdx~ for more information on Object interaction and assignment.

gui:
The Graphical User Interface view. This packet includes the view as well as all of the communication means between the view and the domain. 

test:
All of our unit tests are located here. Every method from all of the controllers are tested, and can all be ran individually or as a whole.

root:
All asset images and values are stored in our root directory. We use these in our graphical user interface to make the view look nice.


Required Packets:
This game relies on a modern version of Eclipse as well as BreezySwing gui.


Testing:
Executing the TestAll.java will run all of the included unit tests. Bank, BoardSpaceFactory, BoardSpace, Board, Die, Modeling, MonopolyGame, and Player are all tested and have unit tests for each method.


Developers:
Chancey Dunn: @cedunn
David Thorpe: @dbthorpe
Gabriella Fontani: @gfontani
Jeffrey Buehler: @buehlerj
Sadie Henry: @sadielise

https://github.com/sadielise/CS414Project
