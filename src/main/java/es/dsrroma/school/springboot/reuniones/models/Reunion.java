package es.dsrroma.school.springboot.reuniones.models;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reunion")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String asunto;
    private ZonedDateTime fecha;
    //defino el tipo de relacion m:m
    @ManyToMany
    //Definir el nombre de la tabla y las columnas
    @JoinTable(name = "asistente",//nombre de la tabla
            joinColumns = {@JoinColumn(name = "reunion_id")},//nombre de las columnnas
            inverseJoinColumns = {@JoinColumn(name = "persona_id")})
    private List<Persona> asistentes = new ArrayList<>();

    /*la lista asistente no se agrega al constructor, se crea un get y un add*/
    public Reunion() {

    }

    public Reunion(long id, String asunto, ZonedDateTime fecha) {
        this.id = id;
        this.asunto = asunto;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public String getAsunto() {
        return asunto;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Persona> getAsistentes() {
        return asistentes;
    }

    public void addAsistente(Persona asistente) {
        asistentes.add(asistente);
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
