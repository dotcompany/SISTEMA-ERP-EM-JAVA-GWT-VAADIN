package dc.model.dao.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.PessoaEntity;
import dc.model.dao.AbstractDAO;

public interface PessoaEnderecoDAO<T> extends AbstractDAO<T> {

	public List<T> list(PessoaEntity entity);

}