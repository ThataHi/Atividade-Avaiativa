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
            x = novoX;
            y = novoY;
            return true;
        }
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
            x = novoX;
            y = novoY;
            return true;
        }
        return false;
    }
}

// Definir outras peças semelhantes (Bispo, Cavalo, Torre, Peão) aqui
class Bispo extends Peca {
    public Bispo(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        if (Math.abs(novoX - x) == Math.abs(novoY - y)) {
            x = novoX;
            y = novoY;
            return true;
        }
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
            x = novoX;
            y = novoY;
            return true;
        }
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
            x = novoX;
            y = novoY;
            return true;
        }
        return false;
    }
}

class Peao extends Peca {
    public Peao(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY) {
        // Considerando que os peões só se movem para frente e capturam na diagonal
        if ((novoX == x && novoY == y + 1) || // Movimento simples
            (Math.abs(novoX - x) == 1 && novoY == y + 1)) { // Captura
            x = novoX;
            y = novoY;
            return true;
        }
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

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca rei = new Rei(0, 0);
        Peca rainha = new Rainha(1, 1);

        tabuleiro.adicionarPeca(rei, 0, 0);
        tabuleiro.adicionarPeca(rainha, 1, 1);

        tabuleiro.moverPeca(0, 0, 1, 1); // Movimento inválido para o Rei
        tabuleiro.moverPeca(1, 1, 3, 3); // Movimento válido para a Rainha
    }
}