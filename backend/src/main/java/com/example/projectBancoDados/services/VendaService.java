package com.example.projectBancoDados.services;

import com.example.projectBancoDados.dto.produto.ProdutoVendaBody;
import com.example.projectBancoDados.dto.venda.VendaRequest;
import com.example.projectBancoDados.dto.venda.VendaResponse;
import com.example.projectBancoDados.entities.*;
import com.example.projectBancoDados.exceptions.NotFoundException;
import com.example.projectBancoDados.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;
    private final OperadoraRepository operadoraRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        VendedorRepository vendedorRepository,
                        ProdutoRepository produtoRepository,
                        OperadoraRepository operadoraRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.produtoRepository = produtoRepository;
        this.operadoraRepository = operadoraRepository;
    }

    @Transactional(readOnly = true)
    public Page<VendaResponse> findAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC, "nome");
        List<Venda> list = vendaRepository.findAll();
        return new PageImpl<>(list.stream().map(x -> new VendaResponse(x)).collect(Collectors.toList()), pageRequest, size);
    }

    public VendaResponse findById(Long id) {
        Venda entity = vendaRepository.findById(id).orElseThrow(NotFoundException::new);
        return new VendaResponse(entity);
    }

    @Transactional
    public VendaResponse insert(VendaRequest dto) {
        if(clienteRepository.existsById(dto.getClienteId()) && vendedorRepository.existsById(dto.getVendedorId())) {
            Venda entity = new Venda();
            List<ProdutoVenda> entityProdutos = new ArrayList<>();
            BigDecimal precoTotal = new BigDecimal(0);

            if(dto.getParcelas() > 0 && dto.getOperadoraId() == null) throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Deve existir uma operadora para parcelar vendas.");

            for(ProdutoVendaBody produto : dto.getProdutos()) {

                if(!produtoRepository.existsById(produto.getId())) throw new NotFoundException();

                Produto produtoVendido = produtoRepository.getById(produto.getId());
                int novaQuantidade = produtoVendido.getQuantidade() - produto.getQuantidade();

                if(novaQuantidade < 0) throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Quantidade para compra deve ser maior que 0");
                entityProdutos.add(new ProdutoVenda(produtoVendido, produto.getQuantidade(), entity));
                produtoVendido.setQuantidade(novaQuantidade);
                produtoRepository.save(produtoVendido);
                precoTotal = precoTotal.add(produtoVendido.getPreco()).multiply(new BigDecimal(produto.getQuantidade()));
            }
            entity.setProdutos(entityProdutos);
            Cliente cliente = clienteRepository.getById(dto.getClienteId());
            entity.setCliente(cliente);
            Vendedor vendedor = vendedorRepository.getById(dto.getVendedorId());
            entity.setVendedor(vendedor);
            if(dto.getOperadoraId() != null) {
                if (operadoraRepository.existsById(dto.getOperadoraId())) {
                    Operadora operadora = operadoraRepository.getById(dto.getOperadoraId());
                    entity.setOperadora(operadora);
                }
            }
            entity.setParcelas(dto.getParcelas());
            entity.setValor(precoTotal.setScale(2, RoundingMode.HALF_EVEN));
            entity.setDataVenda(LocalDateTime.now());
            vendaRepository.save(entity);
            return new VendaResponse(entity);
        } else throw new NotFoundException();
    }
}
