package br.com.ffujihara.avaliacao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pauta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String desc;

	private Long tempoSessao;
	
	private Boolean emSessão;
	
	private LocalDateTime inicioSessao;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(Long sessao) {
		this.tempoSessao = sessao;
	}

	public Boolean getEmSessão() {
		return emSessão;
	}

	public void setEmSessão(Boolean emSessão) {
		this.emSessão = emSessão;
	}

	public LocalDateTime getInicioSessao() {
		return inicioSessao;
	}

	public void setInicioSessao(LocalDateTime inicioSessao) {
		this.inicioSessao = inicioSessao;
	}

}
