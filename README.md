## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Conceitos de Java utilizados

Os programas deste projeto apresentam conceitos fundamentais de Java:

- **Tipos de dados e variáveis:** tipos como `int`, `double`, `boolean` e `String` armazenam valores de acordo com sua natureza.
- **Classes e objetos:** classes definem atributos e métodos; objetos são instâncias dessas classes, como alunos, produtos, clientes e pedidos.
- **Encapsulamento:** atributos privados são acessados ou modificados por métodos, como getters e setters, protegendo os dados do objeto.
- **Enums:** definem um conjunto fixo de opções, por exemplo, categorias de produtos, níveis de acesso e status de pedidos.
- **Exceções e validação:** `try`, `catch` e `finally` permitem tratar erros; exceções personalizadas representam situações específicas, como dados inválidos ou estoque insuficiente. `throw` lança uma exceção e `throws` declara que um método pode lançá-la.
- **Threads e concorrência:** threads executam tarefas simultaneamente. `Runnable` representa uma tarefa; `start()` inicia uma thread e `sleep()` pausa sua execução. `synchronized` protege o acesso a dados compartilhados, evitando condições de corrida, como saques concorrentes ou alterações incorretas de estoque.
- **Interfaces gráficas com Swing:** componentes como `JFrame`, `JLabel`, `JTextField`, `JButton`, `JComboBox`, `JTable` e `JOptionPane` criam janelas, formulários, tabelas e mensagens interativas.
- **Eventos:** ações do usuário, como clicar em um botão, são tratadas por listeners, que executam as operações correspondentes.

O sistema integrador de pedidos combina esses conceitos: cadastra clientes e produtos, valida os dados, cria pedidos com status enumerado e processa pedidos em paralelo, atualizando a interface gráfica.

## Funcionamento do desafio integrador

Execute a classe `./desafioIntegrador/SistemaPedidosSwing.java` para abrir a aplicação. A janela possui três abas:

1. **Clientes:** informe nome e e-mail e clique em **Cadastrar cliente**. Os dados válidos aparecem na tabela e o cliente fica disponível para seleção nos pedidos.
2. **Produtos:** informe descrição, preço e quantidade inicial em estoque e clique em **Cadastrar produto**. O produto cadastrado aparece na tabela e fica disponível para os pedidos.
3. **Pedidos:** selecione um cliente e um produto, informe a quantidade e clique em **Lançar item**. Repita para incluir outros produtos; os itens e subtotais aparecem na tabela de montagem. Um pedido contém um único cliente e pode incluir vários itens. Depois, clique em **Fechar pedido** para registrar o pedido com status **Pendente**.

Na tabela de pedidos, selecione um ou mais pedidos — use Ctrl ou Shift para selecionar várias linhas — e clique em **Processar pedidos selecionados**. Cada pedido é processado em uma thread independente: seu status passa para **Em processamento** e, após a simulação, muda para **Concluído** ou **Falhou**. O estoque dos itens é validado e descontado ao iniciar o processamento; se faltar estoque para qualquer item, o pedido falha sem descontar parcialmente os outros itens. A tabela de produtos exibe as quantidades atualizadas.

Os campos são validados antes do cadastro e da criação de pedidos. Mensagens na tela indicam dados inválidos, seleções ausentes e problemas de estoque. Os dados ficam somente na memória durante a execução; ao fechar o programa, os cadastros são perdidos.

Ao iniciar, o sistema já carrega dois clientes e dez produtos de exemplo, com quantidades variadas em estoque (incluindo um produto sem unidades disponíveis). Eles aparecem nas tabelas e podem ser usados imediatamente para montar pedidos. Novos cadastros podem ser adicionados durante a execução.
