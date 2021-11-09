

The First TMS as a Service

Documentação Projeto Mobile

**Versão**

**Responsável**

**Data**

**Descrição**

1.0

Vinicius Vieira

16/09/2021

Criação do documento.

2.0

Vinicius Vieira

22/09/2021

Revisão de EndPoints





**1**

Documentação de Projetos

**Lupeon**

\1. Introdução

Este documento tem como objetivo descrever o fluxo e os Endpoint’s utilizados para o ***Projeto do***

***Mobile*** da Lupeon.

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**2**

Documentação de Projetos

**Lupeon**

\2. Login

Descrição dos Endpoint’s utilizados para o fluxo de Login e fluxos acessórios.

2.1.Endpoint’s

Este documento tem como objetivo descrever o fluxo e os Endpoint’s utilizados para o ***Projeto do***

***Mobile*** da Lupeon.

**#**

**Botão**

**EndPoint**

**Observação**

1

Entrar

/Security/Login

Endpoint utilizado para Login, irá retornar os dados para indicadores

e de usuário.

2

3

Enviar e-mail

Confirmar

/Security/TokenNovaSenha

/Security/RedefinirSenha

Endpoint utilizado para solicitar o Token de troca de senha.

Endpoint responsável por enviar o token e a senha nova para

alteração.

**Tabela 1 – Endpoint’s Login**

2.2.Regras

**#**

**Botão**

**Regra**

**Observação**

1

Entrar

Somente chamar o token caso os dois

campos estejam preenchidos.

Formatação do Campo de Senha com caracteres ocultos.

2

3

3

Enviar e-mail

Confirmar

O Endpoint irá validar se o e-mail é

valido, caso positivo, iremos retornar

Success, e caso negativo Failed

Caso Failed, retornar o feedback ao usuário para correção.

Validar se o Password e o

ConfirmPassword são iguais (Case

Sensitive).

Caso retorne Failed, informar ao usuário que o mesmo deverá

solicitar um novo token, e direcionar para a solicitação de novo

token.

Confirmar

O EndPoint irá validar se o Token esta Caso retorne Failed, informar ao usuário que o mesmo deverá solicitar

ativo, caso positivo irá retornar Success, um novo token, e direcionar para a solicitação de novo token.

caso negativo Failed.

**Tabela 2 – Regras Login**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**3**

Documentação de Projetos

**Lupeon**

\3. Principal

Descrição da apresentação das informações de Menus, usuário e Alertas.

3.1.Dados Usuário

Apresentar os dados e armazenar em sessão os dados listados abaixo.

**#**

1

2

3

4

5

**Campo**

**Utilização**

**Observação**

Armazenar na Sessão

Armazenar na Sessão

Apresentar após o texto “Olá,”

Armazenar na Sessão

Armazenar na Sessão

Código do usuário na plataforma da Lupeon.

Login único no sistema para o usuário.

Nome cadastrado para o usuário.

E-mail utilizado pelo usuário.

UsuarioId

Login

Nome

Email

Código da Empresa, na plataforma da Lupeon.

CompanyId

**Tabela 3 – Utilização Json Dados Usuário.**

3.2.Menu

Utilizar o retorno do Json, conforme descrição abaixo, na Lista Menus.

**#**

**Campo**

**Utilização**

**Observação**

1

Nome apresentado no Menu

Utilizar o nome para apresentação do menu e validar se será visible

ou não.

option

2

Boolean para apresentação do Menu para Conforme a regra de segurança enviada validar a apresentação do

o usuário. item do Menu. .

Visible

**Tabela 4 – Utilização Json Menus.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**4**

Documentação de Projetos

**Lupeon**

3.3.Alertas

Conforme a Lista Alertasapresentar as informações e seus respectivos campos.

**#**

1

2

3

**Campo**

**Utilização**

**Observação**

Ícone utilizado para alerta

Informação apresentada na Home

Alerta = Sino, Dinheiro = $

Utilizado para chamar a atenção para o Alerta.

Icon

titulo

Texto

Informação apresentada no detalhe do Texto a ser utilizado para descrição do alerta enviado.

alerta

4

Apresentar o texto “Saiba mais” com o O Link irá abrir o navegador padrão e abrir o site utilizado.

link.

Link

**Tabela 5 – Utilização Json Alertas**

3.4.Dados de Atendimento

Conforme o objeto GestorContasapresentar as informações de atendimento.

**#**

1

2

**Campo**

**Utilização**

**Observação**

Abaixo do Texto Gestor da Conta

