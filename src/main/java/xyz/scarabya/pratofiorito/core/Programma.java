package xyz.scarabya.pratofiorito.core;

import Finestre.perdita;
import Finestre.vittoria;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import xyz.scarabya.pratofiorito.finestre.Nuova_Partita;
import xyz.scarabya.pratofiorito.finestre.Testa;

public class Programma
{
    private static JFrame finestra;        
    public static Gioco game;
    public static Pulsantiera tastiera;
    public static Testa header;
    public static Menu barra;

    public static void start()                                                  //FUNZIONE DI LANCIO DEL PROGRAMMA
    {                                                                           //VIENE LANCIATA DAL MAIN
        game = new Gioco();                                                     //INIZIALIZZA UN NUOVO GIOCO
        header = new Testa();                                                   //INIZIALIZZA UN NUOVO HEADER FINESTRA
        barra = new Menu();                                                     //INIZIALIZZA UNA NUOVA BARRA MENU
        Nuova_Partita.main();                                                   //LANCIA LA FINESTRA DI SCELTA NUOVA PARTITA
    }

    public static void creaGioco(int righe, int colonne, int fiori)
    {
        try {finestra.dispose();} catch (Exception E) {}
        
        finestra = new JFrame("Prato Fiorito");
        tastiera = new Pulsantiera(righe, colonne);        

        game.nuovoGioco(righe, colonne, fiori);
        header.fioriRimasti(fiori);
        finestra.setJMenuBar(barra);

        finestra.setLayout(new BorderLayout());
        finestra.add(tastiera,BorderLayout.CENTER);
        finestra.add(header,BorderLayout.NORTH);

        finestra.setVisible(true);

        int altezzaH = (int) header.getSize().getHeight();
        finestra.setBounds(10, 10, (colonne*45), (altezzaH+(righe*45)));

        finestra.addWindowListener(new Eventi.gestoreF());
        header.tempo(game.Tempo());
    }

    public static void aggiornaSecondi()
    {
        header.tempo(game.Tempo());
        game.incrementaSecondi();
    }

    public static void pulsantePremuto(int r, int c)
    {
        game.premiPulsante(r, c);
        aggiornaCampo();
        if(!game.getInGioco())
            finePartita();
    }

    public static void finePartita()
    {
        if(game.vittoria())
        {
            for(int i = 0; i<game.getRighe(); i++)
                for(int j = 0; j<game.getColonne(); j++)
                    if(game.getCasella(i, j)==-1)
                    {
                        tastiera.mettiFiore(i, j);
                        tastiera.cambiaColore(i, j, Color.green);
                    }
            vittoria.main(game.Tempo());
            System.out.println(game.Tempo());
        }
        else
        {
            for(int i = 0; i<game.getRighe(); i++)
                for(int j = 0; j<game.getColonne(); j++)
                    if(game.getCasella(i, j)==-1)
                    {
                        tastiera.mettiFiore(i, j);
                        if(game.Bandierato(i, j))
                            tastiera.cambiaColore(i, j, Color.green);
                        else
                            tastiera.cambiaColore(i, j, Color.blue);
                    }
                    else
                        if(game.Bandierato(i, j))
                            tastiera.cambiaColore(i, j, Color.red);
            perdita.main();
        }
    }

    public static void pulsanteBandierato(int r, int c)
    {
        if(game.getInGioco()&&!game.getScoperta(r, c)&&
                (game.fioriRimasti()>0||game.Bandierato(r, c)))
        {
            game.Bandiera(r, c);
            header.fioriRimasti(game.fioriRimasti());
            aggiornaCampo();
        }
    }

    public static void aggiornaCampo()
    {
        boolean bandierine[][] = game.getStatoBandierine();
        boolean scoperte[][] = game.getStatoScoperte();
        int caselle[][] = game.getStatoCaselle();
        int daAggiornare[][] = game.daAggiornare();
        int modificati = game.modificati();
        for(int i = 0; i<modificati; i++)
            setBottone(daAggiornare[i][0], daAggiornare[i][1],
                caselle[daAggiornare[i][0]][daAggiornare[i][1]],
                scoperte[daAggiornare[i][0]][daAggiornare[i][1]],
                bandierine[daAggiornare[i][0]][daAggiornare[i][1]]);
        game.Aggiornato();
    }

    public static void setBottone
            (int riga, int colonna, int numero,
            boolean scoperto, boolean bandierato)
    {
        if(bandierato)
            tastiera.mettiBandiera(riga, colonna);
        else
        {
            if(scoperto)
            {
                if(numero>-1)
                    tastiera.disabilita(riga, colonna);
                if(numero>0)
                    tastiera.scriviTesto(riga, colonna, numero);
                if(numero==-1)
                    tastiera.mettiFiore(riga, colonna);
            }
            else
                tastiera.svuota(riga, colonna);
        }
    }

    public static void nuovaPartita() {
        Programma.creaGioco(game.getRighe(), game.getColonne(), game.getFiori());
    }

    public static String impacchettaStato()
    {
        return game.toString();
    }
}
