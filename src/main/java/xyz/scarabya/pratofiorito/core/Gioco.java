package xyz.scarabya.pratofiorito.core;

import java.util.Timer;
import java.util.TimerTask;

public class Gioco extends Prato
{
    private int daAggiornare[][];
    private int modificati;
    private boolean inGioco;
    private int nBandierine;
    private boolean inizio;
    private boolean vinto;
    private int coperte;
    private int fiori;
    private int secondi;
    private Timer timer;
    private TimerTask task;

    //FUNZIONE PRINCIPALE DI AVVIO\\\\
    public void nuovoGioco(int r, int c, int nFiori)                            //FUNZIONE AVVIATA QUANDO VIENE RICHIESTA UNA NUOVA PARTITA
    {
        nBandierine = 0;                                                        //RESETTA IL NUMERO DI BANDIERINE MESSE
        inGioco = true;                                                         //IMPOSTA LA PARTITA COME IN CORSO
        secondi = 0;                                                            //RESETTA IL TEMPO
        inizio = true;                                                          //IMPOSTA LA PARTITA COME ANCORA NON INIZIATA
        vinto = false;                                                          //IMPOSTA LA PARTITA COME NON VINTA
        fiori=nFiori;                                                           //IMPOSTA IL NUMERO DI FIORI
        coperte=r*c;                                                            //CALCOLA IL NUMERO DI CASELLE COPERTE
        creaPrato(r, c);                                                        //LANCIA LA FUNZIONE CHE RICREA IL CAMPO DI GIOCO
        reset(nFiori);                                                          //LANCIA LA FUNZIONE CHE RIGENERA I FIORI SUL CAMPO DI GIOCO
        Aggiornato();                                                           //LANCIA LA FUNZIONE CHE REIMPOSTA LA MATRICE CASELLE DA AGGIORNARE
        try {timer.cancel(); task.cancel();} catch (Exception E) {}             //CANCELLA IL TIMER (SE PRESENTE)
        timer = new Timer();                                                    //CREA IL TIMER
        task = new Orologio();                                                  //CREA IL PROCESSO DEL TIMER
    }
    ////||||||||||||||||||||||||||\\\\

    ////FUNZIONI DI CONTROLLO DI GIOCO\\\\
    public void premiPulsante(int r, int c)                                     //FUNZIONE AVVIATA QUANDO VIENE PREMUTA UNA CASELLA
    {
        if((inGioco)&&(!getScoperta(r,c))&&(!getBandierina(r,c)))               //SE SI è IN GIOCO, E LA CASELLA NON è STATA NE SCOPERTA E NE BANDIERATA
        {
            while(inizio&&getCasella(r, c)!=0)                                  //FINTANTOCHè SI è ALL'INIZIO DEL GIOCO, E LA CASELLA SCELTA NON è VUOTA
            {
                int salvataggioBandierine = nBandierine;                        //SALVA IL NUMERO DI BANDIERINE GIà MESSE
                boolean bandierine[][]=getStatoBandierine();                    //SALVA BANDIERINE INSERITE
                nuovoGioco(getRighe(), getColonne(), getFiori());               //RICREA GIOCO CON STESSI PARAMETRI
                setBandierine(bandierine);                                      //RICARICA BANDIERINE INSERITE
                nBandierine = salvataggioBandierine;                            //RIPRISTINA IL NUMERO DI BANDIERINE GIà MESSE
            }
            if(inizio)                                                          //SE SI è ALL'INIZIO DEL GIOCO
                timer.schedule(task, 0, 1000);                                  //FA PARTIRE IL TIMER DI GIOCO
            inizio=false;                                                       //IMPOSTA GIOCO PARTITO
            modifica(r, c);                                                     //SEGNALA LA CASELLA COME MODIFICATA
            setScoperta(r, c, true);                                            //SEGNALA LA CASELLA COME SCOPERTA
            coperte--;                                                          //DECREMENTA NUMERO CASELLE ANCORA COPERTE
            if(getCasella(r, c)==-1)                                            //SE LA CASELLA è UN FIORE (FIORE = -1)
                persoGioco();                                                   //FA PARTIRE LA FUNZIONE DI GIOCO PERSO
            if(coperte==getFiori())                                             //SE IL NUMERO DI CASELLE COPERTE è UGUALE AL NUMERO DI FIORI PRESENTI
                vintoGioco();                                                   //FA PARTIRE LA FUNZIONE DI GIOCO VINTO
            if(getCasella(r, c)==0)                                             //SE LA CASELLA è VUOTA (VUOTA = 0)
                scopriIsola(r, c);                                              //FA PARTIRE LA FUNZIONE CHE SCOPRE LE ISOLE
        }
    }    
    private void scopriIsola(int r, int c)                                      //FUNZIONE LANCIATA QUANDO VIENE TROVATA UNA CASELLA VUOTA
    {
        for (int i = r-1; i<r+2; i++)                                           //PER OGNUNA DELLE 3 RIGHE ADIACENTI
            for (int j = c-1; j<c+2; j++)                                       //PER OGNUNA DELLE 3 COLONNE ADIACENTI
                try                                                             //PROVA
                {
                    if (!((i == r) && (j == c)))                                //SE NON è LA CASELLA CENTRALE
                        premiPulsante(i, j);                                    //PREMI LA CASELLA
                }
                catch (Exception E) {}
    }
    public void Bandiera(int r, int c)                                          //CAMBIA LO STATO DELLA BANDIERA
    {
        modifica(r, c);                                                         //MARCA LA CASELLA COME MODIFICATA
        setBandierina(r, c, !getBandierina(r, c));                              //CAMBIA LO STATO DELLA BANDIERINA
        if(getBandierina(r,c))                                                  //SE LA BANDIERA è STATA MESSA
            nBandierine++;                                                      //INCREMENTA IL NUMERO DI BANDIERE MESSE
        else                                                                    //ALTRIMENTI
            nBandierine--;                                                      //DECREMENTA IL NUMERO DI BANDIERE MESSE

    }
    /////|||||||||||||||||||||||||||||\\\\

