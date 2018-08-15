package net.karolek.revoguild.enums;

import net.karolek.revoguild.data.Messages;

public enum NotifyType {

    GUILD(Messages.INFO_GUILD_MOVE_IN, Messages.INFO_GUILD_MOVE_OUT),
    GUILD_ALLIANCE(Messages.INFO_GUILD$ALLIANCE_MOVE_IN, Messages.INFO_GUILD$ALLIANCE_MOVE_OUT),
    GUILD_ENEMY(Messages.INFO_GUILD$ENEMY_MOVE_IN, Messages.INFO_GUILD$ENEMY_MOVE_OUT);

    public String getMessageIn() {
        return messageIn;
    }

    public String getMessageOut() {
        return messageOut;
    }

    private final String messageIn;
    private final String messageOut;

    NotifyType(String messageIn, String messageOut) {
        this.messageIn = messageIn;
        this.messageOut = messageOut;
    }

}
