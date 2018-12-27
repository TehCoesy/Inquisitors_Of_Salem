package Utility;

import java.util.ArrayList;
import java.util.Random;

public class Ballot {
    private int _count;
    private ArrayList<Integer> _pool;
    private Random random = new Random();

    public Ballot(int count) {
        _pool = new ArrayList<>();

        _count = count;

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(_count);

            while (_pool.contains(index)) {
                index = random.nextInt(_count);
            }

            _pool.add(new Integer(index));
        }
    }

    public int getBallot() throws Exception {
        if (_pool.isEmpty()) {
            throw new Exception("Ballot empty.");
        }

        int _ballot = _pool.get(0);
        _pool.remove(0);
        return _ballot;
    }

    public boolean isEmpty() {
        return _pool.isEmpty();
    }
}
