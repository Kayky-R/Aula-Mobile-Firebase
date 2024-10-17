package com.example.fireauth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, goToRegisterButton, bt_esqueceusenha;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        goToRegisterButton = findViewById(R.id.goToRegisterButton);
        bt_esqueceusenha = findViewById(R.id.bt_esqueceusenha);

        mAuth = FirebaseAuth.getInstance();


        bt_esqueceusenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                esqueceusenha();
                

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                loginUser(email, password);
            }
        });

        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, register.class));  // Corrigi o nome da activity de registro aqui
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                        // Redirecionar para a tela logado
                        Intent intent = new Intent(MainActivity.this, logado.class);
                        startActivity(intent);
                        finish();  // Finaliza a activity de login
                    } else {
                        Toast.makeText(MainActivity.this, "Falha no login: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void esqueceusenha(){

        startActivity(new Intent(MainActivity.this, forgotPassword.class));

    }

}
