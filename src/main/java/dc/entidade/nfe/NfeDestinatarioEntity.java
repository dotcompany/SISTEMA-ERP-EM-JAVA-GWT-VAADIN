package dc.entidade.nfe;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "nfe_destinatario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDestinatarioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_destinatario_id_seq")
	@SequenceGenerator(name = "nfe_destinatario_id_seq", sequenceName = "nfe_destinatario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Field
	@Column(name = "razao_social")
	private String razaoSocial;

	@Field
	@Column(name = "logradouro")
	private String logradouro;

	@Field
	@Column(name = "numero")
	private String numero;

	@Field
	@Column(name = "complemento")
	private String complemento;

	@Field
	@Column(name = "bairro")
	private String bairro;

	@Field
	@Column(name = "codigo_municipio")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "nome_municipio")
	private String nomeMunicipio;

	@Field
	@Column(name = "uf")
	private String uf;

	@Field
	@Column(name = "cep")
	private String cep;

	@Field
	@Column(name = "codigo_pais")
	private Integer codigoPais;

	@Field
	@Column(name = "nome_pais")
	private String nomePais;

	@Field
	@Column(name = "telefone")
	private String telefone;

	@Field
	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	@Field
	@Column(name = "suframa")
	private String suframa;

	@Field
	@Column(name = "email")
	private String email;

	// id_nfe_cabecalho integer NOT NULL,
	// id_empresa integer,

	public NfeDestinatarioEntity() {
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