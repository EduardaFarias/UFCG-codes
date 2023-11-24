package com.ufcg.psoft.commerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.*;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.*;
import com.ufcg.psoft.commerce.service.pedido.PedidoNotificarEstabelecimentoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
@DisplayName("Testes do serviço de notificação")
public class PedidoNotificarEstabelecimentoServiceTests {

    @Autowired
    PedidoNotificarEstabelecimentoService pedidoNotificarEstabelecimentoService;

    @Autowired
    SaborRepository saborRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    Sabor sabor;
    Pizza pizza;
    Pedido pedido;
    Cliente cliente;
    Entregador entregador;
    Estabelecimento estabelecimento;
    PedidoPostPutRequestDTO pedidoPostPutRequestDTO;

    @BeforeEach
    void setUp() {

        sabor = saborRepository.save(Sabor.builder()
                .nome("Calabresa")
                .tipo("salgado")
                .precoM(20.0)
                .precoG(30.0)
                .disponivel(true)
                .build()
        );

        pizza = pizzaRepository.save(Pizza.builder()
                .sabor1(sabor)
                .tamanho("media")
                .build()
        );

        cliente = clienteRepository.save(Cliente.builder()
                .nome("Fulano")
                .codigoAcesso("123456")
                .endereco("Ruinha")
                .build()
        );

        entregador = entregadorRepository.save(Entregador.builder()
                .codigoAcesso("234567")
                .nome("Junin")
                .placaVeiculo("AAA-123")
                .corVeiculo("Azul")
                .tipoVeiculo("carro")
                .build()
        );
        estabelecimento = estabelecimentoRepository.save(Estabelecimento.builder()
                .codigoAcesso("345678")
                .telefone("3333-3333")
                .endereco("Rua afonso campos")
                .nome("PitsS")
                .horario("23:00 - 12:59")
                .build()
        );

        pedido = pedidoRepository.save(Pedido.builder()
                .preco(50.0)
                .enderecoEntrega("Rua trecho verde")
                .clienteId(cliente.getId())
                .estabelecimentoId(estabelecimento.getId())
                .statusPagamento(true)
                .statusEntrega("Pedido entregue")
                .entregadorId(entregador.getId())
                .pizzas(List.of(pizza))
                .build()
        );

        pedidoPostPutRequestDTO = PedidoPostPutRequestDTO.builder()
                .enderecoEntrega(pedido.getEnderecoEntrega())
                .pizzas(pedido.getPizzas())
                .build();
    }

    @AfterEach
    void tearDown() {
        clienteRepository.deleteAll();
        estabelecimentoRepository.deleteAll();
        entregadorRepository.deleteAll();
        saborRepository.deleteAll();
        pizzaRepository.deleteAll();
        pedidoRepository.deleteAll();
    }

    @Test
    @DisplayName("Quando notificamos estabelecimento sobre pedido válido")
    void quandoNotificamosEstabelecimentoPedidoValido() {
        // Arrange
        // nenhuma necessidade além do setUp()

        // Act
        String notificacao = pedidoNotificarEstabelecimentoService.notificar(pedido.getId());

        // Assert
        String notificacaoEsperada = "Pedido entregue! : "
                + estabelecimento.getNome() + ", o pedido -"
                + pedido.getId() + "- foi marcado como entregue";
        assertEquals(notificacaoEsperada, notificacao);
    }
}