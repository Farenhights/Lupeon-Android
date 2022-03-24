The First TMS as a Service
Documentação Projeto Mobile
Versão
1.0 Responsável
Vinicius Vieira Data
16/09/2021 Descrição
Criação do documento.
2.0 Vinicius Vieira 22/09/2021 Revisão de EndPointsDocumentação de Projetos
Lupeon

1. Introdução
   Este documento tem como objetivo descrever o fluxo e os Endpoint’s utilizados para o Projeto do
   Mobile da Lupeon.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   1Documentação de Projetos
   Lupeon
2. Login
   Descrição dos Endpoint’s utilizados para o fluxo de Login e fluxos acessórios.
   2.1. Endpoint’s
   Este documento tem como objetivo descrever o fluxo e os Endpoint’s utilizados para o Projeto do
   Mobile da Lupeon.
   
   # Botão EndPoint Observação
   
   1 Entrar /Security/Login Endpoint utilizado para Login, irá retornar os dados para indicadores
   e de usuário.
   2 Enviar e-mail /Security/TokenNovaSenha Endpoint utilizado para solicitar o Token de troca de senha.
   3 Confirmar /Security/RedefinirSenha Endpoint responsável por enviar o token e a senha nova para
   alteração.
   Tabela 1 – Endpoint’s Login
   2.2. Regras
   
   # Botão Regra Observação
   
   1 Entrar Somente chamar o token caso os dois
   campos estejam preenchidos. Formatação do Campo de Senha com caracteres ocultos.
   2 Enviar e-mail O Endpoint irá validar se o e-mail é
   valido, caso positivo, iremos retornar
   Success, e caso negativo Failed Caso Failed, retornar o feedback ao usuário para correção.
   3 Confirmar Validar se o Password e o
   ConfirmPassword são iguais (Case
   Sensitive). Caso retorne Failed, informar ao usuário que o mesmo deverá
   solicitar um novo token, e direcionar para a solicitação de novo
   token.
   3 Confirmar O EndPoint irá validar se o Token esta
   ativo, caso positivo irá retornar Success,
   caso negativo Failed. Caso retorne Failed, informar ao usuário que o mesmo deverá solicitar
   um novo token, e direcionar para a solicitação de novo token.
   Tabela 2 – Regras Login
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   2Documentação de Projetos
   Lupeon
3. Principal
   Descrição da apresentação das informações de Menus, usuário e Alertas.
   3.1. Dados Usuário
   Apresentar os dados e armazenar em sessão os dados listados abaixo.
   
   # Campo Utilização Observação
   
   1 UsuarioId Armazenar na Sessão Código do usuário na plataforma da Lupeon.
   2 Login Armazenar na Sessão Login único no sistema para o usuário.
   3 Nome Apresentar após o texto “Olá,” Nome cadastrado para o usuário.
   4 Email Armazenar na Sessão E-mail utilizado pelo usuário.
   5 CompanyId Armazenar na Sessão Código da Empresa, na plataforma da Lupeon.
   Tabela 3 – Utilização Json Dados Usuário.
   3.2. Menu
   Utilizar o retorno do Json, conforme descrição abaixo, na Lista Menus .
   
   # Campo Utilização Observação
   
   1 option Nome apresentado no Menu Utilizar o nome para apresentação do menu e validar se será visible
   ou não.
   2 Visible Boolean para apresentação do Menu para
   o usuário. Conforme a regra de segurança enviada validar a apresentação do
   item do Menu. .
   Tabela 4 – Utilização Json Menus.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   3Documentação de Projetos
   Lupeon
   3.3. Alertas
   Conforme a Lista Alertas apresentar as informações e seus respectivos campos.
   
   # Campo Utilização Observação
   
   1 Icon Ícone utilizado para alerta Alerta = Sino, Dinheiro = $
   2 titulo Informação apresentada na Home Utilizado para chamar a atenção para o Alerta.
   3 Texto Informação apresentada no detalhe do
   alerta Texto a ser utilizado para descrição do alerta enviado.
   4 Link Apresentar o texto “Saiba mais” com o
   link. O Link irá abrir o navegador padrão e abrir o site utilizado.
   Tabela 5 – Utilização Json Alertas
   3.4. Dados de Atendimento
   Conforme o objeto GestorContas apresentar as informações de atendimento .
   
   # Campo Utilização Observação
   
   1 NomeGestor Abaixo do Texto Gestor da Conta O Campo é apenas apresentação sem ação.
   2 contato Abaixo do Nome do Gestor Ao Clicar no campo abrir o Gerenciador de e-mail padrão.
   Tabela 6 – Utilização Json GestorContas
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   4Documentação de Projetos
   Lupeon
