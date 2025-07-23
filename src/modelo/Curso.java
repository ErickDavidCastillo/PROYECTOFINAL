package modelo;

public class Curso {
    private int id;
    private String nombre;
    private String horario;

    public Curso(String nombre, String horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    public Curso(int id, String nombre, String horario) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return nombre + " (" + horario + ")";
    }
}