Abaixo do Nome do Gestor

O Campo é apenas apresentação sem ação.

Ao Clicar no campo abrir o Gerenciador de e-mail padrão.

NomeGestor

contato

**Tabela 6 – Utilização Json GestorContas**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**5**

Documentação de Projetos

**Lupeon**

\4. Indicadores

Descrição da apresentação das informações de Indicadores recebidas da API.

4.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

TransportadorId,

PeriodoId

Utilizar para retornar os Indicadores

que irão apresentar na tela de

Indicadores.

Embarcador/Indicadores

2

3

Authorization,

CompanyId

N/A

N/A

Utilizado para o Filtro, no combo de

Transportadores

/Filtro/Transportadoras

/Filtro/Periodo

Authorization,

CompanyId

Utilizado para o Filtro, no combo de

Periodo

**Tabela 7 – Endpoint’s para Indicadores.**

4.2.Indicadores

Apresentar os dados retornados na Api de Indicadores

**#**

1

2

**Campo**

**Utilização**

**Observação**

Título do Indicador

Indicador

Valor

Valor apresentado conforme figura Retornar o texto, sem necessidade de formatar o retorno.

acima

**Tabela 8 – Utilização Json Dados Usuário.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**6**

Documentação de Projetos

**Lupeon**

\5. Simulação

Descrição da apresentação das informações de Indicadores recebidas da API.

5.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

Vide Collection Postman

Utilizar para retornar os resultados

da simulação realizada.

Embarcador/SimulacaoOnline

2

3

4

Authorization,

CompanyId

N/A

Utilizado para preencher o combo

de Filial, Pesquisa Simples.

Filtro/Filial

Authorization,

CompanyId

N/A

Utilizado para preencher o combo

de UF Destino, Pesquisa Simples.

Filtro/UFDestino

Filtro/CidadeDestino

Authorization,

CompanyId

Vide Collection Postman

Utilizado para preencher o combo

de Cidade de Destino, Pesquisa

Simples.

**Tabela 9 – Endpoint’s para Indicadores.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**7**

Documentação de Projetos

**Lupeon**

5.2.Busca Avançada

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

5.2.1. Endpoint

Lista dos Endpoint’s a serem utilizados para preenchimento dos filtros de pesquisa avançada.

**#**

1

2

3

4

**EndPoint**

**Header**

**Request**

Authorization, CompanyId Vide Collection Postman

Authorization, CompanyId Vide Collection Postman

Authorization, CompanyId Vide Collection Postman

Authorization, CompanyId Vide Collection Postman

Filtro/UFOrigem

Filtro/CidadeOrigem

Filtro/TipoOperacao

Filtro/Produtos

**Tabela 10 – Utilização Json Dados Usuário.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**8**

Documentação de Projetos

**Lupeon**

5.2.2. Apresentação da Informação

Abaixo seguem os textos a serem informados em cada respectivo campo, e seu campo no Json de

response.

**#**

1

2

3

4

5

6

7

8

**Texto**

**Campo Json**

Simulação:

Origem

Destino

Peso Simulado

R$ NF

Nome

1

OrigemUF/CidadeOrigem

DestinoUF/CidadeDestino

PesoConsiderado

ValorMercadoria

MelhorTransportadora

MelhorPrazo

3

2

4

5

N/A

6

N/A

8

7

N/A

MelhorFrete

**Tabela 11 – Apresentação do resultado.**

Ocultar

**#**

1

2

**Texto Campo Json**

1

2

Azul = Melhor

Transportadora;

Vermelho = Demais;

3

4

N/A

N/A

Transportadoras.NomeTabelaFrete

Transportadoras. PrazoEntrega

Transportadoras. NomeTransportadora

Transportadoras. ValorFrete

3

4

N/A

N/A

**Tabela 12 – Apresentação do resultado.**

Limitar a 10

caracteres

Não

Não Formatar

Acrescentar

“Dia” ou “Dias”

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**9**

Documentação de Projetos

**Lupeon**

1

3

2

Ocultar

**#**

1

2

**Texto**

**Campo Json**

Transportador Transportadoras.NomeTransportadora

Tabela Frete Transportadoras.NomeTabelaFrete

4

3

4

Origem

Destino

OrigemUF/CidadeOrigem

DestinoUF/CidadeDestino

5

7

5

6

7

Destinatario

N/A

DestinatarioCNPJ

Produtos.NomeProduto

Produto.Peso

6

N/A

**Tabela 13 – Apresentação do resultado.**

