package statepattern.finitestatemachine;

import statepattern.finitestatemachine.states.IMario;
import statepattern.finitestatemachine.states.SmallMario;

public class MarioStateMachine {
    private int score;
    private IMario currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    /**
     * 吃蘑菇事件
     */
    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    /**
     * 获得披风事件
     */
    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    /**
     * 获得火焰花事件
     */
    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    /**
     * 遇到怪物事件
     */
    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState.getName();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }
}
