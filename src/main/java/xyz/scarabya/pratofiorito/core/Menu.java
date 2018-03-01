package xyz.scarabya.pratofiorito.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import xyz.scarabya.pratofiorito.finestre.Info;
import xyz.scarabya.pratofiorito.finestre.sceltaPersonalizzato;

public class Menu extends JMenuBar
{
    private javax.swing.JMenu jMenuGioco;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenu jMenuOpzioni;
    private javax.swing.JMenu jMenuLivello;
    private javax.swing.JMenu jMenuModalità;
    private javax.swing.JMenuItem jMenuItemNuovaPartita;
    private javax.swing.JMenuItem jMenuItemStatistiche;
    private javax.swing.JMenuItem jMenuItemEsci;
    private javax.swing.JMenuItem jMenuItemFacile;
    private javax.swing.JMenuItem jMenuItemMedio;
    private javax.swing.JMenuItem jMenuItemDifficile;
    private javax.swing.JMenuItem jMenuItemPersonalizza;
    private javax.swing.JMenuItem jMenuItemGuida;
    private javax.swing.JMenuItem jMenuItemInformazioni;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemPratoFiorito;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemCampoMinato;

    public Menu()
    {
        costruisci();
    }

    private void costruisci()
    {
        jMenuGioco = new JMenu();
        jMenuHelp = new JMenu();
        jMenuOpzioni = new JMenu();
        jMenuLivello = new JMenu();
        jMenuModalità = new JMenu();

        jMenuItemNuovaPartita = new JMenuItem();
        jMenuItemStatistiche = new JMenuItem();
        jMenuItemEsci = new JMenuItem();
        jMenuItemFacile = new JMenuItem();
        jMenuItemMedio = new JMenuItem();
        jMenuItemDifficile = new JMenuItem();
        jMenuItemPersonalizza = new JMenuItem();
        jMenuItemGuida = new JMenuItem();
        jMenuItemInformazioni = new JMenuItem();

        jRadioButtonMenuItemPratoFiorito = new JRadioButtonMenuItem();
        jRadioButtonMenuItemCampoMinato = new JRadioButtonMenuItem();

        /* IMPOSTAZIONE SCRITTE */
        jMenuGioco.setText("Gioco");
        jMenuHelp.setText("?");
        jMenuOpzioni.setText("Opzioni");
        jMenuLivello.setText("Livello");
        jMenuModalità.setText("Modalità");

        jMenuItemNuovaPartita.setText("Nuova partita");
        jMenuItemStatistiche.setText("Statistiche");
        jMenuItemEsci.setText("Esci");
        jMenuItemFacile.setText("Facile");
        jMenuItemMedio.setText("Medio");
        jMenuItemDifficile.setText("Difficile");
        jMenuItemPersonalizza.setText("Personalizza...");
        jMenuItemGuida.setText("Guida");
        jMenuItemInformazioni.setText("Informazioni");

        jRadioButtonMenuItemPratoFiorito.setText("Prato Fiorito");
        jRadioButtonMenuItemCampoMinato.setText("Campo Minato");
        /* FINE IMPOSTAZIONE SCRITTE */

        /* ASCOLTATORI TASTI RAPIDI */
        jMenuItemNuovaPartita.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItemStatistiche.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItemGuida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        /*FINE ASCOLTATORE TASTI RAPIDI */


        jMenuItemNuovaPartita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Programma.nuovaPartita();
            }
        });
        jMenuItemEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
        jMenuItemFacile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Programma.creaGioco(9, 9, 10);
            }
        });
        jMenuItemMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Programma.creaGioco(16, 16, 40);
            }
        });
        jMenuItemDifficile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Programma.creaGioco(16, 30, 99);
            }
        });
        jMenuItemPersonalizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sceltaPersonalizzato.main();
            }
        });
        jMenuItemInformazioni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Info.main();
            }
        });

        /* AGGIUNTA MENU ALLA BARRA */
        add(jMenuGioco);
        add(jMenuOpzioni);
        add(jMenuHelp);
        /* ELEMENTI AL MENU */
        jMenuGioco.add(jMenuItemNuovaPartita);
        jMenuGioco.add(jMenuItemStatistiche);
        jMenuGioco.add(jMenuItemEsci);
        jMenuLivello.add(jMenuItemFacile);
        jMenuLivello.add(jMenuItemMedio);
        jMenuLivello.add(jMenuItemDifficile);
        jMenuLivello.add(jMenuItemPersonalizza);
        jMenuOpzioni.add(jMenuLivello);
        jMenuHelp.add(jMenuItemGuida);
        jMenuHelp.add(jMenuItemInformazioni);
        jMenuOpzioni.add(jMenuModalità);
        jMenuModalità.add(jRadioButtonMenuItemPratoFiorito);
        jMenuModalità.add(jRadioButtonMenuItemCampoMinato);
        /* FINE AGGIUNTA */


        jMenuModalità.setEnabled(false);
        jMenuItemStatistiche.setEnabled(false);
        jMenuItemGuida.setEnabled(false);

        jRadioButtonMenuItemPratoFiorito.setSelected(true);
        jRadioButtonMenuItemCampoMinato.setSelected(true);
        
        setVisible(true);
    }
}
