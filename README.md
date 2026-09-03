# Gerenciador de Tarefas

Aplicação desktop desenvolvida em Java com interface gráfica (Swing/JOptionPane) para gerenciar uma lista de tarefas com suporte a criação, listagem, conclusão e remoção.

## Funcionalidades

- Adicionar tarefas (inseridas no topo da lista)
- Listar tarefas com status visual (⬜ pendente / ✅ concluída)
- Marcar tarefas como concluídas
- Remover tarefas da lista
- Contador de tarefas pendentes em tempo real
- Interface nativa do sistema operacional (Look and Feel)

## Tecnologias

- Java 17
- Swing / JOptionPane
- ArrayList
- Programação Orientada a Objetos (classe interna `Tarefa`)

## Como Rodar

**Pré-requisitos:** Java 11+

```bash
# Clone o repositório
git clone https://github.com/max777-cyber/Gerenciador-Tarefas.git
cd Gerenciador-Tarefas

# Compile
javac -d out src/GerenciadorTarefas/GerenciadorTarefas.java

# Execute
java -cp out GerenciadorTarefas.GerenciadorTarefas
```

## Estrutura do Projeto

```
src/
└── GerenciadorTarefas/
    └── GerenciadorTarefas.java   # Classe principal + classe interna Tarefa
```

## Aprendizados

- Uso de `JOptionPane` para criar interfaces gráficas sem frameworks externos
- Modelagem com classe interna estática (`Tarefa` com texto e status)
- Manipulação de `ArrayList` com streams para contagem de pendentes
- Renderização de HTML dentro de componentes Swing
- Aplicação do padrão Look and Feel nativo do SO

---

Desenvolvido por [Maximillian Benjamin Vicente](https://github.com/max777-cyber)
