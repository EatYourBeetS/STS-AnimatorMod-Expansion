import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import expansion.resources.Resources_Expansion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ModInitializer
{
    private static final Logger logger = LogManager.getLogger(ModInitializer.class.getName());

    public static void initialize()
    {
        // Entry Point
        new ModInitializer();
    }

    private ModInitializer()
    {
        Resources_Expansion.Initialize();
    }
}