Acrescentar

“KG”

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**10**

Documentação de Projetos

**Lupeon**

\6. Tracking - Embarcador

Descrição da apresentação das informações de Indicadores recebidas da API.

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**11**

Documentação de Projetos

**Lupeon**

6.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

Utilizar

para

retornar

os

Embarcador/IndicadoresTracking

TransportadorId,

PeriodoId

Indicadores que irão apresentar na

tela de Indicadores de Tracking.

2

Authorization,

CompanyId

Vide Postman

Utilizar para consultar os dados de

Embarcador/Tracking

Tracking

e

apresentar nas

próximas telas do fluxo.

3

4

5

6

Authorization,

CompanyId

N/A

Utilizado para o Filtro, no combo

de Transportadores

Filtro/Transportadoras

Filtro/Periodo

Authorization,

CompanyId

N/A

Utilizado para o Filtro, no combo

de Periodo

Authorization,

CompanyId

N/A

Utilizado para o Filtro, no combo

de Filial

Filtro/Filial

Authorization,

CompanyId

Vide Postman

Utilizar para retornar o base64 do

comprovante de entrega

Arquivo/GetComprovante

**Tabela 14 – Endpoint’s para Tracking.**

6.2.Regras

**#**

**Item**

**Regra**

**Observação**

1

Indicadores

Caso a consulta não retorne valor aos indicadores, ocultar as

informações acima e Filtros, mostrando apenas a opção de pesquisa.

2

Status Tracking

A Linha do tempo irá ser apresentada apenas para 3 status,

NFe Emitida, Em Transporte, Status ou Entregue,

Utilizar o campo Entregue

(Boolean) da Api para validar a

apresentação do status da linha do

tempo.

Caso o Entregue não seja “Entregue” ele deverá sempre mostrar o

campo como STATUS, caso contrário mudar para ENTREGUE e seu

respectivo ícone.

3

4

Botão de Ocorrências

Somente apresentar o campo caso o campo PossuiCTe esteja True e

haja +0 na lista de Ocorrências

Botão de

Comprovantes

(Visible)

Caso o flag Comprovante.PossuiComprovante esteja True, habilitar

o botão. .

5

Botão de

Comprovantes

(utilização)

Caso o botão esteja ativo, utilizar o campo NFeId, para consultar o

Endpoint 5 para retornar a imagem do comprovante

Irá retornar o Base64 do arquivo,

será necessário a conversão para

apresentação da informação.

**Tabela 15 – Regras Login**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**12**

Documentação de Projetos

**Lupeon**

6.3.Apresentação

Apresentar os dados retornados na Api de Indicadores

Acrescentar

ícone de Filtro

Acrescentar

combo de Filial

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**13**

Documentação de Projetos

**Lupeon**

Ocultar

**#**

**Texto**

**Campo Json**

1

1.1

1.2

1.3

NFe Emitida

PossuiNFe

Produto Coletado PossuiCTe

2

Em Trânsito |

Entregue

Entregue

3

4

5

2

3

4

Nota Fiscal:

Transportadora

Origem

NumeroNFe

7

Transportadora

CidadeOrigem/UFOrigem

Formatar Data

(dd-MM-YY)

Ac

N

resc

ã

entar

o

“Dia” ou “Dias”

5

Destino

Prazo

CidadeDestino/UFDestino

PrazoEntrega

Remover

6

7

Previsão Entrega PrevisaoEntrega

**Tabela 16 – Apresentação do resultado.**

Ocultar

Ocultar

**#**

**Texto**

**Campo Json**

1

1

N/A

N/A

Ocorrencias.OcorrenciaLupeon

Ocorrencias.DataOcorrencia

2

2

Formatar Data

(dd-MM-YY)

**Tabela 17 – Apresentação do resultado.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**14**

Documentação de Projetos

**Lupeon**

**#**

1

2

**Texto**

**Campo Json**

Data

Ocorrencias.DataOcorrencia

Ocorrencias.OcorrenciaLupeon

Status:

1

3

4

Descrição:

Ocorrencias.OcorrenciaTransportadora

2

3

Transportadora: Transportadora

**Tabela 18 – Apresentação do resultado.**

4

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**15**

Documentação de Projetos

**Lupeon**

\7. Tracking - Transportador

Descrição da apresentação das informações de Indicadores recebidas da API.

Quando Transportador,

mudar o filtro para

Embarcador

Idem fluxo

\8. Motorista

Idem 8.2.2

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**16**

Documentação de Projetos

