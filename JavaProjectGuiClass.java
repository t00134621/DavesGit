/**
 * @(#)JavaProjectGuiClass.java
 *
 *
 * @author DavidMcMahon
 * @version 1.00 2014/11/14
 */
import java.awt.EventQueue;///// used in the timer class
import java.awt.event.ActionEvent; // Action event Listeners and Threads
import javax.swing.*;   /// all swing components
import java.awt.event.*;///// event listeners
import java.awt.*;// all awt components
import java.io.*;// input output file read and write
import java.util.ArrayList;// used with arraylists


public class JavaProjectGuiClass extends JFrame implements ActionListener{      // using jframe inherit, and implement Actionlistoner ie interface with abstract classes ie abstract button
	
      
    private	JMenu player;// drop down creste player game
    private JMenu game;  // drop down start game
    private JMenu level; // drop down level select
    private JMenu exit;  // drop down exit
    private JMenu save;  // drop down save game
    private JMenu info;   // drop down game info
    private JPanel panel;  // three panels that contain various components
    private JPanel panel2;
    private JPanel panel3;
    private ImageIcon icon;   // a component that houses an image ie(k(i))
    private	JTextField jTfans;  // the answer textfield.
    private	JButton btn1;       // a Jbutton to check the users answer//
    private	JButton btn2;       // a Jbutton to select a hint for display//
    private	JLabel jlb1;//// contains the images or icons
    private JLabel hero;// a Jabel that contains a Winners image?
    private JTextArea jta;// Jtextarea to display gameInfo
    
    private static JLabel frontImg;   // must be static to run in main//
    private static JLabel frontImg2;  // DMC Games etc
    
    public	JLabel jlb2;//contains the timer and must be set public for timer purposes//
    
    private	JLabel jlb3; // contains the player name
    private	JLabel jlb4; // contains the score
    private JLabel jlb5;  // contains the hint
    private JProgressBar pgb; // a progress bar
    
    private int i=0; // answer array rotate
    private int k=0; // picture array rotate
    private int j=0; // hint array rotate
    
    
    private int counter = 240;/////time of game
    Timer timer;// timer class
   
    private int score = 0;//// score in game//
    private String x;/// sets the jtextField
    private int s;//used to parse the score to an int for an increment
    
    public PlayerDetails pd; // nested object references PlayerDetails
   
    private static ArrayList<String> hints;       //// arrayLists
    private static ArrayList<String> ans;
    private static ArrayList<String> pic;    
                                                     
   
  
   	public static void main(String [] args){
		                                                       /// array lists must be static as an image is set before main hence the method is called before main is visible(pic.get(i))
	                                                           // other wise a null pointer exception will occur.
     ADDHINTS();
	 ADDANSWERS();
	 ADDPICTURES();                  
	 JavaProjectGuiClass Jproject = new JavaProjectGuiClass();
	 
	 Jproject.setVisible(true);
     MoveLabel1();//////////// begin the "animation"
   
	
		}///////////////// end Main
	


