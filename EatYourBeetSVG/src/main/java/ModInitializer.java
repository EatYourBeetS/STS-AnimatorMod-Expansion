import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.ui.MultiPageFtue;
import eatyourbeets.actions.common.WaitRealtimeAction;
import eatyourbeets.effects.CallbackEffect;
import expansion.resources.Resources_Expansion;
import eatyourbeets.cards.AnimatorCard;
import eatyourbeets.characters.AnimatorMetrics;
import eatyourbeets.powers.PlayerStatistics;
import eatyourbeets.relics.animator.CursedBlade;
import eatyourbeets.relics.animator.PurgingStone_Cards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patches.AbstractEnums;

import java.util.ArrayList;

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
        logger.info("ModInitializer()");


        Resources_Expansion.Initialize();
    }



}