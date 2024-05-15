
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hedieh
 */
public class UpdateAdminUI extends javax.swing.JFrame {

    InsurancePolicy policy;
    AdminUI adminUI;
    /**
     * Creates new form UpdateAdminUI
     */
    public UpdateAdminUI(InsurancePolicy policy,AdminUI adminUI)
    {
        this.policy=policy;
        this.adminUI=adminUI;
        initComponents();
         
       policyIDText.setText(policy.getPolicyID()+"");
       policyHolderNameText.setText(policy.getPolicyHolderName()+"");
       numberOfClaimsText.setText(policy.getNumberOfClaims()+"");
       modelText.setText(policy.getCarModel());
       carTypeComboBox.setSelectedItem(policy.car.getCarType().toString());
       manufacturingYearText.setText(policy.getCar().getManufacturingYear()+"");
       priceText.setText(policy.getCar().getPrice()+"");
       dayText.setText(policy.getExpiryDate().getDay()+"");
       monthText.setText(policy.getExpiryDate().getMonth()+"");
       yearText.setText(policy.getExpiryDate().getYear()+"");
       if(policy instanceof ThirdPartyPolicy)
       {
           thirdPartyPolicy.setSelected(true);
           comprehensivePolicy.setSelected(false);
           driverAgeText.setVisible(false);
           levelText.setVisible(false);
           commentText.setText(((ThirdPartyPolicy)policy).getComment());
       }
      else
       {
           thirdPartyPolicy.setSelected(false);
           comprehensivePolicy.setSelected(true);
           commentText.setVisible(false);
           driverAgeText.setText(((ComprehensivePolicy)policy).getDriverAge()+"");
           levelText.setText(((ComprehensivePolicy)policy).getLevel()+"");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        thirdPartyPolicy = new javax.swing.JRadioButton();
        comprehensivePolicy = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        policyIDLable = new javax.swing.JLabel();
        policyHolderNameText = new javax.swing.JTextField();
        policyIDText = new javax.swing.JTextField();
        numberOfClaimsText = new javax.swing.JTextField();
        modelText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dayText = new javax.swing.JTextField();
        monthText = new javax.swing.JTextField();
        yearText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JToggleButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        manufacturingYearText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        commentLable = new javax.swing.JLabel();
        commentText = new javax.swing.JTextField();
        driverAgeText = new javax.swing.JTextField();
        levelText = new javax.swing.JTextField();
        driverAgeLable = new javax.swing.JLabel();
        levelLable = new javax.swing.JLabel();
        carTypeComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        thirdPartyPolicy.setText("Third Part Policy");
        thirdPartyPolicy.setEnabled(false);
        thirdPartyPolicy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                thirdPartyPolicyStateChanged(evt);
            }
        });

        buttonGroup1.add(comprehensivePolicy);
        comprehensivePolicy.setText("Comprehensive Policy");
        comprehensivePolicy.setEnabled(false);
        comprehensivePolicy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                comprehensivePolicyStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Policy Information");

        jLabel4.setText("Policy Holder Name");

        policyIDLable.setText("Policy ID");

        policyHolderNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyHolderNameTextActionPerformed(evt);
            }
        });

        jLabel8.setText("number of Claims");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Car");

        jLabel10.setText("model ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Expiry Date");

        jLabel12.setText("Day");

        jLabel13.setText("Month");

        jLabel14.setText("Year");

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 0, 102));
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 0, 102));
        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel17.setText("Car Type");

        jLabel18.setText("ManufacturingYear ");

        jLabel19.setText("Price");

        commentLable.setText("Comment");

        commentText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentTextActionPerformed(evt);
            }
        });

        driverAgeLable.setText("Driver Age");

        levelLable.setText("Level");

        carTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SUV", "SED", "LUX", "HATCH", "UTE" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(74, 74, 74)
                                    .addComponent(thirdPartyPolicy)
                                    .addGap(44, 44, 44)
                                    .addComponent(comprehensivePolicy))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(41, 41, 41)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel10)
                                                            .addComponent(jLabel17))
                                                        .addGap(49, 49, 49)))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(9, 9, 9)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel19)
                                                        .addComponent(jLabel18)))
                                                .addComponent(policyIDLable)
                                                .addComponent(jLabel8)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(61, 61, 61)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13)
                                                .addComponent(jLabel12)
                                                .addComponent(jLabel14)
                                                .addComponent(commentLable)
                                                .addComponent(driverAgeLable)
                                                .addComponent(levelLable))))
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(numberOfClaimsText)
                                        .addComponent(modelText)
                                        .addComponent(manufacturingYearText)
                                        .addComponent(priceText)
                                        .addComponent(dayText)
                                        .addComponent(monthText)
                                        .addComponent(yearText)
                                        .addComponent(commentText)
                                        .addComponent(driverAgeText)
                                        .addComponent(levelText)
                                        .addComponent(policyHolderNameText)
                                        .addComponent(policyIDText)
                                        .addComponent(carTypeComboBox, 0, 144, Short.MAX_VALUE))
                                    .addGap(9, 9, 9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3)))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thirdPartyPolicy)
                    .addComponent(comprehensivePolicy))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearButton)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(policyIDLable)
                    .addComponent(policyIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(policyHolderNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(numberOfClaimsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modelText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(carTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manufacturingYearText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(dayText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(monthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(yearText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commentText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(commentLable))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driverAgeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driverAgeLable))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(levelText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelLable))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void thirdPartyPolicyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_thirdPartyPolicyStateChanged
        if (thirdPartyPolicy.isSelected())
        {
            driverAgeText.setVisible(false);
            driverAgeLable.setVisible(false);
            levelLable.setVisible(false);
            levelText.setVisible(false);

        }
        else
        {
            driverAgeText.setVisible(true);
            driverAgeLable.setVisible(true);
            levelLable.setVisible(true);
            levelText.setVisible(true);
        }
    }//GEN-LAST:event_thirdPartyPolicyStateChanged

    private void comprehensivePolicyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comprehensivePolicyStateChanged
        if (comprehensivePolicy.isSelected())
        {
            driverAgeText.setVisible(true);
            driverAgeLable.setVisible(true);
            levelLable.setVisible(true);
            levelText.setVisible(true);
            commentText.setVisible(false);
            commentLable.setVisible(false);

        }
        else
        {
            driverAgeText.setVisible(false);
            driverAgeLable.setVisible(false);
            levelLable.setVisible(false);
            levelText.setVisible(false);
            commentText.setVisible(true);
        }
    }//GEN-LAST:event_comprehensivePolicyStateChanged

    private void policyHolderNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyHolderNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_policyHolderNameTextActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try
        {
            int policyID=Integer.parseInt(policyIDText.getText());
            String policyHolderName= policyHolderNameText.getText();
            int numberOfClaims=Integer.parseInt(numberOfClaimsText.getText());
            String model= modelText.getText();
            CarType carType= CarType.valueOf(carTypeComboBox.getSelectedItem().toString());
            int manufacturingYear=Integer.parseInt(manufacturingYearText.getText());
            double price=Double.parseDouble(priceText.getText());
            Car car= new Car(model,carType,manufacturingYear,price);
            int day=Integer.parseInt(dayText.getText());
            int month=Integer.parseInt(monthText.getText());
            int year=Integer.parseInt(yearText.getText());
            MyDate date= new MyDate(year,month,day);
            //           sets
            policy.setPolicyHolderName(policyHolderName);
            policy.setPolicyID(policyID);
            policy.setNumberOfClaims(numberOfClaims);
            policy.setCar(car);
            policy.setExpiryDate(date);
            if(thirdPartyPolicy.isSelected())
            {
                String comment= commentText.getText();
                ((ThirdPartyPolicy)policy).comments=comment;
            }
            else
            {
                int driverAge=Integer.parseInt(driverAgeText.getText());
                int level=Integer.parseInt(levelText.getText());
                ((ComprehensivePolicy)policy).driverAge=driverAge;
                ((ComprehensivePolicy)policy).level=level;
            }
            JOptionPane.showMessageDialog(this,"Plan has been Updated");
            adminUI.fillTable();
            this.dispose();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        policyIDText.setText("");
        policyHolderNameText.setText("");
        numberOfClaimsText.setText("");
        dayText.setText("");
        monthText.setText("");
        yearText.setText("");
        modelText.setText("");
        manufacturingYearText.setText("");
        //        carTypeText.setText("");
        priceText.setText("");
        commentText.setText("");
        driverAgeText.setText("");
        levelText.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void commentTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentTextActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(UpdateAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UpdateAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UpdateAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UpdateAdminUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new UpdateAdminUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> carTypeComboBox;
    private javax.swing.JToggleButton clearButton;
    private javax.swing.JLabel commentLable;
    private javax.swing.JTextField commentText;
    private javax.swing.JRadioButton comprehensivePolicy;
    private javax.swing.JTextField dayText;
    private javax.swing.JLabel driverAgeLable;
    private javax.swing.JTextField driverAgeText;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel levelLable;
    private javax.swing.JTextField levelText;
    private javax.swing.JTextField manufacturingYearText;
    private javax.swing.JTextField modelText;
    private javax.swing.JTextField monthText;
    private javax.swing.JTextField numberOfClaimsText;
    private javax.swing.JTextField policyHolderNameText;
    private javax.swing.JLabel policyIDLable;
    private javax.swing.JTextField policyIDText;
    private javax.swing.JTextField priceText;
    private javax.swing.JRadioButton thirdPartyPolicy;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField yearText;
    // End of variables declaration//GEN-END:variables
}