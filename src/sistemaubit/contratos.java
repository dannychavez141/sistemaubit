/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaubit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dahepc
 */
public class contratos extends javax.swing.JInternalFrame {
Connection con = null;
    conexion c = new conexion();
    Statement st;
    public static String idusuario="1";
   List serv=null;
   boolean rclie=false,remple=false,mcontrato=false,econt=false;
    public contratos() {
        initComponents();
         con = c.conectar();
        bcrear.setEnabled(false);
        bmod.setEnabled(false);
        beli.setEnabled(false);
        mrucclie.setEnabled(false);
        mrucemp.setEnabled(false);
        llenarservicios();
        buscar("");
        llenarfechas();
    }
    public void llenarfechas(){
        rinicio.setDate(new Date());
        rfin.setDate(new Date());
    }
    

public void llenarfechas1(String f)
{  
    try {
        //"05/12/2008"
        String fecha =f;
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        Date fechaDate =  formato.parse(fecha);
        mini.setDate(fechaDate);
    } catch (ParseException ex) {
        Logger.getLogger(contratos.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
public void llenarfechas2(String f)
{  
    try {
        //"05/12/2008"
        String fecha =f;
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        Date fechaDate =  formato.parse(fecha);
        mfin.setDate(fechaDate);
    } catch (ParseException ex) {
        Logger.getLogger(contratos.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
   public void llenarservicios()
   {
   try {
      String sql = "SELECT * FROM ubit.servicios c join estado e on c.est=e.idestado where c.descr like '%%';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst(); 
            serv=new ArrayList();
            while (rs.next()) {
                  String cod = rs.getString("idservicios");
                String ser = rs.getString("descr");
             serv.add(cod);
DefaultComboBoxModel modelo=(DefaultComboBoxModel) rserv.getModel();
DefaultComboBoxModel modelo2=(DefaultComboBoxModel) mser.getModel();
                String servi=ser;
               
                modelo.addElement(servi);
                modelo2.addElement(servi);
rserv.setModel(modelo);
mser.setModel(modelo2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   
   }
    public void buscar(String nom)
    {try {
        //CONCADENADO PARA BUSCAR CON LIKE
      String sql = "SELECT c.idcontratos,c.rucclie,cl.razonsocial as cliente,c.rucempr,e.razonsocial as empresa \n" +
",c.idservicio,se.descr,c.comentario,c.pago,c.fechinicio,c.fechafin,es.descrEs \n" +
"FROM ubit.contratos c \n" +
"join clientes cl on c.rucclie=cl.ruc join empresas e on c.rucempr=e.ruc\n" +
" join estado es on c.idestado=es.idestado\n" +
" join servicios se on c.idservicio=se.idservicios\n" +
" where\n" +
" concat(c.idcontratos,c.rucclie,cl.razonsocial,c.rucempr,e.razonsocial,c.fechinicio,c.fechafin) like '%"+nom+"%';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                String idcontra=rs.getString("idcontratos");
                 String rucclie = rs.getString("rucclie");
                String rucempre= rs.getString("rucempr");
                String razclie = rs.getString("cliente");
                String servicio = rs.getString("descr");
                String razempr = rs.getString("empresa");
                String obser = rs.getString("comentario");
                String pago = rs.getString("pago");
                Date inicio=rs.getDate("fechinicio");
                Date fin=rs.getDate("fechafin");
                String est = rs.getString("descrEs");
                String formato = "yyyy-MM-dd";
 SimpleDateFormat sdf = new SimpleDateFormat(formato);
    String finicio=sdf.format(inicio);
    String ffin=sdf.format(fin);
                
                
 DefaultTableModel modelo=(DefaultTableModel) jTable1.getModel();
                Object [] fila=new Object[8];
         fila[0]=idcontra;
        fila[1]=razclie;
        fila[2]=razempr;
        fila[3]=servicio; 
         fila[4]=finicio;  
         fila[5]=ffin;
        fila[6]=pago; 
         fila[7]=est;  
                modelo.addRow(fila);
jTable1.setModel(modelo);
            }
        } catch (SQLException ex) {
                System.out.println("error 1:"+ex.getMessage());
        }

}
public void limpiartabla(){
      jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° DE CONTRATO", "CLIENTE", "EMPRESA", "SERVICIO", "INICIO", "FIN", "PAGO", "ESTADO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(10);
        }
    }
public void limpiarregistro(){
    rbclie.setEnabled(true);
        rbempl.setEnabled(true);
    bcrear.setEnabled(false);
    rrucclie.setEnabled(true);
     rrucempr.setEnabled(true);
    rrucclie.setText("");
    rrucempr.setText("");
    rrazoncliente.setText("-----------------");
        rrazonemp.setText("-----------------");
    rserv.setSelectedIndex(0);
    robs.setText("");
    rmonto.setText("0.00");
            llenarfechas();
            rest.setSelectedIndex(0);
}

public void limpiarmodificar(){

    bmod.setEnabled(false);
    bbcont.setEnabled(true);
    mcont.setText("");
    mcont.setEnabled(true);
    mrucclie.setText("");
    mrucemp.setText("");
    mrazonclie.setText("-----------------");
        mrazonemp.setText("-----------------");
    mser.setSelectedIndex(0);
    mobs.setText("");
    mmonto.setText("0.00");
            llenarfechas();
            mest.setSelectedIndex(0);}
public void limpiareliminar(){
beli.setEnabled(false);
econtrato.setText("");
eclie.setText("--------------------------");
eempresa.setText("-----------------------");
edet.setText("--------------------------");
emont.setText("------------");
bbelim.setEnabled(true);
econtrato.setEnabled(true);

}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bcrear = new javax.swing.JButton();
        blimp1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rrucclie = new javax.swing.JTextField();
        rmonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rest = new javax.swing.JComboBox<>();
        rbclie = new javax.swing.JButton();
        rrazoncliente = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rrazonemp = new javax.swing.JLabel();
        rrucempr = new javax.swing.JTextField();
        rbempl = new javax.swing.JButton();
        rserv = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        robs = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        rinicio = new com.toedter.calendar.JDateChooser();
        rfin = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        mrucclie = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        mrazonclie = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        mrucemp = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        mrazonemp = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        mser = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mobs = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        mmonto = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        mini = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        mfin = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        mest = new javax.swing.JComboBox<>();
        bmod = new javax.swing.JButton();
        blimp2 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        mcont = new javax.swing.JTextField();
        bbcont = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bbelim = new javax.swing.JButton();
        beli = new javax.swing.JButton();
        blim3 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        eclie = new javax.swing.JLabel();
        eempresa = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        edet = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        emont = new javax.swing.JLabel();
        econtrato = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txbuscar = new javax.swing.JTextField();
        bbus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("MODULO CONTRATO");

        jButton8.setText("CERRAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("NUEVO CONTRATO");

        bcrear.setText("CREAR CONTRATO");
        bcrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcrearActionPerformed(evt);
            }
        });

        blimp1.setText("LIMPIAR");
        blimp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blimp1ActionPerformed(evt);
            }
        });

        jLabel3.setText("RUC DE CLIENTE:");

        jLabel4.setText("RAZON SOCIAL CLIENTE:");

        jLabel5.setText("SERVICIOS:");

        rrucclie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rrucclieActionPerformed(evt);
            }
        });

        jLabel8.setText("OBSERVACIONES:");

        jLabel9.setText("ESTADO:");

        rest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO", "CANCELADO" }));

        rbclie.setText("BUSCAR");
        rbclie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbclieActionPerformed(evt);
            }
        });

        rrazoncliente.setText("-------------------------------------------------------");

        jLabel19.setText("RUC DE EMPRESA:");

        jLabel20.setText("RAZON SOCIAL EMPRESA:");

        rrazonemp.setText("-------------------------------------------------------");

        rrucempr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rrucemprActionPerformed(evt);
            }
        });

        rbempl.setText("BUSCAR");
        rbempl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbemplActionPerformed(evt);
            }
        });

        jLabel22.setText("MONTO DE CONTRATO:");

        robs.setColumns(20);
        robs.setRows(2);
        jScrollPane2.setViewportView(robs);

        jLabel23.setText("INICIO DE CONTATO:");

        jLabel24.setText("FIN DE CONTRATO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rrucclie, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(rbclie))
                            .addComponent(rrazoncliente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel9))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rrucempr, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(125, 125, 125)
                                        .addComponent(rbempl))
                                    .addComponent(rrazonemp)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rfin, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(bcrear))
                            .addComponent(rest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addComponent(blimp1)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(409, 409, 409)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(rserv, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rrucclie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbclie))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rrazoncliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(rrucempr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbempl))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rrazonemp))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rserv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addGap(18, 18, 18)
                                .addComponent(rinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel24)
                                .addComponent(rfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bcrear)
                                .addComponent(blimp1))))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CREAR CONTRATO", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setText("MODIFICANDO CONTRATO");

        jLabel26.setText("RUC DE CLIENTE:");

        mrucclie.setEditable(false);
        mrucclie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrucclieActionPerformed(evt);
            }
        });

        jLabel27.setText("RAZON SOCIAL CLIENTE:");

        mrazonclie.setText("-------------------------------------------------------");

        jLabel29.setText("RUC DE EMPRESA:");

        mrucemp.setEditable(false);
        mrucemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrucempActionPerformed(evt);
            }
        });

        jLabel30.setText("RAZON SOCIAL EMPRESA:");

        mrazonemp.setText("-------------------------------------------------------");

        jLabel32.setText("SERVIVIOS:");

        jLabel33.setText("OBSERVACIONES:");

        mobs.setColumns(20);
        mobs.setRows(2);
        jScrollPane3.setViewportView(mobs);

        jLabel34.setText("MONTO DE CONTRATO:");

        jLabel35.setText("INICIO DE CONTATO:");

        jLabel36.setText("FIN DE CONTRATO:");

        jLabel37.setText("ESTADO:");

        mest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        bmod.setText("MODIFICAR CONTRATO");
        bmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmodActionPerformed(evt);
            }
        });

        blimp2.setText("LIMPIAR");
        blimp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blimp2ActionPerformed(evt);
            }
        });

        jLabel38.setText("NRO DE CONTRATO:");

        mcont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcontActionPerformed(evt);
            }
        });

        bbcont.setText("BUSCAR");
        bbcont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbcontActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(374, 374, 374)
                        .addComponent(jLabel25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel37)
                        .addGap(61, 61, 61)
                        .addComponent(mest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 496, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(44, 44, 44)
                                .addComponent(mfin, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel35)
                                    .addGap(44, 44, 44)
                                    .addComponent(mini, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel34)
                                    .addGap(44, 44, 44)
                                    .addComponent(mmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(blimp2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(bmod))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(44, 44, 44)
                            .addComponent(mrucclie, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(198, 198, 198))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(44, 44, 44)
                                        .addComponent(mrazonclie))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel33)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel30))
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mser, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(jLabel38)
                                .addGap(44, 44, 44)
                                .addComponent(mcont, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(bbcont)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addGap(44, 44, 44)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(mrazonemp)
                                .addComponent(mrucemp, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(154, 154, 154))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel25)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(mcont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bbcont))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(mrucclie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(mrazonclie))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(mrucemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(mrazonemp))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(mser, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mini, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(mfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bmod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(blimp2)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        jTabbedPane1.addTab("MODIFICAR CONTRATO", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setText("ELIMINAR CONTRATO");

        jLabel17.setText("NRO DE CONTRATO");

        bbelim.setText("BUSCAR");
        bbelim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbelimActionPerformed(evt);
            }
        });

        beli.setText("ELIMINAR");
        beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliActionPerformed(evt);
            }
        });

        blim3.setText("LIMPIAR");
        blim3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blim3ActionPerformed(evt);
            }
        });

        jLabel39.setText("RAZON SOCIAL CLIENTE:");

        eclie.setText("-------------------------------------------------------");

        eempresa.setText("-------------------------------------------------------");

        jLabel42.setText("RAZON SOCIAL EMPRESA:");

        edet.setText("-------------------------------------------------------");

        jLabel44.setText("DETALLES DE SERVICIO:");

        jLabel45.setText("MONTO:");

        emont.setText("-------------------------------------------------------");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel16)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(424, 424, 424)
                                .addComponent(blim3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(jLabel17)
                                .addGap(75, 75, 75)
                                .addComponent(econtrato, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(beli)
                            .addComponent(bbelim)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(44, 44, 44)
                                .addComponent(eempresa))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(44, 44, 44)
                                .addComponent(eclie))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(44, 44, 44)
                                .addComponent(edet))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(44, 44, 44)
                                .addComponent(emont)))))
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(bbelim, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(econtrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(eclie))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(eempresa))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(edet))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(emont))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blim3)
                    .addComponent(beli))
                .addGap(246, 246, 246))
        );

        jTabbedPane1.addTab("ELIMINAR CONTRATO", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("INGRESE BUSQUEDA:");

        bbus.setText("BUSCAR");
        bbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbusActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° DE CONTRATO", "CLIENTE", "EMPRESA", "SERVICIO", "INICIO", "FIN", "PAGO", "ESTADO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(10);
        }

        jLabel2.setText("DETALLES COMPLETOS DE CONTRATO");

        jLabel10.setText("INGRESE N° DE CONTRATO:");

        jButton1.setText("GENERAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(txbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bbus)
                .addGap(180, 180, 180))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jButton1)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bbus)
                    .addComponent(txbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VISUALIZAR CONTRATOS", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addGap(107, 107, 107))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(483, 483, 483)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jButton8)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bbelimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbelimActionPerformed
       try {
            String cod = econtrato.getText();
            if (cod.equals("")) {
               cod="0";
           }
            String sql = "SELECT c.idcontratos,c.rucclie,cl.razonsocial as cliente,c.rucempr,e.razonsocial as empresa \n" +
",c.idservicio,c.comentario,c.pago,c.fechinicio,c.fechafin,c.idestado\n" +
"FROM ubit.contratos c \n" +
"join clientes cl on c.rucclie=cl.ruc join empresas e on c.rucempr=e.ruc where c.idcontratos="+cod+";";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                String rucclie = rs.getString("rucclie");
                String rucempre= rs.getString("rucempr");
                String razclie = rs.getString("cliente");
                String razempr = rs.getString("empresa");
                String obser = rs.getString("comentario");
                String pago = rs.getString("pago");
                //int idserv = rs.getInt("idservicio");
                //Date inicio=rs.getDate("fechinicio");
               // Date fin=rs.getDate("fechafin");
            //    int est = rs.getInt("idestado");
                eclie.setText(rucclie+"-"+razclie);
                 eempresa.setText(rucempre+"-"+razempr);
                 edet.setText(obser);
                 emont.setText("S/"+pago);
                
                beli.setEnabled(true);
                 econtrato.setEnabled(false);
              bbelim.setEnabled(false);
              econt=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }      
 if (econt==true) {
            JOptionPane.showMessageDialog(rootPane, "CONTRATO ENCONTRADO");
        } else{ econt=false;
                  JOptionPane.showMessageDialog(rootPane, "CONTRATO NO ENCONTRADO");}
    }//GEN-LAST:event_bbelimActionPerformed

    private void rrucclieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rrucclieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rrucclieActionPerformed

    private void rrucemprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rrucemprActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rrucemprActionPerformed

    private void mrucclieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrucclieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mrucclieActionPerformed

    private void mrucempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrucempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mrucempActionPerformed

    private void mcontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcontActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcontActionPerformed

    private void rbclieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbclieActionPerformed
