import java.util.Scanner;
public class Principal {
    public static void main(String[] args){
        Scanner leia = new Scanner(System.in);
        int escolha_telainicio,contador_login=0;
        
        String email_login, senha_login;
        
        Biblioteca Space_Team = new Biblioteca();
        Usuario adm1 = new Usuario("Isaque Barbosa Alves","1234567890","senha_confiavel","iba2@aluno.ifal.edu.br",true);
        Space_Team.getUsuarios_biblioteca().add(adm1);
        Usuario adm2 = new Usuario("Emilly Mayara de Brito Santos","0987654321","emmydbs","embs5@aluno.ifal.edu.br",true);
        Space_Team.getUsuarios_biblioteca().add(adm2);
        Usuario adm3 = new Usuario("Maria Vitória dos Santos Paz","24681012","mariapaz","mvsp2@aluno.ifal.edu.br",true);
        Space_Team.getUsuarios_biblioteca().add(adm3);
        Usuario adm4 = new Usuario("Isabela Ferreira Silva","123890","isabela","ifs6@aluno.ifal.edu.br",true);
        Space_Team.getUsuarios_biblioteca().add(adm4);
        Usuario adm5 = new Usuario("João Pedro da Conceição Araújo","1234560","jota","jpca6@aluno.ifal.edu.br",true);
        Space_Team.getUsuarios_biblioteca().add(adm5);
        
        while(true){
            System.out.println("| TELA DE INÍCIO |");
            System.out.println("Deseja realizar um login ou se cadastrar? (Login - 1; Cadastrar - 2; Encerrar aplicação - 3)");
            escolha_telainicio = leia.nextInt();
            if(escolha_telainicio == 2){
                Space_Team.cadastrarUsuarios();
                Space_Team.menuBiblioteca((Space_Team.getUsuarios_biblioteca().size())-1);
            }
            else if(escolha_telainicio == 1){
                System.out.println("Informe o email institucional: ");
                email_login = leia.next();
                System.out.println("Informe a senha: ");
                senha_login = leia.next();
                for (int i = 0; i < Space_Team.getUsuarios_biblioteca().size(); i++) {
                    if(email_login.equals(Space_Team.getUsuarios_biblioteca().get(i).getEmailinstitucional()) && senha_login.equals(Space_Team.getUsuarios_biblioteca().get(i).getSenha())){
                        System.out.println("Login realizado com sucesso");
                        Space_Team.menuBiblioteca(i);
                        contador_login = 0;
                        break;
                    }
                    contador_login++;
                }
                if(contador_login == Space_Team.getUsuarios_biblioteca().size()){
                    System.out.println("Email ou senha inválido!");
                    contador_login = 0;
                }
            }
            else if(escolha_telainicio == 3){
                System.exit(0);
            }
            else{
                System.out.println("Insira uma opção válida.");
            }
        }
    }
}
