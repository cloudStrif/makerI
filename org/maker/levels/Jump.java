package org.maker.levels;

import java.awt.Toolkit;

import org.maker.ui.Content;
import org.maker.ui.Window;

public class Jump implements Runnable {
	Window win;
	Content con;
	public boolean Active;

	public Jump(Window win) {
		this.win = win;
	}

	public Jump(Content con) {
		this.con = con;
		this.Active = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("miku");

		for (int i = 0; i < 11; i++) {
			// con.mario.pixelY -= (1000/24)-3 ;
			con.getMario().actual = con.getMario().parse.tab[5];
			con.getMario().setPixelY(con.getMario().getPixelY() - 12);
			con.mariopos();
			if (!con.getGrid().getField()[con.getGrid().getPosYinit()
					+ con.getMario().getPosY() - 1][con.getGrid().getPosXinit()
					+ con.getMario().getPosX()].equals("N")
					|| !con.getGrid().getField()[con.getGrid().getPosYinit()
							+ con.getMario().getPosY() - 1][con.getGrid()
							.getPosXinit() + con.getMario().getPosX() - 1]
							.equals("N")
					|| !con.getGrid().getField()[con.getGrid().getPosYinit()
							+ con.getMario().getPosY()-1][con.getGrid()
							.getPosXinit() + con.getMario().getPosX() + 1]
							.equals("N")
					
					) {
				con.getMario().setPixelX(con.getMario().getPixelX() - 36);
				con.getGrid().refresh() ;
				break;
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (con.getMario().getPosY() == 7) {
				if (con.getGrid().getPosYinit() < con.getGrid().getS()) {
					con.getMario().setPixelY(
							con.getMario().getPixelY() + ((1000 / 24) - 5));
					con.mariopos();
					con.getGrid().setPosYinit(con.getGrid().getPosYinit() - 1);
					con.getGrid().refresh();
					con.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}

			con.repaint();
			Toolkit.getDefaultToolkit().sync();
		}

		// con.mario.actual = con.mario.parse.tab[con.mario.parse.tab.length
		// -2];

		con.mariopos();
		con.getGrid().refresh();
		con.repaint();
		// Toolkit.getDefaultToolkit().sync();
		// con.gravity.Active = true;
		con.mariopos();

		while ((con.getGrid().getField()[con.getGrid().getPosYinit()
				+ con.getMario().getPosY() + 1][con.getGrid().getPosXinit()
				+ con.getMario().getPosX()].equals("N")
				|| con.getGrid().getField()[con.getGrid().getPosYinit()
						+ con.getMario().getPosY() + 1][con.getGrid()
						.getPosXinit() + con.getMario().getPosX()].equals("p") || con
				.getGrid().getField()[con.getGrid().getPosYinit()
				+ con.getMario().getPosY() + 1][con.getGrid().getPosXinit()
				+ con.getMario().getPosX()].equals("4"))
				&& con.getMario().getPosY() < 14) {

			if (con.getGrid().getField()[con.getGrid().getPosYinit()
					+ con.getMario().getPosY() + 1][con.getGrid().getPosXinit()
					+ con.getMario().getPosX()].equals("p")) {
				con.getMario().setPiece(con.getMario().getPiece() + 1);
			}
			if (!con.getGrid().getField()[con.getGrid().getPosYinit()
					+ con.getMario().getPosY() + 1][con.getGrid().getPosXinit()
					+ con.getMario().getPosX()].equals("N")
					&& !con.getGrid().getField()[con.getGrid().getPosYinit()
							+ con.getMario().getPosY() + 1][con.getGrid()
							.getPosXinit() + con.getMario().getPosX()]
							.equals("p")) {
				break;
			}

			// con.mario.pixelY += (1000/24)-5;
			con.getMario().setPixelY(con.getMario().getPixelY() + 12);
			con.mariopos();

			if (con.getGrid().getField()[con.getGrid().getPosYinit()
					+ con.getMario().getPosY() - 1][con.getGrid().getPosXinit()
					+ con.getMario().getPosX()].equals("4")
					|| con.getGrid().getField()[con.getGrid().getPosYinit()
							+ con.getMario().getPosY() - 1][con.getGrid()
							.getPosXinit() + con.getMario().getPosX()]
							.equals("p")) {
				con.getGrid().getField()[con.getGrid().getPosYinit()
						+ con.getMario().getPosY() - 1][con.getGrid()
						.getPosXinit() + con.getMario().getPosX()] = "N";
			}
			con.repaint();
			Toolkit.getDefaultToolkit().sync();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (con.getMario().getPosY() >= 10) {
				if (con.getGrid().getPosYinit() < con.getGrid().getS()) {
					con.getMario().setPixelY(
							con.getMario().getPixelY() - ((1000 / 24) - 5));
					con.mariopos();
					con.getGrid().setPosYinit(con.getGrid().getPosYinit() + 1);
					con.getGrid().refresh();
					con.repaint();
					Toolkit.getDefaultToolkit().sync();
				}
			}

		}

		con.getGrid().getField()[con.getGrid().getPosYinit()
				+ con.getMario().getPosY()][con.getGrid().getPosXinit()
				+ con.getMario().getPosX()] = "4";
		con.getGrid().refresh();
		System.out.println("forum des halles");

		// Close the current Thread ..

		int value = ((600 / 18) * (con.getMario().getPosY() + 1))
				- con.getMario().getPixelY();

		con.getMario().setPixelY(con.getMario().getPixelY() + (value - 15));// maybe
																			// change
																			// 15
																			// on
																			// linux
																			// ..
		con.mariopos();
		// con.DEBUG();

		con.repaint();
		Toolkit.getDefaultToolkit().sync();

		if (!con.getGrid().getField()[con.getGrid().getPosYinit()
				+ con.getMario().getPosY() + 1][con.getGrid().getPosXinit()
				+ con.getMario().getPosX()].equals("N"))

			con.mariopos();
		con.getGrid().refresh();
		con.getJump().Active = true;
		con.getGravity().Active = true;
		Thread.currentThread().interrupt();

		// Thread.currentThread().interrupt();

	}

}
