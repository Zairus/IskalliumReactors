package zairus.iskalliumreactors;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class IRConfig
{
	public static int eachIskalliumBlockPowerValue = 160;
	
	public static int iskalliumGenerationWeight = 60;
	public static int iskalliumGenerationPatchSize = 1;
	public static boolean logIskalliumOreGeneration = false;
	
	public static Configuration configuration;
	
	public static void init(File cFile)
	{
		configuration = new Configuration(cFile);
		
		configuration.load();
		
		eachIskalliumBlockPowerValue = configuration.getInt("eachIskalliumBlockPowerValue", "ENERGY_VALUES", eachIskalliumBlockPowerValue, 0, 255, "How much energy each Iskallium Block provides");
		
		iskalliumGenerationWeight = configuration.getInt("iskalliumGenerationWeight", "WORLD_GENERATION", iskalliumGenerationWeight, 0, 255, "Weight for ore generation of Iskallium");
		iskalliumGenerationPatchSize = configuration.getInt("iskalliumGenerationPatchSize", "WORLD_GENERATION", iskalliumGenerationPatchSize, 1, 255, "Maximum patch size of Iskallium generated");
		logIskalliumOreGeneration = configuration.getBoolean("logIskalliumOreGeneration", "GENERAL", logIskalliumOreGeneration, "Set to true to see information in the logs about Iskallium Ore generation position");
		
		configuration.save();
	}
}
