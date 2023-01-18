package eatyourbeets.cards.animator.series.RozenMaiden;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.cards.base.Affinity;
import eatyourbeets.cards.base.AnimatorCard;
import eatyourbeets.cards.base.CardUseInfo;
import eatyourbeets.cards.base.EYBCardData;
import eatyourbeets.effects.AttackEffects;
import eatyourbeets.powers.common.DelayedDamagePower;
import eatyourbeets.utilities.BetaActions;
import eatyourbeets.utilities.GameActions;
import eatyourbeets.utilities.GameUtilities;
import eatyourbeets.utilities.TargetHelper;

public class MeguKakizaki extends AnimatorCard
{
    public static final EYBCardData DATA = Register(MeguKakizaki.class)
            .SetSkill(1, AbstractCard.CardRarity.UNCOMMON, eatyourbeets.cards.base.EYBCardTarget.None);

    public MeguKakizaki()
    {
        super(DATA);

        Initialize(0, 8, 6, 3);
        SetUpgrade(0, 2, 5, 0);
        SetAffinity_Light(1, 1, 1);
        SetAffinity_Dark(1, 0, 0);

        SetAffinityRequirement(Affinity.Dark, 3);

        SetEthereal(true);
    }

    @Override
    public void OnUse(AbstractPlayer p, AbstractMonster m, CardUseInfo info)
    {
        GameActions.Bottom.GainBlock(block).AddCallback(() -> {
            int toTransfer = Math.min(magicNumber, GameUtilities.GetPowerAmount(DelayedDamagePower.POWER_ID));
            BetaActions.Bottom.ReducePower(player, player, DelayedDamagePower.POWER_ID, toTransfer);
            AbstractMonster mo = GameUtilities.GetRandomEnemy(true);
            if (mo != null && toTransfer > 0)
            {
                GameActions.Bottom.DealDamageAtEndOfTurn(player, mo, toTransfer, AttackEffects.CLAW);
            }
        });
    }

    @Override
    public void Refresh(AbstractMonster enemy)
    {
        super.Refresh(enemy);

        heal = GameUtilities.GetPowerAmount(DelayedDamagePower.POWER_ID) > 0 ? block : 0;
    }

    @Override
    public void triggerOnExhaust()
    {
        GameActions.Bottom.Callback(() ->
        {
            if (TryUseAffinity(Affinity.Dark))
            {
                GameActions.Bottom.ApplyVulnerable(TargetHelper.Enemies(), secondaryValue);
            }
        });
    }
}