    public JavaProjectGuiClass() {
    	
    	setSize(400,400);                                     // jframe size
    	setResizable(false);                                     // keeps the window at that size
    	setLocation(250,250);
    	setTitle("GUESS THE BADDIE");                              // sets the title
    	setDefaultCloseOperation(EXIT_ON_CLOSE);                     // exit the game(adds the x button to the window)
    	
    	
    	Container cp = getContentPane();   // the content pane or container of the gui inside the frame
    	
    	
    	cp.setBackground(Color.BLACK);
    	frontImg = new JLabel("<html><p style='color:RED; font-size:28pt'>GUESS THE BADDIE???</p></html>"); // embedded html for a nicer look//
    	frontImg2 = new JLabel("<html><p style='color:YELLOW; font-size:25pt'> BY DMC GAMES LTD</p></html>");
    	cp.add(frontImg);//add these two labels to the contentpane the container
    	cp.add(frontImg2);
    	
    	
    	cp.setLayout(new FlowLayout()); // set the layout of the contentpane
    	
    	
    	newPlayer();     // add these methods
    	newGame();
    	newLevel();
    	save();
    	info();
    
    	
    	JMenuBar menubar = new JMenuBar(); //create a Menubar
    	
    	
    	setJMenuBar(menubar);
    	menubar.add(player); // add these menuItems too the menubar//
    	menubar.add(level);
    	menubar.add(game);
    	menubar.add(save);
    	menubar.add(info);
    	
    	
    	pgb = new JProgressBar(); // create a progress bar
    	
    	
        pgb.setIndeterminate(true); // sets the progress bar to be constantly moving
        pgb.setVisible(false);
        pgb.setStringPainted(true);    // sets the string  to be painted too the progressbar
        cp.add(pgb);
        
        panel=new JPanel();   // a new jpanel is created
        
        
        panel.setLayout(new BorderLayout()); // the layout of the panel is created
        icon=new ImageIcon(pic.get(i));       /// edited for arraylist
        jlb1=new JLabel();
        jlb1.setIcon(icon);                    // the label is set too the first image in the arraylist pics.
        panel.add(jlb1,BorderLayout.NORTH);    // this label is added to the northern area of the layout ie top
        	                                              
        
        jTfans = new JTextField();   /// a jtextfield is created
        
        
        jTfans.setHorizontalAlignment(JTextField.CENTER);  // the textfield is added too the panel with a horizontal fixed position
        panel.add(jTfans,BorderLayout.CENTER);  
        	                                           
        
        
        btn1 = new JButton("CHECK ANSWER");
        
        
        btn1.addActionListener(this);    // a jbutton is created and an actionlistener(this) is added too the object
        panel.add(btn1,BorderLayout.SOUTH);
        
        
       
        btn2 = new JButton("HINT");    // a second Jbutton is added
        
        
        btn2.addActionListener(this);
        panel.add(btn2,BorderLayout.WEST);
        
      
        jlb2=new JLabel(String.valueOf(counter));  // a jlabel is created and set to the value of counter which is incremented by Atimer class //
        
        
        jlb2.setBackground(Color.WHITE);
        panel.add(jlb2,BorderLayout.EAST);
        
        
        panel.setSize(500,500);                       // the panels size is set as the game is set inside  a container with the panels holding the game
        panel.setLocation(250,250);
        panel.setVisible(false);                         // panels are false to give the feeling of flow between loading and playing the game
        
        cp.add(panel);    ///////////// first panel is added too the content pane/////
   
        
        panel2 = new JPanel();                                  // these panels are to store the players name and the score of the users correct entries
        
        panel2.setLayout(new FlowLayout());
        
   
        jlb3=new JLabel("PLAYER NAME");
        panel2.add(jlb3,BorderLayout.SOUTH);
        
        
        jlb4=new JLabel("Score");
        panel2.add(jlb4,BorderLayout.SOUTH);
        
        
        panel2.setVisible(false);
        
        
        cp.add(panel2);   ////////////// second panel is added too the content pane////
        
        
        panel3 = new JPanel();        // this panels is added and stores the Jpanel that contains the hints.
        
        
        jlb5 = new JLabel("HINT");       // the idea is to keep the panels and the game looking tight in a small space like a mobile app would look
        panel3.add(jlb5);
        
        
        panel3.setVisible(false);
        
        
        cp.add(panel3);//////////////////////// third panel is added too the content pane///
        
        
    
  }  //constructor
  
  //////////////////////////////////////////////////////////////////////////end constructor/////////////////////////////////////////////////////////////////////

            
            
            
 
