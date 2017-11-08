package br.com.evolucao.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.evolucao.agenda.dao.AlunoDAO;
import br.com.evolucao.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_lista_alunos);
        listaAlunos = (ListView) findViewById(R.id.Lista_alunos); /*Chamando list view do xml*/

        //Para editar cadastro
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                //Toast.makeText(ListaAlunosActivity.this, "Aluno " + aluno.getNome() + " clicado", Toast.LENGTH_SHORT).show();
                //mudando tela
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class );
                //Pendurando aluno na itent e recuperando
                intentVaiProFormulario.putExtra("aluno",aluno);
                startActivity(intentVaiProFormulario);


            }
        });



        //conexao com banco de dados
        //faz uma busca no banco para trazer alunos
        // popular o arry de strings
        //fechar conexao

        //----------------
        //Instanciar o DAO para conexao banco de dados
        // por fim criar metodo carregaLista pelo refactory


        // TESTE PARA EBIBIR ALUNOS SEM BANCO -- String [] alunos = {"Daniel","Ronaldo", "Wanderson","Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe","Ronaldo", "Jeferson", "Felipe"};
        // ListView listaAlunos = (ListView) findViewById(R.id.Lista_alunos); /*Chamando list view do xml*/
        //  ArrayAdapter<String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_list_item_1, alunos);
        //  listaAlunos.setAdapter(adapter);

        //definindo botao para navegar ate o formulario

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        //implementando opçao para excluir aluno
        registerForContextMenu(listaAlunos);
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        // comentado para criar a opção de apagar, colado no oncreate -- ListView listaAlunos = (ListView) findViewById(R.id.Lista_alunos); /*Chamando list view do xml*/
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //logo apos criar o register

    @Override
    //menu de contextoo, pressionar para aparecer o botão para deletas
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                //cod para deletar realmente o aluno

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();
                carregaLista();

                /*Toast.makeText(ListaAlunosActivity.this, "Deletar o Aluno " + aluno.getNome(), Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });
    }

}

