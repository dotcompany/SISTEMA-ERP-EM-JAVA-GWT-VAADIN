package dc.model.dao.ordemservico;

import java.util.List;

import dc.entidade.ordemservico.CarroEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface ICarroDAO extends AbstractDAO<CarroEntity> {

	public List<CarroEntity> list() throws Exception;

}