package com.ufcg.psoft.commerce.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CustomErrorType;
import com.ufcg.psoft.commerce.model.Associacao;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.AssociacaoRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de estabelecimentos")
public class EstabelecimentoControllerTests {
    final String URI_ESTABELECIMENTOS = "/estabelecimentos";
    @Autowired
    MockMvc driver;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    AssociacaoRepository associacaoRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Estabelecimento estabelecimento;

    Entregador entregador;


    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        estabelecimento = estabelecimentoRepository.save(Estabelecimento.builder()
                .codigoAcesso("123456")
                .nome("Paulistano")
                .endereco("Rua assis chateaubriand")
                .telefone("3344-5523")
                .horario("19:00 - 22:40")
                .build());

        entregador = entregadorRepository.save(Entregador.builder()
                .nome("Entregador Um")
                .placaVeiculo("ABC-1234")
                .corVeiculo("Branco")
                .tipoVeiculo("carro")
                .codigoAcesso("123456")
                .build()
        );
    }

    @AfterEach
    void tearDown() {
        estabelecimentoRepository.deleteAll();
        entregadorRepository.deleteAll();
        associacaoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação dos fluxos básicos API Rest")
    class EstabelecimentoVerificacaoFluxosBasicosApiRest {
        EstabelecimentoPostPutRequestDTO estabelecimentoPutRequestDTO;
        EstabelecimentoPostPutRequestDTO estabelecimentoPostRequestDTO;

        @BeforeEach
        void setup() {
            estabelecimentoPutRequestDTO = EstabelecimentoPostPutRequestDTO.builder()
                    .codigoAcesso(estabelecimento.getCodigoAcesso())
                    .nome(estabelecimento.getNome())
                    .endereco(estabelecimento.getEndereco())
                    .telefone(estabelecimento.getTelefone())
                    .horario(estabelecimento.getHorario())
                    .build();

            estabelecimentoPostRequestDTO = EstabelecimentoPostPutRequestDTO.builder()
                    .codigoAcesso("654321")
                    .nome("Tá na mão")
                    .endereco("Rua Malindro caperáu")
                    .telefone("3354-5621")
                    .horario("19:00 - 23:00")
                    .build();
        }

        @Test
        @DisplayName("Quando criamos um novo estabelecimento com dados válidos")
        void quandoCriarEstabelecimentoValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", estabelecimentoPostRequestDTO.getCodigoAcesso(),
                                    "nome", estabelecimentoPostRequestDTO.getNome(),
                                    "endereco", estabelecimentoPostRequestDTO.getEndereco(),
                                    "telefone", estabelecimentoPostRequestDTO.getTelefone(),
                                    "horario", estabelecimentoPostRequestDTO.getHorario())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isCreated()) // Codigo 201
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Estabelecimento resultado = objectMapper.readValue(responseJsonString, Estabelecimento.EstabelecimentoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertNotNull(resultado.getId()),
                    () -> assertEquals(estabelecimentoPostRequestDTO.getCodigoAcesso(), resultado.getCodigoAcesso())
            );
        }

        @Test
        @DisplayName("Quando excluímos um estabelecimento salvo")
        void quandoExcluimosEstabelecimentoValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", estabelecimento.getCodigoAcesso()))
                    .andExpect(status().isNoContent()) // Codigo 204
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

        @Test
        @DisplayName("Quando atualizamos um estabelecimento salvo")
        void quandoAtualizamosEstabelecimentoValido() throws Exception {
            // Arrange
            estabelecimentoPutRequestDTO.setCodigoAcesso("131289");

            // Act
            String responseJsonString = driver.perform(put(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", estabelecimento.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(estabelecimentoPutRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Estabelecimento resultado = objectMapper.readValue(responseJsonString, Estabelecimento.EstabelecimentoBuilder.class).build();
            // Assert
            assertAll(
                    () -> assertEquals(resultado.getId().longValue(), estabelecimento.getId().longValue()),
                    () -> assertEquals("131289", resultado.getCodigoAcesso())
            );
        }

        @Test
        @DisplayName("Quando alteramos um estabelecimento com codigo de acesso inválido")
        void quandoAlterarEstabelecimentoInvalido() throws Exception {
            // Arrange
            EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO = EstabelecimentoPostPutRequestDTO.builder()
                    .codigoAcesso("13")
                    .nome(estabelecimento.getNome())
                    .endereco(estabelecimento.getEndereco())
                    .telefone(estabelecimento.getTelefone())
                    .horario(estabelecimento.getHorario())
                    .build();

            // Act
            String responseJsonString = driver.perform(put(URI_ESTABELECIMENTOS + "/" + estabelecimento.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", estabelecimento.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostPutRequestDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 400
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Codigo de acesso deve ter exatamente 6 digitos numericos", resultado.getErrors().get(0))
            );
        }


        @Test
        @DisplayName("Quando criamos um novo estabelecimento com dados inválidos")
        void quandoCriarEstabelecimentoInvalido() throws Exception {
            // Arrange
            EstabelecimentoPostPutRequestDTO estabelecimentoPostRequestDTO2 = EstabelecimentoPostPutRequestDTO.builder()
                    .codigoAcesso("13")
                    .nome("Dinno's")
                    .endereco("Em frente ao açude velho")
                    .telefone("3322-1069")
                    .horario("19:00 - 22:40")
                    .build();

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", estabelecimentoPostRequestDTO2.getCodigoAcesso(),
                                    "nome", estabelecimentoPostRequestDTO2.getNome(),
                                    "endereco", estabelecimentoPostRequestDTO2.getEndereco(),
                                    "telefone", estabelecimentoPostRequestDTO2.getTelefone(),
                                    "horario", estabelecimentoPostRequestDTO2.getHorario())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO2)))
                    .andExpect(status().isBadRequest()) // Codigo 400
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Codigo de acesso deve ter exatamente 6 digitos numericos", resultado.getErrors().get(0))
            );
        }
        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento")
        void quandoBuscarCardapioEstabelecimento() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigoAcesso("123456")
                    .nome("Pingu's Restaurant")
                    .endereco("Oçivil de Carvalho")
                    .telefone("3322-1069")
                    .horario("19:00 - 22:40")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("estabelecimentoCodigoAcesso", estabelecimento1.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Sabor> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(4, resultado.size())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento que não existe")
        void quandoBuscarCardapioEstabelecimentoInexistente() throws Exception {
            // Arrange
            // Nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/999999/sabores")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("estabelecimentoCodigoAcesso", estabelecimento.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 404
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento por tipo (salgado)")
        void quandoBuscarCardapioEstabelecimentoPorTipo() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigoAcesso("123456")
                    .nome("Pingu's Restaurant")
                    .endereco("Oçivil de Carvalho")
                    .telefone("3322-1069")
                    .horario("19:00 - 22:40")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores" + "/tipo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("tipo", "salgado")
                            .param("estabelecimentoCodigoAcesso", estabelecimento1.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Sabor> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(2, resultado.size())
            );
        }

        @Test
        @DisplayName("Quando buscamos o cardapio de um estabelecimento por tipo (doce)")
        void quandoBuscarCardapioEstabelecimentoPorTipoDoce() throws Exception {
            // Arrange
            Sabor sabor1 = Sabor.builder()
                    .nome("Calabresa")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("salgado")
                    .build();

            Sabor sabor2 = Sabor.builder()
                    .nome("Mussarela")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("salgado")
                    .build();
            Sabor sabor3 = Sabor.builder()
                    .nome("Chocolate")
                    .precoM(25.0)
                    .precoG(35.0)
                    .tipo("doce")
                    .build();

            Sabor sabor4 = Sabor.builder()
                    .nome("Morango")
                    .precoM(20.0)
                    .precoG(30.0)
                    .tipo("doce")
                    .build();
            Estabelecimento estabelecimento1 = Estabelecimento.builder()
                    .codigoAcesso("123456")
                    .nome("Pingu's Restaurant")
                    .endereco("Oçivil de Carvalho")
                    .telefone("3322-1069")
                    .horario("19:00 - 22:40")
                    .sabores(Set.of(sabor1, sabor2, sabor3, sabor4))
                    .build();
            estabelecimentoRepository.save(estabelecimento1);

            // Act
            String responseJsonString = driver.perform(get(URI_ESTABELECIMENTOS + "/" + estabelecimento1.getId() + "/sabores" + "/tipo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("estabelecimentoCodigoAcesso", estabelecimento1.getCodigoAcesso())
                            .param("tipo", "doce")
                            .content(objectMapper.writeValueAsString(estabelecimentoPostRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Sabor> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertAll(
                    () -> assertEquals(2, resultado.size())
            );
        }

    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de criacao de associacao")
    class EstabelecimentoCriacaoAssociacao {
        @Test
        @DisplayName("Quando criamos uma associacao com sucesso")
        void testCriarAssociacaoComSucesso() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEntregador", entregador.getCodigoAcesso())
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Associacao resultado = objectMapper.readValue(responseJsonString, Associacao.AssociacaoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertNotNull(resultado.getId()),
                    () -> assertEquals(entregador.getId(), resultado.getEntregadorId()),
                    () -> assertEquals(estabelecimento.getId(), resultado.getEstabelecimentoId())
            );
        }

        @Test
        @DisplayName("Quando criamos uma associacao com entregador inexistente")
        void testCriarAssociacaoComEntregadorInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/9999/" + estabelecimento.getId() + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", "9999")
                            .param("codigoAcessoEntregador", entregador.getCodigoAcesso())
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(0, associacaoRepository.count()),
                    () -> assertEquals("O entregador consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando criamos uma associacao com estabelecimento inexistente")
        void testCriarAssociacaoComEstabelecimentoInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/9999/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEntregador", entregador.getCodigoAcesso())
                            .param("estabelecimentoId", "9999"))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(0, associacaoRepository.count()),
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando criamos uma associacao passando codigo de acesso invalido")
        void testCriarAssociacaoComCodigoDeAcessoInvalido() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(post(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEntregador", "654321")
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(0, associacaoRepository.count()),
                    () -> assertEquals("Codigo de acesso invalido!", resultado.getMessage())
            );
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de aprovação de associacao")
    class EstabelecimentoAprovacaoAssociacao {

        @BeforeEach
        void setUp() {
            associacaoRepository.save(Associacao.builder()
                    .entregadorId(entregador.getId())
                    .estabelecimentoId(estabelecimento.getId())
                    .status(false)
                    .build()
            );
        }
        @Test
        @DisplayName("Quando aprovamos uma associacao com sucesso")
        void quandoAprovamosAssociacaoComSucesso() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId()+ "/patch/status/aprovar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("estabelecimentoId", estabelecimento.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Associacao resultado = objectMapper.readValue(responseJsonString, Associacao.AssociacaoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertTrue(resultado.isStatus())
            );
        }

        @Test
        @DisplayName("Quando aprovamos uma associacao com entregador inexistente")
        void quandoAprovamosAssociacaoComEntregadorInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/9999/" + estabelecimento.getId()+ "/patch/status/aprovar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", "9999")
                            .param("estabelecimentoId", estabelecimento.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O entregador consultado nao existe!", resultado.getMessage())
            );
        }
        @Test
        @DisplayName("Quando aprovamos uma associacao com estabelecimento inexistente")
        void quandoAprovamosAssociacaoComEstabelecimentoInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/9999/patch/status/aprovar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso())
                            .param("estabelecimentoId", "9999"))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando aprovamos uma associacao passando codigo de acesso invalido")
        void quandoAprovamosAssociacaoComCodigoDeAcessoInvalido() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId()+ "/patch/status/aprovar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", "999999")
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("Codigo de acesso invalido!", resultado.getMessage())
            );
        }
    }
    @Nested
    @DisplayName("Conjunto de casos de verificação de reprovacao de associacao")
    class ClienteReprovacaoAssociacao {

        @BeforeEach
        void setUp() {
            associacaoRepository.save(Associacao.builder()
                    .entregadorId(entregador.getId())
                    .estabelecimentoId(estabelecimento.getId())
                    .status(true)
                    .build()
            );
        }

        @Test
        @DisplayName("Quando rejeitamos uma associacao com sucesso")
        void quandoRejeitaAssociacaoComSucesso() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/patch/status/rejeitar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("estabelecimentoId", estabelecimento.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Associacao resultado = objectMapper.readValue(responseJsonString, Associacao.AssociacaoBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertFalse(resultado.isStatus())
            );
        }
        @Test
        @DisplayName("Quando rejeitamos uma associacao com entregador inexistente")
        void quandoRejeitamosAssociacaoComEntregadorInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/9999/" + estabelecimento.getId() + "/patch/status/rejeitar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", "9999")
                            .param("estabelecimentoId", estabelecimento.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O entregador consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando rejeitamos uma associacao com estabelecimento inexistente")
        void quandoRejeitamosAssociacaoComEstabelecimentoInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/9999/patch/status/rejeitar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", entregador.getCodigoAcesso())
                            .param("estabelecimentoId", "9999"))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando rejeitamos uma associacao passando codigo de acesso invalido")
        void quandoAprovamosAssociacaoComCodigoDeAcessoInvalido() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(patch( URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/patch/status/rejeitar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", "999999")
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("Codigo de acesso invalido!", resultado.getMessage())
            );
        }
    }
    @Nested
    @DisplayName("Conjunto de casos de remocao de associacao")
    class ClienteAprovacaoAssociacao {

        @BeforeEach
        void setUp() {
            associacaoRepository.save(Associacao.builder()
                    .entregadorId(entregador.getId())
                    .estabelecimentoId(estabelecimento.getId())
                    .status(false)
                    .build()
            );
        }

        @Test
        @DisplayName("Quando excluimos uma associacao salva")
        void testeRemoverAssociacaoComSucesso() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso())
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

        @Test
        @DisplayName("Quando removemos uma associacao com entregador inexistente")
        void testCriarRemoverComEntregadorInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/9999/" + estabelecimento.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", "9999")
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso())
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O entregador consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando removemos uma associacao com estabelecimento inexistente")
        void testRemoverAssociacaoComEstabelecimentoInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/9999/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", estabelecimento.getCodigoAcesso())
                            .param("estabelecimentoId", "9999"))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("O estabelecimento consultado nao existe!", resultado.getMessage())
            );
        }

        @Test
        @DisplayName("Quando removemos uma associacao passando codigo de acesso invalido")
        void testeRemoverAssociacaoComCodigoDeAcessoInvalido() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(delete(URI_ESTABELECIMENTOS + "/" + entregador.getId() + "/" + estabelecimento.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("entregadorId", entregador.getId().toString())
                            .param("codigoAcessoEstabelecimento", "654321")
                            .param("estabelecimentoId", estabelecimento.getId().toString()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals(1, associacaoRepository.count()),
                    () -> assertEquals("Codigo de acesso invalido!", resultado.getMessage())
            );
        }
    }
}