package main;


import dao.ClientDAO;
import model.Client;
import presentation.MainFrame;

import java.util.List;

/**
 *  Aceasta este clasa din care pornim aplicatia
 */
public class App {

	private static void  interfGraf()
	{
		MainFrame interfG = new MainFrame();
		interfG.setVisible(true);

	}

	public static void main(String[] args)
	{
		interfGraf();

	}
}
