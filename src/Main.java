import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int[] dx = {1, -1, 0, 0}; // Movimientos posibles.
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        int[][] laberinto = {
                {0, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {1, 0, 0, 0}
        };
        if (!resolverLaberinto(laberinto)) {
            System.out.println("No se encontró un camino.");
        }
    }

    private static void imprimirCamino(List<int[]> camino) {
        System.out.println("Camino encontrado:");
        for (int[] paso : camino) {
            System.out.println("(" + paso[0] + ", " + paso[1] + ")");
        }
    }

    private static boolean resolverLaberinto(int[][] laberinto) {
        int n = laberinto.length;
        int m = laberinto[0].length;
        // Lista para almacenar el camino.
        List<int[]> camino = new ArrayList<>();

        boolean resuelto = recorrer(laberinto, 0, 0, n, m, camino);

        if (resuelto) {
            imprimirCamino(camino);
        }

        return resuelto;
    }

    private static boolean recorrer(int[][] laberinto, int x, int y, int n, int m, List<int[]> camino) {

        // Si ocurre cualquier entrada inválida o encuentra una pared, return false.
        if (x < 0 || y < 0 || x >= n || y >= m || laberinto[x][y] == 1) {
            return false;
        }

        // Condición de término si la matriz se resolvió correctamente.
        if (x == n - 1 && y == m - 1) {
            camino.add(new int[]{x, y});
            return true;
        }

        laberinto[x][y] = 1;  // Marca como visitado
        camino.add(new int[]{x, y});  // Añade al camino

        for (int i = 0; i < 4; i++) {
            if (recorrer(laberinto, x + dx[i], y + dy[i], n, m, camino)) {
                return true;
            }
        }

        camino.remove(camino.size() - 1);  // Elimina del camino
        laberinto[x][y] = 0;  // Desmarca
        return false;
    }
}