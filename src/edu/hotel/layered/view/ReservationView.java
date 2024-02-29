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
import edu.hotel.layered.entity.RoomEntity;
import edu.hotel.layered.view.CustomerView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

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
    Integer noOfpackages=0;
    Double extraPayPercentage=0.10;
    String selectedRoomMessage="Not Found Selected Rooms!";
    char searchClicked='N';
    char displayClicked='N';
    char ClearClicked='N';
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
        loadReservations();
    }
    private void loadPkgs(){
        pkgTypes=new String[]{"Full Board","Half Board","Bed and Breakfast"};
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
    private void setTotalCharge() throws ParseException{
        Double pkgRate;
        Double roomRate=0.0;
        Double totalPkgRate=0.0;
        Integer noOfPkgs=0;
        Integer id=0;
        Double total_charge=0.0;
//        if(listRoom.getSelectedValue()!=null)
//            id=Integer.parseInt(listRoom.getSelectedValue().split("-")[0]);
        if(!selectedRooms.isEmpty()){
            for (int i = 0; i < selectedRooms.size(); i++) {
                id=Integer.parseInt(selectedRooms.get(i).split("-")[0]);
                roomRate+=roomCategoryController.getRate(id)==null?0.0:roomCategoryController.getRate(id);
            }
        }

        //roomRate=roomCategoryController.getRate(id)==null?0.0:roomCategoryController.getRate(id);
        System.out.println("roomRate"+roomRate);
        String pkgType=String.valueOf(cmbobxPkgType.getSelectedItem());
        pkgRate=setPkgRate(pkgType);
        System.out.println("pkgRate-"+pkgRate);
        noOfPkgs=Integer.parseInt(cmboboxNoOfPkgs.getSelectedItem().toString());
        totalPkgRate=pkgRate*noOfPkgs;
        System.out.println("totalPkgRate-"+totalPkgRate);
        total_charge=(roomRate+totalPkgRate)*getReservedDays()*(1+extraPayPercentage);
        txtTotalCharge.setText(String.valueOf(valueFormat(total_charge)));
        System.out.println("totalCharge-"+totalCharge);
        totalCharge=total_charge;
    }
    private long getReservedDays() throws ParseException{
        long reservedDays=0;
        Date from = null;
        Date to;
        System.out.println("dtchooserReservationFrom.getDate()-"+dtchooserReservationFrom.getDate());
        System.out.println("dtchooserReservationTo.getDate()-"+dtchooserReservationTo.getDate());
        
        if(dtchooserReservationFrom.getDate()!=null && dtchooserReservationTo.getDate()!=null){
            from=formattedDateAndTime(dtchooserReservationFrom.getDate(),"yyyy-MM-dd");
            to=formattedDateAndTime(dtchooserReservationFrom.getDate(),"yyyy-MM-dd");
            reservedDays=1+((from.getTime()-to.getTime())/(1000*60*60*24));
        }
        System.out.println("reservedDays-"+reservedDays);
        return reservedDays;
    }
    private Double setPkgRate(String pkgType){
        switch (pkgType) {
            case "Full Board":
                return 15000.0;
            case "Half Board":
                return 10000.0;
            case "Bed and Breakfast":
                return 5000.0;
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
        btnClearReservation = new javax.swing.JButton();
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
        dtchooserBookingDate = new com.toedter.calendar.JDateChooser();
        cmboboxNoOfPkgs = new javax.swing.JComboBox<>();
        btnCalculateTotalCharge = new javax.swing.JButton();
        btnCheckRoomAvailability = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        tblReservation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReservationMouseClicked(evt);
            }
        });
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

        txtDeposit.setText("0");

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

        lblCheckoutDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCheckoutDate.setText("Check out Date");

        lblPkgType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPkgType.setText("Package Type");

        txtTotalCharge.setEditable(false);
        txtTotalCharge.setText("0");
        txtTotalCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalChargeActionPerformed(evt);
            }
        });

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

        btnClearReservation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearReservation.setText("Clear");
        btnClearReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearReservationActionPerformed(evt);
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
        txtOutstanding.setText("0");
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

        dtchooserBookingDate.setEnabled(false);
        dtchooserBookingDate.setMinSelectableDate(Calendar.getInstance().getTime());
        dtchooserBookingDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtchooserBookingDatePropertyChange(evt);
            }
        });

        cmboboxNoOfPkgs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        btnCalculateTotalCharge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCalculateTotalCharge.setText("Calculate");
        btnCalculateTotalCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateTotalChargeActionPerformed(evt);
            }
        });

        btnCheckRoomAvailability.setText("Check Availability");
        btnCheckRoomAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckRoomAvailabilityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheckRoomAvailability))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtReservationId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dtchooserBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34)
                                        .addComponent(btnSelectedRooms))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(233, 233, 233)
                                        .addComponent(btnCalculateTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(148, 148, 148)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmboboxNoOfPkgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(btnCancelReservation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNoOfPkgs, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblOutstanding, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(370, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(lblRoomDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(lblCustomerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(775, 775, 775)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnAddReservation)
                                .addGap(12, 12, 12)
                                .addComponent(btnSearchReservation)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateReservation)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearReservation))
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
                                    .addComponent(dtchooserCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(lblManageReservations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnToHome)
                        .addGap(246, 246, 246))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblRoomDetails)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRoomDescription)
                            .addComponent(txtRoomDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCheckRoomAvailability)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSelectedRooms)
                                .addComponent(lblPkgType)
                                .addComponent(cmbobxPkgType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dtchooserBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNoOfPkgs)
                                    .addComponent(cmboboxNoOfPkgs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblReservationFrom)
                                    .addComponent(dtchooserReservationFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBookingDate)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalCharge)
                            .addComponent(txtTotalCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDeposit)
                            .addComponent(txtDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dtchooserReservationTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtchooserCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblReservationTo)
                                .addGap(18, 18, 18)
                                .addComponent(lblCheckinDate)
                                .addGap(0, 7, Short.MAX_VALUE)))))
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateReservation)
                        .addComponent(btnClearReservation)
                        .addComponent(btnCancelReservation)
                        .addComponent(btnCalculateTotalCharge)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddReservationActionPerformed
        saveReservation();
    }//GEN-LAST:event_btnAddReservationActionPerformed

    private void btnSearchReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReservationActionPerformed
        // TODO add your handling code here:
        searchClicked='Y';
        searchReservation();
    }//GEN-LAST:event_btnSearchReservationActionPerformed

    private void btnToHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToHomeActionPerformed
        // TODO add your handling code here:
        new HomeView().close(this);
        new HomeView().setVisible(true);
    }//GEN-LAST:event_btnToHomeActionPerformed

    private void btnCancelReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelReservationActionPerformed
        // TODO add your handling code here:
        cancelReservation();
    }//GEN-LAST:event_btnCancelReservationActionPerformed

    private void btnUpdateReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReservationActionPerformed
        // TODO add your handling code here:
        updateReservation();
    }//GEN-LAST:event_btnUpdateReservationActionPerformed

    private void btnClearReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearReservationActionPerformed
        // TODO add your handling code here:
        searchClicked='N';
        displayClicked='N';
        ClearClicked='N';
        clearFields();
    }//GEN-LAST:event_btnClearReservationActionPerformed

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
        }
        else{
            JOptionPane.showMessageDialog(null, "Already in Selected Room List");
        }
        
    }//GEN-LAST:event_listRoomMouseClicked

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

    private void txtOutstandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutstandingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOutstandingActionPerformed

    private void btnSelectedRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectedRoomsMouseClicked
        // TODO add your handling code here:
        setSelectRooms();
    }//GEN-LAST:event_btnSelectedRoomsMouseClicked

    private void btnSelectedRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectedRoomsActionPerformed
        // TODO add your handling code here:
        displayClicked='N';
    }//GEN-LAST:event_btnSelectedRoomsActionPerformed

    private void dtchooserBookingDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtchooserBookingDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtchooserBookingDatePropertyChange

    private void tblReservationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReservationMouseClicked
        // TODO add your handling code here:
        displayClicked='Y';
        displayReservations();
    }//GEN-LAST:event_tblReservationMouseClicked

    private void txtTotalChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalChargeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalChargeActionPerformed

    private void btnCalculateTotalChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateTotalChargeActionPerformed
        try {
            // TODO add your handling code here:
            setTotalCharge();
        } catch (ParseException ex) {
            Logger.getLogger(ReservationView.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double outstanding=Double.parseDouble(txtTotalCharge.getText())-Double.parseDouble(txtDeposit.getText());
        txtOutstanding.setText(String.valueOf(outstanding));
    }//GEN-LAST:event_btnCalculateTotalChargeActionPerformed

    private void btnCheckRoomAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckRoomAvailabilityActionPerformed
        // TODO add your handling code here:
        if(dtchooserReservationFrom.getDate()!=null || dtchooserReservationTo.getDate()!=null){
            try {
                    checkAvailability(dtchooserReservationTo);
            } catch (ParseException ex) {
                Logger.getLogger(ReservationView.class.getName()).log(Level.SEVERE, null, ex);
            }  
            try {
                checkAvailability(dtchooserReservationFrom);
            } catch (ParseException ex) {
                Logger.getLogger(ReservationView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Input Reservation Dates!!");
        }
 
    }//GEN-LAST:event_btnCheckRoomAvailabilityActionPerformed
    
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
        if(displayClicked=='N')
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
    private void checkAvailability(JDateChooser date) throws ParseException {
        Date selectedDate = formattedDateAndTime(date.getDate(),"yyyy-MM-dd");
        if (date.getDate() != null) {
            List<ReservationDto> dtos = reservationController.getReservationsAboveDate(formattedDateAndTime(new Date(), "yyyy-MM-dd"));
            System.out.println(""+dtos.get(0).getReservationId());
            if (!selectedRooms.isEmpty()) {
                for (int i = 0; i < dtos.size(); i++) {
                    List<String> desList = dtos.get(i).getRoomDescription();
                    Date reservedFrom = dtos.get(i).getReservedFrom();
                    Date reservedTo = dtos.get(i).getReservedTo();
                    System.out.println(i+")reservedFrom-"+reservedFrom);
                    System.out.println(i+")reservedTo-"+reservedTo);
                    System.out.println(i+")selectedDate-"+selectedDate);
                    for (int j = 0; j < desList.size(); j++) {
                        String roomDescription = desList.get(j);
                        if (selectedRooms.contains(roomDescription)) {
                            if (selectedDate.after(dtos.get(i).getReservedFrom()) && selectedDate.before(dtos.get(i).getReservedFrom())) {
                                JOptionPane.showMessageDialog(null, roomDescription + " - already Booked");
                                date.setDate(null);
                                return;
                            } else if (selectedDate.equals(dtos.get(i).getReservedFrom()) || selectedDate.equals(dtos.get(i).getReservedFrom())) {
                                JOptionPane.showMessageDialog(null, roomDescription + " - already Booked");
                                date.setDate(null);
                                return;
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Selected Rooms-Available for booking");
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select room(s)");
                date.setDate(null);
            }
        }
    }

    private Date formattedDateAndTime(Date date,String format) throws ParseException{
        if(date!=null){
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String formattedDateTime = formatter.format(date);
            return formatter.parse(formattedDateTime);  
        }
        else{
            return null;
        }
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
    private javax.swing.JButton btnCalculateTotalCharge;
    private javax.swing.JButton btnCancelReservation;
    private javax.swing.JButton btnCheckRoomAvailability;
    private javax.swing.JButton btnClearReservation;
    private javax.swing.JLabel btnReservationId;
    private javax.swing.JButton btnSearchReservation;
    private javax.swing.JButton btnSelectedRooms;
    private javax.swing.JButton btnToHome;
    private javax.swing.JButton btnUpdateReservation;
    private javax.swing.JComboBox<String> cmboboxNoOfPkgs;
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
                Integer noOfPkgs=Integer.parseInt(cmboboxNoOfPkgs.getSelectedItem().toString());
                Double pkgRate=setPkgRate(pkgType);
                Double ttlCharge=txtTotalCharge.getText().isEmpty()?0.0:Double.parseDouble(txtTotalCharge.getText());
                Double deposit=txtDeposit.getText().isEmpty()?0.0:Double.parseDouble(txtDeposit.getText());
                
                Date bookedDate=new Date();
                Date reservedFrom=formattedDateAndTime(dtchooserReservationFrom.getDate(), "yyyy-MM-dd");
                Date reservedTo=formattedDateAndTime(dtchooserReservationTo.getDate(),"yyyy-MM-dd");
                if(dtchooserCheckin.getDate()!=null)
                    checkIn=new Date(dtchooserCheckin.getDate().getTime());
                if(dtchooserCheckout.getDate()!=null)
                    checkOut=new Date(dtchooserCheckout.getDate().getTime());
                
                ReservationDto reservationDto=new ReservationDto(0,txtCustName.getText(),
                    selectedRooms,bookedDate,reservedFrom,reservedTo,checkIn,checkOut,
                        pkgType,pkgRate,noOfPkgs,extraPayPercentage,ttlCharge,deposit,"No");

                String result=reservationController.saveReservation(reservationDto);
                if(result.equals("succeed")){
                   JOptionPane.showMessageDialog(null, " Saved Reservation Successfully");
                   clearFields();
                   loadReservations();
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
    private void searchReservation(){
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
            
            /*Not added extraPayPercentage*/
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
            cmboboxNoOfPkgs.setSelectedItem(String.valueOf(dto.getNoOfPkgs()));
            txtTotalCharge.setText(String.valueOf(dto.getTotalCharge()));
            txtDeposit.setText(String.valueOf(dto.getDeposit()));
            txtOutstanding.setText(String.valueOf(dto.getTotalCharge()-dto.getDeposit()));
            
            
            CustomerDto cusDto=customerController.getCustomer(Integer.parseInt(dto.getCustomerName().split("-")[0]));
            lblCustomerDetails.setText("Contact : "+cusDto.getContactNo()+" | Email : "+cusDto.getEmail()+" | Address : "+cusDto.getAddress());
            RoomDto rmDto=roomController.getRoom(Integer.parseInt(selectedRooms.get(0).split("-")[0]));
            lblRoomDetails.setText("Category : "+rmDto.getCategory());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Found Reservation Details!");
            clearFields();
        }
    }

    private void updateReservation() {
        ReservationDto dto=new ReservationDto();
        int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?","Update Reservation",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
             if(choice==JOptionPane.YES_OPTION){
                String pkgType=String.valueOf(cmbobxPkgType.getSelectedItem());
                
                dto.setReservationId(Integer.parseInt(txtReservationId.getText()));
                dto.setCustomerName(txtCustName.getText());
                dto.setRoomDescription(selectedRooms);
                dto.setBookingDate(new Date(dtchooserBookingDate.getDate().getTime()));
                dto.setReservedFrom(dtchooserReservationFrom.getDate());
                dto.setReservedTo(dtchooserReservationTo.getDate());
                if(dtchooserCheckin.getDate()!=null)
                    dto.setCheckIn(new Date(dtchooserCheckin.getDate().getTime()));
                if(dtchooserCheckout.getDate()!=null)
                    dto.setCheckOut(new Date(dtchooserCheckout.getDate().getTime()));
                dto.setPackageType(pkgType);
                dto.setPackageRate(setPkgRate(pkgType));
                dto.setNoOfPkgs(Integer.parseInt(cmboboxNoOfPkgs.getSelectedItem().toString()));
                dto.setExtraPayPercentage(extraPayPercentage);
                dto.setTotalCharge(Double.parseDouble(txtTotalCharge.getText()));
                dto.setDeposit(Double.parseDouble(txtDeposit.getText()));
                dto.setCancellation("No");

                String result=reservationController.updateReservation(dto);
                System.out.println("View result-"+result);
                if(result.equals("succeed")){
                    JOptionPane.showMessageDialog(rootPane, "Reservation Updated Successfully");
                    clearFields();
                    loadReservations();
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Reservation Update Failed");
                }
             }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error-Reservation Update");
        } 
    }

    private void clearFields() {
        txtReservationId.setText("");
        txtCustName.setText("");
        lblCustomerDetails.setText("Select a Customer!");
        txtRoomDescription.setText("");
        lblRoomDetails.setText("Select a Room!");
        dtchooserBookingDate.setDate(null);
        dtchooserReservationTo.setDate(null);
        dtchooserReservationFrom.setDate(null);
        dtchooserCheckin.setDate(null);
        dtchooserCheckout.setDate(null);
        cmbobxPkgType.setSelectedItem("Full Board");
        cmboboxNoOfPkgs.setSelectedItem("1");
        txtTotalCharge.setText("");
        txtDeposit.setText("");
        txtOutstanding.setText("");
        selectedRooms=new ArrayList<>();
        selectedRoomMessage="Not Found Selected Rooms!";
    }

    private void loadReservations() {
        try {
            String columns[] = {"Id", "Customer Name", "Room Description", "Booking",
                "Reserevd From","Reserved To","Check In","Check Out",
                "Pkg Type","pkg Rate","No of Pkgs","Total(Rs.)","Deposit(Rs.)","Outstanding(Rs.)"};
            DefaultTableModel dtm = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            tblReservation.setModel(dtm);
            List<ReservationDto> dtos = reservationController.getAllReservations();
            
            
            for (ReservationDto dto : dtos) {
                if(dto.getCancellation().equals("No")){
                    String roomDes="";
                    List<String> roomDescriptionList=dto.getRoomDescription();
                    for (int i = 0; i < roomDescriptionList.size(); i++) {
                        roomDes+=roomDescriptionList.get(i)+",";
                    }
                    Double outstanding=dto.getTotalCharge()-dto.getDeposit();
                    Object[] rowData = {dto.getReservationId(), dto.getCustomerName(),roomDes,
                        dto.getBookingDate(),formattedDateAndTime(dto.getReservedFrom(), "yyyy-MM-dd"), 
                        formattedDateAndTime(dto.getReservedTo(), "yyyy-MM-dd"),dto.getCheckIn(),
                        dto.getCheckOut(),dto.getPackageType(),dto.getPackageRate(),dto.getNoOfPkgs(),dto.getTotalCharge(),
                        dto.getDeposit(),outstanding};
                    dtm.addRow(rowData);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Loading Error-"+e);
        }
    }

    private void displayReservations() {
        if(!txtReservationId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "First Clear Loaded Reservation Details!");
            return;
        }
        int selectedRow=tblReservation.getSelectedRow();
        //{"Id", "Customer Name", "Room Description", "Booking"Reserevd From","Reserevd To","Check In","Check Out",
        //        +"Pkg Type","pkg Rate","No of Pkgs","Total(Rs.)","Deposit","Outstanding"};
        if(selectedRow!=-1){
            DefaultTableModel model=(DefaultTableModel)tblReservation.getModel();
            
            txtReservationId.setText(String.valueOf(model.getValueAt(selectedRow, 0)));
            txtCustName.setText(String.valueOf(model.getValueAt(selectedRow, 1)));
            Integer revId=(Integer)model.getValueAt(selectedRow, 0);
            ReservationDto dto=reservationController.getReservation(revId);
            
            List<String> rmList=dto.getRoomDescription();
            txtRoomDescription.setText(rmList.get(0));
            selectedRooms=rmList;
            setSelectRooms();
            
            dtchooserBookingDate.setDate((Date) model.getValueAt(selectedRow, 3));
            dtchooserReservationFrom.setDate((Date)(model.getValueAt(selectedRow, 4)));
            dtchooserReservationTo.setDate((Date)(model.getValueAt(selectedRow, 5)));
            dtchooserCheckin.setDate((Date)(model.getValueAt(selectedRow, 6)));
            dtchooserCheckout.setDate((Date)(model.getValueAt(selectedRow, 7)));
            cmbobxPkgType.setSelectedItem((model.getValueAt(selectedRow, 8)));
            cmboboxNoOfPkgs.setSelectedItem(String.valueOf(model.getValueAt(selectedRow, 10)));
            txtTotalCharge.setText(String.valueOf(model.getValueAt(selectedRow, 11)));
            txtDeposit.setText(String.valueOf(model.getValueAt(selectedRow, 12)));
            txtOutstanding.setText(String.valueOf(model.getValueAt(selectedRow, 13)));
        }
    }
    private void cancelReservation() {
        int choice=JOptionPane.showConfirmDialog(rootPane, "Are you sure?","Cancel Reservation",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        try {
            if(choice==JOptionPane.YES_OPTION){
                Integer id=Integer.parseInt(txtReservationId.getText());
                Date bookingDate=reservationController.getBookingDate(id);

                System.out.println("bookingDate"+bookingDate);
                Date now=new Date();
                long hours=(now.getTime()-bookingDate.getTime())/(1000*60*60);
                System.out.println("hours-"+hours);
                if(hours<=24){
                    String result=reservationController.cancelReservation(id);
                    JOptionPane.showMessageDialog(null, "Cancelled Reservation Successfully");
                    loadReservations();
                }
                else if(hours>24){
                    JOptionPane.showMessageDialog(null, "exceeded 24 hours. Unable to Cancel Reservation");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Server Error");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error-Cancel Reservation");
        }
            
    }
}
