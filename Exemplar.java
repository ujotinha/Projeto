import java.util.Scanner;

public class Exemplar {
    private Livro livro;
    private String status = "Disponível", codigo, possuidor="Nenhum";
    Scanner leia = new Scanner(System.in);
    
    public Exemplar(){
        livro = new Livro();
        System.out.println("Informe o código de indentificação do livro: ");
        codigo = leia.nextLine();
    }
    
    public String exibirExemplar(){
        return livro.exibirLivro() + "\nCódigo: "+codigo+"\nStatus: "+status;
    }
    
    public Exemplar(Livro livro, String status, String codigo) {
        this.livro = livro;
        this.status = status;
        this.codigo = codigo;
    }
    
    // Getters e Setters

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }

    public String getPossuidor() {
        return possuidor;
    }

    public void setPossuidor(String possuidor) {
        this.possuidor = possuidor;
    }
    
    
}
