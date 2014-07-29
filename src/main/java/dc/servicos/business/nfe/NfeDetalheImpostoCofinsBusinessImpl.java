package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoCofinsDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoCofinsBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoCofinsBusinessImpl implements Serializable,
		NfeDetalheImpostoCofinsBusiness<NfeDetalheImpostoCofinsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoCofinsDAO mainDao;

	public void setMainDao(NfeDetalheImpostoCofinsDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoCofinsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoCofinsEntity> search(
			NfeDetalheImpostoCofinsEntity entity) throws Exception {
		List<NfeDetalheImpostoCofinsEntity> auxLista = new ArrayList<NfeDetalheImpostoCofinsEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoCofinsEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoCofinsEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoCofinsEntity entity = this.mainDao.find(id);

		return entity;
	}

}