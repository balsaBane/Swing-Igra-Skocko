 
package skocko;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ProgressBar;
import javax.swing.JOptionPane;

public class GlavniFrame extends javax.swing.JFrame implements ActionListener{
    
    int [] niz =  {0,0,0,0}; // niz u koji se privremeno. smesta korisnikova kombinacija. Inicijalno set-ovana na 0.
     
    // DEFINISANJE STAVKI GRAFICKOG INTERFEJSA  
   
    // Definisanje ikonica 
    
    ImageIcon skocko      = new ImageIcon("src/skocko/ikonice/skocko.png"); // skocko bez okvira
    ImageIcon skocko2     = new ImageIcon("src/skocko/ikonice/skocko2.png");// skocko sa okvirom itd...
    ImageIcon karo        = new ImageIcon("src/skocko/ikonice/karo.png");
    ImageIcon karo2       = new ImageIcon("src/skocko/ikonice/karo2.png");
    ImageIcon pik         = new ImageIcon("src/skocko/ikonice/pik.png");
    ImageIcon pik2        = new ImageIcon("src/skocko/ikonice/pik2.png");
    ImageIcon zvezda      = new ImageIcon("src/skocko/ikonice/zvezda.png");
    ImageIcon zvezda2     = new ImageIcon("src/skocko/ikonice/zvezda2.png");
    ImageIcon tref        = new ImageIcon("src/skocko/ikonice/tref.png");
    ImageIcon tref2       = new ImageIcon("src/skocko/ikonice/tref2.png");
    ImageIcon srce        = new ImageIcon("src/skocko/ikonice/srce.png"); 
    ImageIcon srce2       = new ImageIcon("src/skocko/ikonice/srce2.png"); 
    ImageIcon upitnik     = new ImageIcon("src/skocko/ikonice/upitnik.png");
    ImageIcon crveniKrug  = new ImageIcon("src/skocko/ikonice/crveniKrug.png");
    ImageIcon zutiKrug    = new ImageIcon("src/skocko/ikonice/zutiKrug.png");
    ImageIcon crniKrug    = new ImageIcon("src/skocko/ikonice/crniKrug.png"); 
    
    List <JButton>   listaUpitnika     = new ArrayList();
    List <ImageIcon> listaIkonica      = new ArrayList();
    List <ImageIcon> listaIkonica2     = new ArrayList();
    List <JButton>   listaSimbola      = new ArrayList();
    
    List <List<JButton>> listeLevo     = new ArrayList(); //lista dugmadi leve strane
    List <List<JButton>> listaPogodaka = new ArrayList(); //lista dugmadi desne strane
    
    // Meni i stavke menija
    
    MenuBar meni = new MenuBar();
    
    Menu igra = new Menu(); 
    MenuItem novaIgra = new MenuItem();
    MenuItem izlaz = new MenuItem();
    
    
    Menu pomoc = new Menu();
    MenuItem pomocItem = new MenuItem();
    MenuItem oaplikaciji = new MenuItem();
    
    //**************************************************************************
    
    Skocko igra1 = new Skocko(); 
    
    pobeda pobedaProzor = new pobeda();
     
