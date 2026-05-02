In our drawing program, we use the Model-View-Controller (MVC) pattern to organize our code and separate different responsibilities. Here is an overview of the classes we will implement and their relationships:

Model Classes:

Shape (abstract class):
This class will serve as a base for all shapes drawn in the program. It defines common attributes such as position (x, y), width, height, and color, as well as an abstract method for drawing the shape.

DotShape:
A class that represents a point. It will inherit from Shape and implement the draw method to draw a point on the drawing panel.

OvalShape:
A class that represents an oval. It will also inherit from Shape and implement the draw method to draw an oval.

RectShape:
A class that represents a rectangle. It will inherit from Shape and implement the draw method to draw a rectangle.

View Class:

DrawingView:
This class is a JPanel responsible for drawing and displaying the different shapes. It will contain a list of Shape objects and a paintComponent method to draw them. It will also handle mouse interactions for creating and drawing new shapes.

Controller Class:

MenuController:
This class acts as a controller that manages the user’s interactions with the program, such as changing color and drawing mode, as well as performing undo and save functions. It will update DrawingView and a status label based on the user’s choices.

Main Class:

DrawControl:
This class is the main window of the program and will set up the interface. It will create instances of DrawingView, MenuController, and a status label. It also manages the layout of the entire window and connects all components together.
