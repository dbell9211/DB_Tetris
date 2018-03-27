package DB.Project;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Shape {
	Rectangle a;
	Rectangle b;
	Rectangle c;
	Rectangle d;
	private String name;
	public int form = 1;
	
      //Constuctor for the block that molds the shape of the tetramino together
   	public Shape(Rectangle a, Rectangle b, Rectangle c, Rectangle d){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}//end constructor
	
		//Color check for the tetramino, different kinds of tetraminoes are different colors.
      public Shape(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;
		Color color = Color.WHITE;
		switch(name){
			case "j":
				color = Color.SILVER;
				break;
			case "l":
				color = Color.PURPLE;
				break;
			case "o":
				color = Color.GRAY;
				break;
			case "s":
				color = Color.GREEN;
				break;
			case "t":
				color = Color.BROWN;
				break;
			case "z":
				color = Color.BLUE;
				break;
			case "i":
				color = Color.RED;
				break;
				
		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	   }//end color decider
		
      //returns name of tetramino
      public String getName(){
		return name;
	   }
	
   //rotation
	public void changeForm(){
		if(form != 4){
			form++;
		} else{
			form = 1;
		}//end loop for tetramino rotation
	}//end method
}//end shapetetramino class