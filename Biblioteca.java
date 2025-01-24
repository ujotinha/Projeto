import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class Biblioteca {

    private ArrayList<Exemplar> exemplares_catalogo = new ArrayList<Exemplar>();
    private ArrayList<Usuario> usuarios_biblioteca = new ArrayList<Usuario>();
    Scanner leia = new Scanner(System.in);
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = (c.get(Calendar.MONTH)+1);
    private int dia_do_mes = c.get(Calendar.DAY_OF_MONTH);

    //Inserindo exemplares no catálogo
    public void inserindoExemplaresCatalogo() {
        Exemplar novo_exemplar = new Exemplar();
        exemplares_catalogo.add(novo_exemplar);
    }

    //Remover exemplar no catálogo
    public void removerExemplarCatalogo() {
        String remover_exemplar;
        int codigo_nao_encontrado = 0;
        System.out.println("Digite o código do exemplar que deseja remover: ");
        remover_exemplar = leia.next();
        for (int i = 0; i < exemplares_catalogo.size(); i++) {
            if (remover_exemplar.equals(exemplares_catalogo.get(i).getCodigo())) {
                exemplares_catalogo.remove(i);
                System.out.println("Remoção feita com sucesso!!");
                codigo_nao_encontrado = 0;
                break;
            }
            codigo_nao_encontrado++;
        }
        if (codigo_nao_encontrado == exemplares_catalogo.size()) {
            System.out.println("Exemplar não encontrado!!");
            codigo_nao_encontrado = 0;
        }
    }

    // Registrando usuários na biblioteca
    public void cadastrarUsuarios() {
        Usuario novo_usuario = new Usuario();
        usuarios_biblioteca.add(novo_usuario);
    }

    public void cadastrarUsuariosAdm() {
        Usuario novo_usuario_adm = new Usuario(true);
        usuarios_biblioteca.add(novo_usuario_adm);
    }

    public void exibirCatalogo() {
        if (!exemplares_catalogo.isEmpty()) {
            for (int i = 0; i < exemplares_catalogo.size(); i++) {
                System.out.println((i + 1) + "°:\n\n" + exemplares_catalogo.get(i).exibirExemplar() + "\n");
            }
        } else {
            System.out.println("Não há nenhum exemplar no catálogo");
        }
    }
    
    public void calcularMulta(){
        
        
        for (int i = 0; i < usuarios_biblioteca.size(); i++) {
            for (int j = 0; j < usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().size(); j++) {
                int dia_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getDia_do_mes_aux());
                int mes_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getMes_aux());
                int ano_multa = Integer.parseInt(usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).getAno_aux());
                
                if(ano_multa == ano && mes_multa < mes && usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado() == false){
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                }
                else if(ano_multa == ano && mes_multa == mes && dia_multa < dia_do_mes && usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado() == false){
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                }
                else if(ano_multa < ano && usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).isMultado() == false){
                    usuarios_biblioteca.get(i).getHistorico().aplicarMulta(10);
                    usuarios_biblioteca.get(i).getHistorico().getEmprestimos_realizados().get(j).setMultado(true);
                }
            }
        }
    }
    
    public int menuBiblioteca(int usuario) {
        int acao_menu, acao_gerencia, acao_gerencia_cat, acao_catalogo, codigo_nao_encontrado = 0;
        String codigo_emprestimo, codigo_devolucao;
        if (usuarios_biblioteca.get(usuario).isAdm() == false) { // parte dos usuários
            while (true) {
                System.out.println("Seja bem-vindo à Biblioteca Space!");
                System.out.println("(1) - Gerenciar Conta");
                System.out.println("(2) - Abrir Catálogo");
                System.out.println("(3) - Sair");
                System.out.println("Indique sua ação: ");
                acao_menu = leia.nextInt();
                while (true) {
                    if (acao_menu == 1) {
                        calcularMulta();
                        System.out.println("| GERENCIAR CONTA |");
                        System.out.println("Nome: " + usuarios_biblioteca.get(usuario).getNome());
                        System.out.println("Email: " + usuarios_biblioteca.get(usuario).getEmailinstitucional());
                        System.out.println("(1) - Atualizar credenciais da conta");
                        System.out.println("(2) - Histórico de Empréstimos");
                        System.out.println("(3) - Voltar");
                        System.out.println("Indique sua ação: ");
                        acao_gerencia = leia.nextInt();
                        if (acao_gerencia == 1) {
                            calcularMulta();
                            usuarios_biblioteca.get(usuario).atualizarUsuario();
                        } else if (acao_gerencia == 2) {
                            calcularMulta();
                            if (usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size() == 0) {
                                System.out.println("Não há nenhum empréstimo");
                            } else {
                                for (int i = 0; i < usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size(); i++) {
                                    System.out.println("| EMPRÉSTIMOS |");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).exibirEmprestimo();
                                    if(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getStatus().equals("Devolvido")){
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).exibirDataDev();
                                    }
                                }
                                System.out.println("Tolta em multa: " + usuarios_biblioteca.get(usuario).getHistorico().getMulta());
                            }
                        } else if (acao_gerencia == 3) {
                            calcularMulta();
                            break;
                        } else {
                            calcularMulta();
                            System.out.println("Opção inválida!");
                        }
                    } else if (acao_menu == 2) {
                        calcularMulta();
                        exibirCatalogo();
                        System.out.println("(1) - Realizar empréstimo");
                        System.out.println("(2) - Devolver livro");
                        System.out.println("(3) - Voltar");
                        System.out.println("Informe sua ação: ");
                        acao_catalogo = leia.nextInt();
                        if (acao_catalogo == 1) {
                            calcularMulta();
                            if (!exemplares_catalogo.isEmpty()) {
                                calcularMulta();
                                System.out.println("Informe o código do livro que deseja realizar o empréstimo: ");
                                codigo_emprestimo = leia.next();
                                for (int i = 0; i < exemplares_catalogo.size(); i++) {
                                    if (codigo_emprestimo.equals(exemplares_catalogo.get(i).getCodigo()) && exemplares_catalogo.get(i).getStatus().equals("Disponível")) {
                                        // Empréstimo do exemplar informado pelo usuário
                                        Emprestimo emprestimo_realizado = new Emprestimo(exemplares_catalogo.get(i));
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().add(emprestimo_realizado);
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().setPossuidor(usuarios_biblioteca.get(usuario).getNome());
                                        exemplares_catalogo.get(i).setStatus("Indisponível");
                                        System.out.println("Empréstimo realizado com sucesso !!");
                                        codigo_nao_encontrado = 0;
                                        break;
                                    }
                                    codigo_nao_encontrado++;
                                }
                                if (codigo_nao_encontrado == exemplares_catalogo.size()) {
                                    System.out.println("Exemplar não encontrado ou o exemplar está indisponível!!");
                                    codigo_nao_encontrado = 0;
                                }
                            } else {
                                System.out.println("Impossível realizar empréstimo no momento. O catálogo se encontra vazio!!");
                            }
                        } else if (acao_catalogo == 2) {
                            calcularMulta();
                            System.out.println("Informe o código do livro que deseja: ");
                            codigo_devolucao = leia.next();
                            for (int i = 0; i < usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size(); i++) {
                                if(codigo_devolucao.equals(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().getCodigo()) && usuarios_biblioteca.get(usuario).getNome().equals(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().getPossuidor())){
                                    exemplares_catalogo.get(i).setStatus("Disponível");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setStatus("Devolvido");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().setPossuidor("Nenhum");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setAno_dev(ano);
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setMes_dev(mes);
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setDia_dev(dia_do_mes);
                                    codigo_nao_encontrado = 0;
                                    break;
                                }
                                codigo_nao_encontrado++;
                            }
                            if(codigo_nao_encontrado == usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size()){
                                System.out.println("Exemplar não encontrado!!");
                                codigo_nao_encontrado = 0;
                            }
                           
                        } else if (acao_catalogo == 3) {
                            calcularMulta();
                            break;
                        } else {
                            calcularMulta();
                            System.out.println("Opção inválida!!");
                        }
                    } else if (acao_menu == 3) {
                        calcularMulta();
                        return 0;
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }
            }
        } else { // parte dos administradores
            while (true) {
                System.out.println("Seja bem-vindo à biblioteca Space!");
                System.out.println("(1) - Gerenciar Conta");
                System.out.println("(2) - Abrir Catálogo");
                System.out.println("(3) - Gerenciar Catálogo");
                System.out.println("(4) - Sair");
                System.out.println("Indique sua ação: ");
                acao_menu = leia.nextInt();
                while (true) {
                    if (acao_menu == 1) {
                        calcularMulta();
                        System.out.println("| GERENCIAR CONTA |");
                        System.out.println("Nome: " + usuarios_biblioteca.get(usuario).getNome());
                        System.out.println("Email: " + usuarios_biblioteca.get(usuario).getEmailinstitucional());
                        System.out.println("(1) - Atualizar credenciais da conta");
                        System.out.println("(2) - Histórico de Empréstimos");
                        System.out.println("(3) - Voltar");
                        System.out.println("Indique sua ação: ");
                        acao_gerencia = leia.nextInt();
                        if (acao_gerencia == 1) {
                            calcularMulta();
                            usuarios_biblioteca.get(usuario).atualizarUsuario();
                        } else if (acao_gerencia == 2) {
                            calcularMulta();
                            if (usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size() == 0) {
                                System.out.println("Não há nenhum empréstimo");
                            } else {
                                for (int i = 0; i < usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size(); i++) {
                                    System.out.println("| EMPRÉSTIMOS |");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).exibirEmprestimo();
                                    if(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getStatus().equals("Devolvido")){
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).exibirDataDev();
                                    }
                                   
                                }
                                System.out.println("Tolta em multa: " + usuarios_biblioteca.get(usuario).getHistorico().getMulta());
                            }
                        } else if (acao_gerencia == 3) {
                            calcularMulta();
                            break;
                        } else {
                            calcularMulta();
                            System.out.println("Opção inválida!");
                        }
                    } else if (acao_menu == 2) {
                        calcularMulta();
                        exibirCatalogo();
                        System.out.println("(1) - Realizar empréstimo");
                        System.out.println("(2) - Devolver livro");
                        System.out.println("(3) - Voltar");
                        System.out.println("Informe sua ação: ");
                        acao_catalogo = leia.nextInt();
                        if (acao_catalogo == 1) {
                            calcularMulta();
                            if (!exemplares_catalogo.isEmpty()) {
                                System.out.println("Informe o código do livro que deseja realizar o empréstimo: ");
                                codigo_emprestimo = leia.next();
                                for (int i = 0; i < exemplares_catalogo.size(); i++) {
                                    if (codigo_emprestimo.equals(exemplares_catalogo.get(i).getCodigo()) && exemplares_catalogo.get(i).getStatus().equals("Disponível")) {
                                        // Empréstimo do exemplar informado pelo usuário
                                        Emprestimo emprestimo_realizado = new Emprestimo(exemplares_catalogo.get(i));
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().add(emprestimo_realizado);
                                        usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().setPossuidor(usuarios_biblioteca.get(usuario).getNome());
                                        exemplares_catalogo.get(i).setStatus("Indisponível");
                                        System.out.println("Empréstimo realizado com sucesso !!");
                                        codigo_nao_encontrado = 0;
                                        break;
                                    }
                                    codigo_nao_encontrado++;
                                }
                                if (codigo_nao_encontrado == exemplares_catalogo.size()) {
                                    System.out.println("Exemplar não encontrado ou o exemplar está indisponível!!");
                                    codigo_nao_encontrado = 0;
                                }
                            } else {
                                System.out.println("Impossível realizar empréstimo no momento. O catálogo se encontra vazio!!");
                            }
                        } else if (acao_catalogo == 2) {
                            calcularMulta();
                            System.out.println("Informe o código do livro que deseja devolver: ");
                            codigo_devolucao = leia.next();
                            for (int i = 0; i < usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size(); i++) {
                                if(codigo_devolucao.equals(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().getCodigo()) && usuarios_biblioteca.get(usuario).getNome().equals(usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().getPossuidor())){
                                    exemplares_catalogo.get(i).setStatus("Disponível");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).getExemplar().setPossuidor("Nenhum");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setStatus("Devolvido");
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setAno_dev(ano);
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setMes_dev(mes);
                                    usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().get(i).setDia_dev(dia_do_mes);
                                    System.out.println("Devolução feita com sucesso!!");
                                    codigo_nao_encontrado = 0;
                                    break;
                                }
                                codigo_nao_encontrado++;
                            }
                            if(codigo_nao_encontrado == usuarios_biblioteca.get(usuario).getHistorico().getEmprestimos_realizados().size()){
                                System.out.println("Exemplar não encontrado!!");
                                codigo_nao_encontrado = 0;
                            }
                        } else if (acao_catalogo == 3) {
                            calcularMulta();
                            break;
                        } else {
                            calcularMulta();
                            System.out.println("Opção inválida!!");
                        }
                    } else if (acao_menu == 3) {
                        calcularMulta();
                        System.out.println("| GERENCIAR CATÁLOGO |");
                        System.out.println("(1) - Adicionar exemplar no catálogo");
                        System.out.println("(2) - Remover exemplar do catálogo");
                        System.out.println("(3) - Voltar");
                        System.out.println("Informe sua ação: ");
                        acao_gerencia_cat = leia.nextInt();
                        if (acao_gerencia_cat == 1) {
                            calcularMulta();
                            inserindoExemplaresCatalogo();
                        } else if (acao_gerencia_cat == 2) {
                            calcularMulta();
                            exibirCatalogo();
                            removerExemplarCatalogo();
                        } else if (acao_gerencia_cat == 3) {
                            calcularMulta();
                            break;
                        }
                    } else if (acao_menu == 4) {
                        calcularMulta();
                        return 0;
                    } else {
                        calcularMulta();
                        System.out.println("Opção inválida!");
                    }
                }
            }
        }
    }

    // Getters e Setters
    public ArrayList<Exemplar> getExemplares_catalogo() {
        return exemplares_catalogo;
    }

    public void setExemplares_catalogo(ArrayList<Exemplar> exemplares_catalogo) {
        this.exemplares_catalogo = exemplares_catalogo;
    }

    public ArrayList<Usuario> getUsuarios_biblioteca() {
        return usuarios_biblioteca;
    }

    public void setUsuarios_biblioteca(ArrayList<Usuario> usuarios_biblioteca) {
        this.usuarios_biblioteca = usuarios_biblioteca;
    }

    public Scanner getLeia() {
        return leia;
    }

    public void setLeia(Scanner leia) {
        this.leia = leia;
    }

}