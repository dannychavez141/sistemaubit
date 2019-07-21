package sistemaubit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dahepc
 */
public class detallecontrato extends javax.swing.JInternalFrame {
static Connection con = null;
    static conexion c = new conexion();
    static Statement st;
    public detallecontrato() {
        initComponents();
        con = c.conectar();
    }
public  static  void buscar(String id)
    {try {
        //CONCADENADO PARA BUSCAR CON LIKE
      String sql = "SELECT c.idcontratos,c.rucclie,cl.razonsocial as cliente,c.rucempr,e.razonsocial as empresa \n" +
",c.idservicio,se.descr,c.comentario,c.pago,c.fechinicio,c.fechafin,es.descrEs \n" +
"FROM ubit.contratos c \n" +
"join clientes cl on c.rucclie=cl.ruc join empresas e on c.rucempr=e.ruc\n" +
" join estado es on c.idestado=es.idestado\n" +
" join servicios se on c.idservicio=se.idservicios\n" +
" where\n" +
" c.idcontratos= '"+id+"';";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                String idcontra=rs.getString("idcontratos");
                 String rucclie = rs.getString("rucclie");
                String rucempre= rs.getString("rucempr");
                String razclie = rs.getString("cliente");
                String servi = rs.getString("descr");
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
    cliente.setText(rucclie+" "+razclie );
    empresa.setText(rucempre+" "+razempr );
servicio.setText(servi);
observ.setText(obser);
monto.setText("S/"+pago);
txtini.setText(finicio);
txtfin.setText(ffin);
ncont.setText(idcontra);
           
            }
        } catch (SQLException ex) {
                System.out.println("error 1:"+ex.getMessage());
        }

}
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel45 = new javax.swing.JLabel();
        monto = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        servicio = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        empresa = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtini = new javax.swing.JLabel();
        txtfin = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        ncont = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        observ = new javax.swing.JLabel();

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("MONTO:");

        monto.setText("-------------------------------------------------------");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("DETALLES DE SERVICIO:");

        servicio.setText("-------------------------------------------------------------------------------------------------------");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("RUC Y RAZON SOCIAL EMPRESA:");

        empresa.setText("-------------------------------------------------------");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("RUC Y RAZON SOCIAL CLIENTE:");

        cliente.setText("-------------------------------------------------------");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("INICIO DEL CONTRATO:");

        txtini.setText("--------------------------------------------------");

        txtfin.setText("--------------------------------------------------");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("FIN DEL CONTRATO:");

        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("REPORTE CONTRATO");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("N° DE CONTRATO:");

        ncont.setText("-------------------------------------------------------");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("OBSERVACIONES:");

        observ.setText("-------------------------------------------------------------------------------------------------------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(67, 67, 67))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(monto)
                        .addGap(132, 132, 132))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(ncont))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(servicio)
                                .addComponent(jLabel44)
                                .addComponent(jLabel42)
                                .addComponent(cliente)
                                .addComponent(jLabel1)
                                .addComponent(txtini)
                                .addComponent(jLabel4)
                                .addComponent(txtfin)
                                .addComponent(empresa)
                                .addComponent(jLabel39)
                                .addComponent(observ)
                                .addComponent(jLabel46)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(ncont))
                .addGap(25, 25, 25)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cliente)
                .addGap(28, 28, 28)
                .addComponent(jLabel42)
                .addGap(18, 18, 18)
                .addComponent(empresa)
                .addGap(22, 22, 22)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addComponent(servicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addComponent(observ)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtini)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtfin)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(monto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel cliente;
    public static javax.swing.JLabel empresa;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel monto;
    public static javax.swing.JLabel ncont;
    public static javax.swing.JLabel observ;
    public static javax.swing.JLabel servicio;
    public static javax.swing.JLabel txtfin;
    public static javax.swing.JLabel txtini;
    // End of variables declaration//GEN-END:variables
}
