package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoCombustivelDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetEspecificoCombustivelBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoCombustivelBusinessImpl implements Serializable,
		NfeDetEspecificoCombustivelBusiness<NfeDetEspecificoCombustivelEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoCombustivelDAO mainDao;

	public void setMainDao(NfeDetEspecificoCombustivelDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetEspecificoCombustivelEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetEspecificoCombustivelEntity> search(
			NfeDetEspecificoCombustivelEntity entity) throws Exception {
		List<NfeDetEspecificoCombustivelEntity> auxLista = new ArrayList<NfeDetEspecificoCombustivelEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetEspecificoCombustivelEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetEspecificoCombustivelEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		NfeDetEspecificoCombustivelEntity entity = this.mainDao.find(id);

		return entity;
	}

}