package eatyourbeets.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import eatyourbeets.cards.animator.colorless.rare.KotoriKanbe;
import eatyourbeets.cards.animator.colorless.uncommon.IchigoKurosaki;
import eatyourbeets.cards.animator.special.IchigoKurosaki_Bankai;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.resources.GR;
import eatyourbeets.utilities.GameUtilities;

public class CardLibraryPatches
{
    public static void TryReplace(AbstractCard[] card)
    {
        if (GameUtilities.IsPlayerClass(GR.Animator.PlayerClass))
        {
            EYBCardData data = GetReplacement(card[0].cardID);
            if (data != null)
            {
                card[0] = data.MakeCopy(card[0].upgraded);
            }
        }
    }

    // Replace duplicate colorless cards from the official mod only if beta series are enabled
    public static EYBCardData GetReplacement(String cardID)
    {
        if (IchigoKurosaki.DATA.ID.equals(cardID))
        {
            return eatyourbeets.cards.animator.series.Bleach.IchigoKurosaki.DATA;
        }
        if (IchigoKurosaki_Bankai.DATA.ID.equals(cardID))
        {
            return eatyourbeets.cards.animator.special.IchigoKurosaki_Bankai.DATA;
        }
        if (KotoriKanbe.DATA.ID.equals(cardID))
        {
            return eatyourbeets.cards.animator.series.Rewrite.KotoriKanbe.DATA;
        }
        return null;
    }

    @SpirePatch(
            clz = CardLibrary.class,
            method = "getCard",
            paramtypez = {String.class}
    )
    public static class CardLibraryPatches_GetCard
    {
        public CardLibraryPatches_GetCard()
        {
        }

        @SpirePrefixPatch
        public static SpireReturn<AbstractCard> Prefix(String key)
        {
            if (GR.IsLoaded() && GR.Animator.Dungeon != null)
            {
                EYBCardData data = GetReplacement(key);
                if (data != null && GR.Animator.Dungeon.HasBetaSeries)
                {
                    return SpireReturn.Return(data.MakeCopy(false));
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = MakeTempCardInDiscardAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class}
    )
    public static class MakeTempCardInDiscardActionPatches_Ctor
    {
        public MakeTempCardInDiscardActionPatches_Ctor()
        {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInDiscardAction __instance, @ByRef AbstractCard[] card, int amount)
        {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInDrawPileAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class, boolean.class, boolean.class, boolean.class, float.class, float.class}
    )
    public static class MakeTempCardInDrawPileActionPatches_Ctor
    {
        public MakeTempCardInDrawPileActionPatches_Ctor()
        {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInDrawPileAction __instance, @ByRef AbstractCard[] card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY)
        {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, int.class}
    )
    public static class MakeTempCardInHandActionPatches_Ctor2
    {
        public MakeTempCardInHandActionPatches_Ctor2()
        {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInHandAction __instance, @ByRef AbstractCard[] card, int amount)
        {
            TryReplace(card);
        }
    }

    @SpirePatch(
            clz = MakeTempCardInHandAction.class,
            method = "<ctor>",
            paramtypez = {AbstractCard.class, boolean.class}
    )
    public static class MakeTempCardInHandActionPatches_Ctor1
    {
        public MakeTempCardInHandActionPatches_Ctor1()
        {
        }

        @SpirePrefixPatch
        public static void Prefix(MakeTempCardInHandAction __instance, @ByRef AbstractCard[] card, boolean isOtherCardInCenter)
        {
            TryReplace(card);
        }
    }
}
