package trabalhofinal.usj.com.br.restaurantedigital.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import trabalhofinal.usj.com.br.restaurantedigital.dao.MenuDAO;

/**
 * Created by Édipo on 01/07/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BancoMenu";
    private static int version = 1;


    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MenuDAO.criarTabela());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int i, int i1) {

    }
}
