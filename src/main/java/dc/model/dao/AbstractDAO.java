package dc.model.dao;

import java.io.Serializable;
import java.util.List;

import com.vaadin.data.Container.Filter;

import dc.entidade.framework.FmMenu;

public interface AbstractDAO<T> {

	public void delete(T obj);

	public void deleteAllByIds(List<Serializable> ids);

	public void deleteAll(List<Serializable> objs);

	public T find(Serializable id);

	public List<T> fullTextSearch(String valor);

	public List<T> fullTextSearch(String valor, int first, int pageSize,
			String[] sortingFields, boolean[] sortingStates,
			List<Filter> filters);

	public List<T> fullTextSearch(String valor, String[] sortingFields,
			boolean[] states, List<Filter> filters);

	public <E> List<E> getAll(final Class<E> type);

	public List<T> getAllForCombo(final Class<T> type, int idEmpresa,
			FmMenu menu, Boolean getAll);

	public List<T> getAllForComboSelect(final Class<T> type, int idEmpresa,
			FmMenu menu, final String typeSelected, Integer idSelected);

	public String[] getDefaultSearchFields();

	public Class<T> getEntityClass();

	public boolean isConsultaMultiEmpresa(
			@SuppressWarnings("rawtypes") Class c, FmMenu ent, Boolean getAll);

	public boolean isConsultaMultiEmpresa(
			@SuppressWarnings("rawtypes") Class c, FmMenu ent);

	public boolean isMultiEmpresa(@SuppressWarnings("rawtypes") Class c);

	public List<T> listaTodos();

	public List<T> procuraNomeContendo(String query);

	public List<T> query(String q);

	public void save(T obj);

	public <E> void saveOrUpdate(final E o);

}