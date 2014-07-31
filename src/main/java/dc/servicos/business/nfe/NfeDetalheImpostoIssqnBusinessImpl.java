package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIssqnDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoIssqnBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIssqnBusinessImpl implements Serializable,
		NfeDetalheImpostoIssqnBusiness<NfeDetalheImpostoIssqnEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoIssqnDAO mainDao;

	public void setMainDao(NfeDetalheImpostoIssqnDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoIssqnEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoIssqnEntity> search(
			NfeDetalheImpostoIssqnEntity entity) throws Exception {
		List<NfeDetalheImpostoIssqnEntity> auxLista = new ArrayList<NfeDetalheImpostoIssqnEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoIssqnEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoIssqnEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoIssqnEntity entity = this.mainDao.find(id);

		return entity;
	}

}