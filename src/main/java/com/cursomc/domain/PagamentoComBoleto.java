package com.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cursomc.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	private Date dataVenciemnto;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVenciemnto, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVenciemnto = dataVenciemnto;
	}

	public Date getDataVenciemnto() {
		return dataVenciemnto;
	}

	public void setDataVenciemnto(Date dataVenciemnto) {
		this.dataVenciemnto = dataVenciemnto;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
}
