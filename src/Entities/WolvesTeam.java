package Entities;

import Core.EntityContainer;

import java.util.ArrayList;
import java.util.Random;

public class WolvesTeam {
    private Random _rand = new Random();
    private EntityContainer _container;
    private ArrayList<Entity> _wolves;

    public WolvesTeam(EntityContainer container) {
        this._container = container;
        //this._wolves = container._enemies;
    }

    public void onAction() {
        Entity _target = null;
        boolean _decided = false;

        while (!_decided) {
            int _targetID = _rand.nextInt(_container._allEntities.size());

            _target = _container._allEntities.get(_targetID);

            if (!isTeamMate(_target)) {
                _decided = true;
            }
        }

        if (_target != null) {
            _target._harmed = true;
        }
    }

    private boolean isTeamMate(Entity _target) {
        for (Entity _teamMate : _container._enemies) {
            if (_target.getID() == _teamMate.getID()) {
                return true;
            }
        }
        return false;
    }
}
