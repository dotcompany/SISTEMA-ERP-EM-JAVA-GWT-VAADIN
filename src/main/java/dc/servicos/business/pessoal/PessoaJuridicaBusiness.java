package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.List;

import dc.servicos.business.AbstractBusiness;

public interface PessoaJuridicaBusiness<T> extends AbstractBusiness<T> {

	public List<T> find(String s) throws Exception;

	public T find(Serializable id) throws Exception;

	public List<T> listAll() throws Exception;

	public List<T> search(T entity) throws Exception;

}