    public GlavniFrame() {
        initComponents();
        this.setTitle("Skočko");
        this.setMenuBar(meni); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(skocko.getImage());
        this.setSize(new Dimension(620,510));
        this.setLocationRelativeTo(null); 
        
        dodajMenije();
        generisiListe();      
        dodajDugmad();          
          
        this.setVisible(true);  
    } 
    
     
     @Override
    public void actionPerformed(ActionEvent ae){
        int a = 0 ;
        if (ae.getActionCommand() != "" ){
            a = Integer.parseInt(ae.getActionCommand()); 
        } 
        
        if (a < 4 && ae.getSource() == listeLevo.get(igra1.korak - 1).get(a)){
            niz[a] = 0;
            listeLevo.get(igra1.korak - 1).get(a).setIcon(null);
            listaUpitnika.get(igra1.korak-1).setVisible(false);
        }
         
        if (ae.getSource() == listaSimbola.get(a )){
            if (prviSlobodanSlot(niz) == 6){
                return;
            } 
            postaviSimbol(igra1.korak - 1 , prviSlobodanSlot(niz)-1, a + 1);
            niz[prviSlobodanSlot(niz)-1] = a+1;
            if (prviSlobodanSlot(niz) == 6){
                prikaziUpitnik(igra1.korak);
            } 
        }
        
        if (ae.getSource() == this.pobedaProzor.dugme1){
            pobedaProzor.setVisible(false);
            this.dispose();
            GlavniFrame opa = new GlavniFrame();
        }
        if (ae.getSource() == this.pobedaProzor.dugme2){
            this.dispose();
            pobedaProzor.dispose();
        }
        
        if (ae.getSource() == this.novaIgra){
            this.dispose();
            GlavniFrame opa = new GlavniFrame();
        }
        if (ae.getSource() == this.izlaz){
            System.exit(1);
        } 
        if (ae.getSource() == this.pomocItem){
            pomocFrame frejm = new pomocFrame(); 
        }
        if (ae.getSource() == this.oaplikaciji){
            JOptionPane.showMessageDialog(this,
            "Program je napravljen u nekomercijalne svrhe.\n A.u.t.o.r : Balša Bjelogrlić\n"
           + "Email: bjelogrlic.balsa@gmail.com" , "O aplikaciji",1);
        }  
        /*
        U nastavku su definisane akcije posledične priticima na neku od dugmad 
        upitnika. Koji ce upitnik da bude aktiviran(vidljiv) zavisi od trenutnog
        koraka igre. Pa tako, ako je igra u prvoj fazi , nakon sto su popunjena
        prva cetiri slota, pored se pojavljuje prvi upitnik. Klikom na upitnik
        proverava se tacnost unesene kombinacije, igra prelazi na drugi korak 
        (korak++),pa se nakon unosenja simbola u drugi red pojavljuje drugi upitnik itd.
        
        */
        
            if (ae.getSource() == this.listaUpitnika.get(igra1.korak - 1)) {        // Iz liste upitnika se poziva onaj upitnik koji odgovara koraku igre.
                                                                                    // (korak -1) jer je lista indeksirana od nule.                                                                       
                if (igra1.daLiJeKombinacija(niz) == true) { //  
                    prikaziCrveneIzute(igra1.korak - 1, igra1.brojPogodaka, igra1.nisuNaMestu);
                    postaviSedmiRed();                                              // prikazuje se tacna kombinacija u sedmom redu leve strane
                    pobedaProzor.setVisible(true); 
                } else { 
                    if (igra1.korak == 6) {
                        prikaziCrveneIzute(igra1.korak - 1, igra1.brojPogodaka, igra1.nisuNaMestu);
                        postaviSedmiRed();  
                        
                        int reply = JOptionPane.showConfirmDialog(this, "Niste uspeli da pronadjete kombinaciju. Nova igra?", "Kraj igre", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            this.dispose();
                            GlavniFrame noviFrejm = new GlavniFrame();
                        } else {
                            JOptionPane.showMessageDialog(null, "Pozz! ;) ");
                            System.exit(0);
                        }

                    }
                    prikaziInformacije(); 
                    prikaziCrveneIzute(igra1.korak - 1, igra1.brojPogodaka, igra1.nisuNaMestu);
                    this.listaUpitnika.get(igra1.korak - 1).setVisible(false);
                    igra1.korak++;
                    resetNiz(niz); 
                    igra1.resetSkockoParametara();
                }
            }
        } 
    public int  prviSlobodanSlot(int [] niz){ // u korisnickom nizu, 0 oznacava slobodan slot
        int prviSlobodan = 5 ;  
        for (int i = 0; i < 4; i++){
            if (niz[i] == 0){
                prviSlobodan = i;
                break;
            }
        } 
        return prviSlobodan + 1; // ukoliko metod vrati 6 , nijedan slot nije slobodan
    }
    public void postaviSedmiRed(){  // za prikazivanje tacne kombinacija u sedmom redu leve strane
        for (int i = 0; i < 4 ; i++){
            listeLevo.get(6).get(i).setIcon(listaIkonica.get((igra1.glavniNiz[i]) - 1));
        }
} 
    public void prikaziCrveneIzute(int korak, int crvene , int zute){  // prikazuje crvene i zute krugove , tj pogodjene simbole
        int ukupno = crvene + zute;
        for (int i = 0; i < crvene; i++){
            listaPogodaka.get(korak).get(i).setIcon(crveniKrug); 
        }
        for (int i = crvene; i < ukupno; i++){
            listaPogodaka.get(korak).get(i).setIcon(zutiKrug);
        }
    } 
    public void resetNiz(int niz[]){ // resetovanje korisnikove kombinacije na 0 0 0 0 (u prelasku na sledeci korak)
        for (int i = 0; i < niz.length; i++){
            niz[i] = 0;
        }
    }
    public void prikaziInformacije (){
        System.out.println("Racunarska kombinacija: " + igra1.glavniNiz[0]  + " " + igra1.glavniNiz [1] 
                                     + " "+ igra1.glavniNiz[2]  + " " + igra1.glavniNiz[3] ); 
        System.out.println("Korisnikova kombinacija: " + niz[0] + " " + niz[1] + " " + niz[2] + " " + niz[3]);
                    System.out.println("broj pogodaka " + igra1.brojPogodaka);
                    System.out.println("nisu na mestu " + igra1.nisuNaMestu);
                    
    }  
    public void postaviSimbol(int korak, int prviSlobodanSlot, int redniBrojSimbola){ // metod koji se poziva u Action Listeneru odgovarajuceg dugmeta
         listeLevo.get(korak).get(prviSlobodanSlot).setIcon(listaIkonica.get(redniBrojSimbola-1));
    }
    
