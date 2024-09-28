import java.util.Scanner;

public class JogoDaVelha {
    private static final Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] jogo = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        boolean finalizou = false;
        String tipoFim = "";
        char marcaVencedora = '-';

        System.out.println("BEM VINDO AO JOGO DA VELHA!!\n");

        while (!finalizou) {
            int x, y;
            char marca;
            String inputMarca;

            mostrarJogo(jogo);

            System.out.println("\nEscolha a linha que você quer jogar, de 1 a 3:");
            x = leitor.nextInt();

            if (posicaoInvalida(x)) {
                continue;
            }

            System.out.println("\nEscolha a coluna que você quer jogar, de 1 a 3:");
            y = leitor.nextInt();

            if (posicaoInvalida(y)) {
                continue;
            }

            System.out.println("\nEscolha a marca que você quer jogar, X ou O:");
            inputMarca = leitor.next();

            if (!inputMarca.equalsIgnoreCase("X") && !inputMarca.equalsIgnoreCase("O")) {
                System.out.println("\nEscolha uma marca válida!!!\nRecomeçando...\n");
                continue;
            }

            marca = inputMarca.toUpperCase().charAt(0);

            if (jogo[x - 1][y - 1] != '-') {
                System.out.println("\nPosição já ocupada, escolha outra!\n");
                continue;
            }

            jogo[x - 1][y - 1] = marca;

            if(validarEmpate(jogo)) {
                finalizou = true;
                continue;
            }

            if(validarFim(marca, jogo)) {
                tipoFim = "VITORIA";
                marcaVencedora = marca;
                finalizou = true;
            }
        }

        mostrarJogo(jogo);

        if(tipoFim.equals("VITORIA")) {
            System.out.printf("\nParabens!!!\nA marca vencedora foi: %c!", marcaVencedora);
        } else {
            System.out.println("O jogo terminou em empate!");
        }
    }

    private static void mostrarJogo(char[][] jogo) {
        for (char[] ints : jogo) {
            System.out.printf("[%c %c %c]%n", ints[0], ints[1], ints[2]);
        }
    }

    private static boolean posicaoInvalida(int pos) {
        if (pos < 1 || pos > 3) {
            System.out.println("\nEscolha um número valido!\nRecomeçando...\n");
            return true;
        } else {
            return false;
        }
    }

    private static boolean validarFim(char marca, char[][] jogo) {
        return validarHorizontal(marca, jogo) || validarVertical(marca, jogo) || validarDiagonais(marca, jogo);
    }

    private static boolean validarHorizontal(char marca, char[][] jogo) {
        for (char[] linha : jogo) {
            int quantidade = 0;

            for (char c : linha) {
                if (c == marca) {
                    quantidade += 1;
                }
            }

            if (quantidade == 3) {
                return true;
            }
        }

        return false;
    }

    private static boolean validarVertical(char marca, char[][] jogo) {
        for (int i = 0; i < 3; i++) {
            char primeiro = jogo[0][i];
            char segundo = jogo[1][i];
            char terceiro = jogo[2][i];

            if (primeiro == marca && segundo == marca && terceiro == marca) {
                return true;
            }
        }

        return false;
    }

    private static boolean validarDiagonais(char marca, char[][] jogo) {
        char meio = jogo[1][1];

        boolean diagonalValida = (jogo[0][0] == marca) && (meio == marca) && (jogo[2][2] == marca);
        boolean diagonalInversaValida = (jogo[0][2] == marca) && (meio == marca) && (jogo[2][0] == marca);

        return diagonalValida || diagonalInversaValida;
    }

    private static boolean validarEmpate(char[][] jogo) {
        int vazios = 0;

        for (char[] linha : jogo) {
            for (char c : linha) {
                if(c == '-'){
                    vazios += 1;
                }
            }
        }

        return vazios == 0;
    }
}
