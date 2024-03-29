package eatyourbeets.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.animator.AnimatorDungeonData;

import java.util.ArrayList;

public class AnimatorDungeonDataPatches
{
    @SpirePatch(clz = AnimatorDungeonData.class, method = "InitializeCardPool")
    public static class AnimatorPlayerData_InitializeCardPool
    {
        @SpirePostfixPatch
        public static void Postfix(AnimatorDungeonData __instance, boolean startGame)
        {
            // Only add Beta Colorless cards if beta series are not enabled
            if (GR.Animator.Dungeon.HasBetaSeries)
            {
                ArrayList<CardGroup> groups = new ArrayList<>();
                groups.add(AbstractDungeon.colorlessCardPool);
                groups.add(AbstractDungeon.srcColorlessCardPool);
                for (AbstractCard card : CardLibrary.getAllCards())
                {
                    if (card instanceof AnimatorCard && card.color == AbstractCard.CardColor.COLORLESS)
                    {
                        for (CardGroup group : groups)
                        {
                            group.group.add(card.makeCopy());
                        }
                    }
                }
            }
        }
    }
}
