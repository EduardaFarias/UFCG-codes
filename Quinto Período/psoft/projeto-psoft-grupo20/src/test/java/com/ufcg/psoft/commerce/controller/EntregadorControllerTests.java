package com.ufcg.psoft.commerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CustomErrorType;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Entregadores")
public class EntregadorControllerTests {

    final String URI_ENTREGADORES = "/entregadores";

    @Autowired
    MockMvc driver;

    @Autowired
    EntregadorRepository entregadorRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Entregador entregador;
    Entregador entregador1;
    Entregador entregador2;
    Entregador entregador3;
    Entregador entregador4;



    EntregadorPostPutRequestDTO entregadorPostPutRequestDTO;

    //EntregadorGetRequestDTO entregadorDTO;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        entregador = entregadorRepository.save(Entregador.builder()
                .nome("Lana Del Rey")
                .placaVeiculo("ABC-1234")
                .corVeiculo("Azul")
                .tipoVeiculo("moto")
                .codigoAcesso("123456")
                .build()
        );
        entregadorPostPutRequestDTO = EntregadorPostPutRequestDTO.builder()
                .nome(entregador.getNome())
                .codigoAcesso(entregador.getCodigoAcesso())
                .placaVeiculo(entregador.getPlacaVeiculo())
                .corVeiculo(entregador.getCorVeiculo())
                .tipoVeiculo(entregador.getTipoVeiculo())
                .build();

       // entregadorDTO = new EntregadorGetRequestDTO(entregador);
    }

    @AfterEach
    void tearDown() {
        entregadorRepository.deleteAll();
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de fluxos básicos API Rest")
    class EntregadorVerificacaoFluxosBasicosApiRest {

        @Test
        @DisplayName("Quando buscamos por todos entregadores salvos")
        void quandoBuscamosTodosEntregadores() throws Exception {
            // Arrange
            // Vamos ter 3 entregadores no banco
            Entregador entregador1 = Entregador.builder()
                    .nome("Jose")
                    .placaVeiculo("GHF-1212")
                    .corVeiculo("Prata")
                    .tipoVeiculo("carro")
                    .codigoAcesso("654321")
                    .build();
            Entregador entregador2 = Entregador.builder()
                    .nome("Halloran")
                    .placaVeiculo("MRD-0217")
                    .corVeiculo("Preto")
                    .tipoVeiculo("carro")
                    .codigoAcesso("217217")
                    .build();
            entregadorRepository.saveAll(Arrays.asList(entregador1, entregador2));

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/get-all")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Entregador> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
            });

            // Assert
            assertEquals(3, resultado.size());
        }

        @Test
        @DisplayName("Quando buscamos por um entregador salvo pelo id")
        void quandoBuscamosEntregadorPorId() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/" + entregador.getId() +"/get-one")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(entregador.getId(), resultado.getId()),
                    () -> assertEquals(entregador.getNome(), resultado.getNome()),
                    () -> assertEquals(entregador.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregador.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregador.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando buscamos um entregador inexistente")
        void quandoBuscamosEntregadorInexistente() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/999999/get-one")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("O entregador consultado nao existe!", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando criamos um entregador com dados válidos")
        void quandoCriamosEntregadorValido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(post(URI_ENTREGADORES + "/post")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(entregadorPostPutRequestDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador com dados válidos")
        void quandoAlteramosEntregadorValido() throws Exception {
            // Arrange
            Long entregadorId = entregador.getId();

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertAll(
                    () -> assertEquals(entregadorId, resultado.getId().longValue()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getNome(), resultado.getNome()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getPlacaVeiculo(), resultado.getPlacaVeiculo()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getCorVeiculo(), resultado.getCorVeiculo()),
                    () -> assertEquals(entregadorPostPutRequestDTO.getTipoVeiculo(), resultado.getTipoVeiculo())
            );
        }

        @Test
        @DisplayName("Quando alteramos um entregador inexistente")
        void quandoAlteramosEntregadorInexistente() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/999999/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("O entregador consultado nao existe!", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando alteramos um entregador passando um código de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando excluímos um entregador salvo")
        void quandoExcluimosEntregadorSalvo() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/" + entregador.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso()))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(responseJsonString.isBlank());
        }

        @Test
        @DisplayName("Quando excluímos um entregador inexistente")
        void quandoExcluimosEntregadorInexistente() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/999999/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso()))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("O entregador consultado nao existe!", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando excluímos um entregador passando um código de acesso inválido")
        void quandoExcluimosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(delete(URI_ENTREGADORES + "/" + entregador.getId() + "/delete")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999"))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }
    }


    @Nested
    @DisplayName("Conjunto de casos de verificação de nome")
    class EntregadorVerificacaoNome {

        @Test
        @DisplayName("Quando alteramos o nome do entregador com dados válidos")
        void quandoAlteramosNomeDoEntregadorValido() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setNome("Lana Del Rey Alterada");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertEquals("Lana Del Rey Alterada", resultado.getNome());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com nome vazio")
        void quandoAlteramosEntregadorComNomeVazio() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setNome("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Nome e obrigatorio", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de placa")
    class EntregadorVerificacaoPlaca {

        @Test
        @DisplayName("Quando alteramos o entregador com placa válida")
        void quandoAlteramosEntregadorComPlacaValida() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setPlacaVeiculo("DEF-3456");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertEquals("DEF-3456", resultado.getPlacaVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com placa vazia")
        void quandoAlteramosEntregadorComPlacaVazia() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setPlacaVeiculo("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Placa do veiculo e obrigatoria", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de tipo")
    class EntregadorVerificacaoTipo {

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo válido")
        void quandoAlteramosEntregadorComTipoVeiculoValido() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setTipoVeiculo("carro");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertEquals("carro", resultado.getTipoVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo nulo")
        void quandoAlteramosEntregadorComTipoVeiculoVazio() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setTipoVeiculo(null);

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Tipo do veiculo e obrigatorio", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o entregador com tipo de veiculo inválido")
        void quandoAlteramosEntregadorComTipoVeiculoInvalido() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setTipoVeiculo("bicicleta");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Tipo do veiculo deve ser moto ou carro", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos o tipo passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de verificação de cor do veiculo")
    class EntregadorVerificacaoCorVeiculo {

        @Test
        @DisplayName("Quando alteramos o entregador com cor do veiculo válida")
        void quandoAlteramosEntregadorComCorVeiculoValida() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setCorVeiculo("preto");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertEquals("preto", resultado.getCorVeiculo());
        }

        @Test
        @DisplayName("Quando alteramos o entregador com cor do veiculo vazia")
        void quandoAlteramosEntregadorComCorVeiculoVazia() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setCorVeiculo("");

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
                    () -> assertEquals("Cor do veiculo e obrigatoria", resultado.getErrors().get(0))
            );
        }

        @Test
        @DisplayName("Quando alteramos a cor do veiculo passando codigo de acesso inválido")
        void quandoAlteramosEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de alteração de disponibilidade do entregador")
    class EntregadorDefinirDisponibilidade {

        @Test
        @DisplayName("Quando alteramos a disponibilidade do entregador para disponível")
        void quandoAlteramosDisponibilidadeParaDisponivel() throws Exception {
            // Arrange
            entregadorPostPutRequestDTO.setDisponivel(true);
            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertTrue(resultado.isDisponivel());
        }

        @Test
        @DisplayName("Quando alteramos a disponibilidade do entregador para indisponível")
        void quandoAlteramosDisponibilidadeParaIndisponivel() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .param("disponibilidade", "false")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Entregador resultado = objectMapper.readValue(responseJsonString, Entregador.EntregadorBuilder.class).build();

            // Assert
            assertFalse(resultado.isDisponivel());
        }

        @Test
        @DisplayName("Quando alteramos a disponibilidade de um entregador inexistente")
        void quandoAlteramosDisponibilidadeDeEntregadorInexistente() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + 999999 + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", entregador.getCodigoAcesso())
                            .param("disponibilidade", "true")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("O entregador consultado nao existe!", resultado.getMessage());
        }

        @Test
        @DisplayName("Quando alteramos a disponibilidade de um entregador passando codigo de acesso inválido")
        void quandoAlteramosDisponibilidadeDeEntregadorComCodigoAcessoInvalido() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(put(URI_ENTREGADORES + "/" + entregador.getId() + "/put")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", "999999")
                            .param("disponibilidade", "true")
                            .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                    .andExpect(status().isBadRequest())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertEquals("Codigo de acesso invalido!", resultado.getMessage());
            assertFalse(entregador.isDisponivel());
        }
    }

    @Nested
    @DisplayName("Conjunto de casos de busca de entregadores disponiveis e aprovados pelo estabelecimento")
    class EntregadorVerificacaoDisponibilidadeAprovacao {
        @BeforeEach
        void setUp() throws JsonProcessingException {
            objectMapper.registerModule(new JavaTimeModule());
            entregador1 = entregadorRepository.save(Entregador.builder()
                    .nome("Neteyam")
                    .placaVeiculo("PND-2907")
                    .corVeiculo("Azul")
                    .tipoVeiculo("carro")
                    .codigoAcesso("870434")
                    .disponivel(true)
                    .aprovado(true)
                    .build()
            );

            entregador2 = entregadorRepository.save(Entregador.builder()
                    .nome("Loak")
                    .placaVeiculo("TSY-3087")
                    .corVeiculo("Cinza")
                    .tipoVeiculo("moto")
                    .codigoAcesso("467546")
                    .disponivel(true)
                    .aprovado(true)
                    .build()
            );

            entregador3 = entregadorRepository.save(Entregador.builder()
                    .nome("Kiri")
                    .placaVeiculo("WYA-5555")
                    .corVeiculo("Branco")
                    .tipoVeiculo("moto")
                    .codigoAcesso("465415")
                    .disponivel(true)
                    .aprovado(true)
                    .build()
            );

            entregador4 = entregadorRepository.save(Entregador.builder()
                    .nome("Tuktirey")
                    .placaVeiculo("TKK-1234")
                    .corVeiculo("Vermelha")
                    .tipoVeiculo("moto")
                    .codigoAcesso("454219")
                    .disponivel(true)
                    .aprovado(false)
                    .build()
            );
        }
            @Test
            @DisplayName("Quando buscamos todos os entregadores filtrados por disbonibilidade e aprovacao")
            void quandoBuscamosEntregadorPorDisponibilidadeEaprovacao () throws Exception {
                String responseJsonString = driver.perform(get(URI_ENTREGADORES + "/get-all/true/true")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(entregadorPostPutRequestDTO)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andReturn().getResponse().getContentAsString();

                List<Entregador> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
                });
                assertAll(
                        () -> assertEquals(3, resultado.size()),
                        () -> assertEquals(entregador1.getId(), resultado.get(0).getId()),
                        () -> assertEquals(entregador2.getId(), resultado.get(1).getId()),
                        () -> assertEquals(entregador3.getId(), resultado.get(2).getId())
                );
            }

        }
    }