4. Indicadores
   Descrição da apresentação das informações de Indicadores recebidas da API.
   4.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Embarcador/Indicadores Authorization,
   CompanyId TransportadorId,
   PeriodoId Utilizar para retornar os Indicadores
   que irão apresentar na tela de
   Indicadores.
   2 /Filtro/Transportadoras Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo de
   Transportadores
   3 /Filtro/Periodo Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo de
   Periodo
   Tabela 7 – Endpoint’s para Indicadores.
   4.2. Indicadores
   Apresentar os dados retornados na Api de Indicadores
   
   # Campo Utilização
   
   Observação
   1 Indicador Título do Indicador
   2 Valor Valor apresentado
   acima
   conforme
   figura
   Retornar o texto, sem necessidade de formatar o retorno.
   Tabela 8 – Utilização Json Dados Usuário.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   5Documentação de Projetos
   Lupeon
5. Simulação
   Descrição da apresentação das informações de Indicadores recebidas da API.
   5.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Embarcador/SimulacaoOnline Authorization,
   CompanyId Vide Collection Postman Utilizar para retornar os resultados
   da simulação realizada.
   2 Filtro/Filial Authorization,
   CompanyId N/A Utilizado para preencher o combo
   de Filial, Pesquisa Simples.
   3 Filtro/UFDestino Authorization,
   CompanyId N/A Utilizado para preencher o combo
   de UF Destino, Pesquisa Simples.
   4 Filtro/CidadeDestino Authorization,
   CompanyId Vide Collection Postman Utilizado para preencher o combo
   de Cidade de Destino, Pesquisa
   Simples.
   Tabela 9 – Endpoint’s para Indicadores.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   6Documentação de Projetos
   Lupeon
   5.2. Busca Avançada
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   5.2.1. Endpoint
   Lista dos Endpoint’s a serem utilizados para preenchimento dos filtros de pesquisa avançada.
   
   # EndPoint Header Request
   
   1 Filtro/UFOrigem Authorization, CompanyId Vide Collection Postman
   2 Filtro/CidadeOrigem Authorization, CompanyId Vide Collection Postman
   3 Filtro/TipoOperacao Authorization, CompanyId Vide Collection Postman
   4 Filtro/Produtos Authorization, CompanyId Vide Collection Postman
   Tabela 10 – Utilização Json Dados Usuário.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   7Documentação de Projetos
   Lupeon
   5.2.2. Apresentação da Informação
   Abaixo seguem os textos a serem informados em cada respectivo campo, e seu campo no Json de
   response.
   1
   3
   2
   5
   4
   6
   8
   7
   
   # Texto Campo Json
   
   1 Simulação: Nome
   2 Origem OrigemUF/CidadeOrigem
   3 Destino DestinoUF/CidadeDestino
   4 Peso Simulado PesoConsiderado
   5 R$ NF ValorMercadoria
   6 N/A MelhorTransportadora
   7 N/A MelhorPrazo
   8 N/A MelhorFrete
   Tabela 11 – Apresentação do resultado.
   Ocultar
   Azul = Melhor
   Transportadora;
   Vermelho = Demais;
   2
   1
   3
   4
   
   # Texto Campo Json
   
   1 N/A Transportadoras.NomeTabelaFrete
   2 N/A Transportadoras. PrazoEntrega
   3 N/A Transportadoras. NomeTransportadora
   4 N/A Transportadoras. ValorFrete
   Tabela 12 – Apresentação do resultado.
   Limitar a 10
   caracteres
   Não
   Não Formatar
   Não
   preci
   sa
   orma
   tar
   Acrescentar
   “Dia” ou “Dias”
   Não
   preci
   sa
   orma
   tar
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   8Documentação de Projetos
   Lupeon
   1
   2
   Ocultar
   3
   
   # Texto Campo Json
   
   1 Transportador Transportadoras.NomeTransportadora
   2 Tabela Frete Transportadoras.NomeTabelaFrete
   3 Origem OrigemUF/CidadeOrigem
   4 Destino DestinoUF/CidadeDestino
   5 Destinatario DestinatarioCNPJ
   6 N/A Produtos.NomeProduto
   7 N/A Produto.Peso
   4
   5
   7
   6
   Tabela 13 – Apresentação do resultado.
   Acrescentar
   “KG”
   Não
   preci
   sa
   orma
   tar
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   9Documentação de Projetos
   Lupeon
