package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Cartao;

public class CartaoDAO {
    private Connection conexao;

    public CartaoDAO() {
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
        String user = "seu_usuario";
        String password = "sua_senha";

        try {
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void inserirCartao(Cartao cartao) {
        String sql = "INSERT INTO cartao (nome_titular, documento_titular, numero_cartao, data_validade, limite_credito) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cartao.getNomeTitular());
            pst.setString(2, cartao.getDocumentoTitular());
            pst.setString(3, cartao.getNumeroCartao());
            pst.setString(4, cartao.getDataValidade().toString());
            pst.setDouble(5, cartao.getLimiteCredito());
            pst.executeUpdate();
            System.out.println("Cartão inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cartão: " + e.getMessage());
        }
    }

    public Cartao consultarCartao(String numeroCartao) {
        String sql = "SELECT * FROM cartao WHERE numero_cartao = ?";
        Cartao cartao = null;

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, numeroCartao);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nomeTitular = rs.getString("nome_titular");
                String documentoTitular = rs.getString("documento_titular");
                String dataValidadeStr = rs.getString("data_validade");
                double limiteCredito = rs.getDouble("limite_credito");

                cartao = new Cartao(nomeTitular, documentoTitular, limiteCredito);
                cartao.setNumeroCartao(numeroCartao);
                cartao.setDataValidade(LocalDate.parse(dataValidadeStr));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cartão: " + e.getMessage());
        }

        return cartao;
    }

    public void atualizarCartao(Cartao cartao) {
        String sql = "UPDATE cartao SET nome_titular = ?, documento_titular = ?, " +
                     "data_validade = ?, limite_credito = ? WHERE numero_cartao = ?";

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cartao.getNomeTitular());
            pst.setString(2, cartao.getDocumentoTitular());
            pst.setString(3, cartao.getDataValidade().toString());
            pst.setDouble(4, cartao.getLimiteCredito());
            pst.setString(5, cartao.getNumeroCartao());
            pst.executeUpdate();
            System.out.println("Cartão atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cartão: " + e.getMessage());
        }
    }
}