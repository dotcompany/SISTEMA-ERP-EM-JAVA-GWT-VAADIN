package dc.entidade.contabilidade.demonstrativo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class DreEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public DreEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}