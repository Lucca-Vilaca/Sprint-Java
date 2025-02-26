package br.com.fiap.model.DAO;

import br.com.fiap.model.DTO.Jogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JogoDAO {
    private Connection con;
    private Jogo jogo;

    //Estabelecendo conexão
    public JogoDAO(Connection con) { this.con = con; }

    public Connection getCon() { return con; }

    // Método para obter o próximo id_pontuacao
    private int obterProximoIdPontuacao() throws SQLException {
        String sql = "SELECT NVL(MAX(ID_PONTUACAO), 0) + 1 FROM PONTUACAO";
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 1;
    }

    // Verifica se a pontuação já existe e insere se não existir
    public boolean verificarOuInserirPontuacao(int id_pontuacao) throws SQLException {
        String sqlSelect = "SELECT COUNT(*) FROM PONTUACAO WHERE ID_PONTUACAO = ?";
        try (PreparedStatement psSelect = getCon().prepareStatement(sqlSelect)) {
            psSelect.setInt(1, id_pontuacao);
            ResultSet rs = psSelect.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {

                return true;
            } else {
                // A pontuação não existe, então insere
                String sqlInsert = "INSERT INTO PONTUACAO (id_pontuacao) VALUES (?)";
                try (PreparedStatement psInsert = getCon().prepareStatement(sqlInsert)) {
                    psInsert.setInt(1, id_pontuacao);
                    psInsert.executeUpdate();
                    System.out.println("Pontuação inserida com sucesso: " + id_pontuacao);
                    return true;
                } catch (SQLException e) {
                    System.out.println("Erro ao inserir pontuação: " + e.getMessage());
                    return false;
                }
            }
        }
    }

    public String inserirNotaComPontuacao(Object object) {
        jogo = (Jogo) object;

        try {
            // Obter o próximo id_pontuacao
            int proximoIdPontuacao = obterProximoIdPontuacao();

            // Verifique se o id_pontuacao existe na tabela PONTUACAO
            if (!verificarOuInserirPontuacao(proximoIdPontuacao)) {
                return "Erro ao verificar ou inserir pontuação.";
            }

            String sql = "INSERT INTO NOTAS(ID_NOTA, ID_PONTUACAO, NOCAO_MEDICA, VALOR_NOTA) VALUES(ID_NOTA.NEXTVAL,?,?,?)";
            try (PreparedStatement ps = getCon().prepareStatement(sql)) {
                ps.setInt(1, proximoIdPontuacao); // Use o próximo id_pontuacao
                ps.setFloat(2, jogo.getNocao_medica());
                ps.setFloat(3, jogo.getNota());

                int linhasAfetadas = ps.executeUpdate();
                if (linhasAfetadas > 0) {
                    return "Nota inserida com sucesso";
                } else {
                    return "Erro ao inserir nota";
                }
            } catch (SQLException e) {
                return "Erro de SQL ao inserir nota: " + e.getMessage();
            }

        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    //Altera as notas com base no id_nota
    public String alterar_nota(Object object) {
        jogo = (Jogo) object;
        String sql = "UPDATE NOTAS SET ID_PONTUACAO=?, NOCAO_MEDICA=?, VALOR_NOTA=? WHERE ID_NOTA=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, jogo.getId_pontuacao());
            ps.setFloat(2, jogo.getNocao_medica());
            ps.setFloat(3, jogo.getNota());
            ps.setInt(4, jogo.getId_nota()); // Aqui deve ser o ID da nota

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    //Exclui as notas com base no id_nota
    public String excluir_nota(Object object) {
        jogo = (Jogo) object;
        String sql = "DELETE FROM NOTAS WHERE ID_NOTA=?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setInt(1, jogo.getId_nota()); // Use o ID da nota aqui
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            } else {
                return "Erro ao excluir";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
    }

    //lista todas as notas
    public String listarTodos() {
        String sql = "SELECT * FROM NOTAS ORDER BY ID_NOTA";
        StringBuilder resultado = new StringBuilder();
        try (PreparedStatement ps = getCon().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {

                    resultado.append("ID Nota: ").append(rs.getInt("ID_NOTA")).append("\n")
                            .append("ID Pontuação: ").append(rs.getInt("ID_PONTUACAO")).append("\n")
                            .append("Noção Médica: ").append(rs.getFloat("NOCAO_MEDICA")).append("\n")
                            .append("Nota: ").append(rs.getFloat("VALOR_NOTA")).append("\n")
                            .append("----------------------------\n");
                }
            } else {
                return "Nenhum dado encontrado.";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        }
        return resultado.toString();
    }
}