  ///////////////////////////////////////////////////////////////////////////////////METHODS///////////////////////////////////////////////////////////////////////
  
 
  public void HintsRotate(){
  	                                                        /// this method rotates the hints arraylist
  	jlb5.setText(hints.get(j));
  	
  }
  
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public void ShowRules(){
  	
  	
  	JTextArea ttr = new JTextArea(20,35);                                                      // this method shows the rules via the info Menubar object
  	
  	String htp ="   \n             WELCOME TO GUESS THE BADDIE??\n\n"+                         // the text area contains a scroll pane to view text that is outside
  		"1::FIRST ADD YOUR NAME(SELECT CREATE PLAYER).\n\n"+                                   // the text area size
  		"2::SECOND SELECT A LEVEL DIFFICULTY.\n\n"+
  		"    BEGINNER = 240 Seconds:\n\n"+
  		"    INTERMEDIATE = 180 Seconds:\n\n"+
  		"    ADVANCED = 120 Seconds:\n\n"+
  		"3::THEN SELECT START GAME AND WAIT FOR,\n"+
  		"    THE GAME TO LOAD.\n\n"+
  		"4::YOU THE PLAYER MUST GUESS THE BADDIES NAME BEFORE THE TIMER RUNS OUT.\n\n"+
  		"5::YOU CAN SELECT TO SAVE AND VIEW YOUR SCORE BY SELECTING.\n\n"+
  		" (A) SAVE.\n\n"+
  		" (B) VIEW.\n\n"+
  		"6::THE PLAYER GETS 1 POINT FOR EVERY CORRECT ANSWER.\n\n"+
  		"   BEAT THE CLOCK AND GET THEM ALL RIGHT TO RECIEVE A,\n\n"+
  		"   SURPRISE THUMBS UP!!!!!!!!!!!!!!.\n\n"+
  		"   ENJOY!!!!!!!!!!!!!!!!!!.";
  		
  		ttr.append(htp); // appends the text too the textarea
  		
  		JScrollPane scroll = new JScrollPane(ttr); // inserts the textarea into the scrollpane
  		
  		JOptionPane.showMessageDialog(null,scroll);
  	
  	
  }
  
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public void StartGame(){                                                  // This is the start game method that runs the game//
  	
  	 pgb.setValue(25);
     pgb.setVisible(true);/// displays the progressbar with a value of 25%
     	
    	
    	
    JOptionPane.showMessageDialog(null,"Loading Game Please select OK to continue");
    	
    	timer = new Timer(1000, new MyTimerActionListener());                 /// this code creates a delay in the screen to give the impression of the game loading
    	
                                                                              // there is a delay of ten seconds before the game loads
     timer.start();
     
    try {
    	
      Thread.sleep(10000);                                                     // delay.
    } catch (InterruptedException q) {
    	
    }
    
    
    
    timer.stop();                            // stop the timer a fter ten seconds//
    pgb.setValue(100);                       // set the progress bar to 100%
    pgb.setVisible(true);                    // and set it visible//
    
    
    
    int choice=JOptionPane.showConfirmDialog(null,"Game Loaded do You wish to Proceed");     /// a prompt for the user to proceed

   if(choice==JOptionPane.YES_OPTION){
   	
   	pgb.setVisible(false);////////////////////////// make the progress bar invisible
   	frontImg.setVisible(false);//////////////////// make the  animation invisible////
   	panel.setVisible(true);/////////////////////// show all the game panels
   	panel2.setVisible(true);
   	panel3.setVisible(true);
   	
   	
   	ATimerExample ata = new ATimerExample();  ///////////////////////// an instance of the timer class is created to run the timer as the game has started
   		
   		 timer = new Timer(1000, new ActionListener() {               /*****************************************************
                                                                       *    Title: ATimerExample
                                                                       *    Author: Walid
               ///// This timer class is referenced                    *    Site owner/sponsor: stackoverflow.com
                // that I altered and commented for this program       *    Date: 2013
                                                                       *    Code version: edited Jan 10 '13 at 17:42
                                                                       *    Availability: http://stackoverflow.com/questions/9721066/how-to-display-java-timer-on-a-separate-j-frame-form-label/
                                                                       ******************************************************/                      
                                                                                          
          @Override  /// annotation used to state a method above an method overidden by the superclass
            public void actionPerformed(ActionEvent e) {
               jlb2.setText(String.valueOf(counter));//////////////////// sets the value of the string to the label
                counter--;                           /// decrement the counter
                if (counter == 0) {                  // if counter is zero stop
                    
                      timer.stop();
                      
                      JOptionPane.showMessageDialog(null,"Time is up\nYou didn't guess all the Baddies\nPlease save your score and restart the Program");////////////// timer has ran out game over etc.
                      panel.setVisible(false);/////////////////////// hide all the game panels
                      panel2.setVisible(false);
   	                  panel3.setVisible(false);
   	                  frontImg.setVisible(true);//// show anomation
   	                  
                }
            }
        });
        timer.start();
   		                                                           //////// nnnb
   	
   	
   	jlb2.setText(String.valueOf(ata.counter));///////////////////// set my jlb2(Jlabel) to the value of the instantiated class instance variable counter//
   	
   	
   }/// end choice
  	
  	
  	
  	
  	
  	
  }/// end start Game
  
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public void CheckAnswer(){  // this method compares the answer the user enters to the predefined answer in the ans Arraylist
  	
  	
  		
  		if(jTfans.getText().equals("")||!jTfans.getText().equals(ans.get(i))){ /// changed to () from [] for arraylist
  			
  			JOptionPane.showMessageDialog(null,"You must enter a correct answer !!!!!"); // if the answer is incorrect or and empty string do this!!
  			
  		   
  		   	}
  	 
  	
     else{
     
        
    if(jTfans.getText().equals(ans.get(i))){     /// changed to () from [] for arraylist
    	
                                                                                                             // else if the answer is correct do this
  	
  		
  	JOptionPane.showMessageDialog(null,"Welldone you guessed a Baddie");
  	score++;
  	String s = new Integer(score).toString();// nnnb /// s is cast to a string here as it must be displayed in the jlabel
  	jlb4.setText("POINTS::"+s);
  	
  	
  	
  	jlb5.setText("Hint!!");
  	
    i++;// rotates the answer
    k++;// rotates the picture
    j++;// rotates hints

  	icon=new ImageIcon(pic.get(k));  /// edited for arraylist
  	jlb1.setIcon(icon);//////////////////set the new image
  	
  	jTfans.setText("");// reset the text area//
  	 
  	if(score==18) {      // ie if you win
  		                         
  		timer.stop();// stop the timer if the score is 18
  		JOptionPane.showMessageDialog(null,"Congradulations " +pd.toString()+" you beat the baddies"+"Your Score is "+score);
  		
  		panel.setVisible(false);  /// hide all panels and images
        panel2.setVisible(false);
   	    panel3.setVisible(false);
   	    frontImg2.setVisible(false);
   	    ImageIcon ic = new ImageIcon("chuck-norris.jpg");// a new image is created
   	    hero = new JLabel();
   	    hero.setIcon(ic); // a new jlabel is assigned an icon with an image
   	    add(hero); // add this icon to the JFrame
   	    JOptionPane.showMessageDialog(null,"Please exit or ReLoad the Program"); // show the image
  		
  		 
  
  	
  	}// end if score
  	
  	
  	}/// end if getText().equals()
  	
  	
         }/// end else 
     
  	
  	
  }/// end check
  
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

  
  public void CreatePlayer(){                        // a player is created
  	
  		pd = new PlayerDetails(); // the object reference refers to the playerdetails class(public)
    	
    	String a=JOptionPane.showInputDialog(null,"Please enter your First name!");
    	pd.setFirst(a);// Mutators are accesed of playerdetails
    	String b=JOptionPane.showInputDialog(null,"Please enter your Second name!");
    	pd.setSecond(b);
    	
    	jlb3.setText(pd.toString()); // the jlabel takes the tostring method of playerDetails(get())
    	
    	JOptionPane.showMessageDialog(null,"Name added");
  }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  public  static void MoveLabel1(){  // this method is code i edited to create an animation
          int x=1;                   // at the start of my program                               //Title:: Simple Animation
          int y=5;                                                                              //Author:: Unknown 
          try{                                                                                  //Site/Owner::StackOverflow.com,Java2s.com,
              while(x<500){                                                                     //Version 2012
                     frontImg.setLocation(x,y);   //while x is less than 500                    // Availability::www.stackoverflow.simple animation.com
                    Thread.sleep(140);             //move sleep / delay the program                                             //
                    x+=20;                         // move the label x 20,y 20
                    y+=20;
                    if(x>500) x=1;                 // if x>500 go back to the start ie x is 1
                    if(y>500) y=5;
 
                    }  // end while
   
           } // end try
           catch(InterruptedException ie){            // the method is static as it must run in main before the image array is view which also is declared static
           	                                                 // it is asimple animation with out using graphics//
           	System.out.println("Interrupted...");
           	
           	} // end catch
    }
    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
  
  
  
