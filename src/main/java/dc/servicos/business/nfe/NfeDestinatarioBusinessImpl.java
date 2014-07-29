package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.servicos.dao.nfe.NfeDestinatarioDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDestinatarioBusinessImpl")
@Transactional(readOnly = true)
public class NfeDestinatarioBusinessImpl implements Serializable,
		NfeDestinatarioBusiness<NfeDestinatarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDestinatarioDAO mainDao;

	public void setMainDao(NfeDestinatarioDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDestinatarioEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDestinatarioEntity> search(NfeDestinatarioEntity entity)
			throws Exception {
		List<NfeDestinatarioEntity> auxLista = new ArrayList<NfeDestinatarioEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDestinatarioEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDestinatarioEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDestinatarioEntity entity = this.mainDao.find(id);

		return entity;
	}

}