int est=1;
try {
            String ruc = rrucclie.getText();
            String sql = "SELECT * FROM ubit.clientes where ruc='" + ruc + "';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            
            while (rs.next()) {
                String raz = ruc + "-" + rs.getString("razonsocial");
est=2;
                rrazoncliente.setText(raz);
                rbclie.setEnabled(false);
                rrucclie.setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }       
        if (est==2) {
            rclie=true;
            JOptionPane.showMessageDialog(rootPane, "CLIENTE ENCONTRADO");
        } else{JOptionPane.showMessageDialog(rootPane, "CLIENTE NO ENCONTRADO");}
 
    }//GEN-LAST:event_rbclieActionPerformed

    private void rbemplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbemplActionPerformed
   int est=1;
        try {
            String ruc = rrucempr.getText();
            String sql = "SELECT * FROM ubit.empresas where ruc='" + ruc + "';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                String raz = ruc + "-" + rs.getString("razonsocial");
est=2;
                rrazonemp.setText(raz);
                rrucempr.setEnabled(false);
                rbempl.setEnabled(false);
                bcrear.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
              if (est==2) {
            JOptionPane.showMessageDialog(rootPane, "EMPRESA ENCONTRADO");
         remple=true;
        } else{ remple=false;
                  JOptionPane.showMessageDialog(rootPane, "EMPRESA NO ENCONTRADO");}
 
    }//GEN-LAST:event_rbemplActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void bcrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcrearActionPerformed
        if (rclie==true && remple==true) {
            String rucclie=rrucclie.getText();
String  rucempr=rrucempr.getText();
int  idser=rserv.getSelectedIndex();
String servicio=(String) serv.get(idser);
String obs= robs.getText();
String monto=rmonto.getText();
Date inicio=rinicio.getDate();
Date fin=rfin.getDate();
int est = rest.getSelectedIndex() + 1;
String formato = "yyyy-MM-dd";
 SimpleDateFormat sdf = new SimpleDateFormat(formato);
    String finicio=sdf.format(inicio);
    String ffin=sdf.format(fin);
        System.out.println("inicio:"+finicio);
        System.out.println("fin:"+ffin);
        if (rucclie.equals("")|| rucempr.equals("")||servicio.equals("")||obs.equals("")||monto.equals("") ) {
                JOptionPane.showMessageDialog(rootPane, "Verifique que los datos esten llenos");
            } else {
    try {
        String sql = "INSERT INTO `ubit`.`contratos` "
                + "(`rucclie`, `rucempr`, `idservicio`, `comentario`, `pago`, `fechinicio`, `fechafin`, `idusuario`, `idestado`) "
                + "VALUES ('"+rucclie+"', '"+rucempr+"', '"+servicio+"', '"+obs+"', '"+monto+"', '"+finicio+"', '"+ffin+"', '"+"1"+"', '"+est+"');";
        st = con.createStatement();
        st.executeUpdate(sql);
        
        JOptionPane.showMessageDialog(rootPane, "Registrado correctamente");
        limpiartabla();
        buscar("");
              limpiarregistro();
    } catch (SQLException ex) {
        Logger.getLogger(contratos.class.getName()).log(Level.SEVERE, null, ex);
    }
            }
        }else{JOptionPane.showMessageDialog(rootPane, "Por favor Busque a el cliente o a la empresa");}
  
    }//GEN-LAST:event_bcrearActionPerformed

    private void blimp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blimp1ActionPerformed
      limpiarregistro();
    }//GEN-LAST:event_blimp1ActionPerformed

    private void bbcontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbcontActionPerformed
 try {
            String cod = mcont.getText();
            String sql = "SELECT c.idcontratos,c.rucclie,cl.razonsocial as cliente,c.rucempr,e.razonsocial as empresa \n" +
",c.idservicio,c.comentario,c.pago,c.fechinicio,c.fechafin,c.idestado\n" +
"FROM ubit.contratos c \n" +
"join clientes cl on c.rucclie=cl.ruc join empresas e on c.rucempr=e.ruc where c.idcontratos="+cod+";";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                String rucclie = rs.getString("rucclie");
                String rucempre= rs.getString("rucempr");
                String razclie = rs.getString("cliente");
                String razempr = rs.getString("empresa");
                String obser = rs.getString("comentario");
                String pago = rs.getString("pago");
                int idserv = rs.getInt("idservicio");
                Date inicio=rs.getDate("fechinicio");
                Date fin=rs.getDate("fechafin");
                int est = rs.getInt("idestado");
                mrucclie.setText(rucclie);
                mrucemp.setText(rucempre);
                mrazonclie.setText(rucclie+"-"+razclie);
                 mrazonemp.setText(rucempre+"-"+razempr);
                 mobs.setText(obser);
                 mmonto.setText(pago);
                 for (int i = 0; i < serv.size(); i++) {
                     int  id= Integer.parseInt((String) serv.get(i));
                     if (id==idserv ) {
                         idserv=i;
                         i=serv.size();
                     }
                    
                }
                 mini.setDate(inicio);
                 mfin.setDate(fin);
                mser.setSelectedIndex(idserv);
                mest.setSelectedIndex(est - 1);
                bmod.setEnabled(true);
                 mcont.setEnabled(false);
              bbcont.setEnabled(false);
              mrucclie.setEnabled(false);
              mrucemp.setEnabled(false);
              mcontrato=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }      
 if (mcontrato==true) {
            JOptionPane.showMessageDialog(rootPane, "CONTRATO ENCONTRADO");
        } else{ remple=false;
                  JOptionPane.showMessageDialog(rootPane, "CONTRATO NO ENCONTRADO");}
    }//GEN-LAST:event_bbcontActionPerformed

    private void blimp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blimp2ActionPerformed
       limpiarmodificar();
    }//GEN-LAST:event_blimp2ActionPerformed

    private void bmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmodActionPerformed
         if (mcontrato==true) {
             String contrato=mcont.getText();
            String rucclie=mrucclie.getText();
String  rucempr=mrucemp.getText();
int  idser=mser.getSelectedIndex();
String servicio=(String) serv.get(idser);
String obs= mobs.getText();
String monto=mmonto.getText();
Date inicio=mini.getDate();
Date fin=mfin.getDate();
int est = mest.getSelectedIndex() + 1;
String formato = "yyyy-MM-dd";
 SimpleDateFormat sdf = new SimpleDateFormat(formato);
    String finicio=sdf.format(inicio);
    String ffin=sdf.format(fin);
        System.out.println("inicio:"+finicio);
        System.out.println("fin:"+ffin);
        if (rucclie.equals("")|| rucempr.equals("")||servicio.equals("")||obs.equals("")||monto.equals("") ) {
                JOptionPane.showMessageDialog(rootPane, "Verifique que los datos esten llenos");
            } else {
    try {
 
        String sql = "UPDATE `ubit`.`contratos` SET `rucclie`='"+rucclie+"', `rucempr`='"+rucempr+"', "
                + "`idservicio`='"+servicio+"', `comentario`='"+obs+"', "
                + "`pago`='"+monto+"', `fechinicio`='"+finicio+"', `fechafin`='"+ffin+"', `idestado`='"+est+"' "
                + "WHERE `idcontratos`='"+contrato+"';";
        st = con.createStatement();
        st.executeUpdate(sql);
        
        JOptionPane.showMessageDialog(rootPane, "Modificado correctamente");
        limpiartabla();
        buscar("");
              limpiarmodificar();
    } catch (SQLException ex) {
        Logger.getLogger(contratos.class.getName()).log(Level.SEVERE, null, ex);
    }
            }
        }else{JOptionPane.showMessageDialog(rootPane, "Por favor Busque a contrato para modificar");}
    }//GEN-LAST:event_bmodActionPerformed

    private void blim3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blim3ActionPerformed
   limpiareliminar();
    }//GEN-LAST:event_blim3ActionPerformed

    private void beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliActionPerformed
    try {
            String id = econtrato.getText();

            if (id.equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Verifique que los datos esten llenos");
            } else {
                String sql = "DELETE FROM `ubit`.`contratos` WHERE `idcontratos`='" + id + "';";
                st = con.createStatement();
                st.executeUpdate(sql);
                limpiareliminar();
                JOptionPane.showMessageDialog(rootPane, "Eliminado correctamente");
                 limpiartabla();
        buscar("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientes.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        
    }//GEN-LAST:event_beliActionPerformed

    private void bbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbusActionPerformed
      limpiartabla();  
      buscar(txbuscar.getText());
    }//GEN-LAST:event_bbusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String id=codigo.getText();
               if (id.equals("")) {
            id="0";
        }
         detallecontrato de=new detallecontrato();
         de.buscar(id);
         menuprincipal.jDesktopPane1.add(de);
         de.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbcont;
    private javax.swing.JButton bbelim;
    private javax.swing.JButton bbus;
    private javax.swing.JButton bcrear;
    private javax.swing.JButton beli;
    private javax.swing.JButton blim3;
    private javax.swing.JButton blimp1;
    private javax.swing.JButton blimp2;
    private javax.swing.JButton bmod;
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel eclie;
    private javax.swing.JTextField econtrato;
    private javax.swing.JLabel edet;
    private javax.swing.JLabel eempresa;
    private javax.swing.JLabel emont;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mcont;
    private javax.swing.JComboBox<String> mest;
    private com.toedter.calendar.JDateChooser mfin;
    private com.toedter.calendar.JDateChooser mini;
    private javax.swing.JTextField mmonto;
    private javax.swing.JTextArea mobs;
    private javax.swing.JLabel mrazonclie;
    private javax.swing.JLabel mrazonemp;
    private javax.swing.JTextField mrucclie;
    private javax.swing.JTextField mrucemp;
    private javax.swing.JComboBox<String> mser;
    private javax.swing.JButton rbclie;
    private javax.swing.JButton rbempl;
    private javax.swing.JComboBox<String> rest;
    private com.toedter.calendar.JDateChooser rfin;
    private com.toedter.calendar.JDateChooser rinicio;
    private javax.swing.JTextField rmonto;
    private javax.swing.JTextArea robs;
    private javax.swing.JLabel rrazoncliente;
    private javax.swing.JLabel rrazonemp;
    private javax.swing.JTextField rrucclie;
    private javax.swing.JTextField rrucempr;
    private javax.swing.JComboBox<String> rserv;
    private javax.swing.JTextField txbuscar;
    // End of variables declaration//GEN-END:variables
}
