/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.hotel.layered.view;

import edu.hotel.layered.controller.RoomCategoryController;
import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.dto.RoomCategoryDto;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jboss.logging.MDC;

/**
 *
 * @author Yasas Randeeka
 */
public class RoomCategoriesView extends javax.swing.JFrame {
    private RoomCategoryController roomCategoryController;
    /**
     * Creates new form RoomCategoriesView
     */
    public RoomCategoriesView() {
        initComponents();
        this.roomCategoryController=new RoomCategoryController();
        loadRoomCategories();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        lblManageRoomCategories = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblOccupancy = new javax.swing.JLabel();
        cmbobxOccupancy = new javax.swing.JComboBox<>();
        txtRate = new javax.swing.JTextField();
        lblBedsize = new javax.swing.JLabel();
        cmbobxBedsize = new javax.swing.JComboBox<>();
        btnAddRoomCategory = new javax.swing.JButton();
        btnSearchRoomCategory = new javax.swing.JButton();
        btnUpdateRoomCategory = new javax.swing.JButton();
        btnToHome = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRoomCategory = new javax.swing.JTable();
        lblRate = new javax.swing.JLabel();
        txtCategoryId = new javax.swing.JTextField();
        btnDeleteRoomCategory = new javax.swing.JButton();
        btnDeleteRoomCategory1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblManageRoomCategories.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        lblManageRoomCategories.setText("Mange Room Categories");

        lblCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCategory.setText("Category ID");

        lblOccupancy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOccupancy.setText("Occupancy");

        cmbobxOccupancy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbobxOccupancy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Double", "Triple", "Quad" }));

        txtRate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblBedsize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBedsize.setText("Bed Size");

        cmbobxBedsize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbobxBedsize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Queen", "King", "Twin", "Hollywood Twin", "Double double", "Studio" }));

        btnAddRoomCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddRoomCategory.setText("Add");
        btnAddRoomCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomCategoryActionPerformed(evt);
            }
        });

        btnSearchRoomCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchRoomCategory.setText("Search");
        btnSearchRoomCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchRoomCategoryActionPerformed(evt);
            }
        });

        btnUpdateRoomCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateRoomCategory.setText("Update");
        btnUpdateRoomCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomCategoryActionPerformed(evt);
            }
        });

        btnToHome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnToHome.setText("Home");
        btnToHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToHomeActionPerformed(evt);
            }
        });

        tblRoomCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRoomCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRoomCategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRoomCategory);

        lblRate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRate.setText("Rate");

        txtCategoryId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnDeleteRoomCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteRoomCategory.setText("Delete");
        btnDeleteRoomCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomCategoryActionPerformed(evt);
            }
        });

        btnDeleteRoomCategory1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteRoomCategory1.setText("Clear");
        btnDeleteRoomCategory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomCategory1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblManageRoomCategories)
                .addGap(91, 91, 91)
                .addComponent(btnToHome)
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOccupancy, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBedsize, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCategoryId, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtRate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(cmbobxOccupancy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbobxBedsize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdateRoomCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearchRoomCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddRoomCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteRoomCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDeleteRoomCategory1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnToHome)
                    .addComponent(lblManageRoomCategories))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddRoomCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchRoomCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateRoomCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteRoomCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteRoomCategory1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCategory))
                        .addGap(18, 18, 18)
                        .addComponent(cmbobxOccupancy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBedsize)
                            .addComponent(cmbobxBedsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRate)
                            .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblOccupancy)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRoomCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomCategoryActionPerformed
        // TODO add your handling code here:
        saveRoomCategories();
    }//GEN-LAST:event_btnAddRoomCategoryActionPerformed

    private void btnSearchRoomCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchRoomCategoryActionPerformed
        // TODO add your handling code here:
        searchRoomCategory();
    }//GEN-LAST:event_btnSearchRoomCategoryActionPerformed

    private void btnUpdateRoomCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRoomCategoryActionPerformed
        // TODO add your handling code here:
        updateRoomCategories();
    }//GEN-LAST:event_btnUpdateRoomCategoryActionPerformed

    private void btnToHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToHomeActionPerformed
        // TODO add your handling code here:
        new HomeView().close(this);
        new HomeView().setVisible(true);
    }//GEN-LAST:event_btnToHomeActionPerformed

    private void btnDeleteRoomCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomCategoryActionPerformed
        deleteRoomCategory();
    }//GEN-LAST:event_btnDeleteRoomCategoryActionPerformed

    private void btnDeleteRoomCategory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomCategory1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnDeleteRoomCategory1ActionPerformed

    private void tblRoomCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRoomCategoryMouseClicked
        // TODO add your handling code here:
        displayRoomCategories();
    }//GEN-LAST:event_tblRoomCategoryMouseClicked
    private void loadRoomCategories(){
        try {
            String columns[] = {"Id", "Occupancy", "Bed Size", "Rate"};
            DefaultTableModel dtm = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            tblRoomCategory.setModel(dtm);
            List<RoomCategoryDto> roomCategoryDtos = roomCategoryController.getAllRoomCategories();

            for (RoomCategoryDto roomCategoryDto : roomCategoryDtos) {
                Object[] rowData = {roomCategoryDto.getRoomCategoryId(), roomCategoryDto.getOccupancy(),
                    roomCategoryDto.getBedSize(),roomCategoryDto.getRate()};
                dtm.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loading Error-"+e);
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
            java.util.logging.Logger.getLogger(RoomCategoriesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomCategoriesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomCategoriesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomCategoriesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomCategoriesView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRoomCategory;
    private javax.swing.JButton btnDeleteRoomCategory;
    private javax.swing.JButton btnDeleteRoomCategory1;
    private javax.swing.JButton btnSearchRoomCategory;
    private javax.swing.JButton btnToHome;
    private javax.swing.JButton btnUpdateRoomCategory;
    private javax.swing.JComboBox<String> cmbobxBedsize;
    private javax.swing.JComboBox<String> cmbobxOccupancy;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBedsize;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblManageRoomCategories;
    private javax.swing.JLabel lblOccupancy;
    private javax.swing.JLabel lblRate;
    private javax.swing.JTable tblRoomCategory;
    private javax.swing.JTextField txtCategoryId;
    private javax.swing.JTextField txtRate;
    // End of variables declaration//GEN-END:variables

    private void saveRoomCategories() {
        int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Save Room Categories",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                String occupancy=String.valueOf(cmbobxOccupancy.getSelectedItem());
                String bedSize=String.valueOf(cmbobxBedsize.getSelectedItem());
                RoomCategoryDto roomCategoryDto=new RoomCategoryDto(0,occupancy,bedSize,Double.parseDouble(txtRate.getText()));

                String result=roomCategoryController.saveRoomCategory(roomCategoryDto);
                if(result.equals("succeed")){
                   JOptionPane.showMessageDialog(null, " Saved Room Category Successfully");
                   clear();
                   loadRoomCategories();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Save Room Category Failed");
                }
            }
        } catch (Exception ex){
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Input Details");
        }
    }

    private void searchRoomCategory() {
        Integer id=0;
        try {
            if(!txtCategoryId.getText().equals("")){
                id=Integer.parseInt(txtCategoryId.getText());
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Please Enter Category Id");
                return;
            }
            RoomCategoryDto dto=roomCategoryController.getRoomCategory(id);
            cmbobxOccupancy.setSelectedItem(dto.getOccupancy());
            cmbobxBedsize.setSelectedItem(dto.getBedSize());
            txtRate.setText(String.valueOf(dto.getRate()));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Found Details!!");
            clear();
        }
    }

    private void clear() {
          txtCategoryId.setText("");
          cmbobxOccupancy.setSelectedItem("Single");
          cmbobxBedsize.setSelectedItem("Queen");
          txtRate.setText("");
    }

    private void updateRoomCategories() {
        int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Save Room Categories",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                RoomCategoryDto dto=new RoomCategoryDto();
                dto.setRoomCategoryId(Integer.parseInt(txtCategoryId.getText()));
                dto.setOccupancy(cmbobxOccupancy.getSelectedItem().toString());
                dto.setBedSize(cmbobxBedsize.getSelectedItem().toString());
                dto.setRate(Double.parseDouble(txtRate.getText()));
                
                String result=roomCategoryController.updateRoomCategory(dto);
                if(result.equals("succeed")){
                    JOptionPane.showMessageDialog(rootPane, "Room Category Updated Successfully");
                    clear();
                    loadRoomCategories();
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Room Category Update Failed");
                }
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error-Room Category Update");
        } 
    }

    private void deleteRoomCategory() {
        Integer id=Integer.parseInt(txtCategoryId.getText());
        int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Delete Room Category",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                String result=roomCategoryController.deleteRoomCategory(id);
                if(result.equals("succeed")){
                   JOptionPane.showMessageDialog(null, " Deleted Room Category Successfully");
                   clear();
                   loadRoomCategories();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Room Category Failed");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error-Room Category Delete");    
        }
    }

    private void displayRoomCategories() {
        if(!txtCategoryId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First Clear Loaded Room Category Details!");
            return;
        }
        int selectedRow=tblRoomCategory.getSelectedRow();
        //{"Id", "Occupancy", "Bed Size", "Rate"};
        if(selectedRow!=-1){
            DefaultTableModel model=(DefaultTableModel)tblRoomCategory.getModel();
            
            txtCategoryId.setText(String.valueOf(model.getValueAt(selectedRow, 0)));
            cmbobxOccupancy.setSelectedItem(String.valueOf(model.getValueAt(selectedRow, 1)));
            cmbobxBedsize.setSelectedItem(String.valueOf(model.getValueAt(selectedRow, 2)));
            txtRate.setText(String.valueOf(model.getValueAt(selectedRow, 3)));
        }
    }
}
