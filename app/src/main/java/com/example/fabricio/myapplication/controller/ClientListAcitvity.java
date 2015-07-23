package com.example.fabricio.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fabricio.myapplication.model.entities.Client;
import com.example.fabricio.myapplication.R;

import java.util.List;


public class ClientListAcitvity extends AppCompatActivity {

    private ListView listViewClients;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        bindClientList();

    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshClientList();

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_list, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuEdit:
                Intent intent = new Intent(ClientListAcitvity.this, ClientPersistActivity.class);
                intent.putExtra(ClientPersistActivity.CLIENT_PARAM, (Parcelable)client);
                startActivity(intent);
                break;

            case R.id.menuDelete:
                client.delete();
                refreshClientList();
                Toast.makeText(ClientListAcitvity.this, R.string.success, Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuNewClient) {
            Intent goToMainAcitivity = new Intent(ClientListAcitvity.this, ClientPersistActivity.class);
            startActivity(goToMainAcitivity);
        }
        return super.onOptionsItemSelected(item);
    }



    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.setClients(getClients());
        adapter.notifyDataSetInvalidated();
    }

    private void bindClientList() {
        List<Client> clients = getClients();
        listViewClients = (ListView) findViewById(R.id.listViewClients);
        final ClientListAdapter clientAdapter = new ClientListAdapter(ClientListAcitvity.this, clients);
        listViewClients.setAdapter(clientAdapter);

        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });
        registerForContextMenu(listViewClients);
    }

    private List<Client> getClients() {
        List<Client> clientNames = Client.getAll();
        return clientNames;
    }

}
