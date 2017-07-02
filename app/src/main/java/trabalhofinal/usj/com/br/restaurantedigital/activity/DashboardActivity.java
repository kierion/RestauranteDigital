package trabalhofinal.usj.com.br.restaurantedigital.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import trabalhofinal.usj.com.br.restaurantedigital.R;

/**
 * Created by Édipo on 01/07/2017.
 */

public class DashboardActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_restaurante);
        //itemDAO = new ItemDAO(getApplicationContext());
    }

    public void selecionarOpcao(View view) {
        switch (view.getId()){
            case R.id.idMenuInicial_Funcionario_Carrinho:
                //startActivity(new Intent(this, CarrinhoActivity.class));
                //break;
                Toast.makeText(this, "nao cadastrado Carrinho", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_MenuInicia_Funcionario_Funcionarios:
                    //startActivity(new Intent(this, GerenciarFuncionariosActivity.class));
                    //break;
                Toast.makeText(this, "nao cadastrado Cadastro Funcionarios", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_MenuInicial_Funcionario_Menu:
                startActivity(new Intent(this, MenuListActivity.class));
                break;

            case R.id.id_MenuInicial_Funcionario_Cadastro_Menu:
                startActivity(new Intent(this, CadastroMenuListActivity.class));
                break;


            case R.id.id_MenuInicial_Funcionario_Configuracoes:
                startActivity(new Intent(this, CadastroItemActivity.class));
                break;

        }
    }
}
