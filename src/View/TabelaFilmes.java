package View;

import data.Filme;
import java.util.List;
import data.FilmeDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TabelaFilmes extends javax.swing.JFrame {

    public TabelaFilmes() {
        initComponents();
        preencherLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Cadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Pesquisar = new javax.swing.JButton();
        txtCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria");

        Pesquisar.setText("Pesquisar");
        Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisarActionPerformed(evt);
            }
        });

        txtCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoriaActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Filme", "Categoria", "Data lançamento"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Pesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)))
                        .addGap(18, 18, 18)
                        .addComponent(Cadastrar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar)
                    .addComponent(btnEditar)
                    .addComponent(jButton3)
                    .addComponent(Pesquisar)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoriaActionPerformed

    }//GEN-LAST:event_txtCategoriaActionPerformed

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed
        TelaFilme cad = new TelaFilme();
        cad.setVisible(true);
        dispose();
    }//GEN-LAST:event_CadastrarActionPerformed

    private void PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisarActionPerformed
        String categoria = txtCategoria.getText();
        FilmeDAO dao = new FilmeDAO();
        try {
            List<Filme> filmes = dao.ConsultaPorCategoria(categoria);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            for (Filme filme : filmes) {
                model.addRow(new Object[]{
                    filme.getNomeFilme(), filme.getCategoria(), filme.getDataLancamento()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar filmes por categoria: " + ex.getMessage());
        }

    }//GEN-LAST:event_PesquisarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um filme para editar.");
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0); // Obter o ID da primeira coluna (coluna 0)

        String nome = (String) model.getValueAt(selectedRow, 1);
        String categoria = (String) model.getValueAt(selectedRow, 2);
        String dataLancamento = (String) model.getValueAt(selectedRow, 3);

        String novoNome = JOptionPane.showInputDialog("Novo nome:", nome);
        String novaCategoria = JOptionPane.showInputDialog("Nova categoria:", categoria);
        String novaDataLancamento = JOptionPane.showInputDialog("Nova data de lançamento (DD/MM/AAAA):", dataLancamento);

        if (novoNome == null || novaCategoria == null || novaDataLancamento == null) {
            JOptionPane.showMessageDialog(null, "Edição cancelada.");
            return;
        }

        // Verificar se a nova data de lançamento está em um formato válido (DD/MM/AAAA)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(novaDataLancamento);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato DD/MM/AAAA.");
            return;
        }

        // Se a data estiver em um formato válido, continue com a atualização do filme
        Filme filmeEditado = new Filme(novoNome, novaDataLancamento, novaCategoria);
        filmeEditado.setId(id); // Define o ID do filme

        try {
            FilmeDAO dao = new FilmeDAO();
            String resultado = dao.atualizar(filmeEditado);

            if (resultado == null) {
                JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso.");

                model.setValueAt(novoNome, selectedRow, 1);
                model.setValueAt(novaCategoria, selectedRow, 2);
                model.setValueAt(novaDataLancamento, selectedRow, 3);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar o filme: " + resultado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o filme: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(TabelaFilmes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um filme para editar.");
            return;
        }

        String nome = (String) model.getValueAt(selectedRow, 0);
        String categoria = (String) model.getValueAt(selectedRow, 1);
        String dataLancamento = (String) model.getValueAt(selectedRow, 2);

        String novoNome = JOptionPane.showInputDialog("Novo nome:", nome);
        String novaCategoria = JOptionPane.showInputDialog("Nova categoria:", categoria);
        String novaDataLancamento = JOptionPane.showInputDialog("Nova data de lançamento (DD/MM/AAAA):", dataLancamento);

        if (novoNome == null || novaCategoria == null || novaDataLancamento == null) {
            JOptionPane.showMessageDialog(null, "Edição cancelada.");
            return;
        }

        // Verificar se a nova data de lançamento está em um formato válido (DD/MM/AAAA)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(novaDataLancamento);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato DD/MM/AAAA.");
            return;
        }

        // Se a data estiver em um formato válido, continue com a atualização do filme
        Filme filmeEditado = new Filme(novoNome, novaDataLancamento, novaCategoria);

        try {
            FilmeDAO dao = new FilmeDAO();
            String resultado = dao.atualizar(filmeEditado);

            if (resultado == null) {
                JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso.");

                model.setValueAt(novoNome, selectedRow, 0);
                model.setValueAt(novaCategoria, selectedRow, 1);
                model.setValueAt(novaDataLancamento, selectedRow, 2);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar o filme: " + resultado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o filme: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(TabelaFilmes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TabelaFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Pesquisar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtCategoria;
    // End of variables declaration//GEN-END:variables

  public void preencherLista() {
    FilmeDAO dao = new FilmeDAO();
    try {
        List<Filme> filmes = dao.consultarTodos(); // Chama o método que consulta todos os filmes
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setColumnIdentifiers(new Object[]{ "ID", "Nome", "Categoria", "Data de Lançamento"});
        model.setRowCount(0); // Limpa a tabela antes de preencher com os novos dados

        for (Filme filme : filmes) {
            model.addRow(new Object[]{
                filme.getId(), filme.getNomeFilme(), filme.getCategoria(), filme.getDataLancamento()
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar filmes: " + ex.getMessage());
    }
}
}