  public void saveGame() throws IOException{ // this method saves a players details to a dat file//
  	
  	ObjectOutputStream os;
      	os = new ObjectOutputStream(new FileOutputStream ("PLyrD.dat"));
      	os.writeObject(pd); // write the object to the file pd is an object reference to Playerdetails
      	os.close();
  }
  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  



 public void viewGame() {                                                             //// Altered From
      	try{                                                                              //Lab notes John Walsh Lecturer
      	  ObjectInputStream is;
      	  is = new ObjectInputStream(new FileInputStream ("PLyrD.dat"));
          pd  = (PlayerDetails)is.readObject(); /// readback the playerdetails
      	  is.close();
      	  JOptionPane.showMessageDialog(null,pd.toString()+" Your Score is :: "+score);/// to display returned file///
      	}
      	catch(Exception e){
      		JOptionPane.showMessageDialog(null,"You did not enter a name and save");// if the message/file is empty
      		e.printStackTrace();
      	}
      	
      } 
      
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


       private void newPlayer(){
    	
    	
    	JMenuItem drop;  // reusable object
    	
    	player = new JMenu("NewPlayer"); // a jmenu called new player
    	
    	drop = new JMenuItem("Create Player"); // add create player too the dropdown menu 
    	drop.addActionListener(this);
    	player.add(drop);
    	
    	
            }
            
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
      private void newGame(){
    	
    	
    	JMenuItem drop;  // reusable object
    	
    	game = new JMenu("NewGame"); // a jmenu called new game 
    	
    	drop = new JMenuItem("Start Game"); // add startgame too the dropdown menu 
    	drop.addActionListener(this);
    	game.add(drop);
    	
    	
     }
     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     
     private void newLevel(){
    	
    	
    	JMenuItem drop;  // reusable object
    	
    	level = new JMenu("SelectLevel"); // a jmenu called selectLevel
    	
    	drop = new JMenuItem("Beginner"); // 
    	drop.addActionListener(this);
    	level.add(drop);
    	drop = new JMenuItem("Intermediate"); // 
    	drop.addActionListener(this);
    	level.add(drop);
    	drop = new JMenuItem("Advanced"); // 
    	drop.addActionListener(this);
    	level.add(drop);
    	
    	
     }
     
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     private void save(){
     	
     	JMenuItem drop;
     	
     	save = new JMenu("Save/View"); // same as avove
     	
     	drop = new JMenuItem("Save Game");
     	drop.addActionListener(this);       // this referes to this object ie this JmenuItem object
     	save.add(drop);
     	
     	
     	drop = new JMenuItem("View Scores");
     	drop.addActionListener(this);
     	save.add(drop);
     	
     	
     	drop = new JMenuItem("Exit Game");
     	drop.addActionListener(this);
     	save.add(drop);
     	
     	
     }
     
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
     	
     	
     	private void info(){
     	
     	JMenuItem drop;
     	
     	info = new JMenu("GameInfo");              /// same as above
     	
     	drop = new JMenuItem("How To Play");
     	drop.addActionListener(this);
     	info.add(drop);
     	
     	
      }	
      	
   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      	
     
