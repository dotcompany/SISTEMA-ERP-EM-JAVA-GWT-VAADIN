package dc.model.business.geral.pessoal;

import java.util.List;

import dc.entidade.geral.PessoaEntity;
import dc.model.business.AbstractBusiness;

public interface PessoaContatoBusiness<T> extends AbstractBusiness<T> {

	public List<T> list(PessoaEntity entity);

}