package modelo;

public class Inscripcion {
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;
    private Curso curso;

    public Inscripcion(String nombre, String apellido, int edad, String telefono, Curso curso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getTelefono() { return telefono; }
    public Curso getCurso() { return curso; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getDatos() {
        return getNombreCompleto() + " - Edad: " + edad + " - Tel: " + telefono + " - Curso: " + curso.getNombre();
    }
}
