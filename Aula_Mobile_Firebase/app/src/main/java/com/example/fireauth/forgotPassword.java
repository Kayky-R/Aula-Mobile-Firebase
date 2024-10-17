package com.example.fireauth;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {
    private Button sendPassword;
    private TextView emailSend;
    private EditText emailEdit;
    private Button btn_Voltar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        sendPassword = findViewById(R.id.sendPassword);
        emailSend = findViewById(R.id.viewTitulo);
        emailEdit = findViewById(R.id.editTextEmailPass);
        mAuth = FirebaseAuth.getInstance();
        btn_Voltar = findViewById(R.id.btn_Voltar);

        btn_Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(forgotPassword.this, MainActivity.class);
                startActivity(in);
            }
        });


        sendPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString().trim();
                if (!email.isEmpty()) {
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgotPassword.this, "E-mail de redefinição de senha enviado.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(forgotPassword.this, "Falha ao enviar o e-mail de redefinição.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(forgotPassword.this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
