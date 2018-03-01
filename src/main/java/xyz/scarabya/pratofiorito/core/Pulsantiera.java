/* CLASSE PULSANTIERA:
 * CLASSE PULSANTIERA
 * L'INTERA CLASSE E' UN PANNELLO JPanel DOTATO
 * DI TUTTE LE FUNZIONI DI CONTROLLO E PRONTO
 * AD ESSERE INTEGRATO IN UN FRAME
 */
package xyz.scarabya.pratofiorito.core;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Pulsantiera extends JPanel
{
    private final ImageIcon fiore = new ImageIcon
            (Programma.class.getResource
            ("/fiore.png"));
    private final ImageIcon bandiera = new ImageIcon
            (Programma.class.getResource
            ("/bandiera.png"));
    private final JButton pulsanti[][];

    ////FUNZIONE CREAZIONE PULSANTIERA\\\\
    public Pulsantiera(int righe, int colonne)                                  //COSTRUTTORE CON PARAMETRI
    {
        pulsanti = new JButton[righe][colonne];                                 //CREAZIONE MATRICE PULSANTI
        setLayout(new GridLayout(righe,colonne,1,1));                           //SETTAGGIO DEL LAYOUT
        for(int i=0; i<righe; i++)                                              //PER OGNI RIGA
            for(int j=0; j<colonne; j++)                                        //PER OGNI COLONNA
            {
                pulsanti[i][j] = new JButton();                                 //CREA IL PULSANTE NELLA MATRICE
                pulsanti[i][j].addActionListener(new Eventi.gestorePulsanti()); //AGGIUNGI IL GESTORE EVENTI (TASTO SX)
                pulsanti[i][j].addMouseListener(new Eventi.gestoreM());         //AGGIUNGI IL GESTORE EVENTI (TASTO DX)
                pulsanti[i][j].setActionCommand
                        (String.valueOf(i)+"|"+String.valueOf(j));              //SEGNA LE COORDINATE SUL PULSANTE
                add(pulsanti[i][j]);                                            //AGGIUNGE IL PULSANTE AL PANNELLO
            }
        setVisible(true);                                                       //RENDE VISIBILE IL PANNELLO
    }
    ////||||||||||||||||||||||||||||||\\\\

    ////FUNZIONI CONTROLLO PULSANTI\\\\
    public void mettiBandiera(int riga, int colonna)                            //TOGLIE IL NUMERO E METTE LA BANDIERA
    {
        pulsanti[riga][colonna].setText("");
        pulsanti[riga][colonna].setIcon(bandiera);
    }
    public void mettiFiore(int riga, int colonna)                               //TOGLIE IL NUMERO E METTE IL FIORE
    {
        pulsanti[riga][colonna].setText("");
        pulsanti[riga][colonna].setIcon(fiore);
    }
    public void scriviTesto(int riga, int colonna, int numero)                  //TOGLIE L'ICONA E SCRIVE IL NUMERO
    {
        pulsanti[riga][colonna].setIcon(null);
        pulsanti[riga][colonna].setText(String.valueOf(numero));
    }
    public void disabilita(int riga, int colonna)                               //DISABILITA IL PULSANTE
    {
        pulsanti[riga][colonna].setEnabled(false);
    }
    public void svuota(int riga, int colonna)                                   //TOGLIE TUTTO DAL PULSANTE
    {
        pulsanti[riga][colonna].setIcon(null);
        pulsanti[riga][colonna].setText("");
    }
    public void cambiaColore(int riga, int colonna, Color colore)               //IMPOSTA IL COLORE AL PULSANTE
    {
        pulsanti[riga][colonna].setBackground(colore);
    }
    ////|||||||||||||||||||||||||||\\\\
}
