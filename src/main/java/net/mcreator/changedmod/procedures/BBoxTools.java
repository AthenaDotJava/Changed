package net.mcreator.changedmod.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BBoxTools {
    public static VoxelShape toVoxelShape(int[] arr){
        return Block.box(arr[0], arr[1],arr[2],arr[3],arr[4],arr[5]);
    }
    public static int[] swapHorizontalAxis(int[] in) {
        int[] arr = in.clone();
        // Swap the first and third elements
        int temp = arr[0];
        arr[0] = arr[2];
        arr[2] = temp;

        // Swap the fourth and sixth elements
        temp = arr[3];
        arr[3] = arr[5];
        arr[5] = temp;

        return arr;
    }
    public static int[] flipX(int[] in) {
        int[] arr = in.clone();
        // Subtract the first element from 16
        arr[0] = 16 - arr[0];

        // Subtract the fourth element from 16
        arr[3] = 16 - arr[3];

        // Swap the first and fourth elements
        int temp = arr[0];
        arr[0] = arr[3];
        arr[3] = temp;

        return arr;
    }
    public static int[] flipZ(int[] in) {
        int[] arr = in.clone();
        // Subtract the third element from 16
        arr[2] = 16 - arr[2];

        // Subtract the sixth element from 16
        arr[5] = 16 - arr[5];

        // Swap the third and sixth elements
        int temp = arr[2];
        arr[2] = arr[5];
        arr[5] = temp;

        return arr;
    }
}
