package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.plaf.metal.MetalButtonUI;

public class Menu extends Panel implements MouseListener,MouseMotionListener {
Label lbs[];
int width;
int height;
MainInterface app;
public Menu (MainInterface app) {
	// TODO Auto-generated constructor stub
	this.app=app;
	width=100;
	height=160;
	setBounds((640-width)/2,(480-height)/2,width,height);
	setBackground(Color.DARK_GRAY);
	lbs=new Label[4];
	lbs[0]=new Label("start");
	lbs[1]=new Label("save");
	lbs[2]=new Label("load");
	lbs[3]=new Label("exit");
	Font font=new Font("Arial", Font.BOLD, 20);
	for(int i=0;i<lbs.length;i++){
		lbs[i].setFont(font);
		lbs[i].setForeground(Color.cyan);
		lbs[i].setBackground(Color.darkGray);
		lbs[i].addMouseListener(this);
		lbs[i].addMouseMotionListener(this);
		add(lbs[i]);
	}
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
