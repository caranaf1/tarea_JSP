/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author caranaf1
 */
public class Puesto {
    private int id_tipo_sangre;
    private String sangre;
    Conexion cn;
    public Sangre(){}
    public Sangre(int id_tipo_sangre, String puesto) {
        this.id_tipo_sangre = id_tipo_sangre;
        this.puesto = puesto;
    }

    public int getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangreo(int id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }
    public HashMap drop_sangre(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="Select id_tipo_sangre as id,sangre from sangre";
         cn = new Conexion();
         cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("sangre") );
            }
         cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
    
}
