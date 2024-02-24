/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.hotel.layered.view;

import edu.hotel.layered.controller.CustomerController;
import edu.hotel.layered.controller.RoomCategoryController;
import edu.hotel.layered.controller.RoomController;
import edu.hotel.layered.dto.CustomerDto;
import edu.hotel.layered.dto.ReservationDto;
import edu.hotel.layered.dto.RoomDto;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Yasas Randeeka
 */
public class ReservationView extends javax.swing.JFrame {
    CustomerController customerController;
    RoomController roomController;
    RoomCategoryController roomCategoryController;
    List<CustomerDto> cusDtos=new ArrayList<CustomerDto>();
    List<RoomDto> roomDtos=new ArrayList<RoomDto>();
    String[] pkgTypes;
    Double totalCharge=0.0;
    /**
     * Creates new form ReservationView
     */
    public ReservationView() {
        this.customerController=new CustomerController();
        this.roomController=new RoomController();
        this.roomCategoryController=new RoomCategoryController();
        initComponents();
        popupMenuCustomer.add(panelCustomer);
        popupMenuRoom.add(panelRoom);
        loadCustomers();
        loadRooms();
        setPopup(txtCustName,popupMenuCustomer);
        setPopup(txtRoomDescription,popupMenuRoom);
        loadPkgs();
    }
    private void loadPkgs(){
        pkgTypes=new String[]{"Select","Full Board","Half Board","Bed and Breakfast"};
        cmbobxPkgType.setModel(new DefaultComboBoxModel<>(pkgTypes));
    }
    private void setPopup(JTextField field,JPopupMenu menu){
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showPopupMenu();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                showPopupMenu();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
            private void showPopupMenu() {
                SwingUtilities.invokeLater(() -> {
                    // Show the popup menu below the text field
                    menu.show(field, 0, field.getHeight());
                    field.requestFocus();
                });
            }
        });
        getContentPane().add(field, BorderLayout.NORTH);
    }
    private Date setMinResveredDate(){
        Date currentDate = new Date();
        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // Add one day to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // Get the new date after adding one day
        return calendar.getTime();
    }
    private void setTotalCharge(Integer id){
        Double taxes=0.10;
        Double resortFee=500.0;
        Double parkingFee=500.0;
        Double pkgRate;
        Double roomRate=roomCategoryController.getRate(id);
        String pkgType=String.valueOf(cmbobxPkgType.getSelectedItem());
        System.out.println("pkgType-"+pkgType);
        
        switch (pkgType) {
            case "Full Board":
                pkgRate=30000.0;
                break;
            case "Half Board":
                pkgRate=20000.0;
                break;
            case "Bed and Breakfast":
                pkgRate=10000.0;
                break;
            default:
                pkgRate=0.0;
                break;
        }
        System.out.println("pkgRate"+pkgRate);
        totalCharge=(roomRate+pkgRate+resortFee+parkingFee)*(1+taxes);
        txtTotalCharge.setText(String.valueOf(valueFormat(totalCharge)));
    }
    private String valueFormat(Double number){
         //DecimalFormat decimalFormat = new DecimalFormat("#.##");
        // Formatting the number
        return String.format("%.2f", number);
        //return decimalFormat.format(number);
    }
