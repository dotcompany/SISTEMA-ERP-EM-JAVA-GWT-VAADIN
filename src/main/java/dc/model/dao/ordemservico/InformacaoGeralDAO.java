package dc.model.dao.ordemservico;

import dc.entidade.ordemservico.InformacaoGeralEntity;
import dc.entidade.ordemservico.OrdemServicoEntity;
import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
public interface InformacaoGeralDAO<T> extends AbstractDAO<T> {
	InformacaoGeralEntity findByOrdemServico(OrdemServicoEntity t);
}