    public void generisiListe(){
           
         listaIkonica.add(skocko); listaIkonica.add(karo); listaIkonica.add(pik);
         listaIkonica.add(zvezda); listaIkonica.add(tref); listaIkonica.add(srce);
         //------------
         
         listaIkonica2.add(skocko2); listaIkonica2.add(karo2); listaIkonica2.add(pik2);
         listaIkonica2.add(zvezda2); listaIkonica2.add(tref2); listaIkonica2.add(srce2);
         //------------
           
    } 
    
    public void prikaziUpitnik (int n){
        n = n - 1;
        for (int i = 0; i<listaUpitnika.size();i++){
            if(i == n){
                listaUpitnika.get(i).setVisible(true);
                repaint();
            }else{
                listaUpitnika.get(i).setVisible(false);
            }
        }
    } 
    
    public void dodajMenije(){
        // ----------
        meni.add(igra); 
        igra.setLabel("Igra");
        
        igra.add(novaIgra);
        novaIgra.setLabel("Nova igra");
        novaIgra.addActionListener(this);
        novaIgra.setActionCommand("0");
         
        igra.add(izlaz);
        izlaz.setLabel("Izlaz");
        izlaz.addActionListener(this);
        izlaz.setActionCommand("1"); 
        
        //-----------
         
        meni.add(pomoc);
        pomoc.setLabel("Pomoć");
        
        pomoc.add(pomocItem);
        pomocItem.setLabel("Pomoć");
        pomocItem.addActionListener(this);
        pomocItem.setActionCommand("2");
         
        pomoc.add(oaplikaciji);
        oaplikaciji.setLabel("O aplikaciji");
        oaplikaciji.addActionListener(this);
        oaplikaciji.setActionCommand("3");
        
        pobedaProzor.dugme1.addActionListener(this);
        pobedaProzor.dugme2.addActionListener(this);
        izlaz.setActionCommand("");
    }
    
