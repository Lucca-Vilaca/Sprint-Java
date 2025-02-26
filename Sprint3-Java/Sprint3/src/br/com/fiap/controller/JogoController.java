package br.com.fiap.controller;

import br.com.fiap.model.DAO.JogoDAO;
import br.com.fiap.model.DAO.ConnectionFactory;
import br.com.fiap.model.DTO.Jogo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class JogoController {

    //Insere as notas
    public String inserirJogo(float nocao_medica, float nota) throws ClassNotFoundException, SQLException {

        System.out.println("nocao_medica: " + nocao_medica);
        System.out.println("nota: " + nota);

        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Jogo jogo = new Jogo();
        jogo.setNocao_medica(nocao_medica);
        jogo.setNota(nota);
        JogoDAO jogodao = new JogoDAO(con);
        resultado = jogodao.inserirNotaComPontuacao(jogo);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

//Altera as notas
    public String alterarJogo(int id_nota, int id_pontuacao, float nocao_medica, float nota) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Jogo jogo = new Jogo();
        jogo.setId_nota(id_nota);
        jogo.setId_pontuacao(id_pontuacao);
        jogo.setNocao_medica(nocao_medica);
        jogo.setNota(nota);
        JogoDAO jogodao = new JogoDAO(con);
        resultado = jogodao.alterar_nota(jogo);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }


    //Exclui as notas
    public String excluirJogo(int id_nota) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Jogo jogo = new Jogo();
        jogo.setId_nota(id_nota); // Adicione esta linha
        JogoDAO jogodao = new JogoDAO(con);
        resultado = jogodao.excluir_nota(jogo);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }


    //Mostra todas as notas
    public String listarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.abrirConexao();
        JogoDAO jogodao = new JogoDAO(con);

        String resultado = jogodao.listarTodos();

        ConnectionFactory.fecharConexao(con);

        if (resultado != null && !resultado.isEmpty()) {
            return resultado;
        } else {
            return "Nenhum dado encontrado.";
        }
    }


}
