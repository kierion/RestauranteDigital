package trabalhofinal.usj.com.br.restaurantedigital.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import trabalhofinal.usj.com.br.restaurantedigital.entity.Menu;
import trabalhofinal.usj.com.br.restaurantedigital.util.DatabaseHelper;


/**
 * Created by Édipo on 01/07/2017.
 */

public class MenuDAO implements IDAO<Menu> {

    private SQLiteOpenHelper db;


    public static final String TABELA = "menu";
    public static final String ID = "_id";
    //public static final String FOTO = "foto";
    //public static final String ID_Carrinho = "id_carrinho";
    public static final String SPINNER = "spinner";
    public static final String PRECO = "preco";
    public static final String NOME_PRATO = "nomePrato";
    public static final String DESCRICAO = "descricao";


    public MenuDAO(Context context){
        db = new DatabaseHelper(context);
    }

    public static String criarTabela(){
        return "CREATE TABLE " + TABELA + "(" +
                        ID +" INTEGER PRIMARY KEY, " +
                        SPINNER + " INTEGER, " +
                        PRECO + " DOUBLE, " +
                        NOME_PRATO + " TEXT, " +
                        DESCRICAO + " TEXT);";
    }

    @Override
    public boolean salvar(Menu m) {
        ContentValues values = new ContentValues();
        values.put(SPINNER, m.getSpinner());
        values.put(PRECO, m.getPreco());
        values.put(NOME_PRATO, m.getNomePrato());
        values.put(DESCRICAO, m.getDescricao());

        SQLiteDatabase banco = db.getWritableDatabase();
        long resultado = banco.insert(TABELA, null, values);
        return resultado > 0;
    }

    @Override
    public boolean excluir(Integer id) {
        SQLiteDatabase banco = db.getWritableDatabase();
        int resultado =
                banco.delete(TABELA, "_id = ?",new String[]
                        {id.toString()});
        return resultado > 0;
    }

    @Override
    public boolean atualizar(Menu m) {
        ContentValues values = new ContentValues();
        values.put(ID, m.getId());
        values.put(SPINNER, m.getSpinner());
        values.put(PRECO, m.getPreco());
        values.put(NOME_PRATO, m.getNomePrato());
        values.put(DESCRICAO, m.getDescricao());

        String id = String.valueOf(m.getId());

        SQLiteDatabase banco = db.getWritableDatabase();
        int resultado =
                banco.update(TABELA, values, "_id = ?",
                        new String[]{id});
        return resultado > 0;
    }

    @Override
    public List<Menu> listar() {
        List<Menu> retorno = new ArrayList<Menu>();

        SQLiteDatabase banco = db.getReadableDatabase();
        String sql = "SELECT " + ID +", "+ SPINNER +", "+ PRECO +", "+ NOME_PRATO +", "+ DESCRICAO + " FROM "+ TABELA;

        Cursor cursor = banco.rawQuery(sql, null);

        //cursor.moveToFirst();

        while(cursor.moveToNext()){
            Menu m = new Menu();
            m.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            //m.setSpinner(Integer.valueOf(cursor.getString(cursor.getColumnIndex(SPINNER))));
            m.setPreco(cursor.getInt(cursor.getColumnIndex(PRECO)));
            m.setNomePrato(String.valueOf(cursor.getInt(cursor.getColumnIndex(NOME_PRATO))));
            m.setDescricao(String.valueOf(cursor.getInt(cursor.getColumnIndex(DESCRICAO))));
            retorno.add(m);
        }
        cursor.close();

        return retorno;
    }

    @Override
    public Menu buscarPorId(Integer id) {
        SQLiteDatabase banco = db.getReadableDatabase();
        String sql = "SELECT " + ID +", "+ SPINNER +", "+ PRECO +", "+ NOME_PRATO +", "+ DESCRICAO + " FROM "+ TABELA + " WHERE " + ID + " = ?";

        String idString = String.valueOf(id);

        Cursor cursor = banco.rawQuery(sql, new String[]{idString});

        if(cursor.moveToNext()){
            Menu m = new Menu();
            m.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            //m.setSpinner(Integer.valueOf(cursor.getString(cursor.getColumnIndex(SPINNER))));
            m.setPreco(cursor.getInt(cursor.getColumnIndex(PRECO)));
            m.setNomePrato(String.valueOf(cursor.getInt(cursor.getColumnIndex(NOME_PRATO))));
            m.setDescricao(String.valueOf(cursor.getInt(cursor.getColumnIndex(DESCRICAO))));
            cursor.close();
            return m;
        }

        cursor.close();
        return null;
    }
}
