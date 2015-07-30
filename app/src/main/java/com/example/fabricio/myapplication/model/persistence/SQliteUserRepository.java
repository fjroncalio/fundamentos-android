package com.example.fabricio.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fabricio.myapplication.Util.AppUtil;
import com.example.fabricio.myapplication.model.entities.Client;
import com.example.fabricio.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabricio on 30/07/2015.
 */
public class SQliteUserRepository implements UserRepository {

    private static SQliteUserRepository singletonInstance;

    private SQliteUserRepository() {
        super();
    }

    public static SQliteUserRepository getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SQliteUserRepository();
        }
        return singletonInstance;
    }


    @Override
    public List<User> search(User user) {

        List<User> users = new ArrayList<>();

        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = UserContract.buildWereSearch(user);

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, where, UserContract.getArgsSerarch(user), null, null, UserContract.USER_NAME);

        users = UserContract.bindList(cursor);

        db.close();
        helper.close();

        return users;
    }



    @Override
    public void save(User user) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);

        } else {
            String where = UserContract.ID + " = ?";
            String[] args = {user.getId().toString()};

            db.update(UserContract.TABLE, values, where, args);
        }

        db.close();
        helper.close();
    }
}
