package xyz.scarabya.pratofiorito.core;

import java.util.*;

	public class Orologio extends TimerTask {

            @Override
	    public void run()
            {
	        Programma.aggiornaSecondi();
	    }
	}