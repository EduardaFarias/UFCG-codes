# Projetos desenvolvidos durante a disciplina de Laboratório de programação II

Cada diretório possui um  pdf com a especificação do projeto, os laboratórios estão organizados da seguinte maneira:

### Laboratório 01
Uma introdução à linguagem java, códigos no estilo do site beecrowd

[Link para o dirlididi](http://dirlididi.com)

<br>

### Laboratório 02
Controle Institucional da situação acadêmica - CoiSA
<Br>
Nesse projetos desenvolvemos um sistema capaz de gerenciar o uso dos laboratórios de Ciência da computação (LCC's) e sua vida acadêmica, com esse sistema, o aluno poderá:
  
:hourglass_flowing_sand: Organizar seu tempo de uso de internet para as disciplinas

:books: Estudar para as disciplinas
 
:memo: Organizar suas atividades complementares
  
  
:sleeping: Acompanhar sua rotina de descanso

<br>
  
### Laboratório 03
Agenda de contatos
Nesse laboratório trabahamos um sistema para gerenciar seus contatos, o sistema deve permitir:

:bust_in_silhouette: Cadastrar contatos

:busts_in_silhouette: Visualizar os contatos
	
:hearts: Adicionar favoritos 
    
:pushpin: Aplicar tags aos contatos
    
:x: Remover contato
    
:walking: Sair do sistema
    
### Laboratório 04
Primeira prova.
  
EstacionAqui
  
Um sistema para facilitar a busca por vagas de estacionamento na cidade, durante as festividades de são joão, esse sistema possui as seguintes funcionalidades:

:heavy_plus_sign: Adicionar vagas

:arrows_counterclockwise:  Mudar status da vaga
  
:moneybag:  Simular preço da vaga
  
:heavy_check_mark:  Verificações de vagas
  
:heavy_check_mark:  Informar vagas livres, vagas ativas e informar uma vaga livre em um endereço específico e com determinada área
  
:bar_chart:  Informar um relatório de uso das vagas
  
:blue_car:  Listar vagas ativas
  
:oncoming_automobile:  Espaço para o feedback dos clientes
  
### Laboratório 05
  
Lunr 0.1
  
Um sistema de controle e busca de documentos, nesse laboratório partimos já de uma base implementada, o sistema possui 3 funcionalidades, cada uma delas com um pacote diferente, segue abaixo a organização dos pacotes:
  - Busca: Subsistema de busca. Ao contrário dos documentos, o subsistema de
    buscas trata de buscas mais complexas, definir ordenação, ranking, relevância,
    dado as consultas realizadas no subsistema de documentos
  
  - Documentos: Subsistema de gerência de cadastro e recuperação de
    documentos. Esse código é responsável por algum nível de busca / acesso de
    documentos simples.
  
  - Similaridade: Subsistema para realizar os cálculos de similaridade de
    documentos.
  
E é separado em três camadas:
  - Controller/UI: Classes de interação com o usuário ou com requisições do
    usuário, fazendo validação básica e propagando essas requisições para as
    classes resposáveis pela lógica do sistema.

  - Services/Lógica de Negócio: Classes responsáveis pela lógica do sistema.
    Manipulam entidades e elementos que são próprios do sistema.

  - Repository/Dados: Entidades responsáveis pelo armazenamento e operações
    de acesso aos dados.
  
  
### Laboratório 06
  Sistema de Acompanhamento de Pessoas da Organização (SAPO)
  
  Projeto feito em grupo, o sistema realiza operações de acompanhamento de pessoas e suas atividades em uma organização de ensino, é um sistema de      informações no sentido de um conjunto de partes que tem funções específicas mas, que em conjunto, tratam de um conjunto de dados. O sistema é um CRUD   pois realiza as operações de criação, recuperação, atualização e apagamento de dados, respectivamente create, recover, update e delete.
