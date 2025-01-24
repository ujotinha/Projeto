import java.util.Calendar;
import java.util.Scanner;

public class Emprestimo {
    private String dataEmprestimo, status, dia_do_mes_aux,mes_aux,ano_aux;
    private int dia_dev,mes_dev,ano_dev;
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = (c.get(Calendar.MONTH)+1);
    private int dia_do_mes = c.get(Calendar.DAY_OF_MONTH); 
    private String dataDevolucao;
    private Exemplar exemplar;
    private boolean multado = false;
    Scanner leia = new Scanner(System.in);
    
    public Emprestimo(Exemplar exemplar_emprestado){
        if(dia_do_mes < 10){
            dia_do_mes = 0+c.get(Calendar.DAY_OF_MONTH);
        }
        if(mes < 10){
            mes = 0+(c.get(Calendar.MONTH)+1);
        }
        dataEmprestimo = dia_do_mes + "/" + mes + "/" + ano;
        System.out.println("\n!!! Detalhes do Empréstimo !!!");
        System.out.println("Data do empréstimo: "+dataEmprestimo);
        System.out.println("Digite a data da devolução:");
        dataDevolucao = leia.next();
        status = "Em andamento";
        exemplar = exemplar_emprestado;
        dia_do_mes_aux = dataDevolucao.substring(0,2);
        mes_aux = dataDevolucao.substring(3,5);
        ano_aux = dataDevolucao.substring(6,10);
        System.out.println("TESTE:"+dia_do_mes_aux);
        System.out.println("TESTE:"+mes_aux);
        System.out.println("TESTE:"+ano_aux);
    }
    
    public void exibirEmprestimo(){
        System.out.println("Data do empréstimo: "+dataEmprestimo);
        System.out.println("Data de Devolução Prevista: "+dataDevolucao);
        System.out.println("Status: "+status);
        System.out.println("Livro: "+exemplar.getLivro().getTitulo());
    }
    
    public void exibirDataDev(){
        System.out.println("Data de Devolução: "+dia_dev+"/"+mes_dev+"/"+ano_dev);
    }
    
    // Getters e Setters

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }

    public String getDia_do_mes_aux() {
        return dia_do_mes_aux;
    }

    public void setDia_do_mes_aux(String dia_do_mes_aux) {
        this.dia_do_mes_aux = dia_do_mes_aux;
    }

    public String getMes_aux() {
        return mes_aux;
    }

    public void setMes_aux(String mes_aux) {
        this.mes_aux = mes_aux;
    }

    public String getAno_aux() {
        return ano_aux;
    }

    public void setAno_aux(String ano_aux) {
        this.ano_aux = ano_aux;
    }

    public boolean isMultado() {
        return multado;
    }

    public void setMultado(boolean multado) {
        this.multado = multado;
    }

    public int getDia_dev() {
        return dia_dev;
    }

    public void setDia_dev(int dia_dev) {
        this.dia_dev = dia_dev;
    }

    public int getMes_dev() {
        return mes_dev;
    }

    public void setMes_dev(int mes_dev) {
        this.mes_dev = mes_dev;
    }

    public int getAno_dev() {
        return ano_dev;
    }

    public void setAno_dev(int ano_dev) {
        this.ano_dev = ano_dev;
    }


    
    
}
