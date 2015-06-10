package dc.model.dao.geral.pessoal;

import java.util.List;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.AbstractDAO;

public interface ColaboradorDAO<T> extends AbstractDAO<T> {
	public List<ColaboradorEntity> listaVendedores();
	public List<ColaboradorEntity> listaTecnicos();
}