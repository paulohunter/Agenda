package br.com.evolucao.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.evolucao.agenda.modelo.Aluno;

/**
 * Created by Paulo on 22/09/2017.
 */

public class FormularioHelper {

    /* pra fazer o findviewbyid tem que fazer primeiro a referencia aqui*/

    private final EditText campoNome;
    private final EditText campoCurso;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoCurso = (EditText) activity.findViewById(R.id.formulario_curso);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);

    }

    public Aluno pegaAluno() {
        Aluno aluno = new Aluno();
            aluno.setNome(campoNome.getText().toString());
            aluno.setCurso(campoCurso.getText().toString());
            aluno.setTelefone(campoTelefone.getText().toString());
            aluno.setEmail(campoEmail.getText().toString());
            aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }
}
