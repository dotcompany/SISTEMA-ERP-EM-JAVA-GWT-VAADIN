package dc.servicos.business.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.geral.pessoal.PessoaDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service("pessoaBusinessImpl")
@Transactional(readOnly = true)
public class PessoaBusinessImpl implements Serializable,
		PessoaBusiness<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private PessoaDAO pessoaDAO;

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

	@Transactional
	@Override
	public void delete(PessoaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.pessoaDAO.delete(entity);
	}

	@Transactional
	@Override
	public List<PessoaEntity> find(String s) throws Exception {
		// List<PessoaEntity> auxLista = this.mainDao.find(s);
		// List<PessoaEntity> auxLista = this.mainDao.find(id)

		return null;
	}

	@Transactional
	@Override
	public PessoaEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		PessoaEntity entity = this.pessoaDAO.find(id);

		// entity.setPessoaFisica(this.pessoaFisicaDao.find(id));
		// entity.setPessoaJuridica(this.pessoaJuridicaDao.find(id));

		return entity;
	}

	@Transactional
	@Override
	public List<PessoaEntity> listAll() throws Exception {
		// TODO Auto-generated method stub
		// List<PessoaEntity> auxLista = this.mainDao.listAll();

		return null;
	}

	@Transactional
	@Override
	public List<PessoaEntity> search(PessoaEntity entity) throws Exception {
		List<PessoaEntity> auxLista = new ArrayList<PessoaEntity>();

		return auxLista;
	}

	@Transactional
	@Override
	public void saveOrUpdate(PessoaEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.pessoaDAO.save(entity);
	}

}