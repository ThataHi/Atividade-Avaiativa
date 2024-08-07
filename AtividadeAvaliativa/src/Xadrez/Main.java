package Xadrez;

abstract class Peca {
    protected int x, y;

    public Peca(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean mover(int novoX, int novoY);
}

class Rei extends Peca {
    public Rei(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if (Math.abs(novoX - x) <= 1 && Math.abs(novoY - y) <= 1) {
            System.out.println("Movimento válido para o Rei.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para o Rei.");
        return false;
    }
}

class Rainha extends Peca {
    public Rainha(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if (novoX == x || novoY == y || Math.abs(novoX - x) == Math.abs(novoY - y)) {
            System.out.println("Movimento válido para a Rainha.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para a Rainha.");
        return false;
    }
}

//Definir outras peças semelhantes (Bispo, Cavalo, Torre, Peão) aqui
class Bispo extends Peca {
    public Bispo(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if (Math.abs(novoX - x) == Math.abs(novoY - y)) {
            System.out.println("Movimento válido para o Bispo.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para o Bispo.");
        return false;
    }
}

class Cavalo extends Peca {
    public Cavalo(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if ((Math.abs(novoX - x) == 2 && Math.abs(novoY - y) == 1) ||
            (Math.abs(novoX - x) == 1 && Math.abs(novoY - y) == 2)) {
            System.out.println("Movimento válido para o Cavalo.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para o Cavalo.");
        return false;
    }
}

class Torre extends Peca {
    public Torre(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if (novoX == x || novoY == y) {
            System.out.println("Movimento válido para a Torre.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para a Torre.");
        return false;
    }
}

class Peao extends Peca {
    public Peao(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        //Considerando que os peões só se movem para frente e capturam na diagonal
        if ((novoX == x && novoY == y + 1) || // Movimento simples
            (Math.abs(novoX - x) == 1 && novoY == y + 1)) { // Captura
            System.out.println("Movimento válido para o Peão.");
            x = novoX;
            y = novoY;
            return true;
        }
        System.out.println("Movimento inválido para o Peão.");
        return false;
    }
}

class Tabuleiro {
    private Peca[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new Peca[8][8];
    }

    public void adicionarPeca(Peca peca, int x, int y) {
        tabuleiro[x][y] = peca;
    }

    public boolean moverPeca(int x, int y, int novoX, int novoY) {
        Peca peca = tabuleiro[x][y];
        if (peca != null && peca.mover(novoX, novoY)) {
            tabuleiro[novoX][novoY] = peca;
            tabuleiro[x][y] = null;
            return true;
        }
        return false;
    }
}

class Jogador {
    private String nome;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public boolean fazerMovimento(Tabuleiro tabuleiro, int x, int y, int novoX, int novoY) {
        if (tabuleiro.moverPeca(x, y, novoX, novoY)) {
            System.out.println(nome + " fez um movimento válido.");
            return true;
        } else {
            System.out.println(nome + " fez um movimento inválido.");
            return false;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca rei = new Rei(0, 0);
        Peca rainha = new Rainha(1, 1);
        Peca bispo = new Bispo(2, 2);
        Peca cavalo = new Cavalo(3, 3);
        Peca torre = new Torre(4, 4);
        Peca peao = new Peao(5, 5);

        tabuleiro.adicionarPeca(rei, 0, 0);
        tabuleiro.adicionarPeca(rainha, 1, 1);
        tabuleiro.adicionarPeca(bispo, 2, 2);
        tabuleiro.adicionarPeca(cavalo, 3, 3);
        tabuleiro.adicionarPeca(torre, 4, 4);
        tabuleiro.adicionarPeca(peao, 5, 5);

        Jogador jogador1 = new Jogador("Jogador 1");
        Jogador jogador2 = new Jogador("Jogador 2");

        System.out.println(jogador1.fazerMovimento(tabuleiro, 0, 0, 1, 1)); // Movimento inválido para o Rei
        System.out.println(jogador1.fazerMovimento(tabuleiro, 1, 1, 3, 3)); // Movimento válido para a Rainha
    }
}
