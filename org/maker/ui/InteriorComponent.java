package org.maker.ui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.maker.Playable.Controller.ControllerKey;

public class InteriorComponent extends JInternalFrame {
	/**
	 * component interior of the frame.it is the Scene
	 */
	JPanel container = new JPanel();
	JTextField jtf ;
	public InteriorComponent(Window win , Content p) {
		this.setTitle("Frame");
		this.setClosable(true);
		this.setResizable(true);
		this.setSize(1010, 600);
		this.setLocation(-5,-30);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(p, BorderLayout.CENTER);
		
		JPanel north = new JPanel() ;
		jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(0, 0));
		jtf.addKeyListener(new ControllerKey(win, p));
		north.add(jtf);
		container.add(jtf , BorderLayout.SOUTH);
		setContentPane(container);
		this.setVisible(true);
	}
}

