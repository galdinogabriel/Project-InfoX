/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class TelaOS extends javax.swing.JFrame {

    /**
     * Creates new form TelaOS
     */
    // framework do pacote java.SQL
    // incializando  a variavel conexao
    Connection conexao = null;
    // variaveis especiais  de apoio a conexao com  o DB
    PreparedStatement pst = null;
    // objeto  matriz  que recebe  o resultado  do comando SQL
    ResultSet rs = null;

    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    // metódo para consultar usuarios
    private void consultar() {
        String sql = "SELECT * FROM tbos WHERE os = ?";

        try {
            // prepara a execução do comando sql
            pst = conexao.prepareStatement(sql);
            // associando o campo do formulario com select
            pst.setString(1, txtOsOs.getText());
            // executando a query - montando o array
            rs = pst.executeQuery();

            // testa se encontro
            if (rs.next()) {
                // caso positivo
                // seta o campo do formulário com o
                // segundo o campo do array (usuario)
                txtOsDate.setText(rs.getString(2));
                txtOsEquipamento.setText(rs.getString(3));
                txtOsDefeito.setText(rs.getString(4));
                txtOsServico.setText(rs.getString(5));
                txtOsTecnico.setText(rs.getString(6));
                txtOsValor.setText(rs.getString(7));
                txtOsIdCli.setText(rs.getString(8));
            } else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
                txtOsDate.setText(null);
                txtOsDefeito.setText(null);
                txtOsServico.setText(null);
                txtOsTecnico.setText(null);
                txtOsValor.setText(null);
                txtOsIdCli.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void adicionar() {
        String sql = "INSERT INTO tbos(equipamento,defeito,servico,tecnico,valor,idcli) VALUES(?,?,?,?,?,?)";
        try {
            // prepara execução do comando SQL
            pst = conexao.prepareStatement(sql);

            // recebe os campos dos formulários, na ordem
            pst.setString(1, txtOsEquipamento.getText());
            pst.setString(2, txtOsDefeito.getText());
            pst.setString(3, txtOsServico.getText());
            pst.setString(4, txtOsTecnico.getText());
            pst.setString(5, txtOsValor.getText());
            pst.setString(6, txtOsIdCli.getText());

            // validação dos campos obrigatório
            if ((txtOsEquipamento.getText().isEmpty())
                    || (txtOsDefeito.getText().isEmpty())
                    || (txtOsValor.getText().isEmpty())
                    || (txtOsIdCli.getText().isEmpty())
                    || txtOsServico.getText().isEmpty()
                    || txtOsTecnico.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha todos os campos obrigatórios");
            } else {
                // executar comando SQL
                int adicionado = pst.executeUpdate();

                // testa se adicionou
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Os adicionada com sucesso");

                    //limpandSo os campos do formulário
                    txtOsEquipamento.setText(null);
                    txtOsDate.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCli.setText(null);

                }

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Não foi possível adicionar OS, verifiqu se o ID cliente existe");
        }
    }
    // metódo para alterar usuarios

    private void alterar() {
        String sql = "UPDATE tbos SET equipamento=?, defeito=?, servico=?, tecnico=?, valor=?, idcli=? WHERE os=?";

        try {
            //prepara a execução do comando sql
            pst = conexao.prepareStatement(sql);

            //recebe os campos do formulário, na ordem e envia para o update
            pst.setString(1, txtOsEquipamento.getText());
            pst.setString(2, txtOsDefeito.getText());
            pst.setString(3, txtOsServico.getText());
            pst.setString(4, txtOsTecnico.getText());
            pst.setString(5, txtOsValor.getText());
            pst.setString(6, txtOsIdCli.getText());
            pst.setString(7, txtOsOs.getText());

            //validação dos campos obrigatórios
            if ((txtOsEquipamento.getText().isEmpty())
                    || (txtOsDefeito.getText().isEmpty())
                    || (txtOsValor.getText().isEmpty())
                    || (txtOsIdCli.getText().isEmpty())
                    || (txtOsOs.getText().isEmpty())
                    || txtOsServico.getText().isEmpty()
                    || txtOsTecnico.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha todos os campos obrigatórios");
            } else {
                // executar comando SQL
                int adicionado = pst.executeUpdate();

                // testa se adicionou
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterado com sucesso");

                    //limpandSo os campos do formulário
                    txtOsDate.setText(null);
                    txtOsEquipamento.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCli.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivél alterar o usuário! Verifique se ID cliente e OS existem");
        }
    }
    // metódo para remover usuários

    private void remover() {
        // pede confirmação para remover usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover ests OS ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM tbos WHERE os=?";

            try {
                pst = conexao.prepareStatement(sql);

                // recebe o campo id do formulario, e envia para o delete
                pst.setString(1, txtOsOs.getText());

                // executa o comando SQL
                int apagado = pst.executeUpdate();

                // testa se removeu
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS removida com sucesso");

                    //limpandSo os campos do formulário
                    txtOsDate.setText(null);
                    txtOsEquipamento.setText(null);
                    txtOsDefeito.setText(null);
                    txtOsServico.setText(null);
                    txtOsTecnico.setText(null);
                    txtOsValor.setText(null);
                    txtOsIdCli.setText(null);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

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
        txtOsOs = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtOsDate = new javax.swing.JTextField();
        txtOsEquipamento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOsDefeito = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtOsServico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtOsValor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtOsTecnico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtOsIdCli = new javax.swing.JTextField();
        btnCreateCli = new javax.swing.JButton();
        btnReadCli = new javax.swing.JButton();
        btnUpdateCli = new javax.swing.JButton();
        btnDeleteCli = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OS");
        setResizable(false);

        jLabel1.setText("OS:");

        txtOsOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsOsActionPerformed(evt);
            }
        });

        jLabel2.setText("Data:");

        txtOsEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsEquipamentoActionPerformed(evt);
            }
        });

        jLabel3.setText("Equipamento:");

        txtOsDefeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsDefeitoActionPerformed(evt);
            }
        });

        jLabel4.setText("Defeito:");

        txtOsServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsServicoActionPerformed(evt);
            }
        });

        jLabel5.setText("Serviço:");

        jLabel6.setText("Valor:");

        txtOsTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsTecnicoActionPerformed(evt);
            }
        });

        jLabel7.setText("Técnico:");

        jLabel8.setText("ID cliente:");

        txtOsIdCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOsIdCliActionPerformed(evt);
            }
        });

        btnCreateCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCreateCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCliActionPerformed(evt);
            }
        });

        btnReadCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnReadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadCliActionPerformed(evt);
            }
        });

        btnUpdateCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnUpdateCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCliActionPerformed(evt);
            }
        });

        btnDeleteCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnDeleteCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtOsEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGap(34, 34, 34)
                            .addComponent(txtOsOs, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtOsDate, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnCreateCli)
                                    .addGap(57, 57, 57)
                                    .addComponent(btnReadCli)
                                    .addGap(57, 57, 57)
                                    .addComponent(btnUpdateCli)
                                    .addGap(59, 59, 59))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnDeleteCli)
                                    .addGap(19, 19, 19)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtOsServico, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOsDefeito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOsIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOsOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtOsDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOsEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtOsDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOsServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOsTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtOsValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOsIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateCli)
                    .addComponent(btnDeleteCli)
                    .addComponent(btnUpdateCli, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReadCli))
                .addGap(91, 91, 91))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtOsOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsOsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsOsActionPerformed

    private void txtOsEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsEquipamentoActionPerformed

    private void txtOsDefeitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsDefeitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsDefeitoActionPerformed

    private void txtOsServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsServicoActionPerformed

    private void txtOsTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsTecnicoActionPerformed

    private void txtOsIdCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOsIdCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsIdCliActionPerformed

    private void btnCreateCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCliActionPerformed
        // TODO add your handling code here:
        adicionar();

    }//GEN-LAST:event_btnCreateCliActionPerformed

    private void btnReadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadCliActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnReadCliActionPerformed

    private void btnUpdateCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCliActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUpdateCliActionPerformed

    private void btnDeleteCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCliActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnDeleteCliActionPerformed

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
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateCli;
    private javax.swing.JButton btnDeleteCli;
    private javax.swing.JButton btnReadCli;
    private javax.swing.JButton btnUpdateCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtOsDate;
    private javax.swing.JTextField txtOsDefeito;
    private javax.swing.JTextField txtOsEquipamento;
    private javax.swing.JTextField txtOsIdCli;
    private javax.swing.JTextField txtOsOs;
    private javax.swing.JTextField txtOsServico;
    private javax.swing.JTextField txtOsTecnico;
    private javax.swing.JTextField txtOsValor;
    // End of variables declaration//GEN-END:variables
}
