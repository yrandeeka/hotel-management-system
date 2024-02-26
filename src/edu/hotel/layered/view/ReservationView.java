/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.hotel.layered.view;

import com.toedter.calendar.JDateChooser;
import edu.hotel.layered.controller.CustomerController;
import edu.hotel.layered.controller.ReservationController;
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
    ReservationController reservationController;
    List<CustomerDto> cusDtos=new ArrayList<CustomerDto>();
    List<String> selectedRooms=new ArrayList<String>();
    List<RoomDto> roomDtos=new ArrayList<RoomDto>();
    String[] pkgTypes;
    Double totalCharge=0.0;
    String selectedRoomMessage="Not Found Selected Rooms!";
    /**
     * Creates new form ReservationView
     */
    public ReservationView() {
        this.customerController=new CustomerController();
        this.roomController=new RoomController();
        this.roomCategoryController=new RoomCategoryController();
        this.reservationController=new ReservationController();
        initComponents();
        popupMenuCustomer.add(panelCustomer);
        popupMenuRoom.add(panelRoom);
        loadCustomers();
        loadRooms();
        setPopup(txtCustName,popupMenuCustomer);
        setPopup(txtRoomDescription,popupMenuRoom);
        loadPkgs();
        txtOutstanding.setText(String.valueOf(totalCharge));
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
        
        pkgRate=setPkgRate(pkgType);

        totalCharge=(roomRate+pkgRate+resortFee+parkingFee)*(1+taxes);
        txtTotalCharge.setText(String.valueOf(valueFormat(totalCharge)));
    }
    private Double setPkgRate(String pkgType){
        switch (pkgType) {
            case "Full Board":
                return 30000.0;
            case "Half Board":
                return 20000.0;
            case "Bed and Breakfast":
                return 10000.0;
            default:
                return 0.0;
        }
    }
    private String valueFormat(Double number){
        return String.format("%.2f", number);
    }
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
        popupMenuSelectedRooms = new javax.swing.JPopupMenu();
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
        lblReservationTo = new javax.swing.JLabel();
        dtchooserReservationTo = new com.toedter.calendar.JDateChooser();
        lblOutstanding = new javax.swing.JLabel();
        txtOutstanding = new javax.swing.JTextField();
        btnSelectedRooms = new javax.swing.JButton();
        lblNoOfPkgs = new javax.swing.JLabel();
        txtDeposit1 = new javax.swing.JTextField();
        dtchooserBookingDate = new com.toedter.calendar.JDateChooser();

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
        lblDeposit.setText("Deposit (Rs.)");

        txtDeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepositKeyReleased(evt);
            }
        });

        lblBookingDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBookingDate.setText("Booking Date");

        dtchooserReservationFrom.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserReservationFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserReservationFromPropertyChange(evt);
            }
        });

        lblCheckinDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCheckinDate.setText("Check in Date");

        dtchooserCheckout.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserCheckout.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserCheckoutPropertyChange(evt);
            }
        });

        dtchooserCheckin.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserCheckin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserCheckinPropertyChange(evt);
            }
        });

        lblTotalCharge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotalCharge.setText("Total Charge (Rs.)");

        cmbobxPkgType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbobxPkgTypeItemStateChanged(evt);
            }
        });

        lblCheckoutDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCheckoutDate.setText("Check out Date");

        lblPkgType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPkgType.setText("Package Type");

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

        txtRoomDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomDescriptionActionPerformed(evt);
            }
        });
        txtRoomDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRoomDescriptionKeyReleased(evt);
            }
        });

        lblCustomerDetails.setText("Select a Customer!");

        lblRoomDetails.setText("Select a Room!");

        lblReservationFrom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblReservationFrom.setText("Reservation From");

        lblReservationTo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblReservationTo.setText("Reservation To");

        dtchooserReservationTo.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserReservationTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserReservationToPropertyChange(evt);
            }
        });

        lblOutstanding.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOutstanding.setText("Outstanding (Rs.)");

        txtOutstanding.setEditable(false);
        txtOutstanding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutstandingActionPerformed(evt);
            }
        });

        btnSelectedRooms.setText("Selected Rooms");
        btnSelectedRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSelectedRoomsMouseClicked(evt);
            }
        });
        btnSelectedRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectedRoomsActionPerformed(evt);
            }
        });

        lblNoOfPkgs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNoOfPkgs.setText("No of Packages");

        txtDeposit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeposit1KeyReleased(evt);
            }
        });

        dtchooserBookingDate.setEnabled(false);
        dtchooserBookingDate.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserBookingDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserBookingDatePropertyChange(evt);
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
                                .addGap(9, 9, 9)
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCheckinDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCheckoutDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(594, 594, 594)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dtchooserCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtchooserCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(lblManageReservations)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnToHome)
                                .addGap(266, 266, 266))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(dtchooserBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(btnSelectedRooms)
                                .addGap(18, 18, 18)
                                .addComponent(lblPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(222, 222, 222)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(148, 148, 148)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtDeposit1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNoOfPkgs, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblRoomDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(155, 155, 155))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(775, 775, 775)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReservationId)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblManageReservations)
                            .addComponent(btnToHome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustName)
                    .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomerDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRoomDetails))
                    .addComponent(lblRoomDescription))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSelectedRooms)
                                    .addComponent(lblPkgType)
                                    .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(dtchooserBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNoOfPkgs)
                                    .addComponent(txtDeposit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBookingDate)
                        .addGap(18, 18, 18)
                        .addComponent(lblReservationFrom)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReservationTo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblCheckinDate)
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(dtchooserCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalCharge)
                            .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDeposit)
                            .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)))
                .addComponent(jLabel6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOutstanding))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCheckoutDate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtchooserCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddReservation)
                    .addComponent(btnSearchReservation)
                    .addComponent(btnUpdateReservation)
                    .addComponent(btnUpdateReservation1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateReservation2)
                        .addComponent(btnCancelReservation)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddReservationActionPerformed
        // TODO add your handling code here:
        saveReservation();
    }//GEN-LAST:event_btnAddReservationActionPerformed

    private void btnSearchReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReservationActionPerformed
        // TODO add your handling code here:
        searchReservation();
    }//GEN-LAST:event_btnSearchReservationActionPerformed

    private void btnToHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnToHomeActionPerformed

    private void btnCancelReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelReservationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelReservationActionPerformed

    private void btnUpdateReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReservationActionPerformed
        // TODO add your handling code here:
        updateReservation();
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
        if(listCustomer.getSelectedValue()!=null){
        Integer id=Integer.parseInt(listCustomer.getSelectedValue().split("-")[0]);
        
            for (CustomerDto dto : cusDtos) {
                if(dto.getCustomerId().equals(id)){
                    lblCustomerDetails.setText("Contact : "+dto.getContactNo()+" | Email : "+dto.getEmail()+" | Address : "+dto.getAddress());
                }
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
        if(!selectedRooms.contains(listRoom.getSelectedValue())){
            selectedRooms.add(listRoom.getSelectedValue());
            txtRoomDescription.setText(listRoom.getSelectedValue());
            setTotalCharge(id);
        }
        else{
            JOptionPane.showMessageDialog(null, "Already in Selected Room List");
        }
        
    }//GEN-LAST:event_listRoomMouseClicked

    private void cmbobxPkgTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbobxPkgTypeItemStateChanged
        // TODO add your handling code here:
        if(listRoom.getSelectedValue()!=null){
            Integer id=Integer.parseInt(listRoom.getSelectedValue().split("-")[0]);
            setTotalCharge(id);
        }
    }//GEN-LAST:event_cmbobxPkgTypeItemStateChanged

    private void dtchooserReservationToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserReservationToPropertyChange
        // TODO add your handling code here:
        checkDates(dtchooserReservationFrom,dtchooserReservationTo,
                "Insert Reservation Start Date","Reservation End Date must be on or after Reservation Start Date");
    }//GEN-LAST:event_dtchooserReservationToPropertyChange

    private void dtchooserCheckinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserCheckinPropertyChange
        // TODO add your handling code here:
        checkDates(dtchooserCheckin,dtchooserCheckout,"Insert Check In Date",
                "Check Out Date must be on or after Check In Date");
    }//GEN-LAST:event_dtchooserCheckinPropertyChange

    private void dtchooserReservationFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserReservationFromPropertyChange
        // TODO add your handling code here:
        checkDates(dtchooserReservationFrom,dtchooserReservationTo,
                "Insert Reservation Start Date","Reservation End Date must be on or after Reservation Start Date");
    }//GEN-LAST:event_dtchooserReservationFromPropertyChange

    private void dtchooserCheckoutPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserCheckoutPropertyChange
        // TODO add your handling code here:
        checkDates(dtchooserCheckin,dtchooserCheckout,"Insert Check In Date",
                "Check Out Date must be on or after Check In Date");
    }//GEN-LAST:event_dtchooserCheckoutPropertyChange

    private void txtDepositKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyReleased
        // TODO add your handling code here:
        try {
            if(txtDeposit.getText()!=null){
                txtOutstanding.setText(String.valueOf(valueFormat(totalCharge-Double.parseDouble(txtDeposit.getText()))));
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Deposit Value - Error");
            txtOutstanding.setText("0.0");
        }
        
    }//GEN-LAST:event_txtDepositKeyReleased

    private void txtRoomDescriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomDescriptionKeyReleased
        // TODO add your handling code here:
        try {
            if(txtRoomDescription.getText()!=null && listRoom.getSelectedValue()!=null){
                Integer id=Integer.parseInt(listRoom.getSelectedValue().split("-")[0]);
                setTotalCharge(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Deposit Value - Error");
            txtTotalCharge.setText("0.0");
        }
            
    }//GEN-LAST:event_txtRoomDescriptionKeyReleased

    private void txtOutstandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutstandingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOutstandingActionPerformed

    private void txtRoomDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoomDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoomDescriptionActionPerformed

    private void btnSelectedRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectedRoomsMouseClicked
        // TODO add your handling code here:
        setSelectRooms();
    }//GEN-LAST:event_btnSelectedRoomsMouseClicked

    private void txtDeposit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeposit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeposit1KeyReleased

    private void btnSelectedRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectedRoomsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSelectedRoomsActionPerformed

    private void dtchooserBookingDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserBookingDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtchooserBookingDatePropertyChange
    
    /**
     * @param args the command line arguments
     */
    private void setSelectRooms(){
        if(selectedRooms.size()>0){
            selectedRoomMessage="";
            for (int i=1;i<=selectedRooms.size();i++) {
                System.out.println("i"+i);
                if(i<selectedRooms.size()){
                    selectedRoomMessage+=i+") "+selectedRooms.get(i-1)+" , ";
                }
                else{
                    selectedRoomMessage+=i+") "+selectedRooms.get(i-1);
                }
            }
        }
        JOptionPane.showMessageDialog(null, selectedRoomMessage);
    }
    private void checkDates(JDateChooser dateStart,JDateChooser dateEnd,String msg_1,String msg_2){
        if(dateEnd.getDate()!=null && dateStart.getDate()==null){
            JOptionPane.showMessageDialog(null, msg_1);
            dateEnd.setDate(null);
        }
        if(dateStart.getDate()!=null && dateEnd.getDate()!=null 
                && dateEnd.getDate().before(dateStart.getDate())){
            JOptionPane.showMessageDialog(null, msg_2);
            dateEnd.setDate(null);
        }
    }    
    private String formattedDateAndTime(Date date,String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDateTime = formatter.format(date);
        return formattedDateTime;
    }
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
    private javax.swing.JButton btnSelectedRooms;
    private javax.swing.JButton btnToHome;
    private javax.swing.JButton btnUpdateReservation;
    private javax.swing.JButton btnUpdateReservation1;
    private javax.swing.JButton btnUpdateReservation2;
    private javax.swing.JComboBox<String> cmbobxPkgType;
    private com.toedter.calendar.JDateChooser dtchooserBookingDate;
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
    private javax.swing.JLabel lblNoOfPkgs;
    private javax.swing.JLabel lblOutstanding;
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
    private javax.swing.JPopupMenu popupMenuSelectedRooms;
    private javax.swing.JScrollPane scrollCustomer;
    private javax.swing.JScrollPane scrollRoom;
    private javax.swing.JTable tblReservation;
    private javax.swing.JTextField txtCustName;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtDeposit1;
    private javax.swing.JTextField txtOutstanding;
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

    private void saveReservation() {
         int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?","Save Reservation",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                Date checkIn = null;
                Date checkOut = null;
                String pkgType=String.valueOf(cmbobxPkgType.getSelectedItem());
                Double pkgRate=setPkgRate(pkgType);
                Double ttlCharge=txtTotalCharge.getText().isEmpty()?0.0:Double.parseDouble(txtTotalCharge.getText());
                Double deposit=txtDeposit.getText().isEmpty()?0.0:Double.parseDouble(txtDeposit.getText());
                
                Date bookedDate=new Date();
                Date reservedFrom=dtchooserReservationFrom.getDate();
                Date reservedTo=dtchooserReservationTo.getDate();
                if(dtchooserCheckin.getDate()!=null)
                    checkIn=new Date(dtchooserCheckin.getDate().getTime());
                if(dtchooserCheckout.getDate()!=null)
                    checkOut=new Date(dtchooserCheckout.getDate().getTime());
                
                ReservationDto reservationDto=new ReservationDto(0,txtCustName.getText(),
                    selectedRooms,bookedDate,reservedFrom,reservedTo,checkIn,checkOut,
                        pkgType,pkgRate,ttlCharge,deposit,"no");

                String result=reservationController.saveReservation(reservationDto);
                if(result.equals("succeed")){
                   JOptionPane.showMessageDialog(null, " Saved Reservation Successfully");
                   //clear();
                   //loadCustomers();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Save Reservation Failed");
                }
            }
        } catch (Exception ex){
            Logger.getLogger(CustomerView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Input Details");
        }
    }
    private void searchReservation() {
         int id=0;
        try {
            if(!txtReservationId.getText().equals("")){
                id=Integer.parseInt(txtReservationId.getText());
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Please Enter Reservation Id");
                return;
            }
            ReservationDto dto=reservationController.getReservation(id);
            
            txtReservationId.setText(String.valueOf(dto.getReservationId()));
            txtCustName.setText(dto.getCustomerName());
            selectedRooms=dto.getRoomDescription();
            txtRoomDescription.setText(selectedRooms.get(0));
            dtchooserBookingDate.setDate(dto.getBookingDate());
            dtchooserReservationFrom.setDate(dto.getReservedFrom());
            dtchooserReservationTo.setDate(dto.getReservedTo());
            dtchooserCheckin.setDate(dto.getCheckIn());
            dtchooserCheckout.setDate(dto.getCheckOut());
            cmbobxPkgType.setSelectedItem(dto.getPackageType());
            txtTotalCharge.setText(String.valueOf(dto.getTotalCharge()));
            txtDeposit.setText(String.valueOf(dto.getDeposit()));
            txtOutstanding.setText(String.valueOf(dto.getTotalCharge()-dto.getDeposit()));
            
            
            CustomerDto cusDto=customerController.getCustomer(Integer.parseInt(dto.getCustomerName().split("-")[0]));
            lblCustomerDetails.setText("Contact : "+cusDto.getContactNo()+" | Email : "+cusDto.getEmail()+" | Address : "+cusDto.getAddress());
            RoomDto rmDto=roomController.getRoom(Integer.parseInt(selectedRooms.get(0).split("-")[0]));
            lblRoomDetails.setText("Category : "+rmDto.getCategory());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Found Details!!-"+e);
            //clear();
        }
    }

    private void updateReservation() {
        ReservationDto dto=new ReservationDto();
        
        try {
            String pkgType=String.valueOf(cmbobxPkgType.getSelectedItem());
            
            dto.setReservationId(Integer.parseInt(txtReservationId.getText()));
            dto.setCustomerName(txtCustName.getText());
            dto.setRoomDescription(selectedRooms);
            dto.setReservedFrom(dtchooserReservationFrom.getDate());
            dto.setReservedTo(dtchooserReservationTo.getDate());
            dto.setPackageType(pkgType);
            dto.setPackageRate(setPkgRate(pkgType));
            dto.setTotalCharge(Double.parseDouble(txtTotalCharge.getText()));
            dto.setDeposit(Double.parseDouble(txtDeposit.getText()));
            
            String result=reservationController.updateReservation(dto);
            if(result.equals("succeed")){
                JOptionPane.showMessageDialog(rootPane, "Reservation Updated Successfully");
                //clear();
                //loadCustomers();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Reservation Update Failed");
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error-Reservation Update");
        } 
    }
}
