package xyz.scarabya.pratofiorito.core;

public class Prato
{
    private boolean Scoperte[][];
    private boolean Bandierine[][];
    private int AreaGioco[][];
    private int R, C;
    private int fiori;

    ////METODO COSTRUTTORE\\\\
    public Prato()                                                              //COSTRUTTORE SENZA PARAMENTRI
    {

    }
    ////||||||||||||||||||\\\\
    
    ////FUNZIONI PER LA CREAZIONE DEL PRATO\\\\
    public void creaPrato(int righe, int colonne)                               //CREA IL PRATO
    {
        R = righe;                                                              //IMPOSTA IL NUMERO DI RIGHE
        C = colonne;                                                            //IMPOSTA IL NUMERO DI COLONNE
        AreaGioco = new int[R][C];                                              //INIZIALIZZA LE CASELLE DI GIOCO
        Scoperte = new boolean[R][C];                                           //INIZIALIZZA LO STATO DELLE CASELLE
        Bandierine = new boolean[R][C];                                         //INIZIALIZZA LO STATO DELLE BANDIERE
    }
    public void reset(int nFiori)                                               //RIGENERA I FIORI SUL PRATO
    {
        fiori=nFiori;                                                           //IMPOSTA IL NUMERO DI FIORI
        for(int i=0; i<R; i++)                                                  //PER OGNI RIGA
            for(int j=0; j<C; j++)                                              //PER OGNI COLONNA
            {
                AreaGioco[i][j]=0;                                              //SVUOTA LA CASELLA
                Scoperte[i][j]=false;                                           //SETTA LA CASELLA COME COPERTA
                Bandierine[i][j]=false;                                         //SETTA LA BANDIERINA COME NON PRESENTE
            }
        while(nFiori>0)                                                         //FINTANTOCHE I FIORI NON SONO STATI MESSI TUTTI
        {
            int riga = (int)(Math.random()*1000)%R;                             //SCEGLIE UNA RIGA A CASO
            int colonna = (int)(Math.random()*1000)%C;                          //SCEGLIE UNA COLONNA A CASO
            if(AreaGioco[riga][colonna]!=-1)                                    //SE IN QUEL PUNTO NON C'è GIà UN FIORE
            {
                AreaGioco[riga][colonna]=-1;                                    //METTE IL FIORE IN QUEL PUNTO
                nFiori--;                                                       //DECREMENTA IL NUMERO DI FIORI DA METTERE
            }
        }
        contaFiori();                                                           //LANCIA LA FUNZIONE CHE CONTA I FIORI ADIACENTI
    }
    private void contaFiori()                                                   //CONTA I FIORI ADIACENTI AD OGNI CASELLA
    {
        for(int i=0; i<R; i++)                                                  //PER OGNI RIGA
            for(int j=0; j<C; j++)                                              //PER OGNI COLONNA
                if(AreaGioco[i][j]==-1)                                         //SE IN QUEL PUNTO C'è UN FIORE
                    for (int r = i-1; r<i+2; r++)                               //PER OGNUNA DELLE TRE RIGHE ATTORNO AL PUNTO SCELTO
                        for (int c = j-1; c<j+2; c++)                           //PER OGNUNA DELLE TRE COLONNE ATTORNO AL PUNTO SCELTO
                            try                                                 //PROVA
                            {
                                if ((AreaGioco[r][c]>-1)&&(!((r==i)&&(c==j))))  //SE IN QUEL PUNTO NON C'è UN FIORE
                                    AreaGioco[r][c]++;                          //INCREMENTA IL NUMERO IN QUEL PUNTO
                            }
                            catch (Exception E) {}
    }
    ////|||||||||||||||||||||||||||||||||||\\\\
    
    ////FUNZIONI DI RITORNO ATTRIBUTI PRATO\\\\
    public int getRighe()                                                       //RITORNA IL NUMERO DI RIGHE
    {
        return R;
    }    
    public int getColonne()                                                     //RITORNA IL NUMERO DI COLONNE
    {
        return C;
    }
    public int getFiori()                                                       //RITORNA IL NUMERO DI FIORI
    {
        return fiori;
    }
    ////|||||||||||||||||||||||||||||||||||\\\\

    ////FUNZIONI MODIFICA/RITORNO STATI PRATO\\\\
    public int getCasella(int r, int c)                                         //RITORNA IL VALORE DELLA CASELLA
    {
        return AreaGioco[r][c];
    }
    public boolean getScoperta(int r, int c)                                    //RITORNA SE LA CASELLA E' SCOPERTA O NO
    {
        return Scoperte[r][c];
    }
    public void setScoperta(int riga, int colonna, boolean stato)               //IMPOSTA SE LA CASELLA è SCOPERTA O NO
    {
        Scoperte[riga][colonna]=stato;
    }
    public boolean getBandierina(int riga, int colonna)                         //RITORNA LO STATO DELLA BANDIERINA
    {
        return Bandierine[riga][colonna];
    }
    public void setBandierina(int riga, int colonna, boolean attivata)          //IMPOSTA LO STATO DELLA BANDIERINA
    {
        Bandierine[riga][colonna]=attivata;
    }
    ////|||||||||||||||||||||||||||||||||||||\\\\

    ////FUNZIONI DI RITORNO STATI COMPLETI PRATO\\\\
    public boolean[][] getStatoBandierine()                                     //RITORNA LA MATRICE DELLE BANDIERINE
    {
        return Bandierine;
    }
    public boolean[][] getStatoScoperte()                                       //RITORNA LA MATRICE DELLE CASELLE SCOPERTE
    {
        return Scoperte;
    }
    public int[][] getStatoCaselle()                                            //RITORNA LA MATRICE DEI VALORI DELLE CASELLE
    {
        return AreaGioco;
    }
    ////||||||||||||||||||||||||||||||||||||||||\\\\

    ////FUNZIONI DI SETTAGGIO STATI COMPLETI PRATO\\\\
    public void setBandierine(boolean bandierine[][])                           //IMPOSTA LA MATRICE DELLE BANDIERINE
    {
        Bandierine = bandierine;
    }
    ////||||||||||||||||||||||||||||||||||||||||||\\\\
}