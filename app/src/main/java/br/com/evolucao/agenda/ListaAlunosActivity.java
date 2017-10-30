package br.com.evolucao.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_lista_alunos);

        String [] alunos = {"Daniel","Ronaldo", "Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe"};
        ListView listaAlunos = (ListView) findViewById(R.id.Lista_alunos); /*Chamando list view do xml*/

        ArrayAdapter<String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

        //definindo botao para navegar ate o formulario
        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });
    }
}
