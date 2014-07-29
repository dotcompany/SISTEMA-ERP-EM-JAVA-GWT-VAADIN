package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoArmamentoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetEspecificoArmamentoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoArmamentoBusinessImpl implements Serializable,
		NfeDetEspecificoArmamentoBusiness<NfeDetEspecificoArmamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoArmamentoDAO mainDao;

	public void setMainDao(NfeDetEspecificoArmamentoDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetEspecificoArmamentoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetEspecificoArmamentoEntity> search(
			NfeDetEspecificoArmamentoEntity entity) throws Exception {
		List<NfeDetEspecificoArmamentoEntity> auxLista = new ArrayList<NfeDetEspecificoArmamentoEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetEspecificoArmamentoEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetEspecificoArmamentoEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		NfeDetEspecificoArmamentoEntity entity = this.mainDao.find(id);

		return entity;
	}

}