6. Tracking - Embarcador
   Descrição da apresentação das informações de Indicadores recebidas da API.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   10Documentação de Projetos
   Lupeon
   6.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Embarcador/IndicadoresTracking Authorization,
   CompanyId TransportadorId, Utilizar
   para
   retornar
   os
   Indicadores que irão apresentar na
   tela de Indicadores de Tracking.
   PeriodoId
   2 Embarcador/Tracking Authorization,
   CompanyId Vide Postman Utilizar para consultar os dados de
   Tracking e apresentar nas
   próximas telas do fluxo.
   3 Filtro/Transportadoras Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo
   de Transportadores
   4 Filtro/Periodo Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo
   de Periodo
   5 Filtro/Filial Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo
   de Filial
   6 Arquivo/GetComprovante Authorization,
   CompanyId Vide Postman Utilizar para retornar o base64 do
   comprovante de entrega
   Tabela 14 – Endpoint’s para Tracking.
   6.2. Regras
   
   # Item Regra
   
   Observação
   1 Indicadores Caso a consulta não retorne valor aos indicadores, ocultar as
   informações acima e Filtros, mostrando apenas a opção de pesquisa.
   2 Status Tracking A Linha do tempo irá ser apresentada apenas para 3 status,
   NFe Emitida, Em Transporte, Status ou Entregue,
   Caso o Entregue não seja “Entregue” ele deverá sempre mostrar o
   campo como STATUS, caso contrário mudar para ENTREGUE e seu
   respectivo ícone.
   3 Botão de Ocorrências Somente apresentar o campo caso o campo PossuiCTe esteja True e
   haja +0 na lista de Ocorrências
   4 Botão de
   Comprovantes
   (Visible) Caso o flag Comprovante.PossuiComprovante esteja True, habilitar
   o botão. .
   5 Botão de
   Comprovantes
   (utilização) Caso o botão esteja ativo, utilizar o campo NFeId, para consultar o
   Endpoint 5 para retornar a imagem do comprovante
   Utilizar o campo Entregue
   (Boolean) da Api para validar a
   apresentação do status da linha do
   tempo.
   Irá retornar o Base64 do arquivo,
   será necessário a conversão para
   apresentação da informação.
   Tabela 15 – Regras Login
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   11Documentação de Projetos
   Lupeon
   6.3. Apresentação
   Apresentar os dados retornados na Api de Indicadores
   Acrescentar
   ícone de Filtro
   Não
   precisa
   ormatar
   Acrescentar
   combo de Filial
   Não
   precisa
   ormatar
   Ocultar
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   12Documentação de Projetos
   Lupeon
   Ocultar
   1
   
   # Texto Campo Json
   
   1.1 NFe Emitida PossuiNFe
   1.2 Produto Coletado PossuiCTe
   1.3 Em Trânsito |
   Entregue Entregue
   2 Nota Fiscal: NumeroNFe
   3 Transportadora Transportadora
   4 Origem CidadeOrigem/UFOrigem
   5 Destino CidadeDestino/UFDestino
   6 Prazo PrazoEntrega
   7 Previsão Entrega PrevisaoEntrega
   2
   3
   4
   5
   7
   Formatar Data
   (dd-MM-YY)
   Acrescentar
   “Dia” ou “Dias”
   Não
   Remover
   Tabela 16 – Apresentação do resultado.
   Ocultar
   Ocultar
   1
   2
   Formatar Data
   (dd-MM-YY)
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   
   # Texto Campo Json
   
   1 N/A Ocorrencias.OcorrenciaLupeon
   2 N/A Ocorrencias.DataOcorrencia
   Tabela 17 – Apresentação do resultado.
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   13Documentação de Projetos
   Lupeon
   1
   2
   
   # Texto Campo Json
   
   1 Data Ocorrencias.DataOcorrencia
   2 Status: Ocorrencias.OcorrenciaLupeon
   3 Descrição: Ocorrencias.OcorrenciaTransportadora
   4 Transportadora: Transportadora
   Tabela 18 – Apresentação do resultado.
   3
   4
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   1415
   Documentação de Projetos
   Lupeon
7. Tracking - Transportador
   Descrição da apresentação das informações de Indicadores recebidas da API.
   Quando Transportador,
   mudar o filtro para
   Embarcador
   Idem 3.Principal
   Idem fluxo
