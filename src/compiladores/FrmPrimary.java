/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author diego
 */
public class FrmPrimary extends javax.swing.JFrame {
 String NombreArchivo=null;
    /**
     * Creates new form NewJFrame
     */
    public FrmPrimary() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        javax.swing.JButton btnBrowser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_output = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txta_input = new javax.swing.JTextArea();
        txtPath = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton2.setText("Analizar");
        jButton2.setToolTipText("");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnBrowser.setText("Browser");
        btnBrowser.setName(""); // NOI18N
        btnBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowserActionPerformed(evt);
            }
        });

        txta_output.setEditable(false);
        txta_output.setColumns(20);
        txta_output.setRows(5);
        jScrollPane1.setViewportView(txta_output);

        txta_input.setEditable(false);
        txta_input.setColumns(20);
        txta_input.setRows(5);
        jScrollPane2.setViewportView(txta_input);

        txtPath.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBrowser)
                        .addGap(18, 18, 18)
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(jButton2)
                        .addGap(135, 135, 135)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowser)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jButton2)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowserActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", 
                "txt", "text","frag");
        fc.setFileFilter(filter);
        if( fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ){
           txtPath.setText("");
           txtPath.setText(fc.getSelectedFile().getAbsolutePath());
           txta_input.setText("");
           jButton2.setEnabled(true);
           leer();            
        }
    }//GEN-LAST:event_btnBrowserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // Tokenizer.Token token = new Tokenizer.Token();
        txta_output.setText("");
        ArrayList<Tokenizer.Token> tokens = Lexer.lex(txta_input.getText());
        for (Tokenizer.Token token : tokens){
             txta_output.append(token.toString()+ "\n");
        }
           
        
        escribir();
    }//GEN-LAST:event_jButton2ActionPerformed
private void leer() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
                        archivo = new File (txtPath.getText());
			//archivo = new File(txtPath.getText());
                        NombreArchivo=archivo.getName();
                        NombreArchivo=NombreArchivo.replace(".txt", "");
                        fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null)
				txta_input.append(linea + '\n');           
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
    }
private void escribir() {
        File fichero=new File(NombreArchivo+".out");//creando fichero txt en raiz
        PrintWriter writer;
        try{
            writer=new PrintWriter(fichero);
            writer.print(txta_output.getText());//ingresado ecuacion
            writer.close();
            JOptionPane.showMessageDialog(null, 
                    "InfoBox: Se ha analizado con éxito el archivo y se ha "
                            + "creado un archivo de salida en la carpeta raíz llamado "
                            +NombreArchivo, "¡ATENCIÓN!",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(FileNotFoundException ex){
           JOptionPane.showMessageDialog(null, 
                    "Se produjo un error al intentar escribir el archivo de salida", "¡ATENCIÓN!",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrimary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrimary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrimary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrimary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrimary().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextArea txta_input;
    private javax.swing.JTextArea txta_output;
    // End of variables declaration//GEN-END:variables
}
