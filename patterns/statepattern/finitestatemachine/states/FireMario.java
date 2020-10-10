package statepattern.finitestatemachine.states;

import statepattern.finitestatemachine.MarioStateMachine;
import statepattern.finitestatemachine.State;

public class FireMario implements IMario {
    private static final FireMario instance = new FireMario();

    private FireMario() {
    }

    public static FireMario getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
        // nothing happened
    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {
        // nothing happened
    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {
        // nothing happened
    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {
        stateMachine.setCurrentState(SmallMario.getInstance());
        stateMachine.setScore(stateMachine.getScore() - 300);
    }
}
