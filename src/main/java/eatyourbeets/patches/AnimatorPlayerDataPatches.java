package eatyourbeets.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import eatyourbeets.interfaces.delegates.ActionT2;
import eatyourbeets.resources.GR;
import eatyourbeets.resources.animator.AnimatorPlayerData;
import eatyourbeets.resources.animator.misc.AnimatorLoadout;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Bleach;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_Rewrite;
import eatyourbeets.resources.animatorbeta.loadouts.Loadout_RozenMaiden;

public class AnimatorPlayerDataPatches
{
    @SpirePatch(clz = AnimatorPlayerData.class, method = "AddBetaLoadouts")
    public static class AnimatorPlayerData_AddBetaLoadouts
    {
        @SpirePostfixPatch
        public static void Postfix(AnimatorPlayerData __instance)
        {
            ActionT2<AnimatorLoadout, Integer> add = (loadout, unlockLevel) -> {
                if (loadout.IsEnabled) {
                    GR.Animator.Data.BetaLoadouts.add(loadout);
                    loadout.IsBeta = true;
                    loadout.UnlockLevel = unlockLevel;
                    loadout.AddStarterCards();
                }

            };

            add.Invoke(new Loadout_Bleach(), 0);
            add.Invoke(new Loadout_Rewrite(), 0);
            add.Invoke(new Loadout_RozenMaiden(), 0);
        }
    }
}
