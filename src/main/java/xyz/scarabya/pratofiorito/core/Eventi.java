package xyz.scarabya.pratofiorito.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Eventi
{
    public static class gestorePulsanti implements ActionListener
    {
        public void actionPerformed (ActionEvent e)                             //EVENTO PRESSIONE PULSANTE
        {
            int riga, colonna;
            String comando = e.getActionCommand();                              //AQUISISCE IL COMANDO ASSOCIATO AL PULSANTE
            int separatore = comando.indexOf("|");                              //TROVA LA POSIZIONE DEL CARATTERE SEPARATORE
            riga = Integer.parseInt(comando.substring(0, separatore));          //ESTRAE IL NUMERO RIGA
            colonna = Integer.parseInt(comando.substring(separatore+1));        //ESTRAE IL NUMERO COLONNA
            Programma.pulsantePremuto(riga, colonna);                           //LANCIA LA FUNZIONE PER IL PULSANTE PREMUTO
        }
    }

    public static class gestoreM extends MouseAdapter
    {
        public void mousePressed (MouseEvent e)                                 //EVENTO PRESSIONE TASTO DESTRO
        {
            int riga, colonna;
            javax.swing.JButton o = (javax.swing.JButton) e.getSource();        //AQUISISCE IL CODICE DEL PULSANTE PREMUTO
            String comando = o.getActionCommand();                              //AQUISISCE IL COMANDO ASSOCIATO AL PULSANTE
            int separatore = comando.indexOf("|");                              //TROVA LA POSIZIONE DEL CARATTERE SEPARATORE
            riga = Integer.parseInt(comando.substring(0, separatore));          //ESTRAE IL NUMERO RIGA
            colonna = Integer.parseInt(comando.substring(separatore+1));        //ESTRAE IL NUMERO COLONNA
            if(e.getButton()==3)                                                //SE E' IL TASTO DESTRO (TASTO N°3)
                Programma.pulsanteBandierato(riga,colonna);                     //LANCIA LA FUNZIONE PER IL PULSANTE BANDIERATO
        }
    }

    public static class gestoreF extends WindowAdapter                          //GESTORE FINESTRE
    {                                                                           //SENZA DI ESSO IL PROCESSO NON TERMINA
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }
}
