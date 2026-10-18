import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarPadrao();
                    break;
                case 2:
                    cadastrarComissionado();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    gerarFolhaPagamento();
                    break;
                case 5:
                    exibirResumo();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção de 0 a 5.");
            }
        } while (opcao != 0);

        teclado.close();
    }

    static void exibirMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE FOLHA DE PAGAMENTO =====");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair do Programa");
    }

    static void cadastrarPadrao() {
        System.out.println("\n--- Cadastro de Colaborador Padrão ---");
        int matricula = lerMatricula();
        String nome = lerNome();

        colaboradores.add(new ColaboradorPadrao(matricula, nome));
        System.out.println("Colaborador padrão cadastrado com sucesso!");
    }

    static void cadastrarComissionado() {
        System.out.println("\n--- Cadastro de Colaborador Comissionado ---");
        int matricula = lerMatricula();
        String nome = lerNome();

        double valorVendas = lerDecimal("Valor de vendas (R$): ");
        while (valorVendas < 0) {
            System.out.println("O valor de vendas não pode ser negativo.");
            valorVendas = lerDecimal("Valor de vendas (R$): ");
        }

        double percentual = lerDecimal("Percentual de comissão (0 a 100): ");
        while (percentual < 0 || percentual > 100) {
            System.out.println("O percentual deve estar entre 0 e 100.");
            percentual = lerDecimal("Percentual de comissão (0 a 100): ");
        }

        colaboradores.add(new ColaboradorComissionado(matricula, nome, valorVendas, percentual));
        System.out.println("Colaborador comissionado cadastrado com sucesso!");
    }

    static void cadastrarProducao() {
        System.out.println("\n--- Cadastro de Colaborador Produção ---");
        int matricula = lerMatricula();
        String nome = lerNome();

        int quantidade = lerInteiro("Quantidade de peças: ");
        while (quantidade < 0) {
            System.out.println("A quantidade de peças não pode ser negativa.");
            quantidade = lerInteiro("Quantidade de peças: ");
        }

        double valorPorPeca = lerDecimal("Valor por peça (R$): ");
        while (valorPorPeca < 0) {
            System.out.println("O valor por peça não pode ser negativo.");
            valorPorPeca = lerDecimal("Valor por peça (R$): ");
        }

        colaboradores.add(new ColaboradorProducao(matricula, nome, quantidade, valorPorPeca));
        System.out.println("Colaborador de produção cadastrado com sucesso!");
    }

    static void gerarFolhaPagamento() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        System.out.println("\n========== FOLHA DE PAGAMENTO ==========");
        for (Colaborador c : colaboradores) {
            c.exibirDados();
            System.out.println("Salário final.....: " + Colaborador.formatarMoeda(c.calcularSalarioFinal()));
            System.out.println("----------------------------------------");
        }
    }

    static void exibirResumo() {
        if (colaboradores.isEmpty()) {
            System.out.println("\nNenhum colaborador cadastrado.");
            return;
        }

        double total = 0;
        for (Colaborador c : colaboradores) {
            total += c.calcularSalarioFinal();
        }
        double media = total / colaboradores.size();

        System.out.println("\n========== RESUMO DA FOLHA ==========");
        System.out.println("Quantidade de colaboradores: " + colaboradores.size());
        System.out.println("Total da folha.............: " + Colaborador.formatarMoeda(total));
        System.out.println("Média salarial.............: " + Colaborador.formatarMoeda(media));
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = teclado.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    static double lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = teclado.nextLine().trim();

            if (entrada.contains(".")) {
                System.out.println("Não use ponto. Digite sem separador de milhar e use vírgula nos centavos (ex: 10000 ou 1500,50).");
            } else {
                try {
                    double valor = Double.parseDouble(entrada.replace(",", "."));
                    if (Double.isFinite(valor)) {
                        return valor;
                    }
                    System.out.println("Valor inválido! Digite um número (ex: 1500,50).");
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido! Digite um número (ex: 1500,50).");
                }
            }
        }
    }

    static String lerNome() {
        System.out.print("Nome: ");
        String nome = teclado.nextLine().trim();
        while (nome.isEmpty()) {
            System.out.println("O nome não pode ficar vazio.");
            System.out.print("Nome: ");
            nome = teclado.nextLine().trim();
        }
        return nome;
    }

    static int lerMatricula() {
        while (true) {
            int matricula = lerInteiro("Matrícula: ");
            if (matricula <= 0) {
                System.out.println("A matrícula deve ser um número positivo.");
            } else if (matriculaExiste(matricula)) {
                System.out.println("Já existe um colaborador com a matrícula " + matricula + ".");
            } else {
                return matricula;
            }
        }
    }

    static boolean matriculaExiste(int matricula) {
        for (Colaborador c : colaboradores) {
            if (c.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }
}