//    private void filterData(){
//        String text=txtCustName.getText().trim();
//        for(CustomerDto cus : dtos) {
//            if(name.toLowerCase().startsWith(text)){
//                listModel.addElement(name);
//            }
//        }
//        listCustomer.setModel(listModel); 
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCustomer = new javax.swing.JPanel();
        scrollCustomer = new javax.swing.JScrollPane();
        listCustomer = new javax.swing.JList<>();
        popupMenuCustomer = new javax.swing.JPopupMenu();
        panelRoom = new javax.swing.JPanel();
        scrollRoom = new javax.swing.JScrollPane();
        listRoom = new javax.swing.JList<>();
        popupMenuRoom = new javax.swing.JPopupMenu();
        lblManageReservations = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservation = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnAddReservation = new javax.swing.JButton();
        btnSearchReservation = new javax.swing.JButton();
        btnToHome = new javax.swing.JButton();
        lblCustName = new javax.swing.JLabel();
        txtReservationId = new javax.swing.JTextField();
        lblRoomDescription = new javax.swing.JLabel();
        lblDeposit = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        lblBookingDate = new javax.swing.JLabel();
        dtchooserReservationFrom = new com.toedter.calendar.JDateChooser();
        lblCheckinDate = new javax.swing.JLabel();
        dtchooserCheckout = new com.toedter.calendar.JDateChooser();
        dtchooserCheckin = new com.toedter.calendar.JDateChooser();
        lblTotalCharge = new javax.swing.JLabel();
        cmbobxPkgType = new javax.swing.JComboBox<>();
        lblCheckoutDate = new javax.swing.JLabel();
        lblPkgType = new javax.swing.JLabel();
        txtTotalCharge = new javax.swing.JTextField();
        btnReservationId = new javax.swing.JLabel();
        btnCancelReservation = new javax.swing.JButton();
        btnUpdateReservation = new javax.swing.JButton();
        btnUpdateReservation1 = new javax.swing.JButton();
        btnUpdateReservation2 = new javax.swing.JButton();
        txtCustName = new javax.swing.JTextField();
        txtRoomDescription = new javax.swing.JTextField();
        lblCustomerDetails = new javax.swing.JLabel();
        lblRoomDetails = new javax.swing.JLabel();
        lblReservationFrom = new javax.swing.JLabel();
        txtBookingDate = new javax.swing.JTextField();
        lblReservationTo = new javax.swing.JLabel();
        dtchooserReservationTo = new com.toedter.calendar.JDateChooser();

        listCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listCustomerMouseClicked(evt);
            }
        });
        scrollCustomer.setViewportView(listCustomer);

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
            .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
            .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
        );

        listRoom.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listRoomMouseClicked(evt);
            }
        });
        scrollRoom.setViewportView(listRoom);

        javax.swing.GroupLayout panelRoomLayout = new javax.swing.GroupLayout(panelRoom);
        panelRoom.setLayout(panelRoomLayout);
        panelRoomLayout.setHorizontalGroup(
            panelRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
            .addGroup(panelRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
        );
        panelRoomLayout.setVerticalGroup(
            panelRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
            .addGroup(panelRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRoomLayout.createSequentialGroup()
                    .addComponent(scrollRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 255));

        lblManageReservations.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        lblManageReservations.setText("Mange Reservations");

        tblReservation.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblReservation);

        btnAddReservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddReservation.setText("Add");
        btnAddReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddReservationActionPerformed(evt);
            }
        });

        btnSearchReservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchReservation.setText("Search");
        btnSearchReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchReservationActionPerformed(evt);
            }
        });

        btnToHome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnToHome.setText("Home");
        btnToHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToHomeActionPerformed(evt);
            }
        });

        lblCustName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCustName.setText("Customer Name");

        lblRoomDescription.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRoomDescription.setText("Room Description");

        lblDeposit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDeposit.setText("Deposit");

        lblBookingDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBookingDate.setText("Booking Date");

        dtchooserReservationFrom.setMinSelectableDate(Calendar.getInstance().getTime());

        lblCheckinDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCheckinDate.setText("Check in Date");

        dtchooserCheckout.setMinSelectableDate(Calendar.getInstance().getTime());

        dtchooserCheckin.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserCheckin.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dtchooserCheckinInputMethodTextChanged(evt);
            }
        });

        lblTotalCharge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotalCharge.setText("Total Charge (Rs.)");

        cmbobxPkgType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbobxPkgTypeItemStateChanged(evt);
            }
        });
        cmbobxPkgType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbobxPkgTypeMouseClicked(evt);
            }
        });
        cmbobxPkgType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbobxPkgTypeActionPerformed(evt);
            }
        });

        lblCheckoutDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCheckoutDate.setText("Check out Date");

        lblPkgType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPkgType.setText("Pakage Type");

        txtTotalCharge.setEditable(false);

        btnReservationId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReservationId.setText("Reservation ID");

        btnCancelReservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelReservation.setText("Cancel Reservation");
        btnCancelReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelReservationActionPerformed(evt);
            }
        });

        btnUpdateReservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateReservation.setText("Update");
        btnUpdateReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateReservationActionPerformed(evt);
            }
        });

        btnUpdateReservation1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateReservation1.setText("Delete");
        btnUpdateReservation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateReservation1ActionPerformed(evt);
            }
        });

        btnUpdateReservation2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateReservation2.setText("Clear");
        btnUpdateReservation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateReservation2ActionPerformed(evt);
            }
        });

        txtCustName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustNameKeyReleased(evt);
            }
        });

        lblCustomerDetails.setText("Select a Customer!");

        lblRoomDetails.setText("Select a Room!");

        lblReservationFrom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblReservationFrom.setText("Reservation From");

        txtBookingDate.setEditable(false);

        lblReservationTo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblReservationTo.setText("Reservation To");

        dtchooserReservationTo.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserReservationTo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dtchooserReservationToInputMethodTextChanged(evt);
            }
        });
        dtchooserReservationTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserReservationToPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(lblManageReservations)
                                .addGap(257, 257, 257)
                                .addComponent(btnToHome))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(544, 544, 544)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddReservation)
                                .addGap(12, 12, 12)
                                .addComponent(btnSearchReservation)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateReservation)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateReservation1)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateReservation2)
                                .addGap(85, 85, 85)
                                .addComponent(btnCancelReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCheckoutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCheckinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(184, 184, 184)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblRoomDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtchooserCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dtchooserCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(171, 171, 171)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(lblDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(775, 775, 775)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnToHome))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblManageReservations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReservationId))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblRoomDetails))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCustName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRoomDescription)
                                    .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblBookingDate)
                                    .addComponent(txtBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtchooserCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblReservationFrom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblReservationTo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCheckinDate)
                                .addGap(0, 4, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPkgType)
                            .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalCharge)
                            .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDeposit)
                            .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtchooserCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCheckoutDate, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddReservation)
                            .addComponent(btnSearchReservation)
                            .addComponent(btnUpdateReservation)
                            .addComponent(btnUpdateReservation1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUpdateReservation2)
                                .addComponent(btnCancelReservation)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddReservationActionPerformed
        // TODO add your handling code here:
        saveReservation();
    }//GEN-LAST:event_btnAddReservationActionPerformed

    private void btnSearchReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReservationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchReservationActionPerformed

    private void btnToHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnToHomeActionPerformed

    private void cmbobxPkgTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbobxPkgTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbobxPkgTypeActionPerformed

    private void btnCancelReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelReservationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelReservationActionPerformed

    private void btnUpdateReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReservationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateReservationActionPerformed

    private void btnUpdateReservation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReservation1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateReservation1ActionPerformed

    private void btnUpdateReservation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReservation2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateReservation2ActionPerformed

    private void txtCustNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustNameKeyReleased
        // TODO add your handling code here:
