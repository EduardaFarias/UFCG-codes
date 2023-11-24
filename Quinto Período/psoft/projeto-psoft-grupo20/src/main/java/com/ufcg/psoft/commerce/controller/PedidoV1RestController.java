package com.ufcg.psoft.commerce.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.pedido.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE
)
public class PedidoV1RestController {
    @Autowired
    PedidoCriarService pedidoCriarService;

    @Autowired
    PedidoAtualizarService pedidoAtualizarService;

    @Autowired
    PedidoBuscarByIdService pedidoBuscarByIdService;

    @Autowired
    PedidoBuscarTodosByIdCliente pedidoBuscarTodosByIdCliente;

    @Autowired
    PedidoBuscarTodosPedidosEstabelecimentoService pedidoBuscarTodosPedidosEstabelecimento;

    @Autowired
    EstabelecimentoBuscarPedidoByService estabelecimentoBuscarPedidoByService;

    @Autowired
    ClienteExcluirPedidoService clienteExcluirPedidoService;

    @Autowired
    ClienteExcluirTodosPedidosService clienteExcluirTodosPedidosService;

    @Autowired
    EstabelecimentoExcluirPedidoService estabelecimentoExcluirPedidoService;

    @Autowired
    EstabelecimentoExcluirTodosPedidosService estabelecimentoExcluirTodosPedidosService;

    @Autowired
    ClienteCancelarPedidoService clienteCancelarPedidoService;

    @Autowired
    PedidoConfirmarPagamentoService pedidoConfirmarPagamentoService;

    @Autowired
    ClienteConfirmarPedidoService clienteConfirmarPedidoService;

    @Autowired
    ClienteBuscarPedidoByEstabelecimentoService clienteBuscarPedidoByEstabelecimentoService;

    @Autowired
    ClienteBuscarTodosPedidosByEstabelecimentoService clienteBuscarTodosPedidosByEstabelecimentoService;

    @Autowired
    EstabelecimentoPrepararPedidoService estabelecimentoPrepararPedidoService;

    @Autowired
    EstabelecimentoAprontaPedidoService estabelecimentoAprontaPedidoService;

    @Autowired
    ClienteBuscarTodosPedidosByStatusService clienteBuscarTodosPedidosByStatusService;

