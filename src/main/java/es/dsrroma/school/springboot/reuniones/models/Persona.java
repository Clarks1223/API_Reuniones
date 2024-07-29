package es.dsrroma.school.springboot.reuniones.models;

import jakarta.persistence.*;

@Entity//indica que es una entidad,
@Table(name = "persona")//hace referencia a una tabla del squema
public class Persona {
    @Id//se marca la clave primaria que sera autogenerado con GenerateValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Los nombres de los atributos coinciden con los nombres de la tabla den squema
    //si no fuera asi deberiamos usar @Column para indicar los nombre
    private long id;
    private String nombre;
    private String apellidos;

    public Persona() {
    }

    public Persona(long id, String nombre, String apellidos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
