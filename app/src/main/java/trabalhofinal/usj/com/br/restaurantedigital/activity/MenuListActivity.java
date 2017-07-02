package trabalhofinal.usj.com.br.restaurantedigital.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trabalhofinal.usj.com.br.restaurantedigital.R;
import trabalhofinal.usj.com.br.restaurantedigital.dao.IDAO;
import trabalhofinal.usj.com.br.restaurantedigital.dao.MenuDAO;
import trabalhofinal.usj.com.br.restaurantedigital.entity.Menu;

/**
 * Created by Édipo on 01/07/2017.
 */

public class MenuListActivity extends ListActivity {

    private List<Map<String, Object>> itens;
    private AlertDialog menu;
    private AlertDialog caixaConfirmacao;
    private int itemSelecionado;
    private IDAO<Menu> dao;

    private AdapterView.OnItemClickListener listener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?>
                                                adapterView,
                                        View view, int position,
                                        long id) {
                    itemSelecionado = position;
                    menu.show();
                }
            };

    private DialogInterface.OnClickListener listenerMenu =
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface,
                                    int item) {
                    Integer idMenu = (Integer) itens.get(itemSelecionado).get(MenuDAO.ID);
                    Intent intent;
                    switch (item){
                        case 0: //editar item
                            intent = new Intent(getApplicationContext(), CadastroMenuListActivity.class);
                            intent.putExtra(MenuDAO.ID, idMenu);
                            startActivity(intent);
                            break;
                        case 1: //remover item
                            caixaConfirmacao.show();
                            break;
                        case DialogInterface.BUTTON_POSITIVE:

                            Boolean sucesso = dao.excluir(idMenu);
                            if (sucesso){
                                itens.remove((itemSelecionado));
                                Toast.makeText(getApplicationContext(), R.string.remover_item_sucesso,
                                        Toast.LENGTH_LONG).show();
                                intent = new Intent(getApplicationContext(), MenuListActivity.class);;
                                intent.putExtra(MenuDAO.ID, idMenu);
                                caixaConfirmacao.dismiss();
                                startActivity(intent);
                            }
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            caixaConfirmacao.dismiss();
                            break;
                    }
                }
            };

    @Override
    protected void onCreate(
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = new MenuDAO(getApplicationContext());
                itens = listarItens();

        if(itens != null && itens.size() > 0){ //existem itens para exibir
            String[] de = {getString(R.string.nome_do_item), getString(R.string.preco_do_item)};
            int[] para = {R.id.idNomePrato_Menu_Inicial, R.id.idPrecoPrato_Menu_Inicial};

            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    itens,
                    R.layout.layout_menu_menu_inicial,
                    de,
                    para);

            setListAdapter(adapter);
            ListView listView = getListView();
            listView.setOnItemClickListener(listener);
        }
        else{ //não existem gastos para exibir
            Map<String, Object> mapaMensagem = new HashMap<>();
            mapaMensagem.put(getString(R.string.nome_do_item), getString(R.string.nada_cadastrado));
            List<Map<String, Object>> listaMapa = new ArrayList<Map<String, Object>>();
            listaMapa.add(mapaMensagem);
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                    listaMapa, R.layout.layout_menu_menu_inicial, new String[]{getString(R.string.nome_do_item)},
                    new int[]{R.id.idNomePrato_Menu_Inicial});
            setListAdapter(adapter);
        }

        this.menu = criarAlertDialog();
        this.caixaConfirmacao = criarConfirmacaoDialog();
    }

    private List<Map<String, Object>> listarItens(){

        itens = new ArrayList<Map<String, Object>>();

        for(Menu itens: dao.listar()){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put(MenuDAO.ID, itens.getId());
            item.put(getString(R.string.nome_do_item), itens.getNomePrato());
            item.put(getString(R.string.preco_do_item), itens.getPreco());

        }

        return itens;
    }

    private AlertDialog criarAlertDialog(){
        final CharSequence[] items = {
                getString(R.string.editar),
                getString(R.string.excluir)
        };
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, listenerMenu);

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