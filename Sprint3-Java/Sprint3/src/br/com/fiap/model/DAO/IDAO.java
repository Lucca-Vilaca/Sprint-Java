package br.com.fiap.model.DAO;

public interface IDAO {

    public String inserir_nota(Object object);

    public String alterar_nota(Object object);

    public String excluir_nota(Object object);

    public String listarTodos();
}
