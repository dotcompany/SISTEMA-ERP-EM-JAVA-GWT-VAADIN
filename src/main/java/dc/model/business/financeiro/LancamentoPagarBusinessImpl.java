package dc.model.business.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.Container.Filter;

import dc.entidade.financeiro.LancamentoPagar;
import dc.entidade.framework.FmMenu;
import dc.servicos.dao.financeiro.LancamentoPagarDAOf;

@Service
@Transactional(readOnly = true)
public class LancamentoPagarBusinessImpl implements LancamentoPagarBusiness<LancamentoPagar>, Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoPagarDAOf<LancamentoPagar> dao;

	@Override
	public Class<LancamentoPagar> getEntityClass() {
		return LancamentoPagar.class;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(LancamentoPagar t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAll(List<Serializable> list) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName()+ "] deleteAll");

			this.dao.deleteAllByIds(list);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		
	}

	@Override
	public LancamentoPagar find(Serializable id) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			LancamentoPagar ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public LancamentoPagar find(LancamentoPagar t) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagar> find(String s) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagar> findAll() throws Exception {
		
		try {
			List<LancamentoPagar> auxLista = this.dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<LancamentoPagar> findAll(LancamentoPagar t) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagar> fullTextSearch(String valor) throws Exception {
		return null;
	}

	@Override
	public List<LancamentoPagar> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LancamentoPagar> fullTextSearch(String valor,
			String[] sortingFields, boolean[] states, List<Filter> filters)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> getAll(Class<E> type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(LancamentoPagar t) throws Exception {
		
	}

	@Override
	@Transactional(readOnly = false)
	public <E> void saveOrUpdate(E o) throws Exception {
		
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] saveOrUpdate");

			LancamentoPagar ent = (LancamentoPagar) o;

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
		
	}

	@Override
	public List<LancamentoPagar> getAllForComboSelect(
			Class<LancamentoPagar> type, int idEmpresa, FmMenu menu,
			String typeSelected, Integer idSelected) {
		// TODO Auto-generated method stub
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,idSelected);
	}

	@Override
	public List<LancamentoPagar> getAllForCombo(Class<LancamentoPagar> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<LancamentoPagar> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		
		return dao.comboTextSearch(value, menu, getAll);
	}

}
