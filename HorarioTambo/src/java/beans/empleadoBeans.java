package beans;

public class empleadoBeans {
    String idEmpleado;
    int dni;
    String tipo;
    String password;
    String nombres;
    String apellidos;

    public empleadoBeans(String idEmpleado, int dni, String tipo, String password, String nombres, String apellidos) {
        this.idEmpleado = idEmpleado;
        this.dni = dni;
        this.tipo = tipo;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
}