**Lupeon**

7.1.Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

Authorization, EmbarcadorId,

CompanyId PeriodoId

**Request**

**Observação**

1

Utilizar para retornar os

Indicadores que irão apresentar

na tela de Indicadores.

Transportador/Indicadores

2

Authorization, Vide Postman

CompanyId

Utilizado para retornar a Lista

de Notas em Trânsito conforme

filtro

Transportador/NotasEmTransito

3

4

5

6

7

8

Authorization, Vide Postman

CompanyId

Utilizar para enviar o

comprovante de entrega

Transportador/EnviarComprovante

Motorista/EnviarTracking

Filtro/Embarcador

Authorization, Vide Postman

CompanyId

Utilizar para enviar a ocorrência

para a nota fiscal.

Authorization, N/A

CompanyId

Utilizado para o Filtro, no

combo de Embarcador

Authorization, N/A

CompanyId

Utilizado para o Filtro, no

combo de Periodo

Filtro/Periodo

Authorization, Vide Postman

CompanyId

Utilizar para preencher o combo

de Ocorrências

Filtro/Ocorrencias

Authorization, N/A

CompanyId

Utilizado para o Filtro, no

combo de Status Tracking

Filtro/StatusTracking

**Tabela 19 – Endpoint’s para Transportador.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**17**

Documentação de Projetos

**Lupeon**

\8. Motorista

Descrição da apresentação das informações de Indicadores recebidas da API.

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**18**

Documentação de Projetos

**Lupeon**

8.1.Nova Ocorrência

Fluxo abaixo para Novas Ocorrências.

8.1.1. Entreguei

Fluxo abaixo para Novas Ocorrências.

8.1.1.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

Vide Postman

Utilizar para enviar a imagem em

base64.

Motorista/EnviarComprovante

**Tabela 20 – Endpoint’s para Entreguei.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**19**

Documentação de Projetos

**Lupeon**

8.1.2. Outras Ocorrências

Fluxo abaixo para envio de outras Ocorrências, ou que não possuam a imagem do comprovante.

8.1.2.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

Vide Postman

Utilizar para enviar a ocorrência

para a nota fiscal.

Motorista/EnviarTracking

2

Authorization,

CompanyId

Vide Postman

Utilizar para preencher o combo de

Ocorrências

Filtro/Ocorrencias

**Tabela 21 – Endpoint’s para Outras Ocorrências.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**20**

Documentação de Projetos

**Lupeon**

8.2.Notas em Trânsito

Fluxo abaixo para Novas Ocorrências.

8.2.1. Endpoint

Consultar o EndPoint abaixo, com os dados de Header conforme descrito abaixo.

**#**

**EndPoint**

**Header**

**Request**

**Observação**

1

Authorization,

CompanyId

Vide Postman

Utilizar para retornar os Indicadores

que irão apresentar na tela de

Indicadores do Motorista.

Motorista/Indicadores

2

3

4

Authorization,

CompanyId

Vide Postman

Utilizado para retornar a Lista de

Notas em Trânsito conforme filtro

/Motorista/NotasEmTransito

/Filtro/Periodo

Authorization,

CompanyId

N/A

N/A

Utilizado para o Filtro, no combo de

Periodo

Authorization,

CompanyId

Utilizado para o Filtro, no combo de

Periodo

/Filtro/StatusTracking

**Tabela 22 – Endpoint’s para Notas em Trânsito.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**21**

Documentação de Projetos

**Lupeon**

8.2.2. Apresentação da Informação

Abaixo seguem os textos a serem informados em cada respectivo campo, e seu campo no Json de

response.

**#**

1

2

**Texto**

**Campo Json**

N/A

N/A

Indicador

Valor

1

**Tabela 23 – Apresentação do resultado.**

1

2

Ocultar

**#**

1

2

**Texto**

**Campo Json**

2

1

N/A

N/A

NumeroNFe

3

Mudar para texto

fixo:

“Atualizar”

Destinatario

3

Emissão:

DataEmissaoNFe

**Tabela 24 – Apresentação do resultado.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br





**22**

Documentação de Projetos

**Lupeon**

1

Ocultar

**#**

**Texto**

**Observação**

1

Destinatario: Utilizar para Filtrar dados, é um

texto

**Tabela 25 – Apresentação do resultado.**

Avenida Doutor Gastão Vidigal, 1132

Bloco B - Vila Leopoldina

São Paulo – SP

tecnologia@lupeon.com.br

facebook.com/lupeonbrasil/

lupeon.com.br
