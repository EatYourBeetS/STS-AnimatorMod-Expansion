package eatyourbeets.cards.animator.series.Bleach;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.animator.special.IkkakuBankai;
import eatyourbeets.cards.base.*;
import eatyourbeets.powers.AnimatorPower;
import eatyourbeets.powers.CombatStats;
import eatyourbeets.powers.affinity.animator.AgilityPower;
import eatyourbeets.powers.affinity.animator.ForcePower;
import eatyourbeets.stances.ForceStance;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;

public class IkkakuMadarame extends AnimatorCard
{
    public static final EYBCardData DATA = Register(IkkakuMadarame.class)
            .SetAttack(2, CardRarity.COMMON, EYBAttackType.Normal, EYBCardTarget.ALL)
            .PostInitialize(
                    data ->
                            data.AddPreview(new ZarakiKenpachi(), false).AddPreview(new IkkakuBankai(), false));

    public IkkakuMadarame()
    {
        super(DATA);

        Initialize(12, 0, 0, 0);
        SetUpgrade(2, 0, 0);
        SetAffinity_Red(2, 0, 1);
        SetAffinity_Green(0, 0, 1);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.DealDamageToAll(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        if (ForceStance.IsActive())
        {
            GameActions.Bottom.ObtainAffinityToken(Affinity.Red, upgraded);
        }
        else
        {
            GameActions.Bottom.MakeCardInDrawPile(new IkkakuBankai());
            GameActions.Last.ModifyAllInstances(uuid).AddCallback(GameActions.Bottom::Exhaust);
        }
    }
}