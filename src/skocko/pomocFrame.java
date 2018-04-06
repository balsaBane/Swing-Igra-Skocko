 
package skocko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
 
public class pomocFrame extends javax.swing.JFrame implements ActionListener {
    JButton ok = new JButton("Ok");
    
    public pomocFrame() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ok.setBounds(220,150, 100, 40); 
        ok.addActionListener(this);
        this.textArea.add(ok); 
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pomoć");
        setUndecorated(true);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Bookman Old Style", 0, 13)); // NOI18N
        textArea.setRows(5);
        textArea.setText("Skočko je igra sa znakovima. Računar nasumično izabere kominaciju od četiri \nznaka koju treba pogoditi. Znakovi se biraju iz skupa od 6 znakova, a određeni\nznak se može pojavljivati i više puta.Imate 6 pokušaja da pogodite kombinaciju,\na nakon svakog pokušaja računar prikazuje koliko znakova ste pogodili \ni koliko od tih znakova je na pravoj poziciji.");
        textArea.setOpaque(false);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.ok){
            this.dispose();
        }
    }
}
