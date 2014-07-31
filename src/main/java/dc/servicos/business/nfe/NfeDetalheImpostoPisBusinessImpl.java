package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoPisDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoPisBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoPisBusinessImpl implements Serializable,
		NfeDetalheImpostoPisBusiness<NfeDetalheImpostoPisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoPisDAO mainDao;

	public void setMainDao(NfeDetalheImpostoPisDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoPisEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoPisEntity> search(
			NfeDetalheImpostoPisEntity entity) throws Exception {
		List<NfeDetalheImpostoPisEntity> auxLista = new ArrayList<NfeDetalheImpostoPisEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoPisEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoPisEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoPisEntity entity = this.mainDao.find(id);

		return entity;
	}

}