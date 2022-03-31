package com.taahyt.pingu.chunk;

import com.taahyt.pingu.block.Block;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import net.querz.nbt.tag.CompoundTag;

@Getter
public class ChunkData {

    private CompoundTag heightMaps = new CompoundTag();
    private Map<Integer, Block> blockEntities = new HashMap<>();
}
