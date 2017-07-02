package trabalhofinal.usj.com.br.restaurantedigital.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalhofinal.usj.com.br.restaurantedigital.R;
import trabalhofinal.usj.com.br.restaurantedigital.dao.IDAO;
import trabalhofinal.usj.com.br.restaurantedigital.dao.MenuDAO;
import trabalhofinal.usj.com.br.restaurantedigital.entity.Menu;
import trabalhofinal.usj.com.br.restaurantedigital.util.Constantes;

/**
 * Created by Édipo on 01/07/2017.
 */

public class CadastroMenuListActivity extends Activity {

    private ListView listView;
    private IDAO<Menu> dao;
    private List<Map<String, Object>> itens;
    private int itemSelecionada;
    private AlertDialog menu, menuConfirmar;


    private AdapterView.OnItemClickListener listener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?>
                                                adapterView,
                                        View view, int position,
                                        long id) {
                    itemSelecionada = position;
                    menu.show();
                }
            };

    private DialogInterface.OnClickListener listenerMenu =
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface,
                                    int item) {
                    Integer idMenu = (Integer) itens.get(itemSelecionada).
                            get(getString(R.string.id));
                    switch (item){
                        case 0:
                            Intent intent = new Intent(getApplicationContext(),
                                    CadastroItemActivity.class);
                            intent.putExtra(CadastroItemActivity.EXTRA_ID_MENU, idMenu);
                            startActivity(intent);
                            break;
                        case 1:
                            menuConfirmar.show();
                            break;
                        case DialogInterface.BUTTON_POSITIVE:
                            Boolean sucesso = dao.excluir(idMenu);
                            if (sucesso){
                                itens.remove(itemSelecionada);
                                Toast.makeText(getApplicationContext(), R.string.remover_item_sucesso,
                                        Toast.LENGTH_LONG).show();
                                menuConfirmar.dismiss();
                            }
                            listView.invalidateViews();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            menuConfirmar.dismiss();
                            break;
                    }
                }
            };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro_listview_cadastro_itens);
        dao = new MenuDAO(getApplicationContext());
        listView = (ListView) findViewById(R.id.idCadastroListView);

        String[] de = {getString(R.string.nome_do_item), getString(R.string.preco_do_item)};
        int[] para = {R.id.idListaItens_Nome_Item, R.id.idListaItens_Preco_Item};
        itens = listarItens();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                itens,
                R.layout.layout_cadastro_listar_itens_cadastrados,
                de,
                para);
        //setando as opcoes da list view
        listView.setAdapter(adapter);

        //setando as acoes a serem tomadas ao clicar em cada opcao
        listView.setOnItemClickListener(listener);

        menu = criarAlertDialog();
        menuConfirmar = criarConfirmacaoDialog();
    }

    private List<Map<String, Object>> listarItens(){

        List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
        List<Menu> listaItens = dao.listar();

        for (Menu itens: listaItens){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(getString(R.string.id), itens.getId());
            item.put(getString(R.string.nome_do_item),itens.getNomePrato());
            item.put(getString(R.string.preco_do_item), itens.getPreco());
            item.put(getString(R.string.descricao_do_item), itens.getDescricao());


            lista.add(item);
        }
        return lista;
    }

    private AlertDialog criarAlertDialog(){
        final CharSequence[] itens = {
                getString(R.string.editar),
                getString(R.string.excluir)
        };
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(itens, listenerMenu);

        return builder.create();
    }

    private AlertDialog criarConfirmacaoDialog(){

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao);
        builder.setPositiveButton(R.string.sim, listenerMenu);
        builder.setNegativeButton(R.string.nao, listenerMenu);
        return builder.create();
    }

}


//Array para armazenar imagem em bytecode e salvar no SQLite como BLOB
/**
    Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
    ByteArrayOutputStream saida = new ByteArrayOutputStream();
  bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
          byte[] img = saida.toByteArray();

 */