package DB.Final;

import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TetrisDB extends	Application	{
  
  //All static	variables
	
  public	static final int movement = 25;
  public	static final int size =	25;
  public	static int xedge = size	* 10;
  public	static int yedge = size	* 24;
  public	static int[][]	grid = new int[xedge/size][yedge/size];
  private static Pane group =	new Pane();
  private static Pane gameoverGroup	= new	Pane();
  private static Shape object;
  private static Scene scene = new Scene(group,	xedge	+ 200, yedge);
  private static Scene gameoverScene =	new Scene(gameoverGroup, 500,	yedge);
  public	static int score = 0;
  public	static int mins =	0;
  private static int	top =	0;
  private static int	finalscore = 0;
  private static boolean time	= true;
  private static Shape nextShape	= TetrisHolder.createRect();
  
  
  //main	method 
  
  
  
  public	static void	main(String[] args) { launch(args);	}
  @Override	public void	start(Stage	stage) throws Exception	{
	 for(int[] a: grid){
		Arrays.fill(a,	0);
	 }
	 //Game over screen/Restart/Exit	Program
	 
	 Text	gameovertext =	new Text("GAME\nOVER");
	 gameovertext.setFill(Color.RED);
	 gameovertext.setStyle("-fx-font: 100 arial;");
	 gameovertext.setY(250);
	 gameovertext.setX(100);
	 Text	quittingtext =	new Text("");
	 quittingtext.setFill(Color.PURPLE);
	 quittingtext.setStyle("-fx-font: 25 arial;");
	 quittingtext.setY(450);
	 quittingtext.setX(180);
	 Text	finalscoretext	= new	Text("");
	 finalscoretext.setFill(Color.PURPLE);
	 finalscoretext.setStyle("-fx-font: 25 arial;");
	 finalscoretext.setY(150);
	 finalscoretext.setX(160);
	 gameoverGroup.getChildren().addAll(gameovertext, quittingtext, finalscoretext);
	 
	 
	 
	 //Creating	score	and time	texts
	 
	 Line	line = new Line(xedge, 0, xedge,	yedge);
	 Line	line2	= new	Line(xedge,	300, xedge + 200,	300);
	 Text	scoretext =	new Text("Score: 0");
	 Text	timetext	= new	Text("Time: 0 s");
	 scoretext.setY(350);
	 scoretext.setX(xedge +	25);
	 timetext.setY(400);
	 timetext.setX(xedge	+ 25);
	 group.getChildren().addAll(scoretext,	timetext, line, line2);
	 
	 //Playfield and First Tetramino	setup
	 
	 Shape a	= nextShape;
	 group.getChildren().addAll(a.a,	a.b, a.c, a.d);
	 moveOnKeyPress(a);
	 object = a;
	 nextShape = TetrisHolder.createRect();
	 stage.setScene(scene);
	 stage.show();
	 
	 //Timer	for falling	tetraminos
	 
	 Timer fall	= new	Timer();
	 TimerTask task =new	TimerTask()	{
	  @Override
	  public	void run() {
		  Platform.runLater(new	Runnable(){
			  public	void run(){
				  if(object.a.getY()	==	0 || object.b.getY()	==	0 || object.c.getY()	==	0 || object.d.getY()	==	0)
					  top++;
				  else
					  top	= 0;
					  
				  //Check for upper screen	tetramino gameover
				  
				  if(top	==	2){
					  finalscore =	score;
					  stage.hide();
					  stage.setScene(gameoverScene);
					  stage.show();
				  }
				  
				  //Your	final	score	is...
				  
				  if(top	> 2){
					  finalscoretext.setText("Final Score: " + Integer.toString(finalscore));
					  quittingtext.setText("Quitting in " + Integer.toString(10-top/3));
				  }
				  
				  //Quits the game
				  
				  if(top	==	30){
					  System.exit(0);
				  }
				  if(time)
					  fallingdown(object);
				  scoretext.setText("Score: "	+ Integer.toString(score));
			  }
		  });
		}
	  };
	  fall.schedule(task,0,300);
	  
	//Timer for	time showing in game	also includes minutes if needed.
	Timer	time = new Timer();
	 TimerTask task2 =new TimerTask() {
	  @Override
	  public	void run() {
		  Platform.runLater(new	Runnable(){
			  public	void run(){
					mins++;
					timetext.setText("Time: " + Integer.toString(mins)	+ " s");
					if(mins > 60){
					timetext.setText("Time: " + Integer.toString(mins/60)	+ " min " +	Integer.toString(mins%60) + " s");
					}
			  }
		  });
		}
	  };
	  time.schedule(task2,0,1000);	 
	 }//end time method 
 
	//Controls with the switch	statement.	 
	private void moveOnKeyPress(Shape shape) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()	{
		@Override public void handle(KeyEvent event)	{
			switch (event.getCode()) {
			 case	RIGHT: 
			  TetrisHolder.RightBarrier (shape);
			  break;
			 case	DOWN:	 
			  fallingdown(shape);
				  break;
			 case	LEFT:	 
			  TetrisHolder.LeftBarrier	(shape);
			  break;
			 case	SPACE:
			  CheckTurn(shape);
			  break;
			 case	UP:
			  time =	!time;
		  }
		}
	 });
  }//end	controls	method
	 
	 //Check	for possible turn. check by switch statement	& tetramino.
	 private	void CheckTurn(Shape	shape){
	  int	f = shape.form;
	  Rectangle	a = shape.a;
	  Rectangle	b = shape.b;
	  Rectangle	c = shape.c;
	  Rectangle	d = shape.d;
	  switch(shape.getName()){
		 case	"j":
			if(f == 1 && cB(a, 1, -1) && cB(c, -1,	-1) && cB(d, -2, -2)){
				RightBarrier (shape.a);
				fallingdown(shape.a);
				fallingdown(shape.c);
				LeftBarrier	(shape.c);
				fallingdown(shape.d);
				fallingdown(shape.d);
				LeftBarrier	(shape.d);
				LeftBarrier	(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(a, -1, -1)	&&	cB(c,	-1, 1) && cB(d, -2, 2)){
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				LeftBarrier	(shape.d);
				LeftBarrier	(shape.d);
				CheckUp(shape.d);
				CheckUp(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)){
				LeftBarrier	(shape.a);
				CheckUp(shape.a);
				CheckUp(shape.c);
				RightBarrier (shape.c);
				CheckUp(shape.d);
				CheckUp(shape.d);
				RightBarrier (shape.d);
				RightBarrier (shape.d);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)){
				CheckUp(shape.a);
				RightBarrier (shape.a);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				RightBarrier (shape.d);
				RightBarrier (shape.d);
				fallingdown(shape.d);
				fallingdown(shape.d);
				shape.changeForm();
				break;
			}
			break;
		case "l":
			if(f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)){
				RightBarrier (shape.a);
				fallingdown(shape.a);
				CheckUp(shape.c);
				RightBarrier (shape.c);
				CheckUp(shape.b);
				CheckUp(shape.b);
				RightBarrier (shape.b);
				RightBarrier (shape.b);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(a, -1, -1)	&&	cB(b,	2,	-2) && cB(c, 1, -1)){
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				RightBarrier (shape.b);
				RightBarrier (shape.b);
				fallingdown(shape.b);
				fallingdown(shape.b);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(a, -1, 1) && cB(c, -1,	-1) && cB(b, -2, -2)){
				LeftBarrier	(shape.a);
				CheckUp(shape.a);
				fallingdown(shape.c);
				LeftBarrier	(shape.c);
				fallingdown(shape.b);
				fallingdown(shape.b);
				LeftBarrier	(shape.b);
				LeftBarrier	(shape.b);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1,	1)){
				CheckUp(shape.a);
				RightBarrier (shape.a);
				LeftBarrier	(shape.b);
				LeftBarrier	(shape.b);
				CheckUp(shape.b);
				CheckUp(shape.b);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				shape.changeForm();
				break;
			}
			break;
		case "o":
			break;
		case "s":
			if(f == 1 && cB(a, -1, -1)	&&	cB(c,	-1, 1) && cB(d, 0, 2)){
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				CheckUp(shape.d);
				CheckUp(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)){
				CheckUp(shape.a);
				RightBarrier (shape.a);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				fallingdown(shape.d);
				fallingdown(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(a, -1, -1)	&&	cB(c,	-1, 1) && cB(d, 0, 2)){
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				CheckUp(shape.d);
				CheckUp(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)){
				CheckUp(shape.a);
				RightBarrier (shape.a);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				fallingdown(shape.d);
				fallingdown(shape.d);
				shape.changeForm();
				break;
			}
			break;
		case "t":
			if(f == 1 && cB(a, 1, 1) && cB(d, -1, -1)	&&	cB(c,	-1, 1)){
				CheckUp(shape.a);
				RightBarrier (shape.a);
				fallingdown(shape.d);
				LeftBarrier	(shape.d);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(a, 1, -1) && cB(d, -1,	1)	&&	cB(c,	1,	1)){
				RightBarrier (shape.a);
				fallingdown(shape.a);
				LeftBarrier	(shape.d);
				CheckUp(shape.d);
				CheckUp(shape.c);
				RightBarrier (shape.c);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(a, -1, -1)	&&	cB(d,	1,	1)	&&	cB(c,	1,	-1)){
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				CheckUp(shape.d);
				RightBarrier (shape.d);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(a, -1, 1) && cB(d, 1, -1)	&&	cB(c,	-1, -1)){
				LeftBarrier	(shape.a);
				CheckUp(shape.a);
				RightBarrier (shape.d);
				fallingdown(shape.d);
				fallingdown(shape.c);
				LeftBarrier	(shape.c);
				shape.changeForm();
				break;
			}
			break;
		case "z":
			if(f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2,	0)){
				CheckUp(shape.b);
				RightBarrier (shape.b);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				LeftBarrier	(shape.d);
				LeftBarrier	(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(b, -1, -1)	&&	cB(c,	1,	-1) && cB(d, 2, 0)){
				fallingdown(shape.b);
				LeftBarrier	(shape.b);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				RightBarrier (shape.d);
				RightBarrier (shape.d);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2,	0)){
				CheckUp(shape.b);
				RightBarrier (shape.b);
				LeftBarrier	(shape.c);
				CheckUp(shape.c);
				LeftBarrier	(shape.d);
				LeftBarrier	(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(b, -1, -1)	&&	cB(c,	1,	-1) && cB(d, 2, 0)){
				fallingdown(shape.b);
				LeftBarrier	(shape.b);
				RightBarrier (shape.c);
				fallingdown(shape.c);
				RightBarrier (shape.d);
				RightBarrier (shape.d);
				shape.changeForm();
				break;
			}
			break;
		case "i":
			if(f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)){
				CheckUp(shape.a);
				CheckUp(shape.a);
				RightBarrier (shape.a);
				RightBarrier (shape.a);
				CheckUp(shape.b);
				RightBarrier (shape.b);
				fallingdown(shape.d);
				LeftBarrier	(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 2 && cB(a, -2, -2)	&&	cB(b,	-1, -1) && cB(d, 1, 1)){
				fallingdown(shape.a);
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				LeftBarrier	(shape.a);
				fallingdown(shape.b);
				LeftBarrier	(shape.b);
				CheckUp(shape.d);
				RightBarrier (shape.d);
				shape.changeForm();
				break;
			}
			if(f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)){
				CheckUp(shape.a);
				CheckUp(shape.a);
				RightBarrier (shape.a);
				RightBarrier (shape.a);
				CheckUp(shape.b);
				RightBarrier (shape.b);
				fallingdown(shape.d);
				LeftBarrier	(shape.d);
				shape.changeForm();
				break;
			}
			if(f == 4 && cB(a, -2, -2)	&&	cB(b,	-1, -1) && cB(d, 1, 1)){
				fallingdown(shape.a);
				fallingdown(shape.a);
				LeftBarrier	(shape.a);
				LeftBarrier	(shape.a);
				fallingdown(shape.b);
				LeftBarrier	(shape.b);
				CheckUp(shape.d);
				RightBarrier (shape.d);
				shape.changeForm();
				break;
			}
			break;
	  }//end	switch statement
  }//end	check	method
	 
	 
	 //Delete rows	and determine which row	to	be	deleted.
	 private	void DeleteRows(Pane	pane){
	  ArrayList<Node>	rects	= new	ArrayList<Node>();
	  ArrayList<Integer>	lines	= new	ArrayList<Integer>();
	  ArrayList<Node>	newrects	= new	ArrayList<Node>();
	  int	full = 0;
		  for(int i	= 0; i <	grid[0].length; i++){
		  for(int j	= 0; j <	grid.length; j++){
			  if(grid[j][i] == 1)
				  full++;
		  }
		  if(full == grid.length)
			  lines.add(i+lines.size());
		  full =	0;
	  }//end	for if loop
	  
	  if(lines.size()	> 0)
		  do{
			  for(Node node: pane.getChildren()) {
					if(node instanceof Rectangle)
						  rects.add(node);
					}
			  score+= 100;
			  for(Node node: rects){
				  Rectangle	a = (Rectangle)node;
				  if(a.getY() == lines.get(0)*size){
					  grid[(int)a.getX()/size][(int)a.getY()/size] = 0;
					  pane.getChildren().remove(node);
				  }
				  else
					  newrects.add(node);
			  }//end	for if loop
			  for(Node node: newrects){
				  Rectangle	a = (Rectangle)node;
				  if(a.getY() < lines.get(0)*size){
					  grid[(int)a.getX()/size][(int)a.getY()/size] = 0;
					  a.setY(a.getY()	+ size);
					  }
			  }
			  lines.remove(0);
			  rects.clear();
			  newrects.clear();
			  for(Node node: pane.getChildren()) {
					if(node instanceof Rectangle)
						  rects.add(node);
					}
			  for(Node node: rects){
				  Rectangle	a = (Rectangle)node;
				  try	{
					  grid[(int)a.getX()/size][(int)a.getY()/size] = 1;
				  } catch (ArrayIndexOutOfBoundsException	e)	{
				  }
			  }
			  rects.clear();
		  } while(lines.size() > 0);
  }
  
	  //end downward movement of the	tetramino.
	  private void	fallingdown(Rectangle rect){
	  if(rect.getY() + movement <	yedge)
		  rect.setY(rect.getY()	+ movement);
  }
  
	 //check	to	see if it's	possible	to	move right
	 private	void RightBarrier	(Rectangle rect){
	  if(rect.getX() + movement <= xedge -	size)
		  rect.setX(rect.getX()	+ movement);
  }//end	rightbarrier
 
	//check to see	if	it's possible to move left
	private void LeftBarrier (Rectangle	rect){
	  if(rect.getX() - movement >= 0)
		  rect.setX(rect.getX()	- movement);
  }//end	leftbarrier
  
	private void CheckUp(Rectangle rect){
	  if(rect.getY() - movement >	0)
		  rect.setY(rect.getY()	- movement);
  }//end	check	of	the rectangle 
  
	//checks	for block downward movement in order to stick to the rest of the tetraminoes	
	 private	void fallingdown(Shape shape){
	  if(shape.a.getY() == yedge - size	||	shape.b.getY()	==	yedge	- size || shape.c.getY() == yedge -	size || shape.d.getY() == yedge - size	||	checkA(shape) || checkB(shape) || checkC(shape)	||	checkD(shape)){
		  grid[(int)shape.a.getX()/size][(int)shape.a.getY()/size] = 1;
		  grid[(int)shape.b.getX()/size][(int)shape.b.getY()/size] = 1;
		  grid[(int)shape.c.getX()/size][(int)shape.c.getY()/size] = 1;
		  grid[(int)shape.d.getX()/size][(int)shape.d.getY()/size] = 1;
		  DeleteRows(group);
		  Shape a =	nextShape;
		  nextShape	= TetrisHolder.createRect();
		  object	= a;
		  group.getChildren().addAll(a.a, a.b,	a.c, a.d);
		  moveOnKeyPress(a);
		  }
		  
	  //Moving one	block	down if down is not full
	  if(shape.a.getY() + movement <	yedge	&&	shape.b.getY()	+ movement < yedge && shape.c.getY() +	movement	< yedge && shape.d.getY() + movement <	yedge){
		  int	checka =	grid[(int)shape.a.getX()/size][((int)shape.a.getY()/size) +	1];
		  int	checkb =	grid[(int)shape.b.getX()/size][((int)shape.b.getY()/size) +	1];
		  int	checkc =	grid[(int)shape.c.getX()/size][((int)shape.c.getY()/size) +	1];
		  int	checkd =	grid[(int)shape.d.getX()/size][((int)shape.d.getY()/size) +	1];
		  if(checka	==	0 && checka	==	checkb && checkb == checkc	&&	checkc == checkd){
			  shape.a.setY(shape.a.getY()	+ movement);
			  shape.b.setY(shape.b.getY()	+ movement);
			  shape.c.setY(shape.c.getY()	+ movement);
			  shape.d.setY(shape.d.getY()	+ movement);
			  }//end	inner	loop
		  }//end	outer	loop
	  }//end	downward	movement	method
	 
	 //check	for if it's	possible	to	move first block down
	 private	boolean checkA(Shape	shape){
	  return	(grid[(int)shape.a.getX()/size][((int)shape.a.getY()/size) + 1] == 1);
  }//end	check	A
  
	 private	boolean checkB(Shape	shape){
	  return	(grid[(int)shape.b.getX()/size][((int)shape.b.getY()/size) + 1] == 1);
  }//end	check	B
  
	 private	boolean checkC(Shape	shape){
	  return	(grid[(int)shape.c.getX()/size][((int)shape.c.getY()/size) + 1] == 1);
  }//	check	c
  
	 private	boolean checkD(Shape	shape){
	  return	(grid[(int)shape.d.getX()/size][((int)shape.d.getY()/size) + 1] == 1);
  }//end	check	D
  
	//checks	to	see if it is possible to move	the tetramino.	 
	 private	boolean cB(Rectangle	rect,	int x, int y){
	  boolean xb =	false;
	  boolean yb =	false;
	  if(x >= 0)
		  xb = rect.getX() +	x*movement <= xedge - size;
	  if(x <	0)
		  xb = rect.getX() +	x*movement >= 0;
	  if(y >= 0)
		  yb = rect.getY() -	y*movement > 0;
	  if(y <	0)
		  yb = rect.getY() +	y*movement < yedge;
	  return	xb	&&	yb	&&	grid[((int)rect.getX()/size) + x][((int)rect.getY()/size) -	y]	==	0;
  }//end	boolean method
  
}//end main	class