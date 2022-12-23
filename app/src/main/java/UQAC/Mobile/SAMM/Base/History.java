package UQAC.Mobile.SAMM.Base;

import java.util.List;

import UQAC.Mobile.SAMM.Base.Event;

public class History {
    private List<Event> history;

    public History(List<Event> history){
        this.history = history;
    }
    public History(){
        this.history = null;
    }

    public List<Event> getHistory() {
        return history;
    }

    public void setHistory(List<Event> history) {
        this.history = history;
    }
}
