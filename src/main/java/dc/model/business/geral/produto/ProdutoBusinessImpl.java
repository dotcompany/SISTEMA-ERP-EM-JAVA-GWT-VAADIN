package dc.model.business.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;
import com.vaadin.data.Container.Filter;

import dc.control.util.ObjectUtils;
import dc.entidade.framework.FmMenu;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.dao.geral.diverso.AlmoxarifadoDAO;
import dc.model.dao.geral.produto.GrupoDAO;
import dc.model.dao.geral.produto.MarcaDAO;
import dc.model.dao.geral.produto.NcmDAO;
import dc.model.dao.geral.produto.ProdutoDAO;
import dc.model.dao.geral.produto.SubGrupoDAO;
import dc.model.dao.geral.produto.UnidadeProdutoDAO;
import dc.model.dao.tributario.GrupoTributarioDAO;
import dc.model.dao.tributario.IcmsCustomizadoDAO;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

@Service
@Transactional(readOnly = true)
public class ProdutoBusinessImpl implements Serializable,
		ProdutoBusiness<ProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ProdutoBusinessImpl.class);

	@Autowired
	private ProdutoDAO<ProdutoEntity> dao;

	@Autowired
	private UnidadeProdutoDAO<UnidadeProdutoEntity> unidadeProdutoDAO;

	@Autowired
	private MarcaDAO<MarcaEntity> marcaDAO;

	@Autowired
	private SubGrupoDAO<SubGrupoEntity> subGrupoDAO;

	@Autowired
	private GrupoDAO<GrupoEntity> grupoDAO;

	@Autowired
	private NcmDAO<NcmEntity> ncmDAO;

	@Autowired
	private AlmoxarifadoDAO<AlmoxarifadoEntity> almoxarifadoDAO;

	@Autowired
	private IcmsCustomizadoDAO<IcmsCustomizadoCabecalhoEntity> icmsCustomizadoDAO;

	@Autowired
	private GrupoTributarioDAO<GrupoTributarioEntity> grupoTributarioDAO;

	/**
	 * **********************************************
	 */

	@Override
	public Class<ProdutoEntity> getEntityClass() {
		return ProdutoEntity.class;
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(ProdutoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public void deleteAll(List<Serializable> list) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] deleteAll");

			this.dao.deleteAllByIds(list);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ProdutoEntity find(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] find");

			ProdutoEntity ent = this.dao.find(id);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public ProdutoEntity find(ProdutoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoEntity> find(String s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoEntity> findAll() throws Exception {
		// TODO Auto-generated method stub
		try {
			List<ProdutoEntity> auxLista = this.dao.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<ProdutoEntity> findAll(ProdutoEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoEntity> fullTextSearch(String valor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoEntity> fullTextSearch(String valor, int first,
			int pageSize, String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoEntity> fullTextSearch(String valor,
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

	@Transactional(readOnly = false)
	@Override
	public void save(ProdutoEntity t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = false)
	@Override
	public <E> void saveOrUpdate(E o) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName()
					+ "] saveOrUpdate");

			ProdutoEntity ent = (ProdutoEntity) o;

			if (ObjectUtils.isNotBlank(ent.getAlmoxarifado())) {
				AlmoxarifadoEntity almoxarifado = this.almoxarifadoDAO.find(ent
						.getAlmoxarifado().getId());
				ent.setAlmoxarifado(almoxarifado);
			}

			if (ObjectUtils.isNotBlank(ent.getGrupo())) {
				GrupoEntity grupo = this.grupoDAO.find(ent.getGrupo().getId());
				ent.setGrupo(grupo);
			}

			if (ObjectUtils.isNotBlank(ent.getGrupoTributario())) {
				GrupoTributarioEntity grupoTributario = this.grupoTributarioDAO
						.find(ent.getGrupoTributario().getId());
				ent.setGrupoTributario(grupoTributario);
			}

			if (ObjectUtils.isNotBlank(ent.getMarca())) {
				MarcaEntity marca = this.marcaDAO.find(ent.getMarca().getId());
				ent.setMarca(marca);
			}

			if (ObjectUtils.isNotBlank(ent.getNcm())) {
				NcmEntity ncm = this.ncmDAO.find(ent.getNcm().getId());
				ent.setNcm(ncm);
			}

			if (ObjectUtils.isNotBlank(ent.getSubGrupo())) {
				SubGrupoEntity subGrupo = this.subGrupoDAO.find(ent
						.getSubGrupo().getId());
				ent.setSubGrupo(subGrupo);
			}

			if (ObjectUtils.isNotBlank(ent.getUnidadeProduto())) {
				UnidadeProdutoEntity unidadeProduto = this.unidadeProdutoDAO
						.find(ent.getUnidadeProduto().getId());
				ent.setUnidadeProduto(unidadeProduto);
			}

			this.dao.saveOrUpdate(ent);
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Override
	public List<ProdutoEntity> getAllForComboSelect(Class<ProdutoEntity> type,
			int idEmpresa, FmMenu menu, String typeSelected, Integer idSelected) {
		return dao.getAllForComboSelect(type, idEmpresa, menu, typeSelected,
				idSelected);
	}

	@Override
	public List<ProdutoEntity> getAllForCombo(Class<ProdutoEntity> type,
			int idEmpresa, FmMenu menu, Boolean getAll) {
		return dao.getAllForCombo(type, idEmpresa, menu, getAll);
	}

	@Override
	public List<ProdutoEntity> comboTextSearch(String value, FmMenu menu,
			Boolean getAll) {
		return dao.comboTextSearch(value, menu, getAll);
	}

	/**
	 * 
	 */

	@Override
	public List<ProdutoEntity> list() throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println(":: [" + getClass().getSimpleName() + "] list");

			List<ProdutoEntity> auxLista = this.dao.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}