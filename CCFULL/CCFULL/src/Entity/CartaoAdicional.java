import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CartaoAdicional {
    private String nomeTitular;
    private String documentoTitular;
    private String numeroCartao;
    private LocalDate dataValidade;
    private double limiteCredito;

    public CartaoAdicional(String nomeTitular, String documentoTitular, double limiteCredito) {
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataValidadeStr = dataValidade.format(formatter);

        return "CartaoAdicional{" +
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

    public void emitirCartaoAdicional() {
        // Lógica para emitir o cartão adicional
        System.out.println("Cartão adicional emitido com sucesso: " + this);
    }
}
