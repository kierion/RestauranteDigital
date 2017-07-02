package trabalhofinal.usj.com.br.restaurantedigital.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import trabalhofinal.usj.com.br.restaurantedigital.R;

public class RestauranteDigitalActivity extends Activity {

    private EditText idUsuario, idSenha;
    private CheckBox liberaUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_login);
        this.idUsuario = (EditText) findViewById(R.id.id_Texto_Usuario_Login);
        this.idSenha = (EditText) findViewById(R.id.idTexto_Senha_Login);
        liberaUsuario = (CheckBox) findViewById(R.id.IdCheckBox_Libera_Login);
    }

    public void loginSistema(View view) {

        if (liberaUsuario.isChecked()) {
            //Intent intent = new Intent(this, DashboardActivity.class);
            //startActivity(intent);
            setContentView(R.layout.layout_activity_cliente);
            Toast t = Toast.makeText(this,
                    R.string.login_sucesso_cliente, Toast.LENGTH_LONG);
            t.show();

        }else{

        String usuarioInformado = this.idUsuario.getText().
                toString();
        String senhaInformada = this.idSenha.getText().toString();

        if(usuarioInformado.equals("")
                & senhaInformada.equals("")){
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
        else{
            Toast t = Toast.makeText(this,
                    R.string.usuarioInvalido, Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
}
