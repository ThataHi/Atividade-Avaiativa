package Xadrez;

abstract class Peca {
    protected int x, y;

    public Peca(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean mover(int novoX, int novoY, Tabuleiro tabuleiro);
}

class Rei extends Peca {
    public Rei(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        if (Math.abs(novoX - x) <= 1 && Math.abs(novoY - y) <= 1) {
            if (tabuleiro.isPosicaoValida(novoX, novoY)) {
                System.out.println("Movimento válido para o Rei.");
                x = novoX;
                y = novoY;
                return true;
            }
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
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        if (novoX == x || novoY == y || Math.abs(novoX - x) == Math.abs(novoY - y)) {
            // Verifica se o caminho está livre
            int passoX = (novoX > x) ? 1 : (novoX < x) ? -1 : 0;
            int passoY = (novoY > y) ? 1 : (novoY < y) ? -1 : 0;

            for (int i = x + passoX, j = y + passoY; i != novoX || j != novoY; i += passoX, j += passoY) {
                if (tabuleiro.tabuleiro[i][j] != null) {
                    System.out.println("Movimento inválido para a Rainha, caminho obstruído.");
                    return false;
                }
            }

            if (tabuleiro.isPosicaoValida(novoX, novoY)) {
                System.out.println("Movimento válido para a Rainha.");
                x = novoX;
                y = novoY;
                return true;
            }
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
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        if (Math.abs(novoX - x) == Math.abs(novoY - y)) {
            int passoX = (novoX > x) ? 1 : (novoX < x) ? -1 : 0;
            int passoY = (novoY > y) ? 1 : (novoY < y) ? -1 : 0;

            for (int i = x + passoX, j = y + passoY; i != novoX || j != novoY; i += passoX, j += passoY) {
                if (tabuleiro.tabuleiro[i][j] != null) {
                    System.out.println("Movimento inválido para o Bispo, caminho obstruído.");
                    return false;
                }
            }

            if (tabuleiro.isPosicaoValida(novoX, novoY)) {
                System.out.println("Movimento válido para o Bispo.");
                x = novoX;
                y = novoY;
                return true;
            }
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
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        if ((Math.abs(novoX - x) == 2 && Math.abs(novoY - y) == 1) ||
            (Math.abs(novoX - x) == 1 && Math.abs(novoY - y) == 2)) {
            if (tabuleiro.isPosicaoValida(novoX, novoY)) {
                System.out.println("Movimento válido para o Cavalo.");
                x = novoX;
                y = novoY;
                return true;
            }
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
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        if (novoX == x || novoY == y) {
            int passoX = (novoX > x) ? 1 : (novoX < x) ? -1 : 0;
            int passoY = (novoY > y) ? 1 : (novoY < y) ? -1 : 0;

            for (int i = x + passoX, j = y + passoY; i != novoX || j != novoY; i += passoX, j += passoY) {
                if (tabuleiro.tabuleiro[i][j] != null) {
                    System.out.println("Movimento inválido para a Torre, caminho obstruído.");
                    return false;
                }
            }

            if (tabuleiro.isPosicaoValida(novoX, novoY)) {
                System.out.println("Movimento válido para a Torre.");
                x = novoX;
                y = novoY;
                return true;
            }
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
    public boolean mover(int novoX, int novoY, Tabuleiro tabuleiro) {
        int direcao = (y == 1) ? 1 : -1; // Considerando que peões movem-se para cima ou para baixo
        boolean movimentoValido = false;

        // Movimento inicial (dois quadrados para frente)
        if (novoX == x && novoY == y + 2 * direcao && (y == 1 || y == 6) && tabuleiro.tabuleiro[x][y + direcao] == null) {
            movimentoValido = true;
        }
        // Movimento simples (um quadrado para frente)
        else if (novoX == x && novoY == y + direcao && tabuleiro.tabuleiro[novoX][novoY] == null) {
            movimentoValido = true;
        }
        // Captura diagonal
        else if (Math.abs(novoX - x) == 1 && novoY == y + direcao && tabuleiro.tabuleiro[novoX][novoY] != null) {
            movimentoValido = true;
        }

        if (movimentoValido) {
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
    public Peca[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new Peca[8][8];
    }

    public void adicionarPeca(Peca peca, int x, int y) {
        tabuleiro[x][y] = peca;
    }

    public boolean moverPeca(int x, int y, int novoX, int novoY) {
        Peca peca = tabuleiro[x][y];
        if (peca != null && peca.mover(novoX, novoY, this)) {
            if (tabuleiro[novoX][novoY] != null) {
                System.out.println("Peça capturada.");
            }
            tabuleiro[novoX][novoY] = peca;
            tabuleiro[x][y] = null;
            return true;
        }
        return false;
    }

    public boolean isPosicaoValida(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
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

        // Teste de movimentos
        System.out.println(jogador1.fazerMovimento(tabuleiro, 0, 0, 1, 1)); // Movimento inválido para o Rei
        System.out.println(jogador1.fazerMovimento(tabuleiro, 1, 1, 3, 3)); // Movimento válido para a Rainha
        System.out.println(jogador1.fazerMovimento(tabuleiro, 5, 5, 5, 6)); // Movimento válido para o Peão
        System.out.println(jogador1.fazerMovimento(tabuleiro, 5, 6, 4, 7)); // Movimento válido para o Peão (captura diagonal)
        System.out.println(jogador2.fazerMovimento(tabuleiro, 3, 3, 5, 5)); // Movimento inválido para o Cavalo
        System.out.println(jogador2.fazerMovimento(tabuleiro, 4, 4, 4, 7)); // Movimento inválido para a Torre (movimento longo sem checar obstruções)
    }
}
