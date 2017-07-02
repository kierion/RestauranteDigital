package trabalhofinal.usj.com.br.restaurantedigital.dao;

import java.util.List;

/**
 * Created by Édipo on 01/07/2017.
 */

public interface IDAO<T> {

    boolean salvar(T p);

    boolean excluir(Integer id);

    boolean atualizar(T p);

    List<T> listar();

    T buscarPorId(Integer id);
}
