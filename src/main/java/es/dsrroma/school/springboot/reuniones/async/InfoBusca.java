package es.dsrroma.school.springboot.reuniones.async;

public class InfoBusca {
    private long idAsistente;
    private long idReunion;

    public long getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(long idReunion) {
        this.idReunion = idReunion;
    }

    public long getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(long idAsistente) {
        this.idAsistente = idAsistente;
    }
}
