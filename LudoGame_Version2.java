import java.util.*;

public class LudoGame {
    public static final int WIN_POSITION = 25; // Demo size
    private List<String> players = new ArrayList<>();
    private Map<String, Integer> positions = new HashMap<>();
    private int currentPlayer = 0;
    private boolean finished = false;
    private String winner = "";

    public LudoGame(List<String> playerNames) {
        this.players.addAll(playerNames);
        for (String p : playerNames) positions.put(p, 0);
    }

    public String getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public Map<String, Integer> getPositions() {
        return positions;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getWinner() {
        return winner;
    }

    public int rollDiceAndMove() {
        if (finished) return 0;
        String player = getCurrentPlayer();
        int roll = new Random().nextInt(6) + 1;
        int pos = positions.get(player);
        if (pos + roll <= WIN_POSITION) pos += roll;
        positions.put(player, pos);
        if (pos == WIN_POSITION) {
            finished = true;
            winner = player;
        }
        currentPlayer = (currentPlayer + 1) % players.size();
        return roll;
    }

    public void reset() {
        for (String p : players) positions.put(p, 0);
        currentPlayer = 0;
        finished = false;
        winner = "";
    }
}