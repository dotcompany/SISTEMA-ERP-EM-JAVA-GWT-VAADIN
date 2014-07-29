package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.servicos.dao.nfe.NfeDetEspecificoMedicamentoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDetEspecificoMedicamentoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDetEspecificoMedicamentoBusinessImpl implements Serializable,
		NfeDetEspecificoMedicamentoBusiness<NfeDetEspecificoMedicamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDetEspecificoMedicamentoDAO mainDao;

	public void setMainDao(NfeDetEspecificoMedicamentoDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDetEspecificoMedicamentoEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDetEspecificoMedicamentoEntity> search(
			NfeDetEspecificoMedicamentoEntity entity) throws Exception {
		List<NfeDetEspecificoMedicamentoEntity> auxLista = new ArrayList<NfeDetEspecificoMedicamentoEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDetEspecificoMedicamentoEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDetEspecificoMedicamentoEntity find(Serializable id)
			throws Exception {
		// TODO Auto-generated method stub
		NfeDetEspecificoMedicamentoEntity entity = this.mainDao.find(id);

		return entity;
	}

}