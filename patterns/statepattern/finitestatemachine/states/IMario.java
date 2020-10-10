package statepattern.finitestatemachine.states;

import statepattern.finitestatemachine.MarioStateMachine;
import statepattern.finitestatemachine.State;

public interface IMario {
    State getName();

    void obtainMushRoom(MarioStateMachine stateMachine);

    void obtainCape(MarioStateMachine stateMachine);

    void obtainFireFlower(MarioStateMachine stateMachine);

    void meetMonster(MarioStateMachine stateMachine);
}
