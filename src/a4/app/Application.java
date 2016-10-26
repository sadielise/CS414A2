package a4.app;

import a4.domain.IMonopolyGame;
import a4.domain.MonopolyGame;
import a4.gui.Controller;
import a4.gui.Model;
import a4.gui.View;

public class Application {
	public static void main(String[] args)
	{
		Model      model      = new Model();
		View       view       = new View();
		Controller controller = new Controller();
		IMonopolyGame game = new MonopolyGame();

		model.setView(view);
		model.setGame(game);
		controller.setModel(model);
		controller.setView(view);
		view.setModel(model);
		view.setController(controller);

		view.build();
		view.show();
		
		game.setModel(model);
	}
}
