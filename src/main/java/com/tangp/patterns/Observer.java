package com.tangp.patterns;

import java.util.LinkedList;
import java.util.List;

/**
 * 观察者模式；
 * spring框架大量使用了这个模式，比如：ApplicationContextAware、InitializingBean、ResourceLoaderAware
 * 各种Aware；
 */
public class Observer {

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        fireNewState();
    }

    /**
     * 状态更新时，对所有的订阅者推送变更；
     */
    private void fireNewState() {
        if (!stateObserverList.isEmpty()) {
            for (StateObserver observer : stateObserverList) {
                observer.onStateUpdate(this.state);
            }
        }
    }


    /**
     * 用来保存所有的订阅者/观察者
     */
    private final List<StateObserver> stateObserverList = new LinkedList<>();

    /**
     * 用来注册新的观察者
     *
     * @param stateObserver
     */
    public void observe(StateObserver stateObserver) {
        this.stateObserverList.add(stateObserver);
    }

    /**
     * 定义一个接口，用来订阅状态变更；
     */
    interface StateObserver {
        void onStateUpdate(int newState);
    }
}
