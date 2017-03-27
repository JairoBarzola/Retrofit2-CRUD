package jair.example.android.retrofit2_crud_example;

/**
 * Created by Jair Barzola on 24-Mar-17.
 */

public class Alumno {

    private String idalumno;
    private String nombre;
    private String direccion;
    public Alumno (String nombre, String direccion){
        this.nombre=nombre;
        this.direccion=direccion;
    }
    public Alumno (String idalumno){
        this.idalumno=idalumno;
    }
    public Alumno (String idalumno,String nombre, String direccion){
        this.idalumno=idalumno;
        this.nombre=nombre;
        this.direccion=direccion;
    }

    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}