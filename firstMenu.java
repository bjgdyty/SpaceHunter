package display;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

public class firstMenu extends Panel implements MouseListener,MouseMotionListener{
	Label lbs[];
	int width;
	int height;
	MainInterface app;
	public firstMenu(MainInterface app){
		this.app=app;
		width=640;
		height=480;
		setBounds(0,0,width,height);
		setBackground(Color.DARK_GRAY);
		lbs=new Label[2];
		lbs[0]=new Label("Start");
		lbs[1]=new Label("Exit");
		Font font=new Font("Arial", Font.BOLD, 40);
		
			lbs[0].setFont(font);
			lbs[0].setForeground(Color.cyan);
			lbs[0].setBackground(Color.darkGray);
			lbs[0].addMouseListener(this);
			lbs[0].addMouseMotionListener(this);
			add(lbs[0],BorderLayout.CENTER);
			lbs[1].setFont(font);
			lbs[1].setForeground(Color.cyan);
			lbs[1].setBackground(Color.darkGray);
			lbs[1].addMouseListener(this);
			lbs[1].addMouseMotionListener(this);
			add(lbs[1],BorderLayout.SOUTH);
	
}
	public void setLabel(int i,String text){
		lbs[i].setText(text);
	}
	public void mousePressed(MouseEvent arg0){
		
		Label lb=(Label)arg0.getSource();
		if(lb.equals(lbs[0])){
			app.setRunning();
		}
		
		else{
			System.exit(0);
		}
	}
	public void mouseEntered(MouseEvent arg0){
		for(int i=0;i<lbs.length;i++){
			if(arg0.getSource().equals(lbs[i])){
				lbs[i].setForeground(Color.red);
				break;
			}
		}
	}
	public void mouseExited(MouseEvent args0){
		for(int i=0;i<lbs.length;i++){
			if(args0.getSource().equals(lbs[i])){
				lbs[i].setForeground(Color.cyan);
				break;
			}
		}
	}
	public void mouseDragged(MouseEvent arg0){}
	public void mouseMoved(MouseEvent arg0){}
	public void mouseClicked(MouseEvent arg0){}
	public void mouseReleased(MouseEvent arg0){}

	}


