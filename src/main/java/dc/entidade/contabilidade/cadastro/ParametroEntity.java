package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import dc.entidade.framework.AbstractMultiEmpresaModel;

public class ParametroEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

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

	public ParametroEntity() {
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