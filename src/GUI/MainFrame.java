package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


 import javax.swing.JFrame;
 import javax.swing.WindowConstants;
 
 /**
  *
  * @author Auralea A S
  */
 public class MainFrame extends JFrame{
     public void init(){
         setTitle("Word Ladder Solver");
         setSize(450, 300);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setVisible(true);
     }
     
     public static void main (String[] args){
         MainFrame myFrame = new MainFrame();
         myFrame.init();
     }
 }
 
