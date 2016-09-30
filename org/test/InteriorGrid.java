package org.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.maker.Playable.Controller.FirstController;

public class InteriorGrid extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridIso iso  ;
	JTextField jtf = new JTextField() ;
	JPanel container = new JPanel();
	
	public InteriorGrid(GridIso iso ){
		this.iso=iso ;
		this.setTitle("Frame");
		this.setClosable(true);
		this.setResizable(true);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(iso, BorderLayout.CENTER);
		
		JPanel north = new JPanel() ;
		jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(0, 0));
		jtf.addKeyListener(new FirstController( iso));
		north.add(jtf);
		container.add(jtf , BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setSize(1010, 600);
		this.setLocation(-5,-30);
		//this.setContentPane(iso);
		this.setVisible(true);
	}
}
