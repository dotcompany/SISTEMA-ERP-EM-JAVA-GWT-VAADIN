package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaFisicaEntity;
import dc.servicos.dao.geral.pessoal.PessoaFisicaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("pessoaFisicaBusinessImpl")
@Transactional(readOnly = true)
public class PessoaFisicaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaFisicaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PessoaFisicaDAO mainDao;

	public void setMainDao(PessoaFisicaDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(PessoaFisicaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Transactional
	@Override
	public List<PessoaFisicaEntity> find(String s) throws Exception {
		// List<PessoaEntity> auxLista = this.mainDao.find(s);
		// List<PessoaEntity> auxLista = this.mainDao.find(id)

		return null;
	}

	@Transactional
	@Override
	public PessoaFisicaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		PessoaFisicaEntity entity = this.mainDao.find(id);

		return entity;
	}

	@Transactional
	@Override
	public List<PessoaFisicaEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		// List<PessoaEntity> auxLista = this.mainDao.listAll();

		return null;
	}

	@Transactional
	@Override
	public List<PessoaFisicaEntity> search(PessoaFisicaEntity entity)
			throws Exception {
		List<PessoaFisicaEntity> auxLista = new ArrayList<PessoaFisicaEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(PessoaFisicaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

}