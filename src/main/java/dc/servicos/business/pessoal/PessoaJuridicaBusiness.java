package dc.servicos.business.pessoal;

import java.util.List;

import dc.model.business.AbstractBusiness;

public interface PessoaJuridicaBusiness<T> extends AbstractBusiness<T> {

	public List<T> find(String s) throws Exception;

	public List<T> listAll() throws Exception;

	public List<T> search(T entity) throws Exception;

}