8. Motorista
   Idem 8.2.2
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.brDocumentação de Projetos
   Lupeon
   7.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Transportador/Indicadores Authorization,
   CompanyId EmbarcadorId,
   PeriodoId Utilizar para retornar os
   Indicadores que irão apresentar
   na tela de Indicadores.
   2 Transportador/NotasEmTransito Authorization,
   CompanyId Vide Postman Utilizado para retornar a Lista
   de Notas em Trânsito conforme
   filtro
   3 Transportador/EnviarComprovante Authorization,
   CompanyId Vide Postman Utilizar para enviar o
   comprovante de entrega
   4 Motorista/EnviarTracking Authorization,
   CompanyId Vide Postman Utilizar para enviar a ocorrência
   para a nota fiscal.
   5 Filtro/Embarcador Authorization,
   CompanyId N/A Utilizado para o Filtro, no
   combo de Embarcador
   6 Filtro/Periodo Authorization,
   CompanyId N/A Utilizado para o Filtro, no
   combo de Periodo
   7 Filtro/Ocorrencias Authorization,
   CompanyId Vide Postman Utilizar para preencher o combo
   de Ocorrências
   8 Filtro/StatusTracking Authorization,
   CompanyId N/A Utilizado para o Filtro, no
   combo de Status Tracking
   Tabela 19 – Endpoint’s para Transportador.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   16Documentação de Projetos
   Lupeon
9. Motorista
   Descrição da apresentação das informações de Indicadores recebidas da API.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   17Documentação de Projetos
   Lupeon
   8.1. Nova Ocorrência
   Fluxo abaixo para Novas Ocorrências.
   8.1.1. Entreguei
   Fluxo abaixo para Novas Ocorrências.
   8.1.1.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Motorista/EnviarComprovante Authorization,
   CompanyId Vide Postman Utilizar para enviar a imagem em
   base64.
   Tabela 20 – Endpoint’s para Entreguei.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   18Documentação de Projetos
   Lupeon
   8.1.2. Outras Ocorrências
   Fluxo abaixo para envio de outras Ocorrências, ou que não possuam a imagem do comprovante.
   8.1.2.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Motorista/EnviarTracking Authorization,
   CompanyId Vide Postman Utilizar para enviar a ocorrência
   para a nota fiscal.
   2 Filtro/Ocorrencias Authorization,
   CompanyId Vide Postman Utilizar para preencher o combo de
   Ocorrências
   Tabela 21 – Endpoint’s para Outras Ocorrências.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   19Documentação de Projetos
   Lupeon
   8.2. Notas em Trânsito
   Fluxo abaixo para Novas Ocorrências.
   8.2.1. Endpoint
   Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.
   
   # EndPoint Header Request Observação
   
   1 Motorista/Indicadores Authorization,
   CompanyId Vide Postman Utilizar para retornar os Indicadores
   que irão apresentar na tela de
   Indicadores do Motorista.
   2 /Motorista/NotasEmTransito Authorization,
   CompanyId Vide Postman Utilizado para retornar a Lista de
   Notas em Trânsito conforme filtro
   3 /Filtro/Periodo Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo de
   Periodo
   4 /Filtro/StatusTracking Authorization,
   CompanyId N/A Utilizado para o Filtro, no combo de
   Periodo
   Tabela 22 – Endpoint’s para Notas em Trânsito.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   20Documentação de Projetos
   Lupeon
   8.2.2. Apresentação da Informação
   Abaixo seguem os textos a serem informados em cada respectivo campo, e seu campo no Json de
   response.
   1
   
   # Texto Campo Json
   
   1 N/A Indicador
   2 N/A Valor
   Tabela 23 – Apresentação do resultado.
   1
   2
   Ocultar
   2
   1
   3
   Mudar para texto
   fixo:
   “Atualizar”
   
   # Texto Campo Json
   
   1 N/A NumeroNFe
   2 N/A Destinatario
   3 Emissão: DataEmissaoNFe
   Tabela 24 – Apresentação do resultado.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   21Documentação de Projetos
   Lupeon
   1
   Ocultar
   
   # Texto Observação
   
   1 Destinatario: Utilizar para Filtrar dados, é um
   texto
   Tabela 25 – Apresentação do resultado.
   Avenida Doutor Gastão Vidigal, 1132
   Bloco B - Vila Leopoldina
   São Paulo – SP
   facebook.com/lupeonbrasil/
   lupeon.com.br
   tecnologia@lupeon.com.br
   22
