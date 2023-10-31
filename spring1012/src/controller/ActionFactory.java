package controller;

import action.Action;
import action.FBoardAction;

// 모델을 관리하는 관리 객체. 'Action' + 'Factory'
public class ActionFactory {
	private static ActionFactory factory;
	private ActionFactory() {}
	
//	싱글톤 패턴.
	public synchronized static ActionFactory getFactory() {
		if(factory == null)
			factory = new ActionFactory();
		return factory;
	}
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("fboard")) {
			action = new FBoardAction();
		}
		return action;
	}
	
	
}
