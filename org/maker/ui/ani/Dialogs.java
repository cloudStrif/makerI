package org.maker.ui.ani;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dialogs extends JDialog {
	Integer[] size = new Integer[2];
	JComboBox dim;

	int positionX = 0;
	int positionY = 0;
	private JComboBox dim2;
	private JLabel icon;
	private JTextField nom;
	private JLabel nomLabel;
	private JTextField nom2;

	public Dialogs(JFrame a ,String b , boolean c) {
		super(a,b,c);
		this.setSize(550, 200);
		this.setLocation(200, 200);
		this.setLocationRelativeTo(null);
		// this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		/*
		 * this.setUndecorated(true);
		 * 
		 * addMouseListener(new MofuseAdapter() {
		 * 
		 * public void mousePressed(MouseEvent e) { positionX = e.getX(); //
		 * Position X de la souris au clic positionY = e.getY(); // Position Y
		 * de la souris au clic } });
		 * 
		 * addMouseMotionListener(new MouseMotionAdapter() {
		 * 
		 * @Override public void mouseDragged(MouseEvent e) { int depX =
		 * e.getX() - positionX; int depY = e.getY() - positionY;
		 * setLocation(getX() + depX, getY() + depY); } });
		 */
		this.initComponent();

		this.setVisible(true);
		
	}

	public Integer[] showZDialog() {

		return this.size;
	}

	@SuppressWarnings("rawtypes")
	private void initComponent() {
		icon = new JLabel(new ImageIcon("org/maker/ressources/main/dekens.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(50, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Longueur"));
		nomLabel = new JLabel("Saisir un nombre :");
		panNom.add(nomLabel);
		panNom.add(nom);

		JPanel panNom2 = new JPanel();
		panNom2.setBackground(Color.white);
		panNom2.setPreferredSize(new Dimension(220, 60));
		nom2 = new JTextField();
		nom2.setPreferredSize(new Dimension(50, 25));
		panNom2.setBorder(BorderFactory.createTitledBorder("Largeur"));
		nomLabel = new JLabel("Saisir un nombre :");
		panNom2.add(nomLabel);
		panNom2.add(nom2);

		JPanel cont = new JPanel();
		cont.setBackground(Color.white);
		cont.add(panNom2);
		cont.add(panNom);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");

		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// zInfo = new ZDialogInfo(nom.getText(),
				// (String)sexe.getSelectedItem(), getAge(),
				// (String)cheveux.getSelectedItem() ,getTaille());
				if (isNumericOne(nom.getText()) && isNumericOne(nom2.getText())) {
					size[0] = Integer.parseInt(nom.getText());
					size[1] = Integer.parseInt(nom2.getText());
					setVisible(false);
				} else {
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, "Vous devez saisir 2 Digits",
							"Informations personnage",
							JOptionPane.INFORMATION_MESSAGE);
				}
				// dispose() ;
			}

		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		// this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(panIcon, BorderLayout.NORTH);
		this.getContentPane().add(cont, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}

	public static boolean isNumericOne(String s) {
		return s.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	/*public static void main(String[] args) {
		Dialogs zd = new Dialogs();
		Integer[] test = zd.showZDialog();

	}*/
}