     public static void ADDHINTS(){                                              // a static method in main that creates that adds the hints too the hint arraylist
     	
      hints = new ArrayList<String>();
      hints.add("A Sith Lord From The Old Republic");
      hints.add("Batmans Arch Nemesis");
      hints.add("First Name Is A Type Of Sugar");
      hints.add("The Bounty Hunters Real Name");
      hints.add("Jim Carey Plays This Guy");
      hints.add("Toy Story 3 Baddie");
      hints.add("W");
      hints.add("Hurrright");
      hints.add("Cmon BalBoa");
      hints.add("Krustys Shady Friend");
      hints.add("Hates Hedgehogs");
      hints.add("10 x 10");
      hints.add("The Good Doctor Knows Best");
      hints.add("Lives In Castle GreySkull");
      hints.add("Theres Something About...");
      hints.add("Bond Enemy");
      hints.add("Harsh Opposite Of Good");
      hints.add("Muttlys Owner");
      
     }
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     	
     public static void ADDANSWERS(){                    /// same as above except this time it adds the answers to the ans array list
     	
      ans = new ArrayList<String>();
      ans.add("Darth Malgus");
      ans.add("Joker");
      ans.add("Caster Troy");
      ans.add("Boba Fett");
      ans.add("The Grinch");
      ans.add("Lots O");// lotsohugs
      ans.add("Dog");// Patches O callaghans enemy
      ans.add("Snake");//snake
      ans.add("Clubber Lang");//clubber lang
      ans.add("Sideshow Bob");//sideshow Bob
      ans.add("Doctor Robotnick");// Doctor Robotnick
      ans.add("T1000");// t1000
      ans.add("Hanibal Lecter");// lecter
      ans.add("Skeletor");//
      ans.add("Mouse");// about mary
      ans.add("Jaws");// Jaws
      ans.add("Doctor Evil");// dr Evil
      ans.add("Mouse");//Muttlys owner
      
     }
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     public static void ADDPICTURES(){
     	
      pic = new ArrayList<String>();
      pic.add("Baddie.jpg");                               // same as above except this time it adds the  pictures to the array list pic
      pic.add("Baddie4.jpg");
      pic.add("Baddie3.jpg");
      pic.add("Baddie2.jpg");
      pic.add("Baddie5.jpg");
      pic.add("Baddie6.jpg");
      pic.add("Baddie7.jpg");
      pic.add("Baddie8.jpg");
      pic.add("Baddie9.jpg");
      pic.add("Baddie10.jpg");
      pic.add("Baddie11.jpg");
      pic.add("Baddie12.jpg");
      pic.add("Baddie13.jpg");
      pic.add("Baddie14.jpg");
      pic.add("Baddie15.jpg");
      pic.add("Baddie16.jpg");
      pic.add("Baddie17.jpg");
      pic.add("Baddie18.jpg");
      pic.add("end.jpg");
      
     }
     
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
 //////////////////////////////////////////////////////////////////////////////////////ACTION LISTENERS///////////////////////////////////////////////////////////

    
    public void actionPerformed(ActionEvent e){
    	
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    	
    	
    if(e.getActionCommand().equals("Start Game")){
    	
    	
    	StartGame(); // runs the start game method
    }
  
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  	
  
 
  	if(e.getSource()==btn1){
  		
  		
  		CheckAnswer();   // runs the check answer method
  		
  	
   	}// end btn1 if
            	
  	
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  	
  	if(e.getSource()==btn2){
  		
  		
  		
  		HintsRotate();  // runs the rotate method
  		
  	}
  	
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    
    if(e.getActionCommand().equals("Create Player")){
    	
    	CreatePlayer();  // runs the create player method
    	
   
   }
/////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////   
  	
    if(e.getActionCommand().equals("Beginner")){
    	
    	counter=240;
    	JOptionPane.showMessageDialog(null,"Game level loaded");  // this sets the counter/timer to the maximum amount(default value)
    }//
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  	
  	if(e.getActionCommand().equals("Intermediate")){
    	
    	counter=180;                                                      // this sets the counter/timer to an amount of 160
    	JOptionPane.showMessageDialog(null,"Game level loaded");
    	
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if(e.getActionCommand().equals("Advanced")){
    	
    	counter=120;
    	JOptionPane.showMessageDialog(null,"Game level loaded");       // same as above
    	
   }
  
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  
  
   if(e.getActionCommand().equals("Save Game")){
   	
   	
   	  try{
      	 	saveGame();                             // this method saves the game
      	 	
      	 } // try
      	 catch (IOException f){
      	 	
      	 	f.printStackTrace();
      	 }// catch                                     // if not throw/catch an IO exception
   	
   	
   	 }
   	 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  
  if(e.getActionCommand().equals("View Scores")){
   	
   	
                	viewGame();                          // runs the view game method
   	 }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  if(e.getActionCommand().equals("How To Play")){
  	
  	ShowRules();                                                    //  runs the showrules method
  	
  }
 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
  if(e.getActionCommand().equals("Exit Game")){
  	
  	System.exit(0);                                                   // exits the game
  	
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
    		
    }////end  actionListener implements etc
    	
    
   
    
    
    
    
    
    
  }/// end the CLASS
    
    
