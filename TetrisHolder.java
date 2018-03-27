package DB.Project;

import javafx.scene.shape.Rectangle;
public class TetrisHolder {
	
   //Variable retrieval from the main class
	public static final int movement = TetrisDB.movement;
	public static final int size = TetrisDB.size;
	public static int xedge = TetrisDB.xedge;
	public static int yedge = TetrisDB.yedge;
	public static int[][] grid = TetrisDB.grid;
	
   
      //If possible this method will let you move the tetramino to the right.
   	public static void RightBarrier (Shape shape){
		if(shape.a.getX() + movement <= xedge - size && shape.b.getX() + movement <= xedge - size && shape.c.getX() + movement <= xedge - size && shape.d.getX() + movement <= xedge - size){
			int checka = grid[((int)shape.a.getX()/size) + 1][((int)shape.a.getY()/size)];
			int checkb = grid[((int)shape.b.getX()/size) + 1][((int)shape.b.getY()/size)];
			int checkc = grid[((int)shape.c.getX()/size) + 1][((int)shape.c.getY()/size)];
			int checkd = grid[((int)shape.d.getX()/size) + 1][((int)shape.d.getY()/size)];
			if(checka == 0 && checka == checkb && checkb == checkc && checkc == checkd){
				shape.a.setX(shape.a.getX() + movement);
				shape.b.setX(shape.b.getX() + movement);
				shape.c.setX(shape.c.getX() + movement);
				shape.d.setX(shape.d.getX() + movement);
			}//end outer loop
		}//end inner loop
	}//end method
	
   
     //If possible this method will let you move to the left   
   	public static void LeftBarrier (Shape shape){
		  if(shape.a.getX() - movement >= 0 && shape.b.getX() - movement >= 0 && shape.c.getX() - movement >= 0 && shape.d.getX() - movement >= 0){
			  int checka = grid[((int)shape.a.getX()/size) - 1][((int)shape.a.getY()/size)];
			  int checkb = grid[((int)shape.b.getX()/size) - 1][((int)shape.b.getY()/size)];
			  int checkc = grid[((int)shape.c.getX()/size) - 1][((int)shape.c.getY()/size)];
			  int checkd = grid[((int)shape.d.getX()/size) - 1][((int)shape.d.getY()/size)];
			  if(checka == 0 && checka == checkb && checkb == checkc && checkc == checkd){
				  shape.a.setX(shape.a.getX() - movement);
				  shape.b.setX(shape.b.getX() - movement);
				  shape.c.setX(shape.c.getX() - movement);
				  shape.d.setX(shape.d.getX() - movement);
				  }//end inner loop
			  }//end outer loop 
		  }//end method 
	
   
   	public static Shape createRect(){
		
	      //Random number generator spawn for tetraminoes, all are consisent except for I
         int block = (int)(Math.random() * 100);
	      String name;
	      Rectangle a = new Rectangle(size, size), b = new Rectangle(size, size), c = new Rectangle(size, size), d = new Rectangle(size, size);
	      if(block < 15){ //j tetramino spawn
	    	  a.setX(xedge / 2 - size);
	    	  b.setX(xedge / 2 - size); b.setY(size);
	    	  c.setX(xedge / 2); c.setY(size);
	    	  d.setX(xedge / 2 + size); d.setY(size);
	    	  name = "j";
	      }
         
	      else if(block < 30){ //L tetramino spawn
	    	  a.setX(xedge / 2 + size);
	    	  b.setX(xedge / 2 - size); b.setY(size);
	    	  c.setX(xedge / 2); c.setY(size);
	    	  d.setX(xedge / 2 + size); d.setY(size);
	    	  name = "l";
	      }
         
	      else if (block < 45){ //Block tetramino spawn
	    	  a.setX(xedge / 2 - size);
	    	  b.setX(xedge / 2);
	    	  c.setX(xedge / 2 - size); c.setY(size);
	    	  d.setX(xedge / 2); d.setY(size);
	    	  name = "o";
	      }
         
	      else if(block < 60){ //s tetramino spawn
	    	  a.setX(xedge / 2 + size);
	    	  b.setX(xedge / 2);
	    	  c.setX(xedge / 2); c.setY(size);
	    	  d.setX(xedge / 2 - size); d.setY(size);
	    	  name = "s";
	      }
         
	      else if(block < 75){ //t tetramino spawn
	    	  a.setX(xedge / 2 - size);
	    	  b.setX(xedge / 2);
	    	  c.setX(xedge / 2); c.setY(size);
	    	  d.setX(xedge / 2 + size);
	    	  name = "t";
	      }
         
	      else if(block < 90){ //z tetramino spawn
	    	  a.setX(xedge / 2 + size);
	    	  b.setX(xedge / 2);
	    	  c.setX(xedge / 2  + size); c.setY(size);
	    	  d.setX(xedge / 2 + size + size); d.setY(size);
	    	  name = "z";
	      }
         
	      else{ //I tetramino spawn (lowest chance to spawn)
	    	  a.setX(xedge / 2 - size - size);
	    	  b.setX(xedge / 2 - size);
	    	  c.setX(xedge / 2);
	    	  d.setX(xedge / 2 + size);
	    	  name = "i";
	      }
         
		  return new Shape(a, b, c, d, name);
	  }//end method 
}//end class