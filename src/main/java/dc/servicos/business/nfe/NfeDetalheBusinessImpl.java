package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.nfe.NfeDetalheDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheBusinessImpl implements Serializable,
		NfeDetalheBusiness<NfeDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheDAO mainDao;

	public void setMainDao(NfeDetalheDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheEntity> search(NfeDetalheEntity entity)
			throws Exception {
		List<NfeDetalheEntity> auxLista = new ArrayList<NfeDetalheEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheEntity entity = this.mainDao.find(id);

		return entity;
	}

}