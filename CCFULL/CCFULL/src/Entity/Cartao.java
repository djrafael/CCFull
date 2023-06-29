package Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Cartao {
    private String nomeTitular;
    private String documentoTitular;
    private String numeroCartao;
    private LocalDate dataValidade;
    private double limiteCredito;
    private Cartao cartaoAdicional;

    public Cartao(String nomeTitular, String documentoTitular, double limiteCredito) {
        this.nomeTitular = nomeTitular;
        this.documentoTitular = documentoTitular;
        this.numeroCartao = gerarNumeroCartao();
        this.dataValidade = gerarDataValidade();
        this.limiteCredito = limiteCredito;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getDocumentoTitular() {
        return documentoTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public Cartao getCartaoAdicional() {
        return cartaoAdicional;
    }

    public void setCartaoAdicional(Cartao cartaoAdicional) {
        this.cartaoAdicional = cartaoAdicional;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataValidadeStr = dataValidade.format(formatter);

        return "Cartao{" +
                "nomeTitular='" + nomeTitular + '\'' +
                ", documentoTitular='" + documentoTitular + '\'' +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", dataValidade='" + dataValidadeStr + '\'' +
                ", limiteCredito=" + limiteCredito +
                '}';
    }

    private String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(10);
            numeroCartao.append(digito);
        }
        return numeroCartao.toString();
    }

    private LocalDate gerarDataValidade() {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.plusYears(5);
    }
}

public static void emitirCartaoAdicional() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Emitir Cartão Adicional");

    // Solicitar informações do titular do cartão principal
    System.out.print("Nome do titular do cartão principal: ");
    String nomeTitularPrincipal = scanner.nextLine();
    System.out.print("Documento do titular do cartão principal: ");
    String documentoTitularPrincipal = scanner.nextLine();

    // Solicitar informações do cartão adicional
    System.out.print("Nome do titular do cartão adicional: ");
    String nomeTitularAdicional = scanner.nextLine();
    System.out.print("Documento do titular do cartão adicional: ");
    String documentoTitularAdicional = scanner.nextLine();
    System.out.print("Limite de crédito do cartão adicional: ");
    double limiteCreditoAdicional = scanner.nextDouble();

    // Verificar se o cartão principal existe e possui limite suficiente
    // para emitir o cartão adicional
    Cartao cartaoPrincipal = consultarCartaoPrincipal(nomeTitularPrincipal, documentoTitularPrincipal);
    if (cartaoPrincipal != null) {
        if (limiteCreditoAdicional <= cartaoPrincipal.getLimiteCredito()) {
            Cartao cartaoAdicional = new Cartao(nomeTitularAdicional, documentoTitularAdicional, limiteCreditoAdicional);
            cartaoPrincipal.setCartaoAdicional(cartaoAdicional);
            System.out.println("Cartão adicional emitido com sucesso.");
        } else {
            System.out.println("Não é possível emitir o cartão adicional. O limite excede o limite do cartão principal.");
        }
    } else {
        System.out.println("Não foi possível encontrar o cartão principal. Verifique os dados informados.");
    }
}

public static Cartao consultarCartaoPrincipal(String nomeTitular, String documentoTitular) {
    // Implemente a lógica para consultar o cartão principal no banco de dados
    // com base no nome do titular e no documento do titular.
    // Retorne o objeto Cartao correspondente ou null se não for encontrado.
    return null;
}

public static void main(String[] args) {
    int op;
    do {
        op = menu();
        switch (op) {
            case 1:
                emitirCartao();
                break;
            case 2:
                emitirCartaoAdicional();
                break;
            case 3:
                realizarTransacao();
                break;
            case 4:
                consultarSaldoDisponivel();
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    } while (op != 5);
}

public static int menu() {
    Scanner teclado = new Scanner(System.in);
    System.out.println("MENU");
    System.out.println("1- Emitir Cartão");
    System.out.println("2- Emitir Cartão Adicional");
    System.out.println("3- Realizar Transação");
    System.out.println("4- Consultar Saldo Disponível");
    System.out.println("5- Sair");
    System.out.print("Escolha uma opção: ");
    return teclado.nextInt();
}

public static void emitirCartao() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Emitir Cartão");
    System.out.print("Nome do titular: ");
    String nomeTitular = scanner.nextLine();
    System.out.print("Documento do titular: ");
    String documentoTitular = scanner.nextLine();
    System.out.print("Limite de crédito: ");
    double limiteCredito = scanner.nextDouble();

    Cartao cartao = new Cartao(nomeTitular, documentoTitular, limiteCredito);
    System.out.println("Cartão emitido com sucesso: " + cartao);
}

public static void realizarTransacao() {
    // Implementar lógica para realizar uma transação
    System.out.println("Opção 3 selecionada: Realizar Transação");
}

public static void consultarSaldoDisponivel() {
    // Implementar lógica para consultar saldo disponível
    System.out.println("Opção 4 selecionada: Consultar Saldo Disponível");
}
