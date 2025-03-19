package menu;

import actions.Action;

import java.util.List;

public class NotLoggedMenu extends MenuAction {
    public NotLoggedMenu(List<Action> actions) {
        super(actions);
    }

    @Override
    public String getIntro() {
        return "  _____         _                   _                __  __\n" +
                " / ____|       | |                 | |              |  \\/  |\n" +
                "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __\n" +
                "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|\n" +
                "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |\n" +
                " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|\n" +
                "                                                                                   __/ |\n" +
                "                                                                                  |___/\n";
    }
}