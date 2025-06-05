Aluno : Daniel de Carvalho Moreira - 2310419

# Simulador de Sistema de Arquivos com Journaling

## Metodologia

O simulador foi desenvolvido em linguagem de programação **Java**. Ele recebe comandos correspondentes a operações de um sistema operacional em um sistema de arquivos, executando cada funcionalidade e exibindo os resultados na tela quando necessário.

---

## Parte 1: Introdução ao Sistema de Arquivos com Journaling

### Sistema de Arquivos

Um sistema de arquivos é uma estrutura de dados usada para gerenciar e organizar arquivos em dispositivos de armazenamento, permitindo o armazenamento, recuperação e organização eficiente de dados. Ele é fundamental para o funcionamento do sistema operacional, garantindo acesso estruturado aos arquivos.

### Journaling

Journaling é uma técnica usada em sistemas de arquivos para aumentar a confiabilidade e a integridade dos dados. Ela registra em um log (journal) as operações que serão realizadas antes de executá-las de fato, de forma a possibilitar a recuperação do sistema após falhas, como quedas de energia ou erros inesperados. Existem diferentes tipos de journaling, como:

- **Write-ahead logging**: as operações são registradas no journal antes de serem aplicadas no sistema de arquivos.
- **Log-structured file systems**: os dados e metadados são escritos sequencialmente em um log.

---

## Parte 2: Arquitetura do Simulador

### Estrutura de Dados

O simulador utiliza classes Java para representar as principais entidades de um sistema de arquivos:

- **Directory**: representa um diretório, que pode conter arquivos e outros diretórios.
- **File**: representa um arquivo.
- **FileSystemSimulator**: gerencia o sistema de arquivos em si, com as operações para criar, deletar, navegar e renomear arquivos e diretórios.

### Journaling

O journaling foi implementado por meio da classe **Journal**, que mantém um log das operações realizadas no sistema (criação de arquivos/diretórios, renomeação, etc.). Esse log é aplicado para garantir consistência e permitir recuperação em caso de falhas, aplicando as operações pendentes ao reiniciar o simulador.

---

## Parte 3: Implementação em Java

- **FileSystemSimulator**: contém os métodos para manipulação do sistema de arquivos, incluindo criação, deleção, navegação, listagem, renomeação e persistência dos dados.
- **File** e **Directory**: representam os arquivos e diretórios, contendo atributos como nome, referências a arquivos filhos, subdiretórios e o diretório pai.
- **Journal**: gerencia a gravação das operações em um arquivo de log e sua aplicação no sistema.

---

## Parte 4: Instalação e Funcionamento

### Requisitos

- Java JDK 11 ou superior instalado.
- IDE recomendada: Eclipse, IntelliJ IDEA ou similar.
- Git para clonar o repositório (opcional).

### Passos para execução

1. Clone o repositório do projeto:

2. Abra o projeto em sua IDE Java.
3. Compile o projeto.
4. Execute a classe `MainGUI` para iniciar a interface gráfica do simulador.
5. Utilize os comandos disponíveis para manipular o sistema de arquivos, como:
- `criar arquivo <nome>`
- `criar diretorio <nome>`
- `renomear arquivo <antigo> <novo>`
- `renomear diretorio <antigo> <novo>`
- `cd <nome> / ..`
- `listar`
- `salvar`
- `reset`
- `sair`

---

## Resultados Esperados

Espera-se que o simulador forneça insights práticos sobre o funcionamento interno de um sistema de arquivos e o mecanismo de journaling, ajudando na compreensão de como sistemas operacionais gerenciam dados e mantêm a integridade do sistema diante de falhas.

---

## Link do Projeto no GitHub

[https://github.com/DanielMoreiraCarv/Simulador-de-Gerenciador-de-Arquivos.git](https://github.com/DanielMoreiraCarv/Simulador-de-Gerenciador-de-Arquivos.git)

