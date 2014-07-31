package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIcmsDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoIcmsBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIcmsBusinessImpl implements Serializable,
		NfeDetalheImpostoIcmsBusiness<NfeDetalheImpostoIcmsEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoIcmsDAO mainDao;

	public void setMainDao(NfeDetalheImpostoIcmsDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoIcmsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoIcmsEntity> search(
			NfeDetalheImpostoIcmsEntity entity) throws Exception {
		List<NfeDetalheImpostoIcmsEntity> auxLista = new ArrayList<NfeDetalheImpostoIcmsEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoIcmsEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoIcmsEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoIcmsEntity entity = this.mainDao.find(id);

		return entity;
	}

}