//        String search=txtCustName.getText().trim();
//        if(!search.equals("")){
//            popupMenuCustomer.setVisible(true);
//        } 
    }//GEN-LAST:event_txtCustNameKeyReleased

    private void listCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCustomerMouseClicked
        // TODO add your handling code here:
        Integer id=Integer.parseInt(listCustomer.getSelectedValue().split("-")[0]);
        
        for (CustomerDto dto : cusDtos) {
            if(dto.getCustomerId().equals(id)){
                lblCustomerDetails.setText("Contact : "+dto.getContactNo()+" | Email : "+dto.getEmail()+" | Address : "+dto.getAddress());
            }
        }
        txtCustName.setText(listCustomer.getSelectedValue());      
    }//GEN-LAST:event_listCustomerMouseClicked

    private void listRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listRoomMouseClicked
        // TODO add your handling code here:
        Integer id=Integer.parseInt(listRoom.getSelectedValue().split("-")[0]);
        
        for (RoomDto dto : roomDtos) {
            if(dto.getRoomId().equals(id)){
                lblRoomDetails.setText("Category : "+dto.getCategory());
            }
        }
        txtRoomDescription.setText(listRoom.getSelectedValue());
        setTotalCharge(id);
    }//GEN-LAST:event_listRoomMouseClicked

    private void cmbobxPkgTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbobxPkgTypeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbobxPkgTypeMouseClicked

    private void cmbobxPkgTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbobxPkgTypeItemStateChanged
        // TODO add your handling code here:
        Integer id=Integer.parseInt(listRoom.getSelectedValue().split("-")[0]);
        System.out.println("id"+id);
        setTotalCharge(id);
    }//GEN-LAST:event_cmbobxPkgTypeItemStateChanged

    private void dtchooserCheckinInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dtchooserCheckinInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_dtchooserCheckinInputMethodTextChanged

    private void dtchooserReservationToInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dtchooserReservationToInputMethodTextChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_dtchooserReservationToInputMethodTextChanged

    private void dtchooserReservationToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserReservationToPropertyChange
        // TODO add your handling code here:
        System.out.println("dtchooserReservationToInputMethodTextChanged");
        if(dtchooserReservationTo.getDate().before(dtchooserReservationFrom.getDate()))
            JOptionPane.showMessageDialog(null, "Reservation End Date must be on or after Reservation Start Date");
    }//GEN-LAST:event_dtchooserReservationToPropertyChange

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
            java.util.logging.Logger.getLogger(ReservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservationView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddReservation;
    private javax.swing.JButton btnCancelReservation;
    private javax.swing.JLabel btnReservationId;
    private javax.swing.JButton btnSearchReservation;
    private javax.swing.JButton btnToHome;
    private javax.swing.JButton btnUpdateReservation;
    private javax.swing.JButton btnUpdateReservation1;
    private javax.swing.JButton btnUpdateReservation2;
    private javax.swing.JComboBox<String> cmbobxPkgType;
    private com.toedter.calendar.JDateChooser dtchooserCheckin;
    private com.toedter.calendar.JDateChooser dtchooserCheckout;
    private com.toedter.calendar.JDateChooser dtchooserReservationFrom;
    private com.toedter.calendar.JDateChooser dtchooserReservationTo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBookingDate;
    private javax.swing.JLabel lblCheckinDate;
    private javax.swing.JLabel lblCheckoutDate;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JLabel lblCustomerDetails;
    private javax.swing.JLabel lblDeposit;
    private javax.swing.JLabel lblManageReservations;
    private javax.swing.JLabel lblPkgType;
    private javax.swing.JLabel lblReservationFrom;
    private javax.swing.JLabel lblReservationTo;
    private javax.swing.JLabel lblRoomDescription;
    private javax.swing.JLabel lblRoomDetails;
    private javax.swing.JLabel lblTotalCharge;
    private javax.swing.JList<String> listCustomer;
    private javax.swing.JList<String> listRoom;
    private javax.swing.JPanel panelCustomer;
    private javax.swing.JPanel panelRoom;
    private javax.swing.JPopupMenu popupMenuCustomer;
    private javax.swing.JPopupMenu popupMenuRoom;
    private javax.swing.JScrollPane scrollCustomer;
    private javax.swing.JScrollPane scrollRoom;
    private javax.swing.JTable tblReservation;
    private javax.swing.JTextField txtBookingDate;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtReservationId;
    private javax.swing.JTextField txtRoomDescription;
    private javax.swing.JTextField txtTotalCharge;
    // End of variables declaration//GEN-END:variables

    private void loadCustomers() {
        cusDtos=customerController.getAllCustomer();
        DefaultListModel<String> listModel=new DefaultListModel<>();
        for (CustomerDto dto : cusDtos) {
            listModel.addElement(dto.getCustomerId()+"-"+dto.getFirstName()+" "+dto.getLastName());
        }
        listCustomer.setModel(listModel);
    }
    private void loadRooms(){
        roomDtos=roomController.getAllrooms();
        DefaultListModel<String> listModel=new DefaultListModel<>();
        for (RoomDto roomDto : roomDtos) {
            if(roomDto.getAvaiable().equals("Yes"))
                listModel.addElement(roomDto.getRoomId()+"-"+roomDto.getDescription());
        }
        listRoom.setModel(listModel);
    }
    private String formattedDateAndTime(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.format(date);
        return formattedDateTime;
    }
    private void saveReservation() {
         int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?","Save Reservation",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                String checkIn="";
                String checkOut="";
                System.out.println(txtTotalCharge.getText());
                
                Double ttlCharge=txtTotalCharge.getText().isEmpty()?0.0:Double.parseDouble(txtTotalCharge.getText());
                Double deposit=txtDeposit.getText().isEmpty()?0.0:Double.parseDouble(txtDeposit.getText());
                
                String bookedDate=formattedDateAndTime(new Date());
                String reservedFrom=formattedDateAndTime(new Date(dtchooserReservationFrom.getDate().getTime()));
                String reservedTo=formattedDateAndTime(new Date(dtchooserReservationTo.getDate().getTime()));
                if(dtchooserCheckin.getDate()!=null)
                    checkIn=formattedDateAndTime(new Date(dtchooserCheckin.getDate().getTime()));
                if(dtchooserCheckout.getDate()!=null)
                    checkOut=formattedDateAndTime(new Date(dtchooserCheckout.getDate().getTime()));
                
                System.out.println("reservedFrom-"+reservedFrom);
                System.out.println("reservedTo-"+reservedTo);
                ReservationDto reservationDto=new ReservationDto(0,txtCustName.getText(),
                    txtRoomDescription.getText(),bookedDate,reservedFrom,reservedTo,checkIn,checkOut,
                        String.valueOf(cmbobxPkgType.getSelectedItem()),ttlCharge,deposit);

//                String result=customerController.saveCustomer(customerDto);
//                if(result.equals("Succeed")){
//                   JOptionPane.showMessageDialog(null, " Saved Customer Successfully");
//                   clear();
//                   loadCustomers();
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Save Customer Failed");
//                }
            }
        } catch (Exception ex){
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Input Details");
        }
    }
}
