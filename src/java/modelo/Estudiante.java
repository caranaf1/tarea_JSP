/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

        
/**
 *
 * @author caranaf1
 */
public class Estudiante extends Persona {
    private String carnet;
    private int id_tipo_sangre;
    Conexion cn;
    public Estudiante() {}

    public Estudiante(String carnet, int id_tipo_sangre, int id, String nombres, String apellidos, String direccion, String telefono,String correo_electronico, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, correo_electronico,fecha_nacimiento);
        this.carnet = carnet;
        this.id_tipo_sangre = id_tipo_sangre;
    }

    

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(int id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }
 public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "SELECT e.id_tipo_sangre as id,e.carnet,e.nombres,e.apellidos,e.direccion,e.telefono,e.correo_electronico,e.fecha_nacimiento,p.puesto,p.id_puesto FROM empleados as e inner join puestos as p on e.id_puesto = p.id_puesto;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","codigo","nombres","apellidos","direccion","telefono","correo_electronico,"nacimiento","sangre","id_tipo_sangre"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("carnet");
          datos[2] = consulta.getString("nombres");
          datos[3] = consulta.getString("apellidos");
          datos[4] = consulta.getString("direccion");
          datos[5] = consulta.getString("telefono");
          datos[6] = consulta.getString("correo_electronico");
          datos[7] = consulta.getString("fecha_nacimiento");
          datos[8] = consulta.getString("sangre");
          datos[9] = consulta.getString("id_tipo_sangre");
          tabla.addRow(datos);
      
      }
      
     cn.cerrar_conexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
    @Override
    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into estudiantes(carnet,nombres,apellidos,direccion,telefono,correo_electronico,fecha_nacimiento,id_tipo_sangre) values(?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCodigo());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFecha_nacimiento());
            parametro.setInt(8, getId_tipo_sangre());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    @Override
    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update empleados set carnet = ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,correo_electronico=?,fecha_nacimiento= ?,id_puesto= ? where id_empleado = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCodigo());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getCorreo_electronico());
            parametro.setString(7,getFecha_nacimiento());
            parametro.setInt(8, getId_puesto());
            parametro.setInt(9, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    @Override
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from estudiantes  where id_estudiante = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
   
}
