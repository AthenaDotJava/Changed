package net.mcreator.changedmod.block;

import net.minecraft.util.StringRepresentable;

public enum CapsuleEnumStates implements StringRepresentable {

    UP, DOWN;
    @Override
    public String toString(){
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        return this == UP ? "up" : "down";
    }
}
