package beans;

public class permisoBeans {
    int idPermiso;
    String idUsuario;
    String tipo;
    String exp;
    int idTurno;
    String prueba;

    public permisoBeans(int idPermiso, String idUsuario, String tipo, String exp, int idTurno, String prueba) {
        this.idPermiso = idPermiso;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.exp = exp;
        this.idTurno = idTurno;
        this.prueba = prueba;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    
}
