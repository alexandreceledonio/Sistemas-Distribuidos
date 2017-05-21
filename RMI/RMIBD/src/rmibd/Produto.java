/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmibd;

import java.io.Serializable;

public class Produto implements Serializable {

	private String codigo;
	private String nome;
	private String quantidade;

	public Produto(String codigo, String nome, String quantidade) {

		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

}

