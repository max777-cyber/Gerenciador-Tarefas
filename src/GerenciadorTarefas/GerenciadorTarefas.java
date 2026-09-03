package GerenciadorTarefas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GerenciadorTarefas {

    // Tarefa com texto e status de conclusão
    static class Tarefa {
        String texto;
        boolean concluida;

        Tarefa(String texto) {
            this.texto = texto;
            this.concluida = false;
        }
    }

    static ArrayList<Tarefa> tarefas = new ArrayList<>();

    public static void main(String[] args) {
        // Aparência nativa do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        while (true) {
            String[] opcoes = {"➕  Adicionar", "📋  Listar", "✅  Concluir", "🗑  Remover", "Sair"};

            int pendentes = (int) tarefas.stream().filter(t -> !t.concluida).count();
            String subtitulo = tarefas.isEmpty()
                ? "Nenhuma tarefa cadastrada."
                : tarefas.size() + " tarefa(s)  •  " + pendentes + " pendente(s)";

            int escolha = JOptionPane.showOptionDialog(
                null,
                subtitulo,
                "Gerenciador de Tarefas",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            if (escolha == JOptionPane.CLOSED_OPTION || escolha == 4) {
                JOptionPane.showMessageDialog(null,
                    "Até logo! 👋",
                    "Encerrando",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            switch (escolha) {
                case 0 -> adicionarTarefa();
                case 1 -> listarTarefas();
                case 2 -> concluirTarefa();
                case 3 -> removerTarefa();
            }
        }
    }

    // ── Adicionar ──────────────────────────────────────────────────────────────
    static void adicionarTarefa() {
        String texto = JOptionPane.showInputDialog(
            null,
            "Digite o nome da tarefa:",
            "Nova Tarefa",
            JOptionPane.PLAIN_MESSAGE
        );

        if (texto == null) return; // cancelou

        texto = texto.trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "O nome da tarefa não pode ser vazio.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        tarefas.add(0, new Tarefa(texto)); // adiciona no topo
        JOptionPane.showMessageDialog(null,
            "Tarefa \"" + texto + "\" adicionada!",
            "✅ Adicionada",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // ── Listar ─────────────────────────────────────────────────────────────────
    static void listarTarefas() {
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Nenhuma tarefa cadastrada ainda.\nUse \"Adicionar\" para criar uma.",
                "Lista de Tarefas",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family:monospace; font-size:13px;'>");
        sb.append("<table cellpadding='6' cellspacing='0'>");

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa t = tarefas.get(i);
            String cor   = t.concluida ? "#888888" : "#000000";
            String status = t.concluida ? "✅" : "⬜";
            String texto  = t.concluida
                ? "<s>" + escapeHtml(t.texto) + "</s>"
                : escapeHtml(t.texto);

            sb.append("<tr>");
            sb.append("<td style='color:#999; padding-right:8px;'>").append(i + 1).append(".</td>");
            sb.append("<td>").append(status).append("</td>");
            sb.append("<td style='color:").append(cor).append("; padding-left:8px;'>").append(texto).append("</td>");
            sb.append("</tr>");
        }

        sb.append("</table></body></html>");

        JOptionPane.showMessageDialog(null,
            sb.toString(),
            "📋 Tarefas (" + tarefas.size() + ")",
            JOptionPane.PLAIN_MESSAGE);
    }

    // ── Concluir ───────────────────────────────────────────────────────────────
    static void concluirTarefa() {
        ArrayList<Tarefa> pendentes = new ArrayList<>();
        for (Tarefa t : tarefas) {
            if (!t.concluida) pendentes.add(t);
        }

        if (pendentes.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Todas as tarefas já estão concluídas! 🎉",
                "Concluir Tarefa",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] opcoes = new String[pendentes.size()];
        for (int i = 0; i < pendentes.size(); i++) {
            opcoes[i] = pendentes.get(i).texto;
        }

        String escolhida = (String) JOptionPane.showInputDialog(
            null,
            "Qual tarefa deseja marcar como concluída?",
            "✅ Concluir Tarefa",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (escolhida == null) return;

        for (Tarefa t : tarefas) {
            if (t.texto.equals(escolhida)) {
                t.concluida = true;
                break;
            }
        }

        JOptionPane.showMessageDialog(null,
            "\"" + escolhida + "\" marcada como concluída! ✅",
            "Concluída",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // ── Remover ────────────────────────────────────────────────────────────────
    static void removerTarefa() {
        if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Não há tarefas para remover.",
                "Remover Tarefa",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] opcoes = new String[tarefas.size()];
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa t = tarefas.get(i);
            opcoes[i] = (t.concluida ? "✅ " : "⬜ ") + t.texto;
        }

        String escolhida = (String) JOptionPane.showInputDialog(
            null,
            "Qual tarefa deseja remover?",
            "🗑 Remover Tarefa",
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (escolhida == null) return;

        // Remove o prefixo de status antes de comparar
        String textoEscolhido = escolhida.substring(3); // tira "✅ " ou "⬜ "
        tarefas.removeIf(t -> t.texto.equals(textoEscolhido));

        JOptionPane.showMessageDialog(null,
            "Tarefa removida com sucesso.",
            "🗑 Removida",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // ── Utilitário ─────────────────────────────────────────────────────────────
    static String escapeHtml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}