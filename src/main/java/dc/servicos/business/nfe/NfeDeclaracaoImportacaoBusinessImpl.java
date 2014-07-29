package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.nfe.NfeDeclaracaoImportacaoEntity;
import dc.servicos.dao.nfe.NfeDeclaracaoImportacaoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("nfeDeclaracaoImportacaoBusinessImpl")
@Transactional(readOnly = true)
public class NfeDeclaracaoImportacaoBusinessImpl implements Serializable,
		NfeDeclaracaoImportacaoBusiness<NfeDeclaracaoImportacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private NfeDeclaracaoImportacaoDAO mainDao;

	public void setMainDao(NfeDeclaracaoImportacaoDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(NfeDeclaracaoImportacaoEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Override
	public List<NfeDeclaracaoImportacaoEntity> search(
			NfeDeclaracaoImportacaoEntity entity) throws Exception {
		List<NfeDeclaracaoImportacaoEntity> auxLista = new ArrayList<NfeDeclaracaoImportacaoEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(NfeDeclaracaoImportacaoEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

	@Transactional
	@Override
	public NfeDeclaracaoImportacaoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		NfeDeclaracaoImportacaoEntity entity = this.mainDao.find(id);

		return entity;
	}

}