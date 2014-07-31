package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.servicos.dao.nfe.NfeDetalheImpostoIiDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetalheImpostoIiBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetalheImpostoIiBusinessImpl implements Serializable,
		NfeDetalheImpostoIiBusiness<NfeDetalheImpostoIiEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetalheImpostoIiDAO mainDao;

	public void setMainDao(NfeDetalheImpostoIiDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetalheImpostoIiEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetalheImpostoIiEntity> search(
			NfeDetalheImpostoIiEntity entity) throws Exception {
		List<NfeDetalheImpostoIiEntity> auxLista = new ArrayList<NfeDetalheImpostoIiEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetalheImpostoIiEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetalheImpostoIiEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetalheImpostoIiEntity entity = this.mainDao.find(id);

		return entity;
	}

}