    @Autowired
    EstabelecimentoAssociaPedidoEntregadorService estabelecimentoAssociaPedidoEntregadorService;
    @PostMapping("/post")
    public ResponseEntity<?> criarPedido(
            @RequestBody @Valid PedidoPostPutRequestDTO pedidoPostPutRequestDTO,
            @RequestParam Long clienteId,
            @RequestParam String clienteCodigoAcesso,
            @RequestParam Long estabelecimentoId
            ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoCriarService.criarPedido(pedidoPostPutRequestDTO, clienteId, clienteCodigoAcesso, estabelecimentoId));
    }

    @PutMapping("/put")
    public ResponseEntity<?> atualizarPedido(
            @RequestParam Long pedidoId,
            @RequestParam String codigoAcessoCliente,
            @RequestBody @Valid PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoAtualizarService.atualizarPedido(pedidoId, codigoAcessoCliente, pedidoPostPutRequestDTO));
    }

    @PutMapping("/{pedidoId}/associar-pedido-entregador")
    public ResponseEntity<?> associarPedidoEntregador(
            @PathVariable Long pedidoId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestBody @Valid PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoAssociaPedidoEntregadorService.associarPedidoEntregador(
                        pedidoId, estabelecimentoId, estabelecimentoCodigoAcesso, pedidoPostPutRequestDTO
                ));
    }
    @GetMapping("/get/{pedidoId}/{clienteId}")
    public ResponseEntity<?> buscarUmPedidoById(
            @PathVariable Long pedidoId,
            @PathVariable Long clienteId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoBuscarByIdService.buscarById(pedidoId, clienteId));
    }

    @GetMapping("get-all/{clienteId}")
    public ResponseEntity<?> buscarTodosPedidosByClienteId(
            @PathVariable Long clienteId,
            @RequestParam String codigoAcessoCliente
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoBuscarTodosByIdCliente.buscar(clienteId, codigoAcessoCliente));
    }

    @GetMapping("/estabelecimento/get-all/{estabelecimentoId}")
    public ResponseEntity<?> buscarTodosPedidosEstabelecimento(
            @PathVariable Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
    ){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoBuscarTodosPedidosEstabelecimento.buscarPedidosEstabelecimento(estabelecimentoId, estabelecimentoCodigoAcesso));
    }

    @GetMapping("/{pedidoId}/{estabelecimentoId}/{estabelecimentoCodigoAcesso}")
    public ResponseEntity<?> estabelecimentoBuscaPedidoById(
            @PathVariable Long pedidoId,
            @PathVariable Long estabelecimentoId,
            @PathVariable String estabelecimentoCodigoAcesso,
            @RequestBody PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoBuscarPedidoByService.buscarPedidoById(pedidoId,
                        estabelecimentoId, estabelecimentoCodigoAcesso, pedidoPostPutRequestDTO));
    }

    @GetMapping("/pedido-cliente-estabelecimento/{clienteId}/{estabelecimentoId}/{pedidoId}")
    public ResponseEntity<?> clienteBuscaPedidoByEstabelecimento(
            @PathVariable Long clienteId,
            @PathVariable Long estabelecimentoId,
            @PathVariable Long pedidoId,
            @RequestParam String clienteCodigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarPedidoByEstabelecimentoService.buscarPedidoByEstabelecimento(clienteId, estabelecimentoId, pedidoId, clienteCodigoAcesso));
    }

    @GetMapping("/pedidos-cliente-estabelecimento/{clienteId}/{estabelecimentoId}")
    public ResponseEntity<?> clienteBuscarTodosPedidosByEstabelecimento(
            @PathVariable Long clienteId,
            @PathVariable Long estabelecimentoId,
            @RequestParam String clienteCodigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarTodosPedidosByEstabelecimentoService.clienteBuscarTodosPedidos(clienteId, estabelecimentoId, clienteCodigoAcesso));
    }

    @GetMapping("/pedidos-cliente-estabelecimento/{clienteId}/{estabelecimentoId}/{pedidoStatusEntrega}")
    public ResponseEntity<?> clienteBuscaTodosPedidosDeEstabelecimentoByStatus(
            @PathVariable Long clienteId,
            @PathVariable Long estabelecimentoId,
            @PathVariable String pedidoStatusEntrega,
            @RequestParam String clienteCodigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarTodosPedidosByStatusService.buscarPedidosByStatus(clienteId, estabelecimentoId, pedidoStatusEntrega, clienteCodigoAcesso));
    }

    @DeleteMapping("/delete/{pedidoId}/{clienteId}")
    public ResponseEntity<?> clienteExcluirPedido(
            @PathVariable Long pedidoId,
            @PathVariable Long clienteId,
            @RequestParam String clienteCodigoAcesso
    ){
        clienteExcluirPedidoService.excluirPedido(pedidoId, clienteId, clienteCodigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<?> clienteExcluirTodosPedidos(
            @PathVariable Long clienteId,
            @RequestParam String codigoAcessoCliente
    ){
        clienteExcluirTodosPedidosService.excluirTodosPedidos(clienteId, codigoAcessoCliente);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete/{pedidoId}/{estabelecimentoId}/{estabelecimentoCodigoAcesso}")
    public ResponseEntity<?> estabelecimentoExcluirUmPedido(
            @PathVariable Long pedidoId,
            @PathVariable Long estabelecimentoId,
            @PathVariable String estabelecimentoCodigoAcesso
    ){
        estabelecimentoExcluirPedidoService.excluirPedido(pedidoId, estabelecimentoId, estabelecimentoCodigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/delete-all/{estabelecimentoId}")
    public ResponseEntity<?> estabelecimentoExcluirTodosPedidos(
            @PathVariable Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
        ){
        estabelecimentoExcluirTodosPedidosService.excluirTodosPedido(estabelecimentoId, estabelecimentoCodigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @DeleteMapping("/{pedidoId}/cancelar-pedido")
    public ResponseEntity<?> clienteCancelaPedido(
            @PathVariable Long pedidoId,
            @RequestParam String clienteCodigoAcesso
    ) {
      clienteCancelarPedidoService.cancelarPedido(pedidoId, clienteCodigoAcesso);
      return ResponseEntity
              .status(HttpStatus.NO_CONTENT)
              .build();
    }

    @PutMapping("/{clienteId}/confirmar-pagamento")
    public ResponseEntity<?>confirmaPagamento(
            @PathVariable Long clienteId,
            @RequestParam Long pedidoId,
            @RequestParam String codigoAcessoCliente,
            @RequestParam String metodoPagamento,
            @RequestBody PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedidoConfirmarPagamentoService.confirmaPagamento(clienteId, codigoAcessoCliente, pedidoId, metodoPagamento, pedidoPostPutRequestDTO));
    }

    @PatchMapping("/{pedidoId}/estabelecimento-prepara-pedido")
    public ResponseEntity<?> prepararPedido(
            @PathVariable Long pedidoId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestBody PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoPrepararPedidoService.prepararPedidoService(pedidoId, estabelecimentoId, estabelecimentoCodigoAcesso, pedidoPostPutRequestDTO));
    }

    @PatchMapping("/{pedidoId}/estabelecimento-apronta-pedido")
    public ResponseEntity<?> aprontaPedido(
            @PathVariable Long pedidoId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestBody PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoAprontaPedidoService.aprontaPedidoService(pedidoId, estabelecimentoId, estabelecimentoCodigoAcesso, pedidoPostPutRequestDTO));
    }

    @PutMapping("/{pedidoId}/{clienteId}/cliente-confirmar-entrega")
    public ResponseEntity<?> confirmaEntrega(
            @PathVariable Long pedidoId,
            @PathVariable Long clienteId,
            @RequestParam String codigoAcessoCliente,
            @RequestBody @Valid PedidoPostPutRequestDTO pedidoPostPutRequestDTO
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(clienteConfirmarPedidoService.confirmar(pedidoId, clienteId, codigoAcessoCliente, pedidoPostPutRequestDTO));
    }

    @GetMapping("/pedidos-cliente-estabelecimento/{clienteId}/{estabelecimentoId}/{status}")
    public ResponseEntity<?> clienteBuscarTodosPedidosByStatus(
            @PathVariable Long clienteId,
            @PathVariable Long estabelecimentoId,
            @PathVariable String status,
            @RequestParam String clienteCodigoAcesso
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarTodosPedidosByStatusService.buscarPedidosByStatus(clienteId, estabelecimentoId, status, clienteCodigoAcesso));
    }
}
