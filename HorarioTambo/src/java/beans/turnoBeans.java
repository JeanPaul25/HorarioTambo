package beans;

public class turnoBeans {
    int idTurno, dia, horarioI, horarioF;

    public turnoBeans(int idTurno, int dia, int horarioI, int horarioF) {
        this.idTurno = idTurno;
        this.dia = dia;
        this.horarioI = horarioI;
        this.horarioF = horarioF;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHorarioI() {
        return horarioI;
    }

    public void setHorarioI(int horarioI) {
        this.horarioI = horarioI;
    }

    public int getHorarioF() {
        return horarioF;
    }

    public void setHorarioF(int horarioF) {
        this.horarioF = horarioF;
    }

    
}
