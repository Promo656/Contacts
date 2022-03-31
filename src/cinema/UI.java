package cinema;

class UI {
    int[][] room;

    UI(int rows, int seatsPerRows) {
        this.room = new int[rows][seatsPerRows];
    }

    void bookASeat(int row, int seat) {
        room[row - 1][seat - 1] = 1;
    }

    boolean isAvailableSeat(int row, int seat) {
        return room[row - 1][seat - 1] == 0;
    }

    void print() {
        System.out.print("\n");
        System.out.println("Cinema:");
        for (int i = 0; i < room[0].length + 1; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < room.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j] == 0 ? "S " : "B ");
            }
            System.out.print("\n");
        }
    }
}