    ////FUNZIONI PER L'AGGIORNAMENTO\\\\
    public int[][] daAggiornare()                                               //RITORNA LE COORDINATE DELLE CASELLE MODIFICATE
    {
        return daAggiornare;
    }
    public int modificati()                                                     //FUNZIONE CHE RITORNA IL NUMERO DI CASELLE MODIFICATE
    {
        return modificati;
    }
    public void Aggiornato()                                                    //FUNZIONE CHE MARCA IL CAMPO COME AGGIORNATO E PRONTO
    {
        daAggiornare = new int [getRighe()*getColonne()+1][2];                  //RESETTA LA MATRICE DELLE COORDINATE DELLE CELLE MODIFICATE
        modificati = 0;                                                         //IMPOSTA IL NUMERO DI CELLE MODIFICATE A 0
    }
    private void modifica(int r, int c)                                         //FUNZIONE CHE MARCA UNA CELLA COME MODIFICATA
    {
        daAggiornare[modificati][0]=r;                                          //METTE IL NUMERO DI RIGA DELLA CELLA ALLA COLONNA 0 DELLA MATRICE
        daAggiornare[modificati][1]=c;                                          //METTE IL NUMERO DI COLONNA DELLA CELLA ALLA COLONNA 1 DELLA MATRICE
        modificati++;
    }
    ////||||||||||||||||||||||||||||\\\\

    ////FUNZIONI DI FINE PARTITA\\\\
    private void persoGioco()                                                   //FUNZIONE LANCIATA QUANDO VIENE PERSO IL GIOCO
    {
        inGioco = false;                                                        //SETTA IL GIOCO COME TERMINATO
        timer.cancel();                                                         //CANCELLA IL TIMER
        scopriTutto();                                                          //LANCIA LA FUNZIONE CHE SCOPRE TUTTO IL CAMPO DI GIOCO
    }
    private void vintoGioco()                                                   //FUNZIONE LANCIATA QUANDO VIENE VINTO IL GIOCO
    {
        inGioco = false;                                                        //SETTA IL GIOCO COME TERMINATO
        timer.cancel();                                                         //CANCELLA IL TIMER
        vinto = true;                                                           //IMPOSTA LA PARTITA COME VINTA
    }
    private void scopriTutto()                                                  //FUNZIONE CHE SCOPRE TUTTO IL CAMPO DI GIOCO
    {
        for(int i=0; i<getRighe(); i++)                                         //PER OGNI RIGA
            for(int j=0; j<getColonne(); j++)                                   //PER OGNI COLONNA
            {
                if (!getBandierina(i, j))                                       //SE IN QUEL PUNTO NON C'è UNA BANDIERINA
                {
                    modifica(i, j);                                             //IMPOSTA LA CELLA COME MODIFICATA
                    setScoperta(i, j, true);                                    //IMPOSTA LA CELLA COME SCOPERTA
                }
            }
    }
    ////||||||||||||||||||||||||\\\\

    ////FUNZIONI PER IL TIMER\\\\
    public void incrementaSecondi()                                             //FUNZIONE LANCIATA OGNI 1 SECONDO DOPO L'INIZIO DEL GIOCO
    {
        if(!inizio&&inGioco)                                                    //SE IL GIOCO è PARTITO ORA, E SE SI è IN GIOCO
            secondi++;                                                          //INCREMENTA I SECONDI DI 1
    }
    public int Tempo()                                                          //FUNZIONE CHE RITORNA IL TEMPO TRASCORSO IN SECONDI
    {
        return secondi;
    }
    ////|||||||||||||||||||||\\\\

    ////FUNZIONI DI RITORNO VARIE\\\\
    public boolean getInGioco()                                                 //RITORNA LO STATO DEL GIOCO (IN CORSO / TERMINATO)
    {
        return inGioco;
    }
    public boolean vittoria()                                                   //RITORNA L'ESITO DELLA PARTITA (VINTA, NON VINTA)
    {
        return vinto;
    }
    public int fioriRimasti()                                                   //RITORNA IL NUMERO DI FIORI RIMASTI DA INDIVIDUARE
    {
        return fiori-nBandierine;
    }
    public boolean Bandierato(int r, int c)                                     //RITORNA LO STATO DELLA BANDIERA IN UNA CELLA
    {
        return getBandierina(r,c);
    }
    ////|||||||||||||||||||||||||\\\\
}
