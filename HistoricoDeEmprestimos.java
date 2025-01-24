import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;

public class HistoricoDeEmprestimos {
    private double multa = 0;
    private ArrayList<Emprestimo> emprestimos_realizados = new ArrayList<Emprestimo>();
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = (c.get(Calendar.MONTH)+1);
    private int dia_do_mes = c.get(Calendar.DAY_OF_MONTH);
    Scanner leia = new Scanner(System.in);
    
    
    
    
    //Getter e Setters

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
    
    public void aplicarMulta(double multa){
        this.multa += multa;
    }
    
    public ArrayList<Emprestimo> getEmprestimos_realizados() {
        return emprestimos_realizados;
    }

    public void setEmprestimos_realizados(ArrayList<Emprestimo> emprestimos_realizados) {
        this.emprestimos_realizados = emprestimos_realizados;
    }

    public Calendar getC() {
        return c;
    }

    public void setC(Calendar c) {
        this.c = c;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia_do_mes() {
        return dia_do_mes;
    }

    public void setDia_do_mes(int dia_do_mes) {
        this.dia_do_mes = dia_do_mes;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }
    
}
