package org.maker.ui.ani;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class History extends JPanel {
	public String CONTENT = "";//this String contains history.
	private JTextArea jta;

	public History() {
		this.jta = new JTextArea(CONTENT);
		this.setBackground(new Color(255,255,255));
		jta.setBackground(new Color(255,255,255));
		jta.setForeground(Color.BLACK);
		this.add(jta);
		jta.setEditable(false) ; 
	}

	public void setTexte(String texte) {
		this.jta.setText(texte);
	}

	public String getTexte() {
		return this.jta.getText();
	}
}
