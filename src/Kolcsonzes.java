public class Kolcsonzes {
    String name;
    String Jazon;
    int Eora;
    int Eperc;
    int Vora;
    int Vperc;

    public Kolcsonzes(String name, String Jazon, int Eora, int Eperc, int Vora, int Vperc) {
        this.name = name;
        this.Jazon = Jazon;
        this.Eora = Eora;
        this.Eperc = Eperc;
        this.Vora = Vora;
        this.Vperc = Vperc;
    }


    public String getName() {
        return name;

    }

    public String getJazon() {
        return Jazon;
    }

    public  int getEora() {
        return Eora;
    }
}
