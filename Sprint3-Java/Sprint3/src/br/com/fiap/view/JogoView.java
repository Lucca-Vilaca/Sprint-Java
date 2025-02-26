package br.com.fiap.view;

import br.com.fiap.controller.JogoController;
import br.com.fiap.model.DTO.Jogo;

import javax.swing.*;

public class JogoView {
    public static void main(String[] args) {
        int id_nota, id_pontuacao;
        float nocao_medica, nota;
        String aux;
        String[] escolha = {"Inserir nota","Alterar nota","Excluir nota","Listar notas"};
        int opcao;
        JogoController jogoController = new JogoController();

        //Roda o código enquanto o usuario quiser continuar, dando todas as opções de teste
        do {
            try {
                opcao = JOptionPane.showOptionDialog(null, "Oque deseja fazer ?", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]);
                switch (opcao) {
                    case 0:
                        aux = JOptionPane.showInputDialog("Competência: Noção Médica");
                        nocao_medica = Float.parseFloat(aux);
                        aux= JOptionPane.showInputDialog("Nota final do nível");
                        nota = Float.parseFloat(aux);
                        System.out.println(jogoController.inserirJogo(nocao_medica, nota));
                        break;
                    case 1:
                        aux = JOptionPane.showInputDialog("Digite o id da nota que deseja alterar");
                        id_nota = Integer.parseInt(aux);
                        aux = JOptionPane.showInputDialog("Digite o id da pontuação que deseja alterar");
                        id_pontuacao = Integer.parseInt(aux);
                        aux = JOptionPane.showInputDialog("Digite uma nova nota para a competência Noção Médica");
                        nocao_medica = Float.parseFloat(aux);
                        aux = JOptionPane.showInputDialog("Digite uma nova nota final para o nível");
                        nota = Float.parseFloat(aux);
                        System.out.println(jogoController.alterarJogo(id_nota,id_pontuacao, nocao_medica, nota));
                        break;
                    case 2:
                        aux = JOptionPane.showInputDialog("Digite o id da nota");
                        id_nota = Integer.parseInt(aux);
                        System.out.println(jogoController.excluirJogo(id_nota));
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, jogoController.listarTodos());
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());;
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
        JOptionPane.showMessageDialog(null, "Fim de Programa");
    }
}

