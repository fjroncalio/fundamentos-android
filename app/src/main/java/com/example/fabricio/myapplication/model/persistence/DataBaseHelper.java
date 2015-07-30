package com.example.fabricio.myapplication.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fabricio.myapplication.model.entities.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static final String BANCO_DADOS = "MY_DATABASE";
    public  static final int VERSION = 1;
    private static User userDefault(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");

        return user;
    }


    public DataBaseHelper(Context context) {
        super(context, DataBaseHelper.BANCO_DADOS, null, DataBaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getCreateSqlTable());
        db.execSQL(UserContract.getCreateSqlTable());
        //Mantem um usuario Deafult do sistema
        db.execSQL(UserContract.getInsertUserDefault(userDefault()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
