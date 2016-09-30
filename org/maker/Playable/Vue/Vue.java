package org.maker.Playable.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.maker.Playable.Controller.ControllerKey;
import org.maker.ui.Content;
import org.maker.ui.Window;

public class Vue extends JFrame {

	JTextField jtf;
	JPanel container = new JPanel();
	Content p;
	Window win;

	public Vue(Window win, Content p) {
		setTitle("Mini Manette");
		setSize(500, 500);
		this.p = p;
		this.win = win;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(p, BorderLayout.CENTER);
		JPanel north  = new JPanel() ;
		jtf = new JTextField();
		
		jtf.setPreferredSize(new Dimension(0, 0));
		jtf.addKeyListener(new ControllerKey(win, p));
		north.add(jtf);
		container.add(north,BorderLayout.SOUTH);
		setContentPane(container);
		setVisible(true);
	}

}
