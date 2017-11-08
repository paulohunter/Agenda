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
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoCurso = (EditText) activity.findViewById(R.id.formulario_curso);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();

    }

    public Aluno pegaAluno() {

            aluno.setNome(campoNome.getText().toString());
            aluno.setCurso(campoCurso.getText().toString());
            aluno.setTelefone(campoTelefone.getText().toString());
            aluno.setEmail(campoEmail.getText().toString());
            aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoCurso.setText(aluno.getCurso());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        campoNota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
