package net.mcreator.changedmod.utils;

import net.minecraft.nbt.Tag;
import net.minecraft.util.StringRepresentable;

public enum CardTypes implements StringRepresentable{
    RED,
    BLUE,
    GREEN,
    YELLOW,
    PINK,
    PURPLE,
    WHITE,

    NONE;

    @Override
    public String getSerializedName() {
        switch (this) {
            case RED:
                return "red";
            case BLUE:
                return "blue";
            case PINK:
                return "pink";
            case GREEN:
                return "green";
            case YELLOW:
                return "yellow";
            case PURPLE:
                return "purple";
            case NONE:
                return "none";
            default:
                return "white";
        }
    }
}
