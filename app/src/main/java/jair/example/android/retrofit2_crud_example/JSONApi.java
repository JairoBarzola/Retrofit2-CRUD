package jair.example.android.retrofit2_crud_example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 24-Mar-17.
 */

public class JSONApi {
    private Integer estado;
    private List<Alumno> alumnos = new ArrayList<>();

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

}
