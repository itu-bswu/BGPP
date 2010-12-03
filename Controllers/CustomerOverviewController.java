package Controllers;

import Views.*;
import java.awt.event.*;

/**
 * Controller - Customer Overview
 *
 */
public class CustomerOverviewController implements MouseListener {
	CustomerOverview window;
	
	public CustomerOverviewController(CustomerOverview window) {
		this.window = window;
		
		window.addTableMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = window.getSelectedRow();
			int column = window.getSelectedColumn();
			System.out.println("x: " + row + ", y: " + column);
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
}
