package com.everis.actions.mainframe;

import net.serenitybdd.screenplay.Performable;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Emulator {
    public enum ActionsPrints {
        PRINT, ENTER, TAB, WAITINPUTDATA
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static Performable withPrints(ActionsPrints actionsPrints) {
        switch (actionsPrints) {
            case PRINT:
                return instrumented(PrintConsoleScreen.class);
            case ENTER:
                return instrumented(Enter.class);
            case TAB:
                return instrumented(Tab.class);
            case WAITINPUTDATA:
                return instrumented(WaitInputData.class);
            default:
                throw new UnsupportedOperationException("Unsopported " + actionsPrints);
        }


    }
}