 
package skocko;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
 
/*
    Prvi frejm koji se pojavljuje. Nakon pritiska na dugme "zapocni igru" 
poziva se glavni frejm igre.

*/

public class Dobrodoslica extends javax.swing.JFrame implements ActionListener{
    ImageIcon blured     = new ImageIcon("src/skocko/ikonice/blur.png");
    JButton   pocetakDugme    = new JButton  ();
    ImageIcon skocko     = new ImageIcon("src/skocko/ikonice/skocko.png");
    GlavniFrame novi = new GlavniFrame(); 
    
    public Dobrodoslica() {
        novi.setVisible(false);
        initComponents();
        this.setTitle("Skočko"); 
        this.setSize(new Dimension(620,490));
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(skocko.getImage());
        pocetakDugme.setBounds(220,237, 170, 40);
        pocetakDugme.setOpaque(false);
        pocetakDugme.setContentAreaFilled(false);
        pocetakDugme.setBorderPainted(false);
        pocetakDugme.addActionListener(this);
        label2.add(pocetakDugme);
        
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(717, 490));

        label2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/skocko/ikonice/blured.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.pocetakDugme){
            this.dispose();
            novi.setVisible(true);
        }
    }
}
