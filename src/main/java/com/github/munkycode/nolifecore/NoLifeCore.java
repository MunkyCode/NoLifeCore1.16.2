package com.github.munkycode.nolifecore;


import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NoLifeCore.MODID)
public class NoLifeCore {
    public static final String MODID = "nolifecore";
    public static final Logger LOGGER = LogManager.getLogger();

    public NoLifeCore(){
        LOGGER.debug("Get A Life");
    }

}
