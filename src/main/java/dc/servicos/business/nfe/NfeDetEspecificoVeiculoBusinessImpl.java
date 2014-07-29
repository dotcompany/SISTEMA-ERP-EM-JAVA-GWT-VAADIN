package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoVeiculoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetEspecificoVeiculoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoVeiculoBusinessImpl implements Serializable,
		NfeDetEspecificoVeiculoBusiness<NfeDetEspecificoVeiculoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoVeiculoDAO mainDao;

	public void setMainDao(NfeDetEspecificoVeiculoDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetEspecificoVeiculoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetEspecificoVeiculoEntity> search(
			NfeDetEspecificoVeiculoEntity entity) throws Exception {
		List<NfeDetEspecificoVeiculoEntity> auxLista = new ArrayList<NfeDetEspecificoVeiculoEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetEspecificoVeiculoEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetEspecificoVeiculoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDetEspecificoVeiculoEntity entity = this.mainDao.find(id);

		return entity;
	}

}