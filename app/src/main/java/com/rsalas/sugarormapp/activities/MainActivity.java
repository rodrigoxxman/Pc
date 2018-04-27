package com.rsalas.sugarormapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rsalas.sugarormapp.R;
import com.rsalas.sugarormapp.models.User;
import com.rsalas.sugarormapp.repositories.UserRepository;

public class MainActivity extends AppCompatActivity {


    private static final int REGISTER_FORM_REQUEST = 100;
    private SharedPreferences sharedPreferences;
    private EditText usernameinput;
    private EditText passwordinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameinput = findViewById(R.id.username_input);
        passwordinput = findViewById(R.id.password_input);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterActivity.class), REGISTER_FORM_REQUEST);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        ProductAdapter adapter = (ProductAdapter)usersList.getAdapter();
//
//        List<User> users = UserRepository.list();
//        adapter.setUsers(users);
//        adapter.notifyDataSetChanged();
//
//    }
    public void callIndex(View view){
        String username =  usernameinput.getText().toString();
        String password =  passwordinput.getText().toString();
        User user = UserRepository.login(username, password);
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user == null){
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(this, "Bienvenido " + user.getFullname(), Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean success = editor
                    .putString("username", user.getUsername())
                    .putBoolean("está logueado", true)
                    .commit();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}