package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaJuridicaEntity;
import dc.servicos.dao.geral.pessoal.PessoaJuridicaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("pessoaJuridicaBusinessImpl")
@Transactional(readOnly = true)
public class PessoaJuridicaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaJuridicaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PessoaJuridicaDAO mainDao;

	public void setMainDao(PessoaJuridicaDAO mainDao) {
		this.mainDao = mainDao;
	}

	@Transactional
	@Override
	public void delete(PessoaJuridicaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.delete(entity);
	}

	@Transactional
	@Override
	public List<PessoaJuridicaEntity> find(String s) throws Exception {
		// List<PessoaEntity> auxLista = this.mainDao.find(s);
		// List<PessoaEntity> auxLista = this.mainDao.find(id)

		return null;
	}

	@Transactional
	@Override
	public PessoaJuridicaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		PessoaJuridicaEntity entity = this.mainDao.find(id);

		return entity;
	}

	@Transactional
	@Override
	public List<PessoaJuridicaEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		// List<PessoaEntity> auxLista = this.mainDao.listAll();

		return null;
	}

	@Transactional
	@Override
	public List<PessoaJuridicaEntity> search(PessoaJuridicaEntity entity)
			throws Exception {
		List<PessoaJuridicaEntity> auxLista = new ArrayList<PessoaJuridicaEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(PessoaJuridicaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.mainDao.save(entity);
	}

}