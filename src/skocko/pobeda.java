 
package skocko;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
    Frejm koji "Iskače" kada korisnik pronadje pravu kombinaciju. 
*/
 
public class pobeda extends JFrame implements ActionListener {
    ImageIcon skocko   = new ImageIcon("src/skocko/ikonice/skocko.png");
    JButton   dugme1   = new JButton();
    JButton   dugme2   = new JButton();
    public pobeda() {
        initComponents();
        this.setTitle("Pobeda!");
        this.setIconImage(skocko.getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setBackground(new Color(0,0,0,0));
        
         
        this.dugme1.setBounds(75, 124, 117, 30);
        this.dugme2.setBounds(200, 124, 74, 30);
        pobedaLabel.add(dugme1); 
        pobedaLabel.add(dugme2);
        
        dugme1.setOpaque(false);
        dugme1.setContentAreaFilled(false);
        dugme1.setBorderPainted(false);
        
        dugme2.setOpaque(false);
        dugme2.setContentAreaFilled(false);
        dugme2.setBorderPainted(false);
        
        dugme1.addActionListener( this);
        dugme2.addActionListener(this); 
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pobedaLabel = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setFocusable(false);
        setUndecorated(true);

        pobedaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skocko/ikonice/pobeda.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pobedaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pobedaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel pobedaLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
 
}
