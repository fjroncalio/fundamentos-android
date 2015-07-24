package com.example.fabricio.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabricio.myapplication.R;
import com.example.fabricio.myapplication.Util.FormHelper;
import com.example.fabricio.myapplication.model.entities.Client;

public class ClientPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private Client client;

    EditText editTextName;
    EditText editTextAge;
    EditText editTextPhone;
    EditText editTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        setElements();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if (client == null) {
                throw new IllegalArgumentException();
            }
            bindClient(client);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {


            if (FormHelper.requiredValidate(ClientPersistActivity.this, editTextAddress, editTextAge, editTextName, editTextPhone)) {
                bindClient();
                client.save();

                Toast.makeText(ClientPersistActivity.this, R.string.success, Toast.LENGTH_SHORT).show();

                ClientPersistActivity.this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void setElements() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
    }


    private void bindClient() {
        if (client == null) {
            client = new Client();
        }
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setName(editTextName.getText().toString());
        client.setAddress(editTextAddress.getText().toString());
        client.setPhone(editTextPhone.getText().toString());
    }

    private void bindClient(Client client) {
        editTextAge.setText(client.getAge().toString());
        editTextName.setText(client.getName());
        editTextAddress.setText(client.getAddress());
        editTextPhone.setText(client.getPhone());
    }

}
