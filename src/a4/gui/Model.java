package a4.gui;

public class Model {
	private View view;
	
	public void setView(View v){
		view = v;
	}
	
	public String getCurrentPlayer(){
		return "non";
	}
	
	public String getCurrentBankroll(){
		return "non";
	}
}