    public void dodajDugmad() {

 /* Dodavanje prvih sedam redova dugmadi (levo) ********************************
    -> [][][][] <-  O O O O 
    -> [][][][] <-  O O O O 
    -> [][][][] <-  O O O O 
    -> [][][][] <-  O O O O 
    -> [][][][] <-  O O O O 
    -> [][][][] <-  O O O O  
        
    -> [][][][] <-  O O O O  
*/  
        for (int r = 0; r < 7; r++) {
            listeLevo.add(new ArrayList());
            for (int i = 0; i < 4; i++) { 
                if (r < 6) {
                    listeLevo.get(r).add(new JButton());
                    listeLevo.get(r).get(i).addActionListener(this);
                    listeLevo.get(r).get(i).setBounds(20 + (i * 50), 20 + (r * 50), 40, 40);
                    listeLevo.get(r).get(i).setBorderPainted(false);
                    listeLevo.get(r).get(i).setActionCommand(Integer.toString(i));
                    label.add(listeLevo.get(r).get(i));
                }
                if (r == 6) {
                    listeLevo.get(r).add(new JButton());
                    listeLevo.get(r).get(i).setBounds(20 + (i * 50), 370, 40, 40);
                    listeLevo.get(r).get(i).setBorderPainted(false);
                    label.add(listeLevo.get(r).get(i)); 
                }
            }
        }
        
/* Dodavanje pogodak - dumadi s desne strane ***********************************
    [][][][]   -> O O O O <-
    [][][][]   -> O O O O <-
    [][][][]   -> O O O O <- 
    [][][][]   -> O O O O <-
    [][][][]   -> O O O O <-
    [][][][]   -> O O O O <-
*/
        for (int i = 0; i < 6 ; i++){
            listaPogodaka.add(new ArrayList());
            for (int r = 0; r < 4 ; r++){
                listaPogodaka.get(i).add(new JButton());
                listaPogodaka.get(i).get(r).setBounds(390 + (r*50), 20 + (i*50), 40, 40);
                listaPogodaka.get(i).get(r).setOpaque(false);
                listaPogodaka.get(i).get(r).setContentAreaFilled(false);
                listaPogodaka.get(i).get(r).setBorderPainted(false);
                listaPogodaka.get(i).get(r).setIcon(crniKrug);
                label.add(listaPogodaka.get(i).get(r));
            }
        } 
        
// Dodavanje simbol-dugmadi ****************************************************
         
        for (int i = 0 ; i < 6; i++){
            listaSimbola.add(new JButton());
            listaSimbola.get(i).setBounds(290 + (i*50),390, 40, 40);
            listaSimbola.get(i).setOpaque(false);
            listaSimbola.get(i).setContentAreaFilled(false);
            listaSimbola.get(i).setBorderPainted(false); 
            listaSimbola.get(i).setIcon(listaIkonica2.get(i));
            listaSimbola.get(i).setActionCommand(Integer.toString(i)); 
            listaSimbola.get(i).addActionListener(this);
            listaSimbola.get(i).setSelectedIcon(listaIkonica.get(i));
            label.add(listaSimbola.get(i));
        }  
// Dodavanje upitnika  *********************************************************

        for (int i = 0; i < 6; i++) {
            int a = 0;
                if (i == 3) {
                  a = 50;
                } else if (i == 4) {
                 a = 40;
                 } else if (i == 5) {
                 a = 30;
                 }
            listaUpitnika.add(new JButton());
            listaUpitnika.get(i).setBounds(240 + a, 20 + (i * 50), 40, 40);
            listaUpitnika.get(i).setOpaque(false);
            listaUpitnika.get(i).setContentAreaFilled(false);
            listaUpitnika.get(i).setBorderPainted(false);
            listaUpitnika.get(i).setIcon(upitnik);
            listaUpitnika.get(i).setVisible(false);
            listaUpitnika.get(i).addActionListener(this);
            label.add(listaUpitnika.get(i));
    } 
//******************************************************************************  
        repaint();
    } 
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skocko/ikonice/background2.jpg"))); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}