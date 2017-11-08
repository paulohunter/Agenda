package br.com.evolucao.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.evolucao.agenda.dao.AlunoDAO;
import br.com.evolucao.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        //criando intent pra abrir o formulário
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno !=null) {
            helper.preencheFormulario(aluno);
        }


        helper = new FormularioHelper(this);

            /*Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(FormularioActivity.this, "Botao clicado!", Toast.LENGTH_LONG).show();
                    finish();
                }
            });     ANTIGO BOTAO SALVAR*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Criando menu na lateral superior esquerda do formulario
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:


                Aluno aluno = helper.pegaAluno();
                //inserir no banco
                AlunoDAO dao = new AlunoDAO(this);
                //condicao para se ele tiver id, editar, se nao, adicionar um novo
                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    //criar metodo (aquando da o alt enter escreveer o inset no aluno dao
                    dao.insere(aluno);
                }
                //fechar conexao
                dao.close();
            Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " Salvo!", Toast.LENGTH_LONG).show();

                //conexao com o banco
                //faz uma query para salvar o aluno que pega do formulário
                //fecha o banco
                /*
                 foi criado a classe formulário Helper

                EditText campoNome = (EditText) findViewById(R.id.formulario_nome);
                String nome = campoNome.getText().toString();

                EditText campoCurso = (EditText) findViewById(R.id.formulario_curso);
                String curso = campoCurso.getText().toString();

                EditText campoTelefone = (EditText) findViewById(R.id.formulario_telefone);
                String telefone = campoTelefone.getText().toString();

                EditText campoEmail = (EditText) findViewById(R.id.formulario_email);
                String email = campoEmail.getText().toString();    ANTES DE FAZER A CLASSE  */

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
