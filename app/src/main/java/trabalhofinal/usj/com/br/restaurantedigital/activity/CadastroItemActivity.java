package trabalhofinal.usj.com.br.restaurantedigital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import trabalhofinal.usj.com.br.restaurantedigital.R;
import trabalhofinal.usj.com.br.restaurantedigital.dao.IDAO;
import trabalhofinal.usj.com.br.restaurantedigital.dao.MenuDAO;
import trabalhofinal.usj.com.br.restaurantedigital.entity.Menu;

/**
 * Created by Édipo on 02/07/2017.
 */

public class CadastroItemActivity  extends Activity {

    private EditText preco, nomeItem, descricao;
    private Spinner spinner;
    private IDAO<Menu> menuDAO;
    private int idItem = 0;
    public static final String EXTRA_ID_MENU = "ID_MENU";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro_cadastrar_item);


        spinner = (Spinner) findViewById(R.id.idSpinnerMenuCadastro);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this, R.array.categoria_item,
                        android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(null));



        preco = (EditText) findViewById(R.id.idCadastrarPrecoItem);
        nomeItem = (EditText) findViewById(R.id.idCadastrarNomeItem);
        descricao = (EditText) findViewById(R.id.idCadastrarDescricaoItem);
        menuDAO = new MenuDAO(getApplicationContext());

        Intent intent = getIntent();
        idItem = intent.getIntExtra(EXTRA_ID_MENU, 0);

        if (idItem > 0){
            //tela de edicao
            Menu itens = menuDAO.buscarPorId(idItem);
            spinner.setSelection(itens.getSpinner());
            preco.setText((int)itens.getPreco());
            nomeItem.setText(itens.getNomePrato());
            descricao.setText(itens.getDescricao());

        }

    }

    public void cadastrar(View view) {
        String spiner = String.valueOf(spinner.getSelectedItemPosition());
        String valor = preco.getText().toString();
        String nomePrato = nomeItem.getText().toString();
        String description = descricao.getText().toString();

        Menu m = new Menu(spiner, valor, nomePrato, description);
        Boolean resultado = false;

        if(idItem > 0){
            //editar
            m.setId(idItem);
            resultado = menuDAO.atualizar(m);
            idItem = 0;
        }
        else{

            resultado = menuDAO.salvar(m);

        }
        if(resultado){
            Toast.makeText(this, getString(R.string.cadastro_sucesso), Toast.LENGTH_LONG).
                    show();
            preco.setText("");
            nomeItem.setText("");
            descricao.setText("");
        }
        else{
            Toast.makeText(this, getString(R.string.cadastro_erro), Toast.LENGTH_LONG).
                    show();
        }
    }

}
