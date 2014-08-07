package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.servicos.dao.nfe.NfeCabecalhoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeCabecalhoBusinessImpl")
@Transactional(readOnly = true)
public class NfeCabecalhoBusinessImpl implements Serializable,
		NfeCabecalhoBusiness<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeCabecalhoDAO mainDao;

	public void setMainDao(NfeCabecalhoDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeCabecalhoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Transactional
	@Override
	public List<NfeCabecalhoEntity> find(String s) throws Exception {
		List<NfeCabecalhoEntity> auxLista = this.mainDao.find(s);

		return auxLista;
	}

	@Transactional
	@Override
	public NfeCabecalhoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeCabecalhoEntity entity = this.mainDao.find(id);

		return entity;
	}

	@Transactional
	@Override
	public List<NfeCabecalhoEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		List<NfeCabecalhoEntity> auxLista = this.mainDao.listAll();

		return auxLista;
	}

	@Transactional
	@Override
	public List<NfeCabecalhoEntity> search(NfeCabecalhoEntity entity)
			throws Exception {
		List<NfeCabecalhoEntity> auxLista = new ArrayList<NfeCabecalhoEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeCabecalhoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

}