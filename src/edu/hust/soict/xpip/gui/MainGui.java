/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hust.soict.xpip.gui;

import edu.hust.soict.xpip.components.CharactersArrayChunker;
import edu.hust.soict.xpip.components.CharactersLoader;
import edu.hust.soict.xpip.constants.FileConstants;
import edu.hust.soict.xpip.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author thinhntb
 */
public class MainGui extends javax.swing.JFrame {

    /**
     * Tệp tin cần phân tích
     */
    private File inputFile;

    private char[] rawData;

    /**
     * Creates new form MainGui
     */
    public MainGui() {
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

        jLabel1 = new javax.swing.JLabel();
        textFiledInputFile = new javax.swing.JTextField();
        buttonSelectFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        buttonParse = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        comboNumOfThreads = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        labelExeTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Chọn tệp tin:");

        textFiledInputFile.setEditable(false);

        buttonSelectFile.setText("...");
        buttonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFileActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTree1);

        buttonParse.setText("Phân tích");
        buttonParse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonParseActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane1);

        jLabel2.setText("Số luồng:");

        comboNumOfThreads.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabel3.setText("Thời gian thực hiện:");

        labelExeTime.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboNumOfThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelExeTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                        .addComponent(buttonParse))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(textFiledInputFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(textFiledInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSelectFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(comboNumOfThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(labelExeTime)
                    .addComponent(buttonParse))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonSelectFile, jLabel1, textFiledInputFile});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFileActionPerformed
        JFileChooser fChooser = new JFileChooser(new File(System.getProperty("user.home")));
        fChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                String ext = FileUtils.getExtension(f);
                if (f.isDirectory() || FileConstants.XML_EXT.equals(ext)) {
                    return true;
                }

                return false;
            }

            @Override
            public String getDescription() {
                return "*.xml";
            }
        });

        int state = fChooser.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == state) {
            inputFile = fChooser.getSelectedFile();
            textFiledInputFile.setText(inputFile.getAbsolutePath());
        }
    }//GEN-LAST:event_buttonSelectFileActionPerformed

    private void buttonParseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonParseActionPerformed
        if (inputFile == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn tệp tin XML "
                    + "cần phân tích!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /**
         * Load các ký tự từ tệp tin lên mảng rawData
         */
        new SwingWorker<Void, Void>() {

            Exception e = null;

            @Override
            protected Void doInBackground() throws Exception {
                CharactersLoader loader = new CharactersLoader(inputFile);
                try {
                    rawData = loader.load();
                } catch (IOException | NullPointerException ex) {
                    e = ex;
                }
                return null;
            }

            @Override
            protected void done() {
                super.done(); //To change body of generated methods, choose Tools | Templates.
                if (e != null) {
                    JOptionPane.showMessageDialog(MainGui.this, e.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                chunkingData();
            }

        }.execute();
    }//GEN-LAST:event_buttonParseActionPerformed

    /**
     * Phân mảnh dữ liệu.
     */
    public void chunkingData(){
        new SwingWorker<Void, Void>(){

            @Override
            protected Void doInBackground() throws Exception {
                List<Integer> pos = new CharactersArrayChunker(rawData).chunk();
                return null;
            }
            
        }.execute();
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
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonParse;
    private javax.swing.JButton buttonSelectFile;
    private javax.swing.JComboBox comboNumOfThreads;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel labelExeTime;
    private javax.swing.JTextField textFiledInputFile;
    // End of variables declaration//GEN-END:variables
}
