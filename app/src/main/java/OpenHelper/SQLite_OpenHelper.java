package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, password TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Abrir la base de datos
    public void abrir(){
        this.getWritableDatabase();
    }

    //Cerrar la base de datos
    public void cerrar(){
        this.close();
    }

    //Insertar datos en la base de datos
    public void insertar(String usuario, String password){
        ContentValues valores = new ContentValues();
        valores.put("usuario", usuario);
        valores.put("password", password);
        this.getWritableDatabase().insert("usuarios", null, valores);
    }

    //Validar si existe usuario
    public Cursor validarUsuario(String usuario, String password) throws SQLException {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_id", "usuario", "password"},
                "usuario like '" + usuario + "' and password like '" + password + "'", null, null, null, null);
        return cursor;
    }
}
