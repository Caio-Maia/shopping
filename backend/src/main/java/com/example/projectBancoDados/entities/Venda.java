package com.example.projectBancoDados.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_venda")
public class Venda implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "venda")
    private List<ProdutoVenda> produtos;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "clienteId", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "vendedorId", referencedColumnName = "id", nullable = false)
    private Vendedor vendedor;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "operadoraId", referencedColumnName = "id")
    private Operadora operadora;

    @Column(nullable = false)
    private Integer parcelas;
    private BigDecimal valor;
    private LocalDateTime dataVenda;

    public Venda() {
    }

    public Venda(Long id, List<ProdutoVenda> produtos, Cliente cliente, Vendedor vendedor, Operadora operadora, Integer parcelas, BigDecimal valor, LocalDateTime dataVenda) {
        this.id = id;
        this.produtos = produtos;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.operadora = operadora;
        this.parcelas = parcelas;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProdutoVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Operadora getOperadora() {
        return operadora;
    }

    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}
