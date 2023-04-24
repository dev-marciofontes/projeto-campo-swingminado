# Projeto Campo Minado com Swing
Este projeto é uma implementação do clássico jogo Campo Minado, utilizando Java e a biblioteca gráfica Swing para a interface do usuário. O jogo consiste em um tabuleiro com um conjunto de células, algumas das quais contêm minas. O objetivo é descobrir todas as células que não contêm minas, sem abrir as que contêm minas.

Funcionalidades:
- Interface gráfica baseada em Java Swing
- Tabuleiro com número configurável de linhas e colunas
- Distribuição aleatória de minas no tabuleiro
- Contagem do número de minas na vizinhança de cada célula
- Marcação e desmarcação de células que podem conter minas
- Detecção de vitória (todas as células não minadas abertas) ou derrota (abrir uma célula com mina)
- Reiniciar o jogo

# Estrutura do Código
O projeto está organizado em dois pacotes principais:

## br.com.marciofontes.modelo: 
Contém as classes e interfaces relacionadas à lógica do jogo, como Campo, Tabuleiro, CampoObservador e CampoEvento.

### - Campo
A classe Campo representa um campo individual no jogo, armazenando seu estado atual e gerenciando seus vizinhos. Os principais atributos desta classe incluem: 
- linha e coluna: coordenadas do campo no tabuleiro.
- aberto, minado e marcado: booleanos que representam o estado atual do campo.
- vizinhos: uma lista de campos vizinhos.

Os principais métodos desta classe são:
adicionarVizinho(Campo vizinho): adiciona um campo vizinho à lista de vizinhos.
abrir(): abre o campo e dispara eventos para a interface gráfica.
minar(): mina o campo, alterando seu estado para minado.
alternarMarcacao(): alterna a marcação do campo como marcado ou não marcado.

### - Tabuleiro
A classe Tabuleiro é responsável por gerenciar o conjunto de campos no jogo e controlar o fluxo geral do jogo. Os principais atributos desta classe incluem:
linhas e colunas: as dimensões do tabuleiro.
minas: o número total de minas no tabuleiro.
campos: uma matriz bidimensional de objetos Campo.

Os principais métodos desta classe são:
gerarCampos(): gera todos os campos no tabuleiro, criando instâncias de Campo e atribuindo suas coordenadas.
associarVizinhos(): associa os campos vizinhos uns aos outros chamando o método adicionarVizinho() em cada campo.
sortearMinas(): sorteia posições aleatórias para as minas no tabuleiro e mina os campos selecionados.
abrir(int linha, int coluna) e alternarMarcacao(int linha, int coluna): são métodos que delegam a ação de abrir ou marcar um campo específico, respectivamente.

### - CampoEvento
A classe CampoEvento é um enumerador que define os diferentes eventos que podem ocorrer em um campo, como abrir, marcar ou explodir. A interface gráfica utiliza esses eventos para atualizar a aparência dos botões e o estado do jogo.

### - CampoObservador
A interface CampoObservador define um contrato que permite que objetos interessados sejam notificados quando um evento ocorre em um campo. Classes que implementam esta interface, como a classe BotaoCampo no pacote br.com.marciofontes.visao, podem responder a esses eventos e atualizar a interface gráfica.

## br.com.marciofontes.visao:
É responsável pela interface gráfica e interação do usuário com o jogo. Ele contém as seguintes classes:

### - BotaoCampo:
A classe BotaoCampo estende a classe JButton e é responsável pela representação visual de um campo no jogo. Esta classe implementa a interface CampoObservador e a interface MouseListener para lidar com os eventos do campo e os cliques do mouse, respectivamente. Os principais métodos desta classe incluem: eventoOcorreu(Campo c, CampoEvento e): este método é chamado sempre que um evento ocorre em um campo, e ele atualiza a aparência do botão de acordo com o evento.
aplicarEstiloPadrao(), aplicarEstiloExplodir(), aplicarEstiloMarcar() e aplicarEstiloAbrir(): esses métodos aplicam estilos visuais diferentes ao botão com base no estado do campo associado.

### - PainelTabuleiro:
A classe PainelTabuleiro estende a classe JPanel e é responsável por criar e organizar os botões do campo na interface gráfica. Ela recebe uma instância de Tabuleiro como parâmetro e cria os botões com base nas informações do tabuleiro. Os principais métodos desta classe são: gerarBotao(Campo campo): este método cria um novo botão BotaoCampo para um campo específico e adiciona-o ao painel.
gerarBotoes(Tabuleiro tabuleiro): este método cria todos os botões do campo, chamando o método gerarBotao() para cada campo no tabuleiro.

### - TelaPrincipal
A classe TelaPrincipal estende a classe JFrame e é a janela principal do jogo. Ela cria e organiza os componentes da interface gráfica, como o painel de informações, o painel do tabuleiro e os botões de ação. Os principais métodos desta classe são: iniciar(): este método é chamado para iniciar a janela principal do jogo e exibir a interface gráfica.
atualizarTela(): este método atualiza a interface gráfica com base no estado do jogo.

Ao executar o jogo, a classe TelaPrincipal cria uma instância de Tabuleiro e uma instância de PainelTabuleiro, organizando-os na interface gráfica para que o usuário possa interagir e jogar Campo Minado.

### Como usar:
- Clone este repositório ou copie o código-fonte para o seu ambiente de desenvolvimento Java.
- Compile e execute o programa, verificando as dependências necessárias (Java e Swing).
- Ajuste o número de linhas, colunas e minas no tabuleiro conforme desejado.
- Jogue o jogo, abrindo células e marcando aquelas que você acredita conter minas.
- Ao abrir uma célula com uma mina, o jogo termina e você perde. Se todas as células não minadas forem abertas, você ganha.
- Reinicie o jogo a qualquer momento para tentar novamente.

## Boa diversão!
