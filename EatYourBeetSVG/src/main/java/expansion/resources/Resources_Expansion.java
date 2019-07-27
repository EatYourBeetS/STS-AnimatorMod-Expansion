package expansion.resources;

import basemod.BaseMod;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import eatyourbeets.resources.AbstractResources;

public class Resources_Expansion extends AbstractResources
{
    private static String languagePath = null;

    private static void LoadLanguagePath()
    {
        if (languagePath != null)
        {
            return;
        }

//        if (Settings.language == Settings.GameLanguage.ZHT)
//        {
//            languagePath = "localization/animator/expansion/zht/";
//        }
//        else if (Settings.language == Settings.GameLanguage.ZHS)
//        {
//            languagePath = "localization/animator/expansion/zhs/";
//        }
//        else if (Settings.language == Settings.GameLanguage.FRA)
//        {
//            languagePath = "localization/animator/expansion/fra/";
//        }
//        else
//        {
            languagePath = "localization/animator/expansion/eng/";
//        }
    }

    @Override
    protected void InitializeCards()
    {
LoadCustomCards("animator");
    }

    @Override
    protected void InitializeStrings()
    {
        LoadLanguagePath();

        BaseMod.loadCustomStringsFile(CardStrings.class, languagePath + "CardStrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, languagePath + "PotionStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, languagePath + "PowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, languagePath + "RelicStrings.json");
    }
}