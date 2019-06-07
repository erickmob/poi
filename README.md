![github-small](logo_256px.png)
# Zup POI - pontos de interesse por GPS
*Zup test backend developer*


A *XY Inc.* é uma empresa especializada na produção de excelentes receptores GPS (Global Positioning System). A diretoria está empenhada em lançar um dispositivo inovador que promete auxiliar pessoas na localização de ponto de interesse (POIs), e precisa muito de sua ajuda.
Você foi convidado para desenvolver a plataforma que fornecerá toda a inteligência ao dispositivo. Esta plataforma deve ser baseada em serviços, de forma a flexibilizar a integração, sendo
estes:
 - **Serviço para cadastrar pontos de interesse com 3 atributos:**
   - Nome do POI (ponto de intersse)
   - Coordenada X (inteiro não negativo) 
   - Coordenada Y (inteiro não negativo). 
 
 - **Os POIs devem ser armazenados em uma base de dados.**
 
 - **Serviço para listar todos os POIs cadastrados.**
 
 - **Serviço para listar POIs por proximidade:** Este serviço receberá uma coordenada X e uma coordenada Y, especificando um ponto de referência, bem como uma distância máxima (_d-max_) em metros. 
 
 - **O serviço deverá retornar:** todos os POIs da base de dados que estejam a uma distância menor ou igual a _d-max_ a partir do ponto de referência. 

### Exemplo:

##### Base de Dados:
 > 'Lanchonete' (x=27, y=12)\
  'Posto' (x=31, y=18)\
  'Joalheria' (x=15, y=12)\
  'Floricultura' (x=19, y=21)\
  'Pub' (x=12, y=8)\
  'Supermercado' (x=23, y=6)\
  'Churrascaria' (x=28, y=2)\

##### Pesquisa por ponto de referência:
Dado o ponto de referência _(x=20, y=10)_ indicado pelo receptor GPS, e uma distância máxima de _10 metros_, o serviço deve retornar os seguintes POIs:
 > Lanchonete\
  Joalheria\
  Pub\
  Supermercado

### O que deve ser feito:
- Faça um planejamento e nos informe quando conseguirá entregar o teste.
- Construa os 3 serviços especificados
- O código-fonte deve ser disponibilizado neste repositório, juntamente com as instruções para execução e testes da aplicação em um arquivo com o nome _DOCUMENTATION.md_.
- Deve ser possível executar/testar os serviços utilizando qualquer client HTTP.

### Considerações importantes:
Você pode utilizar qualquer tecnologia que julgar necessário e os quesitos avaliados serão: 
 - organização do código fonte;
 - documentação a respeito do projeto;
 - simplicidade da solução [*KISS*](https://en.wikipedia.org/wiki/KISS_principle);
 - conhecimentos da linguagem/framework utilizado, 
 - cobertura de testes;
 - extensibilidade;
 - manutenibilidade.
 
 ## Contato
Use as 'issues' ou envie um e-mail para: thiago.amarante@zup.com.br

# Boa sorte!!
