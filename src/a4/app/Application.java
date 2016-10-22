package a4.app;

import a4.gui.Controller;
import a4.gui.Model;
import a4.gui.View;

public class Application {
	public static void main(String[] args)
	{
    //Construct all the components
	  Model      model      = new Model();
	  View       view       = new View();
	  Controller controller = new Controller();
	  
	  //Notify each component of the other components it needs to refer to
	  model.setView(view);
	  controller.setModel(model);
	  controller.setView(view);
	  view.setModel(model);
	  view.setController(controller);
	  
	  //Build the application, then show it on the screen
	  view.build();
		view.show();
	}
}
