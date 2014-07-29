package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoIpiEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIpiDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoIpiBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIpiBusinessImpl implements Serializable,
		NfeDetalheImpostoIpiBusiness<NfeDetalheImpostoIpiEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoIpiDAO mainDao;

	public void setMainDao(NfeDetalheImpostoIpiDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoIpiEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoIpiEntity> search(
			NfeDetalheImpostoIpiEntity entity) throws Exception {
		List<NfeDetalheImpostoIpiEntity> auxLista = new ArrayList<NfeDetalheImpostoIpiEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoIpiEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoIpiEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoIpiEntity entity = this.mainDao.find(id);

		return entity;
	}

}